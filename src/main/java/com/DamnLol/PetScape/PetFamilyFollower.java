/*
 * Copyright (c) 2026 - DamnLol-GIT
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.DamnLol.PetScape;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;
import java.util.Arrays;

@Slf4j
public class PetFamilyFollower
{
    public enum FamilySize
    {
        NONE("None", 0),
        ONE("1", 1),
        TWO("2", 2),
        THREE("3", 3);

        private final String label;
        @Getter private final int count;

        FamilySize(String label, int count) { this.label = label; this.count = count; }

        @Override public String toString() { return label; }
    }

    // Each clone is 75% of the one ahead - *96 = 75% of 128, 72 = 75% of 96, 54 = 75% of 72*
    private static final int[] CHAIN_SCALES = {96, 72, 54};
    private static final int MOVE_SPEED = 4;

    private final Client client;
    private final ClientThread clientThread;
    @Getter private final int chainLength;

    @Getter
    private NPC realNpc;
    private final RuneLiteObject[] cloneObjects;
    private final boolean[] modelSet;
    private final WorldPoint[] targetWorlds;
    private final LocalPoint[] targetLocals;
    private final WorldPoint[] pendingCloneWorld;

    // Capture the foot position for the current animation frame so breathing stops clipping
    private final float[] cloneMaxFootY;

    private WorldPoint prevNpcWorld = null;
    private int lastCompositionId = -1;
    private int cachedNpcId = -1;
    private int npcTileSize = 1;
    private final java.util.ArrayDeque<WorldPoint> npcStepHistory = new java.util.ArrayDeque<>();

    public PetFamilyFollower(NPC realNpc, Client client, ClientThread clientThread, int chainLength)
    {
        this.realNpc = realNpc;
        this.client = client;
        this.clientThread = clientThread;
        this.chainLength = chainLength;

        this.cloneObjects = new RuneLiteObject[chainLength];
        this.modelSet = new boolean[chainLength];
        this.targetWorlds = new WorldPoint[chainLength];
        this.targetLocals = new LocalPoint[chainLength];
        this.pendingCloneWorld = new WorldPoint[chainLength];
        this.cloneMaxFootY = new float[chainLength];

        NPCComposition comp = realNpc.getComposition();
        this.lastCompositionId = comp != null ? comp.getId() : -1;
        this.cachedNpcId = realNpc.getId();
        this.npcTileSize = resolveNpcTileSize(realNpc);
        this.prevNpcWorld = realNpc.getWorldLocation();

        // Seed maxFootY with static correction from Z_OVERRIDES
        int baseZOffset = Math.max(0, PetScapeGhost.Z_OVERRIDES.getOrDefault(realNpc.getId(), 0));
        Arrays.fill(cloneMaxFootY, baseZOffset);

        // Pre-place each clone behind the pet using player direction
        Player localPlayer = client.getLocalPlayer();
        WorldPoint playerPos = localPlayer != null ? localPlayer.getWorldLocation() : null;
        int dirX = playerPos != null ? Integer.signum(prevNpcWorld.getX() - playerPos.getX()) : -1;
        int dirY = playerPos != null ? Integer.signum(prevNpcWorld.getY() - playerPos.getY()) : 0;
        if (dirX == 0 && dirY == 0) dirX = -1;

        LocalPoint petLp = LocalPoint.fromWorld(client.getTopLevelWorldView(), prevNpcWorld);

        for (int i = 0; i < chainLength; i++)
        {
            cloneObjects[i] = client.createRuneLiteObject();
            initModel(i);

            WorldPoint spawnPos = new WorldPoint(
                    prevNpcWorld.getX() + (i + npcTileSize) * dirX,
                    prevNpcWorld.getY() + (i + npcTileSize) * dirY,
                    prevNpcWorld.getPlane());
            pendingCloneWorld[i] = spawnPos;
            LocalPoint spawnLp = LocalPoint.fromWorld(client.getTopLevelWorldView(), spawnPos);
            if (spawnLp == null) spawnLp = petLp;
            if (spawnLp != null)
            {
                cloneObjects[i].setLocation(spawnLp, client.getPlane());
                int tileZ = Perspective.getTileHeight(client, spawnLp, client.getPlane());
                cloneObjects[i].setZ(tileZ - (int)cloneMaxFootY[i]);
            }
            cloneObjects[i].setActive(true);
        }
    }

    public void gameTick()
    {
        NPCComposition comp = realNpc.getComposition();
        int compId = comp != null ? comp.getId() : -1;
        int npcId = realNpc.getId();
        if (compId != lastCompositionId || npcId != cachedNpcId)
        {
            lastCompositionId = compId;
            cachedNpcId = npcId;
            npcTileSize = resolveNpcTileSize(realNpc);
            npcStepHistory.clear();
            int newBase = Math.max(0, PetScapeGhost.Z_OVERRIDES.getOrDefault(npcId, 0));
            for (int i = 0; i < chainLength; i++)
                if (cloneMaxFootY[i] == 0) cloneMaxFootY[i] = newBase;
            Arrays.fill(modelSet, false);
        }

        for (int i = 0; i < chainLength; i++)
            if (!modelSet[i]) initModel(i);

        WorldPoint petNow = realNpc.getWorldLocation();
        if (prevNpcWorld != null && !petNow.equals(prevNpcWorld))
        {
            // Record where the NPC just stepped from
            npcStepHistory.addLast(prevNpcWorld);
            while (npcStepHistory.size() > npcTileSize + chainLength) npcStepHistory.removeFirst();

            // Clone target npcTileSize+i steps back
            WorldPoint[] steps = npcStepHistory.toArray(new WorldPoint[0]);
            for (int i = 0; i < chainLength; i++)
            {
                int histIdx = steps.length - npcTileSize - i;
                WorldPoint target = histIdx >= 0 ? steps[histIdx] : steps[steps.length - 1];
                LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), target);
                if (lp != null) { pendingCloneWorld[i] = target; targetWorlds[i] = target; targetLocals[i] = lp; }
            }
        }
        prevNpcWorld = petNow;
    }

    public void clientTick()
    {
        for (int i = 0; i < chainLength; i++) clientTickForClone(i);
    }

    private void clientTickForClone(int i)
    {
        RuneLiteObject obj = cloneObjects[i];
        if (!obj.isActive()) return;

        // Refresh model from live NPC every frame
        Model live = realNpc.getModel();
        if (live != null)
        {
            Model snapshot = client.mergeModels(live);
            if (snapshot != null)
            {
                int count = snapshot.getVerticesCount();
                if (count > 0)
                {
                    float s = CHAIN_SCALES[i] / 128.0f;
                    float[] vx = snapshot.getVerticesX();
                    float[] vy = snapshot.getVerticesY();
                    float[] vz = snapshot.getVerticesZ();
                    float maxY = 0;
                    for (int j = 0; j < count; j++)
                    {
                        vx[j] *= s;
                        vy[j] *= s;
                        vz[j] *= s;
                        if (vy[j] > maxY) maxY = vy[j];
                    }
                    cloneMaxFootY[i] = maxY;
                    obj.setModel(snapshot);
                    if (!modelSet[i]) { obj.setShouldLoop(true); modelSet[i] = true; }
                }
            }
        }

        // Face toward the pet directly ahead in the chain
        LocalPoint objPos = obj.getLocation();
        if (objPos != null)
        {
            LocalPoint aheadLp = (i == 0)
                    ? LocalPoint.fromWorld(client.getTopLevelWorldView(), realNpc.getWorldLocation())
                    : cloneObjects[i - 1].getLocation();
            if (aheadLp != null)
            {
                int ox = aheadLp.getX() - objPos.getX();
                int oy = aheadLp.getY() - objPos.getY();
                if (ox != 0 || oy != 0)
                {
                    double angle = Math.atan2(-ox, -oy);
                    obj.setOrientation((int)(angle / (2 * Math.PI) * 2048 + 2048) % 2048);
                }
            }
        }

        LocalPoint curLp = obj.getLocation();
        if (curLp != null)
        {
            int tileZ = Perspective.getTileHeight(client, curLp, client.getPlane());
            obj.setZ(tileZ - (int)cloneMaxFootY[i]);
        }

        if (targetLocals[i] == null) return;
        LocalPoint current = obj.getLocation();
        if (current == null) return;

        int dx = targetLocals[i].getX() - current.getX();
        int dy = targetLocals[i].getY() - current.getY();

        if (dx == 0 && dy == 0)
        {
            targetWorlds[i] = null;
            targetLocals[i] = null;
            return;
        }

        int stepX = Math.abs(dx) > MOVE_SPEED ? Integer.signum(dx) * MOVE_SPEED : dx;
        int stepY = Math.abs(dy) > MOVE_SPEED ? Integer.signum(dy) * MOVE_SPEED : dy;
        LocalPoint newLp = new LocalPoint(current.getX() + stepX, current.getY() + stepY);
        obj.setLocation(newLp, client.getPlane());
        int moveTileZ = Perspective.getTileHeight(client, newLp, client.getPlane());
        obj.setZ(moveTileZ - (int)cloneMaxFootY[i]);
    }

    public void despawn()
    {
        for (RuneLiteObject obj : cloneObjects) obj.setActive(false);
        Arrays.fill(targetWorlds, null);
        Arrays.fill(targetLocals, null);
        Arrays.fill(pendingCloneWorld, null);
        npcStepHistory.clear();
    }

    // For spawn position clearance — keeps clone outside the original pets tile
    private int resolveNpcTileSize(NPC npc)
    {
        NPCComposition comp = npc.getComposition();
        return comp != null ? Math.max(1, comp.getSize()) : 1;
    }

    private void initModel(int cloneIndex)
    {
        // Try live snapshot first
        Model live = realNpc.getModel();
        if (live != null)
        {
            Model snapshot = client.mergeModels(live);
            if (snapshot != null && snapshot.getVerticesCount() > 0)
            {
                float s = CHAIN_SCALES[cloneIndex] / 128.0f;
                int count = snapshot.getVerticesCount();
                float[] vx = snapshot.getVerticesX();
                float[] vy = snapshot.getVerticesY();
                float[] vz = snapshot.getVerticesZ();
                float maxY = 0;
                for (int j = 0; j < count; j++)
                {
                    vx[j] *= s;
                    vy[j] *= s;
                    vz[j] *= s;
                    if (vy[j] > maxY) maxY = vy[j];
                }
                if (maxY > 0) cloneMaxFootY[cloneIndex] = maxY;
                cloneObjects[cloneIndex].setModel(snapshot);
                cloneObjects[cloneIndex].setShouldLoop(true);
                modelSet[cloneIndex] = true;
                return;
            }
        }

        // Snapshot unavailable — fall back to standard ModelData path
        NPCComposition def = realNpc.getTransformedComposition();
        if (def == null) def = realNpc.getComposition();
        if (def == null) return;

        int[] modelIds = def.getModels();
        if (modelIds == null || modelIds.length == 0) return;

        ModelData[] parts = new ModelData[modelIds.length];
        for (int i = 0; i < modelIds.length; i++)
        {
            parts[i] = client.loadModelData(modelIds[i]);
            if (parts[i] == null) return;
        }

        ModelData merged = client.mergeModels(parts);
        if (merged == null) return;

        short[] cFrom = def.getColorToReplace();
        short[] cTo = def.getColorToReplaceWith();
        if (cFrom != null && cTo != null && cFrom.length > 0)
            for (int i = 0; i < cFrom.length; i++)
                merged.recolor(cFrom[i], cTo[i]);

        int chainScale = CHAIN_SCALES[cloneIndex];
        int ws = def.getWidthScale(); ws = ws > 0 ? ws : 128; ws = ws * chainScale / 128;
        int hs = def.getHeightScale(); hs = hs > 0 ? hs : 128; hs = hs * chainScale / 128;
        merged.cloneVertices();
        merged.scale(ws, hs, ws);

        Model lit = merged.light(64, 850, -30, -50, -30);
        if (lit == null) return;

        cloneObjects[cloneIndex].setModel(lit);
        cloneObjects[cloneIndex].setShouldLoop(true);
        modelSet[cloneIndex] = true;
    }
}
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
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

@Slf4j
public class PetScapeGhost
{
    private static final int GHOST_CLEARANCE = 2;
    private static final int MIN_WANDER_DIST = 2;
    private static final int WANDER_TICKS = 3;
    private static final int MOVE_SPEED = 4;
    private static final int STUCK_TIMEOUT_TICKS = 6;
    private static final int POH_HALF_SIZE = 24;
    private static final int BASE_Z_OFFSET = 0;

    // Z correction applied on top of tile height.
    // Note to self = 128 scene units = 1 game tile. Positive = model moves UP, negative = DOWN.
    private static final java.util.Map<Integer, Integer> Z_OVERRIDES = new java.util.HashMap<>();
    static
    {

    // Fliers — push DOWN
        Z_OVERRIDES.put(NpcID.KREEARRA_JR, -200);
        Z_OVERRIDES.put(NpcID.VESPINA, -150);
        Z_OVERRIDES.put(NpcID.FLYING_VESPINA, -150);
        Z_OVERRIDES.put(NpcID.PHOENIX, -180);
        Z_OVERRIDES.put(NpcID.PHOENIX_3078, -180);
        Z_OVERRIDES.put(NpcID.PHOENIX_3079, -180);
        Z_OVERRIDES.put(NpcID.PHOENIX_3080, -180);
        Z_OVERRIDES.put(NpcID.PHOENIX_7368, -180);
        Z_OVERRIDES.put(NpcID.KALPHITE_PRINCESS_6638, -150);

    // Ground NPCs that sink — push UP
        Z_OVERRIDES.put(NpcID.VORKI, 15);
        Z_OVERRIDES.put(NpcID.SKOTOS, 50);
        Z_OVERRIDES.put(NpcID.SKOTOS_7671, 50);
        Z_OVERRIDES.put(NpcID.VETION_JR, 8);
        Z_OVERRIDES.put(NpcID.VETION_JR_5537, 8);
        Z_OVERRIDES.put(NpcID.VETION_JR_11983, 8);
        Z_OVERRIDES.put(NpcID.VETION_JR_11984, 8);
        Z_OVERRIDES.put(NpcID.VETION_JR_5559, 8);
        Z_OVERRIDES.put(NpcID.YOUNGLLEF, 18);
        Z_OVERRIDES.put(NpcID.YOUNGLLEF_8737, 18);
        Z_OVERRIDES.put(NpcID.CORRUPTED_YOUNGLLEF, 18);
        Z_OVERRIDES.put(NpcID.CORRUPTED_YOUNGLLEF_8738, 18);
        Z_OVERRIDES.put(NpcID.KRIL_TSUTSAROTH_JR, 50);
        Z_OVERRIDES.put(NpcID.PRINCE_BLACK_DRAGON, 25);
        Z_OVERRIDES.put(NpcID.CORPOREAL_CRITTER, 25);
        Z_OVERRIDES.put(NpcID.KALPHITE_PRINCESS, 25);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8493, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8494, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8495, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8517, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8518, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8519, 20);
        Z_OVERRIDES.put(NpcID.IKKLE_HYDRA_8520, 20);
        Z_OVERRIDES.put(NpcID.VANGUARD_8198, 30);
        Z_OVERRIDES.put(NpcID.TUMEKENS_GUARDIAN, 20);
        Z_OVERRIDES.put(NpcID.TUMEKENS_GUARDIAN_11812, 20);
    }

    // Pets that never wander
    private static final Set<Integer> STATIONARY_PET_IDS = new HashSet<>(Arrays.asList(
            NpcID.MAYOR_OF_CATHERBY,
            NpcID.PET_ROCK,
            NpcID.PET_ROCK_6657,
            NpcID.FISHBOWL_6659,
            NpcID.FISHBOWL_6660
    ));

    // Pets that must use snapshot model path (vertex-deformed / Maya)
    private static final Set<Integer> KNOWN_MAYA_IDS = new HashSet<>(Arrays.asList(
            NpcID.BUTCH,
            NpcID.BARON,
            NpcID.WISP,
            NpcID.WISP_12157,
            NpcID.LILVIATHAN,
            NpcID.YAMI,
            NpcID.DOM,
            NpcID.SCURRY,
            NpcID.GULL,
            NpcID.GULL_14931,
            NpcID.GULL_15059,
            NpcID.SMOL_HEREDIT,
            NpcID.HUBERTE,
            NpcID.MOXI,

    // Wilderness pets that exploded mesh
            NpcID.CALLISTO_CUB,
            NpcID.CALLISTO_CUB_11982,
            NpcID.CALLISTO_CUB_5558,
            NpcID.CALLISTO_CUB_11986,
            NpcID.VETION_JR,
            NpcID.VETION_JR_5537,
            NpcID.VETION_JR_11983,
            NpcID.VETION_JR_11984,
            NpcID.VETION_JR_5559
    ));

    // Pets forced idle regardless of Maya detection
    private static final Set<Integer> FORCED_IDLE_ONLY_IDS = new HashSet<>();

    // Pets that must stay on standard loadModelData even if widthScale=0
    private static final Set<Integer> FORCE_STANDARD_IDS = new HashSet<>(Arrays.asList(
            NpcID.YOUNGLLEF,
            NpcID.YOUNGLLEF_8737,
            NpcID.CORRUPTED_YOUNGLLEF,
            NpcID.CORRUPTED_YOUNGLLEF_8738,

            // Tumeken's Guardian + Variants
            NpcID.TUMEKENS_GUARDIAN,
            NpcID.TUMEKENS_GUARDIAN_11812,
            NpcID.TUMEKENS_DAMAGED_GUARDIAN,
            NpcID.ELIDINIS_GUARDIAN,
            NpcID.ELIDINIS_DAMAGED_GUARDIAN,
            NpcID.AKKHITO,
            NpcID.KEPHRITI,
            NpcID.BABI,
            NpcID.ZEBO,

            // Olmlet + Variants
            NpcID.OLMLET,
            NpcID.PUPPADILE,
            NpcID.TEKTINY,
            NpcID.VANGUARD_8198,
            NpcID.VASA_MINIRIO,
            NpcID.VESPINA,
            NpcID.FLYING_VESPINA,

            // Smolcano — snapshot gives no animation in other POHs
            NpcID.SMOLCANO,
            NpcID.SMOLCANO_8739

            ));

    private static boolean isMayaRig(NPC npc)
    {
        if (FORCE_STANDARD_IDS.contains(npc.getId())) return false;
        if (KNOWN_MAYA_IDS.contains(npc.getId())) return true;
        NPCComposition def = npc.getTransformedComposition();
        if (def == null) def = npc.getComposition();
        return def.getWidthScale() == 0 && def.getHeightScale() == 0;
    }

    private static final Random RNG = new Random();

    @Getter private NPC realNpc;
    @Getter private final RuneLiteObject runeLiteObject;

    private final Client client;
    private final ClientThread clientThread;
    private final Collection<PetScapeGhost> allGhosts;
    private final WorldPoint anchor;

    // All walkable POH floor tiles — set lookup for isInSceneBounds
    private final Set<WorldPoint> pohFloor;

    // Each ghost has its own wander anchor to spread across the POH
    private final WorldPoint wanderAnchor;

    @Getter private WorldPoint ghostWorld;

    WorldPoint targetWorld;
    private LocalPoint targetLocal;

    private int lastCompositionId;
    private int idleAnimId;
    private int walkAnimId;
    private boolean idleOnly;
    private boolean snapshotModel;
    private final boolean stationary;
    private final int sameNpcClearance;

    // Per-ghost wander radius — scales with clone count so they spread out
    private final int maxWander;

    private int zOffset;
    private int gameTick;
    private boolean modelSet = false;

    // True when real NPC left render distance but hasn't actually despawned
    private boolean detached = false;

    private int cachedNpcId;
    private WorldPoint lastKnownNpcWorld;

    // Tracks last anim sent — avoids redundant setAnimation calls
    private int currentAnimId = -1;

    private int stuckTicks = 0;
    private WorldPoint lastWorldTile = null;

    //Counts ticks with no target — triggers anchor re-roll when too high
    private int idleTicks = 0;

    // Randomised per ghost so pets don't all move on the same tick
    private int nextWanderTick = 0;

    @Getter private String overheadText;
    private int overheadTicksLeft;
    private int dialogIndex;

    public PetScapeGhost(NPC realNpc, Client client, ClientThread clientThread,
                         Collection<PetScapeGhost> allGhosts, int totalClones,
                         Set<WorldPoint> pohFloor)
    {
        this.realNpc = realNpc;
        this.client = client;
        this.clientThread = clientThread;
        this.allGhosts = allGhosts;
        this.pohFloor = pohFloor;
        this.anchor = realNpc.getWorldLocation();
        this.lastKnownNpcWorld = realNpc.getWorldLocation();
        this.wanderAnchor = pickWanderAnchor(realNpc.getWorldLocation());
        this.ghostWorld = pickStartPosition();
        this.gameTick = RNG.nextInt(WANDER_TICKS);
        this.nextWanderTick = RNG.nextInt(6);
        this.idleOnly = FORCED_IDLE_ONLY_IDS.contains(realNpc.getId());
        this.snapshotModel = isMayaRig(realNpc);
        this.stationary = STATIONARY_PET_IDS.contains(realNpc.getId());

        // Patrol radius scales with clone count — fewer clones roam wider
        //   x2  -> 20 tiles
        //   x3 -> 16 tiles
        //   x5 -> 12 tiles
        //   x10 -> 8 tiles
        this.maxWander = Math.min(20, Math.max(8, (int)(22 - totalClones * 1.5)));

        int area = (2 * maxWander + 1) * (2 * maxWander + 1);
        int budget = Math.max(1, area / Math.max(1, totalClones));
        this.sameNpcClearance = Math.min(4, Math.max(1, (int) Math.sqrt(budget)));

        this.zOffset = resolveZOffset();
        this.cachedNpcId = realNpc.getId();
        this.lastCompositionId = realNpc.getComposition().getId();
        cacheAnimIds();

        this.runeLiteObject = client.createRuneLiteObject();
        initModel();
        placeObject();
        runeLiteObject.setActive(true);
        tryPickNewTarget();

        log.info("[PetScape] Ghost ready: {} id={} modelSet={} idle={} walk={} idleOnly={} snapshot={} zOffset={}",
                realNpc.getName(), realNpc.getId(), modelSet, idleAnimId, walkAnimId, idleOnly, snapshotModel, zOffset);
    }


    public void gameTick()
    {
        gameTick++;

        // While detached keep wandering with the last known model
        if (detached)
        {
            if (targetWorld == null && gameTick >= nextWanderTick)
            {
                if (!stationary)
                {
                    tryPickNewTarget();
                    nextWanderTick = gameTick + 2 + RNG.nextInt(5);
                }
            }
            return;
        }

        WorldPoint npcPos = realNpc.getWorldLocation();
        if (npcPos != null) lastKnownNpcWorld = npcPos;

        // Detect NPC form change (KQ/GG/etc)
        NPCComposition comp = realNpc.getComposition();
        if (comp == null) return;
        int compId = comp.getId();
        if (compId != lastCompositionId)
        {
            lastCompositionId = compId;
            cacheAnimIds();
            zOffset = resolveZOffset();
            idleOnly = FORCED_IDLE_ONLY_IDS.contains(realNpc.getId());
            snapshotModel = isMayaRig(realNpc);
            currentAnimId = -1;
            modelSet = false;
        }

        if (!modelSet) initModel();

        if (targetWorld != null)
        {
            LocalPoint loc = runeLiteObject.getLocation();
            WorldPoint cur = (loc != null) ? WorldPoint.fromLocal(client, loc) : ghostWorld;
            if (cur != null && cur.equals(lastWorldTile))
            {
                if (++stuckTicks >= STUCK_TIMEOUT_TICKS)
                {
                    WorldPoint rescue = findRescueTile(cur);
                    if (rescue != null)
                    {
                        LocalPoint rescueLp = LocalPoint.fromWorld(client.getTopLevelWorldView(), rescue);
                        if (rescueLp != null)
                        {
                            ghostWorld = rescue;
                            runeLiteObject.setLocation(rescueLp, client.getPlane());
                            applyZOffset(rescueLp);
                        }
                    }
                    else
                    {
                        ghostWorld = cur;
                    }
                    abandonTarget();
                    tryPickNewTarget();
                }
            }
            else
            {
                stuckTicks = 0;
                lastWorldTile = cur;
            }
        }
        else
        {
            stuckTicks = 0;
        }

        if (targetWorld == null && gameTick >= nextWanderTick)
        {
            if (!stationary)
            {
                tryPickNewTarget();
                if (targetWorld != null)
                {
                    idleTicks = 0;
                    nextWanderTick = gameTick + 2 + RNG.nextInt(5);
                }
                else
                {
                    idleTicks++;
                    nextWanderTick = gameTick + 1 + RNG.nextInt(2);
                    // After repeated failures, BFS to nearest open tile
                    if (idleTicks >= 4)
                    {
                        WorldPoint rescue = bfsNearestOpen(ghostWorld);
                        if (rescue != null)
                        {
                            LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), rescue);
                            if (lp != null)
                            {
                                ghostWorld = rescue;
                                runeLiteObject.setLocation(lp, client.getPlane());
                                applyZOffset(lp);
                            }
                        }
                        idleTicks = 0;
                        tryPickNewTarget();
                        nextWanderTick = gameTick + 1 + RNG.nextInt(3);
                    }
                }
            }
        }
        else if (targetWorld != null)
        {
            idleTicks = 0;
        }

        if (overheadTicksLeft > 0)
        {
            if (--overheadTicksLeft == 0) overheadText = null;
        }
        else if (PetScapePlugin.WANDER_LINES.length > 0 && gameTick % 50 == 0)
        {
            overheadText = PetScapePlugin.WANDER_LINES[dialogIndex % PetScapePlugin.WANDER_LINES.length];
            overheadTicksLeft = 5;
            dialogIndex++;
        }
    }


    public void clientTick()
    {
        if (!runeLiteObject.isActive()) return;

        // Snapshot pets: refresh geometry every client tick to mirror the real pet - Skip while detached
        if (snapshotModel && modelSet && !detached)
        {
            // Noon/Midnight: walk anim plays boss-scale lightning — hold idle pose instead
            boolean skipSnapshot = false;
            int npcId = realNpc.getId();
            if ((npcId == NpcID.NOON || npcId == NpcID.MIDNIGHT) && walkAnimId > 0)
            {
                int currentAnim = realNpc.getAnimation();
                skipSnapshot = (currentAnim == walkAnimId);
            }
            if (!skipSnapshot) refreshSnapshot();
            if (zOffset != 0)
            {
                LocalPoint lp = runeLiteObject.getLocation();
                if (lp != null) applyZOffset(lp);
            }
        }

        if (targetLocal == null) return;

        LocalPoint current = runeLiteObject.getLocation();
        if (current == null) return;

        int dx = targetLocal.getX() - current.getX();
        int dy = targetLocal.getY() - current.getY();

        WorldPoint currentTile = WorldPoint.fromLocal(client, current);
        if (!currentTile.equals(ghostWorld))
            ghostWorld = currentTile;

        if (dx == 0 && dy == 0)
        {
            ghostWorld = targetWorld;
            targetWorld = null;
            targetLocal = null;
            stuckTicks = 0;
            lastWorldTile = null;
            if (!snapshotModel && idleAnimId > 0 && currentAnimId != idleAnimId) setAnimation(idleAnimId);
            return;
        }

        if (!snapshotModel && !idleOnly && walkAnimId > 0 && currentAnimId != walkAnimId)
        {
            setAnimation(walkAnimId);
        }

        double angle = Math.atan2(-dx, -dy);
        int targetJau = (int) (angle / (2 * Math.PI) * 2048 + 2048) % 2048;
        int currentJau = runeLiteObject.getOrientation();
        int delta = (targetJau - currentJau + 2048) % 2048;
        if (delta > 1024) delta -= 2048;
        if (Math.abs(delta) > 32) delta = Integer.signum(delta) * 32;
        runeLiteObject.setOrientation((currentJau + delta + 2048) % 2048);

        int stepX = Math.abs(dx) > MOVE_SPEED ? Integer.signum(dx) * MOVE_SPEED : dx;
        int stepY = Math.abs(dy) > MOVE_SPEED ? Integer.signum(dy) * MOVE_SPEED : dy;

        int wdx = (int) Math.signum(stepX);
        int wdy = (int) Math.signum(stepY);
        if (!new WorldArea(ghostWorld, 1, 1).canTravelInDirection(client.getTopLevelWorldView(), wdx, wdy))
        {
            abandonTarget();
            return;
        }

        LocalPoint newLp = new LocalPoint(current.getX() + stepX, current.getY() + stepY);
        runeLiteObject.setLocation(newLp, client.getPlane());
        applyZOffset(newLp);
    }

    // Called when real NPC leaves render distance — ghost keeps wandering
    public void detach()
    {
        detached = true;
    }

    // Called when real NPC re-enters render distance
    public void reattach(NPC npc)
    {
        this.realNpc = npc;
        detached = false;
        cachedNpcId = npc.getId();

        NPCComposition comp = npc.getComposition();
        if (comp != null)
        {
            int compId = comp.getId();
            if (compId != lastCompositionId)
            {
                lastCompositionId = compId;
                cacheAnimIds();
                zOffset = resolveZOffset();
                idleOnly = FORCED_IDLE_ONLY_IDS.contains(npc.getId());
                snapshotModel = isMayaRig(npc);
                currentAnimId = -1;
                modelSet = false;
            }
        }

        if (snapshotModel)
            modelSet = false;
        else
            currentAnimId = -1;

        log.debug("[PetScape] Reattached ghost for npcId={}", cachedNpcId);
    }

    public void despawn()
    {
        runeLiteObject.setActive(false);
    }


    private void tryPickNewTarget()
    {
        for (int radius = maxWander; radius >= 1; radius--)
        {
            WorldPoint next = pickWanderTarget(radius);
            if (next != null)
            {
                LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), next);
                if (lp != null)
                {
                    targetWorld = next;
                    targetLocal = lp;
                    lastWorldTile = ghostWorld;
                    stuckTicks = 0;
                    return;
                }
            }
        }
    }

    private int resolveZOffset()
    {
        NPCComposition transformed = realNpc.getTransformedComposition();
        if (transformed != null)
        {
            int id = transformed.getId();
            if (Z_OVERRIDES.containsKey(id)) return Z_OVERRIDES.get(id);
        }
        return Z_OVERRIDES.getOrDefault(realNpc.getId(), BASE_Z_OFFSET);
    }

    private void cacheAnimIds()
    {
        int idle = realNpc.getIdlePoseAnimation();
        int walk = realNpc.getWalkAnimation();
        idleAnimId = idle > 0 ? idle : (Math.max(walk, 0));
        walkAnimId = idleOnly ? idleAnimId : (walk > 0 ? walk : idleAnimId);
    }

    private void abandonTarget()
    {
        targetWorld = null;
        targetLocal = null;
        stuckTicks = 0;
        lastWorldTile = null;
        if (!snapshotModel && idleAnimId > 0 && currentAnimId != idleAnimId) setAnimation(idleAnimId);
    }

    private void initModel()
    {
        if (snapshotModel)
        {
            refreshSnapshot();
            return;
        }

        NPCComposition def = realNpc.getTransformedComposition();
        if (def == null) def = realNpc.getComposition();

        int[] modelIds = def.getModels();
        if (modelIds == null || modelIds.length == 0)
        {
            return;
        }

        ModelData[] parts = new ModelData[modelIds.length];
        for (int i = 0; i < modelIds.length; i++)
        {
            parts[i] = client.loadModelData(modelIds[i]);
            if (parts[i] == null)
            {
                return;
            }
        }

        ModelData merged = client.mergeModels(parts);
        if (merged == null) return;

        short[] cFrom = def.getColorToReplace();
        short[] cTo = def.getColorToReplaceWith();
        if (cFrom != null && cTo != null && cFrom.length > 0)
        {
            for (int i = 0; i < cFrom.length; i++)
            {
                merged.recolor(cFrom[i], cTo[i]);
            }
        }

        int ws = def.getWidthScale();
        int hs = def.getHeightScale();
        ws = (ws > 0) ? ws : 128;
        hs = (hs > 0) ? hs : 128;
        if (ws != 128 || hs != 128)
        {
            merged.cloneVertices();
            merged.scale(ws, hs, ws);
        }

        Model lit = merged.light(64, 850, -30, -50, -30);
        if (lit == null) return;

        runeLiteObject.setModel(lit);
        runeLiteObject.setShouldLoop(true);
        if (idleAnimId > 0) setAnimation(idleAnimId);

        modelSet = true;
    }

    // Copies the live NPC model each frame for Maya pets
    // NEVER call setAnimation() on snapshot pets
    private void refreshSnapshot()
    {
        Model live = realNpc.getModel();
        if (live == null) return;

        Model snapshot = client.mergeModels(live);
        if (snapshot == null) return;

        runeLiteObject.setModel(snapshot);

        if (!modelSet)
        {
            runeLiteObject.setShouldLoop(true);
            modelSet = true;
        }
    }

    private void setAnimation(int animId)
    {
        currentAnimId = animId;
        clientThread.invokeLater(() ->
        {
            runeLiteObject.setAnimation(client.loadAnimation(animId));
            return true;
        });
    }

    private void placeObject()
    {
        if (ghostWorld.getPlane() != client.getPlane()) return;
        LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), ghostWorld);
        if (lp == null) return;
        runeLiteObject.setLocation(lp, client.getPlane());
        applyZOffset(lp);
    }

    private void applyZOffset(LocalPoint lp)
    {
        if (zOffset == 0) return;
        int tileZ = Perspective.getTileHeight(client, lp, client.getPlane());
        runeLiteObject.setZ(tileZ - zOffset);
    }

    private boolean isWalkable(WorldPoint pos)
    {
        CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
        if (maps == null) return false;
        int plane = pos.getPlane();
        if (plane < 0 || plane >= maps.length || maps[plane] == null) return false;
        int sx = pos.getX() - client.getTopLevelWorldView().getBaseX();
        int sy = pos.getY() - client.getTopLevelWorldView().getBaseY();
        int[][] flags = maps[plane].getFlags();
        if (sx < 0 || sy < 0 || sx >= flags.length || sy >= flags[sx].length) return false;
        int f = flags[sx][sy];
        return (f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) == 0
                && (f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) == 0;
    }

    private boolean isInSceneBounds(WorldPoint pos)
    {
        return pohFloor.contains(pos);
    }

    private boolean isAcceptablePosition(WorldPoint pos)
    {
        if (!isInSceneBounds(pos)) return false;
        if (chebyshev(pos, wanderAnchor) > maxWander) return false;

        for (PetScapeGhost other : allGhosts)
        {
            if (other == this || other.ghostWorld == null) continue;
            int req = (other.realNpc.getId() == realNpc.getId()) ? sameNpcClearance : GHOST_CLEARANCE;
            if (chebyshev(pos, other.ghostWorld) < req) return false;
            if (other.targetWorld != null && chebyshev(pos, other.targetWorld) < req) return false;
        }

        CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
        if (maps != null)
        {
            int plane = pos.getPlane();
            if (plane >= 0 && plane < maps.length && maps[plane] != null)
            {
                int sx = pos.getX() - client.getTopLevelWorldView().getBaseX();
                int sy = pos.getY() - client.getTopLevelWorldView().getBaseY();
                int[][] flags = maps[plane].getFlags();
                if (sx >= 0 && sy >= 0 && sx < flags.length && sy < flags[sx].length)
                {
                    int f = flags[sx][sy];
                    if ((f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) != 0) return false;
                    if ((f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) != 0) return false;
                }
            }
        }

        int dx = Integer.signum(pos.getX() - ghostWorld.getX());
        int dy = Integer.signum(pos.getY() - ghostWorld.getY());
        if (dx != 0 || dy != 0)
            return new WorldArea(ghostWorld, 1, 1).canTravelInDirection(client.getTopLevelWorldView(), dx, dy);

        return true;
    }

    private WorldPoint pickWanderTarget(int radius)
    {
        for (int i = 0; i < 80; i++)
        {
            int dx = RNG.nextInt(radius * 2 + 1) - radius;
            int dy = RNG.nextInt(radius * 2 + 1) - radius;
            if (Math.abs(dx) + Math.abs(dy) < MIN_WANDER_DIST) continue;
            WorldPoint c = new WorldPoint(wanderAnchor.getX() + dx, wanderAnchor.getY() + dy, wanderAnchor.getPlane());
            if (isAcceptablePosition(c)) return c;
        }
        return null;
    }

    private WorldPoint pickStartPosition()
    {
        for (int attempt = 0; attempt < 100; attempt++)
        {
            int dx = RNG.nextInt(7) - 3, dy = RNG.nextInt(7) - 3;
            WorldPoint c = new WorldPoint(wanderAnchor.getX() + dx, wanderAnchor.getY() + dy, wanderAnchor.getPlane());

            if (!isInSceneBounds(c)) continue;

            CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
            if (maps != null)
            {
                int plane = c.getPlane();
                if (plane >= 0 && plane < maps.length && maps[plane] != null)
                {
                    int sx = c.getX() - client.getTopLevelWorldView().getBaseX();
                    int sy = c.getY() - client.getTopLevelWorldView().getBaseY();
                    int[][] flags = maps[plane].getFlags();
                    if (sx >= 0 && sy >= 0 && sx < flags.length && sy < flags[sx].length)
                    {
                        int f = flags[sx][sy];
                        if ((f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) != 0) continue;
                        if ((f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) != 0) continue;
                    }
                }
            }

            boolean clear = true;
            for (PetScapeGhost other : allGhosts)
                if (other.ghostWorld != null && chebyshev(c, other.ghostWorld) < GHOST_CLEARANCE)
                { clear = false; break; }
            if (clear) return c;
        }
        if (isInSceneBounds(wanderAnchor)) return wanderAnchor;
        return lastKnownNpcWorld;
    }

    // Spreads ghosts across the full POH by assigning random anchors
    private WorldPoint pickWanderAnchor(WorldPoint origin)
    {
        final int ANCHOR_SPREAD = POH_HALF_SIZE;
        final int MIN_ORIGIN_DIST = 2;
        final int MIN_ANCHOR_SEP = Math.max(2, maxWander / 4);

        for (int attempt = 0; attempt < 400; attempt++)
        {
            int dx = RNG.nextInt(ANCHOR_SPREAD * 2 + 1) - ANCHOR_SPREAD;
            int dy = RNG.nextInt(ANCHOR_SPREAD * 2 + 1) - ANCHOR_SPREAD;
            if (Math.abs(dx) + Math.abs(dy) < MIN_ORIGIN_DIST) continue;
            WorldPoint c = new WorldPoint(origin.getX() + dx, origin.getY() + dy, origin.getPlane());

            if (!isInSceneBounds(c)) continue;

            CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
            if (maps != null)
            {
                int plane = c.getPlane();
                if (plane >= 0 && plane < maps.length && maps[plane] != null)
                {
                    int sx = c.getX() - client.getTopLevelWorldView().getBaseX();
                    int sy = c.getY() - client.getTopLevelWorldView().getBaseY();
                    int[][] flags = maps[plane].getFlags();
                    if (sx >= 0 && sy >= 0 && sx < flags.length && sy < flags[sx].length)
                    {
                        int f = flags[sx][sy];
                        if ((f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) != 0) continue;
                        if ((f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) != 0) continue;
                    }
                }
            }

            boolean tooClose = false;
            for (PetScapeGhost other : allGhosts)
            {
                if (other.wanderAnchor == null) continue;
                int clearance = (other.realNpc.getId() == realNpc.getId())
                        ? MIN_ANCHOR_SEP : GHOST_CLEARANCE;
                if (chebyshev(c, other.wanderAnchor) < clearance)
                {
                    tooClose = true;
                    break;
                }
            }
            if (tooClose) continue;

            return c;
        }
        // Fallback - progressively tighter spread
        for (int spread = 6; spread >= 1; spread--)
        {
            for (int attempt = 0; attempt < 30; attempt++)
            {
                int dx = RNG.nextInt(spread * 2 + 1) - spread;
                int dy = RNG.nextInt(spread * 2 + 1) - spread;
                if (dx == 0 && dy == 0) continue;
                WorldPoint c = new WorldPoint(origin.getX() + dx, origin.getY() + dy, origin.getPlane());
                if (isInSceneBounds(c) && isWalkable(c)) return c;
            }
        }
        return origin;
    }

    // BFS to nearest open tile — last resort when tryPickNewTarget fails repeatedly
    private WorldPoint bfsNearestOpen(WorldPoint from)
    {
        if (from == null) return null;
        final int[][] CARDINALS = {{1,0},{-1,0},{0,1},{0,-1}};
        Set<WorldPoint> seen = new HashSet<>();
        Queue<WorldPoint> queue = new ArrayDeque<>();
        queue.add(from);
        seen.add(from);

        // Keep pets in scene
        while (!queue.isEmpty())
        {
            WorldPoint cur = queue.poll();
            for (int[] d : CARDINALS)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (seen.contains(next)) continue;
                seen.add(next);
                if (seen.size() > 512) return null;

                if (!isInSceneBounds(next)) continue;

                if (!new WorldArea(cur, 1, 1).canTravelInDirection(
                        client.getTopLevelWorldView(), d[0], d[1])) continue;

                boolean occupied = false;
                for (PetScapeGhost other : allGhosts)
                {
                    if (other == this || other.ghostWorld == null) continue;
                    if (next.equals(other.ghostWorld)) { occupied = true; break; }
                }
                if (occupied) { queue.add(next); continue; }

                return next;
            }
        }
        return null;
    }

    private WorldPoint findRescueTile(WorldPoint from)
    {
        int[] deltas = {-1, 0, 1};
        for (int dx : deltas)
        {
            for (int dy : deltas)
            {
                if (dx == 0 && dy == 0) continue;
                WorldPoint candidate = new WorldPoint(from.getX() + dx, from.getY() + dy, from.getPlane());
                if (chebyshev(candidate, wanderAnchor) > maxWander) continue;
                if (!isInSceneBounds(candidate)) continue;
                CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
                if (maps != null)
                {
                    int plane = candidate.getPlane();
                    if (plane >= 0 && plane < maps.length && maps[plane] != null)
                    {
                        int sx = candidate.getX() - client.getTopLevelWorldView().getBaseX();
                        int sy = candidate.getY() - client.getTopLevelWorldView().getBaseY();
                        int[][] flags = maps[plane].getFlags();
                        if (sx >= 0 && sy >= 0 && sx < flags.length && sy < flags[sx].length)
                        {
                            int f = flags[sx][sy];
                            if ((f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) != 0) continue;
                            if ((f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) != 0) continue;
                        }
                    }
                }
                if (new WorldArea(from, 1, 1).canTravelInDirection(client.getTopLevelWorldView(), dx, dy))
                    return candidate;
            }
        }
        return null;
    }

    private static int chebyshev(WorldPoint a, WorldPoint b)
    {
        return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
    }
}

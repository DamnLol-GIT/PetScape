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

import com.DamnLol.PetScape.Locations.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.CollisionData;
import net.runelite.api.CollisionDataFlag;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

// Manages all RoamingArea instances and their RoamingPetSpawn pets
@Slf4j
@Singleton
public class RoamingPetManager
{
    // Slightly larger than getApproxRadius() so spawns ready before player arrives
    private static final int ACTIVATION_RADIUS = 60;


    private static final Random RNG = new Random();

    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private PetScapeConfig config;

    private final List<RoamingArea> areas = new ArrayList<>();
    private final Map<String, List<RoamingPetSpawn>> activeSpawns = new HashMap<>();
    private final Set<String> inRangeAreas = new HashSet<>();

    // Register new areas on starup
    public void startUp()
    {
        if (areas.isEmpty())
        {
            areas.add(new KaruulmHydraArea());
            areas.add(new AlchemicalMonarchArea());
            areas.add(VampireArea.zone1());
            areas.add(VampireArea.zone2());
            areas.add(VampireArea.zone3());
            areas.add(VampireArea.zone4());
            areas.add(SlepeArea.zone1());
            areas.add(SlepeArea.zone2());
            areas.add(MorytaniaMiscArea.zone1());
            areas.add(MorytaniaMiscArea.zone2());
            areas.add(MorytaniaMiscArea.zone3());
            areas.add(MorytaniaMiscArea.zone4());
            areas.add(MorytaniaMiscArea.zone5());
            areas.add(MorytaniaTowerArea.zone1());
            areas.add(AbyssalArea.zone1());
            areas.add(AbyssalArea.zone2());
            areas.add(AbyssalArea.zone3());
            areas.add(FaladorArea.zone1());
            areas.add(FaladorArea.zone2());
            areas.add(FaladorArea.zone3());
            areas.add(FaladorArea.zone4());
        }
        log.debug("[RoamingPetManager] Registered {} area(s)", areas.size());
    }

    public void shutDown()
    {
        clientThread.invoke(() ->
        {
            for (List<RoamingPetSpawn> spawns : activeSpawns.values())
                for (RoamingPetSpawn s : spawns) s.despawn();
            activeSpawns.clear();
            inRangeAreas.clear();
        });
    }

    // Proximity checks, area activation/deactivation
    public void gameTick()
    {
        if (!config.truePetScape())
        {
            if (!activeSpawns.isEmpty())
            {
                for (List<RoamingPetSpawn> spawns : activeSpawns.values())
                    for (RoamingPetSpawn s : spawns) s.despawn();
                activeSpawns.clear();
                inRangeAreas.clear();
            }
            return;
        }

        WorldPoint playerPos = getPlayerPos();
        if (playerPos == null) return;

        for (RoamingArea area : areas)
        {
            String id = area.getAreaId();
            WorldPoint ctr = area.getCenter();
            boolean inRange = chebyshev(playerPos, ctr) <= Math.max(ACTIVATION_RADIUS, area.getApproxRadius());

            if (inRange && !inRangeAreas.contains(id))
            {
                inRangeAreas.add(id);
                activateArea(area);
            }
            else if (!inRange && inRangeAreas.contains(id))
            {
                inRangeAreas.remove(id);
                deactivateArea(id);
            }
        }

        for (Map.Entry<String, List<RoamingPetSpawn>> entry : activeSpawns.entrySet())
            for (RoamingPetSpawn spawn : entry.getValue())
                spawn.gameTick();
    }

    public void clientTick()
    {
        if (!config.truePetScape()) return;
        for (List<RoamingPetSpawn> spawns : activeSpawns.values())
            for (RoamingPetSpawn s : spawns) s.clientTick();
    }

    private void activateArea(RoamingArea area)
    {
        String id = area.getAreaId();
        List<RoamingPetSpawn> spawns = new ArrayList<>();
        String[] names = area.getSpawnNames();

        // Shuffled variants spread evenly across spawns
        int nForms = area.getPetNpcIds().length;
        int nSpawns = area.getSpawnCount();
        int[] formAssignment = new int[nSpawns];
        for (int i = 0; i < nSpawns; i++)
            formAssignment[i] = area.getFormAssignment(i, nForms);
        for (int i = nSpawns - 1; i > 0; i--)
        {
            int j = RNG.nextInt(i + 1);
            int tmp = formAssignment[i];
            formAssignment[i] = formAssignment[j];
            formAssignment[j] = tmp;
        }

        for (int i = 0; i < nSpawns; i++)
        {
            try
            {
                WorldPoint startPos = area.isStationary()
                        ? area.getCenter()
                        : pickRandomStartPos(area, i, spawns);
                if (startPos == null) startPos = area.getCenter();

                String spawnName = (names != null && i < names.length && !names[i].isEmpty())
                        ? names[i] : area.getAreaId();

                RoamingPetSpawn spawn = new RoamingPetSpawn(
                        client, clientThread, area, startPos, spawnName, i, formAssignment[i]);
                spawn.activate();
                spawns.add(spawn);
                log.debug("[RoamingPetManager] Activated spawn {}/{} for area {} at {}",
                        i + 1, area.getSpawnCount(), id, startPos);
            }
            catch (Exception e)
            {
                log.warn("[RoamingPetManager] Spawn {}/{} for {} failed to activate: {}",
                        i + 1, nSpawns, id, e.getMessage());
            }
        }

        activeSpawns.put(id, spawns);

        // Pass sibling list so pets avoid converging on the same tile
        for (RoamingPetSpawn spawn : spawns)
            spawn.setSiblingSpawns(spawns);
    }

    private void deactivateArea(String areaId)
    {
        List<RoamingPetSpawn> spawns = activeSpawns.remove(areaId);
        if (spawns != null)
            for (RoamingPetSpawn s : spawns) s.despawn();
        log.debug("[RoamingPetManager] Deactivated area {}", areaId);
    }

    // Picks valid start tile - inside polygon, walkable, clearance, spread from existing spawns
    // Falls back to polygon-only if all in-scene attempts fail - spawn BFS-rescues on entry
    private WorldPoint pickRandomStartPos(RoamingArea area, int spawnIndex,
                                          List<RoamingPetSpawn> existing)
    {
        int[][] poly = area.getPolygonPoints();
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] p : poly)
        {
            if (p[0] < minX) minX = p[0]; if (p[0] > maxX) maxX = p[0];
            if (p[1] < minY) minY = p[1]; if (p[1] > maxY) maxY = p[1];
        }

        int width = Math.max(1, maxX - minX + 1);
        int height = Math.max(1, maxY - minY + 1);

        for (int minSep : new int[]{8, 4, 1})
        {
            for (int attempt = 0; attempt < 200; attempt++)
            {
                int x = minX + RNG.nextInt(width);
                int y = minY + RNG.nextInt(height);
                WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

                if (!area.contains(candidate)) continue;
                if (LocalPoint.fromWorld(client.getTopLevelWorldView(), candidate) == null) continue;
                if (!isWalkable(candidate)) continue;
                if (!hasClearance(candidate, area)) continue;

                boolean tooClose = false;
                for (RoamingPetSpawn other : existing)
                    if (chebyshev(candidate, other.getCurrentWorld()) < minSep)
                    { tooClose = true; break; }
                if (tooClose) continue;

                return candidate;
            }
        }

        // Out-of-scene fallback - spawn BFS-rescues tile when it enters render range
        for (int attempt = 0; attempt < 400; attempt++)
        {
            int x = minX + RNG.nextInt(width);
            int y = minY + RNG.nextInt(height);
            WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

            if (!area.contains(candidate)) continue;

            boolean tooClose = false;
            for (RoamingPetSpawn other : existing)
                if (other.getCurrentWorld() != null
                        && chebyshev(candidate, other.getCurrentWorld()) < 4)
                { tooClose = true; break; }
            if (tooClose) continue;

            return candidate;
        }

        return area.getCenter();
    }

    public void onSceneChange()
    {
        for (RoamingArea area : new ArrayList<>(areas))
            deactivateArea(area.getAreaId());
        inRangeAreas.clear();
        log.debug("[RoamingPetManager] Scene change — all areas reset for re-activation");
    }

    private WorldPoint getPlayerPos()
    {
        Player local = client.getLocalPlayer();
        return local != null ? local.getWorldLocation() : null;
    }

    // Returns rendered spawn at wp, or null - used for right-click menu
    public RoamingPetSpawn getSpawnAt(WorldPoint wp)
    {
        if (wp == null) return null;
        for (List<RoamingPetSpawn> spawns : activeSpawns.values())
            for (RoamingPetSpawn spawn : spawns)
                if (spawn.isRendered() && wp.equals(spawn.getCurrentWorld()))
                    return spawn;
        return null;
    }

    // Returns all rendered spawns - used for canvas on right-click
    public List<RoamingPetSpawn> getRenderedSpawns()
    {
        List<RoamingPetSpawn> result = new ArrayList<>();
        for (List<RoamingPetSpawn> spawns : activeSpawns.values())
            for (RoamingPetSpawn spawn : spawns)
                if (spawn.isRendered()) result.add(spawn);
        return result;
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
                && (f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) == 0
                && (f & CollisionDataFlag.BLOCK_MOVEMENT_FLOOR) == 0;
    }

    // Mini-BFS verifying at least 6 reachable tiles - avoids placing spawns in dead-ends
    private boolean hasClearance(WorldPoint pos, RoamingArea area)
    {
        final int MIN_REACHABLE = 6;
        final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        int exits = 0;
        net.runelite.api.coords.WorldArea wa = new net.runelite.api.coords.WorldArea(pos, 1, 1);
        for (int[] d : dirs)
            if (wa.canTravelInDirection(client.getTopLevelWorldView(), d[0], d[1])) exits++;
        if (exits < 2) return false;

        Set<WorldPoint> visited = new HashSet<>();
        Queue<WorldPoint> q = new ArrayDeque<>();
        visited.add(pos);
        q.add(pos);
        while (!q.isEmpty() && visited.size() <= MIN_REACHABLE)
        {
            WorldPoint cur = q.poll();
            net.runelite.api.coords.WorldArea curWa = new net.runelite.api.coords.WorldArea(cur, 1, 1);
            for (int[] d : dirs)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (visited.contains(next)) continue;
                if (!area.contains(next)) continue;
                if (!isWalkable(next)) continue;
                if (LocalPoint.fromWorld(client.getTopLevelWorldView(), next) == null) continue;
                if (!curWa.canTravelInDirection(client.getTopLevelWorldView(), d[0], d[1])) continue;
                visited.add(next);
                q.add(next);
            }
        }
        return visited.size() >= MIN_REACHABLE;
    }

    // BFS from origin to nearest walkable in-scene tile within area
    private WorldPoint findNearestOpenInArea(WorldPoint origin, RoamingArea area)
    {
        if (origin == null) return null;

        final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
        final int SEARCH_LIMIT = 300;

        Set<WorldPoint> seen = new HashSet<>();
        Queue<WorldPoint> queue = new ArrayDeque<>();
        queue.add(origin);
        seen.add(origin);

        while (!queue.isEmpty() && seen.size() <= SEARCH_LIMIT)
        {
            WorldPoint cur = queue.poll();
            for (int[] d : DIRS)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (seen.contains(next)) continue;
                seen.add(next);

                if (!area.contains(next)) continue;
                if (LocalPoint.fromWorld(client.getTopLevelWorldView(), next) == null) continue;
                if (isWalkable(next)) return next;

                queue.add(next);
            }
        }
        return null;
    }

    private static int chebyshev(WorldPoint a, WorldPoint b)
    {
        return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
    }
}
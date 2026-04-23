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
    // Player must be within this many tiles of the polygon edge to activate zones
    private static final int ACTIVATION_BUFFER = 20;
    private static final int DEACTIVATION_COOLDOWN_TICKS = 50;

    private static final Random RNG = new Random();

    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private PetScapeConfig config;

    private final List<RoamingArea> areas = new ArrayList<>();
    private final Map<String, List<RoamingPetSpawn>> activeSpawns = new HashMap<>();
    private final Set<String> inRangeAreas = new HashSet<>();
    private final Map<String, Integer> cooldownAreas = new HashMap<>();

    // Register new areas on startup
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
            areas.add(GreatConchArea.zone1());
            areas.add(TemporossArea.zone1());
            areas.add(TemporossArea.zone2());
            areas.add(TemporossArea.zone3());
            areas.add(TemporossArea.zone4());
            areas.add(DesertNorthArea.zone1());
            areas.add(DesertNorthArea.zone2());
            areas.add(DesertNorthArea.zone3());
            areas.add(DesertNorthArea.zone4());
            areas.add(DesertNorthArea.zone5());
            areas.add(DesertSouthArea.zone1());
            areas.add(DesertSouthArea.zone2());
            areas.add(DesertSouthArea.zone3());
            areas.add(DesertSouthArea.zone4());
            areas.add(DesertSouthArea.zone5());
            areas.add(DesertMiscArea.zone1());
            areas.add(DesertMiscArea.zone2());
            areas.add(DesertMiscArea.zone3());
            areas.add(DesertMiscArea.zone4());
            areas.add(DesertMiscArea.zone5());
            areas.add(DesertMiscArea.zone6());
            areas.add(DesertMiscArea.zone7());
            areas.add(DesertMiscArea.zone8());
            areas.add(DesertSouthRoamArea.zone1());
            areas.add(DesertSouthRoamArea.zone2());
            areas.add(DesertSouthRoamArea.zone3());
            areas.add(VarrockArea.zone1());
            areas.add(VarrockArea.zone2());
            areas.add(VarrockArea.zone3());
            areas.add(VarrockArea.zone4());
            areas.add(VarrockArea.zone5());
            areas.add(VarrockArea.zone6());
            areas.add(VarrockArea.zone7());
            areas.add(VarrockArea.zone8());
            areas.add(CanifisRoamingArea.zone1());
            areas.add(CanifisRoamingArea.zone2());
            areas.add(CanifisRoamingArea.zone3());
            areas.add(CanifisRoamingArea.zone4());
            areas.add(CanifisRoamingArea.zone5());
            areas.add(CanifisRoamingArea.zone6());
            areas.add(CanifisRoamingArea.zone7());
            areas.add(CanifisRoamingArea.zone8());
            areas.add(GhorrockArea.zone1());
            areas.add(GhorrockArea.zone2());
            areas.add(GhorrockArea.zone3());
            areas.add(StranglewoodArea.zone1());
            areas.add(StranglewoodArea.zone2());
            areas.add(StranglewoodArea.zone3());
            areas.add(StranglewoodArea.zone4());
            areas.add(StranglewoodArea.zone5());
            areas.add(WhisperRuinsArea.zone1());
            areas.add(WhisperRuinsArea.zone2());
            areas.add(WhisperRuinsArea.zone3());
            areas.add(WhisperRuinsArea.zone4());
            areas.add(MudskipperArea.zone1());
            areas.add(MudskipperArea.zone2());
            areas.add(MudskipperArea.zone3());
            areas.add(MudskipperArea.zone4());
            areas.add(MudskipperArea.zone5());
            areas.add(MudskipperArea.zone6());
            areas.add(AlKharidArea.zone1());
            areas.add(AlKharidArea.zone2());
            areas.add(AlKharidArea.zone3());
            areas.add(AlKharidArea.zone4());
            areas.add(AlKharidArea.zone5());
            areas.add(AlKharidArea.zone6());
            areas.add(AlKharidArea.zone7());
            areas.add(DigsiteArea.zone1());
            areas.add(DigsiteArea.zone2());
            areas.add(DigsiteArea.zone3());
            areas.add(DigsiteArea.zone4());
            areas.add(DigsiteArea.zone5());
            areas.add(DigsiteArea.zone6());
            areas.add(DigsiteArea.zone7());
            areas.add(WildyEdgeArea.zone1());
            areas.add(WildyEdgeArea.zone2());
            areas.add(WildyEdgeArea.zone3());
            areas.add(WildyEdgeArea.zone4());
            areas.add(WildyEdgeArea.zone5());
            areas.add(WildyEdgeArea.zone6());
            areas.add(VarrockOutskirtsArea.zone1());
            areas.add(VarrockOutskirtsArea.zone2());
            areas.add(VarrockOutskirtsArea.zone3());
            areas.add(VarrockOutskirtsArea.zone4());
            areas.add(VarrockOutskirtsArea.zone5());
            areas.add(VarrockOutskirtsArea.zone6());
            areas.add(VarrockOutskirtsArea.zone7());
            areas.add(VarrockOutskirtsArea.zone8());
            areas.add(VarrockOutskirtsArea.zone9());
            areas.add(VarrockOutskirtsArea.zone10());
            areas.add(VarrockOutskirtsArea.zone11());
            areas.add(LumbridgeArea.zone1());
            areas.add(LumbridgeArea.zone2());
            areas.add(LumbridgeArea.zone3());
            areas.add(LumbridgeArea.zone4());
            areas.add(LumbridgeArea.zone5());
            areas.add(LumbridgeArea.zone6());
            areas.add(LumbridgeArea.zone7());
            areas.add(LumbridgeArea.zone8());
            areas.add(LumbridgeArea.zone9());
            areas.add(LumbridgeArea.zone10());
            areas.add(LumbridgeArea.zone11());
            areas.add(LumbridgeArea.zone12());
            areas.add(LumbridgeArea.zone13());
            areas.add(LumbridgeArea.zone14());
            areas.add(LumbSwampRiverArea.zone1());
            areas.add(LumbSwampRiverArea.zone2());
            areas.add(LumbSwampRiverArea.zone3());
            areas.add(LumbSwampRiverArea.zone4());
            areas.add(LumbSwampRiverArea.zone5());
            areas.add(LumbSwampRiverArea.zone6());
            areas.add(LumbSwampRiverArea.zone7());
            areas.add(LumbSwampRiverArea.zone8());
            areas.add(LumbSwampRiverArea.zone9());
            areas.add(LumbSwampRiverArea.zone10());
            areas.add(LumbSwampRiverArea.zone11());
            areas.add(LumbSwampRiverArea.zone12());
            areas.add(LumbSwampRiverArea.zone13());
            areas.add(LumbSwampRiverArea.zone14());
            areas.add(LumbSwampRiverArea.zone15());
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
            cooldownAreas.clear();
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
                cooldownAreas.clear();
            }
            return;
        }

        WorldPoint playerPos = getPlayerPos();
        if (playerPos == null) return;

        for (RoamingArea area : areas)
        {
            String id = area.getAreaId();
            // Per-polygon edge buffer - each zone evaluates independently against its own outline
            boolean inRange = area.isWithinBuffer(playerPos, ACTIVATION_BUFFER);

            if (inRange)
            {
                // Cancel cooldown if player returned before expiry — keep existing spawns
                cooldownAreas.remove(id);

                if (!inRangeAreas.contains(id))
                {
                    inRangeAreas.add(id);
                    // Only spawn fresh if area has no live spawns
                    // Re-entry mid-cooldown continues pet paths
                    if (!activeSpawns.containsKey(id)) activateArea(area);
                }
            }
            else if (inRangeAreas.contains(id))
            {
                // Player just left range — start cooldown, keep spawns wandering
                inRangeAreas.remove(id);
                cooldownAreas.put(id, DEACTIVATION_COOLDOWN_TICKS);
            }
        }

        // Tick down cooldowns and despawn expired areas
        Iterator<Map.Entry<String, Integer>> it = cooldownAreas.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String, Integer> entry = it.next();
            int remaining = entry.getValue() - 1;
            if (remaining <= 0)
            {
                despawnArea(entry.getKey());
                it.remove();
            }
            else
            {
                entry.setValue(remaining);
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

    private void despawnArea(String areaId)
    {
        List<RoamingPetSpawn> spawns = activeSpawns.remove(areaId);
        if (spawns != null)
            for (RoamingPetSpawn s : spawns) s.despawn();
        log.debug("[RoamingPetManager] Deactivated area {} (cooldown expired)", areaId);
    }

    // Picks valid start tile - inside polygon, walkable, clearance, spread from existing spawns
    // Out of scene tiles are accepted and BFS-rescued when they enter render range
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
            for (int attempt = 0; attempt < 600; attempt++)
            {
                int x = minX + RNG.nextInt(width);
                int y = minY + RNG.nextInt(height);
                WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

                if (!area.contains(candidate)) continue;

                // If tile is in scene, validate walkability - otherwise accept it and let BFS rescue on entry
                boolean inScene = LocalPoint.fromWorld(client.getTopLevelWorldView(), candidate) != null;
                if (inScene)
                {
                    if (!isWalkable(candidate)) continue;
                    if (!hasClearance(candidate, area)) continue;
                }

                boolean tooClose = false;
                for (RoamingPetSpawn other : existing)
                    if (other.getCurrentWorld() != null
                            && chebyshev(candidate, other.getCurrentWorld()) < minSep)
                    { tooClose = true; break; }
                if (tooClose) continue;

                return candidate;
            }
        }

        return area.getCenter();
    }

    public void onScenePreLoad()
    {
        // Fired on LOADING - hide every pet before the scene swap so nothing renders at stale LocalPoints
        for (List<RoamingPetSpawn> spawns : activeSpawns.values())
            for (RoamingPetSpawn s : spawns) s.onScenePreLoad();
    }

    public void onSceneChange()
    {
        // Spawns hold absolute WorldPoints so pathfinding state survives scene reloads
        int count = 0;
        for (List<RoamingPetSpawn> spawns : activeSpawns.values())
        {
            for (RoamingPetSpawn s : spawns) { s.onSceneChange(); count++; }
        }
    }

    private WorldPoint getPlayerPos()
    {
        Player local = client.getLocalPlayer();
        if (local == null) return null;
        if (client.isInInstancedRegion())
        {
            LocalPoint lp = local.getLocalLocation();
            return lp != null ? WorldPoint.fromLocalInstance(client, lp) : null;
        }
        return local.getWorldLocation();
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
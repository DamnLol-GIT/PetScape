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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;

import java.util.*;

// Roaming Pet Spawn Rules
@Slf4j
public class RoamingPetSpawn
{
    private static final int FORM_CHANGE_MIN = 20;
    private static final int FORM_CHANGE_MAX = 100;
    private static final int STUCK_TIMEOUT = 20;
    private static final int IDLE_MIN = 4;
    private static final int IDLE_MAX = 10;
    private static final int MOVE_SPEED = 4;

    private static final int WANDER_MIN_DIST = 8;
    private static final int WANDER_MAX_DIST = 35;
    private static final int WANDER_ATTEMPTS = 80;
    private static final int BFS_LIMIT = 250;
    private static final int RENDER_DISTANCE = 15;

    private enum RoamState { IDLE, MOVING }

    private static final Random RNG = new Random();

    private final Client client;
    private final ClientThread clientThread;
    private final RoamingArea area;

    // Sibling spawns after full area spawn list is known
    @Setter
    private List<RoamingPetSpawn> siblingSpawns = Collections.emptyList();

    @Getter private final String displayName;
    private final int spawnIndex;

    // Minimum separation from any sibling position or target
    private static final int MIN_SIBLING_SEPARATION = 3;

    @Getter private final RuneLiteObject runeLiteObject;

    @Getter private WorldPoint currentWorld;

    private WorldPoint targetWorld = null;
    private LocalPoint targetLocal = null;

    private List<WorldPoint> activePath = null;
    private int pathStep = 0;

    private RoamState state = RoamState.IDLE;
    private int idleTicksLeft = 0;

    private boolean wasRendered = false;

    private int stuckTicks = 0;
    private WorldPoint lastWorldTile = null;

    private int formIndex = 0;
    private int formChangeTicks = 0;
    private boolean modelDirty = false;

    private int consecutiveFailedMoves = 0;
    private static final int MAX_FAILED_MOVES = 4;

    private int currentAnimId = -1;
    private int idleAnimId = -1;
    private int walkAnimId = -1;

    @Getter
    private int zOffset = 0;

    private boolean modelSet = false;

    @Getter
    private boolean active = false;

    public RoamingPetSpawn(Client client, ClientThread clientThread,
                           RoamingArea area, WorldPoint startPos,
                           String displayName, int spawnIndex, int initialFormIndex)
    {
        this.client = client;
        this.clientThread = clientThread;
        this.area = area;
        this.currentWorld = startPos;
        this.displayName = displayName;
        this.spawnIndex = spawnIndex;

        int[] forms = area.getPetNpcIds();
        this.formIndex = (forms.length > 1) ? (initialFormIndex % forms.length) : 0;
        this.zOffset = area.getZOffset();

        this.formChangeTicks = FORM_CHANGE_MIN + RNG.nextInt(FORM_CHANGE_MAX - FORM_CHANGE_MIN + 1);
        // Stagger initial idles so all spawns dont target on the same tick
        this.idleTicksLeft = RNG.nextInt(20);

        final int capturedScale = resolveScale(forms[formIndex]);
        this.runeLiteObject = new RuneLiteObject(client)
        {
            @Override public Model getModel()
            {
                Model m = super.getModel();
                if (capturedScale > 0 && m != null)
                    return m.scale(capturedScale, capturedScale, capturedScale);
                return m;
            }
        };
        this.runeLiteObject.setWorldView(-1);
    }

    public void activate()
    {
        if (active) return;
        active = true;
        initModel();
        placeAt(currentWorld);
        runeLiteObject.setOrientation(area.getInitialOrientation());
    }

    public void deactivate()
    {
        if (!active) return;
        active = false;
        runeLiteObject.setActive(false);
    }

    public void despawn()
    {
        active = false;
        wasRendered = false;
        runeLiteObject.setActive(false);
    }

    public void onScenePreLoad()
    {
        if (!active) return;
        wasRendered = false;
        runeLiteObject.setActive(false);
    }

    public void onSceneChange()
    {
        if (!active) return;

        activePath = null;
        pathStep = 0;
        targetWorld = null;
        targetLocal = null;
        lastWorldTile = null;
        stuckTicks = 0;

        // Re-sync render state against new scene origin
        Player localPlayer = client.getLocalPlayer();
        boolean inRange = localPlayer != null && currentWorld != null
                && chebyshev(currentWorld, localPlayer.getWorldLocation()) <= RENDER_DISTANCE;
        LocalPoint lp = (inRange && currentWorld != null)
                ? LocalPoint.fromWorld(client.getTopLevelWorldView(), currentWorld) : null;
        if (lp != null)
        {
            placeAt(currentWorld);
            runeLiteObject.setActive(true);
            wasRendered = true;
        }
        else
        {
            runeLiteObject.setActive(false);
            wasRendered = false;
        }
    }

    public boolean isRendered() { return active && runeLiteObject.isActive(); }

    public String getExamineText() { return area.getExamineText(spawnIndex, formIndex); }
    public String getMenuTarget()  { return area.getMenuTarget(spawnIndex, formIndex); }

    public int getMenuClickRadius() { return area.getMenuClickRadius(); }

    public void gameTick() {
        if (!active) return;

        // Render-distance toggle + lazy placement
        Player localPlayer = client.getLocalPlayer();
        boolean inRange = false;
        if (localPlayer != null && currentWorld != null) {
            inRange = chebyshev(currentWorld, localPlayer.getWorldLocation()) <= RENDER_DISTANCE;

            if (inRange && !wasRendered) {
                placeAt(currentWorld);
            }

            if (inRange != runeLiteObject.isActive()) runeLiteObject.setActive(inRange);
        }
        wasRendered = inRange;

        // Recalculate Z every tick to track sloped terrain
        if (runeLiteObject.isActive() && currentWorld != null) {
            LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), currentWorld);
            if (lp != null) applyZOffset(lp);
        }

        // Form cycling
        int[] forms = area.getPetNpcIds();
        if (!area.isFormFixed() && forms.length > 1 && --formChangeTicks <= 0) {
            cycleForm();
            formChangeTicks = FORM_CHANGE_MIN + RNG.nextInt(FORM_CHANGE_MAX - FORM_CHANGE_MIN + 1);
        }
        if (modelDirty) {
            initModel();
            modelDirty = false;
        }

        // Stuck detection
        // Reset counter and drop stale path so pet starts clean on re-entry
        if (!runeLiteObject.isActive()) {
            stuckTicks = 0;
            if (targetWorld != null) {
                activePath = null;
                enterIdle();
            }
        } else if (targetWorld != null) {
            LocalPoint objLp = runeLiteObject.getLocation();
            WorldPoint objTile = (objLp != null) ? WorldPoint.fromLocal(client, objLp) : currentWorld;
            if (objTile != null && objTile.equals(lastWorldTile)) {
                if (++stuckTicks >= STUCK_TIMEOUT) {
                    log.debug("[RoamingPet] Stuck at {} — relocating", currentWorld);
                    teleportToSafeSpot();
                    stuckTicks = 0;
                    return;
                }
            } else {
                stuckTicks = 0;
                lastWorldTile = objTile;
            }
        } else stuckTicks = 0;

        switch (state) {
            case IDLE:
                if (--idleTicksLeft <= 0) startNextMove();
                break;

            case MOVING:
                if (targetWorld == null) advancePath();
                break;
        }

    }

    public void clientTick()
    {
        if (!active || !runeLiteObject.isActive()) return;

        // Sync logical tile
        LocalPoint objLp = runeLiteObject.getLocation();
        if (objLp != null)
        {
            currentWorld = WorldPoint.fromLocal(client, objLp);
        }

        if (targetLocal == null) return;
        LocalPoint current = runeLiteObject.getLocation();
        if (current == null) return;

        int dx = targetLocal.getX() - current.getX();
        int dy = targetLocal.getY() - current.getY();

        // Waypoint reached
        if (dx == 0 && dy == 0)
        {
            if (targetWorld != null) currentWorld = targetWorld;
            stuckTicks = 0;
            lastWorldTile = null;
            targetWorld = null;
            targetLocal = null;

            // Immediately queue next waypoint to keep movement continuous
            if (state == RoamState.MOVING && activePath != null && pathStep < activePath.size())
            {
                WorldPoint next = activePath.get(pathStep);
                LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), next);
                if (lp != null)
                {
                    targetWorld = next;
                    targetLocal = lp;
                    lastWorldTile = currentWorld;
                    pathStep++;
                }
            }
            else
            {
                activePath = null;
                enterIdle();
            }
            return;
        }

        if (walkAnimId > 0 && currentAnimId != walkAnimId) setAnimation(walkAnimId);

        // Smooth orientation
        double angle = Math.atan2(-dx, -dy);
        int targetJau = (int) (angle / (2 * Math.PI) * 2048 + 2048) % 2048;
        int curJau = runeLiteObject.getOrientation();
        int delta = (targetJau - curJau + 2048) % 2048;
        if (delta > 1024) delta -= 2048;
        if (Math.abs(delta) > 32) delta = Integer.signum(delta) * 32;
        runeLiteObject.setOrientation((curJau + delta + 2048) % 2048);

        // Abandon path if BFS route invalidated - reset walk animation
        int wdx = Integer.signum(dx);
        int wdy = Integer.signum(dy);
        if (currentWorld != null &&
                !canTravel(new WorldArea(currentWorld, 1, 1), wdx, wdy))
        {
            activePath = null;
            enterIdle();
            return;
        }

        int stepX = Math.abs(dx) > MOVE_SPEED ? Integer.signum(dx) * MOVE_SPEED : dx;
        int stepY = Math.abs(dy) > MOVE_SPEED ? Integer.signum(dy) * MOVE_SPEED : dy;
        LocalPoint newLp = new LocalPoint(current.getX() + stepX, current.getY() + stepY);
        runeLiteObject.setLocation(newLp, area.getPlane());
        applyZOffset(newLp);
    }

    // Picks a random walkable target and starts BFS path toward it - fallback idle
    private void startNextMove()
    {
        if (area.isStationary()) { enterIdle(); return; }
        int[][] bounds = areaBounds();
        int minX = bounds[0][0], maxX = bounds[0][1];
        int minY = bounds[1][0], maxY = bounds[1][1];
        int w = Math.max(1, maxX - minX + 1);
        int h = Math.max(1, maxY - minY + 1);

        for (int attempt = 0; attempt < WANDER_ATTEMPTS; attempt++)
        {
            int x = minX + RNG.nextInt(w);
            int y = minY + RNG.nextInt(h);
            WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

            if (!area.contains(candidate)) continue;
            if (candidate.equals(currentWorld)) continue;
            int dist = currentWorld != null ? chebyshev(currentWorld, candidate) : 10;
            if (dist < area.getWanderMinDist() || dist > WANDER_MAX_DIST) continue;
            if (!isWalkableInScene(candidate)) continue;
            if (!hasMovementClearance(candidate)) continue;
            if (isTooCloseToSibling(candidate)) continue;

            List<WorldPoint> path = bfsPath(currentWorld, candidate);
            if (path == null || path.isEmpty()) continue;

            consecutiveFailedMoves = 0;
            activePath = path;
            pathStep = 0;
            state = RoamState.MOVING;
            advancePath();
            return;
        }

        // Check if the pet is trapped in walkable pocket - teleport
        if (runeLiteObject.isActive() && isInPocket())
        {
            log.debug("[RoamingPet] {} is in a pocket at {} — teleporting",
                    displayName, currentWorld);
            consecutiveFailedMoves = 0;
            teleportToSafeSpot();
            return;
        }

        // Long-range wander failed, try short-range for better pool
        for (int attempt = 0; attempt < 40; attempt++)
        {
            int x = minX + RNG.nextInt(w);
            int y = minY + RNG.nextInt(h);
            WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

            if (!area.contains(candidate)) continue;
            if (candidate.equals(currentWorld)) continue;
            int dist = currentWorld != null ? chebyshev(currentWorld, candidate) : 4;
            if (dist < 1 || dist > 7) continue;

            if (!isWalkableInScene(candidate)) continue;
            if (!hasMovementClearance(candidate)) continue;

            List<WorldPoint> path = bfsPath(currentWorld, candidate);
            if (path == null || path.isEmpty()) continue;

            consecutiveFailedMoves = 0;
            activePath = path;
            pathStep = 0;
            state = RoamState.MOVING;
            advancePath();
            return;
        }

        if (runeLiteObject.isActive() && ++consecutiveFailedMoves >= MAX_FAILED_MOVES)
        {
            log.debug("[RoamingPet] {} failed to find move target {} times — teleporting",
                    displayName, consecutiveFailedMoves);
            consecutiveFailedMoves = 0;
            teleportToSafeSpot();
            return;
        }
        enterIdle();
    }

    private void advancePath()
    {
        if (activePath == null || pathStep >= activePath.size())
        {
            activePath = null;
            enterIdle();
            return;
        }

        WorldPoint next = activePath.get(pathStep);
        LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), next);
        if (lp == null)
        {
            // Waypoint left scene — drop path and replan next tick
            activePath = null;
            enterIdle();
            return;
        }

        targetWorld = next;
        targetLocal = lp;
        lastWorldTile = currentWorld;
        pathStep++;
    }

    private void enterIdle()
    {
        state = RoamState.IDLE;
        idleTicksLeft = IDLE_MIN + RNG.nextInt(IDLE_MAX - IDLE_MIN + 1);
        targetWorld = null;
        targetLocal = null;
        stuckTicks = 0;
        lastWorldTile = null;
        if (idleAnimId > 0 && currentAnimId != idleAnimId) setAnimation(idleAnimId);
    }

    // BFS using collision - directions shuffled so sibling paths diverge
    private List<WorldPoint> bfsPath(WorldPoint from, WorldPoint to)
    {
        if (from == null || to == null) return null;
        if (from.equals(to)) return new ArrayList<>();

        int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int i = DIRS.length - 1; i > 0; i--)
        {
            int j = RNG.nextInt(i + 1);
            int[] tmp = DIRS[i]; DIRS[i] = DIRS[j]; DIRS[j] = tmp;
        }

        Map<WorldPoint, WorldPoint> cameFrom = new HashMap<>();
        Queue<WorldPoint> queue = new ArrayDeque<>();
        cameFrom.put(from, null);
        queue.add(from);

        while (!queue.isEmpty() && cameFrom.size() <= BFS_LIMIT)
        {
            WorldPoint cur = queue.poll();
            for (int[] d : DIRS)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (cameFrom.containsKey(next)) continue;
                if (!area.contains(next)) continue;
                if (!isWalkableInScene(next)) continue;
                if (!canTravel(new WorldArea(cur, 1, 1), d[0], d[1])) continue;

                cameFrom.put(next, cur);

                if (next.equals(to))
                {
                    List<WorldPoint> path = new ArrayList<>();
                    WorldPoint step = to;
                    while (step != null && !step.equals(from))
                    {
                        path.add(0, step);
                        step = cameFrom.get(step);
                    }
                    return path;
                }
                queue.add(next);
            }
        }
        return null;
    }

    // Teleports to a random valid tile when stuck - same clearance requirements as initial spawn
    private void teleportToSafeSpot()
    {
        int[][] bounds = areaBounds();
        int minX = bounds[0][0], maxX = bounds[0][1];
        int minY = bounds[1][0], maxY = bounds[1][1];
        int w = Math.max(1, maxX - minX + 1);
        int h = Math.max(1, maxY - minY + 1);

        for (int attempt = 0; attempt < 400; attempt++)
        {
            int x = minX + RNG.nextInt(w);
            int y = minY + RNG.nextInt(h);
            WorldPoint candidate = new WorldPoint(x, y, area.getPlane());

            if (!area.contains(candidate)) continue;
            if (!isWalkableInScene(candidate)) continue;
            if (!hasMovementClearance(candidate)) continue;
            if (isTooCloseToSibling(candidate)) continue;

            LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), candidate);
            if (lp == null) continue;

            activePath = null;
            currentWorld = candidate;
            runeLiteObject.setLocation(lp, area.getPlane());
            applyZOffset(lp);
            enterIdle();
            return;
        }
        enterIdle();
    }

    // True if tile is on area plane, has valid LocalPoint, and passes all movement block flags
    private boolean isWalkableInScene(WorldPoint pos)
    {
        if (pos.getPlane() != area.getPlane()) return false;
        if (LocalPoint.fromWorld(client.getTopLevelWorldView(), pos) == null) return false;

        CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
        if (maps == null) return false;

        int plane = pos.getPlane();
        if (plane < 0 || plane >= maps.length || maps[plane] == null) return false;

        int sx = pos.getX() - client.getTopLevelWorldView().getBaseX();
        int sy = pos.getY() - client.getTopLevelWorldView().getBaseY();
        int[][] flags = maps[plane].getFlags();
        if (sx < 0 || sy < 0 || sx >= flags.length || sy >= flags[sx].length) return false;

        int f = flags[sx][sy];
        if (area.isAquatic()) return true;
        if (area.isFlying())
            return (f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) == 0
                    && (f & CollisionDataFlag.BLOCK_MOVEMENT_FLOOR) == 0;


        return (f & CollisionDataFlag.BLOCK_MOVEMENT_FULL) == 0
                && (f & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) == 0
                && (f & CollisionDataFlag.BLOCK_MOVEMENT_FLOOR) == 0;
    }

    // Wraps canTravelInDirection - catch NPE
    private boolean canTravel(WorldArea wa, int dx, int dy)
    {
        // Aquatic pets ignore all directional flags - polygon boundary
        if (area.isAquatic())
        {
            WorldPoint dest = new WorldPoint(wa.getX() + dx, wa.getY() + dy, wa.getPlane());
            return area.contains(dest) && isWalkableInScene(dest);
        }
        // Flying pets ignore object-based directional flags
        if (area.isFlying())
        {
            WorldPoint dest = new WorldPoint(wa.getX() + dx, wa.getY() + dy, wa.getPlane());
            return isWalkableInScene(dest);
        }

        try { return wa.canTravelInDirection(client.getTopLevelWorldView(), dx, dy); }
        catch (NullPointerException ignored) { return false; }
    }

    private static final int MIN_REACHABLE_TILES = 6;

    // Mini-BFS verifying tile connects to at least MIN_REACHABLE_TILES distinct tiles
    private boolean hasMovementClearance(WorldPoint pos)
    {
        final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        int exits = 0;
        WorldArea wa = new WorldArea(pos, 1, 1);
        for (int[] d : dirs)
            if (canTravel(wa, d[0], d[1]))
                exits++;
        if (exits < 2) return false;

        Set<WorldPoint> visited = new HashSet<>();
        Queue<WorldPoint> q = new ArrayDeque<>();
        visited.add(pos);
        q.add(pos);
        while (!q.isEmpty() && visited.size() <= MIN_REACHABLE_TILES)
        {
            WorldPoint cur = q.poll();
            WorldArea curWa = new WorldArea(cur, 1, 1);
            for (int[] d : dirs)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (visited.contains(next)) continue;
                if (!area.contains(next)) continue;
                if (!isWalkableInScene(next)) continue;
                if (!canTravel(curWa, d[0], d[1])) continue;
                visited.add(next);
                q.add(next);
            }
        }
        return visited.size() >= MIN_REACHABLE_TILES;
    }

    // Returns true when the pet is trapped in pocket
    private boolean isInPocket()
    {
        if (currentWorld == null) return false;
        final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
        Set<WorldPoint> visited = new HashSet<>();
        Queue<WorldPoint> queue = new ArrayDeque<>();
        visited.add(currentWorld);
        queue.add(currentWorld);
        while (!queue.isEmpty() && visited.size() < area.getWanderMinDist())
        {
            WorldPoint cur = queue.poll();
            if (cur == null) break;
            WorldArea wa = new WorldArea(cur, 1, 1);
            for (int[] d : DIRS)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (visited.contains(next)) continue;
                if (!area.contains(next)) continue;
                if (!isWalkableInScene(next)) continue;
                if (!canTravel(wa, d[0], d[1])) continue;
                visited.add(next);
                queue.add(next);
            }
        }
        return visited.size() < area.getWanderMinDist();
    }

    // True if candidate is within MIN_SIBLING_SEPARATION tiles of any sibling position or target
    private boolean isTooCloseToSibling(WorldPoint candidate)
    {
        for (RoamingPetSpawn other : siblingSpawns)
        {
            if (other == this) continue;
            if (other.currentWorld != null
                    && chebyshev(candidate, other.currentWorld) < area.getMinSiblingSeparation())
                return true;
            if (other.targetWorld != null
                    && chebyshev(candidate, other.targetWorld) < area.getMinSiblingSeparation())
                return true;
        }
        return false;
    }

    // BFS to nearest open tile from a blocked position
    private WorldPoint bfsNearestOpen(WorldPoint from)
    {
        if (from == null) return null;
        final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
        Set<WorldPoint> seen = new HashSet<>();
        Queue<WorldPoint> q = new ArrayDeque<>();
        q.add(from); seen.add(from);
        while (!q.isEmpty())
        {
            WorldPoint cur = q.poll();
            for (int[] d : DIRS)
            {
                WorldPoint next = new WorldPoint(cur.getX() + d[0], cur.getY() + d[1], cur.getPlane());
                if (seen.contains(next)) continue;
                seen.add(next);
                if (seen.size() > 256) return null;
                if (!area.contains(next)) continue;
                if (!canTravel(new WorldArea(cur, 1, 1), d[0], d[1])) continue;
                if (isWalkableInScene(next) && hasMovementClearance(next)) return next;
                q.add(next);
            }
        }
        return null;
    }

    private void cycleForm()
    {
        int[] forms = area.getPetNpcIds();
        if (forms.length <= 1) return;
        formIndex = (formIndex + 1) % forms.length;
        zOffset = area.getZOffset();
        modelDirty = true;
        currentAnimId = -1;
    }

    private void initModel()
    {
        int[] forms = area.getPetNpcIds();
        if (forms == null || forms.length == 0) return;
        int npcId = forms[formIndex];

        PetGhostData.Entry data = PetGhostData.LOOKUP.get(npcId);
        if (data == null)
        {
            initModelFromComposition(npcId);
            return;
        }

        idleAnimId = data.idleAnim > 0 ? data.idleAnim : -1;
        walkAnimId = (data.walkAnim > 0) ? data.walkAnim : idleAnimId;

        ModelData modelData;
        if (data.modelIds.length == 1)
        {
            modelData = client.loadModelData(data.modelIds[0]);
        }
        else
        {
            ModelData[] parts = new ModelData[data.modelIds.length];
            for (int i = 0; i < data.modelIds.length; i++)
            {
                parts[i] = client.loadModelData(data.modelIds[i]);
                if (parts[i] == null) return;
            }
            modelData = client.mergeModels(parts);
        }
        if (modelData == null) return;
        modelData.cloneVertices();

        if (data.recolors != null && data.recolors.length >= 2)
        {
            modelData.cloneColors();
            int mid = data.recolors.length / 2;
            for (int i = 0; i < mid; i++)
                modelData.recolor(data.recolors[i], data.recolors[mid + i]);
        }

        Model lit = modelData.light(64, 850, -30, -50, -30);
        if (lit == null) return;
        area.tintModel(lit);

        runeLiteObject.setModel(lit);
        runeLiteObject.setShouldLoop(true);
        if (idleAnimId > 0)
        {
            Animation anim = client.loadAnimation(idleAnimId);
            if (anim != null) { runeLiteObject.setAnimation(anim); currentAnimId = idleAnimId; }
        }
        modelSet = true;
    }

    // Fallback for NPCs not in PetGhostData
    private void initModelFromComposition(int npcId)
    {
        NPCComposition def = client.getNpcDefinition(npcId);
        if (def == null) { log.warn("[RoamingPet] NPC definition not found for ID {}", npcId); return; }

        int[] modelIds = def.getModels();
        if (modelIds == null || modelIds.length == 0)
        { log.warn("[RoamingPet] No models in composition for NPC ID {}", npcId); return; }

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
        {
            merged.cloneColors();
            for (int i = 0; i < cFrom.length; i++) merged.recolor(cFrom[i], cTo[i]);
        }

        int ws = def.getWidthScale();  ws = ws > 0 ? ws : 128;
        int hs = def.getHeightScale(); hs = hs > 0 ? hs : 128;
        if (ws != 128 || hs != 128) { merged.cloneVertices(); merged.scale(ws, hs, ws); }

        Model lit = merged.light(64, 850, -30, -50, -30);
        if (lit == null) return;
        area.tintModel(lit);

        int customAnim = area.getCustomIdleAnimId();
        idleAnimId = customAnim > 0 ? customAnim : -1;
        walkAnimId = idleAnimId;

        runeLiteObject.setModel(lit);
        runeLiteObject.setShouldLoop(true);
        if (idleAnimId > 0)
        {
            Animation anim = client.loadAnimation(idleAnimId);
            if (anim != null) { runeLiteObject.setAnimation(anim); currentAnimId = idleAnimId; }
        }
        modelSet = true;
    }

    private void placeAt(WorldPoint pos)
    {
        WorldPoint safePos = pos;
        // Skip clearance BFS for stationary spawns
        if (!area.isStationary() && (!isWalkableInScene(safePos) || !hasMovementClearance(safePos)))
        {
            WorldPoint rescued = bfsNearestOpen(safePos);
            if (rescued != null) { safePos = rescued; currentWorld = safePos; }
        }
        LocalPoint lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), safePos);
        if (lp == null) lp = LocalPoint.fromWorld(client.getTopLevelWorldView(), area.getCenter());
        if (lp == null) return;
        runeLiteObject.setLocation(lp, area.getPlane());
        applyZOffset(lp);
    }

    private void applyZOffset(LocalPoint lp)
    {
        int tileZ = Perspective.getTileHeight(client, lp, area.getPlane());
        runeLiteObject.setZ(zOffset == 0 ? tileZ : tileZ - zOffset);
    }

    private void setAnimation(int animId)
    {
        currentAnimId = animId;
        clientThread.invokeLater(() ->
        {
            Animation anim = client.loadAnimation(animId);
            if (anim != null) runeLiteObject.setAnimation(anim);
            return true;
        });
    }

    private int[][] areaBounds()
    {
        int[][] poly = area.getPolygonPoints();
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] p : poly)
        {
            if (p[0] < minX) minX = p[0]; if (p[0] > maxX) maxX = p[0];
            if (p[1] < minY) minY = p[1]; if (p[1] > maxY) maxY = p[1];
        }
        return new int[][]{{minX, maxX}, {minY, maxY}};
    }

    private static int resolveScale(int npcId)
    {
        PetGhostData.Entry d = PetGhostData.LOOKUP.get(npcId);
        return (d != null && d.scale > 0 && d.scale != 128) ? d.scale : -1;
    }

    private static int chebyshev(WorldPoint a, WorldPoint b)
    {
        return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
    }
}
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

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.*;

@Slf4j
@PluginDescriptor(
        name = "PetScape",
        description = "Visually multiplies all boss pets in your Player Owned House",
        tags = {"pet", "poh", "house", "boss", "double", "clone"}
)
public class PetScapePlugin extends Plugin
{
    private static final Set<Integer> POH_TEMPLATE_REGIONS = Set.of(7790, 7791);

    static final String[] WANDER_LINES = {};

    static final Set<Integer> PET_NPC_IDS = new HashSet<>(Arrays.asList(
            // God Wars Dungeon
            NpcID.NEXLING,
            NpcID.GENERAL_GRAARDOR_JR,
            NpcID.KREEARRA_JR,
            NpcID.ZILYANA_JR,
            NpcID.KRIL_TSUTSAROTH_JR,

            // Slayer bosses
            NpcID.ABYSSAL_ORPHAN,
            NpcID.SMOKE_DEVIL_6655,
            NpcID.SMOKE_DEVIL_8482,
            NpcID.KRAKEN_6640,
            NpcID.HELLPUPPY,
            NpcID.NOON,
            NpcID.MIDNIGHT,
            NpcID.GULL,
            NpcID.GULL_14931,
            NpcID.GULL_15059,
            NpcID.IKKLE_HYDRA,
            NpcID.IKKLE_HYDRA_8493,
            NpcID.IKKLE_HYDRA_8494,
            NpcID.IKKLE_HYDRA_8495,
            NpcID.IKKLE_HYDRA_8520,
            NpcID.NID,
            NpcID.NID_13683,
            NpcID.RAX,
            NpcID.RAX_13684,

            // Retro bosses
            NpcID.CORPOREAL_CRITTER,
            NpcID.PRINCE_BLACK_DRAGON,
            NpcID.KALPHITE_PRINCESS,
            NpcID.KALPHITE_PRINCESS_6638,
            NpcID.DAGANNOTH_PRIME_JR,
            NpcID.DAGANNOTH_REX_JR,
            NpcID.DAGANNOTH_SUPREME_JR,
            NpcID.BABY_MOLE,
            NpcID.BABY_MOLERAT,
            NpcID.CHAOS_ELEMENTAL_JR,

            // Post-quest pets
            NpcID.VORKI,
            NpcID.BUTCH,
            NpcID.BARON,
            NpcID.WISP,
            NpcID.LILVIATHAN,
            NpcID.DOM,
            NpcID.MOXI,
            NpcID.MUPHIN,
            NpcID.MUPHIN_12006,
            NpcID.MUPHIN_12007,
            NpcID.YOUNGLLEF,
            NpcID.CORRUPTED_YOUNGLLEF,

            // ── Misc / other pets ─────────────────────────────────────────────
            NpcID.BRAN,
            NpcID.RIC,
            NpcID.BEEF,
            NpcID.BEEF_15633,
            NpcID.BROAV,
            NpcID.WILY_HELLCAT,
            NpcID.CAT_6665,
            NpcID.MAYOR_OF_CATHERBY,
            NpcID.HUBERTE,
            NpcID.JALNIBREK,
            NpcID.TZREKZUK,
            NpcID.LITTLE_NIGHTMARE,
            NpcID.LITTLE_PARASITE,
            NpcID.SRARACHA,
            NpcID.SRARACHA_11157,
            NpcID.SRARACHA_11158,
            NpcID.SNAKELING_2128,
            NpcID.SNAKELING_2127,
            NpcID.SNAKELING_2129,
            NpcID.SCURRY,
            NpcID.SKOTOS,
            NpcID.SKOTOS_7671,
            NpcID.SMOL_HEREDIT,
            NpcID.TZREKJAD,
            NpcID.JALREKJAD,
            NpcID.YAMI,

            // Wilderness
            NpcID.CALLISTO_CUB,
            NpcID.CALLISTO_CUB_11982,
            NpcID.CALLISTO_CUB_5558,
            NpcID.CALLISTO_CUB_11986,
            NpcID.SCORPIAS_OFFSPRING,
            NpcID.VENENATIS_SPIDERLING,
            NpcID.VENENATIS_SPIDERLING_11981,
            NpcID.VETION_JR,
            NpcID.VETION_JR_5537,
            NpcID.VETION_JR_11983,
            NpcID.VETION_JR_11984,
            NpcID.VETION_JR_5559,

            // ToB Pets
            NpcID.LIL_ZIK,
            NpcID.LIL_MAIDEN,
            NpcID.LIL_BLOAT,
            NpcID.LIL_NYLO,
            NpcID.LIL_SOT,
            NpcID.LIL_XARP,

            // CoX Pets
            NpcID.OLMLET,
            NpcID.PUPPADILE,
            NpcID.TEKTINY,
            NpcID.VANGUARD_8198,
            NpcID.VASA_MINIRIO,
            NpcID.VESPINA,
            NpcID.FLYING_VESPINA,

            // ToA Pets
            NpcID.TUMEKENS_GUARDIAN,
            NpcID.TUMEKENS_GUARDIAN_11812,
            NpcID.TUMEKENS_DAMAGED_GUARDIAN,
            NpcID.ELIDINIS_GUARDIAN,
            NpcID.ELIDINIS_DAMAGED_GUARDIAN,
            NpcID.AKKHITO,
            NpcID.KEPHRITI,
            NpcID.BABI,
            NpcID.ZEBO,

            // Skilling Pets
            NpcID.PHOENIX,
            NpcID.PHOENIX_7368,
            NpcID.PHOENIX_7370,
            NpcID.PHOENIX_3078,
            NpcID.PHOENIX_3082,
            NpcID.PHOENIX_3079,
            NpcID.PHOENIX_3083,
            NpcID.PHOENIX_3080,
            NpcID.PHOENIX_3084,
            NpcID.PHOENIX_3081,
            NpcID.SMOLCANO,
            NpcID.SMOLCANO_8739,
            NpcID.TINY_TEMPOR,
            NpcID.BABY_CHINCHOMPA,
            NpcID.BEAVER,
            NpcID.GIANT_SQUIRREL,
            NpcID.HERON,
            NpcID.RIFT_GUARDIAN,
            NpcID.ROCKY,
            NpcID.SOUP,
            NpcID.SOUP_15058,
            NpcID.TANGLEROOT,
            NpcID.TANGLEROOT_7352,
            NpcID.TANGLEROOT_9492,
            NpcID.TANGLEROOT_9497,
            NpcID.TANGLEROOT_9493,
            NpcID.TANGLEROOT_9498,
            NpcID.TANGLEROOT_9494,
            NpcID.TANGLEROOT_9499,
            NpcID.TANGLEROOT_9495,
            NpcID.TANGLEROOT_9500,
            NpcID.TANGLEROOT_9496,
            NpcID.TANGLEROOT_9501,

            // Rock Golems million different variations
            NpcID.ROCK_GOLEM,
            NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7451,
            NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7452,
            NpcID.ROCK_GOLEM_7441, NpcID.ROCK_GOLEM_7453,
            NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7454,
            NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7455,
            NpcID.ROCK_GOLEM_7444, NpcID.ROCK_GOLEM_7642,
            NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7643,
            NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7644,
            NpcID.ROCK_GOLEM_7447, NpcID.ROCK_GOLEM_7645,
            NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7646,
            NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7647,
            NpcID.ROCK_GOLEM_7450, NpcID.ROCK_GOLEM_7648,
            NpcID.ROCK_GOLEM_7737, NpcID.ROCK_GOLEM_7740,
            NpcID.ROCK_GOLEM_7738, NpcID.ROCK_GOLEM_7741,


            // Minigame/Clog
            NpcID.BLOODHOUND,
            NpcID.BLOODHOUND_7232,
            NpcID.ABYSSAL_PROTECTOR,
            NpcID.CHOMPY_CHICK,
            NpcID.HERBI,
            NpcID.LIL_CREATOR,
            NpcID.LIL_DESTRUCTOR,
            NpcID.PENANCE_PET,
            NpcID.QUETZIN
    ));

    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private OverlayManager overlayManager;
    @Inject private PetScapeOverlay overlay;
    @Inject private PetScapeConfig config;

    // Key: "npcIndex:slot"
    final Map<String, PetScapeGhost> ghosts = new HashMap<>();

    private boolean wasInPoh = false;
    private boolean pendingFloorRebuild = false;
    private int floorRebuildAttempts = 0;
    private static final int MAX_FLOOR_REBUILD_ATTEMPTS = 3;

    // All walkable POH tiles — flood-filled on entry, shared with all ghosts
    Set<WorldPoint> pohFloor = Collections.emptySet();

    // Flood-fills walkable tiles from player position for walkable pet tiles
    private Set<WorldPoint> buildPohFloor()
    {
        Player local = client.getLocalPlayer();
        if (local == null) return Collections.emptySet();

        WorldPoint start = local.getWorldLocation();
        CollisionData[] maps = client.getTopLevelWorldView().getCollisionMaps();
        if (maps == null) return Collections.emptySet();

        byte[][][] tileSettings = client.getTopLevelWorldView().getTileSettings();
        boolean useTileSettings = false;
        if (tileSettings != null)
        {
            int plane = start.getPlane();
            int sx = start.getX() - client.getTopLevelWorldView().getBaseX();
            int sy = start.getY() - client.getTopLevelWorldView().getBaseY();
            if (plane >= 0 && plane < tileSettings.length
                    && tileSettings[plane] != null
                    && sx >= 0 && sy >= 0
                    && sx < tileSettings[plane].length
                    && sy < tileSettings[plane][sx].length)
            {
                useTileSettings = (tileSettings[plane][sx][sy] & 0x2) != 0;
            }
        }

        Set<Long> validChunks = new HashSet<>();
        int[][][] templateChunks = client.getInstanceTemplateChunks();
        int baseX = client.getTopLevelWorldView().getBaseX();
        int baseY = client.getTopLevelWorldView().getBaseY();

        int sumCx = 0, sumCy = 0, chunkCount = 0;
        int minCx = Integer.MAX_VALUE, maxCx = 0, minCy = Integer.MAX_VALUE, maxCy = 0;
        if (templateChunks != null)
        {
            for (int[][] plane : templateChunks) {
                if (plane == null) continue;
                for (int cx = 0; cx < plane.length; cx++) {
                    if (plane[cx] == null) continue;
                    for (int cy = 0; cy < plane[cx].length; cy++) {
                        int chunk = plane[cx][cy];
                        if (chunk == 0) continue;
                        int chunkX = (chunk >> 14) & 0x3FF;
                        int chunkY = (chunk >> 3) & 0x7FF;
                        int region = ((chunkX / 8) << 8) | (chunkY / 8);
                        if (!POH_TEMPLATE_REGIONS.contains(region)) continue;
                        Long key = ((long) cx << 32) | cy;
                        if (validChunks.add(key)) {
                            sumCx += cx;
                            sumCy += cy;
                            chunkCount++;
                            if (cx < minCx) minCx = cx;
                            if (cx > maxCx) maxCx = cx;
                            if (cy < minCy) minCy = cy;
                            if (cy > maxCy) maxCy = cy;
                        }
                    }
                }
            }
        }
        log.info("[PetScape] validChunks={} cx=[{},{}] cy=[{},{}] baseX={} baseY={}",
                validChunks.size(), minCx, maxCx, minCy, maxCy, baseX, baseY);
        if (validChunks.isEmpty()) pendingFloorRebuild = true;

        // Seed BFS from chunk centroid — center of house regardless of entry portal location
        WorldPoint seed;
        WorldPoint fallbackCenter = null;
        if (chunkCount == 0)
        {
            fallbackCenter = new WorldPoint(baseX + 52, baseY + 52, start.getPlane());
        }

        if (chunkCount > 0)
        {
            int centerChunkX = sumCx / chunkCount;
            int centerChunkY = sumCy / chunkCount;
            seed = new WorldPoint(
                    baseX + centerChunkX * 8 + 4,
                    baseY + centerChunkY * 8 + 4,
                    start.getPlane());
            if (net.runelite.api.coords.LocalPoint.fromWorld(
                    client.getTopLevelWorldView(), seed) == null)
                seed = start;
        }
        else
        {
            seed = fallbackCenter != null ? fallbackCenter : start;
        }

        Set<WorldPoint> visited = new HashSet<>();
        Queue<WorldPoint> queue = new ArrayDeque<>();
        queue.add(seed);
        visited.add(seed);
        if (!start.equals(seed))
        {
            queue.add(start);
            visited.add(start);
        }

        int[] dirs = {-1, 0, 1};
        final int MAX_TILES = 6000;

        while (!queue.isEmpty() && visited.size() < MAX_TILES)
        {
            WorldPoint cur = queue.poll();
            for (int dx : dirs)
            {
                for (int dy : dirs)
                {
                    if (dx == 0 && dy == 0) continue;
                    if (dx != 0 && dy != 0) continue;

                    WorldPoint next = new WorldPoint(cur.getX() + dx, cur.getY() + dy, cur.getPlane());
                    if (visited.contains(next)) continue;

                    if (!validChunks.isEmpty())
                    {
                        int lx = next.getX() - baseX;
                        int ly = next.getY() - baseY;
                        int cx = lx / 8;
                        int cy = ly / 8;
                        if (!validChunks.contains(((long) cx << 32) | (long) cy)) continue;
                    }
                    else if (fallbackCenter != null)
                    {
                        // No chunk data — cap at 28 tiles from centroid
                        if (Math.abs(next.getX() - fallbackCenter.getX()) > 28
                                || Math.abs(next.getY() - fallbackCenter.getY()) > 28) continue;
                    }

                    if (net.runelite.api.coords.LocalPoint.fromWorld(
                            client.getTopLevelWorldView(), next) == null) continue;

                    if (!new net.runelite.api.coords.WorldArea(cur, 1, 1)
                            .canTravelInDirection(client.getTopLevelWorldView(), dx, dy)) continue;

                    if (useTileSettings)
                    {
                        int plane = next.getPlane();
                        int sx = next.getX() - client.getTopLevelWorldView().getBaseX();
                        int sy = next.getY() - client.getTopLevelWorldView().getBaseY();
                        if (plane >= 0 && plane < tileSettings.length
                                && tileSettings[plane] != null
                                && sx >= 0 && sy >= 0
                                && sx < tileSettings[plane].length
                                && sy < tileSettings[plane][sx].length
                                && (tileSettings[plane][sx][sy] & 0x2) == 0) continue;
                    }

                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        log.info("[PetScape] POH floor mapped: {} tiles from {} (tileSettings={})",
                visited.size(), start, useTileSettings);
        return visited;
    }

    @Provides
    PetScapeConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(PetScapeConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
        clientThread.invoke(this::scanExistingNpcs);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        clientThread.invoke(() -> { ghosts.values().forEach(PetScapeGhost::despawn); ghosts.clear(); });
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        if (!"petscape".equals(event.getGroup()) || !"cloneCount".equals(event.getKey())) return;
        log.info("[PetScape] Clone count changed, respawning");
        clientThread.invoke(() -> {
            ghosts.values().forEach(PetScapeGhost::despawn);
            ghosts.clear();
            scanExistingNpcs();
        });
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        NPC npc = event.getNpc();
        if (!inPoh() || !PET_NPC_IDS.contains(npc.getId()) || isOnPlayerTile(npc)) return;

        // Reattach existing detached ghosts from NPC index if present
        String prefix = npc.getIndex() + ":";
        boolean hadDetached = false;
        for (Map.Entry<String, PetScapeGhost> entry : ghosts.entrySet())
        {
            if (entry.getKey().startsWith(prefix))
            {
                entry.getValue().reattach(npc);
                hadDetached = true;
            }
        }
        if (hadDetached) return;

        // Skip if floor not ready — instance still loading
        if (pohFloor.isEmpty()) return;
        int desired = config.cloneCount().getExtraClones();
        long petCount = client.getNpcs().stream()
                .filter(n -> PET_NPC_IDS.contains(n.getId()) && !isOnPlayerTile(n))
                .count();
        int slots = (petCount > 0 && petCount * desired > MAX_GHOST_CAP)
                ? (int)(MAX_GHOST_CAP / petCount)
                : desired;
        if (slots > 0) spawnGhostsForNpc(npc, slots);
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned event)
    {
        // Detach rather than despawn — pets in a POH just unload, not disappear
        int idx = event.getNpc().getIndex();
        for (String key : ghosts.keySet())
            if (key.startsWith(idx + ":")) ghosts.get(key).detach();
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        boolean inPoh = inPoh();

        if (client.getTickCount() % 20 == 0)
        {
            Player local = client.getLocalPlayer();
            log.info("[PetScape] TICK {} | inPoh={} | worldPos={} | ghosts={}",
                    client.getTickCount(), inPoh,
                    local != null ? local.getWorldLocation() : null, ghosts.size());
        }

        if (!inPoh)
        {
            if (!ghosts.isEmpty()) { ghosts.values().forEach(PetScapeGhost::despawn); ghosts.clear(); }
            pohFloor = Collections.emptySet();
            wasInPoh = false;
            pendingFloorRebuild = false;
            floorRebuildAttempts = 0;
            return;
        }

        if (!wasInPoh)
        {
            log.info("[PetScape] POH entry detected — scanning NPCs");
            scanExistingNpcs();
        }
        wasInPoh = true;

        if (pendingFloorRebuild && floorRebuildAttempts < MAX_FLOOR_REBUILD_ATTEMPTS)
        {
            pendingFloorRebuild = false;
            floorRebuildAttempts++;
            if (pohFloor.isEmpty())
            {
                scanExistingNpcs();
            }
            else
            {
                int[][][] tc = client.getInstanceTemplateChunks();
                boolean ready = false;
                if (tc != null) { outer: for (int[][] p : tc) { if (p==null) continue; for (int[] r : p) { if (r==null) continue; for (int c : r) { if (c!=0){ready=true;break outer;} } } } }
                if (ready)
                {
                    pohFloor = buildPohFloor();
                }
                else
                {
                    pendingFloorRebuild = true;
                }
            }
        }
        else if (pendingFloorRebuild)
        {
            pendingFloorRebuild = false;
        }

        ghosts.values().forEach(PetScapeGhost::gameTick);
    }

    @Subscribe
    public void onClientTick(ClientTick event)
    {
        if (!inPoh()) return;
        ghosts.values().forEach(PetScapeGhost::clientTick);
    }

    // Swaps original pet menus so "Walk here" is the left-click default (instead of using menu entry swap for them individually)
    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {
        if (!inPoh() || !config.swapRealPetWalkHere()) return;

        int type = event.getType();
        if (type != MenuAction.NPC_FIRST_OPTION.getId()
                && type != MenuAction.NPC_SECOND_OPTION.getId()
                && type != MenuAction.NPC_THIRD_OPTION.getId()
                && type != MenuAction.NPC_FOURTH_OPTION.getId()
                && type != MenuAction.NPC_FIFTH_OPTION.getId()) return;

        int npcIndex = event.getIdentifier();
        boolean isTrackedPet = ghosts.keySet().stream()
                .anyMatch(key -> key.startsWith(npcIndex + ":"));
        if (!isTrackedPet) return;

        MenuEntry[] entries = client.getMenuEntries();
        int walkIdx = -1;
        for (int i = 0; i < entries.length; i++)
        {
            if (entries[i].getType() == MenuAction.WALK)
            {
                walkIdx = i;
                break;
            }
        }
        if (walkIdx < 0 || walkIdx == entries.length - 1) return;

        MenuEntry walk = entries[walkIdx];
        System.arraycopy(entries, walkIdx + 1, entries, walkIdx, entries.length - 1 - walkIdx);
        entries[entries.length - 1] = walk;
        client.setMenuEntries(entries);
    }

        // Max Ghost Cap - tested with 350 on mid/upper ranged gpu, had 56~fps with 350 cap + camera plugin extra zoom out + 117HD plugin max settings
        // Tested higher limits, 580 caused 12fps with above settings
    private static final int MAX_GHOST_CAP = 350;

    private void spawnGhostsForNpc(NPC npc, int slots)
    {
        for (int slot = 0; slot < slots; slot++)
        {
            String key = npc.getIndex() + ":" + slot;
            if (ghosts.containsKey(key)) continue;
            ghosts.put(key, new PetScapeGhost(npc, client, clientThread, ghosts.values(), slots, pohFloor));
        }
    }

    private void scanExistingNpcs()
    {
        if (!inPoh()) return;

        List<NPC> eligible = new ArrayList<>();
        for (NPC npc : client.getNpcs())
        {
            if (!PET_NPC_IDS.contains(npc.getId())) continue;
            eligible.add(npc);
        }

        if (eligible.isEmpty())
        {
            pendingFloorRebuild = true;
            return;
        }

        floorRebuildAttempts = 0;
        pohFloor = buildPohFloor();

        int desired = config.cloneCount().getExtraClones();
        int petCount = eligible.size();

        // Distribute evenly if total clones would exceed cap
        int floor, remainder;
        if ((long) petCount * desired > MAX_GHOST_CAP)
        {
            floor = MAX_GHOST_CAP / petCount;
            remainder = MAX_GHOST_CAP % petCount;
        }
        else
        {
            floor = desired;
            remainder = 0;
        }

        if (floor == 0)
        {
            return;
        }

        // Shuffle so remainder extra clones are assigned randomly
        List<NPC> shuffled = new ArrayList<>(eligible);
        java.util.Collections.shuffle(shuffled);

        for (int i = 0; i < shuffled.size(); i++)
        {
            int slots = floor + (i < remainder ? 1 : 0);
            spawnGhostsForNpc(shuffled.get(i), slots);
        }
    }

    private boolean isOnPlayerTile(NPC npc)
    {
        Player local = client.getLocalPlayer();
        if (local == null) return false;
        WorldPoint pt = local.getWorldLocation(), nt = npc.getWorldLocation();
        return pt != null && pt.equals(nt);
    }

    private boolean inPoh()
    {
        if (!client.isInInstancedRegion()) return false;
        // Makes sure pets don't escape POH bounds — allows other PoH if option enabled
        if (config.allowOtherPoh()) return true;
        int[][][] chunks = client.getInstanceTemplateChunks();
        if (chunks == null) return false;
        for (int[][] plane : chunks) { if (plane == null) continue;
            for (int[] row : plane) { if (row == null) continue;
                for (int chunk : row) { if (chunk == 0) continue;
                    int chunkX = (chunk >> 14) & 0x3FF, chunkY = (chunk >> 3) & 0x7FF;
                    if (POH_TEMPLATE_REGIONS.contains(((chunkX / 8) << 8) | (chunkY / 8))) return true;
                }}}
        return false;
    }
}

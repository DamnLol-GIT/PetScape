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
import net.runelite.api.gameval.NpcID;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
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
        description = "Visually multiplies all pets in your Player Owned House",
        tags = {"pet","poh","pvm","boss","skilling","follower","populate","wildlife","spawn","immersion"}
)
public class PetScapePlugin extends Plugin
{

    private static final Set<Integer> POH_TEMPLATE_REGIONS = Set.of(
            7790, 7791, 8046, 8047, 8302, 8303,
            8558, 8559, 8814, 8815, 9070, 9071
    );

    static final String[] WANDER_LINES = {};

    static final Set<Integer> PET_NPC_IDS = new HashSet<>(Arrays.asList(
            NpcID.POH_ABYSSALSIRE_PET,
            NpcID.POH_ABYSSAL_PET,
            NpcID.POH_WARDEN_PET_AKKHA,
            NpcID.XMAS24_YORKIE_FINAL,
            NpcID.POH_EASTER26_EGG,
            NpcID.POH_EASTER26_EGG_02,
            NpcID.POH_EASTER26_EGG_03,
            NpcID.POH_EASTER26_EGG_04,
            NpcID.POH_EASTER26_EGG_05,
            NpcID.POH_EASTER26_EGG_06,
            NpcID.POH_EASTER26_EGG_07,
            NpcID.POH_WARDEN_PET_BABA,
            NpcID.POH_SKILLPET_HUNTER_RED,
            NpcID.MOLE_BABY_01,
            NpcID.POH_MOLE_PET_NAKED,
            NpcID.POH_MOLE_PET,
            NpcID.POH_DUKE_SUCELLUS_PET,
            NpcID.POH_SKILLPETWC,
            NpcID.COWBOSS_PET,
            NpcID.POH_COWBOSS_PET,
            NpcID.POH_BLOODHOUNDPET,
            NpcID.BLOODHOUNDPET,
            NpcID.POH_SKILLPET_AGILITY_BONE,
            NpcID.POH_RTBRANDA_PET,
            NpcID.WGS_BROAV,
            NpcID.POH_BROAV,
            NpcID.POH_VARDORVIS_PET,
            NpcID.POH_CALLISTO_PET,
            NpcID.POH_CALLISTO_PET_LEGACY,
            NpcID.CALLISTOPET_LEGACY,
            NpcID.CALLISTOPET,
            NpcID.POH_GROWNCAT_BLACK,
            NpcID.CHAOS_ELEMENTAL_PET,
            NpcID.POH_CHAOS_ELEMENTAL_PET,
            NpcID.POH_CHOMPYBIRD_PET,
            NpcID.POH_CORPPET,
            NpcID.CORP_PET,
            NpcID.POH_GAUNTLET_PET_CORRUPT,
            NpcID.POH_PRIME_PET,
            NpcID.REX_PET,
            NpcID.POH_SUPREME_PET,
            NpcID.SKILLPET_AGILITY_DARK,
            NpcID.POH_SKILLPET_AGILITY_DARK,
            NpcID.POH_DOM_PET,
            NpcID.DOM_PET,
            NpcID.POH_WARDEN_PET_ELIDINIS_DESTROYED,
            NpcID.POH_WARDEN_PET_ELIDINIS,
            NpcID.POH_FISHBOWL_GREENFISH,
            NpcID.POH_FISHBOWL_SPINEFISH,
            NpcID.POH_VESPULA_FLYING_PET,
            NpcID.POH_SKILLPET_WC_FOX,
            NpcID.BANDOS_PET,
            NpcID.POH_SKILLPET_AGILITY,
            NpcID.SEABIRD1,
            NpcID.POH_GRYPHONBOSS_PET_ADULT,
            NpcID.GRYPHONBOSS_PET,
            NpcID.POH_GRYPHONBOSS_PET,
            NpcID.KITTENPET_HELL,
            NpcID.GROWNCAT_HELL,
            NpcID.POH_GROWNCAT_HELL,
            NpcID.POH_HELLPET,
            NpcID.POH_HERBIBOAR_PET,
            NpcID.POH_SKILLPET_FISH,
            NpcID.POH_HUEY_PET,
            NpcID.HYDRA_PET,
            NpcID.HYDRA_PET_ELECTRIC,
            NpcID.HYDRA_PET_FIRE,
            NpcID.HYDRA_PET_EXTINGUISHED,
            NpcID.POH_HYDRA_PET_EXTINGUISHED,
            NpcID.POH_INFERNO_PET,
            NpcID.POH_JADPET_INFERNO,
            NpcID.KQ_PET_FLYING,
            NpcID.KQ_PET_WALKING,
            NpcID.POH_KQ_PET_FLYING,
            NpcID.POH_KQ_PET_WALKING,
            NpcID.POH_WARDEN_PET_KEPHRI,
            NpcID.KRAKEN_PET,
            NpcID.POH_KRAKEN_PET,
            NpcID.ARMADYL_PET,
            NpcID.POH_ARMADYL_PET,
            NpcID.ZAMORAK_PET,
            NpcID.POH_ZAMORAK_PET,
            NpcID.POH_LEVIATHAN_PET,
            NpcID.POH_VERZIK_PET_BLOAT,
            NpcID.POH_SOULWARS_PET_BLUE,
            NpcID.POH_SOULWARS_PET_RED,
            NpcID.POH_VERZIK_PET_MAIDEN,
            NpcID.POH_VERZIK_PET_NYLOCAS,
            NpcID.POH_VERZIK_PET_SOTETSEG,
            NpcID.POH_VERZIK_PET_XARPUS,
            NpcID.POH_VERZIK_PET,
            NpcID.POH_NIGHTMARE_PET,
            NpcID.POH_NIGHTMARE_PET_PARASITE,
            NpcID.POH_FISHBOWL_MAYOR_OF_CATHERBY,
            NpcID.POH_DUSK_PET,
            NpcID.POH_AMOXLIATL_PET,
            NpcID.POH_MUSPAH_PET,
            NpcID.POH_MUSPAH_PET_MELEE,
            NpcID.POH_MUSPAH_PET_SHIELDED,
            NpcID.MUSPAH_PET,
            NpcID.MUSPAH_PET_MELEE,
            NpcID.MUSPAH_PET_SHIELDED,
            NpcID.POH_NEX_PET,
            NpcID.POH_ARAXXOR_PET,
            NpcID.ARAXXOR_PET,
            NpcID.POH_DAWN_PET,
            NpcID.POH_OLM_PET,
            NpcID.POH_OVERGROWNCAT_HELL,
            NpcID.POH_PENANCE_PET,
            NpcID.POH_ROCK,
            NpcID.MACRO_PHEASANT_MODEL_1,
            NpcID.POH_SKILLPET_WC_PHEASANT,
            NpcID.POH_PHOENIX_PET_GREEN,
            NpcID.POH_PHOENIX_PET_BLUE,
            NpcID.POH_PHOENIX_PET_WHITE,
            NpcID.POH_PHOENIX_PET_PURPLE,
            NpcID.PHOENIX_PET_GREEN,
            NpcID.PHOENIX_PET_BLUE,
            NpcID.PHOENIX_PET_WHITE,
            NpcID.PHOENIX_PET_PURPLE,
            NpcID.POH_PHOENIX_PET,
            NpcID.PHOENIX_PET,
            NpcID.KBD_PET,
            NpcID.POH_KBD_PET,
            NpcID.POH_DOGADILE_PET,
            NpcID.POH_QUETZAL_PET,
            NpcID.POH_ARAXXOR_PET_CUTE,
            NpcID.ARAXXOR_PET_CUTE,
            NpcID.POH_SKILLPET_THIEVING_PANDA,
            NpcID.POH_RTELDRIC_PET,
            NpcID.POH_SKILLPET_RUNECRAFTING_FIRE,
            NpcID.POH_SKILLPET_THIEVING,
            NpcID.POH_SKILLPET_MINING_AMETHYST,
            NpcID.POH_SKILLPET_MINING_DEFAULT,
            NpcID.POH_SKILLPET_MINING_TIN,
            NpcID.POH_SKILLPET_MINING_COPPER,
            NpcID.POH_SKILLPET_MINING_IRON,
            NpcID.POH_SKILLPET_MINING_BLURITE,
            NpcID.POH_SKILLPET_MINING_SILVER,
            NpcID.POH_SKILLPET_MINING_COAL,
            NpcID.POH_SKILLPET_MINING_GOLD,
            NpcID.POH_SKILLPET_MINING_MITHRIL,
            NpcID.POH_SKILLPET_MINING_GRANITE,
            NpcID.POH_SKILLPET_MINING_ADAMANTITE,
            NpcID.POH_SKILLPET_MINING_RUNITE,
            NpcID.SKILLPET_MINING_DEFAULT,
            NpcID.SKILLPET_MINING_TIN,
            NpcID.SKILLPET_MINING_COPPER,
            NpcID.SKILLPET_MINING_IRON,
            NpcID.SKILLPET_MINING_BLURITE,
            NpcID.SKILLPET_MINING_SILVER,
            NpcID.SKILLPET_MINING_COAL,
            NpcID.SKILLPET_MINING_GOLD,
            NpcID.SKILLPET_MINING_MITHRIL,
            NpcID.SKILLPET_MINING_GRANITE,
            NpcID.SKILLPET_MINING_ADAMANTITE,
            NpcID.SKILLPET_MINING_RUNITE,
            NpcID.POH_SKILLPET_MINING_ELEMENTAL,
            NpcID.POH_SKILLPET_MINING_DAEYALT,
            NpcID.SKILLPET_MINING_ELEMENTAL,
            NpcID.SKILLPET_MINING_DAEYALT,
            NpcID.POH_SCORPIA_PET,
            NpcID.POH_SCURRIUS_PET,
            NpcID.POH_SKOTIZO_PET,
            NpcID.SKOTIZO_PET,
            NpcID.POH_SMOKE_PET,
            NpcID.POH_SMOKE_PET_OLD,
            NpcID.POH_ZALCANO_PET,
            NpcID.ZALCANO_PET,
            NpcID.POH_SOLHEREDIT_PET,
            NpcID.POH_SNAKE_PET_GREEN,
            NpcID.POH_SNAKE_PET_ORANGE,
            NpcID.POH_SNAKE_PET_BLUE,
            NpcID.SKILLPET_SAILING,
            NpcID.POH_SKILLPET_SAILING,
            NpcID.POH_HW_CHAIR,
            NpcID.POH_MENAGERIE_SARACHNISPET,
            NpcID.POH_MENAGERIE_SARACHNISPET_ORANGE,
            NpcID.POH_MENAGERIE_SARACHNISPET_BLUE,
            NpcID.POH_SKILLPET_FARMING,
            NpcID.SKILLPET_FARMING,
            NpcID.POH_SKILLPET_FARMING_CRYSTAL,
            NpcID.POH_SKILLPET_FARMING_DRAGON,
            NpcID.POH_SKILLPET_FARMING_HERB,
            NpcID.POH_SKILLPET_FARMING_LILY,
            NpcID.POH_SKILLPET_FARMING_REDWOOD,
            NpcID.SKILLPET_FARMING_CRYSTAL,
            NpcID.SKILLPET_FARMING_DRAGON,
            NpcID.SKILLPET_FARMING_HERB,
            NpcID.SKILLPET_FARMING_LILY,
            NpcID.SKILLPET_FARMING_REDWOOD,
            NpcID.POH_TEKTON_PET,
            NpcID.POH_TEMPOROSS_PET,
            NpcID.POH_WARDEN_PET_TUMEKEN_DESTROYED,
            NpcID.POH_WARDEN_PET_TUMEKEN,
            NpcID.WARDEN_PET_TUMEKEN,
            NpcID.POH_JADPET,
            NpcID.POH_ZUK_PET,
            NpcID.POH_VANGUARD_PET,
            NpcID.POH_VASA_PET,
            NpcID.POH_VENENATIS_PET,
            NpcID.POH_VENENATIS_PET_LEGACY,
            NpcID.POH_VESPULA_PET,
            NpcID.POH_VETION_PET,
            NpcID.POH_VETION_PET_LEGACY,
            NpcID.POH_VETION_PET_2_LEGACY,
            NpcID.POH_VETION_PET_2,
            NpcID.VETIONPET,
            NpcID.POH_VORKATH_PET,
            NpcID.WILEYCAT_HELL,
            NpcID.POH_WILEYCAT_HELL,
            NpcID.POH_WHISPERER_PET,
            NpcID.POH_YAMA_PET,
            NpcID.POH_GAUNTLET_PET,
            NpcID.POH_WARDEN_PET_ZEBAK,
            NpcID.POH_SKILLPET_THIEVING_TANUKI,
            NpcID.SARADOMIN_PET,
            NpcID.POH_SARADOMIN_PET,
            NpcID.MAGGOT_KING_PET,
            NpcID.POH_MAGGOT_KING_PET,
            NpcID.MAD_ANGEL_PET,
            NpcID.POH_MAD_ANGEL_PET
    ));

    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private OverlayManager overlayManager;
    @Inject private PetScapeOverlay overlay;
    @Inject private PetScapeConfig config;
    @Inject private RoamingPetManager roamingPetManager;

    // Key: npcIndex:slot
    final Map<String, PetScapeGhost> ghosts = new HashMap<>();

    // Tracks untracked debug log - once per session
    private final Set<Integer> loggedUntrackedIds = new HashSet<>();

    private PetFamilyFollower petFamilyFollower = null;

    private boolean wasInPoh = false;
    private boolean pendingFloorRebuild = false;
    private int floorRebuildAttempts = 0;
    private int pohGroundPlane = Integer.MIN_VALUE;
    private boolean wasOnGroundPlane = true;
    private boolean sceneStable = false;
    private int planeLockDelay = 0;
    private int lastInstanceHash = 0;
    private static final int MAX_FLOOR_REBUILD_ATTEMPTS = 3;

    // All walkable POH tiles — flood-filled on entry, shared with all ghosts
    Set<WorldPoint> pohFloor = Collections.emptySet();

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

        // Collect instance chunks that belong to POH template regions
        Set<Long> validChunks = new HashSet<>();
        int[][][] templateChunks = client.getInstanceTemplateChunks();
        int baseX = client.getTopLevelWorldView().getBaseX();
        int baseY = client.getTopLevelWorldView().getBaseY();

        int sumCx = 0, sumCy = 0, chunkCount = 0;
        int minCx = Integer.MAX_VALUE, maxCx = 0, minCy = Integer.MAX_VALUE, maxCy = 0;
        Set<Integer> debugRegions = new TreeSet<>();
        if (templateChunks != null)
        {
            for (int[][] plane : templateChunks)
            {
                if (plane == null) continue;
                for (int cx = 0; cx < plane.length; cx++)
                {
                    if (plane[cx] == null) continue;
                    for (int cy = 0; cy < plane[cx].length; cy++)
                    {
                        int chunk = plane[cx][cy];
                        if (chunk == 0) continue;
                        int chunkX = (chunk >> 14) & 0x3FF;
                        int chunkY = (chunk >> 3) & 0x7FF;
                        int region = ((chunkX / 8) << 8) | (chunkY / 8);
                        debugRegions.add(region);
                        if (!POH_TEMPLATE_REGIONS.contains(region)) continue;
                        Long key = ((long) cx << 32) | cy;
                        if (validChunks.add(key))
                        {
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

        log.info("[PetScape] validChunks={} cx=[{},{}] cy=[{},{}] baseX={} baseY={} | allRegions={}",
                validChunks.size(), minCx, maxCx, minCy, maxCy, baseX, baseY, debugRegions);

        final int POH_MAX_FALLBACK_RADIUS = 45;
        final boolean usingFallback = validChunks.isEmpty();

        WorldPoint seed;
        if (chunkCount > 0)
        {
            int centerChunkX = sumCx / chunkCount;
            int centerChunkY = sumCy / chunkCount;
            seed = new WorldPoint(
                    baseX + centerChunkX * 8 + 4,
                    baseY + centerChunkY * 8 + 4,
                    start.getPlane());
            if (LocalPoint.fromWorld(client.getTopLevelWorldView(), seed) == null)
            {
                seed = start;
            }
        }
        else
        {
            seed = start;
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
                    if (dx != 0 && dy != 0) continue; // cardinal only

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
                    else if (usingFallback)
                    {
                        if (Math.abs(next.getX() - seed.getX()) > POH_MAX_FALLBACK_RADIUS
                                || Math.abs(next.getY() - seed.getY()) > POH_MAX_FALLBACK_RADIUS) continue;
                    }

                    // Must be within the renderable scene
                    if (LocalPoint.fromWorld(client.getTopLevelWorldView(), next) == null) continue;

                    // Collision check
                    if (!new WorldArea(cur, 1, 1)
                            .canTravelInDirection(client.getTopLevelWorldView(), dx, dy)) continue;

                    // Bridge/elevated tile filter
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

        log.info("[PetScape] POH floor mapped: {} tiles from seed {} (tileSettings={})",
                visited.size(), seed, useTileSettings);
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
        roamingPetManager.startUp();
        clientThread.invoke(this::scanExistingNpcs);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        roamingPetManager.shutDown();
        clientThread.invoke(() ->
        {
            if (petFamilyFollower != null) { petFamilyFollower.despawn(); petFamilyFollower = null; }
            ghosts.values().forEach(PetScapeGhost::despawn);
            ghosts.clear();
        });
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        if (!"petscape".equals(event.getGroup())) return;
        if ("cloneCount".equals(event.getKey()))
        {
            log.info("[PetScape] Clone count changed, respawning");
            clientThread.invoke(() -> {
                ghosts.values().forEach(PetScapeGhost::despawn);
                ghosts.clear();
                scanExistingNpcs();
            });
        }
        else if ("petFamily".equals(event.getKey()))
        {
            clientThread.invoke(() ->
            {
                if (petFamilyFollower != null) { petFamilyFollower.despawn(); petFamilyFollower = null; }
                PetFamilyFollower.FamilySize size = config.petFamily();
                if (size != PetFamilyFollower.FamilySize.NONE)
                {
                    NPC follower = client.getFollower();
                    if (follower != null)
                        petFamilyFollower = new PetFamilyFollower(follower, client, clientThread, size.getCount());
                }
            });
        }
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        NPC npc = event.getNpc();

        // PoH UNTRACKED scan - any PoH to grab missing pet info
        if (inPoh() && !PET_NPC_IDS.contains(npc.getId()) && !isOnPlayerTile(npc)
                && loggedUntrackedIds.add(npc.getId()))
            log.info("[PetScape UNTRACKED] {} (ID: {})", npc.getName(), npc.getId());

        if (!inPoh() || !PET_NPC_IDS.contains(npc.getId()) || isOnPlayerTile(npc)) return;

        // Only create ghosts on ground plane
        Player local = client.getLocalPlayer();
        if (pohGroundPlane != Integer.MIN_VALUE && local != null
                && local.getWorldLocation().getPlane() != pohGroundPlane) return;

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
        int slots = (petCount > 0 && petCount * desired > getEffectiveGhostCap())
                ? (int)(getEffectiveGhostCap() / petCount)
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
    public void onGameStateChanged(GameStateChanged event)
    {
        // Prevents stale LocalPoint rendering and keeps transition smooth
        GameState state = event.getGameState();
        if (state == GameState.LOADING)
        {
            sceneStable = false;
            planeLockDelay = 0;
            roamingPetManager.onScenePreLoad();
        }
        else if (state == GameState.LOGGED_IN)
        {
            sceneStable = true;
            planeLockDelay = 1;
            roamingPetManager.onSceneChange();
        }
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        roamingPetManager.gameTick();

        // Pet Family — works anywhere, runs before PoH logic
        if (config.petFamily() != PetFamilyFollower.FamilySize.NONE) handlePetFamily();
        else if (petFamilyFollower != null) { petFamilyFollower.despawn(); petFamilyFollower = null; }

        boolean inPoh = inPoh();
        Player local = client.getLocalPlayer();

        if (client.getTickCount() % 20 == 0)
        {
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
            pohGroundPlane = Integer.MIN_VALUE;
            wasOnGroundPlane = true;
            lastInstanceHash = 0;
            planeLockDelay = 0;
            return;
        }

        int instanceHash = computeInstanceHash();
        if (wasInPoh && instanceHash != lastInstanceHash)
        {
            log.info("[PetScape] POH instance changed — clearing ghosts and re-entering");
            ghosts.values().forEach(PetScapeGhost::despawn);
            ghosts.clear();
            pohFloor = Collections.emptySet();
            wasInPoh = false;
            pendingFloorRebuild = false;
            floorRebuildAttempts = 0;
            pohGroundPlane = Integer.MIN_VALUE;
            wasOnGroundPlane = true;
            planeLockDelay = 1;
        }
        lastInstanceHash = instanceHash;

        if (!wasInPoh)
        {
            // Wait for instance load to complete before reading plane
            if (local == null || !sceneStable) return;
            if (planeLockDelay > 0) { planeLockDelay--; return; }
            pohGroundPlane = local.getWorldLocation().getPlane();
            log.info("[PetScape] POH entry detected on plane {} — scanning NPCs", pohGroundPlane);
            scanExistingNpcs();
        }
        wasInPoh = true;

        // Hide ghosts only on transition off ground plane - restore on transition back
        int currentPlane = local != null ? local.getWorldLocation().getPlane() : pohGroundPlane;
        boolean onGround = currentPlane == pohGroundPlane;
        if (onGround != wasOnGroundPlane)
        {
            final boolean hide = !onGround;
            ghosts.values().forEach(g -> g.setHidden(hide));
            wasOnGroundPlane = onGround;
        }
        if (!onGround) return;

        if (pendingFloorRebuild && floorRebuildAttempts < MAX_FLOOR_REBUILD_ATTEMPTS)
        {
            pendingFloorRebuild = false;
            floorRebuildAttempts++;

            if (pohFloor.isEmpty())
            {
                // No floor yet — attempt a full scan/build
                scanExistingNpcs();
            }
            else
            {
                // Rebuild floor and propagate corrected bounds to all ghosts
                pohFloor = buildPohFloor();
                if (!pohFloor.isEmpty())
                {
                    ghosts.values().forEach(g -> g.updateFloor(pohFloor));
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
        roamingPetManager.clientTick();

        if (config.petFamily() != PetFamilyFollower.FamilySize.NONE && petFamilyFollower != null) petFamilyFollower.clientTick();
        if (!inPoh()) return;
        ghosts.values().forEach(PetScapeGhost::clientTick);
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event)
    {
        if (!config.truePetScape()) return;

        // Hide right-click menu when in bank/ui
        boolean isWorldMenu = false;
        for (MenuEntry e : event.getMenuEntries())
        {
            if ("Walk here".equals(e.getOption())) { isWorldMenu = true; break; }
        }
        if (!isWorldMenu) return;

        net.runelite.api.Point mouse = client.getMouseCanvasPosition();
        if (mouse == null) return;

        // Find the closest rendered spawn to the click position within spawn radius
        RoamingPetSpawn closest = null;
        final int mx = mouse.getX(), my = mouse.getY();

        for (RoamingPetSpawn spawn : roamingPetManager.getRenderedSpawns())
        {
            WorldPoint wp = spawn.getCurrentWorld();
            if (wp == null) continue;

            // Use rendered location so hull lines up with visible model mid-step
            LocalPoint lp = spawn.getRuneLiteObject().getLocation();
            if (lp == null) continue;

            net.runelite.api.Model model = spawn.getRuneLiteObject().getModel();
            if (model == null) continue;

            int tileZ = Perspective.getTileHeight(client, lp, client.getPlane());
            int z = spawn.getZOffset() == 0 ? tileZ : tileZ - spawn.getZOffset();
            int orientation = spawn.getRuneLiteObject().getOrientation();

            java.awt.Shape hull = Perspective.getClickbox(client, client.getTopLevelWorldView(), model, orientation,
                    lp.getX(), lp.getY(), z);
            if (hull == null) continue;

            if (hull.contains(mx, my))
            {
                closest = spawn;
                break;
            }
        }

        if (closest == null) return;
        final String examineText = closest.getExamineText();
        if (examineText == null || examineText.isEmpty()) return;
        final String menuTarget = closest.getMenuTarget();
        if (menuTarget == null || menuTarget.isEmpty()) return;

        client.createMenuEntry(1)
                .setOption("Examine")
                .setTarget(menuTarget)
                .setType(MenuAction.RUNELITE)
                .onClick(e -> client.addChatMessage(
                        ChatMessageType.GAMEMESSAGE, "", examineText, ""));
    }

    // PoH: swaps original pet menus so "Walk here" is the left-click default
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

    private void handlePetFamily()
    {
        NPC follower = client.getFollower();
        if (follower == null)
        {
            if (petFamilyFollower != null) { petFamilyFollower.despawn(); petFamilyFollower = null; }
            return;
        }
        // Recreate if the pet/chain changed
        int desiredChain = config.petFamily().getCount();
        if (petFamilyFollower != null && (petFamilyFollower.getRealNpc() != follower
                || petFamilyFollower.getChainLength() != desiredChain))
        {
            petFamilyFollower.despawn();
            petFamilyFollower = null;
        }
        if (petFamilyFollower == null)
            petFamilyFollower = new PetFamilyFollower(follower, client, clientThread, desiredChain);
        petFamilyFollower.gameTick();
    }

    // Max Ghost Cap - tested with 350 on mid/upper ranged gpu, had 56~fps with 350 cap + camera plugin extra zoom out + 117HD plugin max settings
    // Tested higher limits, 580 caused 12fps with above settings
    private static final int MAX_GHOST_CAP = 350;

    // Naturally we need an uncap option for real gamers
    private int getEffectiveGhostCap()
    {
        return config.disablePetLimit() ? Integer.MAX_VALUE : MAX_GHOST_CAP;
    }

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

        if (pohFloor.isEmpty())
        {
            return;
        }

        int desired = config.cloneCount().getExtraClones();
        int petCount = eligible.size();

        // Distribute evenly if total clones would exceed cap
        int floor, remainder;
        if ((long) petCount * desired > getEffectiveGhostCap())
        {
            floor = getEffectiveGhostCap() / petCount;
            remainder = getEffectiveGhostCap() % petCount;
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
        Collections.shuffle(shuffled);

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
        // Makes sure pets dont escape POH bounds - allows other PoH if option enabled
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

    // Rolling hash of instance chunks - changes when player moves to a different POH instance
    private int computeInstanceHash()
    {
        int[][][] chunks = client.getInstanceTemplateChunks();
        if (chunks == null) return 0;
        int h = 1;
        for (int[][] plane : chunks) { if (plane == null) continue;
            for (int[] row : plane) { if (row == null) continue;
                for (int chunk : row) h = 31 * h + chunk;
            }}
        return h;
    }
}
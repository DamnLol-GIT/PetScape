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

package com.DamnLol.PetScape.Locations;

import com.DamnLol.PetScape.RoamingArea;
import net.runelite.api.NpcID;

import java.util.Arrays;


public class WildyEdgeArea extends RoamingArea
{
    private final int zone;

    private WildyEdgeArea(int zone) { this.zone = zone; }

    public static WildyEdgeArea zone1() { return new WildyEdgeArea(1); }
    public static WildyEdgeArea zone2() { return new WildyEdgeArea(2); }
    public static WildyEdgeArea zone3() { return new WildyEdgeArea(3); }
    public static WildyEdgeArea zone4() { return new WildyEdgeArea(4); }
    public static WildyEdgeArea zone5() { return new WildyEdgeArea(5); }
    public static WildyEdgeArea zone6() { return new WildyEdgeArea(6); }

    @Override
    public String getAreaId() { return "wildyedge_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{
                    NpcID.CALLISTO_CUB, NpcID.CALLISTO_CUB_11982,
                    NpcID.VETION_JR, NpcID.VETION_JR_11983,
                    NpcID.VENENATIS_SPIDERLING, NpcID.VENENATIS_SPIDERLING_11981,
                    NpcID.SCORPIAS_OFFSPRING,
                    NpcID.CHAOS_ELEMENTAL_JR
            };
            case 2:
            case 3: return new int[]{
                    NpcID.CALLISTO_CUB,
                    NpcID.CHAOS_ELEMENTAL_JR,
                    NpcID.SCORPIAS_OFFSPRING,
                    NpcID.VETION_JR,
                    NpcID.VENENATIS_SPIDERLING
            };
            case 4: return new int[]{ NpcID.CALLISTO_CUB };
            case 5: return new int[]{ NpcID.CHAOS_ELEMENTAL_JR };
            default: return new int[]{
                    NpcID.CHAOS_ELEMENTAL_JR,
                    NpcID.CALLISTO_CUB,
                    NpcID.VENENATIS_SPIDERLING,
                    NpcID.SCORPIAS_OFFSPRING,
                    NpcID.VETION_JR
            };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 1 Callisto Cub(0), 1 Callisto Cub retro(1),
                // 1 Vet'ion Jr(2), 1 Vet'ion Jr retro(3),
                // 1 Venenatis Spiderling(4), 1 Venenatis Spiderling retro(5),
                // 2 Scorpia's Offspring(6), 2 Chaos Elemental Jr(7)
                if (spawnIndex < 6) return spawnIndex;
                if (spawnIndex < 8) return 6;
                return 7;
            case 2:
            case 3:
                // 2 Callisto Cub(0), 1 Chaos Elemental Jr(1), 1 Scorpia's Offspring(2),
                // 1 Vet'ion Jr(3), 1 Venenatis Spiderling(4)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 3) return 1;
                if (spawnIndex < 4) return 2;
                if (spawnIndex < 5) return 3;
                return 4;
            case 6:
                // 3 Chaos Elemental Jr(0), 3 Callisto Cub(1),
                // 2 Venenatis Spiderling(2), 2 Scorpia's Offspring(3), 2 Vet'ion Jr(4)
                if (spawnIndex < 3) return 0;
                if (spawnIndex < 6) return 1;
                if (spawnIndex < 8) return 2;
                if (spawnIndex < 10) return 3;
                return 4;
            default:
                return 0;
        }
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Callisto Cub";
                    else if (i < 4) names[i] = "Vet'ion Jr.";
                    else if (i < 6) names[i] = "Venenatis Spiderling";
                    else if (i < 8) names[i] = "Scorpia's Offspring";
                    else names[i] = "Chaos Elemental Jr.";
                }
                break;
            case 2:
            case 3:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Callisto Cub";
                    else if (i < 3) names[i] = "Chaos Elemental Jr.";
                    else if (i < 4) names[i] = "Scorpia's Offspring";
                    else if (i < 5) names[i] = "Vet'ion Jr.";
                    else names[i] = "Venenatis Spiderling";
                }
                break;
            case 4:
                Arrays.fill(names, "Callisto Cub");
                break;
            case 5:
                Arrays.fill(names, "Chaos Elemental Jr.");
                break;
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 3) names[i] = "Chaos Elemental Jr.";
                    else if (i < 6) names[i] = "Callisto Cub";
                    else if (i < 8) names[i] = "Venenatis Spiderling";
                    else if (i < 10) names[i] = "Scorpia's Offspring";
                    else names[i] = "Vet'ion Jr.";
                }
                break;
        }
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                if (formIndex <= 1) return "<col=ffff00>Callisto Cub</col>";
                if (formIndex <= 3) return "<col=ffff00>Vet'ion Jr.</col>";
                if (formIndex <= 5) return "<col=ffff00>Venenatis Spiderling</col>";
                if (formIndex == 6) return "<col=ffff00>Scorpia's Offspring</col>";
                return "<col=ffff00>Chaos Elemental Jr.</col>";
            case 2:
            case 3:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Callisto Cub</col>";
                    case 1: return "<col=ffff00>Chaos Elemental Jr.</col>";
                    case 2: return "<col=ffff00>Scorpia's Offspring</col>";
                    case 3: return "<col=ffff00>Vet'ion Jr.</col>";
                    default: return "<col=ffff00>Venenatis Spiderling</col>";
                }
            case 4:
                return "<col=ffff00>Callisto Cub</col>";
            case 5:
                return "<col=ffff00>Chaos Elemental Jr.</col>";
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Chaos Elemental Jr.</col>";
                    case 1: return "<col=ffff00>Callisto Cub</col>";
                    case 2: return "<col=ffff00>Venenatis Spiderling</col>";
                    case 3: return "<col=ffff00>Scorpia's Offspring</col>";
                    default: return "<col=ffff00>Vet'ion Jr.</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                if (formIndex <= 1) return "Bear-ly interested in the Wilderness anymore.";
                if (formIndex <= 3) return "Skeletons have better conversation down here.";
                if (formIndex <= 5) return "A spider that reads the room.";
                if (formIndex == 6) return "Too good for the Wildy. Too sharp for you.";
                return "Junior chaos. Senior judgement.";
            case 2:
            case 3:
                switch (formIndex)
                {
                    case 0: return "Grew out of the whole 'maul things' phase.";
                    case 1: return "Finds the Wildy predictable. Which is worse.";
                    case 2: return "A scorpion with better things to do.";
                    case 3: return "Too many bones up north. Not worth it.";
                    default: return "Smaller. Smarter. Spider.";
                }
            case 4:
                return "Still waiting for somebody to out claw him.";
            case 5:
                return "Still chaotic. Just tastefully so.";
            default:
                switch (formIndex)
                {
                    case 0: return "Spreading small inconveniences.";
                    case 1: return "Opted out of the whole 'maul or be mauled' life.";
                    case 2: return "Still venomous in theory. Lazy in practice.";
                    case 3: return "Would rather pinch pockets than players.";
                    default: return "A skeleton that prefers the scenic route.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 10;
            case 2: return 6;
            case 3: return 6;
            case 4: return 1;
            case 5: return 2;
            default: return 12;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_NE_DITCH;
            case 2: return ZONE2_NORTH_GE_WALL;
            case 3: return ZONE3_NORTH_EDGEVILLE;
            case 4: return ZONE4_WEST_DITCH;
            case 5: return ZONE5_CHAOS_TEMPLE;
            default: return ZONE6_EDGEVILLE;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 6) return FORBIDDEN_ZONE6;
        return new int[0][][];
    }

    // Zone 1 - N/E Ditch (No Wildy)
    private static final int[][] ZONE1_NE_DITCH = {
            { 3189, 3521 }, { 3201, 3509 }, { 3229, 3509 }, { 3235, 3503 },
            { 3252, 3503 }, { 3253, 3496 }, { 3255, 3494 }, { 3274, 3494 },
            { 3274, 3512 }, { 3291, 3512 }, { 3297, 3518 }, { 3327, 3518 },
            { 3327, 3521 }, { 3264, 3521 }, { 3224, 3521 }, { 3189, 3521 }
    };

    // Zone 2 - North GE Wall (No Wildy)
    private static final int[][] ZONE2_NORTH_GE_WALL = {
            { 3189, 3521 }, { 3190, 3518 }, { 3170, 3518 }, { 3167, 3515 },
            { 3162, 3515 }, { 3159, 3518 }, { 3142, 3518 }, { 3137, 3514 },
            { 3134, 3516 }, { 3133, 3521 }, { 3157, 3521 }, { 3189, 3521 }
    };

    // Zone 3 - North Edgeville (No Wildy)
    private static final int[][] ZONE3_NORTH_EDGEVILLE = {
            { 3071, 3522 }, { 3067, 3520 }, { 3072, 3520 }, { 3072, 3517 },
            { 3076, 3514 }, { 3084, 3514 }, { 3091, 3514 }, { 3107, 3514 },
            { 3107, 3519 }, { 3112, 3519 }, { 3112, 3516 }, { 3118, 3516 },
            { 3130, 3516 }, { 3130, 3520 }, { 3124, 3522 }, { 3120, 3521 },
            { 3074, 3521 }, { 3071, 3522 }
    };

    // Zone 4 - West Ditch Section (No Wildy)
    private static final int[][] ZONE4_WEST_DITCH = {
            { 2964, 3521 }, { 2977, 3521 }, { 2979, 3520 }, { 2986, 3520 },
            { 2989, 3521 }, { 2991, 3521 }, { 2992, 3518 }, { 2989, 3516 },
            { 2986, 3514 }, { 2983, 3514 }, { 2977, 3514 }, { 2973, 3516 },
            { 2971, 3516 }, { 2969, 3519 }, { 2964, 3521 }
    };

    // Zone 5 - Chaos Temple (No Wildy)
    private static final int[][] ZONE5_CHAOS_TEMPLE = {
            { 2947, 3521 }, { 2942, 3520 }, { 2940, 3518 }, { 2940, 3516 },
            { 2937, 3514 }, { 2936, 3511 }, { 2938, 3511 }, { 2940, 3509 },
            { 2941, 3509 }, { 2943, 3512 }, { 2943, 3514 }, { 2944, 3516 },
            { 2947, 3519 }, { 2949, 3520 }, { 2949, 3521 }, { 2947, 3521 }
    };

    // Zone 6 - Edgeville City
    private static final int[][] ZONE6_EDGEVILLE = {
            { 3071, 3513 }, { 3072, 3515 }, { 3072, 3519 }, { 3075, 3517 },
            { 3076, 3514 }, { 3076, 3506 }, { 3084, 3506 }, { 3086, 3511 },
            { 3085, 3517 }, { 3090, 3517 }, { 3090, 3506 }, { 3102, 3507 },
            { 3102, 3516 }, { 3106, 3516 }, { 3106, 3509 }, { 3111, 3509 },
            { 3113, 3510 }, { 3116, 3514 }, { 3119, 3516 }, { 3122, 3516 },
            { 3125, 3515 }, { 3126, 3513 }, { 3126, 3510 }, { 3126, 3507 },
            { 3125, 3505 }, { 3124, 3504 }, { 3122, 3501 }, { 3119, 3500 },
            { 3118, 3498 }, { 3116, 3497 }, { 3112, 3494 }, { 3110, 3493 },
            { 3108, 3492 }, { 3106, 3490 }, { 3105, 3487 }, { 3103, 3485 },
            { 3101, 3481 }, { 3100, 3478 }, { 3100, 3464 }, { 3082, 3464 },
            { 3079, 3467 }, { 3079, 3478 }, { 3077, 3480 }, { 3077, 3486 },
            { 3084, 3486 }, { 3083, 3498 }, { 3082, 3502 }, { 3077, 3502 },
            { 3076, 3476 }, { 3071, 3480 }, { 3071, 3513 }
    };

    // Zone 6 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE6 = {
            // Bank
            {
                    { 3090, 3500 }, { 3099, 3500 }, { 3099, 3488 }, { 3090, 3488 },
                    { 3090, 3500 }
            }
    };
}

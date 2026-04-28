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


public class RimmingtonArea extends RoamingArea
{
    private final int zone;

    private RimmingtonArea(int zone) { this.zone = zone; }

    public static RimmingtonArea zone1() { return new RimmingtonArea(1); }
    public static RimmingtonArea zone2() { return new RimmingtonArea(2); }
    public static RimmingtonArea zone3() { return new RimmingtonArea(3); }
    public static RimmingtonArea zone4() { return new RimmingtonArea(4); }
    public static RimmingtonArea zone5() { return new RimmingtonArea(5); }
    public static RimmingtonArea zone6() { return new RimmingtonArea(6); }
    public static RimmingtonArea zone7() { return new RimmingtonArea(7); }
    public static RimmingtonArea zone8() { return new RimmingtonArea(8); }
    public static RimmingtonArea zone9() { return new RimmingtonArea(9); }
    public static RimmingtonArea zone10() { return new RimmingtonArea(10); }
    public static RimmingtonArea zone11() { return new RimmingtonArea(11); }

    @Override
    public String getAreaId() { return "rimmington_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 2: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 3:
            case 4: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 5: return new int[]{
                    NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                    NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                    NpcID.TANGLEROOT_9496, NpcID.TANGLEROOT_9497, NpcID.TANGLEROOT_9498,
                    NpcID.TANGLEROOT_9499, NpcID.TANGLEROOT_9500, NpcID.TANGLEROOT_9501
            };
            case 6: return new int[]{ NpcID.BEEF };
            case 7: return new int[]{ NpcID.SOUP, NpcID.BEAVER };
            case 8: return new int[]{ NpcID.FOX_12548 };
            case 9: return new int[]{
                    NpcID.RIFT_GUARDIAN,
                    NpcID.RIFT_GUARDIAN_7338, NpcID.RIFT_GUARDIAN_7339, NpcID.RIFT_GUARDIAN_7340,
                    NpcID.RIFT_GUARDIAN_7341, NpcID.RIFT_GUARDIAN_7342, NpcID.RIFT_GUARDIAN_7343,
                    NpcID.RIFT_GUARDIAN_7344, NpcID.RIFT_GUARDIAN_7345, NpcID.RIFT_GUARDIAN_7346,
                    NpcID.RIFT_GUARDIAN_7347, NpcID.RIFT_GUARDIAN_7348, NpcID.RIFT_GUARDIAN_7349,
                    NpcID.RIFT_GUARDIAN_7350, NpcID.RIFT_GUARDIAN_8024
            };
            case 10: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.FOX_12548, NpcID.BEAVER, NpcID.ROCKY };
            default: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.FOX_12548, NpcID.BEAVER, NpcID.ROCKY };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isAquatic() { return zone == 7; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
            case 2:
            case 5:
                return spawnIndex % nForms;
            case 3:
                // 4 Giant Squirrel(0), 4 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 8) return 1;
                return 2;
            case 4:
                // 2 Giant Squirrel(0), 4 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 6) return 1;
                return 2;
            case 7:
                // 2 Soup(0), 1 Beaver(1)
                return spawnIndex < 2 ? 0 : 1;
            case 10:
                // 6 Giant Squirrel(0), 2 Fox(1), 2 Beaver(2), 2 Rocky(3)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 8) return 1;
                if (spawnIndex < 10) return 2;
                return 3;
            default:
                // Zone 11: 6 Giant Squirrel(0), 2 Fox(1), 2 Beaver(2), 2 Rocky(3)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 8) return 1;
                if (spawnIndex < 10) return 2;
                return 3;
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
            case 2:
                Arrays.fill(names, "Rock Golem");
                break;
            case 3:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 8) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 4:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 5:
                Arrays.fill(names, "Tangleroot");
                break;
            case 6:
                Arrays.fill(names, "Beef");
                break;
            case 7:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Soup" : "Beaver";
                break;
            case 8:
                Arrays.fill(names, "Fox");
                break;
            case 9:
                Arrays.fill(names, "Rift Guardian");
                break;
            case 10:
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 8) names[i] = "Fox";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
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
            case 2:
                return "<col=ffff00>Rock Golem</col>";
            case 3:
            case 4:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 5:
                return "<col=ffff00>Tangleroot</col>";
            case 6:
                return "<col=ffff00>Beef</col>";
            case 7:
                return formIndex == 0
                        ? "<col=ffff00>Soup</col>"
                        : "<col=ffff00>Beaver</col>";
            case 8:
                return "<col=ffff00>Fox</col>";
            case 9:
                return "<col=ffff00>Rift Guardian</col>";
            case 10:
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Fox</col>";
                    case 2: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Indistinguishable from the local geology.";
            case 2: return "Awaits the day someone tries to gem-cut him.";
            case 3:
                switch (formIndex)
                {
                    case 0: return "Scoping out the local trees.";
                    case 1: return "Sizing up the local timber.";
                    default: return "Slips between unfamiliar things unseen.";
                }
            case 4:
                switch (formIndex)
                {
                    case 0: return "A blur of tail and intent.";
                    case 1: return "Has opinions on the local carpentry.";
                    default: return "Quietly auditing the area.";
                }
            case 5: return "Conferring with the local weeds.";
            case 6: return "Has noticed how everyone keeps looking at his hide.";
            case 7:
                return formIndex == 0
                        ? "Stuck between the menu and the food chain."
                        : "Wedged between dinner and being dinner.";
            case 8: return "Pretends not to know which house is yours.";
            case 9: return "Comfortable in the gusts.";
            case 10:
                switch (formIndex)
                {
                    case 0: return "Burying things he'll never find again.";
                    case 1: return "Slips through the brush like a rumour.";
                    case 2: return "Considers the nearby water under-performing.";
                    default: return "Has a reputation in three towns.";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "Counts the acorns. Recounts the acorns.";
                    case 1: return "Leaves footprints in clever patterns.";
                    case 2: return "Surveys, measures, judges, repeats.";
                    default: return "A connoisseur of the unattended.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 8;
            case 2: return 1;
            case 3: return 10;
            case 4: return 8;
            case 5: return 3;
            case 6: return 6;
            case 7: return 3;
            case 8: return 6;
            case 9: return 8;
            case 10: return 12;
            default: return 12;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset()
    {
        if (isAquatic()) return -20;
        return 10;
    }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_MINING_SPOT;
            case 2: return ZONE2_CRAFT_GUILD_MINING;
            case 3: return ZONE3_SE_WOODS;
            case 4: return ZONE4_WEST_WOODS;
            case 5: return ZONE5_FARMING_SPOT;
            case 6: return ZONE6_CRAFT_GUILD_COWS;
            case 7: return ZONE7_WATER_INLET;
            case 8: return ZONE8_POH_ZONE;
            case 9: return ZONE9_AIR_ALTAR;
            case 10: return ZONE10_NORTH_OF_GUILD;
            default: return ZONE11_EAST_OF_GUILD;
        }
    }

    // Zone 1 - Mining Spot
    private static final int[][] ZONE1_MINING_SPOT = {
            { 2975, 3252 }, { 2979, 3252 }, { 2983, 3252 }, { 2987, 3248 },
            { 2989, 3245 }, { 2990, 3239 }, { 2989, 3235 }, { 2986, 3231 },
            { 2983, 3228 }, { 2978, 3228 }, { 2973, 3230 }, { 2969, 3233 },
            { 2966, 3237 }, { 2964, 3241 }, { 2967, 3245 }, { 2970, 3247 },
            { 2971, 3250 }, { 2975, 3252 }
    };

    // Zone 2 - Crafting Guild (Mining Spot)
    private static final int[][] ZONE2_CRAFT_GUILD_MINING = {
            { 2939, 3282 }, { 2939, 3277 }, { 2943, 3277 }, { 2943, 3291 },
            { 2939, 3291 }, { 2938, 3283 }, { 2939, 3282 }
    };

    // Zone 3 - S/E Woods
    private static final int[][] ZONE3_SE_WOODS = {
            { 2981, 3225 }, { 2968, 3225 }, { 2962, 3218 }, { 2972, 3218 },
            { 2972, 3203 }, { 2972, 3202 }, { 2944, 3201 }, { 2941, 3196 },
            { 2954, 3193 }, { 2967, 3193 }, { 2969, 3190 }, { 2979, 3196 },
            { 2985, 3197 }, { 2988, 3222 }, { 2981, 3225 }
    };

    // Zone 4 - West Woods
    private static final int[][] ZONE4_WEST_WOODS = {
            { 2941, 3234 }, { 2921, 3238 }, { 2918, 3234 }, { 2920, 3231 },
            { 2921, 3228 }, { 2923, 3226 }, { 2924, 3222 }, { 2926, 3218 },
            { 2927, 3217 }, { 2934, 3219 }, { 2937, 3225 }, { 2942, 3231 },
            { 2941, 3234 }
    };

    // Zone 5 - Farming Spot
    private static final int[][] ZONE5_FARMING_SPOT = {
            { 2938, 3226 }, { 2943, 3226 }, { 2945, 3222 }, { 2944, 3218 },
            { 2937, 3217 }, { 2936, 3223 }, { 2938, 3226 }
    };

    // Zone 6 - Crafting Guild (Cows)
    private static final int[][] ZONE6_CRAFT_GUILD_COWS = {
            { 2916, 3291 }, { 2926, 3291 }, { 2927, 3285 }, { 2928, 3284 },
            { 2928, 3281 }, { 2926, 3279 }, { 2926, 3275 }, { 2927, 3273 },
            { 2931, 3273 }, { 2933, 3278 }, { 2935, 3278 }, { 2938, 3275 },
            { 2937, 3273 }, { 2935, 3270 }, { 2931, 3268 }, { 2928, 3268 },
            { 2926, 3270 }, { 2924, 3270 }, { 2923, 3272 }, { 2922, 3276 },
            { 2922, 3281 }, { 2920, 3284 }, { 2917, 3287 }, { 2914, 3289 },
            { 2916, 3291 }
    };

    // Zone 7 - Water Inlet (Hobgoblins)
    private static final int[][] ZONE7_WATER_INLET = {
            { 2908, 3300 }, { 2909, 3298 }, { 2910, 3296 }, { 2911, 3294 },
            { 2912, 3293 }, { 2911, 3291 }, { 2911, 3289 }, { 2911, 3288 },
            { 2913, 3285 }, { 2913, 3281 }, { 2916, 3276 }, { 2918, 3275 },
            { 2921, 3276 }, { 2921, 3280 }, { 2920, 3282 }, { 2918, 3283 },
            { 2917, 3285 }, { 2916, 3286 }, { 2914, 3287 }, { 2913, 3288 },
            { 2913, 3288 }, { 2912, 3289 }, { 2913, 3292 }, { 2913, 3293 },
            { 2913, 3294 }, { 2912, 3296 }, { 2911, 3298 }, { 2908, 3301 },
            { 2907, 3301 }, { 2908, 3300 }
    };

    // Zone 8 - PoH Zone
    private static final int[][] ZONE8_POH_ZONE = {
            { 2949, 3228 }, { 2953, 3229 }, { 2956, 3227 }, { 2957, 3224 },
            { 2957, 3221 }, { 2953, 3219 }, { 2953, 3218 }, { 2949, 3220 },
            { 2947, 3222 }, { 2947, 3227 }, { 2949, 3228 }
    };

    // Zone 9 - Air Altar Woods
    private static final int[][] ZONE9_AIR_ALTAR = {
            { 2980, 3302 }, { 2972, 3298 }, { 2974, 3290 }, { 2976, 3286 },
            { 2987, 3284 }, { 2997, 3289 }, { 2994, 3299 }, { 2986, 3303 },
            { 2980, 3302 }
    };

    // Zone 10 - North of Crafting Guild
    private static final int[][] ZONE10_NORTH_OF_GUILD = {
            { 2910, 3315 }, { 2920, 3316 }, { 2921, 3320 }, { 2925, 3323 },
            { 2929, 3318 }, { 2932, 3317 }, { 2937, 3311 }, { 2941, 3308 },
            { 2954, 3308 }, { 2959, 3306 }, { 2966, 3303 }, { 2967, 3293 },
            { 2964, 3288 }, { 2962, 3284 }, { 2955, 3283 }, { 2947, 3290 },
            { 2944, 3295 }, { 2936, 3295 }, { 2927, 3295 }, { 2917, 3296 },
            { 2913, 3299 }, { 2909, 3304 }, { 2910, 3315 }
    };

    // Zone 11 - East of Crafting Guild
    private static final int[][] ZONE11_EAST_OF_GUILD = {
            { 2946, 3290 }, { 2946, 3275 }, { 2929, 3264 }, { 2928, 3260 },
            { 2940, 3259 }, { 2948, 3265 }, { 2960, 3263 }, { 2961, 3258 },
            { 2971, 3261 }, { 2977, 3275 }, { 2975, 3279 }, { 2962, 3287 },
            { 2946, 3290 }
    };
}
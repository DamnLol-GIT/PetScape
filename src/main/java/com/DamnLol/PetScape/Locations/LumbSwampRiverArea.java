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


public class LumbSwampRiverArea extends RoamingArea
{
    private final int zone;

    private LumbSwampRiverArea(int zone) { this.zone = zone; }

    public static LumbSwampRiverArea zone1() { return new LumbSwampRiverArea(1); }
    public static LumbSwampRiverArea zone2() { return new LumbSwampRiverArea(2); }
    public static LumbSwampRiverArea zone3() { return new LumbSwampRiverArea(3); }
    public static LumbSwampRiverArea zone4() { return new LumbSwampRiverArea(4); }
    public static LumbSwampRiverArea zone5() { return new LumbSwampRiverArea(5); }
    public static LumbSwampRiverArea zone6() { return new LumbSwampRiverArea(6); }
    public static LumbSwampRiverArea zone7() { return new LumbSwampRiverArea(7); }
    public static LumbSwampRiverArea zone8() { return new LumbSwampRiverArea(8); }
    public static LumbSwampRiverArea zone9() { return new LumbSwampRiverArea(9); }
    public static LumbSwampRiverArea zone10() { return new LumbSwampRiverArea(10); }
    public static LumbSwampRiverArea zone11() { return new LumbSwampRiverArea(11); }
    public static LumbSwampRiverArea zone12() { return new LumbSwampRiverArea(12); }
    public static LumbSwampRiverArea zone13() { return new LumbSwampRiverArea(13); }
    public static LumbSwampRiverArea zone14() { return new LumbSwampRiverArea(14); }
    public static LumbSwampRiverArea zone15() { return new LumbSwampRiverArea(15); }

    @Override
    public String getAreaId() { return "lumb_swamp_river_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 2: return new int[]{ NpcID.HERBI, NpcID.GIANT_SQUIRREL };
            case 3:
            case 4: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 5: return new int[]{ NpcID.BEAVER };
            case 6:
            case 8: return new int[]{ NpcID.GREAT_BLUE_HERON };
            case 7:
            case 9:
            case 11:
            case 13: return new int[]{ NpcID.SOUP };
            case 10:
            case 12:
            case 14: return new int[]{ NpcID.GREAT_BLUE_HERON, NpcID.HERON };
            default: return new int[]{ NpcID.SOUP };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying()
    {
        return zone == 6 || zone == 8 || zone == 10 || zone == 12 || zone == 14;
    }

    @Override
    public boolean isAquatic()
    {
        return zone == 7 || zone == 9 || zone == 11 || zone == 13 || zone == 15;
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 6 Herbi(0), 6 Giant Squirrel(1)
                return spawnIndex < 6 ? 0 : 1;
            case 2:
                // 4 Herbi(0), 4 Giant Squirrel(1)
                return spawnIndex < 4 ? 0 : 1;
            case 3:
            case 4:
                return spawnIndex % nForms;
            case 10:
                // 4 Blue Heron(0), 4 Heron(1)
                return spawnIndex < 4 ? 0 : 1;
            case 12:
            case 14:
                // 2 Blue Heron(0), 2 Heron(1)
                return spawnIndex < 2 ? 0 : 1;
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
                    names[i] = i < 6 ? "Herbi" : "Giant Squirrel";
                break;
            case 2:
                for (int i = 0; i < n; i++)
                    names[i] = i < 4 ? "Herbi" : "Giant Squirrel";
                break;
            case 3:
            case 4:
                Arrays.fill(names, "Rock Golem");
                break;
            case 5:
                Arrays.fill(names, "Beaver");
                break;
            case 6:
            case 8:
                Arrays.fill(names, "Great Blue Heron");
                break;
            case 7:
            case 9:
            case 11:
            case 13:
                Arrays.fill(names, "Soup");
                break;
            case 10:
                for (int i = 0; i < n; i++)
                    names[i] = i < 4 ? "Great Blue Heron" : "Heron";
                break;
            case 12:
            case 14:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Great Blue Heron" : "Heron";
                break;
            default:
                Arrays.fill(names, "Soup");
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
                return formIndex == 0
                        ? "<col=ffff00>Herbi</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
            case 3:
            case 4:
                return "<col=ffff00>Rock Golem</col>";
            case 5:
                return "<col=ffff00>Beaver</col>";
            case 6:
            case 8:
                return "<col=ffff00>Great Blue Heron</col>";
            case 7:
            case 9:
            case 11:
            case 13:
                return "<col=ffff00>Soup</col>";
            case 10:
            case 12:
            case 14:
                return formIndex == 0
                        ? "<col=ffff00>Great Blue Heron</col>"
                        : "<col=ffff00>Heron</col>";
            default:
                return "<col=ffff00>Soup</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
            case 2:
                return formIndex == 0
                        ? "Mushroom first, manners later."
                        : "The only thing moving fast in this swamp.";
            case 3:
            case 4:
                return "Takes his job for granite.";
            case 5:
                return "Eyeing the bank with construction plans.";
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                return formIndex == 0
                        ? "The river's most successful loiterer."
                        : "Already caught more than anyone watching.";
            case 7:
            case 9:
            case 11:
            case 13:
            default:
                return "Paddling along. Unaware of the menu.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 12;
            case 2: return 8;
            case 3: return 4;
            case 4: return 4;
            case 5: return 3;
            case 6: return 4;
            case 7: return 4;
            case 8: return 2;
            case 9: return 2;
            case 10: return 8;
            case 11: return 8;
            case 12: return 4;
            case 13: return 3;
            case 14: return 4;
            default: return 3;
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
            case 1: return ZONE1_SWAMP_WEST;
            case 2: return ZONE2_SWAMP_EAST;
            case 3: return ZONE3_WEST_MINING;
            case 4: return ZONE4_EAST_MINING;
            case 5: return ZONE5_RIVER_EDGE;
            case 6:
            case 7: return ZONE6_RIVER_LUM_SOUTH;
            case 8:
            case 9: return ZONE7_RIVER_LUM_GAP;
            case 10:
            case 11: return ZONE8_RIVER_LUM_MIDDLE;
            case 12:
            case 13: return ZONE9_RIVER_LUM_MID;
            case 14: return ZONE10_RIVER_LUM_NORTH;
            default: return ZONE10_RIVER_LUM_NORTH;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 13) return FORBIDDEN_ZONE13;
        return new int[0][][];
    }

    // Zone 1 - Lumbridge Swamp West
    private static final int[][] ZONE1_SWAMP_WEST = {
            { 3161, 3206 }, { 3158, 3208 }, { 3155, 3209 }, { 3150, 3209 },
            { 3147, 3207 }, { 3143, 3206 }, { 3138, 3190 }, { 3138, 3188 },
            { 3139, 3183 }, { 3141, 3181 }, { 3141, 3178 }, { 3152, 3178 },
            { 3152, 3173 }, { 3143, 3173 }, { 3144, 3167 }, { 3146, 3164 },
            { 3150, 3158 }, { 3157, 3155 }, { 3159, 3153 }, { 3167, 3151 },
            { 3169, 3150 }, { 3173, 3149 }, { 3176, 3148 }, { 3179, 3147 },
            { 3182, 3146 }, { 3187, 3145 }, { 3189, 3145 }, { 3193, 3148 },
            { 3198, 3156 }, { 3199, 3160 }, { 3202, 3166 }, { 3202, 3173 },
            { 3202, 3175 }, { 3202, 3177 }, { 3201, 3180 }, { 3201, 3185 },
            { 3203, 3188 }, { 3204, 3190 }, { 3204, 3192 }, { 3204, 3195 },
            { 3204, 3197 }, { 3203, 3198 }, { 3200, 3198 }, { 3199, 3196 },
            { 3196, 3196 }, { 3194, 3196 }, { 3192, 3198 }, { 3191, 3199 },
            { 3189, 3200 }, { 3184, 3204 }, { 3182, 3205 }, { 3179, 3206 },
            { 3166, 3206 }, { 3161, 3206 }
    };

    // Zone 2 - Lumbridge Swamp East
    private static final int[][] ZONE2_SWAMP_EAST = {
            { 3206, 3197 }, { 3206, 3196 }, { 3204, 3194 }, { 3204, 3191 },
            { 3201, 3187 }, { 3200, 3184 }, { 3200, 3181 }, { 3201, 3177 },
            { 3202, 3174 }, { 3203, 3172 }, { 3205, 3171 }, { 3206, 3171 },
            { 3206, 3167 }, { 3205, 3166 }, { 3204, 3166 }, { 3203, 3164 },
            { 3203, 3161 }, { 3203, 3158 }, { 3202, 3155 }, { 3202, 3152 },
            { 3211, 3146 }, { 3216, 3146 }, { 3219, 3150 }, { 3222, 3154 },
            { 3225, 3154 }, { 3229, 3154 }, { 3232, 3155 }, { 3233, 3156 },
            { 3238, 3157 }, { 3240, 3160 }, { 3240, 3166 }, { 3242, 3169 },
            { 3243, 3176 }, { 3243, 3178 }, { 3244, 3183 }, { 3244, 3185 },
            { 3241, 3186 }, { 3239, 3188 }, { 3233, 3190 }, { 3232, 3191 },
            { 3228, 3195 }, { 3220, 3198 }, { 3206, 3197 }
    };

    // Zone 3 - West Mining Spot
    private static final int[][] ZONE3_WEST_MINING = {
            { 3143, 3158 }, { 3146, 3158 }, { 3149, 3157 }, { 3151, 3154 },
            { 3153, 3151 }, { 3154, 3149 }, { 3154, 3147 }, { 3154, 3144 },
            { 3153, 3143 }, { 3151, 3141 }, { 3148, 3140 }, { 3145, 3139 },
            { 3143, 3139 }, { 3140, 3141 }, { 3139, 3144 }, { 3139, 3146 },
            { 3141, 3148 }, { 3141, 3150 }, { 3139, 3151 }, { 3140, 3153 },
            { 3143, 3155 }, { 3143, 3158 }
    };

    // Zone 4 - East Mining Spot
    private static final int[][] ZONE4_EAST_MINING = {
            { 3222, 3153 }, { 3226, 3154 }, { 3229, 3156 }, { 3233, 3156 },
            { 3237, 3157 }, { 3238, 3155 }, { 3239, 3152 }, { 3239, 3150 },
            { 3237, 3147 }, { 3235, 3145 }, { 3234, 3143 }, { 3232, 3142 },
            { 3227, 3142 }, { 3220, 3142 }, { 3217, 3146 }, { 3218, 3150 },
            { 3222, 3153 }
    };

    // Zone 5 - Swamp River Edge
    private static final int[][] ZONE5_RIVER_EDGE = {
            { 3248, 3187 }, { 3245, 3186 }, { 3243, 3180 }, { 3242, 3172 },
            { 3241, 3161 }, { 3237, 3152 }, { 3238, 3148 }, { 3241, 3150 },
            { 3242, 3154 }, { 3244, 3161 }, { 3245, 3165 }, { 3245, 3171 },
            { 3246, 3177 }, { 3248, 3187 }
    };

    // Zones 6/7 - River Lum South (swamp-bridge)
    private static final int[][] ZONE6_RIVER_LUM_SOUTH = {
            { 3241, 3142 }, { 3270, 3145 }, { 3265, 3148 }, { 3263, 3151 },
            { 3260, 3153 }, { 3258, 3157 }, { 3256, 3160 }, { 3255, 3164 },
            { 3255, 3166 }, { 3254, 3169 }, { 3254, 3172 }, { 3254, 3176 },
            { 3256, 3179 }, { 3257, 3182 }, { 3258, 3185 }, { 3260, 3188 },
            { 3262, 3193 }, { 3262, 3211 }, { 3261, 3215 }, { 3259, 3217 },
            { 3256, 3219 }, { 3253, 3222 }, { 3249, 3222 }, { 3246, 3222 },
            { 3248, 3220 }, { 3251, 3219 }, { 3253, 3217 }, { 3256, 3214 },
            { 3258, 3211 }, { 3259, 3206 }, { 3259, 3203 }, { 3259, 3199 },
            { 3258, 3195 }, { 3256, 3189 }, { 3254, 3187 }, { 3253, 3183 },
            { 3250, 3181 }, { 3250, 3177 }, { 3249, 3174 }, { 3248, 3170 },
            { 3248, 3165 }, { 3249, 3159 }, { 3247, 3157 }, { 3246, 3151 },
            { 3242, 3147 }, { 3241, 3142 }
    };

    // Zones 8/9 - River Lum Gap (bridge-bridge)
    private static final int[][] ZONE7_RIVER_LUM_GAP = {
            { 3243, 3228 }, { 3246, 3228 }, { 3245, 3231 }, { 3243, 3233 },
            { 3241, 3235 }, { 3240, 3237 }, { 3239, 3239 }, { 3239, 3245 },
            { 3240, 3247 }, { 3238, 3251 }, { 3238, 3258 }, { 3236, 3260 },
            { 3234, 3260 }, { 3235, 3257 }, { 3236, 3255 }, { 3236, 3252 },
            { 3235, 3250 }, { 3237, 3247 }, { 3237, 3244 }, { 3237, 3241 },
            { 3237, 3239 }, { 3238, 3235 }, { 3240, 3231 }, { 3243, 3228 }
    };

    // Zones 10/11 - River Lum Middle (bridge-island)
    private static final int[][] ZONE8_RIVER_LUM_MIDDLE = {
            { 3233, 3263 }, { 3233, 3266 }, { 3232, 3270 }, { 3230, 3272 },
            { 3229, 3273 }, { 3226, 3277 }, { 3224, 3278 }, { 3221, 3281 },
            { 3219, 3282 }, { 3217, 3285 }, { 3217, 3286 }, { 3217, 3288 },
            { 3216, 3292 }, { 3216, 3295 }, { 3215, 3296 }, { 3214, 3300 },
            { 3214, 3308 }, { 3213, 3311 }, { 3214, 3314 }, { 3213, 3317 },
            { 3212, 3319 }, { 3209, 3323 }, { 3206, 3325 }, { 3206, 3326 },
            { 3205, 3327 }, { 3204, 3329 }, { 3204, 3332 }, { 3200, 3337 },
            { 3198, 3338 }, { 3192, 3341 }, { 3189, 3342 }, { 3185, 3345 },
            { 3182, 3347 }, { 3182, 3348 }, { 3184, 3348 }, { 3188, 3347 },
            { 3190, 3346 }, { 3193, 3345 }, { 3197, 3343 }, { 3199, 3342 },
            { 3202, 3341 }, { 3204, 3340 }, { 3207, 3336 }, { 3208, 3333 },
            { 3208, 3327 }, { 3210, 3325 }, { 3213, 3322 }, { 3215, 3321 },
            { 3217, 3317 }, { 3217, 3314 }, { 3217, 3309 }, { 3217, 3307 },
            { 3218, 3305 }, { 3218, 3303 }, { 3218, 3301 }, { 3219, 3297 },
            { 3220, 3294 }, { 3221, 3290 }, { 3221, 3286 }, { 3223, 3283 },
            { 3224, 3282 }, { 3225, 3281 }, { 3227, 3279 }, { 3229, 3277 },
            { 3231, 3274 }, { 3234, 3271 }, { 3235, 3268 }, { 3237, 3263 },
            { 3233, 3263 }
    };

    // Zones 12/13 - River Lum Mid (island-bridge)
    private static final int[][] ZONE9_RIVER_LUM_MID = {
            { 3180, 3347 }, { 3183, 3348 }, { 3181, 3350 }, { 3180, 3352 },
            { 3178, 3354 }, { 3177, 3356 }, { 3174, 3357 }, { 3169, 3357 },
            { 3166, 3355 }, { 3163, 3353 }, { 3161, 3352 }, { 3158, 3355 },
            { 3155, 3357 }, { 3153, 3363 }, { 3153, 3369 }, { 3153, 3371 },
            { 3152, 3374 }, { 3151, 3376 }, { 3150, 3378 }, { 3149, 3381 },
            { 3149, 3383 }, { 3148, 3384 }, { 3144, 3387 }, { 3142, 3389 },
            { 3140, 3390 }, { 3133, 3390 }, { 3131, 3389 }, { 3127, 3390 },
            { 3124, 3390 }, { 3120, 3392 }, { 3118, 3395 }, { 3112, 3401 },
            { 3110, 3413 }, { 3109, 3415 }, { 3108, 3417 }, { 3106, 3419 },
            { 3105, 3418 }, { 3105, 3416 }, { 3106, 3414 }, { 3106, 3413 },
            { 3108, 3412 }, { 3109, 3411 }, { 3110, 3409 }, { 3109, 3408 },
            { 3109, 3406 }, { 3110, 3404 }, { 3109, 3403 }, { 3110, 3400 },
            { 3110, 3398 }, { 3115, 3393 }, { 3118, 3390 }, { 3119, 3388 },
            { 3131, 3388 }, { 3135, 3387 }, { 3140, 3385 }, { 3144, 3381 },
            { 3146, 3377 }, { 3147, 3373 }, { 3149, 3367 }, { 3151, 3358 },
            { 3152, 3355 }, { 3156, 3350 }, { 3160, 3349 }, { 3165, 3347 },
            { 3167, 3345 }, { 3171, 3345 }, { 3174, 3345 }, { 3178, 3347 },
            { 3180, 3347 }
    };

    // Zone 13 forbidden Zone
    private static final int[][][] FORBIDDEN_ZONE13 = {
            // Island
            {
                    { 3170, 3354 }, { 3172, 3354 }, { 3173, 3354 }, { 3175, 3352 },
                    { 3175, 3349 }, { 3173, 3348 }, { 3170, 3348 }, { 3168, 3349 },
                    { 3167, 3350 }, { 3167, 3352 }, { 3170, 3354 }
            }
    };

    // Zones 14/15 - River Lum North (bridge-fairyring)
    private static final int[][] ZONE10_RIVER_LUM_NORTH = {
            { 3104, 3422 }, { 3106, 3422 }, { 3107, 3425 }, { 3110, 3427 },
            { 3111, 3428 }, { 3112, 3429 }, { 3114, 3433 }, { 3114, 3435 },
            { 3110, 3439 }, { 3109, 3440 }, { 3107, 3441 }, { 3106, 3443 },
            { 3105, 3444 }, { 3105, 3447 }, { 3104, 3450 }, { 3104, 3452 },
            { 3103, 3454 }, { 3103, 3456 }, { 3103, 3460 }, { 3103, 3468 },
            { 3104, 3472 }, { 3105, 3475 }, { 3107, 3480 }, { 3108, 3483 },
            { 3110, 3486 }, { 3111, 3489 }, { 3117, 3494 }, { 3123, 3498 },
            { 3125, 3500 }, { 3127, 3503 }, { 3127, 3504 }, { 3126, 3504 },
            { 3125, 3502 }, { 3124, 3501 }, { 3123, 3500 }, { 3122, 3499 },
            { 3121, 3498 }, { 3119, 3497 }, { 3117, 3495 }, { 3115, 3494 },
            { 3114, 3493 }, { 3110, 3491 }, { 3109, 3489 }, { 3108, 3487 },
            { 3106, 3485 }, { 3104, 3482 }, { 3103, 3478 }, { 3102, 3476 },
            { 3101, 3474 }, { 3102, 3473 }, { 3101, 3470 }, { 3102, 3469 },
            { 3101, 3467 }, { 3101, 3464 }, { 3102, 3462 }, { 3101, 3459 },
            { 3101, 3455 }, { 3102, 3453 }, { 3101, 3450 }, { 3102, 3448 },
            { 3102, 3447 }, { 3103, 3445 }, { 3103, 3442 }, { 3109, 3437 },
            { 3111, 3434 }, { 3109, 3430 }, { 3105, 3426 }, { 3104, 3423 },
            { 3104, 3422 }
    };
}

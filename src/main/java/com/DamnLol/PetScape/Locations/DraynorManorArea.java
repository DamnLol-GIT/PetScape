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


public class DraynorManorArea extends RoamingArea
{
    private final int zone;

    private DraynorManorArea(int zone) { this.zone = zone; }

    public static DraynorManorArea zone1() { return new DraynorManorArea(1); }
    public static DraynorManorArea zone2() { return new DraynorManorArea(2); }
    public static DraynorManorArea zone3() { return new DraynorManorArea(3); }
    public static DraynorManorArea zone4() { return new DraynorManorArea(4); }
    public static DraynorManorArea zone5() { return new DraynorManorArea(5); }
    public static DraynorManorArea zone6() { return new DraynorManorArea(6); }
    public static DraynorManorArea zone7() { return new DraynorManorArea(7); }
    public static DraynorManorArea zone8() { return new DraynorManorArea(8); }

    @Override
    public String getAreaId() { return "draynor_manor_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 2:
            case 4: return new int[]{ NpcID.BONE_SQUIRREL, NpcID.ROCKY };
            case 3: return new int[]{ NpcID.TANGLEROOT };
            case 5: return new int[]{ NpcID.BONE_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 6:
            case 7: return new int[]{ NpcID.BONE_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            default: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ZIGGY, NpcID.BEAVER };
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
            case 2:
                // 8 Bone Squirrel(0), 2 Rocky(1)
                return spawnIndex < 8 ? 0 : 1;
            case 4:
                // 2 Bone Squirrel(0), 1 Rocky(1)
                return spawnIndex < 2 ? 0 : 1;
            case 5:
                // 6 Bone Squirrel(0), 4 Rocky(1), 2 Beaver(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 6:
            case 7:
                // 3 Bone Squirrel(0), 1 Rocky(1), 1 Beaver(2)
                if (spawnIndex < 3) return 0;
                if (spawnIndex < 4) return 1;
                return 2;
            default:
                // Zone 8: 6 Giant Squirrel(0), 3 Ziggy(1), 2 Beaver(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 9) return 1;
                return 2;
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
                for (int i = 0; i < n; i++)
                    names[i] = i < 8 ? "Bone Squirrel" : "Rocky";
                break;
            case 3:
                Arrays.fill(names, "Tangleroot");
                break;
            case 4:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Bone Squirrel" : "Rocky";
                break;
            case 5:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Bone Squirrel";
                    else if (i < 10) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 6:
            case 7:
                for (int i = 0; i < n; i++)
                {
                    if (i < 3) names[i] = "Bone Squirrel";
                    else if (i < 4) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 9) names[i] = "Rocky";
                    else names[i] = "Beaver";
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
            case 4:
                return formIndex == 0
                        ? "<col=ffff00>Bone Squirrel</col>"
                        : "<col=ffff00>Rocky</col>";
            case 3:
                return "<col=ffff00>Tangleroot</col>";
            case 5:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bone Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 6:
            case 7:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bone Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Ziggy</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return formIndex == 0
                        ? "Outlasted the gardeners. And the gardens."
                        : "Casing the manor, Carefully.";

            case 2:
                return formIndex == 0
                        ? "The manor's liveliest resident. Still dead."
                        : "Pilfers quietly. Leaves the ghosts alone.";

            case 3:
                return "Discussing roots with a distant cousin.";
            case 4:
                return formIndex == 0
                        ? "Been here longer than the floorboards."
                        : "Posed dramatically beneath the back window.";
            case 5:
                switch (formIndex)
                {
                    case 0: return "Refuses to be buried. Again.";
                    case 1: return "Haunting the grounds, professionally.";
                    default: return "Every fencepost is a candidate.";
                }
            case 6:
                switch (formIndex)
                {
                    case 0: return "Creaks softly as he scampers.";
                    case 1: return "Nothing disappears here without his say-so.";
                    default: return "Patience, teeth, and a plan.";
                }
            case 7:
                switch (formIndex)
                {
                    case 0: return "Creaks softly as he scampers.";
                    case 1: return "Nothing disappears here without his say-so.";
                    default: return "Patience, teeth, and a plan.";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "A blur with a tail.";
                    case 1: return "Pretending not to be pretending.";
                    default: return "A quiet renovator with no permission.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 10;
            case 2: return 10;
            case 3: return 2;
            case 4: return 3;
            case 5: return 12;
            case 6: return 5;
            case 7: return 5;
            default: return 11;
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
            case 1: return ZONE1_FRONT_YARD;
            case 2: return ZONE2_BACK_YARD;
            case 3: return ZONE3_FARM_PATCH;
            case 4: return ZONE4_MANOR_F1;
            case 5: return ZONE5_FRONT_OUTER;
            case 6: return ZONE6_EDGE_WEST;
            case 7: return ZONE7_EDGE_EAST;
            default: return ZONE8_WEST_GRASS_PATH;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 4) return FORBIDDEN_ZONE4;
        return new int[0][][];
    }

    // Zone 1 - Manor Front Yard
    private static final int[][] ZONE1_FRONT_YARD = {
            { 3086, 3350 }, { 3090, 3353 }, { 3093, 3352 }, { 3096, 3353 },
            { 3098, 3352 }, { 3101, 3353 }, { 3116, 3353 }, { 3117, 3352 },
            { 3120, 3353 }, { 3121, 3352 }, { 3123, 3352 }, { 3125, 3353 },
            { 3128, 3353 }, { 3128, 3347 }, { 3127, 3346 }, { 3127, 3340 },
            { 3126, 3339 }, { 3126, 3334 }, { 3123, 3331 }, { 3119, 3331 },
            { 3118, 3330 }, { 3115, 3330 }, { 3114, 3331 }, { 3108, 3331 },
            { 3105, 3329 }, { 3103, 3329 }, { 3102, 3330 }, { 3099, 3330 },
            { 3097, 3332 }, { 3093, 3332 }, { 3092, 3331 }, { 3086, 3331 },
            { 3084, 3333 }, { 3084, 3337 }, { 3085, 3338 }, { 3085, 3340 },
            { 3084, 3341 }, { 3084, 3347 }, { 3086, 3349 }, { 3086, 3350 }
    };

    // Zone 2 - Manor Back/Side Yard
    private static final int[][] ZONE2_BACK_YARD = {
            { 3127, 3355 }, { 3129, 3355 }, { 3130, 3356 }, { 3130, 3364 },
            { 3126, 3367 }, { 3126, 3370 }, { 3127, 3372 }, { 3127, 3374 },
            { 3126, 3376 }, { 3117, 3385 }, { 3107, 3385 }, { 3103, 3386 },
            { 3101, 3387 }, { 3098, 3387 }, { 3097, 3388 }, { 3092, 3388 },
            { 3091, 3387 }, { 3088, 3387 }, { 3085, 3384 }, { 3085, 3379 },
            { 3083, 3377 }, { 3083, 3372 }, { 3085, 3370 }, { 3085, 3365 },
            { 3084, 3364 }, { 3084, 3359 }, { 3085, 3358 }, { 3090, 3357 },
            { 3090, 3365 }, { 3096, 3365 }, { 3096, 3375 }, { 3103, 3375 },
            { 3111, 3375 }, { 3112, 3376 }, { 3113, 3376 }, { 3115, 3375 },
            { 3120, 3375 }, { 3120, 3362 }, { 3127, 3362 }, { 3127, 3355 }
    };

    // Zone 3 - Manor Farm Patch
    private static final int[][] ZONE3_FARM_PATCH = {
            { 3084, 3364 }, { 3087, 3364 }, { 3090, 3361 }, { 3090, 3354 },
            { 3092, 3353 }, { 3092, 3350 }, { 3091, 3349 }, { 3089, 3348 },
            { 3088, 3348 }, { 3086, 3349 }, { 3086, 3353 }, { 3085, 3354 },
            { 3085, 3358 }, { 3084, 3359 }, { 3084, 3364 }
    };

    // Zone 4 - Manor F1
    private static final int[][] ZONE4_MANOR_F1 = {
            { 3097, 3354 }, { 3097, 3364 }, { 3102, 3364 }, { 3102, 3374 },
            { 3119, 3374 }, { 3119, 3361 }, { 3126, 3361 }, { 3127, 3354 },
            { 3097, 3354 }
    };

    // Zone 4 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE4 = {
            // Stairs (Down)
            {
                    { 3114, 3361 }, { 3118, 3361 }, { 3118, 3357 }, { 3114, 3357 },
                    { 3114, 3361 }
            }
    };

    // Zone 5 - Manor Front (Outer)
    private static final int[][] ZONE5_FRONT_OUTER = {
            { 3084, 3341 }, { 3081, 3340 }, { 3077, 3338 }, { 3073, 3333 },
            { 3071, 3330 }, { 3070, 3328 }, { 3071, 3325 }, { 3071, 3320 },
            { 3072, 3317 }, { 3074, 3315 }, { 3076, 3314 }, { 3078, 3313 },
            { 3081, 3313 }, { 3083, 3313 }, { 3091, 3314 }, { 3094, 3311 },
            { 3101, 3311 }, { 3105, 3313 }, { 3108, 3315 }, { 3111, 3313 },
            { 3121, 3313 }, { 3131, 3316 }, { 3133, 3318 }, { 3135, 3320 },
            { 3137, 3322 }, { 3140, 3324 }, { 3141, 3327 }, { 3143, 3330 },
            { 3143, 3333 }, { 3142, 3335 }, { 3138, 3337 }, { 3135, 3337 },
            { 3130, 3337 }, { 3128, 3337 }, { 3128, 3336 }, { 3128, 3335 },
            { 3127, 3333 }, { 3126, 3332 }, { 3125, 3330 }, { 3124, 3329 },
            { 3119, 3329 }, { 3117, 3329 }, { 3115, 3329 }, { 3113, 3329 },
            { 3111, 3328 }, { 3108, 3328 }, { 3102, 3327 }, { 3101, 3328 },
            { 3094, 3330 }, { 3092, 3329 }, { 3090, 3329 }, { 3086, 3329 },
            { 3084, 3329 }, { 3083, 3332 }, { 3083, 3337 }, { 3083, 3337 },
            { 3083, 3339 }, { 3084, 3341 }
    };

    // Zone 6 - Manor Edge West (Outer)
    private static final int[][] ZONE6_EDGE_WEST = {
            { 3104, 3392 }, { 3101, 3393 }, { 3094, 3393 }, { 3086, 3391 },
            { 3078, 3387 }, { 3077, 3383 }, { 3075, 3379 }, { 3074, 3378 },
            { 3074, 3369 }, { 3076, 3366 }, { 3075, 3363 }, { 3074, 3361 },
            { 3074, 3359 }, { 3074, 3355 }, { 3076, 3352 }, { 3076, 3350 },
            { 3076, 3347 }, { 3073, 3342 }, { 3076, 3338 }, { 3078, 3337 },
            { 3080, 3337 }, { 3080, 3340 }, { 3083, 3346 }, { 3083, 3348 },
            { 3083, 3349 }, { 3084, 3352 }, { 3083, 3354 }, { 3082, 3357 },
            { 3081, 3360 }, { 3082, 3363 }, { 3082, 3365 }, { 3082, 3367 },
            { 3082, 3370 }, { 3081, 3372 }, { 3081, 3376 }, { 3081, 3377 },
            { 3082, 3380 }, { 3083, 3382 }, { 3084, 3384 }, { 3084, 3386 },
            { 3084, 3387 }, { 3089, 3388 }, { 3095, 3390 }, { 3100, 3388 },
            { 3104, 3388 }, { 3104, 3392 }
    };

    // Zone 7 - Manor Edge East (Outer)
    private static final int[][] ZONE7_EDGE_EAST = {
            { 3121, 3385 }, { 3127, 3385 }, { 3132, 3381 }, { 3135, 3382 },
            { 3138, 3380 }, { 3140, 3377 }, { 3142, 3374 }, { 3144, 3370 },
            { 3145, 3362 }, { 3145, 3360 }, { 3146, 3358 }, { 3147, 3355 },
            { 3147, 3353 }, { 3148, 3351 }, { 3148, 3343 }, { 3149, 3337 },
            { 3150, 3334 }, { 3145, 3335 }, { 3144, 3333 }, { 3141, 3333 },
            { 3140, 3334 }, { 3134, 3339 }, { 3129, 3341 }, { 3130, 3347 },
            { 3130, 3352 }, { 3130, 3355 }, { 3130, 3364 }, { 3129, 3371 },
            { 3128, 3375 }, { 3126, 3379 }, { 3122, 3382 }, { 3121, 3385 }
    };

    // Zone 8 - West Grass Path
    private static final int[][] ZONE8_WEST_GRASS_PATH = {
            { 3061, 3390 }, { 3066, 3391 }, { 3068, 3390 }, { 3072, 3390 },
            { 3075, 3388 }, { 3075, 3386 }, { 3077, 3381 }, { 3075, 3379 },
            { 3075, 3375 }, { 3076, 3372 }, { 3075, 3367 }, { 3074, 3362 },
            { 3073, 3359 }, { 3073, 3356 }, { 3075, 3354 }, { 3076, 3351 },
            { 3077, 3348 }, { 3075, 3345 }, { 3072, 3339 }, { 3070, 3330 },
            { 3070, 3328 }, { 3069, 3328 }, { 3066, 3327 }, { 3064, 3327 },
            { 3062, 3327 }, { 3062, 3330 }, { 3061, 3332 }, { 3060, 3338 },
            { 3061, 3346 }, { 3061, 3351 }, { 3061, 3355 }, { 3061, 3360 },
            { 3061, 3362 }, { 3067, 3369 }, { 3066, 3385 }, { 3061, 3390 }
    };
}
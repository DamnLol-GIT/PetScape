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


public class FaladorArea extends RoamingArea
{
    private final int zone;

    private FaladorArea(int zone) { this.zone = zone; }

    public static FaladorArea zone1() { return new FaladorArea(1); }
    public static FaladorArea zone2() { return new FaladorArea(2); }
    public static FaladorArea zone3() { return new FaladorArea(3); }
    public static FaladorArea zone4() { return new FaladorArea(4); }

    @Override
    public String getAreaId() { return "falador_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.BABY_MOLE, NpcID.BABY_MOLERAT };
            case 2: return new int[]{ NpcID.BLOODHOUND };
            case 3: return new int[]{ NpcID.ROCKY, NpcID.BABY_MOLE, NpcID.GIANT_SQUIRREL };
            default: return new int[]{ NpcID.SOUP };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
                // First 12 Baby Mole, last 6 Baby Mole Rat
                for (int i = 0; i < n; i++)
                    names[i] = i < 12 ? "Baby Mole" : "Baby Mole Rat";
                break;
            case 2:
                Arrays.fill(names, "Bloodhound");
                break;
            case 3:
                // First 4 Rocky, next 12 Baby Mole, last 12 Giant Squirrel
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Rocky";
                    else if (i < 16) names[i] = "Baby Mole";
                    else names[i] = "Giant Squirrel";
                }
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
            case 1: return formIndex == 0 ? "<col=ffff00>Baby Mole</col>" : "<col=ffff00>Baby Mole Rat</col>";
            case 2: return "<col=ffff00>Bloodhound</col>";
            case 3:
                if (formIndex == 0) return "<col=ffff00>Rocky</col>";
                if (formIndex == 1) return "<col=ffff00>Baby Mole</col>";
                return "<col=ffff00>Giant Squirrel</col>";
            default: return "<col=ffff00>Soup</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return formIndex == 0 ? "A cute mole you would want." : "It's inside out!";
            case 2: return "Pawsitive to keep the castle safe.";
            case 3:
                if (formIndex == 0) return "Get robbed mate.";
                if (formIndex == 1) return "An impressively small mole.";
                return "It just wants to be helpful.";
            default: return "Noice";
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 12 Baby Mole (0), 6 Baby Mole Rat (1)
                return spawnIndex < 12 ? 0 : 1;
            case 3:
                // 4 Rocky (0), 12 Baby Mole (1), 12 Giant Squirrel (2)
                if (spawnIndex < 4)  return 0;
                if (spawnIndex < 16) return 1;
                return 2;
            default:
                return super.getFormAssignment(spawnIndex, nForms);
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 18; // 12 x Baby Mole, 6 x Baby Mole Rat
            case 2: return 12; // 12 x Bloodhound
            case 3: return 28; // 4 x Rocky, 12 x Baby Mole, 12 x Giant Squirrel
            default: return 4; // 4 x Soup
        }
    }

    @Override
    public boolean isAquatic() { return zone == 4; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return zone == 4 ? -5 : 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_PARK;
            case 2: return ZONE2_CASTLE_F1;
            case 3: return ZONE3_MAIN;
            default: return ZONE4_MOAT;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        switch (zone)
        {
            case 3: return FORBIDDEN_ZONE3;
            case 4: return FORBIDDEN_ZONE4;
            default: return new int[0][][];
        }
    }

    // Zone 1 - Falador Park
    private static final int[][] ZONE1_PARK = {
            { 3001, 3391 }, { 2984, 3391 }, { 2981, 3389 }, { 2981, 3382 },
            { 2983, 3380 }, { 2987, 3380 }, { 2990, 3377 }, { 2992, 3376 },
            { 2993, 3375 }, { 2998, 3374 }, { 3002, 3370 }, { 3012, 3370 },
            { 3014, 3368 }, { 3019, 3368 }, { 3020, 3369 }, { 3022, 3369 },
            { 3024, 3371 }, { 3024, 3373 }, { 3026, 3375 }, { 3025, 3377 },
            { 3026, 3378 }, { 3026, 3380 }, { 3024, 3382 }, { 3024, 3383 },
            { 3025, 3384 }, { 3021, 3388 }, { 3017, 3388 }, { 3014, 3390 },
            { 3009, 3391 }, { 3009, 3391 }, { 3001, 3391 }
    };

    // Zone 2 - Falador Castle F1
    private static final int[][] ZONE2_CASTLE_F1 = {
            { 2964, 3363 }, { 2965, 3363 }, { 2965, 3355 }, { 2970, 3354 },
            { 2970, 3351 }, { 2980, 3351 }, { 2982, 3354 }, { 2985, 3354 },
            { 2986, 3353 }, { 2985, 3349 }, { 2987, 3347 }, { 2987, 3344 },
            { 2991, 3344 }, { 2991, 3343 }, { 2994, 3345 }, { 2997, 3345 },
            { 2999, 3343 }, { 2999, 3340 }, { 2996, 3338 }, { 2994, 3338 },
            { 2992, 3340 }, { 2991, 3340 }, { 2991, 3335 }, { 2988, 3332 },
            { 2988, 3330 }, { 2986, 3330 }, { 2986, 3329 }, { 2980, 3329 },
            { 2979, 3328 }, { 2962, 3328 }, { 2959, 3331 }, { 2959, 3333 },
            { 2959, 3334 }, { 2957, 3334 }, { 2954, 3338 }, { 2954, 3340 },
            { 2958, 3344 }, { 2960, 3344 }, { 2960, 3354 }, { 2961, 3356 },
            { 2964, 3357 }, { 2964, 3363 }

    };

    // Zone 3 - Falador Main
    private static final int[][] ZONE3_MAIN = {
            { 2937, 3386 }, { 2946, 3393 }, { 2969, 3394 }, { 2985, 3393 },
            { 2979, 3389 }, { 2980, 3381 }, { 2985, 3377 }, { 2990, 3375 },
            { 2998, 3371 }, { 3001, 3368 }, { 3011, 3368 }, { 3014, 3365 },
            { 3025, 3370 }, { 3027, 3374 }, { 3032, 3374 }, { 3032, 3385 },
            { 3026, 3385 }, { 3023, 3388 }, { 3035, 3389 }, { 3060, 3389 },
            { 3065, 3383 }, { 3065, 3369 }, { 3058, 3363 }, { 3058, 3330 },
            { 3025, 3330 }, { 3021, 3327 }, { 3014, 3327 }, { 3009, 3323 },
            { 3004, 3325 }, { 2999, 3325 }, { 2992, 3318 }, { 2989, 3318 },
            { 2985, 3313 }, { 2985, 3318 }, { 2975, 3318 }, { 2975, 3316 },
            { 2967, 3316 }, { 2966, 3312 }, { 2957, 3312 }, { 2956, 3313 },
            { 2943, 3313 }, { 2942, 3314 }, { 2942, 3320 }, { 2942, 3321 },
            { 2942, 3322 }, { 2938, 3322 }, { 2937, 3385 }
    };

    // Zone 3 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE3 = {

            // Falador Castle
            {
                    { 2964, 3363 }, { 2971, 3363 }, { 2975, 3361 }, { 2983, 3361 },
                    { 2990, 3359 }, { 2998, 3354 }, { 3004, 3350 }, { 3005, 3347 },
                    { 3005, 3332 }, { 2999, 3330 }, { 2988, 3320 }, { 2982, 3321 },
                    { 2974, 3321 }, { 2965, 3317 }, { 2959, 3318 }, { 2951, 3328 },
                    { 2948, 3334 }, { 2948, 3347 }, { 2952, 3355 }, { 2957, 3362 },
                    { 2964, 3363 }
            },

            // Party Room
            {
                    { 3041, 3371 }, { 3039, 3370 }, { 3036, 3370 }, { 3034, 3372 },
                    { 3035, 3375 }, { 3036, 3376 }, { 3036, 3382 }, { 3035, 3383 },
                    { 3035, 3386 }, { 3036, 3387 }, { 3040, 3387 }, { 3041, 3386 },
                    { 3051, 3386 }, { 3053, 3387 }, { 3055, 3387 }, { 3056, 3385 },
                    { 3056, 3381 }, { 3055, 3381 }, { 3055, 3376 }, { 3056, 3375 },
                    { 3056, 3372 }, { 3054, 3370 }, { 3051, 3370 }, { 3050, 3371 },
                    { 3041, 3371 }
            },

            // East Bank
            {
                    { 3008, 3359 }, { 3019, 3359 }, { 3021, 3357 }, { 3022, 3357 },
                    { 3022, 3353 }, { 3008, 3353 }, { 3008, 3359 }
            },

            // West Bank
            {
                    { 2942, 3374 }, { 2948, 3374 }, { 2948, 3370 }, { 2950, 3370 },
                    { 2950, 3366 }, { 2949, 3366 }, { 2949, 3359 }, { 2945, 3359 },
                    { 2945, 3362 }, { 2946, 3362 }, { 2946, 3366 }, { 2945, 3366 },
                    { 2945, 3368 }, { 2942, 3368 }, { 2942, 3374 }
            }
    };

    // Zone 4 - Falador Moat
    private static final int[][] ZONE4_MOAT = {
            { 2966, 3362 }, { 2969, 3362 }, { 2974, 3360 }, { 2978, 3360 },
            { 2981, 3359 }, { 2986, 3358 }, { 2989, 3357 }, { 2993, 3355 },
            { 2995, 3354 }, { 3001, 3350 }, { 3004, 3347 }, { 3004, 3343 },
            { 3004, 3342 }, { 3004, 3338 }, { 3003, 3336 }, { 3001, 3333 },
            { 3000, 3332 }, { 2998, 3332 }, { 2994, 3327 }, { 2991, 3323 },
            { 2989, 3322 }, { 2984, 3322 }, { 2974, 3322 }, { 2970, 3320 },
            { 2967, 3319 }, { 2964, 3318 }, { 2959, 3320 }, { 2958, 3323 },
            { 2956, 3324 }, { 2954, 3327 }, { 2953, 3329 }, { 2952, 3330 },
            { 2951, 3332 }, { 2949, 3333 }, { 2949, 3337 }, { 2950, 3338 },
            { 2949, 3342 }, { 2949, 3344 }, { 2952, 3350 }, { 2954, 3355 },
            { 2956, 3359 }, { 2958, 3361 }, { 2963, 3363 }, { 2965, 3362 },
            { 2966, 3362 }
    };

    // Zone 4 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE4 = {
            // Castle island
            {
                    { 2966, 3358 }, { 2964, 3358 }, { 2963, 3358 }, { 2962, 3357 },
                    { 2960, 3358 }, { 2958, 3357 }, { 2957, 3355 }, { 2957, 3354 },
                    { 2956, 3352 }, { 2954, 3350 }, { 2953, 3348 }, { 2953, 3346 },
                    { 2952, 3345 }, { 2951, 3342 }, { 2951, 3336 }, { 2954, 3332 },
                    { 2954, 3331 }, { 2955, 3330 }, { 2956, 3330 }, { 2959, 3325 },
                    { 2962, 3323 }, { 2970, 3323 }, { 2973, 3325 }, { 2974, 3325 },
                    { 2974, 3324 }, { 2979, 3324 }, { 2980, 3325 }, { 2982, 3325 },
                    { 2984, 3324 }, { 2989, 3326 }, { 2992, 3328 }, { 2994, 3330 },
                    { 2998, 3333 }, { 3002, 3338 }, { 3003, 3339 }, { 3003, 3343 },
                    { 3002, 3345 }, { 2995, 3352 }, { 2992, 3354 }, { 2990, 3354 },
                    { 2986, 3357 }, { 2985, 3356 }, { 2982, 3357 }, { 2981, 3357 },
                    { 2979, 3356 }, { 2976, 3356 }, { 2975, 3357 }, { 2974, 3358 },
                    { 2969, 3358 }, { 2968, 3357 }, { 2966, 3358 }
            },
            // North bridge span
            {
                    { 2963, 3363 }, { 2966, 3363 }, { 2966, 3358 }, { 2963, 3358 },
                    { 2963, 3363 }
            }
    };
}
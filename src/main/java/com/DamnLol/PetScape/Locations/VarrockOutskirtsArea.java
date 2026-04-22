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


public class VarrockOutskirtsArea extends RoamingArea
{
    private final int zone;

    private VarrockOutskirtsArea(int zone) { this.zone = zone; }

    public static VarrockOutskirtsArea zone1()  { return new VarrockOutskirtsArea(1); }
    public static VarrockOutskirtsArea zone2()  { return new VarrockOutskirtsArea(2); }
    public static VarrockOutskirtsArea zone3()  { return new VarrockOutskirtsArea(3); }
    public static VarrockOutskirtsArea zone4()  { return new VarrockOutskirtsArea(4); }
    public static VarrockOutskirtsArea zone5()  { return new VarrockOutskirtsArea(5); }
    public static VarrockOutskirtsArea zone6()  { return new VarrockOutskirtsArea(6); }
    public static VarrockOutskirtsArea zone7()  { return new VarrockOutskirtsArea(7); }
    public static VarrockOutskirtsArea zone8()  { return new VarrockOutskirtsArea(8); }
    public static VarrockOutskirtsArea zone9()  { return new VarrockOutskirtsArea(9); }
    public static VarrockOutskirtsArea zone10() { return new VarrockOutskirtsArea(10); }
    public static VarrockOutskirtsArea zone11() { return new VarrockOutskirtsArea(11); }

    @Override
    public String getAreaId() { return "varrock_outskirts_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY, NpcID.ZIGGY };
            case 2: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ZIGGY };
            case 3: return new int[]{ NpcID.QUETZIN };
            case 4:
            case 5: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 6: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 7: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEEF };
            case 8: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 9: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ZIGGY };
            case 10: return new int[]{ NpcID.CAT_6665 };
            default: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ZIGGY };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 3; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 8 Giant Squirrel(0), 2 Beaver(1), 4 Rocky(2), 2 Ziggy(3)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 10) return 1;
                if (spawnIndex < 14) return 2;
                return 3;
            case 2:
                // 6 Giant Squirrel(0), 6 Beaver(1), 2 Ziggy(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 12) return 1;
                return 2;
            case 4:
            case 5:
                return spawnIndex % nForms;
            case 6:
                // 8 Giant Squirrel(0), 6 Beaver(1), 6 Rocky(2)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 14) return 1;
                return 2;
            case 7:
                // 4 Giant Squirrel(0), 1 Rocky(1), 2 Beef(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 5) return 1;
                return 2;
            case 8:
                // 8 Giant Squirrel(0), 2 Beaver(1), 4 Rocky(2)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 9:
                // 6 Giant Squirrel(0), 2 Beaver(1), 2 Ziggy(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 8) return 1;
                return 2;
            default:
                // Zone 11: 4 Giant Squirrel(0), 2 Beaver(1), 2 Ziggy(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
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
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else if (i < 14) names[i] = "Rocky";
                    else names[i] = "Rocky";
                }
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 12) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 3:
                Arrays.fill(names, "Quetzin");
                break;
            case 4:
            case 5:
                Arrays.fill(names, "Rock Golem");
                break;
            case 6:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Giant Squirrel";
                    else if (i < 14) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 7:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 5) names[i] = "Rocky";
                    else names[i] = "Beef";
                }
                break;
            case 8:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 9:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 8) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 10:
                Arrays.fill(names, "Cat");
                break;
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Beaver";
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
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    case 2: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Ziggy</col>";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Ziggy</col>";
                }
            case 3:
                return "<col=ffff00>Quetzin</col>";
            case 4:
            case 5:
                return "<col=ffff00>Rock Golem</col>";
            case 6:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 7:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beef</col>";
                }
            case 8:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 9:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Ziggy</col>";
                }
            case 10:
                return "<col=ffff00>Cat</col>";
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Ziggy</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                switch (formIndex)
                {
                    case 0: return "Weaves through the grass like it's nothing.";
                    case 1: return "Appraising the tree line with quiet judgement.";
                    case 2: return "Loitering where the guards can't see.";
                    default: return "No relation to any raccoon. Allegedly.";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "Uses the log piles as a racetrack.";
                    case 1: return "Watching a lifetime of dreams go through the saw.";
                    default: return "The forest's laziest trickster.";
                }
            case 3:
                return "Waiting on the next flight out.";
            case 4:
            case 5:
                return "Mistaken for ore at least once a shift.";
            case 6:
                switch (formIndex)
                {
                    case 0: return "Vaults the wagon ruts without breaking stride.";
                    case 1: return "Chewing on its next big project.";
                    default: return "Suspiciously interested in the cartloads.";
                }
            case 7:
                switch (formIndex)
                {
                    case 0: return "Honour first. Acorns second.";
                    case 1: return "Robs only those who can spare it.";
                    default: return "Single-handedly emptied Cassius' cellar, Slew a Dragon.";
                }
            case 8:
                switch (formIndex)
                {
                    case 0: return "Darting between the fence posts with practised ease.";
                    case 1: return "Has plans for every log in sight.";
                    default: return "Keeps one paw on someone else's coin pouch.";
                }
            case 9:
                switch (formIndex)
                {
                    case 0: return "Pretends the walls aren't there.";
                    case 1: return "Takes the long route past every woodpile.";
                    default: return "Definitely a tanuki. Not a raccoon.";
                }
            case 10:
                return "Just one more, she said.";
            default:
                switch (formIndex)
                {
                    case 0: return "Rounds the perimeter twice before breakfast.";
                    case 1: return "Judging every plank in the fence.";
                    default: return "Master of disguise. Currently undisguised.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 16;
            case 2: return 14;
            case 3: return 4;
            case 4: return 3;
            case 5: return 3;
            case 6: return 20;
            case 7: return 7;
            case 8: return 14;
            case 9: return 10;
            case 10: return 6;
            default: return 8;
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
            case 1: return ZONE1_EAST_BORDER;
            case 2: return ZONE2_SAWMILL;
            case 3: return ZONE3_VARLAMORE_TRAVEL;
            case 4: return ZONE4_SE_MINE;
            case 5: return ZONE5_SW_MINE;
            case 6: return ZONE6_SOUTH_BORDER;
            case 7: return ZONE7_CHAMPIONS_GUILD;
            case 8: return ZONE8_WEST_BORDER_SOUTH;
            case 9: return ZONE9_WEST_BORDER_NORTH;
            case 10: return ZONE10_GERTRUDE_OUTER;
            default: return ZONE11_WEST_GE;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 8) return FORBIDDEN_ZONE8;
        if (zone == 10) return FORBIDDEN_ZONE10;
        return new int[0][][];
    }

    // Zone 1 - East Border
    private static final int[][] ZONE1_EAST_BORDER = {
            { 3263, 3490 }, { 3263, 3493 }, { 3266, 3494 }, { 3268, 3493 },
            { 3270, 3492 }, { 3270, 3490 }, { 3270, 3488 }, { 3271, 3486 },
            { 3272, 3483 }, { 3274, 3481 }, { 3276, 3479 }, { 3281, 3478 },
            { 3282, 3477 }, { 3282, 3476 }, { 3281, 3473 }, { 3279, 3472 },
            { 3278, 3471 }, { 3279, 3466 }, { 3279, 3464 }, { 3282, 3464 },
            { 3286, 3463 }, { 3288, 3463 }, { 3293, 3460 }, { 3294, 3459 },
            { 3295, 3458 }, { 3295, 3457 }, { 3295, 3446 }, { 3295, 3429 },
            { 3295, 3405 }, { 3295, 3384 }, { 3296, 3383 }, { 3302, 3383 },
            { 3303, 3382 }, { 3303, 3377 }, { 3288, 3376 }, { 3291, 3380 },
            { 3291, 3384 }, { 3290, 3385 }, { 3290, 3389 }, { 3290, 3391 },
            { 3289, 3392 }, { 3289, 3407 }, { 3287, 3409 }, { 3286, 3409 },
            { 3284, 3426 }, { 3279, 3433 }, { 3274, 3437 }, { 3271, 3438 },
            { 3271, 3464 }, { 3264, 3472 }, { 3263, 3490 }
    };

    // Zone 2 - Sawmill
    private static final int[][] ZONE2_SAWMILL = {
            { 3292, 3521 }, { 3287, 3514 }, { 3287, 3503 }, { 3289, 3503 },
            { 3289, 3496 }, { 3287, 3496 }, { 3287, 3486 }, { 3285, 3484 },
            { 3282, 3482 }, { 3281, 3480 }, { 3281, 3477 }, { 3283, 3474 },
            { 3284, 3473 }, { 3288, 3473 }, { 3289, 3465 }, { 3290, 3461 },
            { 3294, 3461 }, { 3296, 3461 }, { 3300, 3458 }, { 3308, 3461 },
            { 3310, 3464 }, { 3316, 3467 }, { 3316, 3469 }, { 3315, 3471 },
            { 3311, 3473 }, { 3310, 3476 }, { 3311, 3482 }, { 3309, 3484 },
            { 3309, 3486 }, { 3310, 3488 }, { 3321, 3489 }, { 3326, 3490 },
            { 3327, 3492 }, { 3327, 3495 }, { 3327, 3498 }, { 3327, 3501 },
            { 3326, 3503 }, { 3316, 3492 }, { 3310, 3492 }, { 3308, 3491 },
            { 3305, 3490 }, { 3304, 3490 }, { 3297, 3492 }, { 3294, 3501 },
            { 3293, 3502 }, { 3291, 3504 }, { 3292, 3521 }
    };

    // Zone 3 - Varlamore Travel Spot
    private static final int[][] ZONE3_VARLAMORE_TRAVEL = {
            { 3278, 3424 }, { 3278, 3420 }, { 3274, 3419 }, { 3274, 3411 },
            { 3277, 3408 }, { 3286, 3408 }, { 3288, 3410 }, { 3287, 3412 },
            { 3288, 3415 }, { 3288, 3417 }, { 3286, 3420 }, { 3285, 3421 },
            { 3284, 3422 }, { 3281, 3426 }, { 3278, 3424 }
    };

    // Zone 4 - S/E Mine
    private static final int[][] ZONE4_SE_MINE = {
            { 3279, 3372 }, { 3280, 3373 }, { 3282, 3374 }, { 3284, 3375 },
            { 3288, 3375 }, { 3290, 3375 }, { 3291, 3373 }, { 3292, 3373 },
            { 3292, 3372 }, { 3290, 3371 }, { 3290, 3366 }, { 3295, 3359 },
            { 3295, 3357 }, { 3292, 3356 }, { 3290, 3356 }, { 3287, 3360 },
            { 3283, 3360 }, { 3282, 3359 }, { 3279, 3359 }, { 3277, 3361 },
            { 3278, 3363 }, { 3280, 3365 }, { 3280, 3370 }, { 3279, 3372 }
    };

    // Zone 5 - S/W Mine
    private static final int[][] ZONE5_SW_MINE = {
            { 3180, 3381 }, { 3177, 3379 }, { 3176, 3378 }, { 3175, 3376 },
            { 3173, 3372 }, { 3172, 3371 }, { 3171, 3369 }, { 3170, 3368 },
            { 3170, 3365 }, { 3171, 3363 }, { 3172, 3363 }, { 3174, 3362 },
            { 3177, 3362 }, { 3180, 3362 }, { 3183, 3364 }, { 3184, 3367 },
            { 3184, 3370 }, { 3185, 3372 }, { 3186, 3374 }, { 3186, 3376 },
            { 3186, 3378 }, { 3186, 3379 }, { 3186, 3380 }, { 3183, 3381 },
            { 3180, 3381 }
    };

    // Zone 6 - South Border
    private static final int[][] ZONE6_SOUTH_BORDER = {
            { 3289, 3377 }, { 3294, 3378 }, { 3299, 3378 }, { 3301, 3376 },
            { 3302, 3373 }, { 3303, 3369 }, { 3303, 3366 }, { 3302, 3362 },
            { 3303, 3360 }, { 3307, 3357 }, { 3310, 3357 }, { 3310, 3349 },
            { 3310, 3346 }, { 3310, 3342 }, { 3310, 3339 }, { 3310, 3336 },
            { 3310, 3334 }, { 3310, 3330 }, { 3306, 3332 }, { 3303, 3332 },
            { 3299, 3332 }, { 3293, 3332 }, { 3290, 3331 }, { 3288, 3331 },
            { 3286, 3332 }, { 3282, 3332 }, { 3277, 3332 }, { 3273, 3331 },
            { 3268, 3332 }, { 3264, 3332 }, { 3260, 3331 }, { 3257, 3331 },
            { 3253, 3332 }, { 3251, 3334 }, { 3250, 3335 }, { 3249, 3336 },
            { 3248, 3338 }, { 3248, 3339 }, { 3249, 3341 }, { 3250, 3343 },
            { 3252, 3344 }, { 3253, 3345 }, { 3256, 3346 }, { 3257, 3345 },
            { 3269, 3342 }, { 3270, 3343 }, { 3272, 3345 }, { 3273, 3349 },
            { 3272, 3351 }, { 3272, 3353 }, { 3270, 3356 }, { 3265, 3360 },
            { 3257, 3361 }, { 3252, 3359 }, { 3251, 3358 }, { 3247, 3360 },
            { 3243, 3360 }, { 3240, 3362 }, { 3237, 3362 }, { 3237, 3365 },
            { 3239, 3368 }, { 3240, 3370 }, { 3241, 3372 }, { 3241, 3374 },
            { 3243, 3374 }, { 3243, 3378 }, { 3247, 3380 }, { 3253, 3379 },
            { 3254, 3378 }, { 3258, 3377 }, { 3261, 3378 }, { 3263, 3375 },
            { 3269, 3375 }, { 3278, 3374 }, { 3277, 3371 }, { 3276, 3367 },
            { 3276, 3365 }, { 3276, 3363 }, { 3278, 3359 }, { 3282, 3356 },
            { 3287, 3356 }, { 3295, 3356 }, { 3296, 3357 }, { 3297, 3360 },
            { 3296, 3362 }, { 3294, 3363 }, { 3293, 3365 }, { 3293, 3367 },
            { 3293, 3370 }, { 3293, 3372 }, { 3290, 3373 }, { 3285, 3373 },
            { 3289, 3377 }
    };

    // Zone 7 - Champions Guild
    private static final int[][] ZONE7_CHAMPIONS_GUILD = {
            { 3184, 3366 }, { 3187, 3371 }, { 3190, 3375 }, { 3192, 3378 },
            { 3198, 3377 }, { 3203, 3377 }, { 3207, 3376 }, { 3210, 3373 },
            { 3213, 3369 }, { 3214, 3366 }, { 3215, 3363 }, { 3216, 3361 },
            { 3215, 3359 }, { 3212, 3355 }, { 3207, 3352 }, { 3205, 3349 },
            { 3202, 3348 }, { 3200, 3351 }, { 3201, 3356 }, { 3200, 3361 },
            { 3197, 3364 }, { 3192, 3366 }, { 3184, 3366 }
    };

    // Zone 8 - West Border (South)
    private static final int[][] ZONE8_WEST_BORDER_SOUTH = {
            { 3109, 3419 }, { 3110, 3415 }, { 3112, 3413 }, { 3113, 3413 },
            { 3115, 3409 }, { 3115, 3405 }, { 3115, 3402 }, { 3117, 3401 },
            { 3122, 3395 }, { 3124, 3394 }, { 3127, 3393 }, { 3131, 3393 },
            { 3135, 3393 }, { 3141, 3392 }, { 3148, 3389 }, { 3150, 3387 },
            { 3154, 3383 }, { 3155, 3377 }, { 3156, 3374 }, { 3157, 3367 },
            { 3159, 3364 }, { 3162, 3361 }, { 3165, 3359 }, { 3171, 3359 },
            { 3175, 3359 }, { 3174, 3360 }, { 3171, 3362 }, { 3168, 3368 },
            { 3169, 3371 }, { 3169, 3373 }, { 3177, 3379 }, { 3179, 3381 },
            { 3180, 3385 }, { 3180, 3388 }, { 3179, 3393 }, { 3177, 3396 },
            { 3174, 3397 }, { 3173, 3397 }, { 3171, 3401 }, { 3172, 3405 },
            { 3172, 3412 }, { 3172, 3415 }, { 3171, 3418 }, { 3171, 3421 },
            { 3171, 3423 }, { 3171, 3425 }, { 3170, 3428 }, { 3166, 3427 },
            { 3164, 3425 }, { 3163, 3424 }, { 3159, 3422 }, { 3156, 3421 },
            { 3153, 3420 }, { 3150, 3419 }, { 3144, 3414 }, { 3136, 3413 },
            { 3131, 3412 }, { 3123, 3413 }, { 3116, 3416 }, { 3109, 3419 }
    };

    // Zone 8 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE8 = {
            // Gertrude's House
            {
                    { 3146, 3414 }, { 3155, 3414 }, { 3156, 3413 }, { 3158, 3413 },
                    { 3158, 3408 }, { 3157, 3407 }, { 3157, 3403 }, { 3145, 3403 },
                    { 3144, 3405 }, { 3143, 3407 }, { 3143, 3412 }, { 3146, 3414 }
            }
    };

    // Zone 9 - West Border (North)
    private static final int[][] ZONE9_WEST_BORDER_NORTH = {
            { 3106, 3463 }, { 3109, 3461 }, { 3119, 3461 }, { 3124, 3464 },
            { 3132, 3464 }, { 3135, 3466 }, { 3137, 3460 }, { 3137, 3456 },
            { 3136, 3452 }, { 3135, 3449 }, { 3136, 3445 }, { 3139, 3444 },
            { 3143, 3441 }, { 3145, 3440 }, { 3148, 3438 }, { 3146, 3434 },
            { 3147, 3432 }, { 3148, 3427 }, { 3149, 3424 }, { 3157, 3424 },
            { 3156, 3421 }, { 3153, 3419 }, { 3149, 3418 }, { 3146, 3420 },
            { 3134, 3418 }, { 3129, 3417 }, { 3125, 3417 }, { 3120, 3421 },
            { 3117, 3422 }, { 3113, 3421 }, { 3113, 3421 }, { 3114, 3426 },
            { 3116, 3428 }, { 3117, 3429 }, { 3118, 3435 }, { 3117, 3436 },
            { 3117, 3438 }, { 3113, 3441 }, { 3111, 3443 }, { 3109, 3447 },
            { 3109, 3448 }, { 3108, 3452 }, { 3107, 3455 }, { 3106, 3458 },
            { 3106, 3460 }, { 3106, 3463 }
    };

    // Zone 10 - Gertrude House (Outer)
    private static final int[][] ZONE10_GERTRUDE_OUTER = {
            { 3150, 3415 }, { 3157, 3415 }, { 3159, 3413 }, { 3159, 3407 },
            { 3159, 3404 }, { 3157, 3402 }, { 3146, 3402 }, { 3143, 3405 },
            { 3142, 3407 }, { 3142, 3413 }, { 3144, 3415 }, { 3150, 3415 }
    };

    // Zone 10 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE10 = {
            // Gertrude's House (Inner)
            {
                    { 3148, 3412 }, { 3152, 3412 }, { 3153, 3411 }, { 3156, 3411 },
                    { 3158, 3411 }, { 3157, 3408 }, { 3157, 3404 }, { 3149, 3404 },
                    { 3149, 3407 }, { 3148, 3408 }, { 3148, 3412 }
            }
    };

    // Zone 11 - West GE
    private static final int[][] ZONE11_WEST_GE = {
            { 3129, 3506 }, { 3132, 3511 }, { 3136, 3510 }, { 3135, 3496 },
            { 3138, 3489 }, { 3134, 3469 }, { 3124, 3467 }, { 3116, 3464 },
            { 3108, 3465 }, { 3108, 3480 }, { 3115, 3490 }, { 3123, 3494 },
            { 3128, 3500 }, { 3129, 3506 }
    };
}

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


public class DigsiteArea extends RoamingArea
{
    private final int zone;

    private DigsiteArea(int zone) { this.zone = zone; }

    public static DigsiteArea zone1() { return new DigsiteArea(1); }
    public static DigsiteArea zone2() { return new DigsiteArea(2); }
    public static DigsiteArea zone3() { return new DigsiteArea(3); }
    public static DigsiteArea zone4() { return new DigsiteArea(4); }
    public static DigsiteArea zone5() { return new DigsiteArea(5); }
    public static DigsiteArea zone6() { return new DigsiteArea(6); }
    public static DigsiteArea zone7() { return new DigsiteArea(7); }

    @Override
    public String getAreaId() { return "digsite_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{
                    NpcID.BONE_SQUIRREL,
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642,
                    NpcID.BABY_MOLERAT, NpcID.ROCKY
            };
            case 2: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 3: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER };
            case 4: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BABY_CHINCHOMPA };
            case 5: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 6: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            default: return new int[]{ NpcID.BONE_SQUIRREL };
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
                // 8 Squirrel(0), 4 Rock Golem, 3 Molerat(20), 2 Rocky(21)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 12) return 1 + ((spawnIndex - 8) % 19);
                if (spawnIndex < 15) return 20;
                return 21;
            case 2:
                // 12 Squirrel(0), 6 Rocky(1), 4 Beaver(2)
                if (spawnIndex < 12) return 0;
                if (spawnIndex < 18) return 1;
                return 2;
            case 3:
                // 6 Squirrel(0), 2 Beaver(1)
                return spawnIndex < 6 ? 0 : 1;
            case 4:
                // 4 Squirrel(0), 2 Chinchompa(1)
                return spawnIndex < 4 ? 0 : 1;
            case 5:
                // 8 Squirrel(0), 2 Beaver(1), 4 Rocky(2)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 6:
                // Rock Golem - random form rotation
                return spawnIndex % nForms;
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
                    if (i < 8) names[i] = "Giant Squirrel";
                    else if (i < 12) names[i] = "Rock Golem";
                    else if (i < 15) names[i] = "Baby Mole Rat";
                    else names[i] = "Rocky";
                }
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 12) names[i] = "Giant Squirrel";
                    else if (i < 18) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 3:
                for (int i = 0; i < n; i++)
                    names[i] = i < 6 ? "Giant Squirrel" : "Beaver";
                break;
            case 4:
                for (int i = 0; i < n; i++)
                    names[i] = i < 4 ? "Giant Squirrel" : "Baby Chinchompa";
                break;
            case 5:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 6:
                Arrays.fill(names, "Rock Golem");
                break;
            default:
                Arrays.fill(names, "Giant Squirrel");
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
                if (formIndex == 0) return "<col=ffff00>Skeletal Squirrel</col>";
                if (formIndex >= 1 && formIndex <= 19) return "<col=ffff00>Rock Golem</col>";
                if (formIndex == 20) return "<col=ffff00>Baby Mole Rat</col>";
                return "<col=ffff00>Rocky</col>";
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 3:
                return formIndex == 0
                        ? "<col=ffff00>Giant Squirrel</col>"
                        : "<col=ffff00>Beaver</col>";
            case 4:
                return formIndex == 0
                        ? "<col=ffff00>Giant Squirrel</col>"
                        : "<col=ffff00>Volatile Chinchompa</col>";
            case 5:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 6:
                return "<col=ffff00>Rock Golem</col>";
            default:
                return "<col=ffff00>Skeletal Squirrel</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                if (formIndex == 0) return "The archaeologists keep trying to catalogue it.";
                if (formIndex >= 1 && formIndex <= 19) return "Mined, sifted, and labelled.";
                if (formIndex == 20) return "Surveying underground. Without permit.";
                return "Pocketing small artifacts. For safekeeping.";
            case 2:
                switch (formIndex)
                {
                    case 0: return "Acrobatics with no audience.";
                    case 1: return "Suspiciously good at vanishing between trees.";
                    default: return "Sizing up trees the diggers haven't touched.";
                }
            case 3:
                return formIndex == 0
                        ? "Has seen every student cry at least once."
                        : "The yard's unappointed inspector.";
            case 4:
                return formIndex == 0
                        ? "Panning for acorns and coming up empty."
                        : "Should probably alert someone with a badge.";
            case 5:
                switch (formIndex)
                {
                    case 0: return "Treats gravity as a suggestion.";
                    case 1: return "Eyeing the woods with a long-term plan.";
                    default: return "Keeping tabs on every traveller.";
                }
            case 6:
                return "One of many rocks. Slightly more suspicious.";
            default:
                return "Refuses to end up in a Pot of vinegar";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 17;
            case 2: return 22;
            case 3: return 8;
            case 4: return 6;
            case 5: return 14;
            case 6: return 2;
            default: return 3;
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
            case 1: return ZONE1_DIGSITE;
            case 2: return ZONE2_WEST_GRASS;
            case 3: return ZONE3_EXAM;
            case 4: return ZONE4_PANNING;
            case 5: return ZONE5_SILVAREA;
            case 6: return ZONE6_SILVAREA_MINING;
            default: return ZONE7_RAG_AND_BONE;
        }
    }

    // Zone 1 - Digsite
    private static final int[][] ZONE1_DIGSITE = {
            { 3336, 3445 }, { 3328, 3445 }, { 3328, 3437 }, { 3326, 3427 },
            { 3324, 3419 }, { 3324, 3408 }, { 3328, 3399 }, { 3331, 3395 },
            { 3340, 3392 }, { 3345, 3394 }, { 3347, 3395 }, { 3354, 3392 },
            { 3359, 3388 }, { 3364, 3391 }, { 3370, 3392 }, { 3376, 3390 },
            { 3378, 3391 }, { 3380, 3394 }, { 3381, 3399 }, { 3382, 3403 },
            { 3383, 3411 }, { 3382, 3414 }, { 3381, 3416 }, { 3380, 3420 },
            { 3381, 3423 }, { 3382, 3426 }, { 3382, 3431 }, { 3382, 3432 },
            { 3380, 3434 }, { 3380, 3442 }, { 3379, 3444 }, { 3367, 3445 },
            { 3349, 3445 }, { 3336, 3445 }
    };

    // Zone 2 - West Grass Area
    private static final int[][] ZONE2_WEST_GRASS = {
            { 3320, 3466 }, { 3324, 3464 }, { 3326, 3461 }, { 3326, 3458 },
            { 3327, 3451 }, { 3329, 3446 }, { 3327, 3442 }, { 3323, 3436 },
            { 3323, 3432 }, { 3322, 3429 }, { 3321, 3418 }, { 3321, 3413 },
            { 3321, 3408 }, { 3324, 3405 }, { 3323, 3401 }, { 3326, 3393 },
            { 3329, 3388 }, { 3332, 3385 }, { 3335, 3382 }, { 3337, 3379 },
            { 3339, 3375 }, { 3340, 3372 }, { 3340, 3367 }, { 3339, 3361 },
            { 3339, 3356 }, { 3339, 3353 }, { 3337, 3350 }, { 3332, 3343 },
            { 3329, 3339 }, { 3326, 3337 }, { 3324, 3337 }, { 3321, 3337 },
            { 3319, 3336 }, { 3316, 3334 }, { 3313, 3336 }, { 3315, 3362 },
            { 3312, 3363 }, { 3308, 3363 }, { 3307, 3370 }, { 3306, 3373 },
            { 3306, 3378 }, { 3306, 3383 }, { 3303, 3389 }, { 3299, 3389 },
            { 3299, 3395 }, { 3299, 3400 }, { 3298, 3409 }, { 3299, 3423 },
            { 3300, 3429 }, { 3298, 3437 }, { 3298, 3446 }, { 3299, 3450 },
            { 3298, 3453 }, { 3304, 3453 }, { 3306, 3449 }, { 3317, 3448 },
            { 3318, 3451 }, { 3316, 3455 }, { 3315, 3456 }, { 3315, 3460 },
            { 3318, 3463 }, { 3320, 3466 }
    };

    // Zone 3 - Exam Area
    private static final int[][] ZONE3_EXAM = {
            { 3368, 3350 }, { 3365, 3353 }, { 3363, 3355 }, { 3361, 3356 },
            { 3355, 3362 }, { 3354, 3364 }, { 3351, 3365 }, { 3344, 3368 },
            { 3344, 3368 }, { 3340, 3364 }, { 3337, 3363 }, { 3335, 3360 },
            { 3329, 3351 }, { 3330, 3347 }, { 3331, 3343 }, { 3331, 3339 },
            { 3330, 3334 }, { 3329, 3331 }, { 3333, 3332 }, { 3336, 3333 },
            { 3343, 3334 }, { 3345, 3333 }, { 3346, 3333 }, { 3346, 3334 },
            { 3347, 3335 }, { 3347, 3339 }, { 3351, 3339 }, { 3355, 3339 },
            { 3355, 3342 }, { 3356, 3346 }, { 3356, 3347 }, { 3356, 3348 },
            { 3356, 3349 }, { 3357, 3350 }, { 3368, 3350 }
    };

    // Zone 4 - Panning Area
    private static final int[][] ZONE4_PANNING = {
            { 3389, 3386 }, { 3384, 3384 }, { 3382, 3380 }, { 3379, 3379 },
            { 3377, 3377 }, { 3376, 3375 }, { 3375, 3374 }, { 3371, 3374 },
            { 3369, 3376 }, { 3364, 3378 }, { 3363, 3379 }, { 3358, 3383 },
            { 3355, 3388 }, { 3355, 3391 }, { 3355, 3392 }, { 3362, 3393 },
            { 3365, 3393 }, { 3369, 3393 }, { 3374, 3392 }, { 3385, 3398 },
            { 3387, 3405 }, { 3391, 3405 }, { 3394, 3404 }, { 3396, 3401 },
            { 3396, 3400 }, { 3394, 3396 }, { 3394, 3395 }, { 3393, 3392 },
            { 3392, 3391 }, { 3392, 3389 }, { 3391, 3387 }, { 3389, 3386 }
    };

    // Zone 5 - Silvarea
    private static final int[][] ZONE5_SILVAREA = {
            { 3328, 3518 }, { 3334, 3519 }, { 3337, 3519 }, { 3341, 3517 },
            { 3343, 3513 }, { 3343, 3512 }, { 3342, 3508 }, { 3342, 3504 },
            { 3344, 3502 }, { 3351, 3495 }, { 3355, 3494 }, { 3362, 3492 },
            { 3368, 3492 }, { 3368, 3494 }, { 3370, 3493 }, { 3370, 3493 },
            { 3371, 3492 }, { 3373, 3492 }, { 3376, 3489 }, { 3379, 3488 },
            { 3382, 3489 }, { 3385, 3491 }, { 3389, 3492 }, { 3393, 3490 },
            { 3394, 3485 }, { 3396, 3483 }, { 3398, 3481 }, { 3394, 3478 },
            { 3389, 3477 }, { 3385, 3475 }, { 3381, 3474 }, { 3376, 3476 },
            { 3374, 3477 }, { 3369, 3477 }, { 3364, 3480 }, { 3362, 3480 },
            { 3356, 3478 }, { 3350, 3478 }, { 3347, 3477 }, { 3342, 3476 },
            { 3339, 3476 }, { 3336, 3475 }, { 3334, 3474 }, { 3329, 3473 },
            { 3329, 3473 }, { 3325, 3473 }, { 3324, 3473 }, { 3319, 3474 },
            { 3316, 3478 }, { 3315, 3480 }, { 3314, 3483 }, { 3316, 3485 },
            { 3320, 3484 }, { 3327, 3485 }, { 3329, 3485 }, { 3332, 3487 },
            { 3330, 3501 }, { 3330, 3503 }, { 3331, 3503 }, { 3332, 3508 },
            { 3331, 3510 }, { 3330, 3511 }, { 3328, 3518 }
    };

    // Zone 6 - Silvarea Mining
    private static final int[][] ZONE6_SILVAREA_MINING = {
            { 3365, 3500 }, { 3368, 3501 }, { 3369, 3502 }, { 3371, 3504 },
            { 3373, 3504 }, { 3374, 3503 }, { 3375, 3501 }, { 3376, 3500 },
            { 3374, 3498 }, { 3373, 3498 }, { 3372, 3496 }, { 3373, 3495 },
            { 3374, 3493 }, { 3371, 3493 }, { 3368, 3494 }, { 3368, 3496 },
            { 3368, 3497 }, { 3367, 3497 }, { 3366, 3498 }, { 3366, 3499 },
            { 3365, 3500 }
    };

    // Zone 7 - Rag and Bone Area
    private static final int[][] ZONE7_RAG_AND_BONE = {
            { 3364, 3501 }, { 3366, 3502 }, { 3367, 3503 }, { 3367, 3504 },
            { 3367, 3507 }, { 3367, 3508 }, { 3366, 3509 }, { 3365, 3509 },
            { 3364, 3512 }, { 3362, 3512 }, { 3361, 3513 }, { 3358, 3512 },
            { 3357, 3511 }, { 3356, 3510 }, { 3355, 3507 }, { 3355, 3506 },
            { 3359, 3504 }, { 3364, 3501 }
    };
}

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


public class IceMountainArea extends RoamingArea
{
    private final int zone;

    private IceMountainArea(int zone) { this.zone = zone; }

    public static IceMountainArea zone1()  { return new IceMountainArea(1); }
    public static IceMountainArea zone2()  { return new IceMountainArea(2); }
    public static IceMountainArea zone3()  { return new IceMountainArea(3); }
    public static IceMountainArea zone4()  { return new IceMountainArea(4); }
    public static IceMountainArea zone5()  { return new IceMountainArea(5); }
    public static IceMountainArea zone6()  { return new IceMountainArea(6); }
    public static IceMountainArea zone7()  { return new IceMountainArea(7); }
    public static IceMountainArea zone8()  { return new IceMountainArea(8); }
    public static IceMountainArea zone9()  { return new IceMountainArea(9); }
    public static IceMountainArea zone10() { return new IceMountainArea(10); }
    public static IceMountainArea zone11() { return new IceMountainArea(11); }
    public static IceMountainArea zone12() { return new IceMountainArea(12); }
    public static IceMountainArea zone13() { return new IceMountainArea(13); }
    public static IceMountainArea zone14() { return new IceMountainArea(14); }
    public static IceMountainArea zone15() { return new IceMountainArea(15); }
    public static IceMountainArea zone16() { return new IceMountainArea(16); }
    public static IceMountainArea zone17() { return new IceMountainArea(17); }
    public static IceMountainArea zone18() { return new IceMountainArea(18); }
    public static IceMountainArea zone19() { return new IceMountainArea(19); }
    public static IceMountainArea zone20() { return new IceMountainArea(20); }

    @Override
    public String getAreaId() { return "ice_mountain_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 3: return new int[]{ NpcID.RIC };
            case 2:
            case 4: return new int[]{ NpcID.PHOENIX_3078 };
            case 5: return new int[]{ NpcID.LIL_CREATOR };
            case 6: return new int[]{ NpcID.PHOENIX_3078, NpcID.PHOENIX_3079, NpcID.PHOENIX_3080 };
            case 7: return new int[]{
                    NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                    NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                    NpcID.TANGLEROOT_9496, NpcID.TANGLEROOT_9497, NpcID.TANGLEROOT_9498,
                    NpcID.TANGLEROOT_9499, NpcID.TANGLEROOT_9500, NpcID.TANGLEROOT_9501
            };
            case 8:
            case 9:
            case 12: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 10: return new int[]{
                    NpcID.RIFT_GUARDIAN,
                    NpcID.RIFT_GUARDIAN_7338, NpcID.RIFT_GUARDIAN_7339, NpcID.RIFT_GUARDIAN_7340,
                    NpcID.RIFT_GUARDIAN_7341, NpcID.RIFT_GUARDIAN_7342, NpcID.RIFT_GUARDIAN_7343,
                    NpcID.RIFT_GUARDIAN_7344, NpcID.RIFT_GUARDIAN_7345, NpcID.RIFT_GUARDIAN_7346,
                    NpcID.RIFT_GUARDIAN_7347, NpcID.RIFT_GUARDIAN_7348, NpcID.RIFT_GUARDIAN_7349,
                    NpcID.RIFT_GUARDIAN_7350, NpcID.RIFT_GUARDIAN_8024
            };
            case 11:
            case 13:
            case 14:
            case 15: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 16: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 17: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 18: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 19: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            default: return new int[]{ NpcID.HERON, NpcID.GREAT_BLUE_HERON };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying()
    {
        return zone == 2 || zone == 4 || zone == 6 || zone == 20;
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 6:
                // 1 Phoenix (Variant)
                return spawnIndex;
            case 7:
            case 10:
            case 16:
                return spawnIndex % nForms;
            case 8:
            case 9:
            case 12:
                // 6 Giant Squirrel(0), 4 Beaver(1), 4 Rocky(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 11:
            case 13:
                // 4 Giant Squirrel(0), 2 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
                return 2;
            case 14:
            case 15:
                // 3 Giant Squirrel(0), 1 Beaver(1), 1 Rocky(2)
                if (spawnIndex < 3) return 0;
                if (spawnIndex < 4) return 1;
                return 2;
            case 18:
                // 6 Giant Squirrel(0), 4 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 19:
                return spawnIndex % nForms;
            default:
                // 1 Heron(0), 1 Great Blue Heron(1)
                return spawnIndex;
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
            case 3:
                Arrays.fill(names, "Ric");
                break;
            case 2:
            case 4:
            case 6:
                Arrays.fill(names, "Phoenix");
                break;
            case 5:
                Arrays.fill(names, "Lil' Creator");
                break;
            case 7:
                Arrays.fill(names, "Tangleroot");
                break;
            case 8:
            case 9:
            case 12:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 10:
                Arrays.fill(names, "Rift Guardian");
                break;
            case 11:
            case 13:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 14:
            case 15:
                for (int i = 0; i < n; i++)
                {
                    if (i < 3) names[i] = "Giant Squirrel";
                    else if (i < 4) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 16:
                Arrays.fill(names, "Rock Golem");
                break;
            case 17:
                Arrays.fill(names, "Rock Golem");
                break;
            case 18:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 19:
                Arrays.fill(names, "Rock Golem");
                break;
            default:
                for (int i = 0; i < n; i++)
                    names[i] = i < 1 ? "Heron" : "Great Blue Heron";
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
            case 3:
                return "<col=ffff00>Ric</col>";
            case 2:
            case 4:
            case 6:
                return "<col=ffff00>Phoenix</col>";
            case 5:
                return "<col=ffff00>Lil' Creator</col>";
            case 7:
                return "<col=ffff00>Tangleroot</col>";
            case 8:
            case 9:
            case 12:
            case 11:
            case 13:
            case 14:
            case 15:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 10:
                return "<col=ffff00>Rift Guardian</col>";
            case 16:
            case 17:
            case 19:
                return "<col=ffff00>Rock Golem</col>";
            case 18:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            default:
                return formIndex == 0
                        ? "<col=ffff00>Heron</col>"
                        : "<col=ffff00>Great Blue Heron</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return "King of the north face. Self-appointed.";
            case 2:
                return "A phoenix who chose the colder rebirth.";
            case 3:
                return "Surveys his frosty domain.";
            case 4:
                return "A blue flame above a white peak.";
            case 5:
                return "Trying out a more peaceful career.";
            case 6:
                return "An ancient bird with a saint's patience.";
            case 7:
                return "Has been here longer than Abbot Langley";
            case 8:
                switch (formIndex)
                {
                    case 0: return "Couldn't stand still if it tried.";
                    case 1: return "Drafting plans in the underbrush.";
                    default: return "Slips between trunks unseen.";
                }
            case 9:
                switch (formIndex)
                {
                    case 0: return "Vaulting trees like a professional.";
                    case 1: return "Marks the better trees. For later.";
                    default: return "Patiently waiting for you to look away.";
                }
            case 10:
                return "Wards an empty corner. Ignores the sea shanties.";
            case 11:
                switch (formIndex)
                {
                    case 0: return "Knows every loose branch by heart.";
                    case 1: return "Eyeing the older trees with quiet ambition.";
                    default: return "A familiar shadow between the trees.";
                }
            case 12:
                switch (formIndex)
                {
                    case 0: return "Outpaces anything wearing armour.";
                    case 1: return "A patient surveyor of the local timber.";
                    default: return "Quietly auditing every traveller.";
                }
            case 13:
                switch (formIndex)
                {
                    case 0: return "Could run faster than you, if it wanted to.";
                    case 1: return "Has plans for the wood here.";
                    default: return "A quiet professional.";
                }
            case 14:
                switch (formIndex)
                {
                    case 0: return "Pacing the mountain edge like clockwork.";
                    case 1: return "Surveys the timber here.";
                    default: return "Watching the crowd come and go. Picking favourites.";
                }
            case 15:
                switch (formIndex)
                {
                    case 0: return "Slips between the trees like it's been here forever.";
                    case 1: return "Marks the better trees.";
                    default: return "Lifts what isn't watched closely.";
                }
            case 16:
                return "Often mistaken for the day's haul.";
            case 17:
                return "Older than the cracks around him. Maybe.";
            case 18:
                switch (formIndex)
                {
                    case 0: return "Faster than any barbarian coming its way.";
                    case 1: return "Has firm opinions on barbarian carpentry.";
                    default: return "A thief among brawlers.";
                }
            case 19:
                return "A fisher with no gear required.";
            default:
                return formIndex == 0
                        ? "Stalking the river shallows for fish."
                        : "Has caught more fish than you will ever catch.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 6;
            case 2: return 1;
            case 3: return 6;
            case 4: return 1;
            case 5: return 4;
            case 6: return 3;
            case 7: return 4;
            case 8: return 14;
            case 9: return 14;
            case 10: return 6;
            case 11: return 8;
            case 12: return 14;
            case 13: return 8;
            case 14: return 5;
            case 15: return 5;
            case 16: return 6;
            case 17: return 4;
            case 18: return 12;
            case 19: return 2;
            default: return 2;
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
            case 1:
            case 2: return ZONE1_NORTH;
            case 3:
            case 4: return ZONE2_SOUTH;
            case 5:
            case 6: return ZONE3_MONASTERY;
            case 7: return ZONE4_MONASTERY_GARDEN;
            case 8: return ZONE5_SOUTH_FOREST;
            case 9: return ZONE6_PATH_SOUTH_EAST;
            case 10: return ZONE7_WARDING;
            case 11: return ZONE8_WEST_OF_ICE;
            case 12: return ZONE9_WEST_PRE_EDGE;
            case 13: return ZONE10_SW_EDGE;
            case 14: return ZONE11_SE_EDGE;
            case 15: return ZONE12_NE_EDGE;
            case 16: return ZONE13_DWARF_AREA;
            case 17: return ZONE14_RUINS_ENTRANCE;
            case 18: return ZONE18_NORTH_BARBARIAN;
            case 19: return ZONE19_BARBARIAN_VILLAGE;
            default: return ZONE20_BARBARIAN_FISHING;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 12) return FORBIDDEN_ZONE12;
        return new int[0][][];
    }

    // Zone 1/2 - Ice Mountain (North)
    private static final int[][] ZONE1_NORTH = {
            { 3002, 3504 }, { 3006, 3502 }, { 3007, 3502 }, { 3007, 3504 },
            { 3005, 3505 }, { 3005, 3506 }, { 3007, 3506 }, { 3008, 3507 },
            { 3010, 3506 }, { 3010, 3504 }, { 3011, 3504 }, { 3011, 3505 },
            { 3013, 3507 }, { 3014, 3507 }, { 3017, 3510 }, { 3021, 3506 },
            { 3020, 3504 }, { 3021, 3503 }, { 3021, 3502 }, { 3020, 3500 },
            { 3020, 3498 }, { 3020, 3497 }, { 3019, 3495 }, { 3017, 3496 },
            { 3015, 3496 }, { 3014, 3495 }, { 3014, 3491 }, { 3012, 3489 },
            { 3012, 3486 }, { 3010, 3486 }, { 3010, 3487 }, { 3007, 3488 },
            { 3005, 3490 }, { 3007, 3493 }, { 3009, 3493 }, { 3011, 3492 },
            { 3012, 3493 }, { 3012, 3494 }, { 3013, 3496 }, { 3013, 3497 },
            { 3015, 3499 }, { 3016, 3499 }, { 3016, 3500 }, { 3014, 3500 },
            { 3011, 3498 }, { 3012, 3496 }, { 3010, 3496 }, { 3010, 3497 },
            { 3008, 3497 }, { 3007, 3496 }, { 3006, 3494 }, { 3005, 3494 },
            { 3004, 3495 }, { 3004, 3498 }, { 3004, 3500 }, { 3002, 3504 }
    };

    // Zone 3/4 - Ice Mountain (South)
    private static final int[][] ZONE2_SOUTH = {
            { 3010, 3485 }, { 3012, 3484 }, { 3012, 3481 }, { 3015, 3478 },
            { 3016, 3475 }, { 3017, 3476 }, { 3020, 3477 }, { 3022, 3476 },
            { 3024, 3477 }, { 3025, 3476 }, { 3027, 3475 }, { 3028, 3475 },
            { 3028, 3472 }, { 3026, 3472 }, { 3025, 3470 }, { 3024, 3473 },
            { 3022, 3473 }, { 3022, 3468 }, { 3021, 3468 }, { 3019, 3469 },
            { 3018, 3470 }, { 3017, 3470 }, { 3016, 3468 }, { 3013, 3468 },
            { 3013, 3470 }, { 3011, 3472 }, { 3009, 3471 }, { 3009, 3467 },
            { 3011, 3467 }, { 3012, 3466 }, { 3012, 3465 }, { 3010, 3466 },
            { 3008, 3466 }, { 3006, 3466 }, { 3006, 3467 }, { 3005, 3468 },
            { 2998, 3468 }, { 2996, 3467 }, { 2995, 3468 }, { 2996, 3470 },
            { 2997, 3472 }, { 2997, 3473 }, { 2999, 3474 }, { 3001, 3475 },
            { 3001, 3476 }, { 3000, 3476 }, { 3000, 3478 }, { 3001, 3478 },
            { 3002, 3479 }, { 3003, 3480 }, { 3003, 3482 }, { 3002, 3484 },
            { 3006, 3487 }, { 3007, 3486 }, { 3007, 3483 }, { 3010, 3485 }
    };

    // Zone 5/6 - Monastery
    private static final int[][] ZONE3_MONASTERY = {
            { 3046, 3509 }, { 3058, 3509 }, { 3058, 3501 }, { 3060, 3501 },
            { 3062, 3499 }, { 3062, 3483 }, { 3059, 3481 }, { 3053, 3481 },
            { 3053, 3480 }, { 3053, 3472 }, { 3051, 3472 }, { 3051, 3480 },
            { 3050, 3481 }, { 3044, 3481 }, { 3042, 3483 }, { 3042, 3499 },
            { 3044, 3501 }, { 3046, 3501 }, { 3046, 3509 }
    };

    // Zone 7 - Monastery Garden
    private static final int[][] ZONE4_MONASTERY_GARDEN = {
            { 3046, 3509 }, { 3058, 3509 }, { 3058, 3501 }, { 3046, 3501 },
            { 3046, 3509 }
    };

    // Zone 8 - South Monastery (Forest)
    private static final int[][] ZONE5_SOUTH_FOREST = {
            { 3054, 3471 }, { 3054, 3475 }, { 3058, 3476 }, { 3061, 3475 },
            { 3062, 3470 }, { 3059, 3467 }, { 3060, 3462 }, { 3062, 3458 },
            { 3063, 3455 }, { 3065, 3450 }, { 3067, 3448 }, { 3068, 3443 },
            { 3069, 3438 }, { 3069, 3431 }, { 3068, 3427 }, { 3068, 3423 },
            { 3066, 3421 }, { 3063, 3420 }, { 3057, 3417 }, { 3055, 3415 },
            { 3050, 3415 }, { 3041, 3424 }, { 3041, 3426 }, { 3038, 3428 },
            { 3037, 3430 }, { 3032, 3431 }, { 3027, 3429 }, { 3026, 3428 },
            { 3025, 3428 }, { 3021, 3427 }, { 3021, 3428 }, { 3019, 3430 },
            { 3019, 3434 }, { 3021, 3437 }, { 3024, 3440 }, { 3027, 3443 },
            { 3029, 3446 }, { 3031, 3448 }, { 3033, 3451 }, { 3034, 3455 },
            { 3035, 3459 }, { 3039, 3464 }, { 3040, 3466 }, { 3045, 3468 },
            { 3044, 3473 }, { 3051, 3468 }, { 3054, 3471 }
    };

    // Zone 9 - Path + South Forest (East)
    private static final int[][] ZONE6_PATH_SOUTH_EAST = {
            { 3065, 3421 }, { 3068, 3415 }, { 3069, 3408 }, { 3070, 3404 },
            { 3071, 3398 }, { 3071, 3394 }, { 3064, 3392 }, { 3053, 3391 },
            { 3049, 3393 }, { 3049, 3402 }, { 3041, 3405 }, { 3034, 3405 },
            { 3031, 3402 }, { 3030, 3396 }, { 3027, 3391 }, { 3024, 3392 },
            { 3019, 3394 }, { 3011, 3396 }, { 3004, 3397 }, { 2999, 3396 },
            { 2994, 3396 }, { 2989, 3396 }, { 2969, 3397 }, { 2970, 3407 },
            { 2971, 3412 }, { 2976, 3416 }, { 2983, 3419 }, { 2984, 3423 },
            { 2987, 3426 }, { 2988, 3429 }, { 2990, 3432 }, { 2992, 3435 },
            { 2995, 3436 }, { 3006, 3436 }, { 3009, 3435 }, { 3017, 3434 },
            { 3018, 3430 }, { 3019, 3429 }, { 3026, 3429 }, { 3031, 3431 },
            { 3035, 3430 }, { 3039, 3426 }, { 3041, 3423 }, { 3045, 3419 },
            { 3046, 3416 }, { 3048, 3413 }, { 3051, 3413 }, { 3056, 3415 },
            { 3065, 3421 }
    };

    // Zone 10 - Warding Area
    private static final int[][] ZONE7_WARDING = {
            { 3034, 3404 }, { 3037, 3404 }, { 3038, 3405 }, { 3039, 3405 },
            { 3040, 3404 }, { 3042, 3404 }, { 3042, 3403 }, { 3046, 3403 },
            { 3047, 3402 }, { 3048, 3402 }, { 3048, 3401 }, { 3049, 3401 },
            { 3049, 3396 }, { 3046, 3393 }, { 3042, 3393 }, { 3040, 3395 },
            { 3038, 3395 }, { 3036, 3394 }, { 3035, 3394 }, { 3034, 3395 },
            { 3033, 3395 }, { 3031, 3397 }, { 3031, 3400 }, { 3034, 3403 },
            { 3034, 3404 }
    };

    // Zone 11 - West of Ice Mountain (North of Falador)
    private static final int[][] ZONE8_WEST_OF_ICE = {
            { 2946, 3414 }, { 2946, 3408 }, { 2945, 3404 }, { 2943, 3401 },
            { 2944, 3397 }, { 2949, 3396 }, { 2953, 3395 }, { 2960, 3396 },
            { 2968, 3396 }, { 2969, 3404 }, { 2969, 3408 }, { 2976, 3414 },
            { 2980, 3418 }, { 2982, 3421 }, { 2983, 3423 }, { 2981, 3425 },
            { 2979, 3428 }, { 2974, 3430 }, { 2973, 3431 }, { 2969, 3432 },
            { 2966, 3435 }, { 2962, 3437 }, { 2960, 3438 }, { 2956, 3439 },
            { 2955, 3440 }, { 2948, 3442 }, { 2948, 3454 }, { 2952, 3461 },
            { 2953, 3468 }, { 2951, 3473 }, { 2946, 3477 }, { 2945, 3490 },
            { 2940, 3503 }, { 2939, 3504 }, { 2937, 3500 }, { 2937, 3496 },
            { 2939, 3493 }, { 2940, 3476 }, { 2943, 3471 }, { 2943, 3455 },
            { 2940, 3452 }, { 2942, 3449 }, { 2946, 3445 }, { 2946, 3414 }
    };

    // Zone 12 - West Ice Mountain (Forest Pre-Edge)
    private static final int[][] ZONE9_WEST_PRE_EDGE = {
            { 2973, 3508 }, { 2977, 3512 }, { 2985, 3513 }, { 2987, 3512 },
            { 2988, 3510 }, { 2988, 3506 }, { 2987, 3502 }, { 2987, 3500 },
            { 2988, 3495 }, { 2985, 3488 }, { 2983, 3485 }, { 2979, 3481 },
            { 2977, 3477 }, { 2975, 3476 }, { 2975, 3465 }, { 2977, 3460 },
            { 2979, 3458 }, { 2982, 3452 }, { 2985, 3447 }, { 2987, 3443 },
            { 2989, 3438 }, { 2983, 3431 }, { 2977, 3436 }, { 2973, 3439 },
            { 2964, 3443 }, { 2963, 3447 }, { 2963, 3452 }, { 2962, 3456 },
            { 2959, 3458 }, { 2961, 3465 }, { 2961, 3470 }, { 2961, 3473 },
            { 2963, 3483 }, { 2963, 3488 }, { 2965, 3491 }, { 2968, 3498 },
            { 2973, 3506 }, { 2973, 3508 }
    };

    // Zone 12 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE12 = {
            // Quest Building
            {
                    { 2964, 3471 }, { 2966, 3473 }, { 2970, 3473 }, { 2972, 3471 },
                    { 2972, 3467 }, { 2971, 3466 }, { 2971, 3460 }, { 2966, 3460 },
                    { 2965, 3460 }, { 2964, 3466 }, { 2964, 3471 }
            }
    };

    // Zone 13 - Ice Mountain (S/W Edge)
    private static final int[][] ZONE10_SW_EDGE = {
            { 2996, 3458 }, { 3006, 3456 }, { 3007, 3447 }, { 3005, 3443 },
            { 3000, 3443 }, { 2995, 3445 }, { 2993, 3452 }, { 2988, 3455 },
            { 2987, 3457 }, { 2984, 3460 }, { 2981, 3462 }, { 2981, 3464 },
            { 2980, 3466 }, { 2980, 3472 }, { 2983, 3475 }, { 2988, 3481 },
            { 2990, 3483 }, { 2990, 3488 }, { 2993, 3489 }, { 2996, 3486 },
            { 2995, 3480 }, { 2992, 3476 }, { 2988, 3473 }, { 2987, 3468 },
            { 2987, 3463 }, { 2988, 3461 }, { 2988, 3459 }, { 2990, 3457 },
            { 2994, 3457 }, { 2995, 3457 }, { 2995, 3460 }, { 2996, 3458 }
    };

    // Zone 14 - Ice Mountain (S/E Edge)
    private static final int[][] ZONE11_SE_EDGE = {
            { 3016, 3463 }, { 3009, 3458 }, { 3009, 3456 }, { 3024, 3456 },
            { 3026, 3456 }, { 3026, 3452 }, { 3028, 3454 }, { 3028, 3457 },
            { 3031, 3461 }, { 3033, 3462 }, { 3033, 3465 }, { 3034, 3467 },
            { 3035, 3472 }, { 3033, 3475 }, { 3032, 3477 }, { 3031, 3478 },
            { 3031, 3475 }, { 3031, 3469 }, { 3028, 3468 }, { 3026, 3468 },
            { 3022, 3463 }, { 3016, 3463 }
    };

    // Zone 15 - Ice Mountain (N/E Edge)
    private static final int[][] ZONE12_NE_EDGE = {
            { 3026, 3503 }, { 3030, 3503 }, { 3033, 3505 }, { 3034, 3509 },
            { 3032, 3511 }, { 3033, 3515 }, { 3035, 3519 }, { 3037, 3518 },
            { 3037, 3511 }, { 3038, 3506 }, { 3037, 3503 }, { 3034, 3499 },
            { 3029, 3495 }, { 3025, 3491 }, { 3026, 3482 }, { 3029, 3480 },
            { 3030, 3478 }, { 3021, 3479 }, { 3018, 3482 }, { 3016, 3482 },
            { 3016, 3491 }, { 3021, 3493 }, { 3023, 3496 }, { 3023, 3499 },
            { 3026, 3503 }
    };

    // Zone 16 - Dwarf Area
    private static final int[][] ZONE13_DWARF_AREA = {
            { 3004, 3452 }, { 3005, 3457 }, { 3009, 3460 }, { 3019, 3460 },
            { 3024, 3456 }, { 3024, 3448 }, { 3020, 3444 }, { 3016, 3443 },
            { 3011, 3445 }, { 3005, 3445 }, { 3004, 3452 }
    };

    // Zone 17 - Ice Mountain (Ruins Entrance)
    private static final int[][] ZONE14_RUINS_ENTRANCE = {
            { 2999, 3507 }, { 2990, 3500 }, { 2993, 3498 }, { 2991, 3495 },
            { 2992, 3494 }, { 2992, 3491 }, { 2990, 3488 }, { 2991, 3486 },
            { 2997, 3487 }, { 2999, 3490 }, { 3000, 3498 }, { 3000, 3500 },
            { 3000, 3502 }, { 2999, 3507 }
    };

    // Zone 18 - North Barbarian Village
    private static final int[][] ZONE18_NORTH_BARBARIAN = {
            { 3069, 3463 }, { 3081, 3464 }, { 3088, 3462 }, { 3091, 3462 },
            { 3096, 3462 }, { 3098, 3458 }, { 3098, 3449 }, { 3099, 3443 },
            { 3100, 3439 }, { 3102, 3437 }, { 3101, 3433 }, { 3099, 3435 },
            { 3094, 3435 }, { 3093, 3430 }, { 3090, 3431 }, { 3089, 3438 },
            { 3088, 3439 }, { 3087, 3445 }, { 3084, 3449 }, { 3076, 3451 },
            { 3073, 3451 }, { 3072, 3457 }, { 3069, 3463 }
    };

    // Zone 19 - Barbarian Village
    private static final int[][] ZONE19_BARBARIAN_VILLAGE = {
            { 3077, 3426 }, { 3083, 3426 }, { 3087, 3425 }, { 3090, 3421 },
            { 3088, 3414 }, { 3086, 3413 }, { 3080, 3414 }, { 3075, 3418 },
            { 3073, 3424 }, { 3076, 3424 }, { 3077, 3420 }, { 3080, 3417 },
            { 3082, 3416 }, { 3084, 3418 }, { 3085, 3420 }, { 3085, 3422 },
            { 3081, 3423 }, { 3077, 3425 }, { 3077, 3426 }
    };

    // Zone 20 - Barbarian Fishing Edge
    private static final int[][] ZONE20_BARBARIAN_FISHING = {
            { 3099, 3441 }, { 3102, 3436 }, { 3103, 3432 }, { 3110, 3430 },
            { 3114, 3432 }, { 3114, 3435 }, { 3114, 3437 }, { 3110, 3441 },
            { 3105, 3443 }, { 3101, 3444 }, { 3099, 3441 }
    };
}
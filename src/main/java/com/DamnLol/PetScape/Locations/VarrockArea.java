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


public class VarrockArea extends RoamingArea
{
    private final int zone;

    private VarrockArea(int zone) { this.zone = zone; }

    public static VarrockArea zone1() { return new VarrockArea(1); }
    public static VarrockArea zone2() { return new VarrockArea(2); }
    public static VarrockArea zone3() { return new VarrockArea(3); }
    public static VarrockArea zone4() { return new VarrockArea(4); }
    public static VarrockArea zone5() { return new VarrockArea(5); }
    public static VarrockArea zone6() { return new VarrockArea(6); }
    public static VarrockArea zone7() { return new VarrockArea(7); }
    public static VarrockArea zone8() { return new VarrockArea(8); }

    @Override
    public String getAreaId() { return "varrock_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{
                    NpcID.BLOODHOUND, NpcID.BEAVER,
                    NpcID.ROCKY, NpcID.GIANT_SQUIRREL
            };
            case 2: return new int[]{
                    NpcID.BLOODHOUND, NpcID.GIANT_SQUIRREL, NpcID.BABY_CHINCHOMPA,
                    NpcID.BEAVER, NpcID.ROCKY
            };
            case 3: return new int[]{ NpcID.SCURRY };
            case 4: return new int[]{ NpcID.BLOODHOUND, NpcID.GIANT_SQUIRREL, NpcID.ROCKY };
            case 5: return new int[]{ NpcID.QUETZIN, NpcID.GIANT_SQUIRREL };
            case 7: return new int[]{ NpcID.TANGLEROOT };
            case 8: return new int[]{
                    NpcID.BLOODHOUND, NpcID.GIANT_SQUIRREL, NpcID.ROCKY,
                    NpcID.BEAVER, NpcID.BABY_CHINCHOMPA
            };
            default: return new int[]{ NpcID.TANGLEROOT };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying()
    {
        return zone == 5;
    }

    @Override
    public int getWanderMinDist()
    {
        switch (zone)
        {
            case 3: return 3;
            case 6: return 4;
            default: return super.getWanderMinDist();
        }
    }

    @Override
    public int getMinSiblingSeparation()
    {
        switch (zone)
        {
            case 3:
            case 6: return 2;
            default: return super.getMinSiblingSeparation();
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 2 Bloodhound(0), 4 Beaver(1), 4 Rocky(2), 6 Giant Squirrel(3)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 6) return 1;
                if (spawnIndex < 10) return 2;
                return 3;
            case 2:
                // 6 Bloodhound(0), 12 Giant Squirrel(1), 8 Baby Chinchompa(2), 6 Beaver(3), 6 Rocky(4)
                if (spawnIndex < 6)  return 0;
                if (spawnIndex < 18) return 1;
                if (spawnIndex < 26) return 2;
                if (spawnIndex < 32) return 3;
                return 4;
            case 4:
                // 8 Bloodhound(0), 4 Giant Squirrel(1), 1 Rocky(2)
                if (spawnIndex < 8)  return 0;
                if (spawnIndex < 12) return 1;
                return 2;
            case 5:
                // 4 Quetzin(0), 4 Giant Squirrel(1)
                return spawnIndex < 4 ? 0 : 1;
            case 8:
                // 1 Bloodhound(0), 6 Giant Squirrel(1), 2 Rocky(2), 2 Beaver(3), 4 Baby Chinchompa(4)
                if (spawnIndex < 1)  return 0;
                if (spawnIndex < 7)  return 1;
                if (spawnIndex < 9)  return 2;
                if (spawnIndex < 11) return 3;
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
                    if (i < 2) names[i] = "Bloodhound";
                    else if (i < 6) names[i] = "Beaver";
                    else if (i < 10) names[i] = "Rocky";
                    else names[i] = "Giant Squirrel";
                }
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Bloodhound";
                    else if (i < 18) names[i] = "Giant Squirrel";
                    else if (i < 26) names[i] = "Baby Chinchompa";
                    else if (i < 32) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 3:
                Arrays.fill(names, "Scurry");
                break;
            case 4:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Bloodhound";
                    else if (i < 12) names[i] = "Giant Squirrel";
                    else names[i] = "Rocky";
                }
                break;
            case 5:
                for (int i = 0; i < n; i++)
                    names[i] = i < 4 ? "Quetzin" : "Giant Squirrel";
                break;
            case 7:
                Arrays.fill(names, "Tangleroot");
                break;
            case 8:
                for (int i = 0; i < n; i++)
                {
                    if (i < 1) names[i] = "Bloodhound";
                    else if (i < 7) names[i] = "Giant Squirrel";
                    else if (i < 9) names[i] = "Rocky";
                    else if (i < 11) names[i] = "Beaver";
                    else names[i] = "Baby Chinchompa";
                }
                break;
            default:
                Arrays.fill(names, "Tangleroot");
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
                    case 0: return "<col=ffff00>Bloodhound</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    case 2: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Giant Squirrel</col>";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bloodhound</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Baby Chinchompa</col>";
                    case 3: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 3:
                return "<col=ffff00>Scurry</col>";
            case 4:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bloodhound</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 5:
                return formIndex == 0
                        ? "<col=ffff00>Quetzin</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
            case 7:
                return "<col=ffff00>Tangleroot</col>";
            case 8:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bloodhound</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Rocky</col>";
                    case 3: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Baby Chinchompa</col>";
                }
            default:
                return "<col=ffff00>Tangleroot</col>";
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
                    case 0: return "No merch shall be flipped without due process.";
                    case 1: return "Would like to audit the log stockpile, please.";
                    case 2: return "Taking a five-finger discount on the market price.";
                    default: return "Faster than the trading clerk, and twice as twitchy.";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "Hasn't lost a scent since the third age.";
                    case 1: return "Never takes the stairs when a wall will do.";
                    case 2: return "Fluffy, fierce, and a little unstable.";
                    case 3: return "Inspecting every fencepost in town.";
                    default: return "Suspiciously innocent. Check your coin pouch.";
                }
            case 3:
                return "Swears he used to train four students down there.";
            case 4:
                switch (formIndex)
                {
                    case 0: return "Sworn to serve King, kingdom, and kibble.";
                    case 1: return "Scouting the next rooftop with great confidence.";
                    default: return "Eyeing pockets with great professional interest.";
                }
            case 5:
                return formIndex == 0
                        ? "Tropical by birth, dignified by choice."
                        : "Takes the scenic route at alarming speeds.";
            case 7:
                return "Rooted in conversation. Literally.";
            case 8:
                switch (formIndex)
                {
                    case 0: return "Stationed at the city gate.";
                    case 1: return "One leap away from leaving town entirely.";
                    case 2: return "Guards the border, one pebble at a time.";
                    case 3: return "A city beaver with country dreams.";
                    default: return "Every hug is a risk assessment.";
                }
            default:
                return "Supervising the crops with quiet authority.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 16;
            case 2: return 38;
            case 3: return 3;
            case 4: return 13;
            case 5: return 8;
            case 7: return 2;
            case 8: return 15;
            default: return 4;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return zone == 1 ? 0 : 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_GE;
            case 2: return ZONE2_CITY;
            case 3: return ZONE3_SEWER;
            case 4: return ZONE4_CASTLE;
            case 5: return ZONE5_SOUTH_EAST;
            case 7: return ZONE7_SPIRIT_TREE;
            case 8: return ZONE8_BORDER;
            default: return ZONE6_FARMING;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        switch (zone)
        {
            case 1: return FORBIDDEN_ZONE1;
            case 2: return FORBIDDEN_ZONE2;
            case 4: return FORBIDDEN_ZONE4;
            default: return new int[0][][];
        }
    }

    //  Zone 1 - Grand Exchange
    private static final int[][] ZONE1_GE = {
            { 3142, 3516 }, { 3150, 3516 }, { 3158, 3516 }, { 3161, 3514 },
            { 3168, 3514 }, { 3171, 3517 }, { 3181, 3517 }, { 3189, 3517 },
            { 3197, 3508 }, { 3197, 3506 }, { 3195, 3504 }, { 3195, 3503 },
            { 3189, 3497 }, { 3189, 3492 }, { 3191, 3492 }, { 3191, 3494 },
            { 3194, 3495 }, { 3197, 3495 }, { 3198, 3493 }, { 3199, 3491 },
            { 3199, 3489 }, { 3198, 3487 }, { 3197, 3486 }, { 3194, 3485 },
            { 3192, 3486 }, { 3191, 3487 }, { 3191, 3488 }, { 3189, 3488 },
            { 3189, 3481 }, { 3184, 3481 }, { 3181, 3478 }, { 3182, 3476 },
            { 3179, 3473 }, { 3176, 3474 }, { 3173, 3471 }, { 3173, 3469 },
            { 3153, 3469 }, { 3153, 3472 }, { 3151, 3474 }, { 3147, 3474 },
            { 3145, 3476 }, { 3145, 3480 }, { 3143, 3482 }, { 3140, 3482 },
            { 3142, 3485 }, { 3142, 3492 }, { 3139, 3495 }, { 3139, 3513 },
            { 3142, 3516 }
    };

    // Zone 1 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE1 = {
            // Grand Exchange Hub
            {
                    { 3164, 3494 }, { 3166, 3494 }, { 3168, 3493 }, { 3169, 3491 },
                    { 3169, 3488 }, { 3167, 3486 }, { 3163, 3486 }, { 3161, 3488 },
                    { 3161, 3491 }, { 3162, 3493 }, { 3164, 3494 }
            }
    };

    //  Zone 2 - Varrock City
    private static final int[][] ZONE2_CITY = {
            { 3190, 3457 }, { 3191, 3448 }, { 3191, 3433 }, { 3180, 3433 },
            { 3174, 3425 }, { 3177, 3425 }, { 3177, 3419 }, { 3175, 3417 },
            { 3174, 3417 }, { 3174, 3401 }, { 3176, 3403 }, { 3182, 3403 },
            { 3182, 3400 }, { 3183, 3399 }, { 3202, 3399 }, { 3202, 3404 },
            { 3204, 3404 }, { 3205, 3405 }, { 3206, 3405 }, { 3207, 3404 },
            { 3209, 3404 }, { 3210, 3403 }, { 3210, 3402 }, { 3209, 3401 },
            { 3209, 3400 }, { 3209, 3398 }, { 3210, 3397 }, { 3210, 3396 },
            { 3209, 3395 }, { 3207, 3395 }, { 3205, 3394 }, { 3204, 3394 },
            { 3203, 3395 }, { 3201, 3395 }, { 3196, 3390 }, { 3196, 3382 },
            { 3202, 3382 }, { 3230, 3382 }, { 3230, 3389 }, { 3225, 3389 },
            { 3225, 3384 }, { 3218, 3384 }, { 3218, 3389 }, { 3230, 3389 },
            { 3230, 3387 }, { 3239, 3387 }, { 3239, 3382 }, { 3242, 3382 },
            { 3242, 3386 }, { 3255, 3386 }, { 3255, 3391 }, { 3255, 3396 },
            { 3259, 3396 }, { 3259, 3404 }, { 3264, 3404 }, { 3264, 3408 },
            { 3272, 3408 }, { 3273, 3409 }, { 3273, 3410 }, { 3273, 3426 },
            { 3273, 3436 }, { 3269, 3436 }, { 3269, 3452 }, { 3269, 3463 },
            { 3262, 3471 }, { 3262, 3484 }, { 3262, 3491 }, { 3261, 3492 },
            { 3254, 3492 }, { 3251, 3495 }, { 3251, 3501 }, { 3249, 3501 },
            { 3249, 3502 }, { 3243, 3502 }, { 3243, 3499 }, { 3238, 3499 },
            { 3238, 3501 }, { 3236, 3501 }, { 3235, 3491 }, { 3242, 3491 },
            { 3242, 3483 }, { 3235, 3483 }, { 3235, 3468 }, { 3242, 3467 },
            { 3243, 3465 }, { 3244, 3461 }, { 3244, 3458 }, { 3243, 3456 },
            { 3243, 3455 }, { 3234, 3455 }, { 3234, 3453 }, { 3220, 3439 },
            { 3214, 3439 }, { 3208, 3439 }, { 3190, 3457 }
    };

    // Zone 2 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE2 = {
            // Museum
            {
                    { 3252, 3456 }, { 3252, 3442 }, { 3268, 3442 }, { 3268, 3460 },
                    { 3265, 3460 }, { 3265, 3456 }, { 3252, 3456 }
            },

            // East Bank
            {
                    { 3252, 3425 }, { 3258, 3425 }, { 3258, 3416 }, { 3250, 3416 },
                    { 3250, 3425 }, { 3252, 3425 }
            },

            // Quest/Event Pub
            {
                    { 3216, 3397 }, { 3216, 3394 }, { 3217, 3393 }, { 3221, 3393 },
                    { 3222, 3394 }, { 3225, 3394 }, { 3226, 3393 }, { 3233, 3393 },
                    { 3233, 3395 }, { 3234, 3396 }, { 3234, 3397 }, { 3233, 3398 },
                    { 3233, 3403 }, { 3222, 3403 }, { 3222, 3404 }, { 3221, 3405 },
                    { 3220, 3405 }, { 3219, 3404 }, { 3219, 3403 }, { 3217, 3403 },
                    { 3217, 3397 }, { 3216, 3397 }
            }
    };

    //  Zone 3 - Sewer Entrance
    private static final int[][] ZONE3_SEWER = {
            { 3235, 3467 }, { 3235, 3464 }, { 3235, 3456 }, { 3242, 3456 },
            { 3243, 3459 }, { 3243, 3461 }, { 3242, 3464 }, { 3241, 3466 },
            { 3235, 3467 }, { 3235, 3464 }, { 3235, 3467 }
    };

    //  Zone 4 - Castle + Yard
    private static final int[][] ZONE4_CASTLE = {
            { 3234, 3468 }, { 3234, 3464 }, { 3233, 3464 }, { 3234, 3456 },
            { 3233, 3455 }, { 3232, 3454 }, { 3219, 3440 }, { 3209, 3440 },
            { 3201, 3448 }, { 3191, 3458 }, { 3187, 3458 }, { 3186, 3459 },
            { 3186, 3461 }, { 3188, 3463 }, { 3188, 3475 }, { 3191, 3478 },
            { 3191, 3488 }, { 3191, 3496 }, { 3197, 3502 }, { 3197, 3503 },
            { 3201, 3507 }, { 3227, 3507 }, { 3224, 3504 }, { 3231, 3498 },
            { 3234, 3501 }, { 3234, 3491 }, { 3234, 3483 }, { 3234, 3468 }
    };

    // Zone 4 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE4 = {
            // Quest - King Room + Garden
            {
                    { 3219, 3471 }, { 3221, 3469 }, { 3225, 3469 }, { 3225, 3470 },
                    { 3226, 3471 }, { 3234, 3471 }, { 3234, 3491 }, { 3227, 3491 },
                    { 3227, 3479 }, { 3225, 3479 }, { 3220, 3479 }, { 3220, 3474 },
                    { 3219, 3473 }, { 3219, 3471 }
            }
    };

    //  Zone 5 - Varrock S/E
    private static final int[][] ZONE5_SOUTH_EAST = {
            { 3264, 3407 }, { 3264, 3380 }, { 3268, 3380 }, { 3268, 3385 },
            { 3273, 3385 }, { 3275, 3387 }, { 3275, 3388 }, { 3266, 3388 },
            { 3266, 3395 }, { 3270, 3395 }, { 3273, 3392 }, { 3275, 3392 },
            { 3275, 3388 }, { 3275, 3387 }, { 3274, 3386 }, { 3274, 3377 },
            { 3275, 3377 }, { 3276, 3378 }, { 3276, 3378 }, { 3277, 3377 },
            { 3286, 3377 }, { 3289, 3380 }, { 3289, 3383 }, { 3288, 3384 },
            { 3288, 3389 }, { 3283, 3394 }, { 3280, 3394 }, { 3279, 3395 },
            { 3278, 3395 }, { 3278, 3407 }, { 3276, 3407 }, { 3274, 3409 },
            { 3273, 3408 }, { 3265, 3408 }, { 3264, 3408 }, { 3264, 3407 }
    };

    //  Zone 6 - Varrock Palace Farming Patch
    private static final int[][] ZONE6_FARMING = {
            { 3233, 3466 }, { 3232, 3467 }, { 3230, 3469 }, { 3228, 3469 },
            { 3226, 3467 }, { 3226, 3466 }, { 3226, 3464 }, { 3225, 3462 },
            { 3226, 3461 }, { 3225, 3459 }, { 3224, 3457 }, { 3221, 3456 },
            { 3220, 3455 }, { 3220, 3452 }, { 3220, 3451 }, { 3221, 3449 },
            { 3225, 3448 }, { 3227, 3449 }, { 3229, 3451 }, { 3232, 3454 },
            { 3232, 3455 }, { 3233, 3456 }, { 3233, 3456 }, { 3233, 3464 },
            { 3233, 3466 }
    };

    //  Zone 7 - Grand Exchange Spirit Tree
    private static final int[][] ZONE7_SPIRIT_TREE = {
            { 3168, 3514 }, { 3168, 3513 }, { 3169, 3511 }, { 3172, 3508 },
            { 3176, 3506 }, { 3180, 3503 }, { 3182, 3502 }, { 3184, 3499 },
            { 3186, 3496 }, { 3186, 3494 }, { 3188, 3493 }, { 3190, 3492 },
            { 3190, 3497 }, { 3196, 3503 }, { 3196, 3504 }, { 3198, 3506 },
            { 3198, 3508 }, { 3189, 3517 }, { 3171, 3517 }, { 3168, 3514 }
    };

    //  Zone 8 - Grand Exchange/Varrock Border
    private static final int[][] ZONE8_BORDER = {
            { 3146, 3466 }, { 3146, 3455 }, { 3149, 3452 }, { 3149, 3447 },
            { 3150, 3442 }, { 3161, 3442 }, { 3164, 3440 }, { 3165, 3439 },
            { 3165, 3434 }, { 3169, 3427 }, { 3170, 3427 }, { 3169, 3423 },
            { 3170, 3420 }, { 3172, 3418 }, { 3174, 3418 }, { 3174, 3425 },
            { 3180, 3433 }, { 3180, 3448 }, { 3184, 3448 }, { 3190, 3457 },
            { 3186, 3457 }, { 3185, 3458 }, { 3185, 3462 }, { 3187, 3464 },
            { 3187, 3467 }, { 3167, 3467 }, { 3146, 3467 }, { 3146, 3466 }
    };
}
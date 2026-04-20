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


public class CanifisRoamingArea extends RoamingArea
{
    private final int zone;

    private CanifisRoamingArea(int zone) { this.zone = zone; }

    public static CanifisRoamingArea zone1() { return new CanifisRoamingArea(1); }
    public static CanifisRoamingArea zone2() { return new CanifisRoamingArea(2); }
    public static CanifisRoamingArea zone3() { return new CanifisRoamingArea(3); }
    public static CanifisRoamingArea zone4() { return new CanifisRoamingArea(4); }
    public static CanifisRoamingArea zone5() { return new CanifisRoamingArea(5); }
    public static CanifisRoamingArea zone6() { return new CanifisRoamingArea(6); }
    public static CanifisRoamingArea zone7() { return new CanifisRoamingArea(7); }
    public static CanifisRoamingArea zone8() { return new CanifisRoamingArea(8); }

    @Override
    public String getAreaId() { return "canifis_zone" + zone; }

    @Override
    public int[] getPetNpcIds() { return new int[]{ NpcID.HERBI }; }

    @Override
    public int getWanderMinDist()
    {
        switch (zone)
        {
            case 4: return 2;
            case 3:
            case 5:
            case 8: return 4;
            default: return super.getWanderMinDist();
        }
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        Arrays.fill(names, "Herbi");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Herbi</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Keeping the mushroom population in check, one bite at a time.";
            case 2: return "Muddy, moody, and mushroom-minded.";
            case 3: return "Has seen things. Under logs, mostly.";
            case 4: return "The swamp's foremost fungal expert.";
            case 5: return "Dodging werewolves with great success.";
            case 6: return "A boar of simple tastes: fungus and more fungus.";
            case 7: return "Foraging with the grim focus of a scholar.";
            default: return "Nose down, standards lower.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 6;
            case 2:
            case 6:
                return 24;
            case 7: return 8;
            default: return 1;
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
            case 1: return ZONE1_NW;
            case 2: return ZONE2_EAST;
            case 3: return ZONE3_SOUTH_A;
            case 4: return ZONE4_SOUTH_B;
            case 5: return ZONE5_SOUTH_C;
            case 6: return ZONE6_MORT_MYRE;
            case 7: return ZONE7_PORT_PHAS;
            default: return ZONE8_MINE;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 2) return FORBIDDEN_ZONE2;
        return new int[0][][];
    }

    //  Zone 1 - N/W Canifis
    private static final int[][] ZONE1_NW = {
            { 3438, 3491 }, { 3444, 3493 }, { 3432, 3509 }, { 3437, 3516 },
            { 3448, 3520 }, { 3457, 3516 }, { 3464, 3521 }, { 3466, 3528 },
            { 3473, 3528 }, { 3478, 3530 }, { 3470, 3534 }, { 3468, 3534 },
            { 3468, 3539 }, { 3473, 3548 }, { 3478, 3548 }, { 3481, 3546 },
            { 3483, 3542 }, { 3485, 3542 }, { 3488, 3546 }, { 3488, 3550 },
            { 3493, 3551 }, { 3506, 3552 }, { 3511, 3552 }, { 3514, 3549 },
            { 3516, 3543 }, { 3516, 3538 }, { 3520, 3530 }, { 3521, 3526 },
            { 3521, 3524 }, { 3521, 3521 }, { 3518, 3520 }, { 3519, 3517 },
            { 3521, 3513 }, { 3521, 3510 }, { 3521, 3506 }, { 3521, 3503 },
            { 3517, 3504 }, { 3517, 3510 }, { 3515, 3512 }, { 3512, 3516 },
            { 3508, 3519 }, { 3501, 3519 }, { 3494, 3518 }, { 3490, 3515 },
            { 3486, 3517 }, { 3479, 3516 }, { 3476, 3517 }, { 3473, 3513 },
            { 3468, 3515 }, { 3464, 3515 }, { 3462, 3514 }, { 3459, 3511 },
            { 3459, 3508 }, { 3461, 3506 }, { 3461, 3504 }, { 3461, 3502 },
            { 3460, 3498 }, { 3457, 3492 }, { 3460, 3485 }, { 3462, 3484 },
            { 3462, 3479 }, { 3463, 3475 }, { 3465, 3473 }, { 3464, 3470 },
            { 3459, 3466 }, { 3456, 3463 }, { 3454, 3458 }, { 3451, 3458 },
            { 3447, 3458 }, { 3445, 3460 }, { 3445, 3464 }, { 3443, 3465 },
            { 3441, 3467 }, { 3439, 3470 }, { 3438, 3471 }, { 3437, 3473 },
            { 3437, 3477 }, { 3435, 3482 }, { 3438, 3491 }
    };

    //  Zone 2 - East Canifis
    private static final int[][] ZONE2_EAST = {
            { 3521, 3516 }, { 3517, 3510 }, { 3520, 3505 }, { 3519, 3502 },
            { 3522, 3498 }, { 3522, 3496 }, { 3521, 3491 }, { 3522, 3487 },
            { 3525, 3485 }, { 3524, 3481 }, { 3523, 3477 }, { 3520, 3469 },
            { 3520, 3464 }, { 3522, 3461 }, { 3523, 3461 }, { 3524, 3461 },
            { 3522, 3464 }, { 3522, 3466 }, { 3525, 3467 }, { 3527, 3467 },
            { 3530, 3465 }, { 3532, 3461 }, { 3533, 3460 }, { 3533, 3456 },
            { 3535, 3459 }, { 3536, 3463 }, { 3537, 3463 }, { 3539, 3463 },
            { 3540, 3463 }, { 3542, 3460 }, { 3544, 3460 }, { 3546, 3460 },
            { 3550, 3461 }, { 3550, 3462 }, { 3545, 3464 }, { 3545, 3468 },
            { 3548, 3469 }, { 3553, 3470 }, { 3556, 3468 }, { 3556, 3465 },
            { 3558, 3463 }, { 3559, 3460 }, { 3563, 3460 }, { 3569, 3461 },
            { 3575, 3462 }, { 3577, 3464 }, { 3582, 3466 }, { 3584, 3465 },
            { 3590, 3458 }, { 3593, 3450 }, { 3598, 3444 }, { 3601, 3439 },
            { 3608, 3430 }, { 3608, 3427 }, { 3614, 3427 }, { 3616, 3434 },
            { 3618, 3438 }, { 3627, 3440 }, { 3631, 3445 }, { 3636, 3446 },
            { 3642, 3448 }, { 3645, 3448 }, { 3644, 3449 }, { 3640, 3449 },
            { 3635, 3451 }, { 3633, 3453 }, { 3632, 3455 }, { 3632, 3458 },
            { 3633, 3460 }, { 3635, 3461 }, { 3635, 3466 }, { 3637, 3467 },
            { 3639, 3469 }, { 3639, 3471 }, { 3638, 3473 }, { 3637, 3475 },
            { 3636, 3478 }, { 3633, 3479 }, { 3631, 3480 }, { 3631, 3483 },
            { 3632, 3485 }, { 3635, 3485 }, { 3636, 3484 }, { 3639, 3484 },
            { 3639, 3486 }, { 3638, 3489 }, { 3636, 3490 }, { 3632, 3490 },
            { 3630, 3492 }, { 3630, 3496 }, { 3638, 3499 }, { 3641, 3497 },
            { 3643, 3494 }, { 3646, 3494 }, { 3646, 3496 }, { 3646, 3499 },
            { 3642, 3500 }, { 3640, 3502 }, { 3640, 3504 }, { 3642, 3505 },
            { 3643, 3507 }, { 3642, 3510 }, { 3640, 3511 }, { 3637, 3513 },
            { 3635, 3514 }, { 3632, 3513 }, { 3627, 3511 }, { 3623, 3513 },
            { 3621, 3513 }, { 3620, 3512 }, { 3618, 3508 }, { 3618, 3506 },
            { 3614, 3506 }, { 3610, 3507 }, { 3607, 3507 }, { 3603, 3505 },
            { 3598, 3510 }, { 3593, 3510 }, { 3590, 3509 }, { 3587, 3508 },
            { 3585, 3505 }, { 3578, 3503 }, { 3573, 3504 }, { 3575, 3508 },
            { 3578, 3511 }, { 3577, 3513 }, { 3574, 3514 }, { 3572, 3514 },
            { 3570, 3513 }, { 3569, 3511 }, { 3567, 3507 }, { 3561, 3507 },
            { 3557, 3505 }, { 3556, 3509 }, { 3554, 3510 }, { 3553, 3512 },
            { 3552, 3514 }, { 3549, 3515 }, { 3546, 3512 }, { 3543, 3510 },
            { 3541, 3510 }, { 3538, 3511 }, { 3538, 3513 }, { 3535, 3514 },
            { 3532, 3516 }, { 3521, 3516 }
    };

    // Zone 2 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE2 = {
            // Avoid Island
            {
                    { 3565, 3500 }, { 3561, 3499 }, { 3559, 3497 }, { 3557, 3493 },
                    { 3557, 3490 }, { 3563, 3485 }, { 3566, 3485 }, { 3567, 3487 },
                    { 3569, 3488 }, { 3571, 3491 }, { 3570, 3493 }, { 3571, 3495 },
                    { 3572, 3496 }, { 3572, 3498 }, { 3571, 3499 }, { 3571, 3501 },
                    { 3569, 3502 }, { 3566, 3501 }, { 3565, 3500 }
            }
    };

    //  Zone 3 - South Canifis A
    private static final int[][] ZONE3_SOUTH_A = {
            { 3499, 3458 }, { 3496, 3459 }, { 3494, 3459 }, { 3492, 3457 },
            { 3489, 3458 }, { 3487, 3459 }, { 3485, 3461 }, { 3483, 3459 },
            { 3483, 3457 }, { 3486, 3455 }, { 3486, 3454 }, { 3489, 3454 },
            { 3491, 3453 }, { 3491, 3449 }, { 3493, 3447 }, { 3495, 3446 },
            { 3497, 3444 }, { 3498, 3442 }, { 3501, 3441 }, { 3502, 3441 },
            { 3502, 3443 }, { 3502, 3444 }, { 3497, 3448 }, { 3497, 3451 },
            { 3499, 3453 }, { 3501, 3453 }, { 3503, 3453 }, { 3503, 3454 },
            { 3501, 3456 }, { 3499, 3458 }
    };

    //  Zone 4 - South Canifis B
    private static final int[][] ZONE4_SOUTH_B = {
            { 3541, 3453 }, { 3542, 3452 }, { 3544, 3452 }, { 3545, 3452 },
            { 3547, 3452 }, { 3549, 3453 }, { 3551, 3453 }, { 3553, 3452 },
            { 3553, 3451 }, { 3553, 3449 }, { 3551, 3450 }, { 3550, 3449 },
            { 3548, 3449 }, { 3545, 3449 }, { 3543, 3449 }, { 3542, 3448 },
            { 3540, 3450 }, { 3541, 3453 }
    };

    //  Zone 5 - South Canifis C
    private static final int[][] ZONE5_SOUTH_C = {
            { 3523, 3450 }, { 3523, 3451 }, { 3524, 3452 }, { 3523, 3453 },
            { 3521, 3454 }, { 3519, 3455 }, { 3518, 3452 }, { 3517, 3449 },
            { 3517, 3447 }, { 3517, 3443 }, { 3517, 3440 }, { 3518, 3438 },
            { 3519, 3436 }, { 3520, 3436 }, { 3522, 3435 }, { 3526, 3436 },
            { 3528, 3437 }, { 3531, 3437 }, { 3533, 3437 }, { 3533, 3440 },
            { 3532, 3442 }, { 3531, 3442 }, { 3531, 3444 }, { 3531, 3446 },
            { 3529, 3445 }, { 3529, 3444 }, { 3527, 3442 }, { 3525, 3442 },
            { 3523, 3442 }, { 3521, 3445 }, { 3522, 3448 }, { 3522, 3449 },
            { 3523, 3450 }
    };

    //  Zone 6 - Mort Myre Swamp
    private static final int[][] ZONE6_MORT_MYRE = {
            { 3462, 3441 }, { 3460, 3449 }, { 3456, 3454 }, { 3448, 3455 },
            { 3443, 3448 }, { 3432, 3449 }, { 3416, 3452 }, { 3409, 3441 },
            { 3411, 3427 }, { 3412, 3417 }, { 3413, 3409 }, { 3409, 3396 },
            { 3410, 3388 }, { 3415, 3381 }, { 3417, 3372 }, { 3412, 3364 },
            { 3411, 3357 }, { 3416, 3348 }, { 3423, 3343 }, { 3430, 3346 },
            { 3428, 3352 }, { 3425, 3355 }, { 3428, 3362 }, { 3431, 3362 },
            { 3438, 3364 }, { 3441, 3365 }, { 3446, 3364 }, { 3452, 3362 },
            { 3455, 3358 }, { 3455, 3348 }, { 3454, 3348 }, { 3456, 3344 },
            { 3458, 3342 }, { 3463, 3345 }, { 3466, 3345 }, { 3471, 3344 },
            { 3474, 3342 }, { 3476, 3345 }, { 3482, 3347 }, { 3485, 3351 },
            { 3486, 3355 }, { 3488, 3356 }, { 3487, 3362 }, { 3485, 3365 },
            { 3484, 3371 }, { 3486, 3376 }, { 3489, 3376 }, { 3492, 3375 },
            { 3493, 3375 }, { 3494, 3376 }, { 3495, 3378 }, { 3496, 3380 },
            { 3497, 3382 }, { 3498, 3383 }, { 3498, 3385 }, { 3498, 3387 },
            { 3498, 3389 }, { 3499, 3390 }, { 3503, 3391 }, { 3509, 3391 },
            { 3512, 3392 }, { 3515, 3394 }, { 3533, 3400 }, { 3536, 3405 },
            { 3538, 3408 }, { 3541, 3412 }, { 3541, 3414 }, { 3541, 3421 },
            { 3538, 3428 }, { 3533, 3428 }, { 3522, 3425 }, { 3520, 3416 },
            { 3514, 3421 }, { 3507, 3423 }, { 3504, 3424 }, { 3503, 3425 },
            { 3501, 3428 }, { 3499, 3428 }, { 3491, 3423 }, { 3485, 3415 },
            { 3479, 3407 }, { 3475, 3404 }, { 3471, 3404 }, { 3469, 3409 },
            { 3476, 3414 }, { 3483, 3419 }, { 3482, 3422 }, { 3487, 3425 },
            { 3486, 3429 }, { 3481, 3429 }, { 3477, 3431 }, { 3479, 3435 },
            { 3480, 3438 }, { 3485, 3440 }, { 3489, 3437 }, { 3492, 3440 },
            { 3491, 3443 }, { 3485, 3445 }, { 3484, 3446 }, { 3481, 3451 },
            { 3477, 3459 }, { 3477, 3464 }, { 3476, 3467 }, { 3471, 3467 },
            { 3463, 3464 }, { 3464, 3459 }, { 3464, 3456 }, { 3464, 3453 },
            { 3462, 3449 }, { 3466, 3449 }, { 3470, 3446 }, { 3474, 3444 },
            { 3476, 3442 }, { 3476, 3440 }, { 3474, 3435 }, { 3470, 3437 },
            { 3468, 3434 }, { 3465, 3433 }, { 3463, 3434 }, { 3461, 3437 },
            { 3462, 3441 }
    };

    //  Zone 7 - South Port Phasmatys
    private static final int[][] ZONE7_PORT_PHAS = {
            { 3649, 3455 }, { 3648, 3452 }, { 3645, 3449 }, { 3640, 3447 },
            { 3636, 3443 }, { 3632, 3442 }, { 3629, 3439 }, { 3627, 3436 },
            { 3623, 3435 }, { 3617, 3433 }, { 3615, 3431 }, { 3613, 3428 },
            { 3614, 3422 }, { 3615, 3421 }, { 3617, 3420 }, { 3621, 3421 },
            { 3628, 3423 }, { 3633, 3418 }, { 3637, 3416 }, { 3642, 3419 },
            { 3643, 3421 }, { 3648, 3423 }, { 3652, 3425 }, { 3655, 3429 },
            { 3657, 3431 }, { 3662, 3435 }, { 3685, 3438 }, { 3689, 3435 },
            { 3693, 3435 }, { 3696, 3436 }, { 3699, 3435 }, { 3702, 3436 },
            { 3702, 3437 }, { 3699, 3439 }, { 3699, 3443 }, { 3701, 3444 },
            { 3703, 3445 }, { 3706, 3450 }, { 3708, 3452 }, { 3704, 3452 },
            { 3702, 3451 }, { 3698, 3450 }, { 3693, 3451 }, { 3692, 3453 },
            { 3686, 3452 }, { 3679, 3453 }, { 3678, 3451 }, { 3674, 3450 },
            { 3670, 3450 }, { 3667, 3451 }, { 3664, 3451 }, { 3660, 3451 },
            { 3657, 3452 }, { 3653, 3453 }, { 3650, 3453 }, { 3649, 3455 }
    };

    //  Zone 8 - Abandoned Mine
    private static final int[][] ZONE8_MINE = {
            { 3444, 3264 }, { 3444, 3266 }, { 3444, 3267 }, { 3443, 3267 },
            { 3442, 3268 }, { 3441, 3268 }, { 3439, 3268 }, { 3439, 3265 },
            { 3439, 3262 }, { 3438, 3259 }, { 3437, 3258 }, { 3438, 3255 },
            { 3441, 3253 }, { 3441, 3251 }, { 3444, 3249 }, { 3444, 3248 },
            { 3446, 3248 }, { 3448, 3250 }, { 3450, 3251 }, { 3452, 3252 },
            { 3455, 3252 }, { 3460, 3251 }, { 3461, 3251 }, { 3465, 3250 },
            { 3466, 3250 }, { 3467, 3251 }, { 3468, 3252 }, { 3467, 3253 },
            { 3460, 3259 }, { 3456, 3257 }, { 3455, 3257 }, { 3452, 3257 },
            { 3446, 3257 }, { 3443, 3260 }, { 3444, 3264 }
    };
}

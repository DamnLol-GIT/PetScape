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


public class MorytaniaTowerArea extends RoamingArea
{
    private final int zone;

    private MorytaniaTowerArea(int zone) { this.zone = zone; }

    public static MorytaniaTowerArea zone1() { return new MorytaniaTowerArea(1); }

    @Override
    public String getAreaId() { return "morytania_tower_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        return new int[]{ NpcID.MIDNIGHT, NpcID.NOON };
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        // First 4 Midnight, last 8 Noon
        for (int i = 0; i < n; i++)
            names[i] = i < 4 ? "Midnight" : "Noon";
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return formIndex == 0
                ? "<col=ffff00>Midnight</col>"
                : "<col=ffff00>Noon</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        return formIndex == 0
                ? "A little Gargoyle up past its bedtime."
                : "A little ray of sunshine.";
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        // First 4 Midnight (0), last 8 Noon (1)
        return spawnIndex < 4 ? 0 : 1;
    }

    @Override
    public int getSpawnCount() { return 12; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints() { return ZONE1_PERIMETER; }

    // Zone 1 - Tower Perimeter
    private static final int[][] ZONE1_PERIMETER = {
            { 3404, 3535 }, { 3412, 3520 }, { 3414, 3521 }, { 3417, 3521 },
            { 3418, 3520 }, { 3420, 3520 }, { 3422, 3522 }, { 3425, 3522 },
            { 3425, 3521 }, { 3427, 3521 }, { 3427, 3520 }, { 3430, 3520 },
            { 3431, 3519 }, { 3431, 3518 }, { 3432, 3517 }, { 3436, 3517 },
            { 3437, 3518 }, { 3437, 3520 }, { 3439, 3520 }, { 3440, 3521 },
            { 3442, 3521 }, { 3443, 3520 }, { 3444, 3520 }, { 3445, 3521 },
            { 3447, 3521 }, { 3448, 3522 }, { 3450, 3522 }, { 3450, 3521 },
            { 3454, 3521 }, { 3455, 3520 }, { 3456, 3520 }, { 3460, 3521 },
            { 3464, 3523 }, { 3466, 3526 }, { 3469, 3529 }, { 3470, 3530 },
            { 3472, 3532 }, { 3473, 3533 }, { 3471, 3535 }, { 3470, 3536 },
            { 3469, 3537 }, { 3469, 3539 }, { 3470, 3541 }, { 3471, 3542 },
            { 3473, 3544 }, { 3475, 3547 }, { 3475, 3548 }, { 3475, 3548 },
            { 3467, 3548 }, { 3465, 3547 }, { 3462, 3548 }, { 3459, 3548 },
            { 3457, 3548 }, { 3457, 3549 }, { 3457, 3552 }, { 3457, 3557 },
            { 3458, 3563 }, { 3469, 3563 }, { 3469, 3548 }, { 3475, 3548 },
            { 3474, 3567 }, { 3470, 3569 }, { 3470, 3571 }, { 3469, 3572 },
            { 3469, 3574 }, { 3469, 3576 }, { 3469, 3578 }, { 3466, 3578 },
            { 3465, 3579 }, { 3464, 3580 }, { 3463, 3581 }, { 3461, 3583 },
            { 3459, 3583 }, { 3456, 3583 }, { 3449, 3589 }, { 3446, 3590 },
            { 3440, 3590 }, { 3407, 3590 }, { 3405, 3581 }, { 3404, 3578 },
            { 3405, 3577 }, { 3409, 3581 }, { 3413, 3581 }, { 3416, 3578 },
            { 3418, 3578 }, { 3420, 3579 }, { 3424, 3579 }, { 3425, 3578 },
            { 3433, 3578 }, { 3434, 3579 }, { 3438, 3579 }, { 3439, 3578 },
            { 3442, 3578 }, { 3445, 3581 }, { 3449, 3581 }, { 3454, 3576 },
            { 3454, 3572 }, { 3450, 3567 }, { 3450, 3564 }, { 3451, 3563 },
            { 3451, 3559 }, { 3449, 3557 }, { 3449, 3553 }, { 3450, 3552 },
            { 3450, 3548 }, { 3449, 3546 }, { 3449, 3543 }, { 3453, 3539 },
            { 3453, 3535 }, { 3448, 3530 }, { 3444, 3530 }, { 3442, 3533 },
            { 3438, 3533 }, { 3438, 3532 }, { 3433, 3532 }, { 3432, 3533 },
            { 3424, 3533 }, { 3423, 3532 }, { 3414, 3532 }, { 3412, 3530 },
            { 3409, 3530 }, { 3404, 3535 }
    };
}
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


public class MorytaniaMiscArea extends RoamingArea
{
    private final int zone;

    private MorytaniaMiscArea(int zone) { this.zone = zone; }

    public static MorytaniaMiscArea zone1() { return new MorytaniaMiscArea(1); }
    public static MorytaniaMiscArea zone2() { return new MorytaniaMiscArea(2); }
    public static MorytaniaMiscArea zone3() { return new MorytaniaMiscArea(3); }
    public static MorytaniaMiscArea zone4() { return new MorytaniaMiscArea(4); }
    public static MorytaniaMiscArea zone5() { return new MorytaniaMiscArea(5); }

    @Override
    public String getAreaId() { return "morytania_misc_zone" + zone; }

    @Override
    public boolean isAquatic() { return zone >= 2 && zone <= 4; }

    @Override
    public int[] getPetNpcIds()
    {
        if (zone == 5)
            return new int[]{ NpcID.NID, NpcID.RAX };

        if (zone >= 2)
            return new int[]{
                    NpcID.SNAKELING_2127,
                    NpcID.SNAKELING_2128,
                    NpcID.SNAKELING_2129
            };

        return new int[]{ NpcID.ZILYANA_JR };
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        if (zone == 5)
        {
            // First 6 Nid, last 12 Rax
            for (int i = 0; i < n; i++)
                names[i] = i < 6 ? "Nid" : "Rax";
            return names;
        }
        String name = zone >= 2 ? "Pet Snakeling" : "Zilyana Jr.";
        Arrays.fill(names, name);
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 5)
            return formIndex == 0 ? "<col=ffff00>Nid</col>" : "<col=ffff00>Rax</col>";
        if (zone >= 2) return "<col=ffff00>Pet Snakeling</col>";
        return "<col=ffff00>Commander Miniana</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        if (zone == 5)
            return formIndex == 0 ? "A tiny warden of the web." : "A small and skittery menace.";
        if (zone >= 2) return "A little serpent of the swamp.";
        return "A sad little commander..";
    }

    @Override
    public boolean isFormFixed() { return zone == 5; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        if (zone == 5)
            // First 6 Nid (0), last 12 Rax (1)
            return spawnIndex < 6 ? 0 : 1;
        return super.getFormAssignment(spawnIndex, nForms);
    }

    @Override
    public int getSpawnCount() { return zone == 5 ? 18 : zone >= 2 ? 2 : 3; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return zone >= 2 && zone <= 4 ? -40 : 10; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 2: return ZONE2;
            case 3: return ZONE3;
            case 4: return ZONE4;
            case 5: return ZONE5;
        }
        return ZONE1;
    }

    // Zone 1 - Iceyne Graveyard
    private static final int[][] ZONE1 = {
            { 3679, 3186 }, { 3679, 3189 }, { 3681, 3191 }, { 3687, 3191 },
            { 3689, 3193 }, { 3689, 3195 }, { 3691, 3195 }, { 3691, 3193 },
            { 3695, 3190 }, { 3696, 3189 }, { 3697, 3189 }, { 3700, 3190 },
            { 3701, 3192 }, { 3702, 3189 }, { 3706, 3189 }, { 3707, 3189 },
            { 3707, 3194 }, { 3708, 3196 }, { 3709, 3195 }, { 3710, 3194 },
            { 3711, 3194 }, { 3711, 3190 }, { 3712, 3190 }, { 3712, 3189 },
            { 3708, 3189 }, { 3708, 3185 }, { 3711, 3185 }, { 3708, 3183 },
            { 3706, 3182 }, { 3705, 3180 }, { 3701, 3180 }, { 3697, 3179 },
            { 3696, 3178 }, { 3693, 3179 }, { 3690, 3180 }, { 3688, 3181 },
            { 3683, 3181 }, { 3679, 3186 }
    };

    // Zone 2 - Pool East (aquatic)
    private static final int[][] ZONE2 = {
            { 3689, 3411 }, { 3685, 3409 }, { 3685, 3407 }, { 3687, 3405 },
            { 3688, 3403 }, { 3687, 3401 }, { 3684, 3399 }, { 3684, 3397 },
            { 3687, 3396 }, { 3689, 3395 }, { 3692, 3396 }, { 3694, 3396 },
            { 3698, 3400 }, { 3698, 3402 }, { 3696, 3404 }, { 3694, 3406 },
            { 3693, 3406 }, { 3692, 3407 }, { 3692, 3410 }, { 3690, 3413 },
            { 3689, 3411 }
    };

    // Zone 3 - Pool West (aquatic)
    private static final int[][] ZONE3 = {
            { 3678, 3410 }, { 3678, 3408 }, { 3674, 3404 }, { 3674, 3401 },
            { 3676, 3399 }, { 3680, 3396 }, { 3679, 3394 }, { 3678, 3394 },
            { 3676, 3397 }, { 3675, 3398 }, { 3674, 3399 }, { 3671, 3399 },
            { 3670, 3400 }, { 3670, 3402 }, { 3671, 3403 }, { 3669, 3407 },
            { 3665, 3407 }, { 3664, 3409 }, { 3667, 3412 }, { 3668, 3414 },
            { 3668, 3415 }, { 3672, 3413 }, { 3674, 3415 }, { 3677, 3414 },
            { 3679, 3411 }, { 3678, 3410 }
    };

    // Zone 4 - Pool South (aquatic)
    private static final int[][] ZONE4 = {
            { 3661, 3192 }, { 3659, 3190 }, { 3659, 3188 }, { 3660, 3187 },
            { 3661, 3187 }, { 3662, 3186 }, { 3665, 3187 }, { 3666, 3188 },
            { 3666, 3190 }, { 3664, 3192 }, { 3664, 3193 }, { 3663, 3194 },
            { 3663, 3206 }, { 3665, 3207 }, { 3664, 3209 }, { 3661, 3209 },
            { 3661, 3207 }, { 3662, 3205 }, { 3662, 3193 }, { 3661, 3192 }
    };

    // Zone 5 - Araxxor Outside Cave
    private static final int[][] ZONE5 = {
            { 3632, 3408 }, { 3638, 3401 }, { 3638, 3394 }, { 3664, 3394 },
            { 3664, 3386 }, { 3676, 3396 }, { 3674, 3398 }, { 3671, 3398 },
            { 3669, 3399 }, { 3668, 3401 }, { 3669, 3403 }, { 3669, 3404 },
            { 3669, 3405 }, { 3667, 3405 }, { 3665, 3406 }, { 3663, 3408 },
            { 3662, 3410 }, { 3663, 3411 }, { 3666, 3413 }, { 3666, 3415 },
            { 3667, 3416 }, { 3669, 3416 }, { 3671, 3415 }, { 3674, 3416 },
            { 3677, 3418 }, { 3677, 3420 }, { 3676, 3421 }, { 3676, 3422 },
            { 3675, 3422 }, { 3675, 3423 }, { 3673, 3423 }, { 3671, 3424 },
            { 3668, 3423 }, { 3667, 3423 }, { 3665, 3423 }, { 3662, 3422 },
            { 3660, 3420 }, { 3657, 3418 }, { 3655, 3416 }, { 3653, 3414 },
            { 3651, 3412 }, { 3647, 3411 }, { 3645, 3410 }, { 3643, 3408 },
            { 3642, 3407 }, { 3635, 3407 }, { 3632, 3408 }
    };
}
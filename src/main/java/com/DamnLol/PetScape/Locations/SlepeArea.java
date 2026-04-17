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


public class SlepeArea extends RoamingArea
{

    private static final String[] FORM_NAMES = {
            "Little Nightmare",
            "Little Parasite"
    };

    private static final String[] FORM_EXAMINES = {
            "She just needs a little hug..", // Nightmare
            "It's so cute!" // Parasite
    };

    private final int zone;

    private SlepeArea(int zone) { this.zone = zone; }

    public static SlepeArea zone1() { return new SlepeArea(1); }
    public static SlepeArea zone2() { return new SlepeArea(2); }

    @Override
    public String getAreaId() { return "slepe_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        // Zone 2 — parasites only
        if (zone == 2)
            return new int[]{ NpcID.LITTLE_PARASITE};

        // Zone 1 — nightmare/parasite
        return new int[]{
                NpcID.LITTLE_NIGHTMARE,
                NpcID.LITTLE_PARASITE
        };
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        if (zone == 2)
        {
            Arrays.fill(names, "Little Parasite");
            return names;
        }
        // Zone 1 - first 16 are nightmares, remaining 24 are parasites
        for (int i = 0; i < n; i++)
            names[i] = i < 16 ? "Little Nightmare" : "Little Parasite";
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 2) return "<col=ffff00>Little Parasite</col>";
        return "<col=ffff00>" + FORM_NAMES[formIndex % FORM_NAMES.length] + "</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        if (zone == 2) return FORM_EXAMINES[1];
        return FORM_EXAMINES[formIndex % FORM_EXAMINES.length];
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        if (zone == 1)
        {
            // First 16 slots nightmare (formIndex 0), remaining 24 parasite (formIndex 1)
            return spawnIndex < 16 ? 0 : 1;
        }
        return super.getFormAssignment(spawnIndex, nForms);
    }

    @Override
    public int getSpawnCount()
    {
        if (zone == 2) return 8;
        return 40;
    }

    @Override
    public boolean isFlying() { return true; }

    @Override
    public int getMenuClickRadius() { return 70; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int[][] getPolygonPoints()
    {
        if (zone == 2) return ZONE2;
        return ZONE1;
    }

    // Zone 1 - Slepe Town
    private static final int[][] ZONE1 = {
            { 3696, 3376 }, { 3695, 3372 }, { 3700, 3366 }, { 3697, 3356 },
            { 3701, 3345 }, { 3691, 3342 }, { 3689, 3334 }, { 3680, 3315 },
            { 3687, 3307 }, { 3689, 3303 }, { 3689, 3294 }, { 3690, 3287 },
            { 3697, 3290 }, { 3702, 3288 }, { 3706, 3285 }, { 3710, 3282 },
            { 3723, 3299 }, { 3737, 3296 }, { 3740, 3293 }, { 3745, 3295 },
            { 3748, 3293 }, { 3752, 3294 }, { 3754, 3295 }, { 3754, 3304 },
            { 3755, 3305 }, { 3757, 3307 }, { 3768, 3307 }, { 3768, 3308 },
            { 3750, 3309 }, { 3760, 3312 }, { 3765, 3314 }, { 3765, 3316 },
            { 3766, 3323 }, { 3768, 3326 }, { 3771, 3332 }, { 3772, 3337 },
            { 3771, 3341 }, { 3770, 3345 }, { 3768, 3351 }, { 3764, 3357 },
            { 3760, 3360 }, { 3761, 3368 }, { 3764, 3370 }, { 3766, 3377 },
            { 3771, 3381 }, { 3769, 3387 }, { 3769, 3393 }, { 3767, 3400 },
            { 3765, 3404 }, { 3762, 3405 }, { 3758, 3408 }, { 3754, 3412 },
            { 3751, 3411 }, { 3748, 3408 }, { 3737, 3403 }, { 3731, 3399 },
            { 3729, 3401 }, { 3719, 3395 }, { 3718, 3398 }, { 3709, 3396 },
            { 3702, 3379 }, { 3696, 3376 }
    };

    // Zone 2 - Slepe Path
    private static final int[][] ZONE2 = {
            { 3696, 3373 }, { 3687, 3377 }, { 3674, 3370 }, { 3674, 3347 },
            { 3671, 3346 }, { 3670, 3330 }, { 3664, 3324 }, { 3652, 3321 },
            { 3650, 3322 }, { 3648, 3296 }, { 3646, 3253 }, { 3652, 3257 },
            { 3662, 3248 }, { 3671, 3256 }, { 3675, 3256 }, { 3670, 3267 },
            { 3660, 3269 }, { 3656, 3275 }, { 3654, 3282 }, { 3657, 3288 },
            { 3662, 3296 }, { 3667, 3296 }, { 3669, 3292 }, { 3676, 3290 },
            { 3682, 3293 }, { 3686, 3292 }, { 3689, 3335 }, { 3696, 3372 },
            { 3696, 3373 }
    };
}
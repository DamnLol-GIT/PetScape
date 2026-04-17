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


public class VampireArea extends RoamingArea
{

    private static final String[] FORM_NAMES = {
            "Lil' Zik",
            "Lil' Maiden",
            "Lil' Bloat",
            "Lil' Nylo",
            "Lil' Sot",
            "Lil' Xarp"
    };

    private static final String[] FORM_EXAMINES = {
            "It’s hard to take a vampire spider seriously - they're just too fang-tastic!",
            "Freed From Her Tiny Constraints",
            "Smaller size, great smell.",
            "Eight small legs of unparalleled cuteness.",
            "Has a lot of anger for such a tiny monster.",
            "The little prince of Yarasa."
    };

    private final int zone;

    private VampireArea(int zone) { this.zone = zone; }


    public static VampireArea zone1() { return new VampireArea(1); }
    public static VampireArea zone2() { return new VampireArea(2); }
    public static VampireArea zone3() { return new VampireArea(3); }
    public static VampireArea zone4() { return new VampireArea(4); }

    @Override
    public String getAreaId() { return "vampire_zone" + zone; }


    @Override
    public int[] getPetNpcIds()
    {
        // Zone 4
        if (zone == 4)
            return new int[]{ NpcID.LIL_MAIDEN };

        // Zones 1/2/3
        if (zone == 3)
            return new int[]{
                    NpcID.LIL_ZIK,
                    NpcID.LIL_BLOAT,
                    NpcID.LIL_NYLO,
                    NpcID.LIL_SOT,
                    NpcID.LIL_XARP
            };


        return new int[]{
                NpcID.LIL_ZIK,
                NpcID.LIL_MAIDEN,
                NpcID.LIL_BLOAT,
                NpcID.LIL_NYLO,
                NpcID.LIL_SOT,
                NpcID.LIL_XARP
        };
    }


    @Override
    public boolean isFormFixed() { return true; }

    // min distance and sibling separation so pets can always find valid targets
    @Override
    public int getWanderMinDist() { return 5; }

    @Override
    public int getMinSiblingSeparation() { return 2; }

    @Override
    public String[] getSpawnNames()
    {
        int[] ids = getPetNpcIds();
        int n = getSpawnCount();
        String[] names = new String[n];
        if (zone == 4)
        {
            Arrays.fill(names, "Lil' Maiden");
            return names;
        }
        // Build name list matching the form IDs for this zone
        String[] zoneFormNames = formNamesForZone();
        for (int i = 0; i < n; i++)
            names[i] = zoneFormNames[i % zoneFormNames.length];
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 4) return "<col=ffff00>Lil' Maiden</col>";
        return "<col=ffff00>" + formNamesForZone()[formIndex % formNamesForZone().length] + "</col>";
    }

    // Each form has unique examine text
    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        if (zone == 4) return FORM_EXAMINES[1]; // Lil' Maiden examine
        return FORM_EXAMINES_FOR_ZONE()[formIndex % FORM_EXAMINES_FOR_ZONE().length];
    }


    // Returns the display names matching getPetNpcIds() order for this zone
    private String[] formNamesForZone()
    {
        if (zone == 3)
            return new String[]{ "Lil' Zik", "Lil' Bloat", "Lil' Nylo", "Lil' Sot", "Lil' Xarp" };
        return FORM_NAMES;
    }

    // Returns the examine text matching getPetNpcIds() order for zone
    private String[] FORM_EXAMINES_FOR_ZONE()
    {
        if (zone == 3)
            return new String[]{
                    FORM_EXAMINES[0], // Lil' Zik
                    FORM_EXAMINES[2], // Lil' Bloat
                    FORM_EXAMINES[3], // Lil' Nylo
                    FORM_EXAMINES[4], // Lil' Sot
                    FORM_EXAMINES[5]  // Lil' Xarp
            };
        return FORM_EXAMINES;
    }

    @Override
    public int getSpawnCount()
    {
        if (zone == 2) return 36; // 6 x 6 forms
        if (zone == 3) return 15; // 3 x 5 forms (no Lil' Maiden)
        if (zone == 4) return 4;  // 4 x Lil' Maiden
        return 24; // zone 1: 4 x 6 forms
    }

    @Override
    public int getMenuClickRadius() { return 70; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1;
            case 2: return ZONE2;
            case 3: return ZONE3;
            case 4: return ZONE4;
        }
        return ZONE1;
    }

    // Zone 1 - Darkmeyer
    private static final int[][] ZONE1 = {
            { 3593, 3397 }, { 3593, 3384 }, { 3592, 3383 }, { 3592, 3369 },
            { 3590, 3367 }, { 3590, 3364 }, { 3594, 3364 }, { 3594, 3361 },
            { 3590, 3361 }, { 3590, 3356 }, { 3594, 3356 }, { 3594, 3352 },
            { 3590, 3352 }, { 3590, 3349 }, { 3588, 3349 }, { 3588, 3342 },
            { 3588, 3333 }, { 3594, 3333 }, { 3594, 3314 }, { 3614, 3314 },
            { 3614, 3331 }, { 3614, 3332 }, { 3660, 3332 }, { 3660, 3337 },
            { 3665, 3337 }, { 3665, 3352 }, { 3668, 3352 }, { 3668, 3382 },
            { 3660, 3382 }, { 3660, 3390 }, { 3660, 3391 }, { 3634, 3391 },
            { 3634, 3398 }, { 3593, 3397 }
    };

    // Zone 2 - Meiyerditch
    private static final int[][] ZONE2 = {
            { 3632, 3327 }, { 3623, 3327 }, { 3620, 3324 }, { 3620, 3319 },
            { 3618, 3318 }, { 3618, 3310 }, { 3596, 3310 }, { 3596, 3306 },
            { 3596, 3289 }, { 3605, 3289 }, { 3605, 3287 }, { 3610, 3287 },
            { 3610, 3285 }, { 3591, 3285 }, { 3591, 3281 }, { 3593, 3279 },
            { 3593, 3267 }, { 3591, 3264 }, { 3591, 3259 }, { 3592, 3258 },
            { 3612, 3258 }, { 3614, 3258 }, { 3614, 3256 }, { 3595, 3256 },
            { 3595, 3254 }, { 3594, 3253 }, { 3594, 3250 }, { 3592, 3249 },
            { 3595, 3242 }, { 3594, 3233 }, { 3596, 3231 }, { 3615, 3231 },
            { 3616, 3229 }, { 3616, 3228 }, { 3592, 3228 }, { 3591, 3226 },
            { 3591, 3219 }, { 3593, 3217 }, { 3593, 3210 }, { 3591, 3208 },
            { 3591, 3181 }, { 3595, 3181 }, { 3599, 3184 }, { 3602, 3184 },
            { 3605, 3181 }, { 3609, 3179 }, { 3612, 3181 }, { 3614, 3181 },
            { 3616, 3181 }, { 3621, 3177 }, { 3623, 3176 }, { 3626, 3176 },
            { 3628, 3180 }, { 3629, 3182 }, { 3631, 3184 }, { 3633, 3184 },
            { 3635, 3186 }, { 3636, 3187 }, { 3637, 3186 }, { 3639, 3186 },
            { 3640, 3186 }, { 3640, 3197 }, { 3642, 3199 }, { 3642, 3200 },
            { 3637, 3205 }, { 3637, 3209 }, { 3635, 3209 }, { 3633, 3212 },
            { 3630, 3214 }, { 3630, 3224 }, { 3632, 3226 }, { 3635, 3230 },
            { 3637, 3231 }, { 3637, 3235 }, { 3642, 3240 }, { 3640, 3243 },
            { 3640, 3261 }, { 3638, 3264 }, { 3641, 3269 }, { 3641, 3281 },
            { 3643, 3282 }, { 3643, 3289 }, { 3641, 3291 }, { 3641, 3302 },
            { 3643, 3304 }, { 3643, 3312 }, { 3640, 3317 }, { 3641, 3323 },
            { 3643, 3324 }, { 3643, 3326 }
    };

    // Zone 3 - Ver Sinhaza
    private static final int[][] ZONE3 = {
            { 3642, 3233 }, { 3642, 3230 }, { 3644, 3230 }, { 3644, 3229 },
            { 3644, 3227 }, { 3645, 3226 }, { 3648, 3226 }, { 3648, 3225 },
            { 3646, 3223 }, { 3646, 3222 }, { 3648, 3222 }, { 3649, 3221 },
            { 3649, 3217 }, { 3648, 3216 }, { 3646, 3216 }, { 3646, 3211 },
            { 3644, 3211 }, { 3644, 3209 }, { 3642, 3208 }, { 3642, 3206 },
            { 3644, 3204 }, { 3648, 3204 }, { 3648, 3208 }, { 3648, 3210 },
            { 3654, 3210 }, { 3654, 3207 }, { 3654, 3204 }, { 3655, 3204 },
            { 3655, 3209 }, { 3656, 3210 }, { 3656, 3215 }, { 3670, 3215 },
            { 3670, 3204 }, { 3681, 3204 }, { 3683, 3206 }, { 3683, 3208 },
            { 3680, 3209 }, { 3678, 3211 }, { 3678, 3212 }, { 3677, 3213 },
            { 3673, 3213 }, { 3673, 3226 }, { 3677, 3226 }, { 3679, 3229 },
            { 3680, 3230 }, { 3681, 3233 }, { 3680, 3235 }, { 3670, 3235 },
            { 3670, 3224 }, { 3668, 3223 }, { 3656, 3223 }, { 3655, 3228 },
            { 3655, 3229 }, { 3655, 3235 }, { 3644, 3235 }, { 3642, 3233 }
    };

    // Zone 4 - Hespori Island Shore
    private static final int[][] ZONE4 = {
            { 3720, 3289 }, { 3718, 3278 }, { 3720, 3275 }, { 3722, 3271 },
            { 3726, 3275 }, { 3729, 3276 }, { 3731, 3276 }, { 3732, 3274 },
            { 3737, 3268 }, { 3743, 3268 }, { 3745, 3267 }, { 3751, 3266 },
            { 3752, 3268 }, { 3753, 3269 }, { 3753, 3272 }, { 3752, 3273 },
            { 3755, 3276 }, { 3755, 3277 }, { 3759, 3282 }, { 3760, 3283 },
            { 3760, 3286 }, { 3759, 3289 }, { 3751, 3289 }, { 3747, 3293 },
            { 3745, 3293 }, { 3751, 3287 }, { 3750, 3285 }, { 3749, 3283 },
            { 3746, 3280 }, { 3743, 3279 }, { 3741, 3277 }, { 3740, 3275 },
            { 3739, 3274 }, { 3735, 3276 }, { 3734, 3277 }, { 3731, 3278 },
            { 3729, 3280 }, { 3728, 3282 }, { 3728, 3283 }, { 3727, 3284 },
            { 3724, 3285 }, { 3722, 3286 }, { 3720, 3289 }
    };
}
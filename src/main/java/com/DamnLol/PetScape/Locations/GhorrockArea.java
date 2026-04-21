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


public class GhorrockArea extends RoamingArea
{
    private final int zone;

    private GhorrockArea(int zone) { this.zone = zone; }

    public static GhorrockArea zone1() { return new GhorrockArea(1); }
    public static GhorrockArea zone2() { return new GhorrockArea(2); }
    public static GhorrockArea zone3() { return new GhorrockArea(3); }

    @Override
    public String getAreaId() { return "ghorrock_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        if (zone == 3)
            return new int[]{
                    NpcID.MUPHIN,
                    NpcID.MUPHIN_12014,
                    NpcID.MUPHIN_12015,
                    NpcID.MUPHIN_12016
            };
        return new int[]{ NpcID.BARON };
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public int getWanderMinDist()
    {
        if (zone == 3) return 4;
        return super.getWanderMinDist();
    }

    @Override
    public int getMinSiblingSeparation()
    {
        if (zone == 3) return 2;
        return super.getMinSiblingSeparation();
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        Arrays.fill(names, zone == 3 ? "Lil' Muspah" : "Baron");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 3) return "<col=ffff00>Lil' Muspah</col>";
        return "<col=ffff00>Baron</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Looks hungry. Always looks hungry.";
            case 2: return "A small noble with a sizeable appetite.";
            case 3:
                switch (formIndex)
                {
                    case 0:
                    case 1:
                        return "Someone dreamed this up. Regretted it.";
                    case 2: return "Prefers you keep moving. For your sake.";
                    default: return "Glowing faintly. That's never good.";
                }
            default: return "";
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        if (zone == 3) return spawnIndex % nForms;
        return super.getFormAssignment(spawnIndex, nForms);
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 3;
            case 2: return 8;
            case 3: return 4;
            default: return 0;
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
            case 2: return ZONE2_DUKE_OUTSIDE;
            case 3: return ZONE3_MUSPAH_OUTSIDE;
            default: return ZONE1_DUKE_INSIDE;
        }
    }

    // Zone 1 - Duke Prison (Inside)
    private static final int[][] ZONE1_DUKE_INSIDE = {
            { 3037, 6432 }, { 3042, 6432 }, { 3042, 6430 }, { 3041, 6429 },
            { 3041, 6428 }, { 3042, 6427 }, { 3054, 6427 }, { 3054, 6424 },
            { 3041, 6424 }, { 3041, 6418 }, { 3037, 6418 }, { 3037, 6424 },
            { 3025, 6424 }, { 3025, 6427 }, { 3037, 6427 }, { 3038, 6428 },
            { 3038, 6429 }, { 3037, 6430 }, { 3037, 6432 }
    };

    // Zone 2 - Duke Prison (Outside)
    private static final int[][] ZONE2_DUKE_OUTSIDE = {
            { 2919, 10337 }, { 2918, 10335 }, { 2919, 10334 }, { 2925, 10334 },
            { 2926, 10333 }, { 2926, 10329 }, { 2926, 10326 }, { 2926, 10324 },
            { 2927, 10323 }, { 2929, 10323 }, { 2929, 10325 }, { 2930, 10326 },
            { 2937, 10326 }, { 2937, 10329 }, { 2930, 10329 }, { 2929, 10330 },
            { 2929, 10341 }, { 2930, 10343 }, { 2931, 10343 }, { 2933, 10345 },
            { 2933, 10346 }, { 2934, 10347 }, { 2934, 10350 }, { 2933, 10351 },
            { 2933, 10353 }, { 2932, 10354 }, { 2923, 10354 }, { 2922, 10353 },
            { 2922, 10351 }, { 2921, 10350 }, { 2921, 10347 }, { 2922, 10346 },
            { 2922, 10344 }, { 2923, 10343 }, { 2925, 10343 }, { 2926, 10342 },
            { 2926, 10338 }, { 2925, 10337 }, { 2919, 10337 }
    };

    // Zone 3 - Muspah Prison (Outside)
    private static final int[][] ZONE3_MUSPAH_OUTSIDE = {
            { 2909, 10316 }, { 2913, 10316 }, { 2914, 10320 }, { 2917, 10320 },
            { 2917, 10323 }, { 2915, 10323 }, { 2914, 10324 }, { 2914, 10333 },
            { 2915, 10334 }, { 2915, 10336 }, { 2914, 10337 }, { 2906, 10337 },
            { 2904, 10336 }, { 2904, 10335 }, { 2905, 10334 }, { 2910, 10334 },
            { 2911, 10334 }, { 2911, 10321 }, { 2910, 10320 }, { 2909, 10316 }
    };
}

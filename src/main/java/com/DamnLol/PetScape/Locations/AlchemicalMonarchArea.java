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
import net.runelite.api.Model;
import net.runelite.api.NpcID;
import net.runelite.api.coords.WorldPoint;

// Alchemical Monarch - Stationary - Recolor

public class AlchemicalMonarchArea extends RoamingArea
{
    private static final int SPAWN_X = 1251;
    private static final int SPAWN_Y = 3810;

    @Override
    public String getAreaId()
    {
        return "alchemical_monarch";
    }

    @Override
    public int[][] getPolygonPoints()
    {
        return new int[][]{
                { 1250, 3805 },
                { 1258, 3805 },
                { 1258, 3813 },
                { 1250, 3813 }
        };
    }


    @Override
    public int[][][] getForbiddenZonePoints()
    {
        return new int[0][][];
    }

    @Override
    public int getSpawnCount()
    {
        return 1;
    }

    @Override
    public boolean isStationary()
    {
        return true;
    }

    @Override
    public int getInitialOrientation()
    {
        return 1536;
    }

    @Override
    public int getCustomIdleAnimId()
    {
        return 8233;
    }


    @Override
    public void tintModel(Model model)
    {
        tintColors(model.getFaceColors1(), model.getFaceColors1());
        tintColors(model.getFaceColors2(), model.getFaceColors2());
        tintColors(model.getFaceColors3(), model.getFaceColors3());
    }

    private static void tintColors(int[] dst, int[] src)
    {
        if (dst == null || src == null) return;
        int len = Math.min(dst.length, src.length);
        for (int i = 0; i < len; i++)
        {
            int c = src[i];
            if (c < 0) { dst[i] = c; continue; }

            int hue = (c >> 10) & 0x3F;
            int sat = (c >>  7) & 0x7;
            int lum =  c & 0x7F;

            // Eye color
            if (sat >= 4 && hue >= 24 && hue <= 40 && lum > 45)
                continue;

            float redness = Math.min(1.0f, Math.max(0.0f, (lum - 15) / 45.0f));

            float targetS = 7  * redness;
            float targetL = 2  + 12 * redness;

            final float B = 0.93f;
            hue = 0;
            sat = Math.round(sat * (1 - B) + targetS * B);
            lum = Math.round(lum * (1 - B) + targetL * B);

            dst[i] = (Math.max(0, Math.min(7,   sat)) <<  7)
                    |  Math.max(0, Math.min(127, lum));
        }
    }

    @Override
    public int getMenuClickRadius() { return 160; }

    @Override
    public int getPlane()
    {
        return 0;
    }

    @Override
    public int[] getPetNpcIds()
    {
        return new int[]{ NpcID.ALCHEMICAL_HYDRA };
    }

    @Override
    public int getZOffset()
    {
        return 0;
    }

    @Override
    public String[] getSpawnNames()
    {
        return new String[]{ "Alchemical Monarch" };
    }

    @Override
    public String getMenuTarget(int spawnIndex)
    {
        return "<col=ffff00>Alchemical Monarch</col><col=ff0000> (Lvl ???)</col>";
    }

    @Override
    public String getExamineText()
    {
        return "King of the Alchemical Void.";
    }

    @Override
    public WorldPoint getCenter()
    {
        return new WorldPoint(SPAWN_X, SPAWN_Y, getPlane());
    }
}
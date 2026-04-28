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


public class XericArea extends RoamingArea
{
    private final int zone;

    private XericArea(int zone) { this.zone = zone; }

    public static XericArea zone1() { return new XericArea(1); }
    public static XericArea zone2() { return new XericArea(2); }
    public static XericArea zone3() { return new XericArea(3); }
    public static XericArea zone4() { return new XericArea(4); }

    @Override
    public String getAreaId() { return "xeric_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 3: return new int[]{
                    NpcID.OLMLET, NpcID.PUPPADILE, NpcID.TEKTINY,
                    NpcID.VANGUARD_8198, NpcID.VASA_MINIRIO, NpcID.VESPINA
            };
            default: return new int[]{ NpcID.FLYING_VESPINA };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 2 || zone == 4; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        return spawnIndex;
    }

    @Override
    public String[] getSpawnNames()
    {
        switch (zone)
        {
            case 1:
            case 3: return new String[]{
                    "Olmlet", "Puppadile", "Tektiny",
                    "Vanguard", "Vasa Minirio", "Vespina"
            };
            default: return new String[]{ "Flying Vespina" };
        }
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 2 || zone == 4) return "<col=ffff00>Flying Vespina</col>";
        switch (formIndex)
        {
            case 0: return "<col=ffff00>Olmlet</col>";
            case 1: return "<col=ffff00>Puppadile</col>";
            case 2: return "<col=ffff00>Tektiny</col>";
            case 3: return "<col=ffff00>Vanguard</col>";
            case 4: return "<col=ffff00>Vasa Minirio</col>";
            default: return "<col=ffff00>Vespina</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        if (zone == 2 || zone == 4) return "Never got a proper fight. Holds a grudge.";
        switch (formIndex)
        {
            case 0: return "Cuddly today. Cataclysmic in a few centuries.";
            case 1: return "Lost the meat tree. Kept the appetite.";
            case 2: return "Hammer first, talk later.";
            case 3: return "Practising formations alone.";
            case 4: return "Hereditary problem made physical.";
            default: return "Has opinions about prayer abuse.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1:
            case 3: return 6;
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
            case 1:
            case 2: return ZONE1_LOBBY_EAST;
            default: return ZONE2_LOBBY_WEST;
        }
    }

    // Zone 1/2 - CoX Lobby (East)
    private static final int[][] ZONE1_LOBBY_EAST = {
            { 1249, 3569 }, { 1250, 3571 }, { 1252, 3570 }, { 1252, 3570 },
            { 1256, 3570 }, { 1257, 3571 }, { 1257, 3569 }, { 1259, 3567 },
            { 1261, 3567 }, { 1262, 3563 }, { 1263, 3561 }, { 1262, 3558 },
            { 1260, 3555 }, { 1258, 3553 }, { 1250, 3552 }, { 1245, 3550 },
            { 1242, 3558 }, { 1241, 3562 }, { 1248, 3562 }, { 1249, 3563 },
            { 1251, 3565 }, { 1251, 3568 }, { 1249, 3569 }
    };

    // Zone 3/4 - CoX Lobby (West)
    private static final int[][] ZONE2_LOBBY_WEST = {
            { 1241, 3566 }, { 1236, 3570 }, { 1231, 3570 }, { 1228, 3567 },
            { 1225, 3566 }, { 1225, 3557 }, { 1225, 3552 }, { 1228, 3549 },
            { 1231, 3548 }, { 1239, 3548 }, { 1243, 3552 }, { 1246, 3554 },
            { 1244, 3560 }, { 1241, 3564 }, { 1241, 3566 }
    };
}

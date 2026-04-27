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


public class FarmingGuildArea extends RoamingArea
{
    private final int zone;

    private FarmingGuildArea(int zone) { this.zone = zone; }

    public static FarmingGuildArea zone1() { return new FarmingGuildArea(1); }
    public static FarmingGuildArea zone2() { return new FarmingGuildArea(2); }
    public static FarmingGuildArea zone3() { return new FarmingGuildArea(3); }
    public static FarmingGuildArea zone4() { return new FarmingGuildArea(4); }
    public static FarmingGuildArea zone5() { return new FarmingGuildArea(5); }

    @Override
    public String getAreaId() { return "farming_guild_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        return new int[]{
                NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                NpcID.TANGLEROOT_9496, NpcID.TANGLEROOT_9497, NpcID.TANGLEROOT_9498,
                NpcID.TANGLEROOT_9499, NpcID.TANGLEROOT_9500, NpcID.TANGLEROOT_9501
        };
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        return spawnIndex % nForms;
    }

    @Override
    public String[] getSpawnNames()
    {
        String[] names = new String[getSpawnCount()];
        Arrays.fill(names, "Tangleroot");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Tangleroot</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Tending the Guild with quiet authority.";
            case 2: return "Considers the spirit tree a colleague.";
            case 3: return "Approves of this season's compost.";
            case 4: return "On first-name terms with every herb here.";
            default: return "Considers the redwood a distant relative.";
        }
    }

    @Override
    public int getSpawnCount() { return 4; }

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
            case 1: return ZONE1_MAIN;
            case 2: return ZONE2_NORTH;
            case 3: return ZONE3_WEST;
            case 4: return ZONE4_EAST;
            default: return ZONE5_REDWOOD;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 3) return FORBIDDEN_ZONE3;
        if (zone == 5) return FORBIDDEN_ZONE5;
        return new int[0][][];
    }

    // Zone 1 - Farming Guild (Main)
    private static final int[][] ZONE1_MAIN = {
            { 1248, 3745 }, { 1249, 3745 }, { 1251, 3745 }, { 1250, 3738 },
            { 1251, 3737 }, { 1252, 3733 }, { 1252, 3731 }, { 1254, 3731 },
            { 1255, 3728 }, { 1252, 3728 }, { 1251, 3723 }, { 1247, 3723 },
            { 1246, 3727 }, { 1245, 3727 }, { 1245, 3728 }, { 1244, 3728 },
            { 1244, 3732 }, { 1245, 3732 }, { 1245, 3738 }, { 1242, 3738 },
            { 1242, 3744 }, { 1245, 3744 }, { 1247, 3744 }, { 1248, 3745 }
    };

    // Zone 2 - Farming Guild (North)
    private static final int[][] ZONE2_NORTH = {
            { 1240, 3757 }, { 1244, 3757 }, { 1245, 3758 }, { 1246, 3758 },
            { 1246, 3759 }, { 1250, 3759 }, { 1250, 3758 }, { 1252, 3756 },
            { 1253, 3756 }, { 1254, 3757 }, { 1255, 3757 }, { 1256, 3756 },
            { 1258, 3755 }, { 1258, 3752 }, { 1251, 3752 }, { 1251, 3748 },
            { 1251, 3747 }, { 1249, 3747 }, { 1247, 3747 }, { 1246, 3748 },
            { 1246, 3752 }, { 1242, 3752 }, { 1242, 3753 }, { 1240, 3753 },
            { 1240, 3757 }
    };

    // Zone 3 - Farming Guild (West)
    private static final int[][] ZONE3_WEST = {
            { 1242, 3732 }, { 1242, 3735 }, { 1238, 3739 }, { 1235, 3739 },
            { 1234, 3734 }, { 1229, 3735 }, { 1227, 3733 }, { 1227, 3728 },
            { 1226, 3726 }, { 1226, 3725 }, { 1235, 3725 }, { 1240, 3723 },
            { 1242, 3725 }, { 1242, 3728 }, { 1243, 3728 }, { 1243, 3729 },
            { 1244, 3729 }, { 1244, 3731 }, { 1242, 3731 }, { 1242, 3732 }
    };

    // Zone 3 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE3 = {
            // Hespori Entrance
            {
                    { 1230, 3731 }, { 1231, 3732 }, { 1233, 3732 }, { 1234, 3731 },
                    { 1234, 3729 }, { 1233, 3728 }, { 1231, 3728 }, { 1230, 3729 },
                    { 1230, 3731 }
            }
    };

    // Zone 4 - Farming Guild (East)
    private static final int[][] ZONE4_EAST = {
            { 1255, 3732 }, { 1259, 3732 }, { 1260, 3733 }, { 1262, 3733 },
            { 1262, 3735 }, { 1262, 3737 }, { 1260, 3739 }, { 1261, 3740 },
            { 1263, 3740 }, { 1263, 3744 }, { 1263, 3746 }, { 1268, 3746 },
            { 1268, 3745 }, { 1266, 3743 }, { 1266, 3740 }, { 1266, 3739 },
            { 1267, 3731 }, { 1273, 3731 }, { 1272, 3729 }, { 1266, 3728 },
            { 1266, 3723 }, { 1265, 3721 }, { 1263, 3722 }, { 1262, 3723 },
            { 1262, 3727 }, { 1259, 3727 }, { 1255, 3727 }, { 1255, 3728 },
            { 1255, 3732 }
    };

    // Zone 5 - Farming Guild (Redwood Floor)
    private static final int[][] ZONE5_REDWOOD = {
            { 1227, 3761 }, { 1235, 3761 }, { 1237, 3759 }, { 1237, 3750 },
            { 1235, 3748 }, { 1224, 3748 }, { 1222, 3750 }, { 1222, 3759 },
            { 1224, 3761 }, { 1227, 3761 }
    };

    // Zone 5 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE5 = {
            // Redwood
            {
                    { 1225, 3759 }, { 1233, 3759 }, { 1233, 3751 }, { 1225, 3751 },
                    { 1225, 3759 }
            }
    };
}

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


public class KaruulmHydraArea extends RoamingArea
{
    private KaruulmHydraArea() {}

    public static KaruulmHydraArea zone() { return new KaruulmHydraArea(); }

    @Override
    public String getAreaId() { return "karuulm_hydra"; }

    @Override
    public int[] getPetNpcIds()
    {
        return new int[]{
                NpcID.IKKLE_HYDRA,
                NpcID.IKKLE_HYDRA_8493,
                NpcID.IKKLE_HYDRA_8494,
                NpcID.IKKLE_HYDRA_8495
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
        Arrays.fill(names, "Ikkle Hydra");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Ikkle Hydra</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        return "It looks around 5'11. It's so tiny.";
    }

    @Override
    public int getSpawnCount() { return 25; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints() { return POLYGON; }

    @Override
    public int[][][] getForbiddenZonePoints() { return FORBIDDEN_ZONES; }

    // Outer boundary
    private static final int[][] POLYGON = {
            { 1277, 3780 }, { 1274, 3778 }, { 1270, 3778 }, { 1269, 3777 },
            { 1266, 3779 }, { 1264, 3779 }, { 1262, 3780 }, { 1263, 3782 },
            { 1267, 3783 }, { 1267, 3785 }, { 1267, 3786 }, { 1262, 3786 },
            { 1257, 3789 }, { 1256, 3789 }, { 1256, 3792 }, { 1261, 3794 },
            { 1261, 3801 }, { 1260, 3802 }, { 1260, 3805 }, { 1257, 3807 },
            { 1247, 3811 }, { 1246, 3814 }, { 1247, 3816 }, { 1249, 3814 },
            { 1250, 3813 }, { 1253, 3812 }, { 1255, 3813 }, { 1257, 3813 },
            { 1259, 3815 }, { 1266, 3821 }, { 1267, 3823 }, { 1268, 3825 },
            { 1268, 3827 }, { 1266, 3829 }, { 1264, 3830 }, { 1266, 3834 },
            { 1267, 3835 }, { 1272, 3837 }, { 1277, 3838 }, { 1280, 3839 },
            { 1283, 3843 }, { 1282, 3843 }, { 1284, 3847 }, { 1286, 3845 },
            { 1288, 3846 }, { 1288, 3849 }, { 1291, 3853 }, { 1294, 3851 },
            { 1297, 3850 }, { 1299, 3850 }, { 1301, 3852 }, { 1301, 3854 },
            { 1299, 3856 }, { 1302, 3861 }, { 1305, 3857 }, { 1307, 3857 },
            { 1310, 3854 }, { 1312, 3854 }, { 1317, 3848 }, { 1320, 3849 },
            { 1324, 3857 }, { 1325, 3857 }, { 1325, 3852 }, { 1326, 3852 },
            { 1327, 3849 }, { 1329, 3849 }, { 1330, 3848 }, { 1332, 3849 },
            { 1333, 3850 }, { 1336, 3848 }, { 1338, 3844 }, { 1340, 3845 },
            { 1342, 3843 }, { 1342, 3841 }, { 1342, 3840 }, { 1347, 3836 },
            { 1351, 3823 }, { 1350, 3820 }, { 1353, 3816 }, { 1352, 3812 },
            { 1346, 3805 }, { 1349, 3801 }, { 1347, 3798 }, { 1347, 3795 },
            { 1354, 3793 }, { 1357, 3792 }, { 1363, 3792 }, { 1366, 3789 },
            { 1370, 3791 }, { 1372, 3791 }, { 1374, 3789 }, { 1370, 3782 },
            { 1371, 3775 }, { 1360, 3771 }, { 1355, 3769 }, { 1357, 3765 },
            { 1361, 3766 }, { 1364, 3762 }, { 1362, 3757 }, { 1360, 3756 },
            { 1359, 3762 }, { 1355, 3763 }, { 1352, 3761 }, { 1346, 3759 },
            { 1343, 3758 }, { 1338, 3760 }, { 1335, 3763 }, { 1331, 3770 },
            { 1324, 3772 }, { 1323, 3767 }, { 1318, 3767 }, { 1317, 3772 },
            { 1311, 3772 }, { 1309, 3771 }, { 1304, 3771 }, { 1300, 3772 },
            { 1288, 3778 }, { 1286, 3779 }, { 1284, 3782 }, { 1281, 3783 }
    };

    // Restricted zones
    private static final int[][][] FORBIDDEN_ZONES = {
            // Slayer Master Area
            {
                    { 1305, 3793 }, { 1305, 3780 }, { 1315, 3780 },
                    { 1314, 3793 }, { 1309, 3795 }
            },

            // Bank Area
            {
                    { 1321, 3826 }, { 1321, 3822 }, { 1328, 3822 },
                    { 1328, 3827 }, { 1321, 3827 }
            }
    };
}
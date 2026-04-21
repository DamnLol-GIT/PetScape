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


public class MudskipperArea extends RoamingArea
{
    private final int zone;

    private MudskipperArea(int zone) { this.zone = zone; }

    public static MudskipperArea zone1() { return new MudskipperArea(1); }
    public static MudskipperArea zone2() { return new MudskipperArea(2); }
    public static MudskipperArea zone3() { return new MudskipperArea(3); }
    public static MudskipperArea zone4() { return new MudskipperArea(4); }
    public static MudskipperArea zone5() { return new MudskipperArea(5); }
    public static MudskipperArea zone6() { return new MudskipperArea(6); }

    @Override
    public String getAreaId() { return "mudskipper_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.BRAN, NpcID.RIC };
            case 2: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 3: return new int[]{ NpcID.BABY_CHINCHOMPA };
            case 4: return new int[]{ NpcID.HERON };
            case 6: return new int[]{ NpcID.BRAN };
            default: return new int[]{ NpcID.RIC };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 4; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 4 Bran(0), 4 Ric(1)
                return spawnIndex < 4 ? 0 : 1;
            case 2:
                // 6 Giant Squirrel(0), 4 Beaver(1), 4 Rocky(2)
                if (spawnIndex < 6)  return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            default:
                return 0;
        }
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
                for (int i = 0; i < n; i++)
                    names[i] = i < 4 ? "Bran" : "Ric";
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 3:
                Arrays.fill(names, "Baby Chinchompa");
                break;
            case 4:
                Arrays.fill(names, "Heron");
                break;
            case 6:
                Arrays.fill(names, "Bran");
                break;
            default:
                Arrays.fill(names, "Ric");
                break;
        }
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return formIndex == 0
                        ? "<col=ffff00>Bran</col>"
                        : "<col=ffff00>Ric</col>";
            case 2:
                switch (formIndex)
                {
                    case 0:  return "<col=ffff00>Giant Squirrel</col>";
                    case 1:  return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 3:
                return "<col=ffff00>Baby Chinchompa</col>";
            case 4:
                return "<col=ffff00>Heron</col>";
            case 6:
                return "<col=ffff00>Bran</col>";
            default:
                return "<col=ffff00>Ric</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return formIndex == 0
                        ? "A tiny tantrum in royal form."
                        : "The coolest guy in the room. Technically.";
            case 2:
                switch (formIndex)
                {
                    case 0:  return "Knows the fastest route to the beach.";
                    case 1:  return "A woodsman of modest goals and sharp incisors.";
                    default: return "Looks innocent. Isn't.";
                }
            case 3:
                return "Rare, radiant, and mildly unstable.";
            case 4:
                return "Already caught more than you have.";
            case 6:
                return "Melting a perfectly good patch of ice.";
            default:
                return "Ice to meet you.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 8;
            case 2: return 14;
            case 3: return 6;
            case 4: return 6;
            case 6: return 4;
            default: return 10;
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
            case 1: return ZONE1_DUNGEON_OUTER;
            case 2: return ZONE2_NORTH_DUNGEON;
            case 3: return ZONE3_MUDSKIPPER_POINT;
            case 4: return ZONE3_MUDSKIPPER_POINT;
            case 6: return ZONE6_DUNGEON_INNER;
            default: return ZONE5_ROYAL_TITAN;
        }
    }

    // Zone 1 - Dungeon Entrance (Outer)
    private static final int[][] ZONE1_DUNGEON_OUTER = {
            { 3003, 3158 }, { 3003, 3160 }, { 3002, 3161 }, { 3001, 3162 },
            { 3001, 3164 }, { 3003, 3165 }, { 3003, 3166 }, { 3006, 3166 },
            { 3007, 3167 }, { 3009, 3166 }, { 3011, 3165 }, { 3012, 3164 },
            { 3013, 3164 }, { 3014, 3163 }, { 3014, 3162 }, { 3014, 3160 },
            { 3015, 3160 }, { 3015, 3159 }, { 3015, 3157 }, { 3017, 3157 },
            { 3016, 3156 }, { 3014, 3155 }, { 3014, 3154 }, { 3014, 3153 },
            { 3015, 3152 }, { 3016, 3151 }, { 3017, 3151 }, { 3016, 3149 },
            { 3015, 3147 }, { 3013, 3147 }, { 3013, 3146 }, { 3011, 3145 },
            { 3010, 3144 }, { 3006, 3144 }, { 3005, 3147 }, { 3006, 3148 },
            { 3006, 3153 }, { 3005, 3154 }, { 3006, 3156 }, { 3005, 3158 },
            { 3003, 3158 }
    };

    // Zone 2 - North of Dungeon Entrance (Outer)
    private static final int[][] ZONE2_NORTH_DUNGEON = {
            { 2993, 3169 }, { 2994, 3166 }, { 2998, 3163 }, { 2999, 3161 },
            { 3000, 3158 }, { 3002, 3158 }, { 3006, 3158 }, { 3010, 3157 },
            { 3012, 3158 }, { 3014, 3157 }, { 3018, 3157 }, { 3019, 3161 },
            { 3019, 3164 }, { 3021, 3165 }, { 3025, 3165 }, { 3029, 3166 },
            { 3030, 3177 }, { 3026, 3185 }, { 3024, 3187 }, { 3023, 3186 },
            { 3021, 3183 }, { 3021, 3179 }, { 3021, 3177 }, { 3017, 3176 },
            { 3010, 3176 }, { 3003, 3175 }, { 2999, 3174 }, { 2996, 3172 },
            { 2993, 3169 }
    };

    // Zone 3/4 - Mudskipper Point
    private static final int[][] ZONE3_MUDSKIPPER_POINT = {
            { 2996, 3156 }, { 2998, 3158 }, { 3000, 3158 }, { 3001, 3158 },
            { 3002, 3155 }, { 3002, 3152 }, { 3002, 3150 }, { 3002, 3148 },
            { 3003, 3146 }, { 3002, 3144 }, { 3003, 3143 }, { 3003, 3141 },
            { 3005, 3141 }, { 3010, 3142 }, { 3010, 3140 }, { 3008, 3139 },
            { 3007, 3138 }, { 3005, 3136 }, { 3005, 3134 }, { 3004, 3132 },
            { 3003, 3130 }, { 3003, 3128 }, { 3004, 3125 }, { 3003, 3122 },
            { 3004, 3120 }, { 3003, 3117 }, { 3003, 3114 }, { 3004, 3113 },
            { 3004, 3112 }, { 3002, 3111 }, { 3000, 3109 }, { 2997, 3109 },
            { 2992, 3109 }, { 2989, 3109 }, { 2987, 3109 }, { 2985, 3108 },
            { 2984, 3109 }, { 2984, 3110 }, { 2984, 3113 }, { 2986, 3115 },
            { 2985, 3116 }, { 2986, 3120 }, { 2986, 3122 }, { 2989, 3123 },
            { 2989, 3125 }, { 2990, 3126 }, { 2991, 3126 }, { 2993, 3128 },
            { 2994, 3128 }, { 2996, 3129 }, { 2997, 3130 }, { 2999, 3131 },
            { 2999, 3133 }, { 3000, 3135 }, { 2999, 3137 }, { 2997, 3139 },
            { 2996, 3143 }, { 2995, 3145 }, { 2994, 3146 }, { 2995, 3148 },
            { 2996, 3151 }, { 2996, 3153 }, { 2996, 3156 }
    };

    // Zone 5 - Royal Titan Area
    private static final int[][] ZONE5_ROYAL_TITAN = {
            { 2956, 9583 }, { 2958, 9582 }, { 2959, 9581 }, { 2959, 9580 },
            { 2959, 9578 }, { 2958, 9578 }, { 2958, 9577 }, { 2957, 9576 },
            { 2956, 9575 }, { 2955, 9574 }, { 2955, 9573 }, { 2955, 9571 },
            { 2956, 9569 }, { 2955, 9568 }, { 2955, 9565 }, { 2956, 9565 },
            { 2957, 9564 }, { 2958, 9561 }, { 2959, 9561 }, { 2961, 9559 },
            { 2959, 9557 }, { 2960, 9556 }, { 2961, 9555 }, { 2962, 9555 },
            { 2964, 9555 }, { 2965, 9554 }, { 2967, 9554 }, { 2969, 9553 },
            { 2972, 9553 }, { 2973, 9554 }, { 2974, 9554 }, { 2975, 9555 },
            { 2975, 9556 }, { 2977, 9558 }, { 2978, 9556 }, { 2977, 9554 },
            { 2976, 9553 }, { 2973, 9550 }, { 2966, 9550 }, { 2964, 9552 },
            { 2958, 9552 }, { 2956, 9554 }, { 2956, 9558 }, { 2951, 9563 },
            { 2949, 9564 }, { 2947, 9565 }, { 2944, 9565 }, { 2942, 9567 },
            { 2942, 9570 }, { 2944, 9570 }, { 2946, 9571 }, { 2949, 9573 },
            { 2950, 9575 }, { 2951, 9578 }, { 2952, 9580 }, { 2953, 9580 },
            { 2955, 9582 }, { 2956, 9583 }
    };

    // Zone 6 - Dungeon Entrance (Inner)
    private static final int[][] ZONE6_DUNGEON_INNER = {
            { 2993, 9556 }, { 2994, 9558 }, { 2995, 9558 }, { 2996, 9556 },
            { 2996, 9555 }, { 2998, 9553 }, { 3000, 9553 }, { 3002, 9551 },
            { 3005, 9551 }, { 3006, 9552 }, { 3007, 9548 }, { 3004, 9545 },
            { 3003, 9545 }, { 2999, 9543 }, { 2998, 9542 }, { 2995, 9542 },
            { 2993, 9543 }, { 2991, 9545 }, { 2991, 9550 }, { 2993, 9552 },
            { 2993, 9556 }
    };
}
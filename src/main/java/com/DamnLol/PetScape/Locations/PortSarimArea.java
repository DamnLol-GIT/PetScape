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


public class PortSarimArea extends RoamingArea
{
    private final int zone;

    private PortSarimArea(int zone) { this.zone = zone; }

    public static PortSarimArea zone1() { return new PortSarimArea(1); }
    public static PortSarimArea zone2() { return new PortSarimArea(2); }
    public static PortSarimArea zone3() { return new PortSarimArea(3); }
    public static PortSarimArea zone4() { return new PortSarimArea(4); }
    public static PortSarimArea zone5() { return new PortSarimArea(5); }
    public static PortSarimArea zone6() { return new PortSarimArea(6); }
    public static PortSarimArea zone7() { return new PortSarimArea(7); }
    public static PortSarimArea zone8() { return new PortSarimArea(8); }
    public static PortSarimArea zone9() { return new PortSarimArea(9); }
    public static PortSarimArea zone10() { return new PortSarimArea(10); }

    @Override
    public String getAreaId() { return "port_sarim_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.ROCKY };
            case 2: return new int[]{ NpcID.ROCKY, NpcID.GIANT_SQUIRREL, NpcID.BEAVER };
            case 3: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 4: return new int[]{
                    NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                    NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                    NpcID.TANGLEROOT_9496, NpcID.TANGLEROOT_9497, NpcID.TANGLEROOT_9498,
                    NpcID.TANGLEROOT_9499, NpcID.TANGLEROOT_9500, NpcID.TANGLEROOT_9501
            };
            case 5: return new int[]{ NpcID.BEAVER, NpcID.ROCKY, NpcID.GIANT_SQUIRREL };
            case 6: return new int[]{ NpcID.HERON };
            case 7: return new int[]{ NpcID.ROCKY, NpcID.GIANT_SQUIRREL };
            case 8: return new int[]{ NpcID.HERON, NpcID.GREAT_BLUE_HERON };
            case 9: return new int[]{ NpcID.SCURRY };
            default: return new int[]{ NpcID.BONE_SQUIRREL };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 6 || zone == 8; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 2:
                // 6 Rocky(0), 8 Giant Squirrel(1), 6 Beaver(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 14) return 1;
                return 2;
            case 3:
                // 3 Giant Squirrel(0), 2 Rocky(1), 2 Beaver(2)
                if (spawnIndex < 3) return 0;
                if (spawnIndex < 5) return 1;
                return 2;
            case 4:
                return spawnIndex % nForms;
            case 5:
                // 2 Beaver(0), 2 Rocky(1), 4 Giant Squirrel(2)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 4) return 1;
                return 2;
            case 7:
                // 2 Rocky(0), 2 Giant Squirrel(1)
                return spawnIndex < 2 ? 0 : 1;
            case 8:
                // 6 Heron(0), 2 Great Blue Heron(1)
                return spawnIndex < 6 ? 0 : 1;
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
                Arrays.fill(names, "Rocky");
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Rocky";
                    else if (i < 14) names[i] = "Giant Squirrel";
                    else names[i] = "Beaver";
                }
                break;
            case 3:
                for (int i = 0; i < n; i++)
                {
                    if (i < 3) names[i] = "Giant Squirrel";
                    else if (i < 5) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 4:
                Arrays.fill(names, "Tangleroot");
                break;
            case 5:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Beaver";
                    else if (i < 4) names[i] = "Rocky";
                    else names[i] = "Giant Squirrel";
                }
                break;
            case 6:
                Arrays.fill(names, "Heron");
                break;
            case 7:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Rocky" : "Giant Squirrel";
                break;
            case 8:
                for (int i = 0; i < n; i++)
                    names[i] = i < 6 ? "Heron" : "Great Blue Heron";
                break;
            case 9:
                Arrays.fill(names, "Scurry");
                break;
            default:
                Arrays.fill(names, "Bone Squirrel");
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
                return "<col=ffff00>Rocky</col>";
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Rocky</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 3:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 4:
                return "<col=ffff00>Tangleroot</col>";
            case 5:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beaver</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Giant Squirrel</col>";
                }
            case 6:
                return "<col=ffff00>Heron</col>";
            case 7:
                return formIndex == 0
                        ? "<col=ffff00>Rocky</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
            case 8:
                return formIndex == 0
                        ? "<col=ffff00>Heron</col>"
                        : "<col=ffff00>Great Blue Heron</col>";
            case 9:
                return "<col=ffff00>Scurry</col>";
            default:
                return "<col=ffff00>Bone Squirrel</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return "Already planned three ways of escape.";
            case 2:
                switch (formIndex)
                {
                    case 0: return "A scoundrel in a town of scoundrels.";
                    case 1: return "Treats the whole port as an agility course.";
                    default: return "Every plank gets a second look.";
                }
            case 3:
                switch (formIndex)
                {
                    case 0: return "The only thing faster than the tide.";
                    case 1: return "Collects the taxes. Unofficially.";
                    default: return "Would audit every hull, given permission.";
                }
            case 4:
                return "Considers the sea a very large watering can.";
            case 5:
                switch (formIndex)
                {
                    case 0: return "Has opinions on the boat construction.";
                    case 1: return "The locals know to keep an eye on him.";
                    default: return "Too fast for the sailors to catch";
                }
            case 6:
                return "Already out-fished the sailors.";
            case 7:
                return formIndex == 0
                        ? "Waiting for a crate to be set down."
                        : "Outruns every seagull here.";
            case 8:
                return formIndex == 0
                        ? "Outlasts the tides. Outwaits the fishermen."
                        : "The docks most distinguished fisherman.";
            case 9:
                return "Reigning champion of the pits.";
            default:
                return "Remembers a wedding here once.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 6;
            case 2: return 20;
            case 3: return 7;
            case 4: return 3;
            case 5: return 8;
            case 6: return 2;
            case 7: return 4;
            case 8: return 8;
            case 9: return 2;
            default: return 3;
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
            case 1: return ZONE1_JAIL;
            case 2: return ZONE2_WEST_WOODS;
            case 3: return ZONE3_NORTH_WOODS;
            case 4: return ZONE4_FARMING_SPOT;
            case 5: return ZONE5_TOWN_EDGE;
            case 6: return ZONE5_TOWN_EDGE;
            case 7: return ZONE7_DOCKS;
            case 8: return ZONE7_DOCKS;
            case 9: return ZONE9_RAT_PITS;
            default: return ZONE10_GRAVEYARD;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 5 || zone == 6) return FORBIDDEN_TOWN_EDGE;
        return new int[0][][];
    }

    // Zone 1 - Jail
    private static final int[][] ZONE1_JAIL = {
            { 3004, 3196 }, { 3005, 3192 }, { 3006, 3186 }, { 3008, 3178 },
            { 3017, 3175 }, { 3023, 3176 }, { 3026, 3179 }, { 3026, 3185 },
            { 3026, 3188 }, { 3022, 3193 }, { 3018, 3198 }, { 3016, 3200 },
            { 3008, 3200 }, { 3004, 3196 }
    };

    // Zone 2 - West Woods
    private static final int[][] ZONE2_WEST_WOODS = {
            { 3017, 3262 }, { 3018, 3267 }, { 3021, 3268 }, { 3023, 3269 },
            { 3027, 3270 }, { 3028, 3272 }, { 3025, 3273 }, { 3021, 3273 },
            { 3014, 3274 }, { 3011, 3275 }, { 2999, 3277 }, { 2993, 3277 },
            { 2988, 3273 }, { 2985, 3268 }, { 2980, 3263 }, { 2982, 3258 },
            { 2983, 3255 }, { 2992, 3250 }, { 2993, 3247 }, { 2993, 3242 },
            { 2994, 3238 }, { 2992, 3234 }, { 2988, 3227 }, { 2986, 3218 },
            { 2987, 3215 }, { 2986, 3212 }, { 2984, 3211 }, { 2982, 3207 },
            { 2983, 3200 }, { 2985, 3196 }, { 2987, 3194 }, { 2992, 3191 },
            { 2997, 3184 }, { 3002, 3183 }, { 3006, 3179 }, { 3007, 3182 },
            { 3007, 3201 }, { 3007, 3209 }, { 3008, 3211 }, { 3008, 3220 },
            { 3009, 3227 }, { 3007, 3234 }, { 3009, 3248 }, { 3009, 3254 },
            { 3011, 3263 }, { 3017, 3262 }
    };

    // Zone 3 - North Woods
    private static final int[][] ZONE3_NORTH_WOODS = {
            { 3067, 3274 }, { 3067, 3280 }, { 3055, 3280 }, { 3047, 3280 },
            { 3043, 3280 }, { 3038, 3280 }, { 3032, 3279 }, { 3026, 3277 },
            { 3024, 3272 }, { 3024, 3269 }, { 3026, 3266 }, { 3031, 3266 },
            { 3033, 3264 }, { 3034, 3261 }, { 3034, 3257 }, { 3033, 3253 },
            { 3035, 3252 }, { 3040, 3254 }, { 3040, 3259 }, { 3044, 3262 },
            { 3050, 3262 }, { 3062, 3262 }, { 3066, 3266 }, { 3067, 3273 },
            { 3067, 3274 }
    };

    // Zone 4 - Farming Spot
    private static final int[][] ZONE4_FARMING_SPOT = {
            { 3057, 3262 }, { 3060, 3263 }, { 3062, 3264 }, { 3065, 3263 },
            { 3066, 3260 }, { 3066, 3257 }, { 3064, 3254 }, { 3063, 3252 },
            { 3059, 3251 }, { 3056, 3252 }, { 3056, 3258 }, { 3056, 3262 },
            { 3057, 3262 }
    };

    // Zone 5/6 - Town/Beach Edge
    private static final int[][] ZONE5_TOWN_EDGE = {
            { 3064, 3253 }, { 3062, 3254 }, { 3056, 3255 }, { 3043, 3255 },
            { 3043, 3258 }, { 3031, 3259 }, { 3022, 3259 }, { 3017, 3256 },
            { 3016, 3250 }, { 3016, 3244 }, { 3020, 3241 }, { 3020, 3236 },
            { 3020, 3229 }, { 3019, 3222 }, { 3018, 3217 }, { 3014, 3211 },
            { 3017, 3208 }, { 3017, 3203 }, { 3016, 3197 }, { 3017, 3197 },
            { 3017, 3191 }, { 3021, 3191 }, { 3021, 3179 }, { 3029, 3175 },
            { 3032, 3172 }, { 3035, 3172 }, { 3034, 3175 }, { 3032, 3178 },
            { 3030, 3180 }, { 3028, 3183 }, { 3027, 3185 }, { 3028, 3186 },
            { 3026, 3190 }, { 3024, 3192 }, { 3022, 3195 }, { 3020, 3199 },
            { 3020, 3212 }, { 3020, 3215 }, { 3022, 3220 }, { 3024, 3224 },
            { 3024, 3227 }, { 3023, 3230 }, { 3022, 3231 }, { 3024, 3238 },
            { 3026, 3241 }, { 3031, 3241 }, { 3034, 3244 }, { 3036, 3245 },
            { 3037, 3245 }, { 3039, 3247 }, { 3044, 3250 }, { 3049, 3251 },
            { 3053, 3252 }, { 3055, 3251 }, { 3058, 3250 }, { 3061, 3251 },
            { 3064, 3251 }, { 3064, 3253 }
    };

    // Zone 5/6 forbidden Zones
    private static final int[][][] FORBIDDEN_TOWN_EDGE = {
            // Store
            {
                    { 3023, 3254 }, { 3031, 3254 }, { 3031, 3245 }, { 3023, 3245 },
                    { 3023, 3254 }
            }
    };

    // Zone 7/8 - Docks
    private static final int[][] ZONE7_DOCKS = {
            { 3055, 3252 }, { 3055, 3250 }, { 3056, 3250 }, { 3056, 3245 },
            { 3044, 3245 }, { 3044, 3238 }, { 3053, 3238 }, { 3053, 3234 },
            { 3030, 3234 }, { 3030, 3205 }, { 3051, 3205 }, { 3051, 3202 },
            { 3033, 3202 }, { 3033, 3195 }, { 3051, 3195 }, { 3051, 3192 },
            { 3027, 3192 }, { 3027, 3197 }, { 3030, 3197 }, { 3030, 3202 },
            { 3019, 3202 }, { 3019, 3211 }, { 3026, 3211 }, { 3026, 3216 },
            { 3019, 3216 }, { 3019, 3220 }, { 3026, 3220 }, { 3026, 3241 },
            { 3030, 3241 }, { 3030, 3238 }, { 3040, 3238 }, { 3040, 3250 },
            { 3053, 3250 }, { 3053, 3252 }, { 3055, 3252 }
    };

    // Zone 9 - Rat Pits
    private static final int[][] ZONE9_RAT_PITS = {
            { 3015, 3236 }, { 3019, 3236 }, { 3020, 3234 }, { 3020, 3233 },
            { 3020, 3231 }, { 3018, 3230 }, { 3014, 3230 }, { 3013, 3232 },
            { 3015, 3234 }, { 3015, 3236 }
    };

    // Zone 10 - Graveyard
    private static final int[][] ZONE10_GRAVEYARD = {
            { 2996, 3190 }, { 2998, 3190 }, { 3000, 3190 }, { 3002, 3189 },
            { 3002, 3186 }, { 3000, 3185 }, { 2997, 3185 }, { 2995, 3187 },
            { 2995, 3189 }, { 2996, 3190 }
    };
}
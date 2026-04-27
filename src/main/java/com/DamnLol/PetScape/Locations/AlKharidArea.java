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


public class AlKharidArea extends RoamingArea
{
    private final int zone;

    private AlKharidArea(int zone) { this.zone = zone; }

    public static AlKharidArea zone1() { return new AlKharidArea(1); }
    public static AlKharidArea zone2() { return new AlKharidArea(2); }
    public static AlKharidArea zone3() { return new AlKharidArea(3); }
    public static AlKharidArea zone4() { return new AlKharidArea(4); }
    public static AlKharidArea zone5() { return new AlKharidArea(5); }
    public static AlKharidArea zone6() { return new AlKharidArea(6); }
    public static AlKharidArea zone7() { return new AlKharidArea(7); }

    @Override
    public String getAreaId() { return "alkharid_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BABY_CHINCHOMPA_6759, NpcID.ZIGGY };
            case 2: return new int[]{ NpcID.BLOODHOUND, NpcID.QUETZIN };
            case 3: return new int[]{ NpcID.BEEF, NpcID.GIANT_SQUIRREL, NpcID.ZIGGY, NpcID.BEAVER };
            case 4: return new int[]{ NpcID.KALPHITE_PRINCESS, NpcID.BABY_CHINCHOMPA_6759, NpcID.GIANT_SQUIRREL };
            case 5: return new int[]{
                    NpcID.ROCK_GOLEM,
                    NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                    NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                    NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                    NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                    NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                    NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
            };
            case 6: return new int[]{ NpcID.QUETZIN };
            default: return new int[]{ NpcID.BABY_CHINCHOMPA_6759 };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 2 || zone == 6; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                if (spawnIndex < 12) return 0;
                if (spawnIndex < 24) return 1;
                return 2;
            case 2:
                return spawnIndex < 2 ? 0 : 1;
            case 3:
                if (spawnIndex < 1) return 0;
                if (spawnIndex < 7) return 1;
                if (spawnIndex < 11) return 2;
                return 3;
            case 4:
                if (spawnIndex < 1) return 0;
                if (spawnIndex < 5) return 1;
                return 2;
            case 5:
                return spawnIndex % nForms;
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
                {
                    if (i < 12) names[i] = "Giant Squirrel";
                    else if (i < 24) names[i] = "Baby Chinchompa";
                    else names[i] = "Rocky";
                }
                break;
            case 2:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Bloodhound" : "Quetzin";
                break;
            case 3:
                for (int i = 0; i < n; i++)
                {
                    if (i < 1) names[i] = "Beef";
                    else if (i < 7) names[i] = "Giant Squirrel";
                    else if (i < 11) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 4:
                for (int i = 0; i < n; i++)
                {
                    if (i < 1) names[i] = "Kalphite Princess";
                    else if (i < 5) names[i] = "Baby Chinchompa";
                    else names[i] = "Giant Squirrel";
                }
                break;
            case 5:
                Arrays.fill(names, "Rock Golem");
                break;
            case 6:
                Arrays.fill(names, "Quetzin");
                break;
            default:
                Arrays.fill(names, "Baby Chinchompa");
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
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Desert Chinchompa</col>";
                    default: return "<col=ffff00>Ziggy</col>";
                }
            case 2:
                return formIndex == 0
                        ? "<col=ffff00>Bloodhound</col>"
                        : "<col=ffff00>Quetzin</col>";
            case 3:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beef</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Ziggy</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 4:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Kalphite Scout</col>";
                    case 1: return "<col=ffff00>Desert Chinchompa</col>";
                    default: return "<col=ffff00>Giant Squirrel</col>";
                }
            case 5:
                return "<col=ffff00>Rock Golem</col>";
            case 6:
                return "<col=ffff00>Quetzin</col>";
            default:
                return "<col=ffff00>Desert Chinchompa</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                switch (formIndex)
                {
                    case 0: return "Running laps around the caravan routes.";
                    case 1: return "A desert wanderer of questionable stability.";
                    default: return "Blends in better than he should.";
                }
            case 2:
                return formIndex == 0
                        ? "Sniffing for spies. Or scraps."
                        : "Imported. Expensive. Indifferent.";
            case 3:
                switch (formIndex)
                {
                    case 0: return "Pretty sure the grass was greener back there.";
                    case 1: return "Sand slows him down. Not by much.";
                    case 2: return "Watching the market from a polite distance.";
                    default: return "Looking very hard for trees. Finding cacti.";
                }
            case 4:
                switch (formIndex)
                {
                    case 0: return "The hive's tiniest informant.";
                    case 1: return "Glittering quietly. Ticking quietly.";
                    default: return "Outpacing the heat, barely.";
                }
            case 5:
                return "Dense in all the right ways.";
            case 6:
                return "Used to be a rich bird. Used to be.";
            default:
                return "Short fuse, shorter odds.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 30;
            case 2: return 6;
            case 3: return 13;
            case 4: return 11;
            case 5: return 12;
            case 6: return 6;
            default: return 4;
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
            case 1: return ZONE1_CITY_PASS;
            case 2: return ZONE2_PALACE;
            case 3: return ZONE3_GRASS_EDGE;
            case 4: return ZONE4_SAND;
            case 5: return ZONE5_MINING;
            default: return ZONE6_EMIR_ENTRANCE;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 1) return FORBIDDEN_ZONE1;
        return new int[0][][];
    }

    // Zone 1 - City/Pass
    private static final int[][] ZONE1_CITY_PASS = {
            { 3303, 3118 }, { 3300, 3120 }, { 3299, 3124 }, { 3300, 3127 },
            { 3302, 3130 }, { 3302, 3134 }, { 3301, 3135 }, { 3300, 3139 },
            { 3295, 3139 }, { 3291, 3140 }, { 3289, 3142 }, { 3285, 3146 },
            { 3281, 3145 }, { 3275, 3143 }, { 3273, 3146 }, { 3270, 3149 },
            { 3266, 3156 }, { 3261, 3165 }, { 3258, 3170 }, { 3258, 3175 },
            { 3261, 3183 }, { 3264, 3187 }, { 3267, 3190 }, { 3267, 3198 },
            { 3267, 3202 }, { 3271, 3210 }, { 3271, 3222 }, { 3279, 3220 },
            { 3290, 3216 }, { 3294, 3218 }, { 3295, 3220 }, { 3298, 3219 },
            { 3303, 3215 }, { 3306, 3217 }, { 3310, 3221 }, { 3311, 3222 },
            { 3314, 3222 }, { 3318, 3222 }, { 3320, 3219 }, { 3322, 3213 },
            { 3323, 3209 }, { 3322, 3201 }, { 3318, 3199 }, { 3316, 3194 },
            { 3319, 3188 }, { 3326, 3193 }, { 3333, 3193 }, { 3333, 3179 },
            { 3334, 3170 }, { 3331, 3165 }, { 3333, 3161 }, { 3332, 3155 },
            { 3328, 3154 }, { 3325, 3151 }, { 3325, 3148 }, { 3322, 3148 },
            { 3322, 3145 }, { 3320, 3143 }, { 3315, 3142 }, { 3314, 3138 },
            { 3311, 3138 }, { 3308, 3139 }, { 3306, 3138 }, { 3304, 3135 },
            { 3306, 3130 }, { 3308, 3127 }, { 3307, 3122 }, { 3305, 3119 },
            { 3303, 3118 }
    };

    // Zone 1 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE1 = {
            // Bank
            {
                    { 3264, 3174 }, { 3273, 3174 }, { 3273, 3160 }, { 3264, 3160 },
                    { 3264, 3174 }
            },

            // Palace
            {
                    { 3281, 3179 }, { 3305, 3179 }, { 3305, 3158 }, { 3281, 3158 },
                    { 3281, 3179 }
            }
    };

    // Zone 2 - Palace
    private static final int[][] ZONE2_PALACE = {
            { 3281, 3179 }, { 3305, 3179 }, { 3305, 3158 }, { 3281, 3158 },
            { 3281, 3179 }
    };

    // Zone 3 - Grass Edge (West)
    private static final int[][] ZONE3_GRASS_EDGE = {
            { 3332, 3313 }, { 3316, 3317 }, { 3309, 3322 }, { 3306, 3324 },
            { 3303, 3326 }, { 3297, 3326 }, { 3293, 3324 }, { 3289, 3322 },
            { 3287, 3321 }, { 3282, 3321 }, { 3280, 3319 }, { 3278, 3318 },
            { 3275, 3314 }, { 3275, 3308 }, { 3275, 3303 }, { 3274, 3299 },
            { 3274, 3296 }, { 3274, 3294 }, { 3274, 3292 }, { 3273, 3288 },
            { 3273, 3284 }, { 3273, 3280 }, { 3272, 3277 }, { 3270, 3274 },
            { 3270, 3270 }, { 3270, 3267 }, { 3271, 3265 }, { 3273, 3261 },
            { 3280, 3254 }, { 3279, 3242 }, { 3276, 3236 }, { 3273, 3231 },
            { 3275, 3224 }, { 3275, 3219 }, { 3275, 3217 }, { 3275, 3213 },
            { 3274, 3210 }, { 3274, 3207 }, { 3272, 3203 }, { 3269, 3200 },
            { 3266, 3200 }, { 3265, 3201 }, { 3268, 3204 }, { 3268, 3220 },
            { 3268, 3222 }, { 3269, 3224 }, { 3269, 3231 }, { 3268, 3234 },
            { 3268, 3237 }, { 3268, 3239 }, { 3267, 3241 }, { 3267, 3247 },
            { 3266, 3249 }, { 3266, 3254 }, { 3267, 3255 }, { 3267, 3269 },
            { 3267, 3286 }, { 3267, 3298 }, { 3267, 3322 }, { 3271, 3326 },
            { 3271, 3329 }, { 3275, 3330 }, { 3297, 3330 }, { 3307, 3329 },
            { 3317, 3329 }, { 3324, 3326 }, { 3324, 3323 }, { 3329, 3318 },
            { 3332, 3313 }
    };

    // Zone 4 - Sand Area (Outside Mining Zone)
    private static final int[][] ZONE4_SAND = {
            { 3314, 3318 }, { 3321, 3313 }, { 3329, 3311 }, { 3334, 3305 },
            { 3334, 3301 }, { 3336, 3297 }, { 3336, 3294 }, { 3333, 3292 },
            { 3330, 3289 }, { 3328, 3286 }, { 3328, 3282 }, { 3329, 3279 },
            { 3330, 3274 }, { 3331, 3272 }, { 3330, 3268 }, { 3324, 3267 },
            { 3323, 3265 }, { 3322, 3255 }, { 3321, 3251 }, { 3320, 3249 },
            { 3316, 3248 }, { 3312, 3247 }, { 3310, 3246 }, { 3310, 3243 },
            { 3310, 3238 }, { 3310, 3236 }, { 3311, 3234 }, { 3311, 3231 },
            { 3304, 3230 }, { 3300, 3228 }, { 3294, 3229 }, { 3291, 3229 },
            { 3288, 3231 }, { 3287, 3236 }, { 3285, 3240 }, { 3283, 3244 },
            { 3288, 3257 }, { 3281, 3260 }, { 3275, 3265 }, { 3272, 3269 },
            { 3276, 3280 }, { 3283, 3278 }, { 3289, 3269 }, { 3293, 3266 },
            { 3300, 3265 }, { 3307, 3268 }, { 3311, 3271 }, { 3314, 3277 },
            { 3318, 3280 }, { 3320, 3285 }, { 3319, 3291 }, { 3319, 3296 },
            { 3320, 3298 }, { 3321, 3301 }, { 3319, 3307 }, { 3316, 3312 },
            { 3313, 3317 }, { 3314, 3318 }
    };

    // Zone 5 - Mining Zone
    private static final int[][] ZONE5_MINING = {
            { 3302, 3320 }, { 3305, 3320 }, { 3309, 3320 }, { 3311, 3317 },
            { 3313, 3316 }, { 3314, 3314 }, { 3315, 3313 }, { 3317, 3311 },
            { 3318, 3309 }, { 3318, 3306 }, { 3317, 3304 }, { 3317, 3301 },
            { 3318, 3300 }, { 3318, 3297 }, { 3318, 3294 }, { 3317, 3293 },
            { 3317, 3291 }, { 3317, 3289 }, { 3317, 3288 }, { 3316, 3286 },
            { 3314, 3282 }, { 3313, 3280 }, { 3313, 3278 }, { 3312, 3275 },
            { 3310, 3272 }, { 3307, 3271 }, { 3303, 3271 }, { 3298, 3272 },
            { 3295, 3270 }, { 3290, 3270 }, { 3289, 3272 }, { 3287, 3275 },
            { 3284, 3281 }, { 3283, 3285 }, { 3284, 3286 }, { 3288, 3289 },
            { 3287, 3291 }, { 3287, 3294 }, { 3285, 3296 }, { 3285, 3299 },
            { 3285, 3301 }, { 3287, 3302 }, { 3287, 3306 }, { 3288, 3306 },
            { 3289, 3309 }, { 3290, 3310 }, { 3291, 3311 }, { 3292, 3314 },
            { 3292, 3316 }, { 3293, 3317 }, { 3294, 3318 }, { 3294, 3319 },
            { 3302, 3320 }
    };

    // Zone 6/7 - Emir Entrance
    private static final int[][] ZONE6_EMIR_ENTRANCE = {
            { 3317, 3245 }, { 3319, 3248 }, { 3326, 3248 }, { 3326, 3241 },
            { 3329, 3239 }, { 3329, 3224 }, { 3324, 3223 }, { 3321, 3224 },
            { 3315, 3224 }, { 3316, 3228 }, { 3312, 3229 }, { 3312, 3236 },
            { 3312, 3238 }, { 3317, 3238 }, { 3317, 3245 }
    };
}
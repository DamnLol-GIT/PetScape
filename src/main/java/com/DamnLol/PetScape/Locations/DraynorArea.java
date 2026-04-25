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


public class DraynorArea extends RoamingArea
{
    private final int zone;

    private DraynorArea(int zone) { this.zone = zone; }

    public static DraynorArea zone1() { return new DraynorArea(1); }
    public static DraynorArea zone2() { return new DraynorArea(2); }
    public static DraynorArea zone3() { return new DraynorArea(3); }
    public static DraynorArea zone4() { return new DraynorArea(4); }
    public static DraynorArea zone5() { return new DraynorArea(5); }
    public static DraynorArea zone6() { return new DraynorArea(6); }
    public static DraynorArea zone7() { return new DraynorArea(7); }
    public static DraynorArea zone8() { return new DraynorArea(8); }
    public static DraynorArea zone9() { return new DraynorArea(9); }
    public static DraynorArea zone10() { return new DraynorArea(10); }
    public static DraynorArea zone11() { return new DraynorArea(11); }

    @Override
    public String getAreaId() { return "draynor_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.BONE_SQUIRREL, NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 2: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 3: return new int[]{ NpcID.ROCKY, NpcID.GIANT_SQUIRREL };
            case 4: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 5: return new int[]{ NpcID.HERON };
            case 6: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER };
            case 7: return new int[]{
                    NpcID.BONE_SQUIRREL,
                    NpcID.RIFT_GUARDIAN,
                    NpcID.RIFT_GUARDIAN_7338, NpcID.RIFT_GUARDIAN_7339, NpcID.RIFT_GUARDIAN_7340,
                    NpcID.RIFT_GUARDIAN_7341, NpcID.RIFT_GUARDIAN_7342, NpcID.RIFT_GUARDIAN_7343,
                    NpcID.RIFT_GUARDIAN_7344, NpcID.RIFT_GUARDIAN_7345, NpcID.RIFT_GUARDIAN_7346,
                    NpcID.RIFT_GUARDIAN_7347, NpcID.RIFT_GUARDIAN_7348, NpcID.RIFT_GUARDIAN_7349,
                    NpcID.RIFT_GUARDIAN_7350, NpcID.RIFT_GUARDIAN_8024
            };
            case 8: return new int[]{ NpcID.SOUP, NpcID.BEAVER };
            case 9: return new int[]{ NpcID.SOUP, NpcID.BEAVER };
            case 10: return new int[]{ NpcID.ROCKY, NpcID.BEAVER, NpcID.GIANT_SQUIRREL };
            default: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 5; }

    @Override
    public boolean isAquatic() { return zone == 8 || zone == 9; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 1 Bone Squirrel(0), 6 Giant Squirrel(1), 2 Beaver(2), 2 Rocky(3)
                if (spawnIndex < 1) return 0;
                if (spawnIndex < 7) return 1;
                if (spawnIndex < 9) return 2;
                return 3;
            case 2:
                // 4 Giant Squirrel(0), 2 Rocky(1), 1 Beaver(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
                return 2;
            case 3:
                // 2 Rocky(0), 4 Giant Squirrel(1)
                return spawnIndex < 2 ? 0 : 1;
            case 4:
                // 5 Giant Squirrel(0), 2 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 5) return 0;
                if (spawnIndex < 7) return 1;
                return 2;
            case 6:
                // 2 Giant Squirrel(0), 2 Beaver(1)
                return spawnIndex < 2 ? 0 : 1;
            case 7:
                // 2 Bone Squirrel(0), 2 random Rift Guardian (1..15)
                if (spawnIndex < 2) return 0;
                return 1 + ((spawnIndex - 2) % 15);
            case 8:
                // 3 Soup(0), 3 Beaver(1)
                return spawnIndex < 3 ? 0 : 1;
            case 9:
                // 3 Soup(0), 1 Beaver(1)
                return spawnIndex < 3 ? 0 : 1;
            case 10:
                // 8 Rocky(0), 2 Beaver(1), 4 Giant Squirrel(2)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            default:
                // 2 Giant Squirrel(0), 2 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 4) return 1;
                return 2;
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
                    if (i < 1) names[i] = "Bone Squirrel";
                    else if (i < 7) names[i] = "Giant Squirrel";
                    else if (i < 9) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 3:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Rocky" : "Giant Squirrel";
                break;
            case 4:
                for (int i = 0; i < n; i++)
                {
                    if (i < 5) names[i] = "Giant Squirrel";
                    else if (i < 7) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 5:
                Arrays.fill(names, "Heron");
                break;
            case 6:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Giant Squirrel" : "Beaver";
                break;
            case 7:
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Bone Squirrel" : "Rift Guardian";
                break;
            case 8:
                for (int i = 0; i < n; i++)
                    names[i] = i < 3 ? "Soup" : "Beaver";
                break;
            case 9:
                for (int i = 0; i < n; i++)
                    names[i] = i < 3 ? "Soup" : "Beaver";
                break;
            case 10:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Rocky";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Giant Squirrel";
                }
                break;
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Giant Squirrel";
                    else if (i < 4) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
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
                    case 0: return "<col=ffff00>Bone Squirrel</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 3:
                return formIndex == 0
                        ? "<col=ffff00>Rocky</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
            case 4:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 5:
                return "<col=ffff00>Heron</col>";
            case 6:
                return formIndex == 0
                        ? "<col=ffff00>Giant Squirrel</col>"
                        : "<col=ffff00>Beaver</col>";
            case 7:
                return formIndex == 0
                        ? "<col=ffff00>Fluffy</col>"
                        : "<col=ffff00>Rift Guardian</col>";
            case 8:
                return formIndex == 0
                        ? "<col=ffff00>Soup</col>"
                        : "<col=ffff00>Beaver</col>";
            case 9:
                return formIndex == 0
                        ? "<col=ffff00>Soup</col>"
                        : "<col=ffff00>Beaver</col>";
            case 10:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Rocky</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Giant Squirrel</col>";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Son</col>";
                    case 1: return "<col=ffff00>Yui</col>";
                    default: return "<col=ffff00>Sid</col>";
                }
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
                    case 0: return "Lost something between the trees. A while ago.";
                    case 1: return "Already three trees ahead of you.";
                    case 2: return "Drafting a dam in his head. Optimistically.";
                    default: return "Quietly auditing every traveller.";
                }
            case 2:
                switch (formIndex)
                {
                    case 0: return "Faster than the wind. Most days.";
                    case 1: return "A familiar shadow on the road.";
                    default: return "Plotting against a nearby fence post.";
                }
            case 3:
                return formIndex == 0
                        ? "The town's quietest professional."
                        : "A regular at the seed stall.";
            case 4:
                switch (formIndex)
                {
                    case 0: return "Knows every shortcut on this side of town.";
                    case 1: return "A professional opinion on every plank in sight.";
                    default: return "Watching the road for unsuspecting travellers.";
                }
            case 5:
                return "Has a higher fishing level than you.";
            case 6:
                return formIndex == 0
                        ? "Drawn to the tower, repelled by the wizards."
                        : "Banned from the tower. The wizards know why.";
            case 7:
                return formIndex == 0
                        ? "Probably an experiment. Possibly several."
                        : "A rune given form. And opinions.";
            case 8:
                return formIndex == 0
                        ? "First name basis with several chefs."
                        : "Treats the river as a long-term commitment.";
            case 9:
                return formIndex == 0
                        ? "Hopes nobody's hungry."
                        : "Paddling laps around the wizards' courtyard.";
            case 10:
                switch (formIndex)
                {
                    case 0: return "Knows three ways into the jail. Two ways out.";
                    case 1: return "Plotting a watery escape route.";
                    default: return "The jail's beams won't last forever.";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "Has scouted a gap in the banks wall.";
                    case 1: return "Eyeing the wooden repairs in the banks wall.";
                    default: return "Seems to be communicating with the other animals.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 11;
            case 2: return 7;
            case 3: return 6;
            case 4: return 9;
            case 5: return 3;
            case 6: return 4;
            case 7: return 4;
            case 8: return 6;
            case 9: return 4;
            case 10: return 14;
            default: return 6;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset()
    {
        if (isAquatic()) return -20;
        return 10;
    }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_NORTH_OF_TOWN;
            case 2: return ZONE2_WEST_OF_TOWN;
            case 3: return ZONE3_DRAYNOR_TOWN;
            case 4: return ZONE4_SOUTH_OF_BANK;
            case 5: return ZONE5_SHORELINE;
            case 6: return ZONE6_WIZARDS_GUILD;
            case 7: return ZONE7_WIZARDS_TOWER;
            case 8: return ZONE8_WEST_WATER;
            case 9: return ZONE9_GUILD_WATER;
            case 10: return ZONE10_DRAYNOR_JAIL;
            default: return ZONE11_BANK_BORDER;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 3) return FORBIDDEN_ZONE3;
        if (zone == 6) return FORBIDDEN_ZONE6;
        if (zone == 10) return FORBIDDEN_ZONE10;
        return new int[0][][];
    }

    // Zone 1 - North of Town
    private static final int[][] ZONE1_NORTH_OF_TOWN = {
            { 3127, 3293 }, { 3122, 3301 }, { 3107, 3306 }, { 3093, 3310 },
            { 3077, 3312 }, { 3071, 3306 }, { 3072, 3295 }, { 3071, 3288 },
            { 3071, 3280 }, { 3081, 3277 }, { 3090, 3280 }, { 3096, 3284 },
            { 3103, 3283 }, { 3107, 3282 }, { 3111, 3289 }, { 3117, 3294 },
            { 3127, 3293 }
    };

    // Zone 2 - West of Town
    private static final int[][] ZONE2_WEST_OF_TOWN = {
            { 3096, 3282 }, { 3086, 3280 }, { 3073, 3275 }, { 3070, 3273 },
            { 3069, 3268 }, { 3069, 3262 }, { 3069, 3256 }, { 3066, 3254 },
            { 3067, 3250 }, { 3069, 3247 }, { 3075, 3255 }, { 3075, 3257 },
            { 3082, 3257 }, { 3082, 3263 }, { 3088, 3264 }, { 3088, 3270 },
            { 3095, 3271 }, { 3106, 3271 }, { 3106, 3277 }, { 3111, 3282 },
            { 3111, 3288 }, { 3096, 3283 }, { 3096, 3282 }
    };

    // Zone 3 - Draynor Town
    private static final int[][] ZONE3_DRAYNOR_TOWN = {
            { 3087, 3256 }, { 3083, 3256 }, { 3081, 3259 }, { 3077, 3257 },
            { 3076, 3256 }, { 3076, 3255 }, { 3074, 3253 }, { 3074, 3250 },
            { 3074, 3246 }, { 3075, 3244 }, { 3079, 3243 }, { 3083, 3240 },
            { 3087, 3239 }, { 3096, 3238 }, { 3101, 3239 }, { 3101, 3243 },
            { 3101, 3246 }, { 3101, 3249 }, { 3102, 3254 }, { 3102, 3256 },
            { 3096, 3256 }, { 3095, 3256 }, { 3095, 3251 }, { 3087, 3251 },
            { 3087, 3256 }
    };

    // Zone 3 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE3 = {
            // Bank
            {
                    { 3088, 3247 }, { 3098, 3247 }, { 3098, 3240 }, { 3088, 3240 },
                    { 3088, 3247 }
            }
    };

    // Zone 4 - South of Bank
    private static final int[][] ZONE4_SOUTH_OF_BANK = {
            { 3086, 3239 }, { 3090, 3235 }, { 3091, 3231 }, { 3090, 3223 },
            { 3092, 3217 }, { 3101, 3212 }, { 3110, 3210 }, { 3124, 3207 },
            { 3131, 3207 }, { 3130, 3217 }, { 3124, 3227 }, { 3117, 3232 },
            { 3109, 3233 }, { 3104, 3238 }, { 3104, 3246 }, { 3098, 3237 },
            { 3086, 3239 }
    };

    // Zone 5 - Shoreline
    private static final int[][] ZONE5_SHORELINE = {
            { 3065, 3252 }, { 3068, 3251 }, { 3071, 3249 }, { 3075, 3245 },
            { 3082, 3241 }, { 3086, 3238 }, { 3089, 3235 }, { 3092, 3228 },
            { 3092, 3225 }, { 3091, 3222 }, { 3092, 3218 }, { 3093, 3216 },
            { 3095, 3214 }, { 3103, 3211 }, { 3110, 3208 }, { 3106, 3209 },
            { 3104, 3209 }, { 3101, 3209 }, { 3098, 3210 }, { 3096, 3211 },
            { 3094, 3212 }, { 3094, 3213 }, { 3093, 3213 }, { 3089, 3217 },
            { 3088, 3219 }, { 3086, 3221 }, { 3086, 3223 }, { 3088, 3225 },
            { 3088, 3226 }, { 3087, 3228 }, { 3087, 3230 }, { 3086, 3231 },
            { 3085, 3233 }, { 3083, 3236 }, { 3080, 3239 }, { 3077, 3241 },
            { 3074, 3243 }, { 3072, 3244 }, { 3070, 3244 }, { 3068, 3245 },
            { 3066, 3249 }, { 3065, 3250 }, { 3065, 3252 }
    };

    // Zone 6 - Wizards Guild
    private static final int[][] ZONE6_WIZARDS_GUILD = {
            { 3116, 3174 }, { 3119, 3174 }, { 3122, 3171 }, { 3122, 3166 },
            { 3123, 3162 }, { 3123, 3159 }, { 3121, 3156 }, { 3118, 3153 },
            { 3115, 3151 }, { 3113, 3148 }, { 3109, 3146 }, { 3104, 3146 },
            { 3102, 3149 }, { 3100, 3153 }, { 3099, 3157 }, { 3097, 3158 },
            { 3094, 3162 }, { 3096, 3167 }, { 3096, 3169 }, { 3098, 3171 },
            { 3100, 3172 }, { 3103, 3175 }, { 3105, 3176 }, { 3108, 3174 },
            { 3111, 3174 }, { 3116, 3174 }
    };

    // Zone 6 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE6 = {
            // Tower
            {
                    { 3107, 3167 }, { 3112, 3167 }, { 3116, 3163 }, { 3116, 3158 },
                    { 3112, 3154 }, { 3107, 3154 }, { 3103, 3158 }, { 3103, 3162 },
                    { 3102, 3163 }, { 3102, 3163 }, { 3102, 3165 }, { 3103, 3166 },
                    { 3105, 3166 }, { 3106, 3165 }, { 3107, 3166 }, { 3107, 3167 }
            }
    };

    // Zone 7 - Wizards Guild (Tower)
    private static final int[][] ZONE7_WIZARDS_TOWER = {
            { 3107, 3167 }, { 3112, 3167 }, { 3116, 3163 }, { 3116, 3158 },
            { 3112, 3154 }, { 3107, 3154 }, { 3103, 3158 }, { 3103, 3162 },
            { 3102, 3163 }, { 3102, 3163 }, { 3102, 3165 }, { 3103, 3166 },
            { 3105, 3166 }, { 3106, 3165 }, { 3107, 3166 }, { 3107, 3167 }
    };

    // Zone 8 - West Water (Draynor-Wizard)
    private static final int[][] ZONE8_WEST_WATER = {
            { 3056, 3248 }, { 3057, 3248 }, { 3059, 3248 }, { 3061, 3248 },
            { 3062, 3249 }, { 3063, 3248 }, { 3064, 3247 }, { 3065, 3246 },
            { 3066, 3244 }, { 3068, 3242 }, { 3069, 3241 }, { 3073, 3239 },
            { 3076, 3238 }, { 3076, 3235 }, { 3078, 3233 }, { 3080, 3233 },
            { 3081, 3230 }, { 3081, 3228 }, { 3081, 3225 }, { 3081, 3220 },
            { 3084, 3216 }, { 3087, 3211 }, { 3092, 3209 }, { 3096, 3205 },
            { 3100, 3204 }, { 3106, 3205 }, { 3110, 3207 }, { 3106, 3208 },
            { 3102, 3208 }, { 3098, 3208 }, { 3092, 3212 }, { 3089, 3214 },
            { 3085, 3221 }, { 3085, 3224 }, { 3086, 3225 }, { 3086, 3226 },
            { 3085, 3229 }, { 3084, 3231 }, { 3084, 3232 }, { 3083, 3234 },
            { 3080, 3238 }, { 3075, 3241 }, { 3072, 3242 }, { 3072, 3243 },
            { 3070, 3243 }, { 3067, 3245 }, { 3065, 3249 }, { 3064, 3250 },
            { 3061, 3250 }, { 3059, 3249 }, { 3056, 3249 }, { 3056, 3248 }
    };

    // Zone 9 - Wizards Guild (Water)
    private static final int[][] ZONE9_GUILD_WATER = {
            { 3111, 3177 }, { 3105, 3180 }, { 3100, 3179 }, { 3094, 3175 },
            { 3091, 3168 }, { 3089, 3164 }, { 3088, 3158 }, { 3091, 3154 },
            { 3097, 3150 }, { 3098, 3146 }, { 3100, 3143 }, { 3107, 3142 },
            { 3117, 3143 }, { 3124, 3146 }, { 3127, 3151 }, { 3129, 3156 },
            { 3131, 3160 }, { 3131, 3164 }, { 3126, 3176 }, { 3120, 3178 },
            { 3117, 3175 }, { 3121, 3174 }, { 3125, 3171 }, { 3125, 3167 },
            { 3125, 3163 }, { 3126, 3161 }, { 3126, 3158 }, { 3124, 3155 },
            { 3120, 3152 }, { 3117, 3149 }, { 3115, 3147 }, { 3112, 3145 },
            { 3107, 3144 }, { 3102, 3146 }, { 3101, 3148 }, { 3099, 3151 },
            { 3098, 3154 }, { 3096, 3157 }, { 3094, 3160 }, { 3093, 3161 },
            { 3093, 3166 }, { 3096, 3171 }, { 3099, 3174 }, { 3103, 3177 },
            { 3105, 3178 }, { 3110, 3175 }, { 3111, 3177 }
    };

    // Zone 10 - Draynor Jail
    private static final int[][] ZONE10_DRAYNOR_JAIL = {
            { 3115, 3259 }, { 3119, 3259 }, { 3120, 3260 }, { 3122, 3260 },
            { 3124, 3261 }, { 3125, 3261 }, { 3127, 3260 }, { 3129, 3260 },
            { 3132, 3258 }, { 3132, 3251 }, { 3134, 3249 }, { 3134, 3245 },
            { 3135, 3245 }, { 3135, 3240 }, { 3133, 3238 }, { 3133, 3236 },
            { 3131, 3234 }, { 3128, 3234 }, { 3127, 3233 }, { 3120, 3233 },
            { 3119, 3234 }, { 3118, 3234 }, { 3117, 3235 }, { 3114, 3235 },
            { 3112, 3236 }, { 3110, 3236 }, { 3109, 3238 }, { 3107, 3239 },
            { 3107, 3240 }, { 3108, 3242 }, { 3107, 3246 }, { 3107, 3248 },
            { 3107, 3250 }, { 3108, 3250 }, { 3107, 3252 }, { 3107, 3254 },
            { 3107, 3258 }, { 3106, 3260 }, { 3108, 3261 }, { 3115, 3259 }
    };

    // Zone 10 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE10 = {
            // Jail (Quest Zone)
            {
                    { 3121, 3247 }, { 3131, 3247 }, { 3131, 3240 }, { 3121, 3240 },
                    { 3121, 3247 }
            }
    };

    // Zone 11 - Bank Border
    private static final int[][] ZONE11_BANK_BORDER = {
            { 3096, 3248 }, { 3093, 3251 }, { 3090, 3254 }, { 3087, 3254 },
            { 3087, 3251 }, { 3087, 3250 }, { 3085, 3248 }, { 3083, 3246 },
            { 3083, 3240 }, { 3087, 3236 }, { 3093, 3235 }, { 3099, 3234 },
            { 3102, 3240 }, { 3098, 3248 }, { 3096, 3248 }
    };
}
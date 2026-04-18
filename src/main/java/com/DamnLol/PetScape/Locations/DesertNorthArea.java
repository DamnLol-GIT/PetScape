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


public class DesertNorthArea extends RoamingArea
{
    private final int zone;

    private DesertNorthArea(int zone) { this.zone = zone; }

    public static DesertNorthArea zone1() { return new DesertNorthArea(1); }
    public static DesertNorthArea zone2() { return new DesertNorthArea(2); }
    public static DesertNorthArea zone3() { return new DesertNorthArea(3); }
    public static DesertNorthArea zone4() { return new DesertNorthArea(4); }
    public static DesertNorthArea zone5() { return new DesertNorthArea(5); }

    @Override
    public String getAreaId() { return "desert_north_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 2:
                return new int[]{ NpcID.KALPHITE_PRINCESS, NpcID.KALPHITE_PRINCESS_6653 };
            case 3:
                return new int[]{
                        NpcID.ROCK_GOLEM,
                        NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                        NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                        NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                        NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                        NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453,
                        NpcID.ROCK_GOLEM_7454, NpcID.ROCK_GOLEM_7455, NpcID.ROCK_GOLEM_7642
                };
            case 4:
                return new int[]{
                        NpcID.GIANT_SQUIRREL,
                        NpcID.BABY_MOLE,
                        NpcID.KALPHITE_PRINCESS,
                        NpcID.BABY_CHINCHOMPA
                };
            default:
                return new int[]{ NpcID.QUETZIN, NpcID.GREAT_BLUE_HERON, NpcID.PHOENIX };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 5; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
            case 2:
                // 12 Kalphite Princess (ground), 12 Kalphite Princess (flying)
                Arrays.fill(names, "Kalphite Princess");
                break;
            case 3:
                Arrays.fill(names, "Rock Golem");
                break;
            case 4:
                // 4 Giant Squirrel, 2 Baby Mole, 1 Kalphite Princess, 2 Baby Chinchompa
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Baby Mole";
                    else if (i < 7) names[i] = "Kalphite Princess";
                    else names[i] = "Baby Chinchompa";
                }
                break;
            default:
                // 4 Quetzin, 2 Heron, 2 Phoenix
                for (int i = 0; i < n; i++)
                {
                    if (i < 4)      names[i] = "Quetzin";
                    else if (i < 6) names[i] = "Heron";
                    else            names[i] = "Phoenix";
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
            case 2:
                return "<col=ffff00>Kalphite Countess</col>";
            case 3:
                return "<col=ffff00>Ruin Golem</col>";
            case 4:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Desert Mole</col>";
                    case 2: return "<col=ffff00>Kalphite Duchess</col>";
                    default: return "<col=ffff00>Desert Chinchompa</col>";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Quetzin</col>";
                    case 1: return "<col=ffff00>Great Blue Heron</col>";
                    default: return "<col=ffff00>Desert Phoenix</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
            case 2:
                return formIndex == 0
                        ? "A lesser title, an equal amount of sand."
                        : "Too royal to walk, too small to matter.";
            case 3:
                return "Ancient, crumbling, and absolutely tiny.";
            case 4:
                switch (formIndex)
                {
                    case 0: return "Went looking for nuts, found a desert.";
                    case 1: return "Sandy paws, zero regrets.";
                    case 2: return "Rules the sand with a tiny iron claw.";
                    default: return "The desert's softest hazard.";
                }
            default:
                switch (formIndex)
                {
                    case 0: return "The most colourful thing in the desert.";
                    case 1: return "Great name, small bird, dry feet.";
                    default: return "Ashes to ashes, dunes to dunes.";
                }
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
            case 2:
                // 12 crawling (0), 12 flying (1)
                return spawnIndex < 12 ? 0 : 1;
            case 3:
                // One of each form in order
                return spawnIndex % nForms;
            case 4:
                // 4 Giant Squirrel (0), 2 Baby Mole (1), 1 Kalphite Princess (2), 2 Baby Chinchompa (3)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
                if (spawnIndex < 7) return 2;
                return 3;
            default:
                // 4 Quetzin (0), 2 Heron (1), 2 Phoenix (2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
                return 2;
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1:
            case 2: return 24;
            case 3: return 19;
            case 4: return 9;
            default: return 8;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int getWanderMinDist() { return zone == 4 ? 5 : 8; }

    @Override
    public int getMinSiblingSeparation() { return zone == 4 ? 2 : 3; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_WEST_ELID;
            case 2: return ZONE2_NORTH_NARDAH;
            case 3: return ZONE3_RUINS_UNZER;
            case 4:
            case 5: return ZONE4_OASIS; // Zone 5 uses shore polygon so BFS finds walkable tiles
            default: return ZONE4_OASIS;

        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 1) return FORBIDDEN_ZONE1;
        return new int[0][][];
    }

    // Zone 1 - West Elid/North Bandit
    private static final int[][] ZONE1_WEST_ELID = {
            { 3277, 3132 }, { 3269, 3133 }, { 3263, 3132 }, { 3262, 3129 },
            { 3255, 3127 }, { 3250, 3123 }, { 3242, 3121 }, { 3239, 3117 },
            { 3230, 3117 }, { 3223, 3122 }, { 3216, 3128 }, { 3211, 3127 },
            { 3208, 3123 }, { 3207, 3117 }, { 3211, 3107 }, { 3211, 3102 },
            { 3210, 3097 }, { 3210, 3090 }, { 3211, 3085 }, { 3209, 3076 },
            { 3209, 3070 }, { 3210, 3065 }, { 3209, 3061 }, { 3207, 3059 },
            { 3199, 3057 }, { 3191, 3057 }, { 3186, 3046 }, { 3188, 3035 },
            { 3188, 3029 }, { 3183, 3025 }, { 3175, 3017 }, { 3167, 3010 },
            { 3176, 3002 }, { 3185, 2998 }, { 3191, 2997 }, { 3189, 3005 },
            { 3193, 3009 }, { 3198, 3009 }, { 3203, 3002 }, { 3205, 2996 },
            { 3207, 2987 }, { 3214, 2990 }, { 3220, 2987 }, { 3226, 2985 },
            { 3232, 2990 }, { 3240, 2992 }, { 3245, 2988 }, { 3250, 2985 },
            { 3255, 2990 }, { 3260, 2997 }, { 3262, 3002 }, { 3265, 3002 },
            { 3271, 3003 }, { 3276, 2998 }, { 3282, 2996 }, { 3286, 2995 },
            { 3295, 2996 }, { 3304, 3000 }, { 3307, 3004 }, { 3310, 3006 },
            { 3314, 3009 }, { 3322, 3009 }, { 3322, 3004 }, { 3320, 3000 },
            { 3316, 2995 }, { 3315, 2994 }, { 3313, 2991 }, { 3319, 2990 },
            { 3322, 2989 }, { 3328, 2992 }, { 3330, 2993 }, { 3332, 3001 },
            { 3332, 3007 }, { 3338, 3012 }, { 3349, 3011 }, { 3352, 3014 },
            { 3351, 3020 }, { 3354, 3025 }, { 3364, 3031 }, { 3370, 3034 },
            { 3370, 3041 }, { 3366, 3044 }, { 3361, 3052 }, { 3356, 3071 },
            { 3357, 3077 }, { 3352, 3083 }, { 3341, 3089 }, { 3332, 3096 },
            { 3334, 3102 }, { 3339, 3109 }, { 3343, 3118 }, { 3348, 3130 },
            { 3356, 3134 }, { 3356, 3139 }, { 3351, 3142 }, { 3348, 3145 },
            { 3347, 3148 }, { 3348, 3151 }, { 3349, 3153 }, { 3345, 3155 },
            { 3341, 3154 }, { 3337, 3148 }, { 3337, 3142 }, { 3332, 3134 },
            { 3328, 3133 }, { 3324, 3130 }, { 3328, 3123 }, { 3329, 3119 },
            { 3326, 3116 }, { 3323, 3111 }, { 3322, 3106 }, { 3320, 3104 },
            { 3313, 3101 }, { 3310, 3101 }, { 3307, 3103 }, { 3305, 3106 },
            { 3300, 3107 }, { 3291, 3106 }, { 3291, 3109 }, { 3283, 3112 },
            { 3284, 3116 }, { 3283, 3121 }, { 3277, 3128 }, { 3277, 3131 },
            { 3277, 3132 }
    };

    // Zone 1 forbidden Zones
    // Desert Mining Camp
    private static final int[][][] FORBIDDEN_ZONE1 = {
        {
            { 3273, 3048 }, { 3285, 3050 }, { 3296, 3050 }, { 3306, 3048 },
            { 3315, 3034 }, { 3315, 3027 }, { 3314, 3017 }, { 3305, 3007 },
            { 3297, 3005 }, { 3287, 3005 }, { 3278, 3007 }, { 3270, 3011 },
            { 3264, 3016 }, { 3263, 3023 }, { 3263, 3027 }, { 3267, 3038 },
            { 3273, 3048 }
        }
    };

    // Zone 2 - North Nardah/East Elid
    private static final int[][] ZONE2_NORTH_NARDAH = {
            { 3381, 3121 }, { 3388, 3125 }, { 3389, 3132 }, { 3390, 3136 },
            { 3393, 3139 }, { 3394, 3141 }, { 3397, 3144 }, { 3401, 3149 },
            { 3399, 3155 }, { 3400, 3157 }, { 3401, 3158 }, { 3404, 3158 },
            { 3412, 3159 }, { 3416, 3159 }, { 3421, 3160 }, { 3425, 3163 },
            { 3433, 3153 }, { 3437, 3143 }, { 3440, 3139 }, { 3444, 3135 },
            { 3450, 3136 }, { 3454, 3141 }, { 3458, 3141 }, { 3465, 3139 },
            { 3472, 3137 }, { 3473, 3133 }, { 3469, 3133 }, { 3465, 3134 },
            { 3457, 3133 }, { 3451, 3130 }, { 3454, 3125 }, { 3455, 3119 },
            { 3458, 3116 }, { 3464, 3117 }, { 3477, 3123 }, { 3481, 3121 },
            { 3484, 3118 }, { 3485, 3115 }, { 3483, 3114 }, { 3477, 3117 },
            { 3475, 3117 }, { 3468, 3114 }, { 3462, 3112 }, { 3460, 3105 },
            { 3460, 3098 }, { 3458, 3088 }, { 3462, 3075 }, { 3466, 3071 },
            { 3468, 3069 }, { 3465, 3064 }, { 3456, 3057 }, { 3455, 3050 },
            { 3455, 3039 }, { 3455, 3033 }, { 3457, 3023 }, { 3466, 3006 },
            { 3469, 3004 }, { 3465, 2992 }, { 3461, 2982 }, { 3462, 2977 },
            { 3462, 2971 }, { 3467, 2962 }, { 3466, 2956 }, { 3463, 2952 },
            { 3458, 2951 }, { 3454, 2946 }, { 3449, 2945 }, { 3444, 2946 },
            { 3440, 2946 }, { 3430, 2948 }, { 3427, 2949 }, { 3420, 2955 },
            { 3409, 2950 }, { 3405, 2944 }, { 3399, 2939 }, { 3396, 2935 },
            { 3391, 2928 }, { 3385, 2928 }, { 3377, 2930 }, { 3378, 2944 },
            { 3389, 2951 }, { 3394, 2957 }, { 3400, 2962 }, { 3399, 2969 },
            { 3398, 2979 }, { 3395, 2986 }, { 3396, 2993 }, { 3401, 3000 },
            { 3395, 3005 }, { 3384, 3011 }, { 3383, 3014 }, { 3389, 3018 },
            { 3398, 3025 }, { 3401, 3033 }, { 3402, 3043 }, { 3394, 3051 },
            { 3383, 3066 }, { 3388, 3075 }, { 3382, 3094 }, { 3379, 3108 },
            { 3381, 3121 }
    };

    // Zone 3 - Ruins of Unzer
    private static final int[][] ZONE3_RUINS_UNZER = {
            { 3479, 3113 }, { 3469, 3108 }, { 3466, 3104 }, { 3465, 3093 },
            { 3464, 3083 }, { 3466, 3079 }, { 3478, 3078 }, { 3483, 3081 },
            { 3486, 3083 }, { 3490, 3084 }, { 3500, 3084 }, { 3500, 3097 },
            { 3493, 3097 }, { 3496, 3100 }, { 3495, 3102 }, { 3492, 3107 },
            { 3485, 3113 }, { 3479, 3113 }
    };

    // Zone 4 - Oasis
    private static final int[][] ZONE4_OASIS = {
            { 3468, 3054 }, { 3471, 3055 }, { 3472, 3057 }, { 3474, 3057 },
            { 3476, 3055 }, { 3478, 3054 }, { 3480, 3053 }, { 3481, 3053 },
            { 3484, 3052 }, { 3485, 3052 }, { 3494, 3052 }, { 3495, 3050 },
            { 3491, 3050 }, { 3486, 3050 }, { 3484, 3050 }, { 3482, 3049 },
            { 3481, 3049 }, { 3478, 3049 }, { 3477, 3050 }, { 3475, 3050 },
            { 3470, 3051 }, { 3467, 3050 }, { 3466, 3049 }, { 3468, 3048 },
            { 3470, 3048 }, { 3472, 3047 }, { 3474, 3047 }, { 3476, 3046 },
            { 3478, 3045 }, { 3482, 3045 }, { 3483, 3044 }, { 3485, 3044 },
            { 3489, 3043 }, { 3492, 3041 }, { 3494, 3040 }, { 3495, 3041 },
            { 3498, 3042 }, { 3499, 3042 }, { 3499, 3043 }, { 3500, 3044 },
            { 3501, 3046 }, { 3500, 3047 }, { 3500, 3048 }, { 3498, 3049 },
            { 3497, 3050 }, { 3495, 3050 }, { 3494, 3052 }, { 3497, 3050 },
            { 3502, 3050 }, { 3503, 3046 }, { 3502, 3041 }, { 3499, 3040 },
            { 3497, 3039 }, { 3495, 3038 }, { 3494, 3038 }, { 3490, 3039 },
            { 3489, 3040 }, { 3487, 3041 }, { 3485, 3041 }, { 3483, 3042 },
            { 3481, 3042 }, { 3479, 3042 }, { 3478, 3043 }, { 3474, 3043 },
            { 3472, 3043 }, { 3468, 3044 }, { 3464, 3046 }, { 3464, 3051 },
            { 3468, 3054 }
    };
}
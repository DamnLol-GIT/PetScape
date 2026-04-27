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


public class DesertSouthRoamArea extends RoamingArea
{
    private final int zone;

    private DesertSouthRoamArea(int zone) { this.zone = zone; }

    public static DesertSouthRoamArea zone1() { return new DesertSouthRoamArea(1); }
    public static DesertSouthRoamArea zone2() { return new DesertSouthRoamArea(2); }
    public static DesertSouthRoamArea zone3() { return new DesertSouthRoamArea(3); }

    @Override
    public String getAreaId() { return "desert_south_roam_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
            case 2:
                return new int[]{
                        NpcID.KALPHITE_PRINCESS,
                        NpcID.KALPHITE_PRINCESS_6653,
                        NpcID.BABY_MOLERAT,
                        NpcID.QUETZIN,
                        NpcID.BABY_CHINCHOMPA_6759,
                        NpcID.SOUP
                };
            default: // Zone 3
                return new int[]{ NpcID.QUETZIN };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying()
    {
        return zone == 3;
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
            case 2:
                // 6 Kalphite Princess (ground), 6 Kalphite Princess (flying), 6 Baby Mole Rat,
                // 6 Quetzin, 6 Baby Chinchompa, 1 Soup
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Kalphite Princess";
                    else if (i < 12) names[i] = "Kalphite Princess";
                    else if (i < 18) names[i] = "Baby Mole Rat";
                    else if (i < 24) names[i] = "Quetzin";
                    else if (i < 30) names[i] = "Baby Chinchompa";
                    else names[i] = "Soup";                }
                break;
            default: // Zone 3
                Arrays.fill(names, "Quetzin");
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
                switch (formIndex)
                {
                    case 0:
                    case 1:
                        return "<col=ffff00>Kalphite Viscountess</col>";
                    case 2: return "<col=ffff00>Desert Mole</col>";
                    case 3: return "<col=ffff00>Quetzin</col>";
                    case 4: return "<col=ffff00>Desert Chinchompa</col>";
                    default: return zone == 1 ? "<col=ffff00>Dusty</col>" : "<col=ffff00>Sandy</col>";
                }
            default: // Zone 3
                return "<col=ffff00>Quetzin</col>";
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
            case 2:
                switch (formIndex)
                {
                    case 0: return "A viscountess requires no introduction. Or shoes.";
                    case 1: return "Promoted herself. Nobody objected.";
                    case 2: return "Surfaces occasionally. Judges immediately.";
                    case 3: return "Took a very wrong turn at the rainforest.";
                    case 4: return "The softest thing in the desert. Also the most explosive.";
                    default: return zone == 1
                            ? "Where Sandy goes, Dusty follows. Or was it the other way around?"
                            : "Where Dusty goes, Sandy follows. Or was it the other way around?";
                }
            default: // Zone 3
                return "Oasis or mirage? Either way, it's not sharing.";
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
            case 2:
                // 6 Kalphite Princess ground (0), 6 Kalphite Princess flying (1),
                // 6 Baby Mole Rat (2), 6 Quetzin (3), 6 Baby Chinchompa (4), 1 Soup (5)
                if (spawnIndex < 6)  return 0;
                if (spawnIndex < 12) return 1;
                if (spawnIndex < 18) return 2;
                if (spawnIndex < 24) return 3;
                if (spawnIndex < 30) return 4;
                return 5;
            default:
                return super.getFormAssignment(spawnIndex, nForms);
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1:
            case 2: return 31;
            default: return 6;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset()
    {
        // Soup gets -10, everything else 10
        return 10;
    }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_WEST_DESERT;
            case 2: return ZONE2_EAST_DESERT;
            default: return ZONE3_WATERING_HOLE;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        switch (zone)
        {
            case 1: return FORBIDDEN_ZONE1;
            case 2: return FORBIDDEN_ZONE2;
            default: return new int[0][][];
        }
    }

    // Zone 1 - West Desert
    private static final int[][] ZONE1_WEST_DESERT = {
            { 3321, 2941 }, { 3308, 2942 }, { 3300, 2944 }, { 3295, 2951 },
            { 3291, 2956 }, { 3285, 2961 }, { 3287, 2969 }, { 3292, 2973 },
            { 3294, 2978 }, { 3284, 2982 }, { 3278, 2982 }, { 3268, 2977 },
            { 3268, 2977 }, { 3263, 2975 }, { 3266, 2980 }, { 3266, 2982 },
            { 3265, 2983 }, { 3258, 2982 }, { 3257, 2980 }, { 3254, 2977 },
            { 3244, 2977 }, { 3239, 2983 }, { 3237, 2983 }, { 3234, 2980 },
            { 3230, 2977 }, { 3225, 2976 }, { 3219, 2976 }, { 3217, 2978 },
            { 3216, 2981 }, { 3215, 2982 }, { 3214, 2983 }, { 3212, 2982 },
            { 3210, 2980 }, { 3210, 2977 }, { 3207, 2977 }, { 3207, 2974 },
            { 3207, 2973 }, { 3207, 2971 }, { 3209, 2968 }, { 3209, 2963 },
            { 3203, 2965 }, { 3196, 2968 }, { 3191, 2973 }, { 3182, 2966 },
            { 3178, 2959 }, { 3180, 2955 }, { 3189, 2954 }, { 3196, 2957 },
            { 3202, 2955 }, { 3204, 2953 }, { 3209, 2947 }, { 3212, 2941 },
            { 3209, 2936 }, { 3205, 2930 }, { 3198, 2928 }, { 3197, 2913 },
            { 3192, 2912 }, { 3199, 2905 }, { 3200, 2901 }, { 3198, 2898 },
            { 3189, 2888 }, { 3181, 2889 }, { 3173, 2894 }, { 3164, 2890 },
            { 3165, 2883 }, { 3167, 2879 }, { 3184, 2881 }, { 3194, 2880 },
            { 3199, 2879 }, { 3200, 2874 }, { 3200, 2864 }, { 3195, 2859 },
            { 3196, 2854 }, { 3198, 2851 }, { 3200, 2850 }, { 3203, 2847 },
            { 3202, 2838 }, { 3204, 2833 }, { 3206, 2831 }, { 3209, 2824 },
            { 3211, 2821 }, { 3218, 2819 }, { 3225, 2818 }, { 3233, 2812 },
            { 3237, 2815 }, { 3245, 2816 }, { 3253, 2816 }, { 3256, 2814 },
            { 3260, 2814 }, { 3259, 2820 }, { 3259, 2827 }, { 3257, 2833 },
            { 3257, 2839 }, { 3257, 2844 }, { 3259, 2850 }, { 3257, 2854 },
            { 3256, 2859 }, { 3258, 2865 }, { 3257, 2870 }, { 3255, 2877 },
            { 3257, 2881 }, { 3259, 2884 }, { 3264, 2887 }, { 3266, 2891 },
            { 3264, 2900 }, { 3275, 2905 }, { 3279, 2905 }, { 3281, 2904 },
            { 3285, 2906 }, { 3286, 2911 }, { 3292, 2919 }, { 3301, 2927 },
            { 3312, 2927 }, { 3318, 2925 }, { 3325, 2926 }, { 3331, 2928 },
            { 3340, 2935 }, { 3330, 2940 }, { 3321, 2941 }
    };

    // Zone 1 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE1 = {
        // Desert Treasure Pyramid
        {
            { 3216, 2914 }, { 3250, 2914 }, { 3250, 2882 }, { 3216, 2882 },
            { 3216, 2914 }
        },

        // Desert Treasure Mirrors
        {
            { 3213, 2960 }, { 3215, 2960 }, { 3217, 2959 }, { 3219, 2957 },
            { 3219, 2953 }, { 3215, 2950 }, { 3213, 2950 }, { 3209, 2953 },
            { 3209, 2956 }, { 3210, 2958 }, { 3213, 2960 }
        }
    };

    // Zone 2 - East Desert
    private static final int[][] ZONE2_EAST_DESERT = {
            { 3363, 2929 }, { 3368, 2937 }, { 3384, 2941 }, { 3391, 2939 },
            { 3397, 2924 }, { 3391, 2917 }, { 3390, 2909 }, { 3388, 2904 },
            { 3400, 2899 }, { 3403, 2902 }, { 3413, 2899 }, { 3416, 2891 },
            { 3418, 2885 }, { 3423, 2881 }, { 3434, 2883 }, { 3443, 2881 },
            { 3447, 2879 }, { 3445, 2871 }, { 3444, 2868 }, { 3438, 2871 },
            { 3431, 2873 }, { 3423, 2871 }, { 3413, 2869 }, { 3405, 2868 },
            { 3398, 2860 }, { 3392, 2855 }, { 3391, 2847 }, { 3385, 2856 },
            { 3380, 2861 }, { 3367, 2867 }, { 3359, 2865 }, { 3351, 2863 },
            { 3342, 2854 }, { 3340, 2840 }, { 3340, 2837 }, { 3336, 2837 },
            { 3331, 2836 }, { 3329, 2826 }, { 3332, 2820 }, { 3341, 2818 },
            { 3350, 2818 }, { 3359, 2818 }, { 3366, 2818 }, { 3370, 2816 },
            { 3366, 2812 }, { 3352, 2810 }, { 3348, 2806 }, { 3348, 2802 },
            { 3347, 2793 }, { 3349, 2783 }, { 3342, 2769 }, { 3331, 2765 },
            { 3327, 2766 }, { 3326, 2810 }, { 3325, 2814 }, { 3293, 2813 },
            { 3293, 2821 }, { 3271, 2820 }, { 3274, 2836 }, { 3277, 2841 },
            { 3283, 2841 }, { 3289, 2841 }, { 3290, 2847 }, { 3290, 2851 },
            { 3289, 2854 }, { 3287, 2856 }, { 3283, 2863 }, { 3275, 2865 },
            { 3269, 2863 }, { 3269, 2873 }, { 3269, 2879 }, { 3273, 2890 },
            { 3274, 2895 }, { 3281, 2898 }, { 3284, 2897 }, { 3295, 2904 },
            { 3300, 2913 }, { 3312, 2917 }, { 3324, 2916 }, { 3338, 2918 },
            { 3341, 2924 }, { 3351, 2920 }, { 3356, 2920 }, { 3363, 2926 },
            { 3363, 2929 }
    };

    // Zone 2 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE2 = {
        // Quest Area
        {
            { 3309, 2858 }, { 3309, 2845 }, { 3314, 2841 }, { 3323, 2842 },
            { 3328, 2846 }, { 3328, 2851 }, { 3327, 2858 }, { 3323, 2861 },
            { 3318, 2863 }, { 3311, 2861 }, { 3309, 2858 }
        },

        // Rock Golem Mining Spot
        {
            { 3320, 2879 }, { 3314, 2874 }, { 3319, 2869 }, { 3326, 2869 },
            { 3329, 2872 }, { 3331, 2876 }, { 3328, 2879 }, { 3323, 2881 },
            { 3320, 2879 }
        },

        // Dungeon Entrance
        {
            { 3372, 2909 }, { 3372, 2907 }, { 3373, 2906 }, { 3373, 2905 },
            { 3371, 2903 }, { 3372, 2901 }, { 3373, 2900 }, { 3375, 2900 },
            { 3375, 2902 }, { 3374, 2903 }, { 3376, 2905 }, { 3376, 2906 },
            { 3375, 2907 }, { 3374, 2909 }, { 3373, 2910 }, { 3372, 2909 }
        }
    };

    // Zone 3 - Watering Hole
    private static final int[][] ZONE3_WATERING_HOLE = {
            { 3273, 2867 }, { 3270, 2858 }, { 3272, 2848 }, { 3275, 2841 },
            { 3284, 2837 }, { 3287, 2837 }, { 3292, 2840 }, { 3297, 2844 },
            { 3296, 2850 }, { 3294, 2856 }, { 3292, 2860 }, { 3288, 2865 },
            { 3280, 2867 }, { 3273, 2867 }
    };
}

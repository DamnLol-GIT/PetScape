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


public class TemporossArea extends RoamingArea
{
    private final int zone;

    private TemporossArea(int zone) { this.zone = zone; }

    public static TemporossArea zone1() { return new TemporossArea(1); }
    public static TemporossArea zone2() { return new TemporossArea(2); }
    public static TemporossArea zone3() { return new TemporossArea(3); }
    public static TemporossArea zone4() { return new TemporossArea(4); }

    @Override
    public String getAreaId() { return "tempoross_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        return new int[]{ NpcID.TINY_TEMPOR };
    }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        Arrays.fill(names, "Tiny Tempor");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Tiny Tempor</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        return "Just a little splash of trouble.";
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 3;
            case 2: return 5;
            case 3: return 6;
            default: return 8;
        }
    }

    @Override
    public boolean isAquatic() { return true; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return -5; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_EAST_SOUTH_DOCKS;
            case 2: return ZONE2_EAST_NORTH_DOCKS;
            case 3: return ZONE3_KARAMJA;
            default: return ZONE4_PANDAMONIUM;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 4) return FORBIDDEN_ZONE4;
        return new int[0][][];
    }

    // Zone 1 - East Side / South Docks
    private static final int[][] ZONE1_EAST_SOUTH_DOCKS = {
            { 3142, 2806 }, { 3142, 2808 }, { 3143, 2809 }, { 3143, 2811 },
            { 3143, 2814 }, { 3145, 2818 }, { 3146, 2819 }, { 3146, 2821 },
            { 3146, 2822 }, { 3146, 2823 }, { 3142, 2823 }, { 3142, 2829 },
            { 3142, 2830 }, { 3151, 2830 }, { 3152, 2832 }, { 3150, 2832 },
            { 3147, 2834 }, { 3147, 2836 }, { 3147, 2837 }, { 3147, 2838 },
            { 3137, 2838 }, { 3137, 2828 }, { 3134, 2824 }, { 3132, 2823 },
            { 3129, 2814 }, { 3129, 2800 }, { 3122, 2789 }, { 3132, 2796 },
            { 3139, 2806 }, { 3142, 2806 }
    };

    // Zone 2 - East Side / North Docks
    private static final int[][] ZONE2_EAST_NORTH_DOCKS = {
            { 3137, 2850 }, { 3133, 2857 }, { 3129, 2865 }, { 3122, 2873 },
            { 3124, 2887 }, { 3127, 2890 }, { 3125, 2902 }, { 3128, 2902 },
            { 3136, 2891 }, { 3138, 2889 }, { 3139, 2887 }, { 3138, 2886 },
            { 3138, 2884 }, { 3136, 2883 }, { 3133, 2879 }, { 3134, 2876 },
            { 3139, 2871 }, { 3146, 2865 }, { 3146, 2861 }, { 3154, 2854 },
            { 3153, 2851 }, { 3153, 2845 }, { 3151, 2843 }, { 3150, 2843 },
            { 3150, 2846 }, { 3146, 2846 }, { 3146, 2842 }, { 3142, 2842 },
            { 3141, 2844 }, { 3137, 2844 }, { 3137, 2850 }
    };

    // Zone 3 - West Side / Karamja
    private static final int[][] ZONE3_KARAMJA = {
            { 2945, 2880 }, { 2943, 2875 }, { 2947, 2875 }, { 2950, 2876 },
            { 2954, 2880 }, { 2955, 2882 }, { 2958, 2884 }, { 2960, 2886 },
            { 2963, 2887 }, { 2967, 2891 }, { 2974, 2894 }, { 2987, 2902 },
            { 2988, 2905 }, { 2989, 2910 }, { 2990, 2917 }, { 2989, 2920 },
            { 2986, 2921 }, { 2985, 2923 }, { 2982, 2923 }, { 2978, 2923 },
            { 2980, 2921 }, { 2981, 2918 }, { 2983, 2914 }, { 2984, 2910 },
            { 2983, 2905 }, { 2979, 2901 }, { 2974, 2898 }, { 2973, 2898 },
            { 2970, 2895 }, { 2967, 2893 }, { 2961, 2888 }, { 2959, 2887 },
            { 2956, 2887 }, { 2955, 2887 }, { 2953, 2885 }, { 2950, 2882 },
            { 2947, 2880 }, { 2945, 2880 }
    };

    // Zone 4 - Pandamonium
    private static final int[][] ZONE4_PANDAMONIUM = {
            { 3042, 2961 }, { 3038, 2959 }, { 3036, 2961 }, { 3037, 2964 },
            { 3036, 2965 }, { 3034, 2964 }, { 3029, 2964 }, { 3028, 2967 },
            { 3030, 2969 }, { 3034, 2971 }, { 3034, 2972 }, { 3033, 2974 },
            { 3033, 2975 }, { 3029, 2975 }, { 3027, 2974 }, { 3024, 2974 },
            { 3024, 2976 }, { 3025, 2979 }, { 3025, 2982 }, { 3026, 2984 },
            { 3026, 2986 }, { 3025, 2988 }, { 3024, 2988 }, { 3019, 2988 },
            { 3010, 2988 }, { 3007, 2993 }, { 3009, 2999 }, { 3013, 2999 },
            { 3008, 3003 }, { 3002, 3002 }, { 3000, 2999 }, { 3002, 2990 },
            { 3002, 2987 }, { 3002, 2982 }, { 3001, 2980 }, { 2999, 2977 },
            { 3002, 2972 }, { 3003, 2972 }, { 3007, 2967 }, { 3009, 2962 },
            { 3009, 2956 }, { 3012, 2953 }, { 3016, 2944 }, { 3020, 2942 },
            { 3023, 2940 }, { 3028, 2933 }, { 3028, 2929 }, { 3037, 2922 },
            { 3041, 2922 }, { 3043, 2925 }, { 3047, 2928 }, { 3049, 2932 },
            { 3052, 2935 }, { 3050, 2937 }, { 3050, 2941 }, { 3057, 2943 },
            { 3065, 2947 }, { 3068, 2954 }, { 3058, 2954 }, { 3056, 2956 },
            { 3055, 2958 }, { 3052, 2962 }, { 3043, 2961 }, { 3042, 2961 }
    };

    // Zone 4 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE4 = {
            // Island South
            {
                    { 3032, 2953 }, { 3030, 2956 }, { 3027, 2957 }, { 3025, 2956 },
                    { 3024, 2959 }, { 3021, 2959 }, { 3020, 2957 }, { 3020, 2955 },
                    { 3021, 2953 }, { 3024, 2953 }, { 3025, 2951 }, { 3027, 2950 },
                    { 3030, 2950 }, { 3032, 2953 }
            },
            // Island Mid/South
            {
                    { 3013, 2980 }, { 3012, 2979 }, { 3013, 2975 }, { 3016, 2977 },
                    { 3017, 2979 }, { 3014, 2981 }, { 3013, 2980 }
            },
            // Island Mid/North
            {
                    { 3022, 2985 }, { 3022, 2983 }, { 3020, 2981 }, { 3018, 2981 },
                    { 3016, 2984 }, { 3019, 2987 }, { 3021, 2987 }, { 3022, 2985 }
            },
            // Island North
            {
                    { 3024, 2992 }, { 3024, 2990 }, { 3020, 2988 }, { 3016, 2989 },
                    { 3015, 2988 }, { 3014, 2989 }, { 3014, 2991 }, { 3013, 2993 },
                    { 3012, 2997 }, { 3014, 2998 }, { 3017, 3000 }, { 3019, 3001 },
                    { 3022, 3001 }, { 3024, 3000 }, { 3026, 2997 }, { 3025, 2994 },
                    { 3024, 2992 }
            }
    };
}
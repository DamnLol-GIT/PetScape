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


public class DesertMiscArea extends RoamingArea
{
    private final int zone;

    private DesertMiscArea(int zone) { this.zone = zone; }

    public static DesertMiscArea zone1() { return new DesertMiscArea(1); }
    public static DesertMiscArea zone2() { return new DesertMiscArea(2); }
    public static DesertMiscArea zone3() { return new DesertMiscArea(3); }
    public static DesertMiscArea zone4() { return new DesertMiscArea(4); }
    public static DesertMiscArea zone5() { return new DesertMiscArea(5); }
    public static DesertMiscArea zone6() { return new DesertMiscArea(6); }
    public static DesertMiscArea zone7() { return new DesertMiscArea(7); }
    public static DesertMiscArea zone8() { return new DesertMiscArea(8); }

    @Override
    public String getAreaId() { return "desert_misc_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
                return new int[]{ NpcID.SMOKE_DEVIL_6655 };
            case 2:
            case 3:
            case 4:
                return new int[]{
                        NpcID.ROCK_GOLEM,
                        NpcID.ROCK_GOLEM_7439, NpcID.ROCK_GOLEM_7440, NpcID.ROCK_GOLEM_7441,
                        NpcID.ROCK_GOLEM_7442, NpcID.ROCK_GOLEM_7443, NpcID.ROCK_GOLEM_7444,
                        NpcID.ROCK_GOLEM_7445, NpcID.ROCK_GOLEM_7446, NpcID.ROCK_GOLEM_7447,
                        NpcID.ROCK_GOLEM_7448, NpcID.ROCK_GOLEM_7449, NpcID.ROCK_GOLEM_7450,
                        NpcID.ROCK_GOLEM_7451, NpcID.ROCK_GOLEM_7452, NpcID.ROCK_GOLEM_7453
                };
            case 5:
                return new int[]{ NpcID.BEAVER, NpcID.GIANT_SQUIRREL };
            case 6:
                return new int[]{ NpcID.GREAT_BLUE_HERON };
            case 7:
                return new int[]{ NpcID.KALPHITE_PRINCESS };
            default: // Zone 8
                return new int[]{ NpcID.KALPHITE_PRINCESS_6653, NpcID.GIANT_SQUIRREL, NpcID.BABY_CHINCHOMPA };
        }
    }

    @Override
    public boolean isFormFixed()
    {
        // Fixed for zones with mixed named forms, free for rock golem zones
        switch (zone)
        {
            case 5:
            case 7:
            case 8: return true;
            default: return false;
        }
    }

    @Override
    public boolean isFlying() { return zone == 6; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
                Arrays.fill(names, "Smoke Devil");
                break;
            case 2:
            case 3:
            case 4:
                Arrays.fill(names, "Rock Golem");
                break;
            case 5:
                // 2 Beaver, 4 Giant Squirrel
                for (int i = 0; i < n; i++)
                    names[i] = i < 2 ? "Beaver" : "Giant Squirrel";
                break;
            case 6:
                Arrays.fill(names, "Great Blue Heron");
                break;
            case 7:
                Arrays.fill(names, "Simon's Princess");
                break;
            default: // Zone 8
                // 1 Kalphite Princess (flying), 2 Giant Squirrel, 2 Baby Chinchompa
                for (int i = 0; i < n; i++)
                {
                    if (i < 1) names[i] = "Kalphite Princess";
                    else if (i < 3) names[i] = "Giant Squirrel";
                    else names[i] = "Baby Chinchompa";
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
            case 1: return "<col=ffff00>Smoke Devil</col>";
            case 2:
            case 3:
            case 4: return "<col=ffff00>Rock Golem</col>";
            case 5:
                return formIndex == 0 ? "<col=ffff00>Beaver</col>" : "<col=ffff00>Giant Squirrel</col>";
            case 6: return "<col=ffff00>Great Blue Heron</col>";
            case 7: return "<col=ffff00>Simon's Princess</col>";
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Kalphite Baroness</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    default: return "<col=ffff00>Baby Chinchompa</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "A tiny swirl of smoke and mischief.";
            case 2:
            case 3:
            case 4: return "A little piece of the desert, come to life.";
            case 5:
                return formIndex == 0
                        ? "Somehow found wood out here."
                        : "Absolutely certain there are acorns here somewhere.";
            case 6: return "Arrived looking important, achieved nothing.";
            case 7: return "She only moves when Simon says.";
            default:
                switch (formIndex)
                {
                    case 0: return "She didn't climb the ranks to walk.";
                    case 1: return "Lost, but committed.";
                    default: return "Do not squeeze.";
                }
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 5:
                // 2 Beaver (0), 4 Giant Squirrel (1)
                return spawnIndex < 2 ? 0 : 1;
            case 8:
                // 1 Kalphite Princess flying (0), 2 Giant Squirrel (1), 2 Baby Chinchompa (2)
                if (spawnIndex < 1) return 0;
                if (spawnIndex < 3) return 1;
                return 2;
            default:
                return super.getFormAssignment(spawnIndex, nForms);
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 12;
            case 2: return 16;
            case 3:
            case 4:
                return 2;
            case 5: return 6;
            case 6: return 4;
            case 7: return 1;
            default: return 5;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int getWanderMinDist()
    {
        // Reduce for small/cramped zones
        switch (zone)
        {
            case 3:
            case 4:
            case 7: return 4;
            default: return 8;
        }
    }

    @Override
    public int getMinSiblingSeparation()
    {
        switch (zone)
        {
            case 3:
            case 4:
            case 7: return 2;
            default: return 3;
        }
    }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_SMOKE_DUNGEON;
            case 2: return ZONE2_QUARRY;
            case 3: return ZONE3_MINING_EAST;
            case 4: return ZONE4_ULLEK_MINING;
            case 5: //ZONE5_WOODCUTTING isFlying
            case 6: return ZONE5_WOODCUTTING;
            case 7: return ZONE7_PYRAMID_MIDDLE;
            default: return ZONE8_PYRAMID_INNER;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 8) return FORBIDDEN_ZONE8;
        return new int[0][][];
    }

    // Zone 1 - Smoke Dungeon Entrance
    private static final int[][] ZONE1_SMOKE_DUNGEON = {
            { 3312, 2947 }, { 3310, 2939 }, { 3307, 2936 }, { 3299, 2935 },
            { 3294, 2939 }, { 3289, 2944 }, { 3286, 2950 }, { 3282, 2958 },
            { 3283, 2961 }, { 3283, 2965 }, { 3284, 2970 }, { 3289, 2975 },
            { 3293, 2980 }, { 3296, 2982 }, { 3302, 2978 }, { 3305, 2980 },
            { 3307, 2981 }, { 3309, 2981 }, { 3312, 2978 }, { 3313, 2977 },
            { 3314, 2976 }, { 3315, 2976 }, { 3317, 2977 }, { 3320, 2977 },
            { 3323, 2978 }, { 3324, 2978 }, { 3323, 2977 }, { 3320, 2975 },
            { 3318, 2973 }, { 3316, 2971 }, { 3314, 2969 }, { 3315, 2967 },
            { 3314, 2963 }, { 3315, 2961 }, { 3314, 2959 }, { 3315, 2956 },
            { 3314, 2954 }, { 3312, 2953 }, { 3312, 2950 }, { 3312, 2947 }
    };

    // Zone 2 - Quarry
    private static final int[][] ZONE2_QUARRY = {
            { 3196, 2925 }, { 3196, 2929 }, { 3196, 2933 }, { 3193, 2934 },
            { 3189, 2936 }, { 3186, 2934 }, { 3183, 2932 }, { 3180, 2930 },
            { 3177, 2930 }, { 3174, 2933 }, { 3171, 2934 }, { 3167, 2934 },
            { 3163, 2933 }, { 3160, 2931 }, { 3158, 2928 }, { 3160, 2926 },
            { 3160, 2923 }, { 3158, 2922 }, { 3157, 2920 }, { 3156, 2919 },
            { 3155, 2917 }, { 3153, 2915 }, { 3150, 2915 }, { 3147, 2914 },
            { 3146, 2911 }, { 3146, 2909 }, { 3145, 2900 }, { 3145, 2896 },
            { 3146, 2894 }, { 3147, 2892 }, { 3146, 2891 }, { 3148, 2890 },
            { 3148, 2890 }, { 3150, 2892 }, { 3153, 2892 }, { 3153, 2891 },
            { 3155, 2890 }, { 3159, 2891 }, { 3162, 2893 }, { 3165, 2897 },
            { 3175, 2897 }, { 3177, 2896 }, { 3182, 2899 }, { 3182, 2901 },
            { 3183, 2904 }, { 3186, 2907 }, { 3186, 2911 }, { 3188, 2913 },
            { 3188, 2916 }, { 3189, 2919 }, { 3190, 2923 }, { 3193, 2925 },
            { 3196, 2925 }
    };

    // Zone 3 - Mining Spot/East
    private static final int[][] ZONE3_MINING_EAST = {
            { 3323, 2878 }, { 3317, 2872 }, { 3318, 2870 }, { 3319, 2868 },
            { 3321, 2868 }, { 3322, 2870 }, { 3323, 2871 }, { 3322, 2871 },
            { 3325, 2874 }, { 3325, 2875 }, { 3324, 2876 }, { 3324, 2877 },
            { 3323, 2878 }
    };

    // Zone 4 - Ullek Mining Spot
    private static final int[][] ZONE4_ULLEK_MINING = {
            { 3373, 2781 }, { 3373, 2779 }, { 3374, 2779 }, { 3375, 2778 },
            { 3374, 2777 }, { 3374, 2774 }, { 3376, 2773 }, { 3378, 2773 },
            { 3382, 2774 }, { 3382, 2775 }, { 3382, 2776 }, { 3381, 2778 },
            { 3380, 2781 }, { 3379, 2783 }, { 3377, 2783 }, { 3375, 2780 },
            { 3373, 2781 }
    };

    // Zone 5/6 - South/East Woodcutting Spot
    private static final int[][] ZONE5_WOODCUTTING = {
            { 3411, 2761 }, { 3413, 2765 }, { 3417, 2766 }, { 3420, 2768 },
            { 3422, 2770 }, { 3424, 2771 }, { 3425, 2775 }, { 3424, 2777 },
            { 3423, 2782 }, { 3424, 2787 }, { 3425, 2789 }, { 3430, 2794 },
            { 3433, 2793 }, { 3436, 2793 }, { 3438, 2796 }, { 3438, 2798 },
            { 3437, 2799 }, { 3438, 2800 }, { 3439, 2801 }, { 3440, 2801 },
            { 3442, 2800 }, { 3443, 2800 }, { 3445, 2798 }, { 3443, 2796 },
            { 3443, 2795 }, { 3444, 2794 }, { 3445, 2792 }, { 3447, 2792 },
            { 3447, 2789 }, { 3447, 2787 }, { 3446, 2784 }, { 3446, 2783 },
            { 3445, 2780 }, { 3444, 2778 }, { 3445, 2777 }, { 3445, 2776 },
            { 3445, 2774 }, { 3445, 2773 }, { 3443, 2771 }, { 3442, 2770 },
            { 3442, 2767 }, { 3438, 2765 }, { 3437, 2765 }, { 3436, 2765 },
            { 3436, 2767 }, { 3437, 2767 }, { 3440, 2770 }, { 3440, 2772 },
            { 3442, 2776 }, { 3442, 2778 }, { 3439, 2782 }, { 3439, 2784 },
            { 3436, 2787 }, { 3432, 2786 }, { 3430, 2785 }, { 3430, 2783 },
            { 3430, 2780 }, { 3432, 2778 }, { 3432, 2777 }, { 3432, 2775 },
            { 3433, 2775 }, { 3433, 2774 }, { 3433, 2773 }, { 3431, 2771 },
            { 3430, 2770 }, { 3429, 2769 }, { 3427, 2766 }, { 3428, 2763 },
            { 3428, 2760 }, { 3427, 2759 }, { 3426, 2758 }, { 3424, 2758 },
            { 3421, 2758 }, { 3419, 2758 }, { 3418, 2760 }, { 3416, 2761 },
            { 3415, 2761 }, { 3411, 2761 }
    };

    // Zone 7 - Agility Pyramid Middle
    private static final int[][] ZONE7_PYRAMID_MIDDLE = {
            { 3348, 2830 }, { 3346, 2832 }, { 3342, 2832 }, { 3339, 2831 },
            { 3339, 2826 }, { 3340, 2825 }, { 3341, 2824 }, { 3344, 2823 },
            { 3345, 2823 }, { 3347, 2822 }, { 3349, 2822 }, { 3350, 2823 },
            { 3350, 2824 }, { 3349, 2825 }, { 3347, 2826 }, { 3348, 2828 },
            { 3348, 2830 }
    };

    // Zone 8 - Agility Pyramid Inner
    private static final int[][] ZONE8_PYRAMID_INNER = {
            { 3353, 2826 }, { 3356, 2825 }, { 3360, 2825 }, { 3364, 2825 },
            { 3366, 2824 }, { 3368, 2826 }, { 3370, 2826 }, { 3371, 2825 },
            { 3375, 2827 }, { 3377, 2829 }, { 3379, 2831 }, { 3380, 2833 },
            { 3380, 2835 }, { 3380, 2838 }, { 3381, 2843 }, { 3381, 2846 },
            { 3379, 2847 }, { 3379, 2850 }, { 3380, 2851 }, { 3377, 2856 },
            { 3376, 2856 }, { 3374, 2858 }, { 3372, 2858 }, { 3371, 2858 },
            { 3370, 2859 }, { 3365, 2859 }, { 3364, 2858 }, { 3361, 2858 },
            { 3357, 2858 }, { 3355, 2856 }, { 3354, 2856 }, { 3351, 2853 },
            { 3351, 2852 }, { 3350, 2851 }, { 3350, 2850 }, { 3349, 2848 },
            { 3349, 2843 }, { 3348, 2840 }, { 3348, 2835 }, { 3350, 2833 },
            { 3352, 2830 }, { 3353, 2830 }, { 3353, 2826 }
    };

    // Zone 8 forbidden Zone
    // Agility Pyramid Structure
    private static final int[][][] FORBIDDEN_ZONE8 = {
            {
                    { 3354, 2853 }, { 3376, 2853 }, { 3376, 2831 }, { 3354, 2831 },
                    { 3354, 2853 }
            }
    };
}
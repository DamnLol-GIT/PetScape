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


public class DesertSouthArea extends RoamingArea
{
    private final int zone;

    private DesertSouthArea(int zone) { this.zone = zone; }

    public static DesertSouthArea zone1() { return new DesertSouthArea(1); }
    public static DesertSouthArea zone2() { return new DesertSouthArea(2); }
    public static DesertSouthArea zone3() { return new DesertSouthArea(3); }
    public static DesertSouthArea zone4() { return new DesertSouthArea(4); }
    public static DesertSouthArea zone5() { return new DesertSouthArea(5); }

    @Override
    public String getAreaId() { return "desert_south_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1:
                return new int[]{
                        NpcID.TUMEKENS_GUARDIAN,
                        NpcID.TUMEKENS_DAMAGED_GUARDIAN,
                        NpcID.ELIDINIS_GUARDIAN,
                        NpcID.ELIDINIS_DAMAGED_GUARDIAN,
                        NpcID.AKKHITO,
                        NpcID.BABI,
                        NpcID.KEPHRITI
                };
            case 2:
            case 3:
                return new int[]{ NpcID.ZEBO };
            case 5:
                return new int[]{ NpcID.SOUP };
            default: // Zone 4
                return new int[]{ NpcID.ROCKY, NpcID.GIANT_SQUIRREL };
        }
    }

    @Override
    public boolean isFormFixed()
    {
        switch (zone)
        {
            case 1:
            case 4: return true;
            default: return false;
        }
    }

    @Override
    public boolean isAquatic() { return zone == 2 || zone == 5; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        switch (zone)
        {
            case 1:
                String[] tumokenNames = {
                        "Tumeken's Guardian", "Tumeken's Damaged Guardian",
                        "Elidinis' Guardian", "Elidinis' Damaged Guardian",
                        "Akkhito", "Babi", "Kephriti"
                };
                for (int i = 0; i < n; i++)
                    names[i] = tumokenNames[i / 3];
                break;
            case 2:
            case 3:
            case 5:
                Arrays.fill(names, zone == 5 ? "Soup" : "Zebo");
                break;
            default:
                for (int i = 0; i < n; i++)
                    names[i] = i < 12 ? "Rocky" : "Giant Squirrel";
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
                    case 0: return "<col=ffff00>Tumeken's Guardian</col>";
                    case 1: return "<col=ffff00>Tumeken's Damaged Guardian</col>";
                    case 2: return "<col=ffff00>Elidinis' Guardian</col>";
                    case 3: return "<col=ffff00>Elidinis' Damaged Guardian</col>";
                    case 4: return "<col=ffff00>Akkhito</col>";
                    case 5: return "<col=ffff00>Babi</col>";
                    default: return "<col=ffff00>Kephriti</col>";
                }
            case 2:
            case 3:
                return "<col=ffff00>Zebo</col>";
            case 5:
                return "<col=ffff00>Snap</col>";
            default:
                return formIndex == 0
                        ? "<col=ffff00>Rocky</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
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
                    case 0: return "A tiny guardian of the tombs.";
                    case 1: return "A little worse for wear, but still standing.";
                    case 2: return "Small but sacred.";
                    case 3: return "Small, chipped, and still on duty.";
                    case 4: return "Tiny hero, full-size determination.";
                    case 5: return "Sand between the toes, rage in the heart.";
                    default: return "Rolling through life one ball at a time.";
                }
            case 2:
                return "Snap.";
            case 3:
                return "What's a crocodile's favourite meal?";
            case 5:
                return "Swim, little turtle, swim!";
            default:
                return formIndex == 0
                        ? "Just browsing the pyramid. Professionally."
                        : "Still searching. Still optimistic. Still wrong.";
        }
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                //3 of each ToA (-zebo)
                return spawnIndex / 3;
            case 4:
                return spawnIndex < 12 ? 0 : 1;
            default:
                return super.getFormAssignment(spawnIndex, nForms);
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 21;
            case 2:
            case 3:
                return 2;
            case 5: return 1;
            default: return 24;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return (zone == 2 || zone == 5) ? -20 : 5; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_NECROPOLIS;
            case 2: return ZONE2_NECROPOLIS_WATER;
            case 3: return ZONE3_NECROPOLIS_BEACH;
            case 5: return ZONE5_SOUTH_RIVER;
            default: return ZONE4_SOPHANEM;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 4) return FORBIDDEN_ZONE4;
        return new int[0][][];
    }

    // Zone 1 - Necropolis
    private static final int[][] ZONE1_NECROPOLIS = {
            { 3291, 2747 }, { 3291, 2742 }, { 3292, 2742 }, { 3294, 2740 },
            { 3295, 2738 }, { 3293, 2738 }, { 3292, 2738 }, { 3291, 2737 },
            { 3288, 2734 }, { 3286, 2732 }, { 3283, 2728 }, { 3286, 2724 },
            { 3289, 2721 }, { 3289, 2719 }, { 3291, 2716 }, { 3292, 2713 },
            { 3296, 2708 }, { 3299, 2707 }, { 3301, 2706 }, { 3304, 2703 },
            { 3306, 2703 }, { 3310, 2700 }, { 3317, 2698 }, { 3320, 2698 },
            { 3320, 2698 }, { 3321, 2701 }, { 3320, 2702 }, { 3320, 2703 },
            { 3314, 2703 }, { 3313, 2706 }, { 3311, 2708 }, { 3310, 2710 },
            { 3312, 2712 }, { 3316, 2714 }, { 3323, 2714 }, { 3326, 2710 },
            { 3325, 2696 }, { 3332, 2689 }, { 3334, 2688 }, { 3338, 2684 },
            { 3348, 2681 }, { 3362, 2677 }, { 3350, 2684 }, { 3348, 2690 },
            { 3348, 2716 }, { 3346, 2720 }, { 3347, 2721 }, { 3350, 2724 },
            { 3352, 2722 }, { 3362, 2723 }, { 3367, 2723 }, { 3375, 2734 },
            { 3364, 2749 }, { 3359, 2752 }, { 3359, 2757 }, { 3354, 2757 },
            { 3336, 2748 }, { 3321, 2746 }, { 3291, 2747 }
    };

    // Zone 2 - Necropolis Water
    private static final int[][] ZONE2_NECROPOLIS_WATER = {
            { 3278, 2746 }, { 3274, 2746 }, { 3272, 2735 }, { 3273, 2733 },
            { 3276, 2730 }, { 3278, 2727 }, { 3279, 2726 }, { 3280, 2723 },
            { 3281, 2722 }, { 3283, 2720 }, { 3284, 2718 }, { 3285, 2715 },
            { 3286, 2712 }, { 3288, 2708 }, { 3288, 2705 }, { 3291, 2703 },
            { 3292, 2705 }, { 3292, 2709 }, { 3292, 2710 }, { 3291, 2712 },
            { 3291, 2713 }, { 3290, 2714 }, { 3289, 2715 }, { 3289, 2716 },
            { 3287, 2718 }, { 3287, 2720 }, { 3285, 2722 }, { 3284, 2724 },
            { 3283, 2725 }, { 3283, 2727 }, { 3282, 2728 }, { 3279, 2731 },
            { 3279, 2732 }, { 3276, 2736 }, { 3276, 2741 }, { 3277, 2742 },
            { 3278, 2746 }
    };

    // Zone 3 - Necropolis Beach
    private static final int[][] ZONE3_NECROPOLIS_BEACH = {
            { 3278, 2746 }, { 3277, 2742 }, { 3276, 2741 }, { 3276, 2736 },
            { 3279, 2732 }, { 3279, 2731 }, { 3283, 2727 }, { 3283, 2725 },
            { 3284, 2724 }, { 3285, 2722 }, { 3287, 2720 }, { 3287, 2718 },
            { 3289, 2716 }, { 3289, 2715 }, { 3291, 2713 }, { 3291, 2712 },
            { 3293, 2711 }, { 3295, 2710 }, { 3295, 2709 }, { 3297, 2708 },
            { 3298, 2707 }, { 3301, 2706 }, { 3302, 2705 }, { 3303, 2704 },
            { 3304, 2704 }, { 3304, 2706 }, { 3303, 2707 }, { 3302, 2709 },
            { 3300, 2710 }, { 3299, 2710 }, { 3297, 2712 }, { 3295, 2714 },
            { 3294, 2716 }, { 3291, 2719 }, { 3290, 2722 }, { 3288, 2726 },
            { 3285, 2730 }, { 3283, 2733 }, { 3281, 2736 }, { 3280, 2739 },
            { 3280, 2743 }, { 3281, 2746 }, { 3278, 2746 }
    };

    // Zone 4 - Sophanem
    private static final int[][] ZONE4_SOPHANEM = {
            { 3273, 2806 }, { 3279, 2807 }, { 3319, 2807 }, { 3318, 2804 },
            { 3319, 2794 }, { 3320, 2791 }, { 3320, 2778 }, { 3318, 2778 },
            { 3316, 2779 }, { 3314, 2779 }, { 3311, 2778 }, { 3311, 2766 },
            { 3313, 2763 }, { 3320, 2763 }, { 3320, 2760 }, { 3317, 2761 },
            { 3313, 2761 }, { 3310, 2757 }, { 3313, 2753 }, { 3316, 2753 },
            { 3317, 2756 }, { 3321, 2756 }, { 3320, 2754 }, { 3321, 2753 },
            { 3318, 2750 }, { 3317, 2751 }, { 3315, 2750 }, { 3283, 2750 },
            { 3282, 2751 }, { 3284, 2753 }, { 3293, 2752 }, { 3296, 2753 },
            { 3296, 2755 }, { 3295, 2756 }, { 3293, 2757 }, { 3292, 2758 },
            { 3291, 2759 }, { 3276, 2759 }, { 3276, 2762 }, { 3277, 2763 },
            { 3285, 2764 }, { 3286, 2764 }, { 3286, 2778 }, { 3285, 2779 },
            { 3276, 2779 }, { 3279, 2781 }, { 3281, 2781 }, { 3280, 2789 },
            { 3277, 2789 }, { 3276, 2794 }, { 3274, 2797 }, { 3272, 2803 },
            { 3273, 2806 }
    };

    // Zone 4 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE4 = {
            // Pyramid North
            {
                    { 3283, 2801 }, { 3295, 2801 }, { 3295, 2789 }, { 3283, 2789 },
                    { 3283, 2801 }
            },

            // Pyramid South
            {
                    { 3288, 2780 }, { 3302, 2780 }, { 3302, 2766 }, { 3288, 2766 },
                    { 3288, 2780 }
            }
    };

    // Zone 5 - Necropolis South River/Ocean
    private static final int[][] ZONE5_SOUTH_RIVER = {
            { 3304, 2701 }, { 3306, 2701 }, { 3306, 2699 }, { 3307, 2699 },
            { 3307, 2694 }, { 3305, 2694 }, { 3305, 2695 }, { 3303, 2695 },
            { 3303, 2697 }, { 3298, 2697 }, { 3298, 2696 }, { 3297, 2696 },
            { 3297, 2695 }, { 3291, 2695 }, { 3291, 2696 }, { 3287, 2696 },
            { 3287, 2697 }, { 3285, 2697 }, { 3285, 2698 }, { 3289, 2698 },
            { 3292, 2699 }, { 3293, 2699 }, { 3295, 2698 }, { 3296, 2698 },
            { 3299, 2699 }, { 3300, 2702 }, { 3304, 2701 }
    };
}
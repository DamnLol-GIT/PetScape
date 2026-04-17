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


public class AbyssalArea extends RoamingArea
{
    private final int zone;

    private AbyssalArea(int zone) { this.zone = zone; }

    public static AbyssalArea zone1() { return new AbyssalArea(1); }
    public static AbyssalArea zone2() { return new AbyssalArea(2); }
    public static AbyssalArea zone3() { return new AbyssalArea(3); }

    @Override
    public String getAreaId() { return "abyssal_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        // Zone 1 - Runecrafting Abyss - Abyssal Protector
        if (zone == 1)
            return new int[]{ NpcID.ABYSSAL_PROTECTOR };

        // Zone 3 - Abyssal Rift - LilViathan
        if (zone == 3)
            return new int[]{ NpcID.LILVIATHAN };

        // Zone 2 - Abyssal Nexus - Abyssal Orphan
        return new int[]{ NpcID.ABYSSAL_ORPHAN };
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        String petName = zone == 1 ? "Abyssal Protector" : zone == 3 ? "Lil' Viathan" : "Abyssal Orphan";
        Arrays.fill(names, petName);
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        if (zone == 1) return "<col=ffff00>Abyssal Protector</col>";
        if (zone == 3) return "<col=ffff00>Lil' Viathan</col>";
        return "<col=ffff00>Abyssal Orphan</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        if (zone == 1) return "A tiny protector of the rift.";
        if (zone == 3) return "A tiny terror from the depths of the cuteness.";
        return "Isn't it cute? It seems to want some more..";
    }

    @Override
    public int getSpawnCount()
    {
        if (zone == 1) return 13; // Runecrafting Abyss - Abyssal Protectors
        if (zone == 3) return 4; // Abyssal Rift - LilViathan
        return 18; // Abyssal Nexus - Abyssal Orphans
    }

    @Override
    public int getWanderMinDist() { return 6; }

    @Override
    public int getMinSiblingSeparation() { return 3; }

    @Override
    public int getPlane() { return 0; }

    @Override
    public boolean isAquatic() { return zone == 3; }

    @Override
    public int getZOffset() { return zone == 3 ? -5 : 10; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        if (zone == 1) return ZONE1_RC_ABYSS;
        if (zone == 3) return ZONE3_RIFT;
        return ZONE2_NEXUS;
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 1) return FORBIDDEN_ZONE1_RC_ABYSS;
        if (zone == 2) return FORBIDDEN_ZONE2_NEXUS;
        return new int[0][][];
    }

    // Zone 2 - Abyssal Nexus (inner ring)
    private static final int[][] ZONE2_NEXUS = {
            { 2989, 4812 }, { 2996, 4816 }, { 3000, 4807 }, { 3002, 4804 },
            { 3002, 4794 }, { 3004, 4794 }, { 3004, 4790 }, { 3010, 4784 },
            { 3019, 4784 }, { 3022, 4786 }, { 3030, 4787 }, { 3034, 4792 },
            { 3036, 4797 }, { 3039, 4802 }, { 3046, 4792 }, { 3049, 4791 },
            { 3049, 4790 }, { 3069, 4792 }, { 3078, 4794 }, { 3085, 4804 },
            { 3084, 4808 }, { 3083, 4812 }, { 3083, 4814 }, { 3087, 4816 },
            { 3091, 4817 }, { 3096, 4812 }, { 3092, 4811 }, { 3091, 4810 },
            { 3089, 4808 }, { 3089, 4803 }, { 3088, 4797 }, { 3086, 4796 },
            { 3085, 4795 }, { 3083, 4792 }, { 3079, 4791 }, { 3079, 4790 },
            { 3078, 4787 }, { 3079, 4785 }, { 3081, 4784 }, { 3080, 4781 },
            { 3078, 4781 }, { 3076, 4780 }, { 3075, 4776 }, { 3076, 4775 },
            { 3077, 4773 }, { 3080, 4771 }, { 3080, 4768 }, { 3082, 4767 },
            { 3080, 4766 }, { 3079, 4765 }, { 3078, 4761 }, { 3078, 4755 },
            { 3080, 4752 }, { 3086, 4749 }, { 3091, 4749 }, { 3094, 4751 },
            { 3097, 4752 }, { 3100, 4749 }, { 3095, 4744 }, { 3093, 4744 },
            { 3092, 4745 }, { 3091, 4744 }, { 3088, 4745 }, { 3086, 4746 },
            { 3085, 4748 }, { 3080, 4748 }, { 3079, 4749 }, { 3079, 4751 },
            { 3077, 4752 }, { 3076, 4755 }, { 3075, 4756 }, { 3074, 4759 },
            { 3070, 4763 }, { 3070, 4764 }, { 3067, 4766 }, { 3066, 4767 },
            { 3058, 4767 }, { 3058, 4764 }, { 3054, 4764 }, { 3052, 4761 },
            { 3051, 4761 }, { 3050, 4762 }, { 3048, 4763 }, { 3048, 4764 },
            { 3047, 4766 }, { 3045, 4766 }, { 3044, 4765 }, { 3043, 4766 },
            { 3040, 4766 }, { 3039, 4764 }, { 3038, 4764 }, { 3035, 4764 },
            { 3035, 4765 }, { 3031, 4764 }, { 3028, 4764 }, { 3027, 4763 },
            { 3027, 4762 }, { 3023, 4763 }, { 3022, 4763 }, { 3021, 4764 },
            { 3013, 4764 }, { 3005, 4761 }, { 3004, 4758 }, { 3003, 4753 },
            { 2993, 4749 }, { 2993, 4747 }, { 2989, 4746 }, { 2989, 4748 },
            { 2988, 4749 }, { 2983, 4749 }, { 2980, 4745 }, { 2986, 4754 },
            { 2991, 4754 }, { 2991, 4752 }, { 2997, 4752 }, { 2997, 4753 },
            { 3003, 4760 }, { 3003, 4766 }, { 3002, 4768 }, { 3002, 4769 },
            { 3000, 4770 }, { 3000, 4772 }, { 3000, 4777 }, { 3000, 4779 },
            { 3000, 4781 }, { 3000, 4783 }, { 2998, 4783 }, { 2998, 4784 },
            { 2996, 4784 }, { 2996, 4787 }, { 2995, 4788 }, { 2996, 4789 },
            { 2997, 4792 }, { 2998, 4793 }, { 2998, 4797 }, { 2998, 4800 },
            { 2998, 4801 }, { 2997, 4802 }, { 2997, 4804 }, { 2997, 4804 },
            { 2996, 4805 }, { 2996, 4806 }, { 2994, 4807 }, { 2994, 4808 },
            { 2993, 4810 }, { 2989, 4812 }
    };

    // Zone 2 forbidden Zone (pillars)
    private static final int[][][] FORBIDDEN_ZONE2_NEXUS = {
            {
                    { 3012, 4780 }, { 3026, 4780 }, { 3029, 4772 }, { 3022, 4767 },
                    { 3020, 4768 }, { 3019, 4767 }, { 3010, 4767 }, { 3007, 4767 },
                    { 3005, 4771 }, { 3005, 4775 }, { 3006, 4776 }, { 3012, 4779 },
                    { 3012, 4780 }
            },
            {
                    { 3052, 4783 }, { 3057, 4784 }, { 3065, 4784 }, { 3065, 4782 },
                    { 3069, 4782 }, { 3073, 4778 }, { 3072, 4776 }, { 3062, 4771 },
                    { 3055, 4771 }, { 3051, 4773 }, { 3051, 4774 }, { 3052, 4775 },
                    { 3052, 4778 }, { 3052, 4779 }, { 3052, 4783 }
            }
    };

    // Zone 1 - Runecrafting Abyss
    private static final int[][] ZONE1_RC_ABYSS = {
            { 3031, 4844 }, { 3030, 4844 }, { 3027, 4841 }, { 3027, 4839 },
            { 3025, 4837 }, { 3025, 4835 }, { 3024, 4835 }, { 3024, 4832 },
            { 3025, 4831 }, { 3025, 4828 }, { 3026, 4826 }, { 3027, 4824 },
            { 3028, 4823 }, { 3028, 4822 }, { 3029, 4821 }, { 3030, 4821 },
            { 3031, 4820 }, { 3034, 4820 }, { 3035, 4819 }, { 3036, 4819 },
            { 3037, 4818 }, { 3038, 4818 }, { 3039, 4817 }, { 3040, 4817 },
            { 3041, 4817 }, { 3042, 4818 }, { 3042, 4818 }, { 3044, 4819 },
            { 3046, 4819 }, { 3047, 4820 }, { 3048, 4820 }, { 3049, 4821 },
            { 3050, 4821 }, { 3052, 4823 }, { 3052, 4824 }, { 3053, 4826 },
            { 3053, 4827 }, { 3054, 4828 }, { 3054, 4830 }, { 3055, 4831 },
            { 3055, 4832 }, { 3054, 4832 }, { 3054, 4836 }, { 3053, 4837 },
            { 3053, 4839 }, { 3052, 4840 }, { 3052, 4841 }, { 3051, 4841 },
            { 3050, 4842 }, { 3050, 4843 }, { 3048, 4845 }, { 3046, 4845 },
            { 3045, 4846 }, { 3044, 4846 }, { 3042, 4847 }, { 3041, 4846 },
            { 3039, 4846 }, { 3039, 4847 }, { 3037, 4847 }, { 3036, 4846 },
            { 3035, 4846 }, { 3034, 4845 }, { 3032, 4845 }, { 3031, 4844 }
    };

    // Zone 1 forbidden Zone (outer wall)
    private static final int[][][] FORBIDDEN_ZONE1_RC_ABYSS = {
            {
                    { 3043, 4841 }, { 3041, 4838 }, { 3041, 4837 }, { 3042, 4836 },
                    { 3043, 4834 }, { 3044, 4833 }, { 3044, 4831 }, { 3043, 4830 },
                    { 3042, 4828 }, { 3040, 4828 }, { 3039, 4827 }, { 3037, 4828 },
                    { 3036, 4829 }, { 3035, 4830 }, { 3035, 4831 }, { 3035, 4833 },
                    { 3035, 4833 }, { 3036, 4834 }, { 3038, 4836 }, { 3038, 4837 },
                    { 3038, 4838 }, { 3038, 4839 }, { 3038, 4841 }, { 3038, 4842 },
                    { 3033, 4842 }, { 3031, 4840 }, { 3031, 4840 }, { 3030, 4838 },
                    { 3029, 4836 }, { 3028, 4835 }, { 3028, 4834 }, { 3029, 4833 },
                    { 3029, 4832 }, { 3030, 4829 }, { 3031, 4828 }, { 3031, 4827 },
                    { 3032, 4826 }, { 3032, 4825 }, { 3033, 4824 }, { 3034, 4824 },
                    { 3035, 4823 }, { 3036, 4823 }, { 3037, 4822 }, { 3043, 4822 },
                    { 3044, 4823 }, { 3045, 4824 }, { 3045, 4825 }, { 3046, 4826 },
                    { 3047, 4827 }, { 3048, 4827 }, { 3048, 4828 }, { 3049, 4829 },
                    { 3049, 4831 }, { 3050, 4832 }, { 3050, 4835 }, { 3049, 4837 },
                    { 3049, 4838 }, { 3048, 4839 }, { 3048, 4840 }, { 3048, 4841 },
                    { 3047, 4841 }, { 3046, 4842 }, { 3042, 4842 }, { 3042, 4840 },
                    { 3041, 4838 }, { 3043, 4841 }
            }
    };

    // Zone 3 - Abyssal Rift
    private static final int[][] ZONE3_RIFT = {
            { 2030, 6454 }, { 2037, 6458 }, { 2040, 6455 }, { 2040, 6453 },
            { 2041, 6452 }, { 2041, 6451 }, { 2040, 6450 }, { 2040, 6449 },
            { 2039, 6448 }, { 2038, 6445 }, { 2038, 6442 }, { 2038, 6441 },
            { 2037, 6440 }, { 2037, 6437 }, { 2036, 6435 }, { 2037, 6433 },
            { 2035, 6432 }, { 2034, 6429 }, { 2033, 6427 }, { 2033, 6426 },
            { 2033, 6421 }, { 2036, 6418 }, { 2037, 6412 }, { 2036, 6412 },
            { 2035, 6413 }, { 2032, 6413 }, { 2030, 6415 }, { 2030, 6418 },
            { 2029, 6419 }, { 2029, 6420 }, { 2028, 6421 }, { 2028, 6424 },
            { 2029, 6425 }, { 2029, 6428 }, { 2030, 6430 }, { 2030, 6433 },
            { 2030, 6436 }, { 2032, 6439 }, { 2034, 6442 }, { 2034, 6446 },
            { 2035, 6448 }, { 2035, 6450 }, { 2033, 6452 }, { 2033, 6453 },
            { 2030, 6454 }
    };
}
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


public class LumbridgeArea extends RoamingArea
{
    private final int zone;

    private LumbridgeArea(int zone) { this.zone = zone; }

    public static LumbridgeArea zone1()  { return new LumbridgeArea(1); }
    public static LumbridgeArea zone2()  { return new LumbridgeArea(2); }
    public static LumbridgeArea zone3()  { return new LumbridgeArea(3); }
    public static LumbridgeArea zone4()  { return new LumbridgeArea(4); }
    public static LumbridgeArea zone5()  { return new LumbridgeArea(5); }
    public static LumbridgeArea zone6()  { return new LumbridgeArea(6); }
    public static LumbridgeArea zone7()  { return new LumbridgeArea(7); }
    public static LumbridgeArea zone8()  { return new LumbridgeArea(8); }
    public static LumbridgeArea zone9()  { return new LumbridgeArea(9); }
    public static LumbridgeArea zone10() { return new LumbridgeArea(10); }
    public static LumbridgeArea zone11() { return new LumbridgeArea(11); }
    public static LumbridgeArea zone12() { return new LumbridgeArea(12); }
    public static LumbridgeArea zone13() { return new LumbridgeArea(13); }
    public static LumbridgeArea zone14() { return new LumbridgeArea(14); }

    @Override
    public String getAreaId() { return "lumbridge_zone" + zone; }

    @Override
    public int[] getPetNpcIds()
    {
        switch (zone)
        {
            case 1: return new int[]{ NpcID.BLOODHOUND, NpcID.GIANT_SQUIRREL };
            case 2: return new int[]{ NpcID.BLOODHOUND, NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 3: return new int[]{ NpcID.BONE_SQUIRREL };
            case 4: return new int[]{
                    NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                    NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                    NpcID.TANGLEROOT_9496, NpcID.TANGLEROOT_9497, NpcID.TANGLEROOT_9498,
                    NpcID.TANGLEROOT_9499, NpcID.TANGLEROOT_9500, NpcID.TANGLEROOT_9501
            };
            case 5: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 6: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 7: return new int[]{ NpcID.HERON };
            case 8: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 9: return new int[]{ NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 10: return new int[]{ NpcID.BEAVER, NpcID.GIANT_SQUIRREL, NpcID.ROCKY };
            case 11: return new int[]{ NpcID.BEEF, NpcID.GIANT_SQUIRREL, NpcID.ROCKY, NpcID.BEAVER };
            case 12: return new int[]{ NpcID.BEEF, NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ROCKY };
            case 13: return new int[]{
                    NpcID.TANGLEROOT, NpcID.TANGLEROOT_7352, NpcID.TANGLEROOT_9492,
                    NpcID.TANGLEROOT_9493, NpcID.TANGLEROOT_9494, NpcID.TANGLEROOT_9495,
                    NpcID.GIANT_SQUIRREL, NpcID.BEAVER, NpcID.ZIGGY
            };
            default: return new int[]{ NpcID.BEEF, NpcID.GIANT_SQUIRREL, NpcID.BEAVER };
        }
    }

    @Override
    public boolean isFormFixed() { return true; }

    @Override
    public boolean isFlying() { return zone == 7; }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms)
    {
        switch (zone)
        {
            case 1:
                // 4 Bloodhound(0), 4 Giant Squirrel(1)
                return spawnIndex < 4 ? 0 : 1;
            case 2:
                // 2 Bloodhound(0), 8 Giant Squirrel(1), 2 Beaver(2), 2 Rocky(3)
                if (spawnIndex < 2) return 0;
                if (spawnIndex < 10) return 1;
                if (spawnIndex < 12) return 2;
                return 3;
            case 4:
                // 12 Tangleroot(0-12)
                return spawnIndex % nForms;
            case 5:
                // 12 Giant Squirrel(0), 6 Beaver(1), 4 Rocky(2)
                if (spawnIndex < 12) return 0;
                if (spawnIndex < 18) return 1;
                return 2;
            case 6:
                // 6 Giant Squirrel(0), 4 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 10) return 1;
                return 2;
            case 8:
                // 4 Giant Squirrel(0), 2 Rocky(1), 2 Beaver(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 6) return 1;
                return 2;
            case 9:
                // 6 Giant Squirrel(0), 2 Beaver(1), 2 Rocky(2)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 8) return 1;
                return 2;
            case 10:
                // 4 Beaver(0), 4 Giant Squirrel(1), 2 Rocky(2)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 8) return 1;
                return 2;
            case 11:
                // 6 Beef(0), 6 Giant Squirrel(1), 2 Rocky(2), 4 Beaver(3)
                if (spawnIndex < 6) return 0;
                if (spawnIndex < 12) return 1;
                if (spawnIndex < 14) return 2;
                return 3;
            case 12:
                // 4 Beef(0), 6 Giant Squirrel(1), 2 Beaver(2), 2 Rocky(3)
                if (spawnIndex < 4) return 0;
                if (spawnIndex < 10) return 1;
                if (spawnIndex < 12) return 2;
                return 3;
            case 13:
                // 6 Tangleroot(0-5), 4 Giant Squirrel(6), 2 Beaver(7), 1 Ziggy(8)
                if (spawnIndex < 6) return spawnIndex;
                if (spawnIndex < 10) return 6;
                if (spawnIndex < 12) return 7;
                return 8;
            default:
                // Zone 14: 8 Beef(0), 6 Giant Squirrel(1), 4 Beaver(2)
                if (spawnIndex < 8) return 0;
                if (spawnIndex < 14) return 1;
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
                    names[i] = i < 4 ? "Bloodhound" : "Giant Squirrel";
                break;
            case 2:
                for (int i = 0; i < n; i++)
                {
                    if (i < 2) names[i] = "Bloodhound";
                    else if (i < 10) names[i] = "Giant Squirrel";
                    else if (i < 12) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 3:
                Arrays.fill(names, "Bone Squirrel");
                break;
            case 4:
                Arrays.fill(names, "Tangleroot");
                break;
            case 5:
                for (int i = 0; i < n; i++)
                {
                    if (i < 12) names[i] = "Giant Squirrel";
                    else if (i < 18) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 6:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 10) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 7:
                Arrays.fill(names, "Heron");
                break;
            case 8:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Giant Squirrel";
                    else if (i < 6) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 9:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Giant Squirrel";
                    else if (i < 8) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 10:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Beaver";
                    else if (i < 8) names[i] = "Giant Squirrel";
                    else names[i] = "Rocky";
                }
                break;
            case 11:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Beef";
                    else if (i < 12) names[i] = "Giant Squirrel";
                    else if (i < 14) names[i] = "Rocky";
                    else names[i] = "Beaver";
                }
                break;
            case 12:
                for (int i = 0; i < n; i++)
                {
                    if (i < 4) names[i] = "Beef";
                    else if (i < 10) names[i] = "Giant Squirrel";
                    else if (i < 12) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            case 13:
                for (int i = 0; i < n; i++)
                {
                    if (i < 6) names[i] = "Tangleroot";
                    else if (i < 10) names[i] = "Giant Squirrel";
                    else if (i < 12) names[i] = "Beaver";
                    else names[i] = "Rocky";
                }
                break;
            default:
                for (int i = 0; i < n; i++)
                {
                    if (i < 8) names[i] = "Beef";
                    else if (i < 14) names[i] = "Giant Squirrel";
                    else names[i] = "Beaver";
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
                return formIndex == 0
                        ? "<col=ffff00>Bloodhound</col>"
                        : "<col=ffff00>Giant Squirrel</col>";
            case 2:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Bloodhound</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 3:
                return "<col=ffff00>Bone Squirrel</col>";
            case 4:
                return "<col=ffff00>Tangleroot</col>";
            case 5:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 6:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 7:
                return "<col=ffff00>Heron</col>";
            case 8:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 9:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Giant Squirrel</col>";
                    case 1: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 10:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beaver</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 11:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beef</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Rocky</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
            case 12:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beef</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    case 2: return "<col=ffff00>Beaver</col>";
                    default: return "<col=ffff00>Rocky</col>";
                }
            case 13:
                if (formIndex <= 5) return "<col=ffff00>Tangleroot</col>";
                if (formIndex == 6) return "<col=ffff00>Giant Squirrel</col>";
                if (formIndex == 7) return "<col=ffff00>Beaver</col>";
                return "<col=ffff00>Ziggy</col>";
            default:
                switch (formIndex)
                {
                    case 0: return "<col=ffff00>Beef</col>";
                    case 1: return "<col=ffff00>Giant Squirrel</col>";
                    default: return "<col=ffff00>Beaver</col>";
                }
        }
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1:
                return formIndex == 0
                        ? "Suspicious of anyone without a bronze sword."
                        : "Faster than the castle messengers.";
            case 2:
                switch (formIndex)
                {
                    case 0: return "Sniffing out tutorial-island escapees.";
                    case 1: return "Already circled the castle four times.";
                    case 2: return "Thinks the castle could use more timber.";
                    default: return "Takes the occasional coin from unattended pockets.";
                }
            case 3:
                return "Rattles softly between the headstones.";
            case 4:
                return "Would prefer fewer rakes, more respect.";
            case 5:
                switch (formIndex)
                {
                    case 0: return "Launching itself between branches with reckless faith.";
                    case 1: return "Eyeing the taller trees with long-term plans.";
                    default: return "Any missing belongings?";
                }
            case 6:
                switch (formIndex)
                {
                    case 0: return "Found a shortcut you haven't.";
                    case 1: return "A woodsman without the axe.";
                    default: return "Patiently waiting for travellers to pass.";
                }
            case 7:
                return "People kept trying to catch him saying they were 'Dry'.";
            case 8:
                switch (formIndex)
                {
                    case 0: return "Couldn't sit still if he tried.";
                    case 1: return "A quiet professional at work.";
                    default: return "Chewing his way through the afternoon.";
                }
            case 9:
                switch (formIndex)
                {
                    case 0: return "Surveying the woods.";
                    case 1: return "Prefers a riverside to any road.";
                    default: return "Quietly judging every passing adventurer.";
                }
            case 10:
                switch (formIndex)
                {
                    case 0: return "Found the ideal log for a new project.";
                    case 1: return "Faster than the wind.";
                    default: return "Makes no noise. Leaves no witnesses.";
                }
            case 11:
                switch (formIndex)
                {
                    case 0: return "A free-range cow on personal time.";
                    case 1: return "Running errands for no one in particular.";
                    case 2: return "Hanging around the fishing spot, for reasons.";
                    default: return "Appraising the newcomers. Harshly.";
                }
            case 12:
                switch (formIndex)
                {
                    case 0: return "Wants nothing to do with the milk trade.";
                    case 1: return "Found a shortcut.";
                    case 2: return "Tooth-first, thought-second.";
                    default: return "Looking at all the newcomers intently.";
                }
            case 13:
                if (formIndex <= 5) return "Rooted in the routine.";
                if (formIndex == 6) return "Darts between branches with a schedule to keep.";
                if (formIndex == 7) return "Redirecting the farm's irrigation.";
                return "Didn't that used to be a bush?";
            default:
                switch (formIndex)
                {
                    case 0: return "Wandered off during milking.";
                    case 1: return "The canopy's fastest commuter.";
                    default: return "Looking at the delicious fenceline.";
                }
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 8;
            case 2: return 14;
            case 3: return 3;
            case 4: return 3;
            case 5: return 22;
            case 6: return 12;
            case 7: return 2;
            case 8: return 8;
            case 9: return 10;
            case 10: return 10;
            case 11: return 18;
            case 12: return 14;
            case 13: return 13;
            default: return 18;
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
            case 1: return ZONE1_CASTLE;
            case 2: return ZONE2_TOWN;
            case 3: return ZONE3_GRAVEYARD;
            case 4: return ZONE4_FARMING_PATCH;
            case 5: return ZONE5_WEST_WOODS_SOUTH;
            case 6: return ZONE6_WEST_WOODS_NORTH;
            case 7: return ZONE7_WEST_WOODS_LAKE;
            case 8: return ZONE7_WEST_WOODS_LAKE;
            case 9: return ZONE9_NW_PATH;
            case 10: return ZONE10_NW_N;
            case 11: return ZONE11_EAST_SOUTH;
            case 12: return ZONE12_EAST_NORTH;
            case 13: return ZONE13_EAST_FARMING;
            default: return ZONE14_NW_FIELD;
        }
    }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (zone == 1) return FORBIDDEN_ZONE1;
        if (zone == 2) return FORBIDDEN_ZONE2;
        return new int[0][][];
    }

    // Zone 1 - Lumbridge Castle
    private static final int[][] ZONE1_CASTLE = {
            { 3201, 3234 }, { 3204, 3237 }, { 3212, 3237 }, { 3215, 3235 },
            { 3221, 3235 }, { 3227, 3229 }, { 3227, 3222 }, { 3228, 3221 },
            { 3230, 3221 }, { 3230, 3217 }, { 3228, 3217 }, { 3227, 3216 },
            { 3227, 3209 }, { 3221, 3203 }, { 3215, 3203 }, { 3213, 3201 },
            { 3204, 3201 }, { 3201, 3204 }, { 3201, 3234 }
    };

    // Zone 1 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE1 = {
            // Dining Room (Quest Zone)
            {
                    { 3205, 3227 }, { 3213, 3227 }, { 3213, 3218 }, { 3205, 3218 },
                    { 3205, 3227 }
            }
    };

    // Zone 2 - Lumbridge Town
    private static final int[][] ZONE2_TOWN = {
            { 3198, 3218 }, { 3196, 3214 }, { 3195, 3202 }, { 3194, 3199 },
            { 3195, 3198 }, { 3199, 3198 }, { 3200, 3199 }, { 3224, 3199 },
            { 3226, 3197 }, { 3229, 3197 }, { 3229, 3199 }, { 3238, 3199 },
            { 3238, 3204 }, { 3240, 3204 }, { 3240, 3209 }, { 3238, 3209 },
            { 3238, 3212 }, { 3240, 3212 }, { 3240, 3216 }, { 3248, 3216 },
            { 3248, 3204 }, { 3252, 3204 }, { 3253, 3196 }, { 3258, 3200 },
            { 3258, 3208 }, { 3257, 3210 }, { 3256, 3211 }, { 3255, 3213 },
            { 3253, 3214 }, { 3252, 3215 }, { 3251, 3217 }, { 3249, 3219 },
            { 3245, 3219 }, { 3242, 3221 }, { 3242, 3225 }, { 3241, 3228 },
            { 3239, 3229 }, { 3238, 3231 }, { 3237, 3233 }, { 3236, 3235 },
            { 3236, 3239 }, { 3235, 3241 }, { 3235, 3244 }, { 3234, 3247 },
            { 3234, 3251 }, { 3234, 3253 }, { 3233, 3256 }, { 3233, 3258 },
            { 3232, 3260 }, { 3231, 3261 }, { 3228, 3264 }, { 3223, 3265 },
            { 3220, 3265 }, { 3214, 3265 }, { 3214, 3261 }, { 3214, 3257 },
            { 3214, 3256 }, { 3210, 3256 }, { 3205, 3256 }, { 3204, 3255 },
            { 3202, 3254 }, { 3201, 3253 }, { 3200, 3249 }, { 3199, 3245 },
            { 3198, 3241 }, { 3198, 3231 }, { 3198, 3218 }
    };

    // Zone 2 forbidden Zones
    private static final int[][][] FORBIDDEN_ZONE2 = {
            // Lumbridge Castle
            {
                    { 3201, 3234 }, { 3204, 3237 }, { 3212, 3237 }, { 3215, 3235 },
                    { 3221, 3235 }, { 3227, 3229 }, { 3227, 3222 }, { 3228, 3221 },
                    { 3230, 3221 }, { 3230, 3217 }, { 3228, 3217 }, { 3227, 3216 },
                    { 3227, 3209 }, { 3221, 3203 }, { 3215, 3203 }, { 3213, 3201 },
                    { 3204, 3201 }, { 3201, 3204 }, { 3201, 3234 }
            }
    };

    // Zone 3 - Lumbridge Graveyard
    private static final int[][] ZONE3_GRAVEYARD = {
            { 3238, 3201 }, { 3238, 3204 }, { 3248, 3204 }, { 3252, 3204 },
            { 3252, 3196 }, { 3247, 3196 }, { 3247, 3191 }, { 3238, 3191 },
            { 3238, 3201 }
    };

    // Zone 4 - Farming Patch
    private static final int[][] ZONE4_FARMING_PATCH = {
            { 3192, 3240 }, { 3189, 3239 }, { 3187, 3236 }, { 3185, 3233 },
            { 3188, 3228 }, { 3191, 3226 }, { 3194, 3225 }, { 3197, 3226 },
            { 3199, 3228 }, { 3200, 3230 }, { 3200, 3234 }, { 3200, 3237 },
            { 3195, 3240 }, { 3192, 3240 }
    };

    // Zone 5 - West Woods (South)
    private static final int[][] ZONE5_WEST_WOODS_SOUTH = {
            { 3206, 3239 }, { 3205, 3243 }, { 3203, 3252 }, { 3200, 3252 },
            { 3197, 3251 }, { 3192, 3248 }, { 3191, 3243 }, { 3186, 3241 },
            { 3181, 3242 }, { 3176, 3240 }, { 3175, 3237 }, { 3169, 3236 },
            { 3164, 3235 }, { 3162, 3237 }, { 3159, 3235 }, { 3156, 3232 },
            { 3152, 3232 }, { 3149, 3233 }, { 3146, 3231 }, { 3144, 3229 },
            { 3142, 3226 }, { 3131, 3223 }, { 3131, 3222 }, { 3129, 3220 },
            { 3129, 3217 }, { 3127, 3214 }, { 3128, 3211 }, { 3128, 3208 },
            { 3134, 3206 }, { 3136, 3209 }, { 3144, 3216 }, { 3148, 3217 },
            { 3149, 3215 }, { 3156, 3213 }, { 3157, 3212 }, { 3160, 3209 },
            { 3162, 3208 }, { 3170, 3208 }, { 3172, 3208 }, { 3176, 3208 },
            { 3179, 3209 }, { 3183, 3209 }, { 3184, 3209 }, { 3191, 3204 },
            { 3193, 3203 }, { 3194, 3201 }, { 3196, 3200 }, { 3198, 3200 },
            { 3199, 3209 }, { 3196, 3218 }, { 3195, 3221 }, { 3196, 3223 },
            { 3197, 3225 }, { 3198, 3226 }, { 3198, 3229 }, { 3198, 3231 },
            { 3198, 3234 }, { 3204, 3239 }, { 3206, 3239 }
    };

    // Zone 6 - West Woods (North)
    private static final int[][] ZONE6_WEST_WOODS_NORTH = {
            { 3119, 3220 }, { 3117, 3225 }, { 3114, 3226 }, { 3114, 3232 },
            { 3116, 3232 }, { 3122, 3232 }, { 3128, 3232 }, { 3132, 3234 },
            { 3135, 3235 }, { 3136, 3238 }, { 3136, 3242 }, { 3135, 3248 },
            { 3135, 3251 }, { 3135, 3255 }, { 3138, 3258 }, { 3145, 3261 },
            { 3154, 3263 }, { 3157, 3268 }, { 3159, 3265 }, { 3160, 3264 },
            { 3165, 3262 }, { 3172, 3262 }, { 3175, 3261 }, { 3179, 3262 },
            { 3184, 3263 }, { 3187, 3262 }, { 3188, 3262 }, { 3191, 3259 },
            { 3193, 3255 }, { 3195, 3255 }, { 3196, 3254 }, { 3194, 3250 },
            { 3192, 3248 }, { 3188, 3245 }, { 3186, 3245 }, { 3184, 3243 },
            { 3176, 3241 }, { 3170, 3239 }, { 3168, 3239 }, { 3158, 3235 },
            { 3155, 3234 }, { 3151, 3235 }, { 3147, 3233 }, { 3137, 3227 },
            { 3132, 3226 }, { 3126, 3223 }, { 3124, 3222 }, { 3119, 3220 }
    };

    // Zone 7/8 - West Woods (Lake) - shared polygon for flying Heron and grounded
    private static final int[][] ZONE7_WEST_WOODS_LAKE = {
            { 3159, 3287 }, { 3163, 3287 }, { 3168, 3285 }, { 3172, 3284 },
            { 3175, 3285 }, { 3179, 3284 }, { 3182, 3283 }, { 3183, 3281 },
            { 3183, 3278 }, { 3183, 3270 }, { 3182, 3268 }, { 3180, 3267 },
            { 3175, 3265 }, { 3171, 3263 }, { 3168, 3263 }, { 3162, 3263 },
            { 3158, 3266 }, { 3157, 3271 }, { 3157, 3271 }, { 3158, 3273 },
            { 3158, 3281 }, { 3159, 3283 }, { 3159, 3287 }
    };

    // Zone 9 - N/W Path
    private static final int[][] ZONE9_NW_PATH = {
            { 3131, 3272 }, { 3135, 3270 }, { 3137, 3272 }, { 3137, 3277 },
            { 3138, 3282 }, { 3137, 3284 }, { 3137, 3287 }, { 3138, 3289 },
            { 3139, 3294 }, { 3143, 3294 }, { 3146, 3294 }, { 3150, 3293 },
            { 3152, 3293 }, { 3154, 3292 }, { 3159, 3288 }, { 3160, 3286 },
            { 3163, 3286 }, { 3166, 3287 }, { 3168, 3292 }, { 3168, 3294 },
            { 3168, 3296 }, { 3169, 3301 }, { 3173, 3305 }, { 3173, 3308 },
            { 3180, 3308 }, { 3180, 3304 }, { 3182, 3304 }, { 3187, 3300 },
            { 3185, 3289 }, { 3187, 3285 }, { 3192, 3285 }, { 3193, 3301 },
            { 3194, 3303 }, { 3210, 3303 }, { 3210, 3308 }, { 3192, 3307 },
            { 3185, 3313 }, { 3177, 3314 }, { 3174, 3315 }, { 3168, 3317 },
            { 3161, 3314 }, { 3156, 3314 }, { 3151, 3313 }, { 3147, 3311 },
            { 3147, 3309 }, { 3149, 3308 }, { 3153, 3308 }, { 3156, 3308 },
            { 3159, 3307 }, { 3161, 3304 }, { 3164, 3300 }, { 3165, 3299 },
            { 3165, 3291 }, { 3164, 3290 }, { 3162, 3290 }, { 3157, 3295 },
            { 3154, 3295 }, { 3151, 3298 }, { 3139, 3298 }, { 3132, 3298 },
            { 3125, 3298 }, { 3131, 3291 }, { 3132, 3283 }, { 3132, 3276 },
            { 3131, 3272 }
    };

    // Zone 10 - N/W (N)
    private static final int[][] ZONE10_NW_N = {
            { 3170, 3288 }, { 3168, 3286 }, { 3168, 3284 }, { 3173, 3284 },
            { 3176, 3284 }, { 3181, 3284 }, { 3183, 3282 }, { 3187, 3281 },
            { 3190, 3281 }, { 3193, 3280 }, { 3193, 3278 }, { 3205, 3277 },
            { 3207, 3276 }, { 3209, 3276 }, { 3212, 3274 }, { 3214, 3270 },
            { 3214, 3268 }, { 3215, 3263 }, { 3216, 3260 }, { 3222, 3261 },
            { 3227, 3262 }, { 3230, 3265 }, { 3228, 3270 }, { 3220, 3278 },
            { 3216, 3280 }, { 3215, 3282 }, { 3211, 3282 }, { 3206, 3281 },
            { 3199, 3281 }, { 3196, 3282 }, { 3193, 3284 }, { 3192, 3285 },
            { 3192, 3287 }, { 3192, 3290 }, { 3192, 3299 }, { 3189, 3298 },
            { 3187, 3293 }, { 3186, 3289 }, { 3182, 3288 }, { 3170, 3288 }
    };

    // Zone 11 - Lumbridge East (S)
    private static final int[][] ZONE11_EAST_SOUTH = {
            { 3262, 3208 }, { 3263, 3203 }, { 3264, 3203 }, { 3266, 3204 },
            { 3266, 3223 }, { 3266, 3228 }, { 3266, 3233 }, { 3265, 3238 },
            { 3265, 3241 }, { 3265, 3246 }, { 3262, 3248 }, { 3263, 3252 },
            { 3253, 3254 }, { 3251, 3257 }, { 3251, 3271 }, { 3248, 3276 },
            { 3244, 3278 }, { 3242, 3280 }, { 3238, 3282 }, { 3235, 3279 },
            { 3233, 3278 }, { 3232, 3277 }, { 3234, 3274 }, { 3237, 3271 },
            { 3237, 3268 }, { 3239, 3265 }, { 3240, 3259 }, { 3240, 3255 },
            { 3240, 3252 }, { 3242, 3247 }, { 3242, 3240 }, { 3246, 3233 },
            { 3250, 3230 }, { 3251, 3226 }, { 3258, 3221 }, { 3263, 3216 },
            { 3263, 3208 }, { 3262, 3208 }
    };

    // Zone 12 - Lumbridge East (N)
    private static final int[][] ZONE12_EAST_NORTH = {
            { 3237, 3283 }, { 3241, 3282 }, { 3240, 3285 }, { 3240, 3287 },
            { 3241, 3290 }, { 3242, 3292 }, { 3240, 3293 }, { 3240, 3298 },
            { 3240, 3299 }, { 3241, 3305 }, { 3258, 3322 }, { 3267, 3322 },
            { 3270, 3326 }, { 3271, 3329 }, { 3265, 3330 }, { 3255, 3330 },
            { 3252, 3331 }, { 3250, 3331 }, { 3248, 3334 }, { 3239, 3335 },
            { 3234, 3331 }, { 3234, 3325 }, { 3233, 3323 }, { 3233, 3320 },
            { 3235, 3318 }, { 3235, 3313 }, { 3234, 3312 }, { 3234, 3303 },
            { 3237, 3302 }, { 3236, 3299 }, { 3232, 3294 }, { 3237, 3290 },
            { 3237, 3283 }
    };

    // Zone 13 - Lumbridge East (Farming Patch)
    private static final int[][] ZONE13_EAST_FARMING = {
            { 3210, 3333 }, { 3209, 3338 }, { 3224, 3333 }, { 3227, 3329 },
            { 3232, 3328 }, { 3232, 3319 }, { 3234, 3317 }, { 3234, 3313 },
            { 3233, 3302 }, { 3225, 3302 }, { 3220, 3312 }, { 3222, 3318 },
            { 3215, 3325 }, { 3210, 3333 }
    };

    // Zone 14 - Lumbridge N/W Field
    private static final int[][] ZONE14_NW_FIELD = {
            { 3162, 3345 }, { 3159, 3347 }, { 3155, 3347 }, { 3154, 3345 },
            { 3153, 3342 }, { 3153, 3339 }, { 3154, 3336 }, { 3155, 3334 },
            { 3155, 3331 }, { 3154, 3329 }, { 3154, 3327 }, { 3153, 3325 },
            { 3154, 3322 }, { 3154, 3320 }, { 3155, 3316 }, { 3158, 3315 },
            { 3160, 3316 }, { 3164, 3319 }, { 3167, 3319 }, { 3171, 3318 },
            { 3173, 3317 }, { 3177, 3317 }, { 3184, 3314 }, { 3190, 3311 },
            { 3195, 3310 }, { 3201, 3310 }, { 3205, 3311 }, { 3206, 3313 },
            { 3205, 3320 }, { 3198, 3332 }, { 3196, 3335 }, { 3186, 3339 },
            { 3180, 3343 }, { 3166, 3342 }, { 3162, 3345 }
    };
}

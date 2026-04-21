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


public class WhisperRuinsArea extends RoamingArea
{
    private final int zone;

    private WhisperRuinsArea(int zone) { this.zone = zone; }

    public static WhisperRuinsArea zone1() { return new WhisperRuinsArea(1); }
    public static WhisperRuinsArea zone2() { return new WhisperRuinsArea(2); }
    public static WhisperRuinsArea zone3() { return new WhisperRuinsArea(3); }
    public static WhisperRuinsArea zone4() { return new WhisperRuinsArea(4); }

    @Override
    public String getAreaId() { return "whisper_ruins_zone" + zone; }

    @Override
    public int[] getPetNpcIds() { return new int[]{ NpcID.WISP }; }

    @Override
    public boolean isFlying() { return true; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        Arrays.fill(names, "Wisp");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Wisp</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Not one for small talk. Just small whispers.";
            case 2: return "Haunts the quiet residential streets.";
            case 3: return "Shh. She's concentrating.";
            default: return "Flickers when you're not looking.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 6;
            case 2: return 16;
            case 3: return 16;
            default: return 6;
        }
    }

    @Override
    public int getPlane() { return 0; }

    @Override
    public int getZOffset() { return 30; }

    @Override
    public int getMenuClickRadius() { return 60; }

    @Override
    public int[][] getPolygonPoints()
    {
        switch (zone)
        {
            case 1: return ZONE1_ENTRANCE;
            case 2: return ZONE2_RESIDENTIAL;
            case 3: return ZONE3_SCIENCE;
            default: return ZONE4_MIDDLE;
        }
    }

    // Zone 1 - Entrance (Post-Quest)
    private static final int[][] ZONE1_ENTRANCE = {
            { 2580, 6460 }, { 2587, 6460 }, { 2588, 6456 }, { 2591, 6456 },
            { 2600, 6447 }, { 2600, 6444 }, { 2604, 6444 }, { 2604, 6437 },
            { 2599, 6437 }, { 2599, 6429 }, { 2601, 6426 }, { 2602, 6426 },
            { 2604, 6424 }, { 2600, 6420 }, { 2598, 6422 }, { 2598, 6423 },
            { 2596, 6425 }, { 2588, 6425 }, { 2588, 6420 }, { 2580, 6420 },
            { 2580, 6424 }, { 2576, 6424 }, { 2568, 6432 }, { 2568, 6436 },
            { 2564, 6436 }, { 2564, 6444 }, { 2568, 6444 }, { 2568, 6448 },
            { 2576, 6456 }, { 2580, 6456 }, { 2580, 6460 }
    };

    // Zone 2 - Residential District (Post-Quest)
    private static final int[][] ZONE2_RESIDENTIAL = {
            { 2655, 6458 }, { 2655, 6451 }, { 2650, 6447 }, { 2645, 6447 },
            { 2630, 6449 }, { 2626, 6444 }, { 2626, 6439 }, { 2620, 6433 },
            { 2621, 6430 }, { 2624, 6428 }, { 2623, 6421 }, { 2626, 6419 },
            { 2625, 6414 }, { 2627, 6412 }, { 2629, 6408 }, { 2629, 6400 },
            { 2631, 6398 }, { 2641, 6398 }, { 2643, 6400 }, { 2650, 6400 },
            { 2651, 6404 }, { 2653, 6405 }, { 2660, 6405 }, { 2660, 6411 },
            { 2672, 6411 }, { 2672, 6416 }, { 2669, 6416 }, { 2669, 6424 },
            { 2684, 6423 }, { 2684, 6412 }, { 2691, 6412 }, { 2694, 6416 },
            { 2692, 6418 }, { 2692, 6422 }, { 2699, 6427 }, { 2700, 6428 },
            { 2700, 6431 }, { 2697, 6435 }, { 2696, 6435 }, { 2696, 6428 },
            { 2693, 6425 }, { 2689, 6425 }, { 2687, 6427 }, { 2688, 6437 },
            { 2690, 6445 }, { 2692, 6445 }, { 2690, 6447 }, { 2686, 6448 },
            { 2685, 6448 }, { 2685, 6445 }, { 2685, 6439 }, { 2676, 6439 },
            { 2677, 6428 }, { 2670, 6428 }, { 2668, 6430 }, { 2668, 6445 },
            { 2661, 6445 }, { 2658, 6449 }, { 2658, 6457 }, { 2658, 6458 },
            { 2655, 6458 }
    };

    // Zone 3 - Science District (Post-Quest)
    private static final int[][] ZONE3_SCIENCE = {
            { 2572, 6392 }, { 2574, 6394 }, { 2588, 6394 }, { 2594, 6400 },
            { 2599, 6399 }, { 2602, 6399 }, { 2607, 6397 }, { 2611, 6397 },
            { 2613, 6396 }, { 2620, 6394 }, { 2623, 6394 }, { 2626, 6391 },
            { 2626, 6389 }, { 2623, 6385 }, { 2619, 6382 }, { 2618, 6380 },
            { 2618, 6371 }, { 2622, 6366 }, { 2623, 6365 }, { 2623, 6357 },
            { 2623, 6354 }, { 2619, 6351 }, { 2619, 6345 }, { 2619, 6340 },
            { 2622, 6338 }, { 2619, 6334 }, { 2611, 6334 }, { 2602, 6331 },
            { 2597, 6325 }, { 2601, 6322 }, { 2603, 6320 }, { 2603, 6315 },
            { 2600, 6316 }, { 2599, 6320 }, { 2597, 6321 }, { 2596, 6321 },
            { 2584, 6334 }, { 2589, 6330 }, { 2593, 6330 }, { 2595, 6331 },
            { 2595, 6355 }, { 2592, 6357 }, { 2593, 6366 }, { 2580, 6367 },
            { 2580, 6370 }, { 2592, 6371 }, { 2592, 6389 }, { 2592, 6390 },
            { 2573, 6390 }, { 2572, 6392 }
    };

    // Zone 4 - Middle (Post-Quest)
    private static final int[][] ZONE4_MIDDLE = {
            { 2609, 6427 }, { 2596, 6414 }, { 2596, 6412 }, { 2589, 6404 },
            { 2589, 6404 }, { 2594, 6403 }, { 2595, 6402 }, { 2602, 6402 },
            { 2605, 6399 }, { 2610, 6399 }, { 2611, 6401 }, { 2616, 6401 },
            { 2617, 6400 }, { 2617, 6396 }, { 2626, 6396 }, { 2627, 6399 },
            { 2627, 6406 }, { 2623, 6406 }, { 2622, 6408 }, { 2622, 6412 },
            { 2624, 6418 }, { 2622, 6420 }, { 2622, 6428 }, { 2619, 6430 },
            { 2619, 6426 }, { 2618, 6423 }, { 2615, 6424 }, { 2614, 6428 },
            { 2609, 6427 }
    };
}

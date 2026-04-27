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


public class StranglewoodArea extends RoamingArea
{
    private final int zone;

    private StranglewoodArea(int zone) { this.zone = zone; }

    public static StranglewoodArea zone1() { return new StranglewoodArea(1); }
    public static StranglewoodArea zone2() { return new StranglewoodArea(2); }
    public static StranglewoodArea zone3() { return new StranglewoodArea(3); }
    public static StranglewoodArea zone4() { return new StranglewoodArea(4); }
    public static StranglewoodArea zone5() { return new StranglewoodArea(5); }

    @Override
    public String getAreaId() { return "stranglewood_zone" + zone; }

    @Override
    public int[] getPetNpcIds() { return new int[]{ NpcID.BUTCH }; }

    @Override
    public String[] getSpawnNames()
    {
        int n = getSpawnCount();
        String[] names = new String[n];
        Arrays.fill(names, "Butch");
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        return "<col=ffff00>Butch</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        switch (zone)
        {
            case 1: return "Half the size. Twice the grudge.";
            case 2:
            case 3:
                return "Strangler's little helper.";
            case 4: return "The ritual's little intern.";
            default: return "Axe him no questions.";
        }
    }

    @Override
    public int getSpawnCount()
    {
        switch (zone)
        {
            case 1: return 12;
            case 2: return 24;
            case 3: return 8;
            default: return 6;
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
            case 1: return ZONE1_BOAT_PATH;
            case 2: return ZONE2_MAIN;
            case 3: return ZONE3_WEST_ISLAND;
            case 4: return ZONE4_SE_START;
            default: return ZONE5_VARDOVIS_PATH;
        }
    }

    // Zone 1 - Boat Path
    private static final int[][] ZONE1_BOAT_PATH = {
            { 1151, 3447 }, { 1151, 3448 }, { 1156, 3448 }, { 1158, 3447 },
            { 1159, 3444 }, { 1158, 3443 }, { 1166, 3443 }, { 1168, 3443 },
            { 1169, 3443 }, { 1176, 3443 }, { 1177, 3443 }, { 1178, 3444 },
            { 1183, 3444 }, { 1183, 3446 }, { 1184, 3446 }, { 1184, 3448 },
            { 1181, 3448 }, { 1181, 3449 }, { 1183, 3449 }, { 1184, 3450 },
            { 1185, 3450 }, { 1185, 3446 }, { 1186, 3447 }, { 1187, 3447 },
            { 1187, 3444 }, { 1189, 3444 }, { 1190, 3443 }, { 1190, 3438 },
            { 1189, 3437 }, { 1188, 3438 }, { 1186, 3440 }, { 1164, 3440 },
            { 1158, 3440 }, { 1158, 3441 }, { 1156, 3441 }, { 1156, 3438 },
            { 1152, 3441 }, { 1151, 3442 }, { 1151, 3447 }
    };

    // Zone 2 - Main Area
    private static final int[][] ZONE2_MAIN = {
            { 1159, 3438 }, { 1161, 3438 }, { 1163, 3436 }, { 1163, 3417 },
            { 1186, 3417 }, { 1187, 3418 }, { 1187, 3436 }, { 1188, 3436 },
            { 1189, 3435 }, { 1189, 3425 }, { 1199, 3416 }, { 1188, 3415 },
            { 1188, 3399 }, { 1198, 3399 }, { 1199, 3392 }, { 1186, 3381 },
            { 1174, 3381 }, { 1172, 3379 }, { 1161, 3379 }, { 1159, 3381 },
            { 1155, 3381 }, { 1153, 3379 }, { 1150, 3379 }, { 1148, 3375 },
            { 1142, 3375 }, { 1130, 3388 }, { 1130, 3392 }, { 1136, 3398 },
            { 1140, 3393 }, { 1149, 3393 }, { 1152, 3391 }, { 1161, 3391 },
            { 1161, 3411 }, { 1160, 3413 }, { 1157, 3413 }, { 1157, 3420 },
            { 1159, 3420 }, { 1159, 3438 }
    };

    // Zone 3 - West Island
    private static final int[][] ZONE3_WEST_ISLAND = {
            { 1112, 3377 }, { 1110, 3379 }, { 1109, 3379 }, { 1107, 3377 },
            { 1103, 3374 }, { 1101, 3372 }, { 1100, 3370 }, { 1100, 3368 },
            { 1104, 3368 }, { 1104, 3366 }, { 1104, 3363 }, { 1105, 3360 },
            { 1102, 3360 }, { 1099, 3360 }, { 1097, 3358 }, { 1097, 3356 },
            { 1097, 3355 }, { 1099, 3355 }, { 1100, 3354 }, { 1101, 3352 },
            { 1103, 3352 }, { 1104, 3351 }, { 1106, 3354 }, { 1107, 3354 },
            { 1110, 3354 }, { 1111, 3354 }, { 1112, 3353 }, { 1112, 3352 },
            { 1113, 3350 }, { 1113, 3348 }, { 1112, 3346 }, { 1112, 3346 },
            { 1111, 3344 }, { 1111, 3343 }, { 1113, 3342 }, { 1113, 3339 },
            { 1114, 3339 }, { 1116, 3339 }, { 1117, 3342 }, { 1117, 3342 },
            { 1116, 3345 }, { 1117, 3347 }, { 1118, 3349 }, { 1118, 3352 },
            { 1118, 3355 }, { 1118, 3357 }, { 1118, 3358 }, { 1122, 3360 },
            { 1127, 3362 }, { 1130, 3364 }, { 1129, 3366 }, { 1125, 3366 },
            { 1124, 3368 }, { 1124, 3370 }, { 1122, 3370 }, { 1122, 3372 },
            { 1121, 3373 }, { 1121, 3374 }, { 1121, 3375 }, { 1120, 3377 },
            { 1119, 3378 }, { 1117, 3379 }, { 1113, 3379 }, { 1112, 3377 }
    };

    // Zone 4 - S/E Start
    private static final int[][] ZONE4_SE_START = {
            { 1133, 3346 }, { 1139, 3346 }, { 1151, 3346 }, { 1155, 3352 },
            { 1159, 3357 }, { 1164, 3358 }, { 1171, 3358 }, { 1174, 3353 },
            { 1166, 3349 }, { 1161, 3347 }, { 1156, 3342 }, { 1154, 3339 },
            { 1156, 3335 }, { 1157, 3330 }, { 1156, 3327 }, { 1152, 3326 },
            { 1149, 3326 }, { 1146, 3334 }, { 1142, 3334 }, { 1138, 3337 },
            { 1135, 3340 }, { 1129, 3340 }, { 1133, 3346 }
    };

    // Zone 5 - Vardovis Path
    private static final int[][] ZONE5_VARDOVIS_PATH = {
            { 1105, 3437 }, { 1103, 3435 }, { 1102, 3433 }, { 1102, 3432 },
            { 1105, 3432 }, { 1107, 3430 }, { 1107, 3428 }, { 1107, 3427 },
            { 1108, 3427 }, { 1109, 3429 }, { 1110, 3430 }, { 1110, 3431 },
            { 1113, 3432 }, { 1113, 3434 }, { 1115, 3435 }, { 1117, 3436 },
            { 1118, 3437 }, { 1120, 3438 }, { 1122, 3437 }, { 1124, 3436 },
            { 1126, 3435 }, { 1128, 3436 }, { 1129, 3435 }, { 1131, 3435 },
            { 1133, 3435 }, { 1134, 3436 }, { 1137, 3436 }, { 1140, 3434 },
            { 1143, 3433 }, { 1144, 3435 }, { 1142, 3436 }, { 1141, 3437 },
            { 1140, 3438 }, { 1138, 3439 }, { 1137, 3439 }, { 1135, 3438 },
            { 1134, 3439 }, { 1131, 3439 }, { 1130, 3440 }, { 1128, 3439 },
            { 1125, 3440 }, { 1124, 3439 }, { 1119, 3441 }, { 1117, 3440 },
            { 1116, 3439 }, { 1112, 3438 }, { 1107, 3438 }, { 1105, 3437 }
    };
}

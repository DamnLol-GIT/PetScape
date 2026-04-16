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

package com.DamnLol.PetScape;

import net.runelite.api.Model;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;

// Roaming Area Rules
public abstract class RoamingArea
{
    private Polygon cachedPolygon = null;
    private Polygon[] cachedForbiddenPolygons = null;

    public abstract String getAreaId();
    public abstract int[][] getPolygonPoints();
    public abstract int getSpawnCount();
    public abstract int getPlane();
    public abstract int[] getPetNpcIds();

    public String[] getSpawnNames()
    {
        return new String[0];
    }

    public String getExamineText()
    {
        return "";
    }

    // Right-click menu target
    public String getMenuTarget(int spawnIndex)
    {
        String[] names = getSpawnNames();
        String name = (names != null && spawnIndex < names.length && !names[spawnIndex].isEmpty())
                ? names[spawnIndex] : getAreaId();
        return "<col=ffff00>" + name + "</col>";
    }

    public boolean isStationary()
    {
        return false;
    }

    public int getInitialOrientation()
    {
        return 0;
    }

    public int getCustomIdleAnimId()
    {
        return -1;
    }

    public void tintModel(Model model)
    {
    }

    public boolean isAnimated()
    {
        return false;
    }

    public void animateModel(Model model, int[] base1, int[] base2, int[] base3, int tick)
    {
    }

    public int getMenuClickRadius()
    {
        return 50;
    }

    public int getZOffset()
    {
        int[] ids = getPetNpcIds();
        if (ids == null || ids.length == 0) return 0;
        return PetScapeGhost.Z_OVERRIDES.getOrDefault(ids[0], 0);
    }

    // Forbidden sub-zones within the boundary - tiles inside are excluded from spawn/nav
    public int[][][] getForbiddenZonePoints()
    {
        return new int[0][][];
    }

    // Builds and caches boundary Polygon for fast contains() checks
    public synchronized Polygon getPolygon()
    {
        if (cachedPolygon == null)
        {
            int[][] pts = getPolygonPoints();
            int[] xs = new int[pts.length];
            int[] ys = new int[pts.length];
            for (int i = 0; i < pts.length; i++)
            {
                xs[i] = pts[i][0];
                ys[i] = pts[i][1];
            }
            cachedPolygon = new Polygon(xs, ys, pts.length);
        }
        return cachedPolygon;
    }

    // Builds and caches one Polygon per forbidden zone
    public synchronized Polygon[] getForbiddenPolygons()
    {
        if (cachedForbiddenPolygons == null)
        {
            int[][][] zones = getForbiddenZonePoints();
            cachedForbiddenPolygons = new Polygon[zones.length];
            for (int z = 0; z < zones.length; z++)
            {
                int[][] pts = zones[z];
                int[] xs = new int[pts.length];
                int[] ys = new int[pts.length];
                for (int i = 0; i < pts.length; i++)
                {
                    xs[i] = pts[i][0];
                    ys[i] = pts[i][1];
                }
                cachedForbiddenPolygons[z] = new Polygon(xs, ys, pts.length);
            }
        }
        return cachedForbiddenPolygons;
    }

    // True if point is inside boundary - outside all forbidden zones - on correct plane
    public boolean contains(WorldPoint point)
    {
        if (point == null || point.getPlane() != getPlane()) return false;
        if (!getPolygon().contains(point.getX(), point.getY())) return false;
        for (Polygon forbidden : getForbiddenPolygons())
        {
            if (forbidden.contains(point.getX(), point.getY())) return false;
        }
        return true;
    }

    // Proximity activation checks
    public WorldPoint getCenter()
    {
        int[][] pts = getPolygonPoints();
        long sumX = 0, sumY = 0;
        for (int[] pt : pts) { sumX += pt[0]; sumY += pt[1]; }
        return new WorldPoint((int) (sumX / pts.length), (int) (sumY / pts.length), getPlane());
    }

    // Distance to farthest vertex plus buffer
    public int getApproxRadius()
    {
        WorldPoint center = getCenter();
        int[][] pts = getPolygonPoints();
        int maxDist = 0;
        for (int[] pt : pts)
        {
            int d = Math.max(Math.abs(pt[0] - center.getX()), Math.abs(pt[1] - center.getY()));
            if (d > maxDist) maxDist = d;
        }
        return maxDist + 12;
    }
}
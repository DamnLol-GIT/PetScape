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

// JSON area config - boxed types are optional, null falls back to RoamingArea default
public class AreaConfig
{
    public String id;
    public int plane;
    public int[][] polygon;
    public Form[] forms;
    public ForbiddenZone[] forbiddenZones;

    public Boolean formFixed;
    public Boolean flying;
    public Boolean aquatic;
    public Boolean stationary;
    public Integer wanderMinDist;
    public Integer minSiblingSeparation;
    public Integer menuClickRadius;
    public Integer zOffset;
    public Integer customIdleAnimId;
    public Integer initialOrientation;

    public static class ForbiddenZone
    {
        public String comment;
        public int[][] polygon;
    }

    public static class Form
    {
        public String name;
        public String displayName;
        public String examine;
        public int count;
        public int[] npcIds;
    }

    // Throws on missing or malformed required fields
    public void validate(String src)
    {
        String p = "AreaConfig (" + src + "): ";
        if (id == null || id.isEmpty()) throw new IllegalStateException(p + "missing 'id'");
        if (polygon == null || polygon.length < 3) throw new IllegalStateException(p + "polygon needs >= 3 points");
        for (int i = 0; i < polygon.length; i++)
        {
            if (polygon[i] == null || polygon[i].length != 2) throw new IllegalStateException(p + "polygon[" + i + "] not [x, y]");
        }
        if (forms == null || forms.length == 0) throw new IllegalStateException(p + "forms is empty");

        for (int i = 0; i < forms.length; i++)
        {
            Form f = forms[i];
            String fp = p + "forms[" + i + "]: ";
            if (f == null) throw new IllegalStateException(fp + "null");
            if (f.name == null || f.name.isEmpty()) throw new IllegalStateException(fp + "missing 'name'");
            if (f.count <= 0) throw new IllegalStateException(fp + "count " + f.count + " <= 0");
            if (f.npcIds == null || f.npcIds.length == 0) throw new IllegalStateException(fp + "npcIds empty");
        }

        if (forbiddenZones == null) return;
        for (int i = 0; i < forbiddenZones.length; i++)
        {
            ForbiddenZone fz = forbiddenZones[i];
            String fp = p + "forbiddenZones[" + i + "]: ";
            if (fz == null) throw new IllegalStateException(fp + "null");
            if (fz.polygon == null || fz.polygon.length < 3) throw new IllegalStateException(fp + "polygon needs >= 3 points");
            for (int j = 0; j < fz.polygon.length; j++)
            {
                if (fz.polygon[j] == null || fz.polygon[j].length != 2) throw new IllegalStateException(fp + "polygon[" + j + "] not [x, y]");
            }
        }
    }
}
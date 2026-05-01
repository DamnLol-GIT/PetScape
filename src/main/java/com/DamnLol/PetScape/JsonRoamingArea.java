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

import net.runelite.api.NpcID;

import java.lang.reflect.Field;

// RoamingArea backed by JSON config - flattens forms[].npcIds into the legacy contract
public class JsonRoamingArea extends RoamingArea
{
    private final AreaConfig config;

    // getPetNpcIds() result - concat of every form's npcIds in declared order
    private final int[] flatNpcIds;

    // spawnIndex -> flat NPC index, precomputed
    private final int[] spawnToFlatForm;

    // flat NPC index -> logical form index in config.forms, for menu/examine lookups
    private final int[] flatToLogicalForm;

    private final int totalSpawnCount;

    public JsonRoamingArea(AreaConfig config)
    {
        this.config = config;

        int totalNpcCount = 0;
        for (AreaConfig.Form f : config.forms) totalNpcCount += f.npcIds.length;

        this.flatNpcIds = new int[totalNpcCount];
        this.flatToLogicalForm = new int[totalNpcCount];
        int flatIdx = 0;
        for (int formIdx = 0; formIdx < config.forms.length; formIdx++)
        {
            AreaConfig.Form form = config.forms[formIdx];
            for (String name : form.npcIds)
            {
                flatNpcIds[flatIdx] = resolveNpcId(name, config.id);
                flatToLogicalForm[flatIdx] = formIdx;
                flatIdx++;
            }
        }

        int totalCount = 0;
        for (AreaConfig.Form f : config.forms) totalCount += f.count;
        this.totalSpawnCount = totalCount;

        // Each form occupies a contiguous block of spawn indices - within that block
        // spawns cycle through the form's npcIds via (i % npcIds.length)
        this.spawnToFlatForm = new int[totalCount];
        int spawnIdx = 0;
        int flatOffset = 0;
        for (AreaConfig.Form form : config.forms)
        {
            for (int i = 0; i < form.count; i++)
            {
                spawnToFlatForm[spawnIdx++] = flatOffset + (i % form.npcIds.length);
            }
            flatOffset += form.npcIds.length;
        }
    }

    // Reflection lookup against NpcID - (Change to gameval in future)
    private static int resolveNpcId(String name, String areaId)
    {
        try
        {
            return NpcID.class.getField(name).getInt(null);
        }
        catch (NoSuchFieldException e)
        {
            throw new IllegalStateException("Area '" + areaId + "': unknown NpcID '" + name + "'");
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalStateException("Area '" + areaId + "': can't access NpcID '" + name + "'", e);
        }
    }

    private AreaConfig.Form formAt(int flatFormIdx) { return config.forms[flatToLogicalForm[flatFormIdx]]; }

    @Override
    public String getAreaId() { return config.id; }

    @Override
    public int[] getPetNpcIds() { return flatNpcIds; }

    @Override
    public int[][] getPolygonPoints() { return config.polygon; }

    @Override
    public int getSpawnCount() { return totalSpawnCount; }

    @Override
    public int getPlane() { return config.plane; }

    @Override
    public int[][][] getForbiddenZonePoints()
    {
        if (config.forbiddenZones == null || config.forbiddenZones.length == 0) return new int[0][][];
        int[][][] out = new int[config.forbiddenZones.length][][];
        for (int i = 0; i < config.forbiddenZones.length; i++) out[i] = config.forbiddenZones[i].polygon;
        return out;
    }

    @Override
    public int getFormAssignment(int spawnIndex, int nForms) { return spawnToFlatForm[spawnIndex]; }

    @Override
    public String[] getSpawnNames()
    {
        String[] names = new String[totalSpawnCount];
        int idx = 0;
        for (AreaConfig.Form form : config.forms)
        {
            for (int i = 0; i < form.count; i++) names[idx++] = form.name;
        }
        return names;
    }

    @Override
    public String getMenuTarget(int spawnIndex, int formIndex)
    {
        AreaConfig.Form form = formAt(formIndex);
        String label = (form.displayName != null && !form.displayName.isEmpty()) ? form.displayName : form.name;
        return "<col=ffff00>" + label + "</col>";
    }

    @Override
    public String getExamineText(int spawnIndex, int formIndex)
    {
        String e = formAt(formIndex).examine;
        return e != null ? e : "";
    }

    @Override
    public boolean isFormFixed() { return config.formFixed != null ? config.formFixed : super.isFormFixed(); }

    @Override
    public boolean isFlying() { return config.flying != null ? config.flying : super.isFlying(); }

    @Override
    public boolean isAquatic() { return config.aquatic != null ? config.aquatic : super.isAquatic(); }

    @Override
    public boolean isStationary() { return config.stationary != null ? config.stationary : super.isStationary(); }

    @Override
    public int getWanderMinDist() { return config.wanderMinDist != null ? config.wanderMinDist : super.getWanderMinDist(); }

    @Override
    public int getMinSiblingSeparation() { return config.minSiblingSeparation != null ? config.minSiblingSeparation : super.getMinSiblingSeparation(); }

    @Override
    public int getMenuClickRadius() { return config.menuClickRadius != null ? config.menuClickRadius : super.getMenuClickRadius(); }

    @Override
    public int getZOffset() { return config.zOffset != null ? config.zOffset : super.getZOffset(); }

    @Override
    public int getCustomIdleAnimId() { return config.customIdleAnimId != null ? config.customIdleAnimId : super.getCustomIdleAnimId(); }

    @Override
    public int getInitialOrientation() { return config.initialOrientation != null ? config.initialOrientation : super.getInitialOrientation(); }
}
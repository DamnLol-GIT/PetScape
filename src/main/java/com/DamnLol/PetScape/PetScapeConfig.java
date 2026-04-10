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

import lombok.Getter;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("petscape")
public interface PetScapeConfig extends Config
{
    enum CloneCount
    {
        X2("x2", 1),
        X3("x3", 2),
        X5("x5", 4),
        X10("x10", 9);

        private final String label;
        @Getter
        private final int extraClones;

        CloneCount(String label, int extraClones) { this.label = label; this.extraClones = extraClones; }

        @Override
        public String toString() { return label; }
    }

    @ConfigSection(
            name = "General Settings",
            description = "General Settings",
            position = 0
    )
    String generalSection = "general";

    @ConfigItem(
            keyName = "cloneCount",
            name = "Pet Amount",
            description = "Total Pet Amount (Includes Original)",
            section = generalSection,
            position = 0
    )
    default CloneCount cloneCount() { return CloneCount.X2; }

    @ConfigItem(
            keyName = "swapRealPetWalkHere",
            name = "'Walk Here' NPCs",
            description = "Makes 'Walk here' default on left click for original pets",
            section = generalSection,
            position = 1
    )
    default boolean swapRealPetWalkHere() { return false; }

    @ConfigItem(
            keyName = "allowOtherPoh",
            name = "Active in other PoH",
            description = "Multiply pets in other players houses",
            section = generalSection,
            position = 2
    )
    default boolean allowOtherPoh() { return true; }
}

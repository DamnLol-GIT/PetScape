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

import net.runelite.api.gameval.NpcID;
import java.util.*;


// Recolor format: short[] — first half = find, second half = replace.

public class PetGhostData {
    public static final class Entry {
        public final int[] modelIds;
        public final int idleAnim;
        public final int walkAnim;
        public final int scale;
        public final short[] recolors;

        Entry(int[] modelIds, int idleAnim, int walkAnim, int scale, short... recolors) {
            this.modelIds = modelIds;
            this.idleAnim = idleAnim;
            this.walkAnim = walkAnim;
            this.scale = scale;
            this.recolors = (recolors.length == 0) ? null : recolors;
        }

        Entry(int[] modelIds, int idleAnim, int walkAnim, int scale) {
            this(modelIds, idleAnim, walkAnim, scale, new short[0]);
        }
    }

    public static final Map<Integer, Entry> LOOKUP;

    static {
        Map<Integer, Entry> m = new HashMap<>();
        m.put(NpcID.POH_ABYSSALSIRE_PET, new Entry(new int[]{29631}, 7125, 7124, -1));
        m.put(NpcID.POH_ABYSSAL_PET, new Entry(new int[]{44070}, 2185, 2184, 80));
        m.put(NpcID.POH_WARDEN_PET_AKKHA, new Entry(new int[]{46360, 46356, 46357}, 9760, 9421, 58));
        m.put(NpcID.XMAS24_YORKIE_FINAL, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_02, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_03, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_04, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_05, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_06, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_EASTER26_EGG_07, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.POH_WARDEN_PET_BABA, new Entry(new int[]{46352, 46350}, 9741, 9739, 36));
        m.put(NpcID.POH_SKILLPET_HUNTER_RED, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)3988, (short)3988, (short)3982, (short)3986, (short)5014, (short)3988));
        m.put(NpcID.SKILLPET_HUNTER_RED, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)3988, (short)3988, (short)3982, (short)3986, (short)5014, (short)3988));
        m.put(NpcID.SKILLPET_HUNTER_BLACK, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)20, (short)33, (short)12, (short)37, (short)45, (short)49));
        m.put(NpcID.SKILLPET_HUNTER_GOLD, new Entry(new int[]{29757}, 5182, 5181, -1));
        m.put(NpcID.MOLE_BABY_01, new Entry(new int[]{42012}, 3309, 3313, 90));
        m.put(NpcID.POH_MOLE_PET_NAKED, new Entry(new int[]{42012}, 3309, 3313, 90,
                (short)5388, (short)5392, (short)5268, (short)5272, (short)5276, (short)4558,
                (short)317, (short)322, (short)326, (short)328, (short)332, (short)63706));
        m.put(NpcID.POH_MOLE_PET, new Entry(new int[]{42012}, 3309, 3313, 90));
        m.put(NpcID.POH_DUKE_SUCELLUS_PET, new Entry(new int[]{49195}, 10217, 10218, -1,
                (short)937, (short)790, (short)33988, (short)39207));
        m.put(NpcID.POH_SKILLPETWC, new Entry(new int[]{29754}, 7177, 7178, -1));
        m.put(NpcID.COWBOSS_PET, new Entry(new int[]{60118}, 5852, 5856, 64));
        m.put(NpcID.POH_COWBOSS_PET, new Entry(new int[]{60118}, 5852, 5856, 64));
        m.put(NpcID.POH_BLOODHOUNDPET, new Entry(new int[]{31740}, 7269, 7280, -1));
        m.put(NpcID.BLOODHOUNDPET, new Entry(new int[]{31740}, 7269, 7280, -1));
        m.put(NpcID.POH_SKILLPET_AGILITY_BONE, new Entry(new int[]{54969}, 11662, 11663, 110));
        m.put(NpcID.POH_RTBRANDA_PET, new Entry(new int[]{55951}, 11970, 11972, 26));
        m.put(NpcID.WGS_BROAV, new Entry(new int[]{53343}, 11232, 11234, 120));
        m.put(NpcID.POH_BROAV, new Entry(new int[]{53343}, 11232, 11234, 120));
        m.put(NpcID.POH_VARDORVIS_PET, new Entry(new int[]{49298}, 10337, 10339, 60));
        m.put(NpcID.POH_CALLISTO_PET, new Entry(new int[]{47396}, 10011, 10010, 16));
        m.put(NpcID.POH_CALLISTO_PET_LEGACY, new Entry(new int[]{28298}, 4919, 4923, 35));
        m.put(NpcID.CALLISTOPET_LEGACY, new Entry(new int[]{28298}, 4919, 4923, 35));
        m.put(NpcID.CALLISTOPET, new Entry(new int[]{47396}, 10011, 10010, 16));
        m.put(NpcID.POH_GROWNCAT_BLACK, new Entry(new int[]{3010, 3006}, 317, 314, 60,
                (short)61, (short)16, (short)127, (short)12, (short)12, (short)33));
        m.put(NpcID.CHAOS_ELEMENTAL_PET, new Entry(new int[]{28256}, 3144, 3145, -1));
        m.put(NpcID.POH_CHAOS_ELEMENTAL_PET, new Entry(new int[]{28256}, 3144, 3145, -1));
        m.put(NpcID.POH_CHOMPYBIRD_PET, new Entry(new int[]{26861}, 6764, 6765, 83));
        m.put(NpcID.POH_CORPPET, new Entry(new int[]{11056}, 1678, 7974, 64));
        m.put(NpcID.CORP_PET, new Entry(new int[]{11056}, 1678, 7974, 64));
        m.put(NpcID.POH_GAUNTLET_PET_CORRUPT, new Entry(new int[]{38597}, 8417, 8428, 25));
        m.put(NpcID.GAUNTLET_PET_CORRUPT, new Entry(new int[]{38597}, 8417, 8428, 25));
        m.put(NpcID.POH_PRIME_PET, new Entry(new int[]{9940, 9943, 9942}, 2850, 2849, 60,
                (short)11930, (short)27144, (short)16536, (short)16540,
                (short)5931, (short)1688, (short)21530, (short)21534));
        m.put(NpcID.REX_PET, new Entry(new int[]{9941}, 2850, 2849, 60,
                (short)16536, (short)16540, (short)27144, (short)2477,
                (short)7322, (short)7326, (short)10403, (short)2595));
        m.put(NpcID.POH_SUPREME_PET, new Entry(new int[]{9941, 9943}, 2850, 2849, 60));
        m.put(NpcID.SKILLPET_AGILITY_DARK, new Entry(new int[]{32206}, 7309, 7310, 110,
                (short)3816, (short)381, (short)56, (short)3633, (short)329, (short)362,
                (short)24, (short)12, (short)6, (short)6, (short)824, (short)67312));
        m.put(NpcID.POH_SKILLPET_AGILITY_DARK, new Entry(new int[]{32206}, 7309, 7310, 110,
                (short)38160, (short)38156, (short)36333, (short)29036, (short)2344, (short)30,
                (short)241, (short)266, (short)82, (short)4, (short)67, (short)312));
        m.put(NpcID.POH_DOM_PET, new Entry(new int[]{56456}, 12401, 12402, -1));
        m.put(NpcID.DOM_PET, new Entry(new int[]{56456}, 12401, 12402, -1));
        m.put(NpcID.POH_WARDEN_PET_ELIDINIS_DESTROYED, new Entry(new int[]{46332}, 9420, 9420, 65));
        m.put(NpcID.POH_WARDEN_PET_ELIDINIS, new Entry(new int[]{46332}, 9656, 9652, 65));
        m.put(NpcID.POH_VESPULA_FLYING_PET, new Entry(new int[]{32689}, 8639, 8639, 20));
        m.put(NpcID.POH_SKILLPET_WC_FOX, new Entry(new int[]{49832}, 6561, 6560, -1));
        m.put(NpcID.BANDOS_PET, new Entry(new int[]{27660, 27665}, 7017, 7016, 30));
        m.put(NpcID.POH_SKILLPET_AGILITY, new Entry(new int[]{32206}, 7309, 7310, 110));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_GOTR, new Entry(new int[]{44061}, 9379, 9378, 32));
        m.put(NpcID.POH_SKILLPET_FISH_TEMPOROSS, new Entry(new int[]{41628}, 6772, 6774, -1));
        m.put(NpcID.SEABIRD1, new Entry(new int[]{59403}, 12586, 12587, 72));
        m.put(NpcID.POH_GRYPHONBOSS_PET_ADULT, new Entry(new int[]{59398}, 12548, 12550, 30));
        m.put(NpcID.GRYPHONBOSS_PET, new Entry(new int[]{59398}, 12548, 12550, 30));
        m.put(NpcID.POH_GRYPHONBOSS_PET, new Entry(new int[]{59403}, 12586, 12587, 72));
        m.put(NpcID.GROWNCAT_HELL, new Entry(new int[]{13409, 13405}, 317, 314, 60, (short)16, (short)914));
        m.put(NpcID.POH_GROWNCAT_HELL, new Entry(new int[]{13409, 13405}, 317, 314, 60, (short)16, (short)914));
        m.put(NpcID.KITTENPET_HELL, new Entry(new int[]{13406, 13410}, 317, 2662, 40, (short)16, (short)35, (short)914, (short)914));
        m.put(NpcID.POH_HELLPET, new Entry(new int[]{29240}, 6561, 6560, -1));
        m.put(NpcID.POH_HERBIBOAR_PET, new Entry(new int[]{33890}, 7694, 7695, 110,
                (short)19992, (short)20364, (short)19988, (short)20422,
                (short)6049, (short)6040, (short)4781, (short)4038));
        m.put(NpcID.POH_SKILLPET_FISH, new Entry(new int[]{29756}, 6772, 6774, -1));
        m.put(NpcID.POH_HUEY_PET, new Entry(new int[]{54453}, 11732, 11733, 100));
        m.put(NpcID.HYDRA_PET, new Entry(new int[]{36185}, 8233, 8296, 20));
        m.put(NpcID.HYDRA_PET_ELECTRIC, new Entry(new int[]{36192}, 8298, 8297, 20));
        m.put(NpcID.HYDRA_PET_FIRE, new Entry(new int[]{36188}, 8247, 8299, 20));
        m.put(NpcID.HYDRA_PET_EXTINGUISHED, new Entry(new int[]{36191}, 8254, 8300, 20));
        m.put(NpcID.POH_HYDRA_PET, new Entry(new int[]{36185}, 8233, 8296, 20));
        m.put(NpcID.POH_HYDRA_PET_ELECTRIC, new Entry(new int[]{36192}, 8298, 8297, 20));
        m.put(NpcID.POH_HYDRA_PET_FIRE, new Entry(new int[]{36188}, 8247, 8299, 20));
        m.put(NpcID.POH_HYDRA_PET_EXTINGUISHED, new Entry(new int[]{36191}, 8254, 8300, 20));
        m.put(NpcID.POH_INFERNO_PET, new Entry(new int[]{33005}, 7573, 7572, -1));
        m.put(NpcID.POH_JADPET_INFERNO, new Entry(new int[]{33012}, 7589, 8857, 20));
        m.put(NpcID.KQ_PET_FLYING, new Entry(new int[]{24597, 24598}, 6239, 6238, 45));
        m.put(NpcID.KQ_PET_WALKING, new Entry(new int[]{24602, 24605, 24606}, 6236, 6236, 45));
        m.put(NpcID.POH_KQ_PET_FLYING, new Entry(new int[]{24602, 24605, 24606}, 6236, 6236, 45));
        m.put(NpcID.POH_KQ_PET_WALKING, new Entry(new int[]{24597, 24598}, 6239, 6238, 45));
        m.put(NpcID.POH_WARDEN_PET_KEPHRI, new Entry(new int[]{46417}, 9572, 9419, 38));
        m.put(NpcID.SLAYER_KRAKEN_BOSS, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.KRAKEN_PET, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.POH_KRAKEN_PET, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.ARMADYL_PET, new Entry(new int[]{28019, 28021, 28020}, 7166, 7167, 30));
        m.put(NpcID.POH_ARMADYL_PET, new Entry(new int[]{28019, 28021, 28020}, 7166, 7167, 30));
        m.put(NpcID.ZAMORAK_PET, new Entry(new int[]{27683, 27681, 27692, 27682, 27690}, 6935, 4070, 20));
        m.put(NpcID.POH_ZAMORAK_PET, new Entry(new int[]{27683, 27681, 27692, 27682, 27690}, 6935, 4070, 20));
        m.put(NpcID.LAZYCAT_HELL, new Entry(new int[]{13411, 13407}, 317, 314, 70, (short)16, (short)914));
        m.put(NpcID.POH_LAZYCAT_HELL, new Entry(new int[]{13411, 13407}, 317, 314, 70, (short)16, (short)914));
        m.put(NpcID.POH_LEVIATHAN_PET, new Entry(new int[]{49285, 49284}, 10277, 10292, 20));
        m.put(NpcID.POH_VERZIK_PET_BLOAT, new Entry(new int[]{35404}, 8080, 9031, 25));
        m.put(NpcID.POH_SOULWARS_PET_BLUE, new Entry(new int[]{41240}, 8842, 8846, 24));
        m.put(NpcID.POH_SOULWARS_PET_RED, new Entry(new int[]{41242}, 3079, 8847, 24));
        m.put(NpcID.POH_VERZIK_PET_MAIDEN, new Entry(new int[]{42280}, 8090, 8090, 30));
        m.put(NpcID.POH_VERZIK_PET_NYLOCAS, new Entry(new int[]{35183}, 8002, 8003, 35));
        m.put(NpcID.POH_VERZIK_PET_SOTETSEG, new Entry(new int[]{35403}, 8137, 9032, 30));
        m.put(NpcID.POH_VERZIK_PET_XARPUS, new Entry(new int[]{35383}, 9033, 9033, 20));
        m.put(NpcID.POH_VERZIK_PET, new Entry(new int[]{35381}, 8120, 8122, 20));
        m.put(NpcID.POH_NIGHTMARE_PET, new Entry(new int[]{39196}, 8593, 8634, 30));
        m.put(NpcID.POH_NIGHTMARE_PET_PARASITE, new Entry(new int[]{39210}, 8553, 8553, 40));
        m.put(NpcID.POH_FISHBOWL_MAYOR_OF_CATHERBY, new Entry(new int[]{9811}, -1, -1, -1,
                (short)-23598, (short)-27727, (short)-30142,
                (short)-23206, (short)10169, (short)10950));
        m.put(NpcID.POH_DUSK_PET, new Entry(new int[]{34187}, 7807, 7806, 34));
        m.put(NpcID.POH_AMOXLIATL_PET, new Entry(new int[]{54438, 54437}, 11528, 11529, 32));
        m.put(NpcID.POH_MUSPAH_PET, new Entry(new int[]{47149}, 9913, 9915, 25));
        m.put(NpcID.POH_MUSPAH_PET_MELEE, new Entry(new int[]{47147}, 9913, 9915, 25));
        m.put(NpcID.POH_MUSPAH_PET_SHIELDED, new Entry(new int[]{47156}, 9913, 9915, 25));
        m.put(NpcID.MUSPAH_PET, new Entry(new int[]{47149}, 9913, 9915, 25));
        m.put(NpcID.MUSPAH_PET_MELEE, new Entry(new int[]{47147}, 9913, 9915, 25));
        m.put(NpcID.MUSPAH_PET_SHIELDED, new Entry(new int[]{47156}, 9913, 9915, 25));
        m.put(NpcID.POH_NEX_PET, new Entry(new int[]{43209}, 9177, 9176, 48));
        m.put(NpcID.POH_ARAXXOR_PET, new Entry(new int[]{54289}, 11473, 11474, 25));
        m.put(NpcID.ARAXXOR_PET, new Entry(new int[]{54289}, 11473, 11474, 25));
        m.put(NpcID.POH_DAWN_PET, new Entry(new int[]{34183}, 7768, 7768, 34));
        m.put(NpcID.POH_OLM_PET, new Entry(new int[]{32697}, 7396, 7395, 64));
        m.put(NpcID.OVERGROWNCAT_HELL, new Entry(new int[]{13409, 13405}, 317, 314, 80, (short)16, (short)914));
        m.put(NpcID.POH_OVERGROWNCAT_HELL, new Entry(new int[]{13409, 13405}, 317, 314, 80, (short)16, (short)914));
        m.put(NpcID.POH_PENANCE_PET, new Entry(
                new int[]{20717, 20715, 20714, 20709, 20713, 20712, 20711, 20710, 20716}, 5410, 5409, 29));
        m.put(NpcID.MACRO_PHEASANT_MODEL_1, new Entry(new int[]{49907}, 2370, 2369, 30));
        m.put(NpcID.POH_SKILLPET_WC_PHEASANT, new Entry(new int[]{49907}, 2370, 2369, 30));
        m.put(NpcID.POH_PHOENIX_PET_GREEN, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.POH_PHOENIX_PET_BLUE, new Entry(new int[]{39146}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)43943, (short)41907, (short)39855, (short)38715, (short)39855, (short)38860));
        m.put(NpcID.POH_PHOENIX_PET_WHITE, new Entry(new int[]{39149}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)20, (short)33, (short)49, (short)20, (short)74, (short)86));
        m.put(NpcID.POH_PHOENIX_PET_PURPLE, new Entry(new int[]{39147}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)48819, (short)48844, (short)48844, (short)50006, (short)48844, (short)50904));
        m.put(NpcID.PHOENIX_PET_GREEN, new Entry(new int[]{39148}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_PET_BLUE, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_PET_WHITE, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_PET_PURPLE, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.POH_PHOENIX_PET, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_PET, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.KBD_PET, new Entry(new int[]{17414, 17415, 17429, 17422, 17423}, 90, 4635, 40));
        m.put(NpcID.POH_KBD_PET, new Entry(new int[]{17414, 17415, 17429, 17422, 17423}, 90, 4635, 40));
        m.put(NpcID.POH_DOGADILE_PET, new Entry(new int[]{32681}, 7417, 7982, 45));
        m.put(NpcID.POH_QUETZAL_PET, new Entry(new int[]{52601}, 10952, 10952, -1));
        m.put(NpcID.POH_ARAXXOR_PET_CUTE, new Entry(new int[]{44936}, 8340, 9139, 64));
        m.put(NpcID.ARAXXOR_PET_CUTE, new Entry(new int[]{44936}, 8340, 9139, 64));
        m.put(NpcID.POH_SKILLPET_THIEVING_PANDA, new Entry(new int[]{37361}, 7315, 7316, 110));
        m.put(NpcID.POH_RTELDRIC_PET, new Entry(new int[]{55938}, 11969, 11971, 26));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_FIRE, new Entry(new int[]{32204}, 7307, 7306, -1));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_AIR, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)103, (short)127, (short)74));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_MIND, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)0, (short)5056, (short)5551));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_WATER, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)39849, (short)38866, (short)38086));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_EARTH, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)6036, (short)6942, (short)6319));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_BODY, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43961, (short)0, (short)43313));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_COSMIC, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)10469, (short)11200, (short)10425));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_CHAOS, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)7104, (short)127, (short)5551));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_NATURE, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)22430, (short)22461, (short)21698));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_LAW, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43945, (short)43968, (short)43313));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_DEATH, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)10469, (short)11200, (short)10425));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_SOUL, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43484, (short)46040, (short)45361));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_ASTRAL, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)54503, (short)54742, (short)54449));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_BLOOD, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)920, (short)910, (short)57));
        m.put(NpcID.POH_SKILLPET_RUNECRAFTING_WRATH, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)4, (short)962, (short)43059));
        m.put(NpcID.POH_SKILLPET_THIEVING, new Entry(new int[]{32203}, 7315, 7316, 110));
        m.put(NpcID.POH_SKILLPET_MINING_AMETHYST, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.POH_SKILLPET_MINING_DEFAULT, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)66, (short)61, (short)49, (short)53, (short)53, (short)49));
        m.put(NpcID.POH_SKILLPET_MINING_TIN, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)4044, (short)3912, (short)3906, (short)3901, (short)3897, (short)4021));
        m.put(NpcID.POH_SKILLPET_MINING_COPPER, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)2337, (short)2332, (short)2328, (short)2452, (short)3346, (short)3470));
        m.put(NpcID.POH_SKILLPET_MINING_IRON, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43235, (short)43233, (short)43486, (short)43482, (short)43862, (short)43730));
        m.put(NpcID.POH_SKILLPET_MINING_BLURITE, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43105, (short)43100, (short)43096, (short)43092, (short)43088, (short)43084));
        m.put(NpcID.POH_SKILLPET_MINING_SILVER, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)10388, (short)10512, (short)10508, (short)10378, (short)8, (short)4));
        m.put(NpcID.POH_SKILLPET_MINING_COAL, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)8134, (short)8128, (short)7104, (short)7101, (short)7099, (short)7097));
        m.put(NpcID.POH_SKILLPET_MINING_GOLD, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43181, (short)43177, (short)43173, (short)43297, (short)43292, (short)43288));
        m.put(NpcID.POH_SKILLPET_MINING_MITHRIL, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)4550, (short)4548, (short)4544, (short)4539, (short)4535, (short)2487));
        m.put(NpcID.POH_SKILLPET_MINING_GRANITE, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)21675, (short)21671, (short)21667, (short)21662, (short)21658, (short)21782));
        m.put(NpcID.POH_SKILLPET_MINING_ADAMANTITE, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)36145, (short)36141, (short)36137, (short)36133, (short)36257, (short)36252));
        m.put(NpcID.POH_SKILLPET_MINING_RUNITE, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)63830, (short)62800, (short)63692, (short)60624, (short)63675, (short)63663));
        m.put(NpcID.SKILLPET_MINING_DEFAULT, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)805, (short)929, (short)798, (short)796, (short)790, (short)912));
        m.put(NpcID.SKILLPET_MINING_TIN, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)50999, (short)50995, (short)50993, (short)49967, (short)50087, (short)51098));
        m.put(NpcID.SKILLPET_MINING_COPPER, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)31531, (short)31655, (short)31651, (short)31518, (short)31640, (short)30612));
        m.put(NpcID.POH_SKILLPET_MINING_ELEMENTAL, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.POH_SKILLPET_MINING_DAEYALT, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.SKILLPET_MINING_ELEMENTAL, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.SKILLPET_MINING_DAEYALT, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.POH_SCORPIA_PET, new Entry(new int[]{29193}, 6258, 6257, 280,
                (short)142, (short)4525, (short)4636, (short)4884, (short)4645,
                (short)28, (short)16, (short)16, (short)16, (short)16));
        m.put(NpcID.POH_SCURRIUS_PET, new Entry(new int[]{50146}, 10687, 10715, 28));
        m.put(NpcID.POH_SKOTIZO_PET, new Entry(new int[]{31653}, 6935, 4070, 20));
        m.put(NpcID.SKOTIZO_PET, new Entry(new int[]{31653}, 6935, 4070, 20));
        m.put(NpcID.POH_SMOKE_PET, new Entry(new int[]{36012}, 1829, 1828, 35));
        m.put(NpcID.POH_SMOKE_PET_OLD, new Entry(new int[]{28442}, 1829, 1828, 60));
        m.put(NpcID.POH_ZALCANO_PET, new Entry(new int[]{38592}, 8429, 8447, 30));
        m.put(NpcID.ZALCANO_PET, new Entry(new int[]{38592}, 8429, 8447, 30));
        m.put(NpcID.POH_SOLHEREDIT_PET, new Entry(new int[]{52580, 52582, 52578}, 10874, 10880, 50));
        m.put(NpcID.POH_SNAKE_PET_GREEN, new Entry(new int[]{10413}, 1721, 2405, -1));
        m.put(NpcID.POH_SNAKE_PET_ORANGE, new Entry(new int[]{10416}, 1721, 2405, -1));
        m.put(NpcID.POH_SNAKE_PET_BLUE, new Entry(new int[]{10414}, 1721, 2405, -1));
        m.put(NpcID.SKILLPET_SAILING, new Entry(new int[]{59482}, 13498, 13499, 100));
        m.put(NpcID.POH_SKILLPET_SAILING, new Entry(new int[]{59482}, 13498, 13499, 100));
        m.put(NpcID.POH_HW_CHAIR, new Entry(new int[]{11470}, 3220, 3220, -1, (short)4886, (short)5908, (short)8070, (short)8844));
        m.put(NpcID.POH_MENAGERIE_SARACHNISPET, new Entry(new int[]{37292}, 8320, 8319, 48));
        m.put(NpcID.POH_MENAGERIE_SARACHNISPET_ORANGE, new Entry(new int[]{37291}, 8320, 8319, 48,
                (short)229, (short)348, (short)412, (short)555, (short)670,
                (short)36069, (short)35041, (short)3505, (short)5945, (short)4007));
        m.put(NpcID.POH_MENAGERIE_SARACHNISPET_BLUE, new Entry(new int[]{37290}, 8320, 8319, 48,
                (short)229, (short)348, (short)412, (short)555, (short)670,
                (short)36069, (short)35041, (short)39219, (short)39611, (short)39719));
        m.put(NpcID.POH_SKILLPET_FARMING, new Entry(new int[]{32202}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING, new Entry(new int[]{32202}, 7312, 7313, -1));
        m.put(NpcID.POH_SKILLPET_FARMING_CRYSTAL, new Entry(new int[]{39573}, 7312, 7313, -1));
        m.put(NpcID.POH_SKILLPET_FARMING_DRAGON, new Entry(new int[]{39571}, 7312, 7313, -1));
        m.put(NpcID.POH_SKILLPET_FARMING_HERB, new Entry(new int[]{39572}, 7312, 7313, -1));
        m.put(NpcID.POH_SKILLPET_FARMING_LILY, new Entry(new int[]{39574}, 7312, 7313, -1));
        m.put(NpcID.POH_SKILLPET_FARMING_REDWOOD, new Entry(new int[]{39575}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING_CRYSTAL, new Entry(new int[]{39573}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING_DRAGON, new Entry(new int[]{39571}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING_HERB, new Entry(new int[]{39572}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING_LILY, new Entry(new int[]{39574}, 7312, 7313, -1));
        m.put(NpcID.SKILLPET_FARMING_REDWOOD, new Entry(new int[]{39575}, 7312, 7313, -1));
        m.put(NpcID.POH_TEKTON_PET, new Entry(new int[]{32682}, 7476, 7477, 25));
        m.put(NpcID.POH_TEMPOROSS_PET, new Entry(new int[]{41812}, 8895, 8895, 24));
        m.put(NpcID.POH_WARDEN_PET_TUMEKEN_DESTROYED, new Entry(new int[]{46333}, 9420, 9420, 65));
        m.put(NpcID.POH_WARDEN_PET_TUMEKEN, new Entry(new int[]{46337}, 9655, 9651, 65));
        m.put(NpcID.WARDEN_PET_TUMEKEN, new Entry(new int[]{46337}, 9655, 9651, 65));
        m.put(NpcID.POH_JADPET, new Entry(new int[]{9319}, 2650, 5805, 20));
        m.put(NpcID.POH_ZUK_PET, new Entry(new int[]{34586}, 7975, 7977, 18));
        m.put(NpcID.POH_VANGUARD_PET, new Entry(new int[]{32684}, 7430, 7429, 40));
        m.put(NpcID.POH_VASA_PET, new Entry(new int[]{32680}, 7416, 7411, 20));
        m.put(NpcID.POH_VENENATIS_PET, new Entry(new int[]{47393}, 9986, 9987, 58));
        m.put(NpcID.POH_VENENATIS_PET_LEGACY, new Entry(new int[]{28294, 28295}, 5326, 5325, 60));
        m.put(NpcID.POH_VESPULA_PET, new Entry(new int[]{32689}, 7449, 7448, 20));
        m.put(NpcID.POH_VETION_PET, new Entry(new int[]{47387, 47384}, 9965, 9967, 54));
        m.put(NpcID.POH_VETION_PET_LEGACY, new Entry(new int[]{28299}, 5505, 5497, 45));
        m.put(NpcID.POH_VETION_PET_2_LEGACY, new Entry(new int[]{28299}, 5505, 5497, 45,
                (short)55184, (short)54926, (short)54693, (short)55190, (short)54571, (short)54804,
                (short)4019, (short)4007, (short)4007, (short)3879, (short)4023, (short)2966));
        m.put(NpcID.POH_VETION_PET_2, new Entry(new int[]{47383, 47385, 47388, 47389}, 9965, 9967, 54,
                (short)55184, (short)54926, (short)54693, (short)55190, (short)54571, (short)54804,
                (short)4019, (short)4007, (short)4007, (short)3879, (short)4023, (short)2966));
        m.put(NpcID.VETIONPET, new Entry(new int[]{28299}, 5505, 5497, 45));
        m.put(NpcID.POH_VORKATH_PET, new Entry(new int[]{35023}, 7948, 7959, 16));
        m.put(NpcID.WILEYCAT_HELL, new Entry(new int[]{13412, 13408}, 317, 314, 70, (short)8, (short)914));
        m.put(NpcID.POH_WILEYCAT_HELL, new Entry(new int[]{13412, 13408}, 317, 314, 70, (short)8, (short)914));
        m.put(NpcID.POH_WHISPERER_PET, new Entry(new int[]{49222, 49218, 49221, 49224}, 10230, 10233, 40));
        m.put(NpcID.POH_YAMA_PET, new Entry(new int[]{10339}, 12140, 12143, 30));
        m.put(NpcID.POH_GAUNTLET_PET, new Entry(new int[]{38596}, 8417, 8428, 25));
        m.put(NpcID.GAUNTLET_PET, new Entry(new int[]{38596}, 8417, 8428, 25));
        m.put(NpcID.POH_WARDEN_PET_ZEBAK, new Entry(new int[]{46509}, 2037, 2036, 112));
        m.put(NpcID.POH_SKILLPET_THIEVING_TANUKI, new Entry(new int[]{14390}, 7315, 7316, 110));
        m.put(NpcID.SARADOMIN_PET, new Entry(new int[]{27989, 27937, 27985, 27968, 27990}, 6966, 6965, 60));
        m.put(NpcID.POH_SARADOMIN_PET, new Entry(new int[]{27989, 27937, 27985, 27968, 27990}, 6966, 6965, 60));
        m.put(NpcID.MAGGOT_KING_PET, new Entry(new int[]{61513}, 13926, 13935, 25));
        m.put(NpcID.POH_MAGGOT_KING_PET, new Entry(new int[]{61513}, 13926, 13935, 25));
        LOOKUP = Collections.unmodifiableMap(m);
    }


    private PetGhostData() {}
}
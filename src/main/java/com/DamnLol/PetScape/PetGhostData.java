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
        m.put(NpcID.ABYSSAL_ORPHAN, new Entry(new int[]{29631}, 7125, 7124, -1));
        m.put(NpcID.ABYSSAL_PROTECTOR, new Entry(new int[]{44070}, 2185, 2184, 80));
        m.put(NpcID.AKKHITO, new Entry(new int[]{46360, 46356, 46357}, 9760, 9421, 58));
        m.put(NpcID.ARCHIBALD, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15586, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15588, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15591, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15593, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15595, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15597, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.ARCHIBALD_15599, new Entry(new int[]{55852}, 7269, 6577, -1));
        m.put(NpcID.BABI, new Entry(new int[]{46352, 46350}, 9741, 9739, 36));
        m.put(NpcID.BABY_CHINCHOMPA, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)3988, (short)3988, (short)3982, (short)3986, (short)5014, (short)3988));
        m.put(NpcID.BABY_CHINCHOMPA_6756, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)3988, (short)3988, (short)3982, (short)3986, (short)5014, (short)3988));
        m.put(NpcID.BABY_CHINCHOMPA_6758, new Entry(new int[]{19371}, 5182, 5181, -1,
                (short)5169, (short)7343, (short)7335, (short)7339, (short)7343, (short)5165,
                (short)20, (short)33, (short)12, (short)37, (short)45, (short)49));
        m.put(NpcID.BABY_CHINCHOMPA_6759, new Entry(new int[]{29757}, 5182, 5181, -1));
        m.put(NpcID.BABY_MOLE, new Entry(new int[]{42012}, 3309, 3313, 90));
        m.put(NpcID.BABY_MOLERAT, new Entry(new int[]{42012}, 3309, 3313, 90,
                (short)5388, (short)5392, (short)5268, (short)5272, (short)5276, (short)4558,
                (short)317, (short)322, (short)326, (short)328, (short)332, (short)63706));
        m.put(NpcID.BABY_MOLE_6651, new Entry(new int[]{42012}, 3309, 3313, 90));
        m.put(NpcID.BARON, new Entry(new int[]{49195}, 10217, 10218, -1,
                (short)937, (short)790, (short)33988, (short)39207));
        m.put(NpcID.BEAVER, new Entry(new int[]{29754}, 7177, 7178, -1));
        m.put(NpcID.BEEF, new Entry(new int[]{60118}, 5852, 5856, 64));
        m.put(NpcID.BEEF_15633, new Entry(new int[]{60118}, 5852, 5856, 64));
        m.put(NpcID.BLOODHOUND, new Entry(new int[]{31740}, 7269, 7280, -1));
        m.put(NpcID.BLOODHOUND_7232, new Entry(new int[]{31740}, 7269, 7280, -1));
        m.put(NpcID.BRAN, new Entry(new int[]{55951}, 11970, 11972, 26));
        m.put(NpcID.BROAV, new Entry(new int[]{53343}, 11232, 11234, 120));
        m.put(NpcID.BROAV_13665, new Entry(new int[]{53343}, 11232, 11234, 120));
        m.put(NpcID.BUTCH, new Entry(new int[]{49298}, 10337, 10339, 60));
        m.put(NpcID.CALLISTO_CUB, new Entry(new int[]{47396}, 10011, 10010, 16));
        m.put(NpcID.CALLISTO_CUB_11982, new Entry(new int[]{28298}, 4919, 4923, 35));
        m.put(NpcID.CALLISTO_CUB_11986, new Entry(new int[]{28298}, 4919, 4923, 35));
        m.put(NpcID.CALLISTO_CUB_5558, new Entry(new int[]{47396}, 10011, 10010, 16));
        m.put(NpcID.CAT_6665, new Entry(new int[]{3010, 3006}, 317, 314, 60,
                (short)61, (short)16, (short)127, (short)12, (short)12, (short)33));
        m.put(NpcID.CHAOS_ELEMENTAL_JR, new Entry(new int[]{28256}, 3144, 3145, -1));
        m.put(NpcID.CHAOS_ELEMENTAL_JR_5907, new Entry(new int[]{28256}, 3144, 3145, -1));
        m.put(NpcID.CHOMPY_CHICK, new Entry(new int[]{26861}, 6764, 6765, 83));
        m.put(NpcID.CORPOREAL_CRITTER, new Entry(new int[]{11056}, 1678, 7974, 64));
        m.put(NpcID.CORPOREAL_CRITTER_8010, new Entry(new int[]{11056}, 1678, 7974, 64));
        m.put(NpcID.CORRUPTED_YOUNGLLEF, new Entry(new int[]{38597}, 8417, 8428, 25));
        m.put(NpcID.CORRUPTED_YOUNGLLEF_8738, new Entry(new int[]{38597}, 8417, 8428, 25));
        m.put(NpcID.DAGANNOTH_PRIME_JR, new Entry(new int[]{9940, 9943, 9942}, 2850, 2849, 60,
                (short)11930, (short)27144, (short)16536, (short)16540,
                (short)5931, (short)1688, (short)21530, (short)21534));
        m.put(NpcID.DAGANNOTH_REX_JR, new Entry(new int[]{9941}, 2850, 2849, 60,
                (short)16536, (short)16540, (short)27144, (short)2477,
                (short)7322, (short)7326, (short)10403, (short)2595));
        m.put(NpcID.DAGANNOTH_SUPREME_JR, new Entry(new int[]{9941, 9943}, 2850, 2849, 60));
        m.put(NpcID.DOM, new Entry(new int[]{56456}, 12401, 12402, -1));
        m.put(NpcID.DOM_14785, new Entry(new int[]{56456}, 12401, 12402, -1));
        m.put(NpcID.ELIDINIS_DAMAGED_GUARDIAN, new Entry(new int[]{46332}, 9420, 9420, 65));
        m.put(NpcID.ELIDINIS_GUARDIAN, new Entry(new int[]{46332}, 9656, 9652, 65));
        m.put(NpcID.FLYING_VESPINA, new Entry(new int[]{32689}, 8639, 8639, 20));
        m.put(NpcID.GENERAL_GRAARDOR_JR, new Entry(new int[]{27660, 27665}, 7017, 7016, 30));
        m.put(NpcID.GIANT_SQUIRREL, new Entry(new int[]{32206}, 7309, 7310, 110));
        m.put(NpcID.GREATISH_GUARDIAN, new Entry(new int[]{44061}, 9379, 9378, 32));
        m.put(NpcID.GREAT_BLUE_HERON, new Entry(new int[]{41628}, 6772, 6774, -1));
        m.put(NpcID.GULL, new Entry(new int[]{59403}, 12586, 12587, 72));
        m.put(NpcID.GULLIVER_15060, new Entry(new int[]{59398}, 12548, 12550, 30));
        m.put(NpcID.GULL_14931, new Entry(new int[]{59398}, 12548, 12550, 30));
        m.put(NpcID.GULL_15059, new Entry(new int[]{59403}, 12586, 12587, 72));
        m.put(NpcID.HELLPUPPY, new Entry(new int[]{29240}, 6561, 6560, -1));
        m.put(NpcID.HERBI, new Entry(new int[]{33890}, 7694, 7695, 110,
                (short)19992, (short)20364, (short)19988, (short)20422,
                (short)6049, (short)6040, (short)4781, (short)4038));
        m.put(NpcID.HERON, new Entry(new int[]{29756}, 6772, 6774, -1));
        m.put(NpcID.HUBERTE, new Entry(new int[]{54453}, 11732, 11733, 100));
        m.put(NpcID.IKKLE_HYDRA, new Entry(new int[]{36185}, 8233, 8296, 20));
        m.put(NpcID.IKKLE_HYDRA_8493, new Entry(new int[]{36192}, 8298, 8297, 20));
        m.put(NpcID.IKKLE_HYDRA_8494, new Entry(new int[]{36188}, 8247, 8299, 20));
        m.put(NpcID.IKKLE_HYDRA_8495, new Entry(new int[]{36191}, 8254, 8300, 20));
        m.put(NpcID.IKKLE_HYDRA_8517, new Entry(new int[]{36185}, 8233, 8296, 20));
        m.put(NpcID.IKKLE_HYDRA_8518, new Entry(new int[]{36192}, 8298, 8297, 20));
        m.put(NpcID.IKKLE_HYDRA_8519, new Entry(new int[]{36188}, 8247, 8299, 20));
        m.put(NpcID.IKKLE_HYDRA_8520, new Entry(new int[]{36191}, 8254, 8300, 20));
        m.put(NpcID.JALNIBREK, new Entry(new int[]{33005}, 7573, 7572, -1));
        m.put(NpcID.JALREKJAD, new Entry(new int[]{33012}, 7589, 8857, 20));
        m.put(NpcID.KALPHITE_PRINCESS, new Entry(new int[]{24597, 24598}, 6239, 6238, 45));
        m.put(NpcID.KALPHITE_PRINCESS_6638, new Entry(new int[]{24602, 24605, 24606}, 6236, 6236, 45));
        m.put(NpcID.KALPHITE_PRINCESS_6653, new Entry(new int[]{24602, 24605, 24606}, 6236, 6236, 45));
        m.put(NpcID.KALPHITE_PRINCESS_6654, new Entry(new int[]{24597, 24598}, 6239, 6238, 45));
        m.put(NpcID.KEPHRITI, new Entry(new int[]{46417}, 9572, 9419, 38));
        m.put(NpcID.KRAKEN, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.KRAKEN_6640, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.KRAKEN_6656, new Entry(new int[]{28231}, 3989, 3989, 15));
        m.put(NpcID.KREEARRA_JR, new Entry(new int[]{28019, 28021, 28020}, 7166, 7167, 30));
        m.put(NpcID.KREEARRA_JR_6643, new Entry(new int[]{28019, 28021, 28020}, 7166, 7167, 30));
        m.put(NpcID.KRIL_TSUTSAROTH_JR, new Entry(new int[]{27683, 27681, 27692, 27682, 27690}, 6935, 4070, 20));
        m.put(NpcID.KRIL_TSUTSAROTH_JR_6647, new Entry(new int[]{27683, 27681, 27692, 27682, 27690}, 6935, 4070, 20));
        m.put(NpcID.LILVIATHAN, new Entry(new int[]{49285, 49284}, 10277, 10292, 20));
        m.put(NpcID.LIL_BLOAT, new Entry(new int[]{35404}, 8080, 9031, 25));
        m.put(NpcID.LIL_CREATOR, new Entry(new int[]{41240}, 8842, 8846, 24));
        m.put(NpcID.LIL_DESTRUCTOR, new Entry(new int[]{41242}, 3079, 8847, 24));
        m.put(NpcID.LIL_MAIDEN, new Entry(new int[]{42280}, 8090, 8090, 30));
        m.put(NpcID.LIL_NYLO, new Entry(new int[]{35183}, 8002, 8003, 35));
        m.put(NpcID.LIL_SOT, new Entry(new int[]{35403}, 8137, 9032, 30));
        m.put(NpcID.LIL_XARP, new Entry(new int[]{35383}, 9033, 9033, 20));
        m.put(NpcID.LIL_ZIK, new Entry(new int[]{35381}, 8120, 8122, 20));
        m.put(NpcID.LITTLE_NIGHTMARE, new Entry(new int[]{39196}, 8593, 8634, 30));
        m.put(NpcID.LITTLE_PARASITE, new Entry(new int[]{39210}, 8553, 8553, 40));
        m.put(NpcID.MAYOR_OF_CATHERBY, new Entry(new int[]{9811}, -1, -1, -1,
                (short)-23598, (short)-27727, (short)-30142,
                (short)-23206, (short)10169, (short)10950));
        m.put(NpcID.MIDNIGHT, new Entry(new int[]{34187}, 7807, 7806, 34));
        m.put(NpcID.MOXI, new Entry(new int[]{54438, 54437}, 11528, 11529, 32));
        m.put(NpcID.MUPHIN, new Entry(new int[]{47149}, 9913, 9915, 25));
        m.put(NpcID.MUPHIN_12006, new Entry(new int[]{47147}, 9913, 9915, 25));
        m.put(NpcID.MUPHIN_12007, new Entry(new int[]{47156}, 9913, 9915, 25));
        m.put(NpcID.MUPHIN_12014, new Entry(new int[]{47149}, 9913, 9915, 25));
        m.put(NpcID.MUPHIN_12015, new Entry(new int[]{47147}, 9913, 9915, 25));
        m.put(NpcID.MUPHIN_12016, new Entry(new int[]{47156}, 9913, 9915, 25));
        m.put(NpcID.NEXLING, new Entry(new int[]{43209}, 9177, 9176, 48));
        m.put(NpcID.NID, new Entry(new int[]{54289}, 11473, 11474, 25));
        m.put(NpcID.NID_13683, new Entry(new int[]{54289}, 11473, 11474, 25));
        m.put(NpcID.NOON, new Entry(new int[]{34183}, 7768, 7768, 34));
        m.put(NpcID.OLMLET, new Entry(new int[]{32697}, 7396, 7395, 64));
        m.put(NpcID.PENANCE_PET, new Entry(
                new int[]{20717, 20715, 20714, 20709, 20713, 20712, 20711, 20710, 20716}, 5410, 5409, 29));
        m.put(NpcID.PHOENIX, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_3078, new Entry(new int[]{39146}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)43943, (short)41907, (short)39855, (short)38715, (short)39855, (short)38860));
        m.put(NpcID.PHOENIX_3079, new Entry(new int[]{39149}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)20, (short)33, (short)49, (short)20, (short)74, (short)86));
        m.put(NpcID.PHOENIX_3080, new Entry(new int[]{39147}, 6809, 6808, 80,
                (short)4894, (short)4647, (short)5669, (short)6053, (short)5066, (short)5053,
                (short)48819, (short)48844, (short)48844, (short)50006, (short)48844, (short)50904));
        m.put(NpcID.PHOENIX_3081, new Entry(new int[]{39148}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_3082, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_3083, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_3084, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_7368, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PHOENIX_7370, new Entry(new int[]{26852}, 6809, 6808, 80));
        m.put(NpcID.PRINCE_BLACK_DRAGON, new Entry(new int[]{17414, 17415, 17429, 17422, 17423}, 90, 4635, 40));
        m.put(NpcID.PRINCE_BLACK_DRAGON_6652, new Entry(new int[]{17414, 17415, 17429, 17422, 17423}, 90, 4635, 40));
        m.put(NpcID.PUPPADILE, new Entry(new int[]{32681}, 7417, 7982, 45));
        m.put(NpcID.QUETZIN, new Entry(new int[]{52601}, 10952, 10952, -1));
        m.put(NpcID.RAX, new Entry(new int[]{44936}, 8340, 9139, 64));
        m.put(NpcID.RAX_13684, new Entry(new int[]{44936}, 8340, 9139, 64));
        m.put(NpcID.RIC, new Entry(new int[]{55938}, 11969, 11971, 26));
        m.put(NpcID.RIFT_GUARDIAN, new Entry(new int[]{32204}, 7307, 7306, -1));
        m.put(NpcID.RIFT_GUARDIAN_7338, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)103, (short)127, (short)74));
        m.put(NpcID.RIFT_GUARDIAN_7339, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)0, (short)5056, (short)5551));
        m.put(NpcID.RIFT_GUARDIAN_7340, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)39849, (short)38866, (short)38086));
        m.put(NpcID.RIFT_GUARDIAN_7341, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)6036, (short)6942, (short)6319));
        m.put(NpcID.RIFT_GUARDIAN_7342, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43961, (short)0, (short)43313));
        m.put(NpcID.RIFT_GUARDIAN_7343, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)10469, (short)11200, (short)10425));
        m.put(NpcID.RIFT_GUARDIAN_7344, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)7104, (short)127, (short)5551));
        m.put(NpcID.RIFT_GUARDIAN_7345, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)22430, (short)22461, (short)21698));
        m.put(NpcID.RIFT_GUARDIAN_7346, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43945, (short)43968, (short)43313));
        m.put(NpcID.RIFT_GUARDIAN_7347, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)10469, (short)11200, (short)10425));
        m.put(NpcID.RIFT_GUARDIAN_7348, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)43484, (short)46040, (short)45361));
        m.put(NpcID.RIFT_GUARDIAN_7349, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)54503, (short)54742, (short)54449));
        m.put(NpcID.RIFT_GUARDIAN_7350, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)920, (short)910, (short)57));
        m.put(NpcID.RIFT_GUARDIAN_8024, new Entry(new int[]{32204}, 7307, 7306, -1,
                (short)939, (short)960, (short)60595, (short)4, (short)962, (short)43059));
        m.put(NpcID.ROCKY, new Entry(new int[]{32203}, 7315, 7316, 110));
        m.put(NpcID.ROCK_GOLEM, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.ROCK_GOLEM_7439, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)66, (short)61, (short)49, (short)53, (short)53, (short)49));
        m.put(NpcID.ROCK_GOLEM_7440, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)4044, (short)3912, (short)3906, (short)3901, (short)3897, (short)4021));
        m.put(NpcID.ROCK_GOLEM_7441, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)2337, (short)2332, (short)2328, (short)2452, (short)3346, (short)3470));
        m.put(NpcID.ROCK_GOLEM_7442, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43235, (short)43233, (short)43486, (short)43482, (short)43862, (short)43730));
        m.put(NpcID.ROCK_GOLEM_7443, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43105, (short)43100, (short)43096, (short)43092, (short)43088, (short)43084));
        m.put(NpcID.ROCK_GOLEM_7444, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)10388, (short)10512, (short)10508, (short)10378, (short)8, (short)4));
        m.put(NpcID.ROCK_GOLEM_7445, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)8134, (short)8128, (short)7104, (short)7101, (short)7099, (short)7097));
        m.put(NpcID.ROCK_GOLEM_7446, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)43181, (short)43177, (short)43173, (short)43297, (short)43292, (short)43288));
        m.put(NpcID.ROCK_GOLEM_7447, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)4550, (short)4548, (short)4544, (short)4539, (short)4535, (short)2487));
        m.put(NpcID.ROCK_GOLEM_7448, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)21675, (short)21671, (short)21667, (short)21662, (short)21658, (short)21782));
        m.put(NpcID.ROCK_GOLEM_7449, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)36145, (short)36141, (short)36137, (short)36133, (short)36257, (short)36252));
        m.put(NpcID.ROCK_GOLEM_7450, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)63830, (short)62800, (short)63692, (short)60624, (short)63675, (short)63663));
        m.put(NpcID.ROCK_GOLEM_7451, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)805, (short)929, (short)798, (short)796, (short)790, (short)912));
        m.put(NpcID.ROCK_GOLEM_7452, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)50999, (short)50995, (short)50993, (short)49967, (short)50087, (short)51098));
        m.put(NpcID.ROCK_GOLEM_7453, new Entry(new int[]{29755}, 7180, 7181, -1,
                (short)6823, (short)6697, (short)6819, (short)6814, (short)6682, (short)5656,
                (short)31531, (short)31655, (short)31651, (short)31518, (short)31640, (short)30612));
        m.put(NpcID.ROCK_GOLEM_7737, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.ROCK_GOLEM_7738, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.ROCK_GOLEM_7740, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.ROCK_GOLEM_7741, new Entry(new int[]{29755}, 7180, 7181, -1));
        m.put(NpcID.SCORPIAS_OFFSPRING, new Entry(new int[]{29193}, 6258, 6257, 280,
                (short)142, (short)4525, (short)4636, (short)4884, (short)4645,
                (short)28, (short)16, (short)16, (short)16, (short)16));
        m.put(NpcID.SCURRY, new Entry(new int[]{50146}, 10687, 10715, 28));
        m.put(NpcID.SKOTOS, new Entry(new int[]{31653}, 6935, 4070, 20));
        m.put(NpcID.SKOTOS_7671, new Entry(new int[]{31653}, 6935, 4070, 20));
        m.put(NpcID.SMOKE_DEVIL_6655, new Entry(new int[]{36012}, 1829, 1828, 35));
        m.put(NpcID.SMOKE_DEVIL_8482, new Entry(new int[]{28442}, 1829, 1828, 60));
        m.put(NpcID.SMOLCANO, new Entry(new int[]{38592}, 8429, 8447, 30));
        m.put(NpcID.SMOLCANO_8739, new Entry(new int[]{38592}, 8429, 8447, 30));
        m.put(NpcID.SMOL_HEREDIT, new Entry(new int[]{52580, 52582, 52578}, 10874, 10880, 50));
        m.put(NpcID.SNAKELING_2127, new Entry(new int[]{10413}, 1721, 2405, -1));
        m.put(NpcID.SNAKELING_2128, new Entry(new int[]{10416}, 1721, 2405, -1));
        m.put(NpcID.SNAKELING_2129, new Entry(new int[]{10414}, 1721, 2405, -1));
        m.put(NpcID.SOUP, new Entry(new int[]{59482}, 13498, 13499, 100));
        m.put(NpcID.SOUP_15058, new Entry(new int[]{59482}, 13498, 13499, 100));
        m.put(NpcID.SPOOKY_CHAIR, new Entry(new int[]{11470}, 3220, 3220, -1, (short)4886, (short)5908, (short)8070, (short)8844));
        m.put(NpcID.SRARACHA, new Entry(new int[]{37292}, 8320, 8319, 48));
        m.put(NpcID.SRARACHA_11157, new Entry(new int[]{37291}, 8320, 8319, 48,
                (short)229, (short)348, (short)412, (short)555, (short)670,
                (short)36069, (short)35041, (short)3505, (short)5945, (short)4007));
        m.put(NpcID.SRARACHA_11158, new Entry(new int[]{37290}, 8320, 8319, 48,
                (short)229, (short)348, (short)412, (short)555, (short)670,
                (short)36069, (short)35041, (short)39219, (short)39611, (short)39719));
        m.put(NpcID.TANGLEROOT, new Entry(new int[]{32202}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_7352, new Entry(new int[]{32202}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9492, new Entry(new int[]{39573}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9493, new Entry(new int[]{39571}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9494, new Entry(new int[]{39572}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9495, new Entry(new int[]{39574}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9496, new Entry(new int[]{39575}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9497, new Entry(new int[]{39573}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9498, new Entry(new int[]{39571}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9499, new Entry(new int[]{39572}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9500, new Entry(new int[]{39574}, 7312, 7313, -1));
        m.put(NpcID.TANGLEROOT_9501, new Entry(new int[]{39575}, 7312, 7313, -1));
        m.put(NpcID.TEKTINY, new Entry(new int[]{32682}, 7476, 7983, 25));
        m.put(NpcID.TINY_TEMPOR, new Entry(new int[]{41812}, 8895, 8895, 24));
        m.put(NpcID.TUMEKENS_DAMAGED_GUARDIAN, new Entry(new int[]{46333}, 9420, 9420, 65));
        m.put(NpcID.TUMEKENS_GUARDIAN, new Entry(new int[]{46337}, 9655, 9651, 65));
        m.put(NpcID.TUMEKENS_GUARDIAN_11812, new Entry(new int[]{46337}, 9655, 9651, 65));
        m.put(NpcID.TZREKJAD, new Entry(new int[]{9319}, 2650, 5805, 20));
        m.put(NpcID.TZREKZUK, new Entry(new int[]{34586}, 7975, 7977, 18));
        m.put(NpcID.VANGUARD_8198, new Entry(new int[]{32684}, 7430, 7984, 40));
        m.put(NpcID.VASA_MINIRIO, new Entry(new int[]{32680}, 7416, 7985, 20));
        m.put(NpcID.VENENATIS_SPIDERLING, new Entry(new int[]{47393}, 9986, 9987, 58));
        m.put(NpcID.VENENATIS_SPIDERLING_11981, new Entry(new int[]{28294, 28295}, 5326, 5325, 60));
        m.put(NpcID.VESPINA, new Entry(new int[]{32689}, 7449, 7986, 20));
        m.put(NpcID.VETION_JR, new Entry(new int[]{47387, 47384}, 9965, 9967, 54));
        m.put(NpcID.VETION_JR_11983, new Entry(new int[]{28299}, 5505, 5497, 45));
        m.put(NpcID.VETION_JR_11984, new Entry(new int[]{28299}, 5505, 5497, 45,
                (short)55184, (short)54926, (short)54693, (short)55190, (short)54571, (short)54804,
                (short)4019, (short)4007, (short)4007, (short)3879, (short)4023, (short)2966));
        m.put(NpcID.VETION_JR_5537, new Entry(new int[]{47383, 47385, 47388, 47389}, 9965, 9967, 54,
                (short)55184, (short)54926, (short)54693, (short)55190, (short)54571, (short)54804,
                (short)4019, (short)4007, (short)4007, (short)3879, (short)4023, (short)2966));
        m.put(NpcID.VETION_JR_5559, new Entry(new int[]{28299}, 5505, 5497, 45));
        m.put(NpcID.VORKI, new Entry(new int[]{35023}, 7948, 7959, 16));
        m.put(NpcID.WILY_HELLCAT, new Entry(new int[]{13412, 13408}, 317, 314, 70, (short)8, (short)914));
        m.put(NpcID.WILY_HELLCAT_6696, new Entry(new int[]{13412, 13408}, 317, 314, 70, (short)8, (short)914));
        m.put(NpcID.WISP, new Entry(new int[]{49222, 49218, 49221, 49224}, 10230, 10233, 40));
        m.put(NpcID.YAMI, new Entry(new int[]{10339}, 12140, 12143, 30));
        m.put(NpcID.YOUNGLLEF, new Entry(new int[]{38596}, 8417, 8428, 25));
        m.put(NpcID.YOUNGLLEF_8737, new Entry(new int[]{38596}, 8417, 8428, 25));
        m.put(NpcID.ZEBO, new Entry(new int[]{46509}, 2037, 2036, 112));
        m.put(NpcID.ZILYANA_JR, new Entry(new int[]{27989, 27937, 27985, 27968, 27990}, 6966, 6965, 60));
        m.put(NpcID.ZILYANA_JR_6646, new Entry(new int[]{27989, 27937, 27985, 27968, 27990}, 6966, 6965, 60));

        LOOKUP = Collections.unmodifiableMap(m);
    }


    private PetGhostData() {}
}
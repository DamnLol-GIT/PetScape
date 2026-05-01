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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// Loads JSON area definitions from resources/areas via the index.json manifest
@Slf4j
public final class AreaLoader
{
    private static final String AREAS_PATH = "/areas/";
    private static final String INDEX_FILE = "index.json";

    private AreaLoader() {}

    public static List<RoamingArea> loadAll(Gson gson)
    {
        String[] filenames = loadIndex(gson);
        List<RoamingArea> areas = new ArrayList<>(filenames.length);
        for (String filename : filenames)
        {
            AreaConfig config = loadConfig(gson, filename);
            config.validate(filename);
            areas.add(new JsonRoamingArea(config));
        }
        log.info("AreaLoader: loaded {} JSON area(s)", areas.size());
        return areas;
    }

    private static String[] loadIndex(Gson gson)
    {
        String path = AREAS_PATH + INDEX_FILE;
        try (InputStream in = AreaLoader.class.getResourceAsStream(path))
        {
            if (in == null) throw new IllegalStateException("AreaLoader: missing " + path);
            try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
            {
                String[] entries = gson.fromJson(r, String[].class);
                if (entries == null || entries.length == 0) throw new IllegalStateException("AreaLoader: empty index");
                return entries;
            }
        }
        catch (IOException e) { throw new IllegalStateException("AreaLoader: failed to read " + path, e); }
        catch (JsonSyntaxException e) { throw new IllegalStateException("AreaLoader: bad JSON in " + path + " - " + e.getMessage(), e); }
    }

    private static AreaConfig loadConfig(Gson gson, String filename)
    {
        String path = AREAS_PATH + filename;
        try (InputStream in = AreaLoader.class.getResourceAsStream(path))
        {
            if (in == null) throw new IllegalStateException("AreaLoader: missing " + path);
            try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
            {
                AreaConfig c = gson.fromJson(r, AreaConfig.class);
                if (c == null) throw new IllegalStateException("AreaLoader: " + filename + " parsed to null");
                return c;
            }
        }
        catch (IOException e) { throw new IllegalStateException("AreaLoader: failed to read " + path, e); }
        catch (JsonSyntaxException e) { throw new IllegalStateException("AreaLoader: bad JSON in " + filename + " - " + e.getMessage(), e); }
    }
}
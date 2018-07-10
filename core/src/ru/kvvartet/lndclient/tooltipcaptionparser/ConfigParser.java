package ru.kvvartet.lndclient.tooltipcaptionparser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ConfigParser implements TooltipCaptionParser {
    private static class StringToIntStringHashMap extends HashMap<String, Map<Integer, String>> {}

    @Override
    public @Nullable Map<String, Map<Integer, String>> parseConfig(@NotNull String fileName) {
        return new Json().fromJson(StringToIntStringHashMap.class, Gdx.files.internal(fileName));
    }
}

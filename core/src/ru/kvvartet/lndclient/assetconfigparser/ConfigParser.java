package ru.kvvartet.lndclient.assetconfigparser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ConfigParser implements AssetConfigParser {
    private static class StringToStringHashMap extends HashMap<String, Map<String, String>> {}

    @Override
    public @Nullable Map<String, Map<String, String>> parseConfig(@NotNull String fileName) {
        return new Json().fromJson(StringToStringHashMap.class, Gdx.files.internal(fileName));
    }
}

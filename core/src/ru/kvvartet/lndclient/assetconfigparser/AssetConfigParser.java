package ru.kvvartet.lndclient.assetconfigparser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface AssetConfigParser {
    @Nullable
    Map<String, Map<String, String>> parseConfig(@NotNull String fileName);
}

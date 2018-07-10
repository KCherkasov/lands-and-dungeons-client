package ru.kvvartet.lndclient.tooltipcaptionparser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface TooltipCaptionParser {
    @Nullable
    Map<String, Map<Integer, String>> parseConfig(@NotNull String fileName);
}

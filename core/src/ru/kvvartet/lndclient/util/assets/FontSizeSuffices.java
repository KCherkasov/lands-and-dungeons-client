package ru.kvvartet.lndclient.util.assets;

import org.jetbrains.annotations.NotNull;

public enum FontSizeSuffices {
    FS_CAPTION("_24pt", 24),
    FS_BIG("_18pt", 18),
    FS_TEXT("_14pt", 14),
    FS_SMALL("_10pt", 10);

    private final String suffix;
    private final Integer size;

    FontSizeSuffices(@NotNull String suffix, @NotNull Integer size) {
        this.suffix = suffix;
        this.size = size;
    }

    public @NotNull String asText() {
        return suffix;
    }

    public @NotNull Integer asInt() {
        return size;
    }
}

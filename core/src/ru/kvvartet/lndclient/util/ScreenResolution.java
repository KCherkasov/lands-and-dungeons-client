package ru.kvvartet.lndclient.util;

import org.jetbrains.annotations.NotNull;

public enum ScreenResolution {
    SR_640_480_PX("640 x 480", 640, 480),
    SR_800_600_PX("800 x 600", 800, 600),
    SR_1024_768_PX("1024 x 768", 1024, 768),
    SR_1280_768_PX("1280 x 768", 1280, 768),
    SR_1920_1080_PX("1920 x 1080", 1920, 1080);

    private final String tag;
    private final Integer width;
    private final Integer height;

    ScreenResolution(@NotNull String tag, @NotNull Integer width, @NotNull Integer height) {
        this.tag = tag;
        this.width = width;
        this.height = height;
    }

    public @NotNull String asText() {
        return tag;
    }

    public @NotNull Integer width() {
        return width;
    }

    public @NotNull Integer height() {
        return height;
    }
}

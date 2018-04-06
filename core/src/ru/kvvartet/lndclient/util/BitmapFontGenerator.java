package ru.kvvartet.lndclient.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.jetbrains.annotations.NotNull;

public final class BitmapFontGenerator {
    private BitmapFontGenerator() {}

    public static @NotNull BitmapFont generateFont(@NotNull String ttfFontPath,
                                                   @NotNull Integer fontSize,
                                                   @NotNull Color color) {
        final FreeTypeFontGenerator fontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal(ttfFontPath));
        final FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;
        return fontGenerator.generateFont(parameter);
    }
}

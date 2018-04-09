package ru.kvvartet.lndclient.assets.holders.fonts;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.List;

public class BitmapFontAssetHolderImpl extends AbstractAssetHolder<BitmapFont>
        implements BitmapFontAssetHolder {
    private static final Character SLASH = '/';

    public BitmapFontAssetHolderImpl(@NotNull List<String> assetsNames,
                                     @NotNull AssetManager manager) {
        super(assetsNames, manager);
    }

    @Override
    public @NotNull Class<BitmapFont> getAssetClass() {
        return BitmapFont.class;
    }

    @Override
    protected void load(@NotNull List<String> assetsNames, @NotNull AssetManager manager) {
        for (String assetName : assetsNames) {
            if (isTtf(assetName)) {
                final String shortAssetName = getShortTtfFontName(assetName);
                putAsset(shortAssetName + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF, manager);
                putAsset(shortAssetName + FontSizeSuffices.FS_BIG.asText() + Extensions.TTF, manager);
                putAsset(shortAssetName + FontSizeSuffices.FS_TEXT.asText() + Extensions.TTF, manager);
                putAsset(shortAssetName + FontSizeSuffices.FS_SMALL.asText() + Extensions.TTF, manager);
            } else {
                putAsset(assetName, manager);
            }
        }
    }

    private @NotNull String getShortTtfFontName(@NotNull String ttfFontName) {
        return ttfFontName.substring(ttfFontName.lastIndexOf(SLASH) + 1,
                ttfFontName.indexOf(Extensions.TTF));
    }

    private @NotNull Boolean isTtf(@NotNull String fontName) {
        return fontName.endsWith(Extensions.TTF);
    }

    private void putAsset(@NotNull String assetName, @NotNull AssetManager manager) {
        if (areClassesMet(assetName, manager) && manager.get(assetName, getAssetClass()) != null) {
            assets.put(assetName, manager.get(assetName, getAssetClass()));
        } else {
            onError(assetName, manager);
        }
    }
}

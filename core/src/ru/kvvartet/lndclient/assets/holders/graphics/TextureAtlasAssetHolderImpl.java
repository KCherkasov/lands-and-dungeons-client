package ru.kvvartet.lndclient.assets.holders.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class TextureAtlasAssetHolderImpl extends AbstractAssetHolder<TextureAtlas>
        implements TextureAtlasAssetHolder {
    public TextureAtlasAssetHolderImpl(@NotNull List<String> assetsList,
                                       @NotNull AssetManager manager) {
        super(assetsList, manager);
    }

    @Override
    protected @NotNull Class<TextureAtlas> getAssetClass() {
        return TextureAtlas.class;
    }
}

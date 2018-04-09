package ru.kvvartet.lndclient.assets.holders.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class TextureRegionAssetHolderImpl extends AbstractAssetHolder<TextureRegion>
        implements TextureRegionAssetHolder {
    public TextureRegionAssetHolderImpl(@NotNull List<String> assetsList,
                                        @NotNull AssetManager manager) {
        super(assetsList, manager);
    }

    @Override
    protected @NotNull Class<TextureRegion> getAssetClass() {
        return TextureRegion.class;
    }
}

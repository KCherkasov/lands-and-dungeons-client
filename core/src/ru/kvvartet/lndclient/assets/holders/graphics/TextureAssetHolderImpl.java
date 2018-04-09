package ru.kvvartet.lndclient.assets.holders.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class TextureAssetHolderImpl extends AbstractAssetHolder<Texture> implements TextureAssetHolder {
    public TextureAssetHolderImpl(@NotNull List<String> assetsList,
                                  @NotNull AssetManager manager) {
        super(assetsList, manager);
    }

    @Override
    protected @NotNull Class<Texture> getAssetClass() {
        return Texture.class;
    }
}

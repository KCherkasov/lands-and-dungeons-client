package ru.kvvartet.lndclient.assets.holders.skins;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class SkinAssetHolderImpl extends AbstractAssetHolder<Skin> implements SkinAssetHolder {
    public SkinAssetHolderImpl(@NotNull List<String> assetsNames, @NotNull AssetManager assetManager) {
        super(assetsNames, assetManager);
    }

    @Override
    protected  @NotNull Class<Skin> getAssetClass() {
        return Skin.class;
    }
}

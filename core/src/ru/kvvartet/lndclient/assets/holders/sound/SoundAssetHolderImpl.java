package ru.kvvartet.lndclient.assets.holders.sound;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class SoundAssetHolderImpl extends AbstractAssetHolder<Sound>
        implements SoundAssetHolder {
    public SoundAssetHolderImpl(@NotNull List<String> assetsNames,
                                @NotNull AssetManager assetManager) {
        super(assetsNames, assetManager);
    }

    @Override
    protected @NotNull Class<Sound> getAssetClass() {
        return Sound.class;
    }
}

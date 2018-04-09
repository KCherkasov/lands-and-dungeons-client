package ru.kvvartet.lndclient.assets.holders.sound;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.AbstractAssetHolder;

import java.util.List;

public class MusicAssetHolderImpl extends AbstractAssetHolder<Music>
        implements MusicAssetHolder {
    public MusicAssetHolderImpl(@NotNull List<String> assetsNames,
                                @NotNull AssetManager manager) {
        super(assetsNames, manager);
    }

    @Override
    protected @NotNull Class<Music> getAssetClass() {
        return Music.class;
    }
}

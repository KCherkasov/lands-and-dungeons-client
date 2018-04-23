package ru.kvvartet.lndclient.assets.manager;

import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureAssetHolder;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureAtlasAssetHolder;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureRegionAssetHolder;
import ru.kvvartet.lndclient.assets.holders.skins.SkinAssetHolder;
import ru.kvvartet.lndclient.assets.holders.sound.MusicAssetHolder;
import ru.kvvartet.lndclient.assets.holders.sound.SoundAssetHolder;

import java.util.Map;

public interface ClientAssetManager extends Disposable, AssetErrorListener {
    void initialize(@NotNull AssetManager assetManager);

    @NotNull AssetManager getAssetManager();

    void preload();

    @Nullable Map<String, Map<String, String>> getConfig();

    @NotNull Boolean isLoaded();

    @NotNull Boolean isConfigured();

    @NotNull Boolean update();

    void configureHolders();

    @Nullable BitmapFontAssetHolder getFontAssets();

    @Nullable MusicAssetHolder getMusicAssets();

    @Nullable SoundAssetHolder getSoundAssets();

    @Nullable TextureAssetHolder getTextureAssets();

    @Nullable TextureRegionAssetHolder getTextureRegionAssets();

    @Nullable TextureAtlasAssetHolder getTextureAtlasAssets();

    @Nullable SkinAssetHolder getSkinAssets();
}

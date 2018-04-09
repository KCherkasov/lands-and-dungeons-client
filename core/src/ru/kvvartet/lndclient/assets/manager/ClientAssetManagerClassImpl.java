package ru.kvvartet.lndclient.assets.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.assetconfigparser.ConfigParser;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolderImpl;
import ru.kvvartet.lndclient.assets.holders.graphics.*;
import ru.kvvartet.lndclient.assets.holders.skins.SkinAssetHolder;
import ru.kvvartet.lndclient.assets.holders.skins.SkinAssetHolderImpl;
import ru.kvvartet.lndclient.assets.holders.sound.MusicAssetHolder;
import ru.kvvartet.lndclient.assets.holders.sound.MusicAssetHolderImpl;
import ru.kvvartet.lndclient.assets.holders.sound.SoundAssetHolder;
import ru.kvvartet.lndclient.assets.holders.sound.SoundAssetHolderImpl;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ClientAssetManagerClassImpl implements ClientAssetManager {
    private static final String ASSET_LOADING_ERROR = "asset loading error at ";
    private static final String CONFIG_NPE_ERROR = "config data missing";
    private static final String ASSET_MANAGER_CONFIG_PATH = "core/assetconfig/AssetManager.json";

    private static final Character SLASH = '/';

    private static final Color DEFAULT_COLOR = Color.WHITE;

    private static ClientAssetManager instance = null;
    private static Boolean isConfigured = false;


    private final Map<String, Map<String, String> > config =
            new ConfigParser().parseConfig(ASSET_MANAGER_CONFIG_PATH);
    private AssetManager assetManager = null;

    private BitmapFontAssetHolder fontAssets = null;
    private SoundAssetHolder soundAssets = null;
    private MusicAssetHolder musicAssets = null;
    private TextureAssetHolder textureAssets = null;
    private TextureRegionAssetHolder textureRegionAssets = null;
    private TextureAtlasAssetHolder textureAtlasAssets = null;
    private SkinAssetHolder skinAssets = null;

    private ClientAssetManagerClassImpl() {}

    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public void initialize(@NotNull AssetManager assetManager) {
        this.assetManager = assetManager;
        this.assetManager.setErrorListener(this);
        enqueueAssets();
    }

    @Override
    public @NotNull AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public @Nullable Map<String, Map<String, String>> getConfig() {
        return config;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        isConfigured = false;
    }

    @Override
    public void error(AssetDescriptor descriptor, Throwable throwable) {
        Gdx.app.error(getClass().getName(),
                ASSET_LOADING_ERROR + descriptor.fileName, throwable);
    }

    @Override
    public @NotNull Boolean isLoaded() {
        return assetManager.update();
    }

    @Override
    public @NotNull Boolean isConfigured() {
        return isConfigured;
    }

    @Override
    public @NotNull Boolean update() {
        return assetManager.update();
    }

    @Override
    public void configureHolders() {
        if (isConfigured || !isLoaded() || config == null) {
            return;
        }

        configureGraphicAssets();
        configureSoundAssets();
        configureFontAssets();

        if (config.containsKey(AssetConfigKeys.AC_SKINS)) {
            skinAssets = new SkinAssetHolderImpl(makeAssetNamesList(
                    config.get(AssetConfigKeys.AC_SKINS)), assetManager);
        }

        isConfigured = true;
    }

    @Override
    public @Nullable BitmapFontAssetHolder getFontAssets() {
        return fontAssets;
    }

    @Override
    public @Nullable MusicAssetHolder getMusicAssets() {
        return musicAssets;
    }

    @Override
    public @Nullable SoundAssetHolder getSoundAssets() {
        return soundAssets;
    }

    @Override
    public @Nullable TextureAssetHolder getTextureAssets() {
        return textureAssets;
    }

    @Override
    public @Nullable TextureRegionAssetHolder getTextureRegionAssets() {
        return textureRegionAssets;
    }

    @Override
    public @Nullable TextureAtlasAssetHolder getTextureAtlasAssets() {
        return textureAtlasAssets;
    }

    @Override
    public @Nullable SkinAssetHolder getSkinAssets() {
        return skinAssets;
    }

    public static @NotNull ClientAssetManager getInstance() {
        if (instance == null) {
            instance = new ClientAssetManagerClassImpl();
        }
        return instance;
    }

    private void enqueueAssets() {
        enqueueIfExists(AssetConfigKeys.AC_TEXTURES, Texture.class);
        enqueueIfExists(AssetConfigKeys.AC_TEXTURE_REGIONS, TextureRegion.class);
        enqueueIfExists(AssetConfigKeys.AC_TEXTURE_ATLASES, TextureAtlas.class);

        enqueueIfExists(AssetConfigKeys.AC_SOUNDS, Sound.class);
        enqueueIfExists(AssetConfigKeys.AC_MUSIC, Music.class);

        processFonts();

        enqueueIfExists(AssetConfigKeys.AC_SKINS, Skin.class);
    }

    private void enqueueIfExists(@NotNull String key, @NotNull Class<?> type) {
        if (config == null) {
            Gdx.app.error(getClass().getName(), CONFIG_NPE_ERROR);
            return;
        }
        if (config.containsKey(key) && !config.get(key).isEmpty()) {
            for (String innerKey : config.get(key).keySet()) {
                assetManager.load(config.get(key).get(innerKey), type);
            }
        }
    }

    private @NotNull List<String> makeAssetNamesList(@NotNull Map<String, String> assetNamesMap) {
        final List<String> assetNamesList = new ArrayList<>();
        for (String key : assetNamesMap.keySet()) {
            assetNamesList.add(assetNamesMap.get(key));
        }
        return assetNamesList;
    }

    private void configureGraphicAssets() {
        if (config == null) {
            Gdx.app.error(getClass().getName(), CONFIG_NPE_ERROR);
            return;
        }

        if (config.containsKey(AssetConfigKeys.AC_TEXTURES)) {
            textureAssets = new TextureAssetHolderImpl(makeAssetNamesList(
                    config.get(AssetConfigKeys.AC_TEXTURES)), assetManager);
        }
        if (config.containsKey(AssetConfigKeys.AC_TEXTURE_REGIONS)) {
            textureRegionAssets = new TextureRegionAssetHolderImpl(
                    makeAssetNamesList(config.get(
                            AssetConfigKeys.AC_TEXTURE_REGIONS)), assetManager);
        }
        if (config.containsKey(AssetConfigKeys.AC_TEXTURE_ATLASES)) {
            textureAtlasAssets = new TextureAtlasAssetHolderImpl(
                    makeAssetNamesList(config.get(
                            AssetConfigKeys.AC_TEXTURE_ATLASES)), assetManager);
        }
    }

    private void configureSoundAssets() {
        if (config == null) {
            Gdx.app.error(getClass().getName(), CONFIG_NPE_ERROR);
            return;
        }

        if (config.containsKey(AssetConfigKeys.AC_SOUNDS)) {
            soundAssets = new SoundAssetHolderImpl(makeAssetNamesList(
                    config.get(AssetConfigKeys.AC_SOUNDS)), assetManager);
        }
        if (config.containsKey(AssetConfigKeys.AC_MUSIC)) {
            musicAssets = new MusicAssetHolderImpl(makeAssetNamesList(
                    config.get(AssetConfigKeys.AC_MUSIC)), assetManager);
        }
    }

    private void configureFontAssets() {
        if (config == null) {
            Gdx.app.error(getClass().getName(), CONFIG_NPE_ERROR);
            return;
        }

        if (config.containsKey(AssetConfigKeys.AC_FONTS)) {
            fontAssets = new BitmapFontAssetHolderImpl(makeAssetNamesList(
                    config.get(AssetConfigKeys.AC_FONTS)), assetManager);
        }
    }

    private void processFonts() {
        if (config == null) {
            Gdx.app.error(getClass().getName(), CONFIG_NPE_ERROR);
            return;
        }

        setFreetypeFontsLoader();

        if (config.containsKey(AssetConfigKeys.AC_FONTS)) {
            for (String key : config.get(AssetConfigKeys.AC_FONTS).keySet()) {
                final String fontName = config.get(AssetConfigKeys.AC_FONTS).get(key);
                if (isTtf(fontName)) {
                    makeBitmapFontsFromTtf(fontName);
                } else {
                    assetManager.load(fontName, BitmapFont.class);
                }
            }
        }
    }

    private void setFreetypeFontsLoader() {
        final FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    private void makeBitmapFontsFromTtf(@NotNull String fontFileName) {
        makeBitmapFontsFromTtf(fontFileName, DEFAULT_COLOR);
    }

    @SuppressWarnings("SameParameterValue")
    private void makeBitmapFontsFromTtf(@NotNull String fontFileName, @NotNull Color color) {
        makeBitmapFontFromTtf(fontFileName, FontSizeSuffices.FS_CAPTION, color);
        makeBitmapFontFromTtf(fontFileName, FontSizeSuffices.FS_BIG, color);
        makeBitmapFontFromTtf(fontFileName, FontSizeSuffices.FS_TEXT, color);
        makeBitmapFontFromTtf(fontFileName, FontSizeSuffices.FS_SMALL, color);
    }

    private @NotNull String getShortTtfFontName(@NotNull String ttfFontName) {
        return ttfFontName.substring(ttfFontName.lastIndexOf(SLASH) + 1,
                ttfFontName.indexOf(Extensions.TTF));
    }

    private @NotNull Boolean isTtf(@NotNull String fontName) {
        return fontName.endsWith(Extensions.TTF);
    }

    private void makeBitmapFontFromTtf(@NotNull String fullName,
                                       @NotNull FontSizeSuffices size,
                                       @NotNull Color color) {
        final String shortFontName = getShortTtfFontName(fullName) + size.asText() + Extensions.TTF;
        final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParameters.fontFileName = fullName;
        fontParameters.fontParameters.size = size.asInt();
        fontParameters.fontParameters.color = color;

        assetManager.load(shortFontName, BitmapFont.class, fontParameters);
    }
}

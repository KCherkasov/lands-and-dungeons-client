package ru.kvvartet.lndclient.assets.holders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAssetHolder<T> implements AssetHolder<T> {
    private static final String ASSET_DOES_NOT_EXIST = "asset doesn\'t exist: ";
    private static final String WRONG_ASSET_TYPE = "wrong asset type: ";
    private static final String INSTEAD = " excpected, found: ";

    protected final Map<String, T> assets = new HashMap<>();

    protected AbstractAssetHolder(@NotNull List<String> assetsList,
                                  @NotNull AssetManager manager) {
        load(assetsList, manager);
    }

    @Override
    public @NotNull Map<String, T> getAssets() {
        return assets;
    }

    @Override
    public @Nullable T getAsset(@NotNull String assetName) {
        return assets.getOrDefault(assetName, null);
    }

    @Override
    public @NotNull Boolean hasAsset(@NotNull String assetName) {
        return assets.containsKey(assetName);
    }

    protected @NotNull Boolean areClassesMet(@NotNull String assetName,
                                             @NotNull AssetManager manager) {
        return manager.getAssetType(assetName) != null && manager.getAssetType(assetName).equals(getAssetClass());
    }

    protected abstract @NotNull Class<T> getAssetClass();

    protected void load(@NotNull List<String> assetsNames, @NotNull AssetManager manager) {
        for (String assetName : assetsNames) {
            if (areClassesMet(assetName, manager) && manager.get(assetName, getAssetClass()) != null) {
                assets.put(assetName, manager.get(assetName, getAssetClass()));
            } else {
                onError(assetName, manager);
            }
        }
    }

    protected void onError(@NotNull String assetName, @NotNull AssetManager manager) {
        if (manager.getAssetType(assetName) != null) {
            Gdx.app.error(getClass().getName(), WRONG_ASSET_TYPE + getAssetClass().getSimpleName()
                    + INSTEAD + manager.getAssetType(assetName).getSimpleName());
        } else {
            Gdx.app.error(getClass().getName(), ASSET_DOES_NOT_EXIST + assetName);
        }
    }
}

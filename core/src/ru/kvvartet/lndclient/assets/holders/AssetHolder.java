package ru.kvvartet.lndclient.assets.holders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface AssetHolder<T> {
    @NotNull Map<String, T> getAssets();

    @Nullable T getAsset(@NotNull String assetName);

    @NotNull Boolean hasAsset(@NotNull String assetName);
}

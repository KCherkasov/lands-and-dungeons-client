package ru.kvvartet.lndclient.logic.components.entity.state.dataholders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public abstract class DataHolderProvider {
    private final Map<Integer, DataHolder> dataHolders;

    protected DataHolderProvider(@NotNull Map<Integer, DataHolder> dataHolders) {
        this.dataHolders = dataHolders;
    }

    protected @NotNull Map<Integer, DataHolder> getAvailableHolders() {
        return dataHolders;
    }

    protected @NotNull Boolean containsHolder(@NotNull Integer key) {
        return dataHolders.containsKey(key);
    }

    protected @Nullable DataHolder getHolder(@NotNull Integer key) {
        return dataHolders.getOrDefault(key, null);
    }

    protected @NotNull Integer getHolderValue(@NotNull Integer key) {
        return dataHolders.containsKey(key) ? dataHolders.get(key).getValue() : Integer.MIN_VALUE;
    }

    protected @NotNull Integer getHolderValue(@NotNull Integer key, @NotNull Integer dataIndex) {
        return dataHolders.containsKey(key) ? dataHolders.get(key).getValue(dataIndex) : Integer.MIN_VALUE;
    }

    protected @NotNull Set<Integer> keyset() {
        return dataHolders.keySet();
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolderProvider;

import java.util.Map;
import java.util.Set;

public class AffectorProviderImpl extends DataHolderProvider implements AffectorProvider {
    public AffectorProviderImpl(@NotNull Map<Integer, DataHolder> values) {
        super(values);
    }

    @Override
    public @NotNull Boolean hasAffector(@NotNull Integer key) {
        return containsHolder(key);
    }

    @Override
    public @Nullable DataHolder getAffector(@NotNull Integer key) {
        return getHolder(key);
    }

    @Override
    public @NotNull Integer getAffectorValue(@NotNull Integer key) {
        return getHolderValue(key);
    }

    @Override
    public @NotNull Integer getAffectorValue(@NotNull Integer key, @NotNull Integer dataIndex) {
        return getHolderValue(key, dataIndex);
    }

    @Override
    public @NotNull Set<Integer> affectorsKeyset() {
        return keyset();
    }
}

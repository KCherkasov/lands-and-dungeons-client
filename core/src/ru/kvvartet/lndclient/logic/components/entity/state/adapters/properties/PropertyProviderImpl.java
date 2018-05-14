package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolderProvider;

import java.util.Map;
import java.util.Set;

public class PropertyProviderImpl extends DataHolderProvider implements PropertyProvider {
    public PropertyProviderImpl(@NotNull Map<Integer, DataHolder> values) {
        super(values);
    }

    @Override
    public @NotNull Boolean hasProperty(@NotNull Integer key) {
        return containsHolder(key);
    }

    @Override
    public @Nullable DataHolder getProperty(@NotNull Integer key) {
        return getHolder(key);
    }

    @Override
    public @NotNull Integer getPropertyValue(@NotNull Integer key) {
        return getHolderValue(key);
    }

    @Override
    public @NotNull Integer getPropertyValue(@NotNull Integer key, @NotNull Integer dataIndex) {
        return getHolderValue(key, dataIndex);
    }

    @Override
    public @NotNull Set<Integer> propertiesKeyset() {
        return keyset();
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;

import java.util.Map;
import java.util.Set;

public interface PropertyProvider {
    @NotNull Boolean hasProperty(@NotNull Integer propertyKey);

    @Nullable DataHolder getProperty(@NotNull Integer propertyKey);

    @NotNull Integer getPropertyValue(@NotNull Integer propertyKey);

    @NotNull Integer getPropertyValue(@NotNull Integer propertyKey, @NotNull Integer dataIndex);

    @NotNull Set<Integer> propertiesKeyset();

    @NotNull Map<Integer, DataHolder> getAvailableProperties();
}

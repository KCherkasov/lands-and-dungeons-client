package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;

import java.util.Map;
import java.util.Set;

public interface AffectorProvider {
    @NotNull Boolean hasAffector(@NotNull Integer propertyKey);

    @Nullable DataHolder getAffector(@NotNull Integer propertyKey);

    @NotNull Integer getAffectorValue(@NotNull Integer propertyKey);

    @NotNull Integer getAffectorValue(@NotNull Integer propertyKey, @NotNull Integer dataIndex);

    @NotNull Set<Integer> affectorsKeyset();

    @NotNull Map<Integer, DataHolder> getAvailableAffectors();
}

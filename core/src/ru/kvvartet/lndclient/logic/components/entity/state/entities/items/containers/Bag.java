package ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.IngameItem;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.GameEntity;

public interface Bag extends GameEntity {
    @NotNull Integer getSlotsCount();

    @NotNull Integer getFreeSlotsCount();

    @Nullable IngameItem getItem(@NotNull Integer pos);

    @NotNull Boolean addItem(@Nullable IngameItem item, @NotNull Integer toPos);

    @NotNull Boolean addItem(@NotNull IngameItem item);

    @NotNull Boolean removeItem(@NotNull Integer fromPos, @NotNull Boolean isConfirmed);
}

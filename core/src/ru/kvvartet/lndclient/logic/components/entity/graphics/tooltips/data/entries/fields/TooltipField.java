package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipEntry;

public interface TooltipField<T> extends TooltipEntry {
    @NotNull String getCaption();

    @NotNull T getValue();
}

package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.ListHolder;

import java.util.List;

public class ListProperty extends ListHolder {
    public ListProperty(@NotNull List<Integer> values) {
        super(values);
    }
}

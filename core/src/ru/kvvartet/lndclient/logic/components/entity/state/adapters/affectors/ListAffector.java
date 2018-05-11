package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.ListHolder;

import java.util.List;

public class ListAffector extends ListHolder {
    public ListAffector(@NotNull List<Integer> values) {
        super(values);
    }
}

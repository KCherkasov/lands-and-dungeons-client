package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SetHolder;

import java.util.Set;

public class SetAffector extends SetHolder {
    public SetAffector(@JsonProperty("properties") @NotNull Set<Integer> values) {
        super(values);
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SetHolder;

import java.util.Set;

public class SetProperty extends SetHolder {
    public SetProperty(@JsonProperty("properties") @NotNull Set<Integer> values) {
        super(values);
    }
}

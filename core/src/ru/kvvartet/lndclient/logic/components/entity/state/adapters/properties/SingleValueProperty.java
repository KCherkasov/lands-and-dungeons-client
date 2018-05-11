package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SingleValueHolder;

public class SingleValueProperty extends SingleValueHolder {
    public SingleValueProperty(@JsonProperty("property") @NotNull Integer value) {
        super(value);
    }
}

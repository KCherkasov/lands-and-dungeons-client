package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SingleValueHolder;

public class SingleValueAffector extends SingleValueHolder {
    public SingleValueAffector(@JsonProperty("property") @NotNull Integer value) {
        super(value);
    }
}

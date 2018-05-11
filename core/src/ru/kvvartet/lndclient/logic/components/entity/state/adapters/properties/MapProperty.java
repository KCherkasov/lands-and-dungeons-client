package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.MapHolder;

import java.util.Map;

public class MapProperty extends MapHolder {
    public MapProperty(@JsonProperty("properties") @NotNull Map<Integer, Integer> values) {
        super(values);
    }
}

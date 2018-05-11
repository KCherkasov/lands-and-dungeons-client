package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.MapHolder;

import java.util.Map;

public class MapAffector extends MapHolder {
    public MapAffector(@JsonProperty("properties") @NotNull Map<Integer, Integer> values) {
        super(values);
    }
}

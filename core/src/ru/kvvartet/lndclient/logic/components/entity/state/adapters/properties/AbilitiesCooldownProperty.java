package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class AbilitiesCooldownProperty extends MapProperty {
    private static final int ZERO_COOLDOWN = 0;

    public AbilitiesCooldownProperty(@JsonProperty("propertyMap") @NotNull Map<Integer, Integer> properties) {
        super(properties);
    }

    @Override
    public void modifyByAddition(@NotNull Integer toAdd) {
        for (Integer key : Objects.requireNonNull(getMapData()).keySet()) {
            if (getMapData().get(key) > ZERO_COOLDOWN) {
                getMapData().replace(key, getMapData().get(key) + toAdd);
            }
        }
    }
}

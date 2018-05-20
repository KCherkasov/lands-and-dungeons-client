package ru.kvvartet.lndclient.logic.components.entity.state.entities.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public final class EmptyBagModel {
    private final String name;
    private final String description;
    private final Integer bagSize;

    public EmptyBagModel(@JsonProperty("name") @NotNull String name,
                         @JsonProperty("description") @NotNull String description,
                         @JsonProperty("bagSize") @NotNull Integer bagSize) {
        this.name = name;
        this.description = description;
        this.bagSize = bagSize;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public @NotNull Integer getBagSize() {
        return bagSize;
    }
}

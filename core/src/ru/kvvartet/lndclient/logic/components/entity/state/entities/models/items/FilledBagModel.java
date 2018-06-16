package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class FilledBagModel {
    private final Integer id;
    private final String name;
    private final String description;
    private final List<ItemModel> contents;

    public FilledBagModel(@JsonProperty("id") @NotNull Integer id,
                          @JsonProperty("name") @NotNull String name,
                          @JsonProperty("description") @NotNull String description,
                          @JsonProperty("contents") @NotNull List<ItemModel> contents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contents = contents;
    }

    @JsonProperty("id")
    public @NotNull Integer getId() {
        return id;
    }

    @JsonProperty("name")
    public @NotNull String getName() {
        return name;
    }

    @JsonProperty("description")
    public @NotNull String getDescription() {
        return description;
    }

    @JsonProperty("contents")
    public @NotNull List<ItemModel> getContents() {
        return contents;
    }
}

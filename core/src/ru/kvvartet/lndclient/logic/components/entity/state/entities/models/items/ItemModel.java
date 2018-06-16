package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.util.Constants;

import java.util.Map;

public final class ItemModel {
    private final Integer id;

    private final String name;
    private final String description;

    private final Integer level;

    private final Map<Integer, DataHolder> properties;
    private final Map<Integer, DataHolder> affectors;

    public ItemModel(@JsonProperty("id") @NotNull Integer id,
                     @JsonProperty("name") @NotNull String name,
                     @JsonProperty("description") @NotNull String description,
                     @JsonProperty("level") @NotNull Integer level,
                     @JsonProperty("properties") @NotNull Map<Integer, DataHolder> properties,
                     @JsonProperty("affectors") @NotNull Map<Integer, DataHolder> affectors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.properties = properties;
        this.affectors = affectors;
    }

    public ItemModel(@JsonProperty("name") @NotNull String name,
                     @JsonProperty("description") @NotNull String description,
                     @JsonProperty("level") @NotNull Integer level,
                     @JsonProperty("properties") @NotNull Map<Integer, DataHolder> properties,
                     @JsonProperty("affectors") @NotNull Map<Integer, DataHolder> affectors) {
        this(Constants.UNDEFINED_ID, name, description, level, properties, affectors);
    }

    public @NotNull Integer getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public @NotNull Integer getLevel() {
        return level;
    }

    public @NotNull Map<Integer, DataHolder> getProperties() {
        return properties;
    }

    public @NotNull Map<Integer, DataHolder> getAffectors() {
        return affectors;
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.flyweights;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;

import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public final class CharacterRaceModel {
    private final Integer id;
    private final String name;
    private final String descripion;
    private final Map<Integer, DataHolder> affectors;

    public CharacterRaceModel(@NotNull Integer id,
                              @NotNull String name,
                              @NotNull String descripion,
                              @NotNull Map<Integer, DataHolder> affectors) {
        this.id = id;
        this.name = name;
        this.descripion = descripion;
        this.affectors = affectors;
    }

    @JsonProperty("id")
    public @NotNull Integer getId() {
        return id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getDescripion() {
        return descripion;
    }

    public @NotNull Map<Integer, DataHolder> getAffectors() {
        return affectors;
    }
}

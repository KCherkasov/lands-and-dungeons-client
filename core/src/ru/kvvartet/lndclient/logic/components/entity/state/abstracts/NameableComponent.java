package ru.kvvartet.lndclient.logic.components.entity.state.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.GameEntity;

public abstract class NameableComponent extends EntityStateComponent implements GameEntity {
    private final String name;
    private final String description;

    protected NameableComponent(@NotNull String name,
                                @NotNull String description) {
        this.name = name;
        this.description = description;
    }

    @JsonIgnore
    public @NotNull String getName() {
        return name;
    }

    @JsonIgnore
    public @NotNull String getDescription() {
        return description;
    }
}

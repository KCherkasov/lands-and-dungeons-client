package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.aliveentities;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.Bag;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.CharacterRole;

import java.util.List;
import java.util.Map;

public abstract class AbstractAliveEntityModel {
    private final String name;
    private final String description;

    private final Map<Integer, DataHolder> properties;
    private final List<Bag> bags;

    private final CharacterRole role;

    public AbstractAliveEntityModel(@NotNull String name,
                                    @NotNull String description,
                                    @NotNull Map<Integer, DataHolder> properties,
                                    @NotNull List<Bag> bags,
                                    @NotNull CharacterRole role) {
        this.name = name;
        this.description = description;
        this.properties = properties;
        this.bags = bags;
        this.role = role;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public @NotNull Map<Integer, DataHolder> getProperties() {
        return properties;
    }

    public @NotNull List<Bag> getBags() {
        return bags;
    }

    public @NotNull CharacterRole getRole() {
        return role;
    }
}

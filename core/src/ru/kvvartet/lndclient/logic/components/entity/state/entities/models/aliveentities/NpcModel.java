package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.aliveentities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.Bag;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.CharacterRole;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.DecisionMaker;

import java.util.List;
import java.util.Map;

public class NpcModel extends AbstractAliveEntityModel {
    private final DecisionMaker behavior;

    public NpcModel(@NotNull String name,
                    @NotNull String description,
                    @NotNull Map<Integer, DataHolder> properties,
                    @NotNull List<Bag> bags,
                    @NotNull CharacterRole role,
                    @NotNull DecisionMaker behavior) {
        super(name, description, properties, bags, role);
        this.behavior = behavior;
    }

    public NpcModel(@NotNull String name,
                    @NotNull String description,
                    @NotNull Map<Integer, DataHolder> properties,
                    @NotNull List<Bag> bags,
                    @NotNull CharacterRole role) {
        super(name, description, properties, bags, role);
        this.behavior = null;
    }

    public @Nullable DecisionMaker getBehavior() {
        return behavior;
    }
}

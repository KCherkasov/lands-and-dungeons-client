package ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.GameEntityComponent;
import ru.kvvartet.lndclient.logic.messages.Message;

import java.util.List;

public class MovementMessage extends Message {
    private final List<GameEntityComponent> route;

    public MovementMessage(@NotNull List<GameEntityComponent> route) {
        this.route = route;
    }

    public @NotNull List<GameEntityComponent> getRoute() {
        return route;
    }
}

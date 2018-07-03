package ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.MapNodeGraphic;
import ru.kvvartet.lndclient.logic.messages.Message;

import java.util.List;

public class MovementMessage extends Message {
    private final List<MapNodeGraphic> route;

    public MovementMessage(@NotNull List<MapNodeGraphic> route) {
        this.route = route;
    }

    public @NotNull List<MapNodeGraphic> getRoute() {
        return route;
    }
}

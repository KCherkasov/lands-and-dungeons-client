package ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.GameEntityComponent;
import ru.kvvartet.lndclient.logic.messages.Message;

public class DieMessage extends Message {
    private final GameEntityComponent tile;

    public DieMessage(@NotNull GameEntityComponent tile) {
        this.tile = tile;
    }

    public @NotNull GameEntityComponent getTile() {
        return tile;
    }
}

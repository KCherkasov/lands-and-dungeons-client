package ru.kvvartet.lndclient.logic.messages.battle.graphics.tiles;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.Message;

public class SelectMessage extends Message {
    private final Boolean isSelect;

    public SelectMessage(@NotNull Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public @NotNull Boolean getIsSelect() {
        return isSelect;
    }
}

package ru.kvvartet.lndclient.logic.messages.bag;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.ItemModel;
import ru.kvvartet.lndclient.logic.messages.Message;
import ru.kvvartet.lndclient.util.Constants;

public class ItemAddMessage extends Message {
    private final Integer toPos;
    private final ItemModel toAdd;

    public ItemAddMessage(@JsonProperty("id") @Nullable Integer toPos,
                          @JsonProperty("toAdd") @NotNull ItemModel toAdd) {
        this.toPos = toPos == null ? Constants.UNDEFINED_ID : toPos;
        this.toAdd = toAdd;
    }

    public @NotNull Integer getToPos() {
        return toPos;
    }

    public @NotNull ItemModel getToAdd() {
        return toAdd;
    }
}

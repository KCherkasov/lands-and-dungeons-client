package ru.kvvartet.lndclient.logic.messages.bag;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.Message;

public class ItemRemoveMessage extends Message {
    private final Integer fromPos;
    private final Boolean confirmation;

    public ItemRemoveMessage(@JsonProperty("fromPos") @NotNull Integer fromPos,
                             @JsonProperty("confirmation") @NotNull Boolean confirmation) {
        this.fromPos = fromPos;
        this.confirmation = confirmation;
    }

    public @NotNull Integer getFromPos() {
        return fromPos;
    }

    public @NotNull Boolean getConfirmation() {
        return confirmation;
    }
}

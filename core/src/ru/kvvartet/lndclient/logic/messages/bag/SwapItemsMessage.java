package ru.kvvartet.lndclient.logic.messages.bag;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.Bag;
import ru.kvvartet.lndclient.logic.messages.Message;

public class SwapItemsMessage extends Message {
    private final Integer fromPos;
    private final Integer toPos;
    private final Bag toBag;

    public SwapItemsMessage(@JsonProperty("fromPos") @NotNull Integer fromPos,
                            @JsonProperty("toPos") @NotNull Integer toPos,
                            @JsonProperty("toBag") @Nullable Bag toBag) {
        this.fromPos = fromPos;
        this.toPos = toPos;
        this.toBag = toBag;
    }

    public @NotNull Integer getFromPos() {
        return fromPos;
    }

    public @NotNull Integer getToPos() {
        return toPos;
    }

    public @Nullable Bag getToBag() {
        return toBag;
    }
}

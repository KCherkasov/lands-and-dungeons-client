package ru.kvvartet.lndclient.logic.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.kvvartet.lndclient.logic.messages.bag.ItemAddMessage;
import ru.kvvartet.lndclient.logic.messages.bag.ItemRemoveMessage;
import ru.kvvartet.lndclient.logic.messages.bag.SwapItemsMessage;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(SwapItemsMessage.class),
                @JsonSubTypes.Type(ItemAddMessage.class),
                @JsonSubTypes.Type(ItemRemoveMessage.class)})
public abstract class Message {
}

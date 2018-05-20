package ru.kvvartet.lndclient.logic.components.entity;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.Message;

import java.util.function.Consumer;

public interface Component {
    interface MessageProcessor extends Consumer<Message> {}

    void registerHandler(@NotNull String messageType, @NotNull MessageProcessor handler);

    void unregisterHandler(@NotNull String messageType);

    @NotNull Boolean hasHandler(@NotNull String messageType);

    void receiveMessage(@NotNull Message message);

    void update(float timeDelta);

    void markToDispose();

    @NotNull Boolean isToDispose();

    void dispose();

    @NotNull Integer getId();
}

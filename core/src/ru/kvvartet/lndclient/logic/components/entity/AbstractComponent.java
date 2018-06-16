package ru.kvvartet.lndclient.logic.components.entity;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.Message;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class AbstractComponent implements Component {
    protected Boolean toDispose = false;
    protected final Queue<Message> inboxMessages = new ConcurrentLinkedDeque<>();
    protected final HashMap<String, MessageProcessor> handlers = new HashMap<>();

    protected AbstractComponent() {
        initHandlers();
    }

    @Override
    public void update(float delta) {
        if (toDispose) {
            return;
        }
        while (!inboxMessages.isEmpty()) {
            final Message message = inboxMessages.poll();
            if (message != null) {
                handle(message);
            }
        }
    }

    @Override
    public void markToDispose() {
        toDispose = true;
    }

    @Override
    public @NotNull Boolean isToDispose() {
        return toDispose;
    }

    @Override
    public void receiveMessage(@NotNull Message message) {
        inboxMessages.offer(message);
    }

    @Override
    public void registerHandler(@NotNull String messageType, @NotNull MessageProcessor handler) {
        if (handlers.containsKey(messageType)) {
            handlers.replace(messageType, handler);
        } else {
            handlers.put(messageType, handler);
        }
    }

    @Override
    public void unregisterHandler(@NotNull String messageType) {
        handlers.remove(messageType);
    }

    @Override
    public @NotNull Boolean hasHandler(@NotNull String messageType) {
        return handlers.containsKey(messageType);
    }

    protected void handle(@NotNull Message message) {
        if (handlers.containsKey(message.getClass().getName())) {
            handlers.get(message.getClass().getName()).accept(message);
        }
    }

    protected abstract void initHandlers();
}

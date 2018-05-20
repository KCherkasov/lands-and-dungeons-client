package ru.kvvartet.lndclient.logic.components.entity.input;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.AbstractComponent;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class InputComponent extends AbstractComponent {
    private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger(0);

    private final Integer id = INSTANCE_COUNTER.getAndIncrement();

    @Override
    public @NotNull Integer getId() {
        return id;
    }
}

package ru.kvvartet.lndclient.logic.components.threads;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.AbstractComponent;
import ru.kvvartet.lndclient.logic.components.entity.graphics.GraphicsComponent;
import ru.kvvartet.lndclient.logic.components.entity.input.InputComponent;
import ru.kvvartet.lndclient.logic.components.entity.state.abstracts.EntityStateComponent;

/**
 * {@link ru.kvvartet.lndclient.logic.components.entity.Component} object pool interface.
 * Handles attached objects' update and may be used for multi-threading purposes.
 * @param <T> specifies which group of components (e.g. {@link GraphicsComponent},
 * {@link InputComponent} or {@link EntityStateComponent}.
 */
public interface ComponentPool<T extends AbstractComponent> {
    /**
     * checks if this pool has a component with given id.
     * @param componentId - component id to look for.
     * @return true if there's such component in this pool or
     */
    @NotNull Boolean hasComponent(@NotNull Integer componentId);

    /**
     * try to get a component from pool by its id
     * @param componentId - component id to look for
     * @return component if there's a one with such id in the pool or null otherwise
     */
    @Nullable T getComponent(@NotNull Integer componentId);

    void addComponent(@NotNull T component);

    void removeComponent(@NotNull Integer componentId);

    void update(float timeDelta);

    void clearPool();

    interface GraphicsComponentPool extends ComponentPool<GraphicsComponent> {}

    interface InputComponentPool extends ComponentPool<InputComponent> {}

    interface EntityStateComponentPool extends ComponentPool<EntityStateComponent> {}
}

package ru.kvvartet.lndclient.logic.components.threads;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.AbstractComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractComponentPool<T extends AbstractComponent> implements ComponentPool<T>  {
    private final Map<Integer, T> componentsPool = new ConcurrentHashMap<>();

    @Override
    public @NotNull Boolean hasComponent(@NotNull Integer componentId) {
        return componentsPool.containsKey(componentId);
    }

    @Override
    public @Nullable T getComponent(@NotNull Integer componentId) {
        return componentsPool.getOrDefault(componentId, null);
    }

    @Override
    public void addComponent(@NotNull T component) {
        if (!componentsPool.containsKey(component.getId())) {
            componentsPool.put(component.getId(), component);
        }
    }

    @Override
    public void removeComponent(@NotNull Integer componentId) {
        componentsPool.remove(componentId);
    }

    @Override
    public void update(float timeDelta) {
        for (Integer componentId : componentsPool.keySet()) {
            if (componentsPool.get(componentId) != null) {
                componentsPool.get(componentId).update(timeDelta);
            }
        }
    }

    @Override
    public void clearPool() {
        componentsPool.clear();
    }
}

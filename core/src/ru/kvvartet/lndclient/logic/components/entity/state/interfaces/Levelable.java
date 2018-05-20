package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;

public interface Levelable extends GameEntity {
    @NotNull Integer getLevel();

    default void levelUp() {}
}

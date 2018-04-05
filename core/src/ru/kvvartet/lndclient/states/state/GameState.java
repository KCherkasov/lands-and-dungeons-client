package ru.kvvartet.lndclient.states.state;

import com.sun.istack.internal.NotNull;

public interface GameState {
    @NotNull
    Boolean update(float timeDelta);

    void handleInput();
}

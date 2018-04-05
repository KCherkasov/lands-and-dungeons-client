package ru.kvvartet.lndclient.states.manager;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.states.state.GameState;

public interface StateManager {
    void requestStatePop();
    void requestStateClear();
    void requestStatePush(@NotNull GameState gameState);

    void update(float timeDelta);
    void handleInput();
}

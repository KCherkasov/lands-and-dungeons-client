package ru.kvvartet.lndclient.client.states.manager;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.client.states.state.GameState;

public interface StateManager {
    void requestStatePop();

    void requestStateClear();

    void requestStatePush(@NotNull GameState gameState);

    void update(float timeDelta);
}

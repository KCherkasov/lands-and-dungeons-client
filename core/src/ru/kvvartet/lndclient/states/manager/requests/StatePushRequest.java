package ru.kvvartet.lndclient.states.manager.requests;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.states.state.GameState;

public class StatePushRequest implements StateManagerRequest {
    private final GameState requestedState;

    public StatePushRequest(@NotNull GameState requestedState) {
        this.requestedState = requestedState;
    }

    @NotNull
    public GameState getRequestedState() {
        return requestedState;
    }
}

package ru.kvvartet.lndclient.client.states.manager.requests;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.client.states.state.GameState;

public class StatePushRequest implements StateManagerRequest {
    private final GameState gameState;

    public StatePushRequest(@NotNull GameState gameState) {
        this.gameState = gameState;
    }

    @NotNull
    public GameState getGameState() {
        return gameState;
    }
}

package ru.kvvartet.lndclient.client.states.manager.requests;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.client.states.state.GameState;

public class StatePushRequest implements StateManagerRequest {
    private final GameState gameState;

    public StatePushRequest(@NotNull GameState gameState) {
        this.gameState = gameState;
    }

    public @NotNull GameState getGameState() {
        return gameState;
    }
}

package ru.kvvartet.lndclient.client.states.state;

import com.sun.istack.internal.NotNull;
import ru.kvvartet.lndclient.client.states.manager.StateManager;

public class MainMenuState extends AbstractState {
    public MainMenuState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    @NotNull
    public Boolean isBlocking() {
        return false;
    }

    @Override
    public void setupStage() {
        // TODO: Main menu flexible layout there
    }

    @Override
    public void render(float timeDelta) {

    }
}

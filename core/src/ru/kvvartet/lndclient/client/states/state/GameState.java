package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Screen;
import com.sun.istack.internal.NotNull;

public interface GameState extends Screen {
    @NotNull
    Boolean isBlocking();

    void setupStage();

}

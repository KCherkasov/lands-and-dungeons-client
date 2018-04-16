package ru.kvvartet.lndclient.client.gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.jetbrains.annotations.NotNull;

public interface HudScreen extends Screen {
    @NotNull Stage getStage();
}

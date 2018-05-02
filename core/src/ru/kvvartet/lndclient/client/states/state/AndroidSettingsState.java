package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.preferences.AndroidPreferences;

public class AndroidSettingsState extends AbstractSettingsState {
    public AndroidSettingsState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void addExtraOptions(@NotNull Table root, @NotNull Skin skin) {}

    @Override
    public void loadSettings() {
        settings = AndroidPreferences.getInstance();
    }

    @SuppressWarnings("unused")
    private @NotNull AndroidPreferences getSettings() {
        return (AndroidPreferences)settings;
    }
}

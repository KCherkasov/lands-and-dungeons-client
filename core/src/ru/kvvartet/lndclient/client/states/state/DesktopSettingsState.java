package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.configs.SgxUiSkinKeys;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.preferences.DesktopPreferences;
import ru.kvvartet.lndclient.util.ScreenResolution;

import java.util.HashMap;
import java.util.Map;

public class DesktopSettingsState extends AbstractSettingsState {
    private static final String SCREEN_RESOLUTION_LABEL = "Resolution: ";
    private static final String FULLSCREEN_LABEL = "Fullscreen: ";
    private static final String EMPTY_RESOLUTION_KEY = "";

    private static final Map<String, ScreenResolution> SCREEN_RESOLUTIONS = initScreenResolutionsMap();

    private CheckBox enableFullScreen = null;
    private List<String> screenResolutions = null;

    public DesktopSettingsState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    protected void loadSettings() {
        settings = DesktopPreferences.getInstance();
    }

    @Override
    protected void addExtraOptions(@NotNull Table root, @NotNull Skin theme) {
        enableFullScreen = new CheckBox(EMPTY_LABEL, theme, SgxUiSkinKeys.CHECKBOX_SWITCH);
        enableFullScreen.setChecked(getSettings().isFullScreen());
        addCheckbox(FULLSCREEN_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT, root, enableFullScreen);

        root.row().center().fillX();
        root.add(new Label(SCREEN_RESOLUTION_LABEL, theme, SgxUiSkinKeys.LABEL_MEDIUM_WHITE)).left();
        screenResolutions = new List<>(theme);
        screenResolutions.setItems(new Array<>(SCREEN_RESOLUTIONS.keySet().toArray()));
        screenResolutions.setSelected(getResolutionTagByWidth(getSettings().getScreenWidth()));
        root.add(screenResolutions);
    }

    @Override
    protected void flushFromControls() {
        super.flushFromControls();
        getSettings().setFullScreen(enableFullScreen.isChecked());
        getSettings().setScreenWidth(SCREEN_RESOLUTIONS.get(screenResolutions.getSelected()).width());
        getSettings().setScreenHeight(SCREEN_RESOLUTIONS.get(screenResolutions.getSelected()).height());
    }

    @Override
    protected void resetControls() {
        super.resetControls();
        enableFullScreen.setChecked(getSettings().isFullScreen());
        screenResolutions.setSelected(getResolutionTagByWidth(getSettings().getScreenWidth()));
    }

    @Override
    protected void applyChanges() {
        super.applyChanges();
        if (getSettings().isFullScreen()) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            Gdx.graphics.setWindowedMode(getSettings().getScreenWidth(),
                    getSettings().getScreenHeight());
        }
    }

    private @NotNull DesktopPreferences getSettings() {
        return (DesktopPreferences)settings;
    }

    private static @NotNull Map<String, ScreenResolution> initScreenResolutionsMap() {
        final Map<String, ScreenResolution> screenResolutionMap = new HashMap<>();
        screenResolutionMap.put(ScreenResolution.SR_640_480_PX.asText(), ScreenResolution.SR_640_480_PX);
        screenResolutionMap.put(ScreenResolution.SR_800_600_PX.asText(), ScreenResolution.SR_800_600_PX);
        screenResolutionMap.put(ScreenResolution.SR_1024_768_PX.asText(), ScreenResolution.SR_1024_768_PX);
        screenResolutionMap.put(ScreenResolution.SR_1280_768_PX.asText(), ScreenResolution.SR_1280_768_PX);
        screenResolutionMap.put(ScreenResolution.SR_1920_1080_PX.asText(), ScreenResolution.SR_1920_1080_PX);
        return screenResolutionMap;
    }

    private @NotNull String getResolutionTagByWidth(@NotNull Integer width) {
        for (String key : SCREEN_RESOLUTIONS.keySet()) {
            if (SCREEN_RESOLUTIONS.get(key).width().equals(width)) {
                return key;
            }
        }
        return EMPTY_RESOLUTION_KEY;
    }
}

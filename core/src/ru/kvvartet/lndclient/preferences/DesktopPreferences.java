package ru.kvvartet.lndclient.preferences;

import org.jetbrains.annotations.NotNull;

public final class DesktopPreferences extends AbstractPreferences {
    private static final String SETTINGS_FILE_NAME = "settings.dpf";

    public static final String WINDOW_HEIGHT_KEY = "windowHeight";
    public static final String WINDOW_WIDTH_KEY = "windowWidth";
    public static final String IS_RESIZABLE_KEY = "isResizable";
    public static final String IS_FULL_SCREEN_KEY = "isFullscreen";
    public static final String IS_UNDECORATED_KEY = "isUndecorated";

    private static final int DEFAULT_WINDOW_WIDTH = 800;
    private static final int DEFAULT_WINDOW_HEIGHT = 600;
    private static final boolean DEFAULT_IS_RESIZABLE = false;
    private static final boolean DEFAULT_IS_FULL_SCREEN = false;
    private static final boolean DEFAULT_IS_UNDECORATED = true;

    private static final DesktopPreferences INSTANCE = new DesktopPreferences();

    private Integer screenWidth;
    private Integer screenHeight;
    private Boolean isFullScreen;
    private Boolean isResizable;
    private Boolean isUndecorated;

    public static @NotNull DesktopPreferences getInstance() {
        return INSTANCE;
    }

    private DesktopPreferences() {
        super(SETTINGS_FILE_NAME);
    }

    public @NotNull Integer getScreenWidth() {
        return screenWidth;
    }

    public @NotNull Integer getScreenHeight() {
        return screenHeight;
    }

    public @NotNull Boolean isFullScreen() {
        return isFullScreen;
    }

    public @NotNull Boolean isResizable() {
        return isResizable;
    }

    public @NotNull Boolean isUndecorated() {
        return isUndecorated;
    }

    public void setScreenWidth(@NotNull Integer value) {
        screenWidth = value;
    }

    public void setScreenHeight(@NotNull Integer value) {
        screenHeight = value;
    }

    public void setFullScreen(@NotNull Boolean value) {
        isFullScreen = value;
    }

    public void setResizable(@NotNull Boolean value) {
        isResizable = value;
    }

    public void setUndecorated(@NotNull Boolean value) {
        isUndecorated = value;
    }

    @Override
    protected void pushSettings() {
        super.pushSettings();
        preferences.putInteger(WINDOW_HEIGHT_KEY, screenHeight);
        preferences.putInteger(WINDOW_WIDTH_KEY, screenWidth);
        preferences.putBoolean(IS_RESIZABLE_KEY, isResizable);
        preferences.putBoolean(IS_FULL_SCREEN_KEY, isFullScreen);
        preferences.putBoolean(IS_UNDECORATED_KEY, isUndecorated);
    }

    @Override
    protected void pullSettings() {
        super.pullSettings();
        screenHeight = preferences.getInteger(WINDOW_HEIGHT_KEY, DEFAULT_WINDOW_HEIGHT);
        screenWidth = preferences.getInteger(WINDOW_WIDTH_KEY, DEFAULT_WINDOW_WIDTH);
        isFullScreen = preferences.getBoolean(IS_FULL_SCREEN_KEY, DEFAULT_IS_FULL_SCREEN);
        isResizable = preferences.getBoolean(IS_RESIZABLE_KEY, DEFAULT_IS_RESIZABLE);
        isUndecorated = preferences.getBoolean(IS_UNDECORATED_KEY, DEFAULT_IS_UNDECORATED);
    }
}

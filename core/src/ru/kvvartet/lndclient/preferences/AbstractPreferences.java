package ru.kvvartet.lndclient.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class AbstractPreferences {
    public static final String APP_NAME = "Lands and Dungeons";

    public static final String SOUND_ENABLED_KEY = "soundEnabled";
    public static final String MUSIC_ENABLED_KEY = "musicEnabled";
    public static final String SOUND_VOLUME_KEY = "soundVolume";
    public static final String MUSIC_VOLUME_KEY = "musicVolume";

    public static final String VSYNC_ENABLED_KEY = "vsyncEnabled";
    public static final String HDPI_ENABLED_KEY = "hdpiEnabled";

    public static final String LOGIN_SAVING_ENABLED_KEY = "saveLogin";
    public static final String SAVED_LOGIN_KEY = "lastUsedLogin";

    public static final Float MIN_VOLUME = 0.0f;
    public static final Float MAX_VOLUME = 1.0f;

    private static final Boolean DEFAULT_SOUND_ENABLED = true;
    private static final Boolean DEFAULT_MUSIC_ENABLED = true;
    private static final Float DEFAULT_MUSIC_VOLUME = 0.5f;
    private static final Float DEFAULT_SOUND_VOLUME = 0.5f;

    private static final Boolean DEFAULT_VSYNC_ENABLED = true;
    private static final Boolean DEFAULT_HDPI_ENABLED = true;

    private static final Boolean DEFAULT_LOGIN_SAVING_POLICY = false;
    private static final String DEFAULT_SAVED_LOGIN = "";

    protected Boolean soundEnabled;
    protected Boolean musicEnabled;
    protected Float soundVolume;
    protected Float musicVolume;

    protected Boolean vsyncEnabled;
    protected Boolean hdpiEnabled;

    protected Boolean loginSavingPolicy;
    protected String lastUsedLogin;

    protected Preferences preferences;

    protected AbstractPreferences(@NotNull String preferencesFile) {
        preferences = Gdx.app.getPreferences(preferencesFile);
        if (preferences.get().isEmpty()) {
            fill();
        } else {
            load();
        }
    }

    public @NotNull Boolean isSoundEnabled() {
        return soundEnabled;
    }

    public @NotNull Boolean isMusicEnabled() {
        return musicEnabled;
    }

    public @NotNull Float getSoundVolume() {
        return soundVolume;
    }

    public @NotNull Float getMusicVolume() {
        return musicVolume;
    }

    public @NotNull Boolean isVsyncEnabled() {
        return vsyncEnabled;
    }

    public @NotNull Boolean isHdpiEnabled() {
        return hdpiEnabled;
    }

    public @NotNull Boolean isLoginSavingEnabled() {
        return loginSavingPolicy;
    }

    public @NotNull String getLastUsedLogin() {
        return lastUsedLogin;
    }

    public void setSoundEnabled(@NotNull Boolean value) {
        soundEnabled = value;
    }

    public void setMusicEnabled(@NotNull Boolean value) {
        musicEnabled = value;
    }

    public void setSoundVolume(@NotNull Float value) {
        soundVolume = value;
    }

    public void setMusicVolume(@NotNull Float value) {
        musicVolume = value;
    }

    public void setVsyncEnabled(@NotNull Boolean value) {
        vsyncEnabled = value;
    }

    public void setHdpiEnabled(@NotNull Boolean value) {
        hdpiEnabled = value;
    }

    public void setLoginSavingPolicy(@NotNull Boolean value) {
        loginSavingPolicy = value;
    }

    public void setLastUsedLogin(@NotNull String value) {
        lastUsedLogin = value;
    }

    public void save() {
        pushSettings();
        preferences.flush();
    }

    public void load() {
        pullSettings();
    }

    protected void pushSettings() {
        preferences.putBoolean(SOUND_ENABLED_KEY, soundEnabled);
        preferences.putBoolean(MUSIC_ENABLED_KEY, musicEnabled);
        preferences.putFloat(SOUND_VOLUME_KEY, soundVolume);
        preferences.putFloat(MUSIC_VOLUME_KEY, musicVolume);
        preferences.putBoolean(VSYNC_ENABLED_KEY, vsyncEnabled);
        preferences.putBoolean(HDPI_ENABLED_KEY, DEFAULT_HDPI_ENABLED);
        preferences.putBoolean(LOGIN_SAVING_ENABLED_KEY, loginSavingPolicy);
        preferences.putString(SAVED_LOGIN_KEY, lastUsedLogin);
    }

    protected void pullSettings() {
        soundEnabled = preferences.getBoolean(SOUND_ENABLED_KEY, DEFAULT_SOUND_ENABLED);
        musicEnabled = preferences.getBoolean(MUSIC_ENABLED_KEY, DEFAULT_MUSIC_ENABLED);
        soundVolume = preferences.getFloat(SOUND_VOLUME_KEY, DEFAULT_SOUND_VOLUME);
        musicVolume = preferences.getFloat(MUSIC_VOLUME_KEY, DEFAULT_MUSIC_VOLUME);
        vsyncEnabled = preferences.getBoolean(VSYNC_ENABLED_KEY, DEFAULT_VSYNC_ENABLED);
        hdpiEnabled = preferences.getBoolean(HDPI_ENABLED_KEY, DEFAULT_HDPI_ENABLED);
        loginSavingPolicy = preferences.getBoolean(LOGIN_SAVING_ENABLED_KEY, DEFAULT_LOGIN_SAVING_POLICY);
        lastUsedLogin = preferences.getString(SAVED_LOGIN_KEY, DEFAULT_SAVED_LOGIN);
    }

    private void fill() {
        load();
        save();
    }
}

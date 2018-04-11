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

    private static final Float MIN_VOLUME = 0.0f;
    private static final Float MAX_VOLUME = 1.0f;

    private static final Boolean DEFAULT_SOUND_ENABLED = true;
    private static final Boolean DEFAULT_MUSIC_ENABLED = true;
    private static final Float DEFAULT_MUSIC_VOLUME = 0.5f;
    private static final Float DEFAULT_SOUND_VOLUME = 0.5f;

    private static final Boolean DEFAULT_VSYNC_ENABLED = true;
    private static final Boolean DEFAULT_HDPI_ENABLED = true;

    protected Boolean soundEnabled;
    protected Boolean musicEnabled;
    protected Float soundVolume;
    protected Float musicVolume;

    protected Boolean vsyncEnabled;
    protected Boolean hdpiEnabled;

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
    }

    protected void pullSettings() {
        soundEnabled = preferences.getBoolean(SOUND_ENABLED_KEY, DEFAULT_SOUND_ENABLED);
        musicEnabled = preferences.getBoolean(MUSIC_ENABLED_KEY, DEFAULT_MUSIC_ENABLED);
        soundVolume = preferences.getFloat(SOUND_VOLUME_KEY, DEFAULT_SOUND_VOLUME);
        musicVolume = preferences.getFloat(MUSIC_VOLUME_KEY, DEFAULT_MUSIC_VOLUME);
        vsyncEnabled = preferences.getBoolean(VSYNC_ENABLED_KEY, DEFAULT_VSYNC_ENABLED);
        hdpiEnabled = preferences.getBoolean(HDPI_ENABLED_KEY, DEFAULT_HDPI_ENABLED);
    }

    private void fill() {
        load();
        save();
    }
}

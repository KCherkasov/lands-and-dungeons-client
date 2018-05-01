package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.configs.SgxUiSkinKeys;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.SettingsStateKeys;
import ru.kvvartet.lndclient.preferences.AbstractPreferences;

import java.util.Objects;

public abstract class AbstractSettingsState extends AbstractState {
    private static final Float PERCENT_STEP_SIZE = 0.01f;
    protected static final  String EMPTY_LABEL = "";

    private static final String GRAPHICS_LABEL = "Video";
    private static final String AUDIO_LABEL = "Audio";
    private static final String TITLE_LABEL = "Settings";

    protected static final Float MINOR_TITLE_SCALE = 0.75f;

    protected static final Integer TITLE_COLSPAN = 2;

    private static final String SAVE_BUTTON_LABEL = "Apply";
    private static final String CANCEL_BUTTON_LABEL = "Cancel";
    private static final String BACK_BUTTON_LABEL = "Back";

    private static final String VSYNC_LABEL = "Verticl synchronization: ";
    private static final String SOUND_ENABLED_LABEL = "Sound enabled: ";
    private static final String MUSIC_ENABLED_LABEL = "Music enabled";

    private static final String SOUND_VOLUME_LABEL = "Sound volume: ";
    private static final String MUSIC_VOLUME_LABEL = "Music volume: ";

    private CheckBox enableSound = null;
    private CheckBox enableMusic = null;
    private Slider soundVolume = null;
    private Slider musicVolume = null;

    private CheckBox enableVsync = null;

    protected AbstractPreferences settings = null;

    protected AbstractSettingsState(@NotNull StateManager stateManager) {
        super(stateManager);
        loadSettings();
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return true;
    }

    @Override
    public void setupStage() {
        final Skin menuTheme = Objects.requireNonNull(ClientAssetManagerImpl.getInstance()
                .getSkinAssets()).getAsset(SettingsStateKeys.SKIN_KEY);
        if (menuTheme != null) {
            final Table settingsWindow = new Table();
            settingsWindow.setFillParent(true);
            settingsWindow.setOrigin(Align.center);
            final Table label = new Table();
            label.add(new Label(TITLE_LABEL, menuTheme, SgxUiSkinKeys.LABEL_TITLE_WHITE)).center();
            settingsWindow.add(label).fill().center();
            settingsWindow.row().fillX().center();
            settingsWindow.add(addAudioOptions(menuTheme)).fill();
            settingsWindow.row().fillX().center();
            settingsWindow.add(addVideoOptions(menuTheme)).fill();
            settingsWindow.row().fillX().center();
            settingsWindow.add(configureButtons(menuTheme)).fillX().center();
            addActor(settingsWindow);
        } else {
            error(SKIN_MISSING + SettingsStateKeys.SKIN_KEY);
            stateManager.requestStatePop();
        }
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        super.act(timeDelta);
        super.draw();
    }

    protected abstract void loadSettings();

    private void saveSettings() {
        flushFromControls();
        settings.save();
        applyChanges();
        clear();
        setupStage();
    }

    private void cancelChanges() {
        resetControls();
    }

    private @NotNull Table addVideoOptions(@NotNull Skin theme) {
        final Table graphicsTable = new Table();
        makeTitleLabel(GRAPHICS_LABEL, theme, SgxUiSkinKeys.LABEL_TITLE_WHITE, graphicsTable);
        enableVsync = new CheckBox(EMPTY_LABEL, theme, SgxUiSkinKeys.CHECKBOX_SWITCH);
        enableVsync.setChecked(settings.isVsyncEnabled());
        addCheckbox(VSYNC_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT, graphicsTable, enableVsync);
        graphicsTable.row().center().fillX();
        addExtraOptions(graphicsTable, theme);
        return graphicsTable;
    }

    protected abstract void addExtraOptions(@NotNull Table root, @NotNull Skin theme);

    private @NotNull Table addAudioOptions(@NotNull Skin theme) {
        final Table audioTable = new Table();
        makeTitleLabel(AUDIO_LABEL, theme, SgxUiSkinKeys.LABEL_TITLE_WHITE, audioTable);
        enableSound = new CheckBox(EMPTY_LABEL, theme, SgxUiSkinKeys.CHECKBOX_SWITCH);
        enableSound.setChecked(settings.isSoundEnabled());
        addCheckbox(SOUND_ENABLED_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT, audioTable, enableSound);

        audioTable.row().center().fillX();
        audioTable.add(new Label(SOUND_VOLUME_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT)).left();
        soundVolume = new Slider(AbstractPreferences.MIN_VOLUME, AbstractPreferences.MAX_VOLUME,
                PERCENT_STEP_SIZE, false, theme, SgxUiSkinKeys.SLIDER_HORIZONTAL_DEFAULT);
        soundVolume.setValue(settings.getSoundVolume());
        audioTable.add(soundVolume).fillX();

        audioTable.row().center().fillX();
        enableMusic = new CheckBox(EMPTY_LABEL, theme, SgxUiSkinKeys.CHECKBOX_SWITCH);
        enableMusic.setChecked(settings.isMusicEnabled());
        addCheckbox(MUSIC_ENABLED_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT, audioTable, enableMusic);

        audioTable.row().center().fillX();
        audioTable.add(new Label(MUSIC_VOLUME_LABEL, theme, SgxUiSkinKeys.LABEL_DEFAULT)).left();
        musicVolume = new Slider(AbstractPreferences.MIN_VOLUME, AbstractPreferences.MAX_VOLUME,
                PERCENT_STEP_SIZE, false, theme, SgxUiSkinKeys.SLIDER_HORIZONTAL_DEFAULT);
        musicVolume.setValue(settings.getMusicVolume());
        audioTable.add(musicVolume).fillX();
        return audioTable;
    }

    private @NotNull Table configureButtons(@NotNull Skin theme) {
        final Table buttonsRoot = new Table();
        final TextButton saveButton = makeMenuButton(SAVE_BUTTON_LABEL, theme);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveSettings();
            }
        });
        buttonsRoot.add(saveButton);
        final TextButton cancelButton = makeMenuButton(CANCEL_BUTTON_LABEL, theme);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                cancelChanges();
            }
        });
        buttonsRoot.add(cancelButton);
        final TextButton backButton = makeMenuButton(BACK_BUTTON_LABEL, theme);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateManager.requestStatePop();
            }
        });
        buttonsRoot.add(backButton);
        return buttonsRoot;
    }

    protected void flushFromControls() {
        settings.setVsyncEnabled(enableVsync.isChecked());

        settings.setSoundEnabled(enableSound.isChecked());
        settings.setSoundVolume(soundVolume.getValue());

        settings.setMusicEnabled(enableMusic.isChecked());
        settings.setMusicVolume(musicVolume.getValue());
    }

    protected void makeTitleLabel(@NotNull String titleText, @NotNull Skin skin,
                                  @NotNull String style, @NotNull Table target) {
        final Label title = new Label(titleText, skin, style);
        title.setFontScale(MINOR_TITLE_SCALE);
        target.add(title).colspan(TITLE_COLSPAN).center();
        target.row().center().fillX();
    }

    protected void resetControls() {
        enableVsync.setChecked(settings.isVsyncEnabled());
        enableMusic.setChecked(settings.isMusicEnabled());
        musicVolume.setValue(settings.getMusicVolume());
        enableSound.setChecked(settings.isSoundEnabled());
        soundVolume.setValue(settings.getSoundVolume());
    }

    protected void applyChanges() {
        Gdx.graphics.setVSync(settings.isVsyncEnabled());
    }

    protected void addCheckbox(@NotNull String label, @NotNull Skin theme,
                               @NotNull String style, @NotNull Table target,
                               @NotNull CheckBox checkBox) {
        target.add(new Label(label, theme, style)).left();
        target.add(checkBox);
    }
}

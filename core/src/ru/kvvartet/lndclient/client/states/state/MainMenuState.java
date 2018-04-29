package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureAtlasAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.MainMenuStateKeys;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;

import java.util.Objects;

public class MainMenuState extends AbstractState {
    private static final String PLAY_BUTTON_CAPTION = "Play";
    private static final String SETTINGS_BUTTON_CAPTION = "Settings";
    private static final String ABOUT_BUTTON_CAPTION = "Authors";
    private static final String EXIT_BUTTON_CAPTION = "Exit";

    private static final String BUTTON_PRESSED_LOG_MESSAGE = "Main menu button ";
    private static final String PRESSED_LOG_MESSAGE = " pressed";

    private static final Integer PADDING = 3;

    public MainMenuState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return false;
    }

    @Override
    public void setupStage() {
        final Table layoutRoot = new Table();
        layoutRoot.setFillParent(true);
        layoutRoot.defaults().expand().fill();
        layoutRoot.top().center();
        addActor(layoutRoot);

        if (ClientAssetManagerImpl.getInstance().isConfigured()) {
            final TextureAtlasAssetHolder textureAtlasAssetHolder =
                    ClientAssetManagerImpl.getInstance().getTextureAtlasAssets();
            if (textureAtlasAssetHolder == null) {
                error(TEXTURE_ATLAS_HOLDER_MISSING);
                return;
            }
            final TextureAtlas guiAtlas = textureAtlasAssetHolder.getAsset(TextureAtlasesKeys.GUI_ATLAS_KEY);
            if (guiAtlas == null) {
                error(TEXTURE_ATLAS_MISSING + TextureAtlasesKeys.GUI_ATLAS_KEY);
                return;
            }
            final TextureRegion logoTexture = guiAtlas.findRegion(TextureAtlasesKeys.GUI_LOGO_KEY);
            final Image logoImage = new Image(logoTexture);
            logoImage.setOrigin(Align.center);
            logoImage.setScaling(Scaling.fillX);
            layoutRoot.add(logoImage).top().center();

            final TextureRegion bannerTexture = guiAtlas.findRegion(TextureAtlasesKeys.GUI_BANNER_KEY);
            final Image bannerImage = new Image(bannerTexture);
            bannerImage.setOrigin(Align.center);
            bannerImage.setScaling(Scaling.fillY);
            bannerImage.setAlign(Align.center);
            final Stack buttonsWithBackground = new Stack();
            buttonsWithBackground.setOrigin(Align.center);
            buttonsWithBackground.add(bannerImage);
            buttonsWithBackground.add(new Table());
            buttonsWithBackground.getChildren().get(1).setWidth(
                    bannerImage.getWidth() * bannerImage.getScaleX());
            fillMenuWidgetWithButtons(((Table)buttonsWithBackground.getChildren().get(1)));
            layoutRoot.row();
            layoutRoot.add(buttonsWithBackground).center().top();
        } else {
            error(ASSET_MANAGER_NOT_CONFIGURED);
        }
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        super.act();
        super.draw();
    }

    private void fillMenuWidgetWithButtons(@NotNull Table target) {
        target.setOrigin(Align.center);
        target.setFillParent(true);

        final Skin skin = Objects.requireNonNull(ClientAssetManagerImpl.getInstance()
                .getSkinAssets()).getAsset(MainMenuStateKeys.SKIN_KEY);

        if (skin != null) {
            final TextButton playButton = makeMenuButton(PLAY_BUTTON_CAPTION, skin);
            playButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (actor.getClass().equals(TextButton.class)) {
                        defaultOnClickButtonCallback(
                                ((TextButton) actor).getLabel().getText().toString());
                    }
                }
            });
            playButton.setOrigin(Align.center);
            target.add(playButton).center().fill().padLeft(PADDING).padRight(PADDING);
            target.row().fillX();

            final TextButton settingsButton = makeMenuButton(SETTINGS_BUTTON_CAPTION, skin);
            settingsButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (actor.getClass().equals(TextButton.class)) {
                        defaultOnClickButtonCallback(
                                ((TextButton) actor).getLabel().getText().toString());
                        settingsButtonOnClickCallback();
                    }
                }
            });
            settingsButton.setOrigin(Align.center);
            target.add(settingsButton).center().fill().padLeft(PADDING).padRight(PADDING);
            target.row().fillX();

            final TextButton aboutButton = makeMenuButton(ABOUT_BUTTON_CAPTION, skin);
            aboutButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (actor.getClass().equals(TextButton.class)) {
                        defaultOnClickButtonCallback(
                                ((TextButton) actor).getLabel().getText().toString());
                        aboutButtonOnClickCallback();
                    }
                }
            });
            aboutButton.setOrigin(Align.center);
            target.add(aboutButton).center().fill().padLeft(PADDING).padRight(PADDING);
            target.row().fillX();

            final TextButton exitButton = makeMenuButton(EXIT_BUTTON_CAPTION, skin);
            exitButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    defaultOnClickButtonCallback(
                            ((TextButton) actor).getLabel().getText().toString());
                    exitButtonOnClickCallback();
                }
            });
            exitButton.setOrigin(Align.center);
            target.add(exitButton).center().fill().padLeft(PADDING).padRight(PADDING);
        } else {
            Gdx.app.error(getClass().getName(), SKIN_MISSING + MainMenuStateKeys.SKIN_KEY);
            Gdx.app.exit();
        }
    }

    private void defaultOnClickButtonCallback(@NotNull String name) {
        Gdx.app.log(getClass().getSimpleName(), BUTTON_PRESSED_LOG_MESSAGE + name + PRESSED_LOG_MESSAGE);
    }

    private void exitButtonOnClickCallback() {
        Gdx.app.exit();
    }

    private void aboutButtonOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new AboutState(stateManager));
    }

    private void settingsButtonOnClickCallback() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            stateManager.requestStatePush(new DesktopSettingsState(stateManager));
        }
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            stateManager.requestStatePush(new AndroidSettingsState(stateManager));
        }
    }
}

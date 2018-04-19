package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
                Gdx.app.error(getClass().getName(), "TextureAtlas holder isn't initialized");
                return;
            }
            final TextureAtlas guiAtlas = textureAtlasAssetHolder.getAsset(TextureAtlasesKeys.GUI_ATLAS_KEY);
            if (guiAtlas == null) {
                Gdx.app.error(getClass().getName(),
                        "TextureAtlas " + TextureAtlasesKeys.GUI_ATLAS_KEY + " doesn\'t exist");
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
            Gdx.app.error(getClass().getName(), "Asset manager isn't loaded");
        }
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        super.act();
        super.draw();
    }

    private @NotNull TextButton makeMenuButton(@NotNull String caption){
        return new TextButton(caption, Objects.requireNonNull(Objects.requireNonNull(
                ClientAssetManagerImpl.getInstance().getSkinAssets()).getAsset(MainMenuStateKeys.SKIN_KEY)));
    }

    private void fillMenuWidgetWithButtons(@NotNull Table target) {
        // TODO: change on-click listeners on buttons as new states will be added
        target.setOrigin(Align.center);
        target.setFillParent(true);

        final TextButton playButton = makeMenuButton(PLAY_BUTTON_CAPTION);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getClass().equals(TextButton.class)) {
                    defaultOnClickButtonCallback(
                            ((TextButton)actor).getLabel().getText().toString());
                }
            }
        });
        playButton.setOrigin(Align.center);
        target.add(playButton).center().fill().padLeft(PADDING).padRight(PADDING);
        target.row().fillX();

        final TextButton settingsButton = makeMenuButton(SETTINGS_BUTTON_CAPTION);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getClass().equals(TextButton.class)) {
                    defaultOnClickButtonCallback(
                            ((TextButton)actor).getLabel().getText().toString());
                }
            }
        });
        settingsButton.setOrigin(Align.center);
        target.add(settingsButton).center().fill().padLeft(PADDING).padRight(PADDING);
        target.row().fillX();

        final TextButton aboutButton = makeMenuButton(ABOUT_BUTTON_CAPTION);
        aboutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor.getClass().equals(TextButton.class)) {
                    defaultOnClickButtonCallback(
                            ((TextButton)actor).getLabel().getText().toString());
                }
            }
        });
        aboutButton.setOrigin(Align.center);
        target.add(aboutButton).center().fill().padLeft(PADDING).padRight(PADDING);
        target.row().fillX();

        final TextButton exitButton = makeMenuButton(EXIT_BUTTON_CAPTION);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                defaultOnClickButtonCallback(
                        ((TextButton)actor).getLabel().getText().toString());
                exitButtonOnClickCallback();
            }
        });
        exitButton.setOrigin(Align.center);
        target.add(exitButton).center().fill().padLeft(PADDING).padRight(PADDING);
    }

    private void defaultOnClickButtonCallback(@NotNull String name) {
        Gdx.app.log(getClass().getSimpleName(), "Main menu button " + name + " pressed");
    }

    private void exitButtonOnClickCallback() {
        Gdx.app.exit();
    }
}

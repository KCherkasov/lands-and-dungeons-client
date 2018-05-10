package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.AuthorizationStateKeys;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;
import ru.kvvartet.lndclient.preferences.AbstractPreferences;
import ru.kvvartet.lndclient.preferences.AndroidPreferences;
import ru.kvvartet.lndclient.preferences.DesktopPreferences;

public abstract class AbstractAuthorizationState extends AbstractState implements Net.HttpResponseListener {
    protected static final String EMPTY_FILLER = "";


    protected AbstractAuthorizationState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return true;
    }

    @Override
    public void setupStage() {
        if (ClientAssetManagerImpl.getInstance().isConfigured()) {
            final Skin theme = Objects.requireNonNull(ClientAssetManagerImpl.getInstance()
                    .getSkinAssets()).getAsset(AuthorizationStateKeys.SKIN_KEY);
            if (theme != null) {
                final Table layoutRoot = new Table();
                final Stack formWithBackground = new Stack();
                formWithBackground.add(getBanner());
                final Table formRoot = new Table();
                layoutRoot.setFillParent(true);
                formRoot.add(makeInputArea(theme)).fill().row();
                formRoot.add(makeControls(theme)).fillX();
                formWithBackground.add(formRoot);
                layoutRoot.add(formWithBackground).fill();
                addActor(layoutRoot);
            } else {
                logErrorAndLeave(SKIN_MISSING + AuthorizationStateKeys.SKIN_KEY);
            }
        } else {
            logErrorAndLeave(ASSET_MANAGER_NOT_CONFIGURED);
        }
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        processMessages();
        super.act(timeDelta);
        super.draw();
    }

    private @NotNull Image getBanner() {
        final TextureAtlas guiAtlas = Objects.requireNonNull(ClientAssetManagerImpl.getInstance()
                .getTextureAtlasAssets()).getAsset(TextureAtlasesKeys.GUI_ATLAS_KEY);
        final TextureRegion bannerTexture = Objects.requireNonNull(guiAtlas)
                .findRegion(TextureAtlasesKeys.GUI_BANNER_KEY);
        final Image banner = new Image(bannerTexture);
        banner.setOrigin(Align.center);
        banner.setScaling(Scaling.fillY);
        return banner;
    }

    protected abstract @NotNull Table makeControls(@NotNull Skin theme);

    protected abstract  @NotNull Table makeInputArea(@NotNull Skin theme);

    protected abstract void processMessages();

    protected abstract String validateInput();

    protected void logErrorAndLeave(@NotNull String error) {
        error(error);
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }

    protected @NotNull TextButton makeButton(@NotNull String label, @NotNull Skin theme,
                                             @NotNull EventListener listener) {
        final TextButton button = new TextButton(label, theme);
        button.addListener(listener);
        return button;
    }

    protected @NotNull AbstractPreferences getSettings() {
        return Gdx.app.getType() == Application.ApplicationType.Android
                ? AndroidPreferences.getInstance() : DesktopPreferences.getInstance();
    }
}

package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import org.jetbrains.annotations.NotNull;

import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureAtlasAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.IntroStateKeys;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

public class IntroState extends AbstractState {
    private static final String PRESS_ANY_KEY = "Press any key";

    public IntroState(@NotNull StateManager stateManager) {
        super(stateManager);
        stateManager.getNetworkManager().initSocket();
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return false;
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        super.act(timeDelta);
        super.draw();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void setupStage() {

        final Table layoutRoot = new Table();
        layoutRoot.setFillParent(true);
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
            layoutRoot.add(logoImage).center().bottom();

            final Label.LabelStyle style = new Label.LabelStyle();
            final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerImpl.getInstance().getFontAssets();
            if (fontAssetHolder == null) {
                error(BITMAP_FONT_ASSET_HOLDER_MISSING);
                return;
            }
            final BitmapFont font = fontAssetHolder.getAsset(
                    IntroStateKeys.FONT_KEY + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF);
            if (font == null) {
                error(BITMAP_FONT_MISSING + IntroStateKeys.FONT_KEY
                        + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF);
                return;
            }
            style.font = font;

            final Label label = new Label(PRESS_ANY_KEY, style);
            label.setOrigin(Align.center);

            if (layoutRoot.getChildren().size > 0) {
                layoutRoot.row().expandX();
                layoutRoot.add(label).center().top();
            }

        } else {
            error(ASSET_MANAGER_NOT_CONFIGURED);
        }

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                disappearOnClick();
                return true;
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                disappearOnClick();
                return true;
            }

            @Override
            public boolean keyTyped(InputEvent event, char character) {
                disappearOnClick();
                return true;
            }
        });

    }

    private void disappearOnClick() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }
}

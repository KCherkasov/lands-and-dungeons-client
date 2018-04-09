package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
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
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerClassImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.IntroStateKeys;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

public class IntroState extends AbstractState {
    public IntroState(@NotNull StateManager stateManager) {
        super(stateManager);
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
    public void setupStage() {
        final Table layoutRoot = new Table();
        layoutRoot.setFillParent(true);
        addActor(layoutRoot);

        if (ClientAssetManagerClassImpl.getInstance().isConfigured()) {
            final TextureAtlasAssetHolder textureAtlasAssetHolder =
                    ClientAssetManagerClassImpl.getInstance().getTextureAtlasAssets();
            if (textureAtlasAssetHolder == null) {
                Gdx.app.error(getClass().getName(), "TextureAsset holder is not initialized");
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
            layoutRoot.add(logoImage).center().bottom();

            final Label.LabelStyle style = new Label.LabelStyle();
            final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerClassImpl.getInstance().getFontAssets();
            if (fontAssetHolder == null) {
                Gdx.app.error(getClass().getName(), "BitmapFont holder isn\'t initialized");
                return;
            }
            final BitmapFont font = fontAssetHolder.getAsset(
                    IntroStateKeys.FONT_KEY + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF);
            if (font == null) {
                Gdx.app.error(getClass().getName(),
                        "BitmapFont " + IntroStateKeys.FONT_KEY
                                + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF + " doesn\'t exist");
                return;
            }
            style.font = font;

            final Label label = new Label("Press any key", style);
            label.setOrigin(Align.center);

            if (layoutRoot.getChildren().size > 0) {
                layoutRoot.row().expandX();
                layoutRoot.add(label).center().top();
            }
        } else {
            Gdx.app.error(getClass().getName(), "Asset manager isn\'t configured");
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

package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.graphics.TextureAtlasAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;

public class MainMenuState extends AbstractState {
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

            layoutRoot.row().expandX().center();
            final TextureRegion bannerTexture = guiAtlas.findRegion(TextureAtlasesKeys.GUI_BANNER_KEY);
            final Image bannerImage = new Image(bannerTexture);
            bannerImage.setOrigin(Align.center);
            bannerImage.setScaling(Scaling.fillY);
            layoutRoot.add(bannerImage).center().top();
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
}

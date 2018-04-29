package ru.kvvartet.lndclient.client.states.state;

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
import ru.kvvartet.lndclient.assetconfigparser.ConfigParser;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.AboutStateKeys;
import ru.kvvartet.lndclient.client.states.state.configkeys.TextureAtlasesKeys;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.Map;
import java.util.Objects;

public class AboutState extends AbstractState {
    private static final String PRESS_ANY_KEY = "Press any key to return to main menu";
    private static final Character SPACE_CHAR = ' ';
    private static final Integer TITLE_COLSPAN = 2;

    public AboutState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return true;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void setupStage() {
        final Map<String, Map<String, String>> text = new ConfigParser().parseConfig(AboutStateKeys.TITLES_TEXT_SOURCE);
        final Table layoutRoot = new Table();
        layoutRoot.setFillParent(true);
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
        addActor(layoutRoot);
        if (ClientAssetManagerImpl.getInstance().isConfigured()) {
            final TextureAtlas guiAtlas = Objects.requireNonNull(ClientAssetManagerImpl.getInstance()
                    .getTextureAtlasAssets()).getAsset(TextureAtlasesKeys.GUI_ATLAS_KEY);
            if (guiAtlas != null) {
                final TextureRegion logoTexture
                        = guiAtlas.findRegion(TextureAtlasesKeys.GUI_LOGO_KEY);
                final Image logoImage = new Image(logoTexture);
                logoImage.setOrigin(Align.center);
                logoImage.setScaling(Scaling.fillX);
                layoutRoot.add(logoImage).center().expandX();
                layoutRoot.row().expandX().top();
            } else {
                error(TEXTURE_ATLAS_MISSING + TextureAtlasesKeys.GUI_ATLAS_KEY);
            }
            final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerImpl.getInstance().getFontAssets();
            if (fontAssetHolder != null) {
                final Label.LabelStyle titleStyle = new Label.LabelStyle();
                titleStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                        AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_CAPTION.asText()
                                + Extensions.TTF));
                final Label.LabelStyle textStyle = new Label.LabelStyle();
                textStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                        AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_BIG.asText()
                                + Extensions.TTF));
                if (text != null) {
                    final Map<String, String> gameTitleData = text.get(
                            AboutStateKeys.GAME_TITLE_MAP_KEY);
                    final Label gameTitle = new Label(
                            gameTitleData.get(AboutStateKeys.GAME_NAME_KEY) + SPACE_CHAR
                                    + gameTitleData.get(AboutStateKeys.GAME_VERSION_KEY), titleStyle);
                    layoutRoot.add(gameTitle).center().expandX();
                    layoutRoot.row().expandX().top();
                    final Table titles = new Table();
                    fillTitleBlock(titles, text, AboutStateKeys.TEAM_MEMBERS_MAP_KEY,
                            AboutStateKeys.TEAM_MEMBERS_TITLE_KEY, titleStyle, textStyle);
                    fillTitleBlock(titles, text, AboutStateKeys.RESOURCES_MAP_KEY,
                            AboutStateKeys.RESOURCES_TITLE_KEY, titleStyle, textStyle);
                    layoutRoot.add(titles).fill();
                    layoutRoot.row().expandX().bottom();
                    layoutRoot.add(new Label(PRESS_ANY_KEY, textStyle));
                } else {
                    error(CONFIG_NOT_PARSED + AboutStateKeys.TITLES_TEXT_SOURCE);
                }
            } else {
                error(BITMAP_FONT_ASSET_HOLDER_MISSING);
            }
        } else {
            error(ASSET_MANAGER_NOT_CONFIGURED);
        }
    }

    @Override
    public void render(float timeDelta) {
        clearScreen();
        super.act(timeDelta);
        super.draw();
    }

    private void disappearOnClick() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }

    private void fillTitles(@NotNull Table root, @NotNull Map<String, String> data,
                            @NotNull String prohibitedKey, @NotNull Label.LabelStyle style) {
        for (String key : data.keySet()) {
            if (!key.equals(prohibitedKey)) {
                final Label position = new Label(key, style);
                root.add(position).left();
                final Label value = new Label(data.get(key), style);
                root.add(value).right();
                root.row().fillX().top();
            }
        }
    }

    private void fillTitleBlock(@NotNull Table root, @NotNull Map<String, Map<String, String>> dataSource,
                                @NotNull String blockKey, @NotNull String titleKey,
                                @NotNull Label.LabelStyle titleStyle, @NotNull Label.LabelStyle textStyle) {
        final Map<String, String> dataSet = dataSource.get(blockKey);
        final Label titleLabel = new Label(dataSet.get(titleKey), titleStyle);
        root.add(titleLabel).center().colspan(TITLE_COLSPAN);
        root.row().fillX().top();
        fillTitles(root, dataSet, titleKey, textStyle);
    }
}

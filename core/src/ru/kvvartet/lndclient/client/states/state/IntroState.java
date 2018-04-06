package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assetconfigparser.ConfigParser;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.IntroStateKeys;
import ru.kvvartet.lndclient.util.BitmapFontGenerator;
import ru.kvvartet.lndclient.util.GenericDefaults;

import java.util.Map;

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
        final Map<String, String> keys = new ConfigParser().parseConfig("core/assetconfig/IntroState.json");
        final Table layoutRoot = new Table();
        layoutRoot.setFillParent(true);
        addActor(layoutRoot);
        if (keys == null) {
            return;
        }

        if (keys.containsKey(IntroStateKeys.LOGO_KEY)) {
            final Texture logoTexture = new Texture(Gdx.files.internal(
                    keys.get(IntroStateKeys.LOGO_KEY)));
            final Image logoImage = new Image(logoTexture);
            logoImage.setOrigin(Align.center);
            logoImage.setScaling(Scaling.fillX);
            layoutRoot.add(logoImage).center().bottom();
        } else {
            System.err.println("error parsing logo asset path");
        }

        if (keys.containsKey(IntroStateKeys.FONT_KEY)) {
            final Label.LabelStyle style = new Label.LabelStyle();
            style.font = BitmapFontGenerator.generateFont(keys.get(IntroStateKeys.FONT_KEY),
                    GenericDefaults.CAPTION_FONT_SIZE, Color.WHITE);

            final Label label = new Label("Press any key", style);
            label.setOrigin(Align.center);

            if (layoutRoot.getChildren().size > 0) {
                layoutRoot.row().expandX();
                layoutRoot.add(label).center().top();
            }
        } else {
            System.err.println("error parsing font asset path");
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

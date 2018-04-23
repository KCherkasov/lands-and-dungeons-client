package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.manager.StateManager;
import ru.kvvartet.lndclient.client.states.state.configkeys.LoadingStateKeys;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.Objects;

public class LoadingScreen extends AbstractState {
    private static final String LOADING_STRING = "Loading ";
    private static final String PERCENT_TOKEN = " %";

    private static final Integer PERCENTAGE_MULTIPLIER = 100;

    private Label loadingLabel = null;
    private final Table root = new Table();

    public LoadingScreen(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public @NotNull Boolean isBlocking() {
        return true;
    }

    @Override
    public void setupStage() {
        addActor(root);
        root.setFillParent(true);
        ClientAssetManagerImpl.getInstance().preload();
        final Label.LabelStyle style = new Label.LabelStyle();
        style.font = Objects.requireNonNull(Objects.requireNonNull(ClientAssetManagerImpl.getInstance().getFontAssets())
                .getAsset(LoadingStateKeys.PRELOAD_FONT_KEY
                        + FontSizeSuffices.FS_CAPTION.asText() + Extensions.TTF));
        loadingLabel = new Label(LOADING_STRING, style);
        loadingLabel.setOrigin(Align.center);
        root.add(loadingLabel).center();
    }

    @Override
    public void render(float timeDelta) {
        if (ClientAssetManagerImpl.getInstance().update()) {
            ClientAssetManagerImpl.getInstance().configureHolders();
            stateManager.requestStatePop();
            stateManager.requestStatePush(new IntroState(stateManager));
        }

        loadingLabel.setText(LOADING_STRING + ClientAssetManagerImpl.getInstance().getAssetManager().getProgress()
                * PERCENTAGE_MULTIPLIER + PERCENT_TOKEN);
        clearScreen();
        super.act(timeDelta);
        super.draw();
    }
}

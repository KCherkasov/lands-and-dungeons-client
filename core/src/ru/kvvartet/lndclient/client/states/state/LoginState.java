package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.configs.SgxUiSkinKeys;
import ru.kvvartet.lndclient.client.states.manager.StateManager;

public class LoginState extends AbstractAuthorizationState {
    // TODO: send all prsf to .json file and add some keys for retrieving these to AuthorizationStateKeys
    private static final String SUBMIT_LABEL = "Login";
    private static final String SIGN_UP_LABEL = "Register";
    private static final String BACK_LABEL = "Back";

    private static final String SAVE_LOGIN_LABEL = "Remember login";

    private static final String PASSWORD_LABEL = "Password: ";
    private static final String LOGIN_LABEL = "Login: ";

    private static final String LOGIN_FILLER = "Enter your login...";
    private static final String PASSWORD_FILLER = "Enter your password";

    private CheckBox loginSavingPolicy;

    private TextField password;
    private TextField login;

    public LoginState(@NotNull StateManager stateManager) {
        super(stateManager);
    }

    @Override
    protected  @NotNull Table makeControls(@NotNull Skin theme) {
        final Table controlsRoot = new Table();

        final TextButton submit = makeButton(SUBMIT_LABEL, theme, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                submitOnClickCallback();
            }
        });
        controlsRoot.add(submit);

        final TextButton signUp = makeButton(SIGN_UP_LABEL, theme, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                signUpOnClickCallback();
            }
        });
        controlsRoot.add(signUp);

        final TextButton back = makeButton(BACK_LABEL, theme, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backOnClickCallback();
            }
        });
        controlsRoot.add(back);

        return controlsRoot;
    }

    @Override
    protected  @NotNull Table makeInputArea(@NotNull Skin theme) {
        final Table inputRoot = new Table();

        inputRoot.add(new Label(LOGIN_LABEL, theme)).left();
        login = new TextField(getSettings().getLastUsedLogin(), theme);
        login.setMessageText(LOGIN_FILLER);
        // TODO: set login max length there
        // login.setMaxLength(maxLength);
        inputRoot.add(login).fillX().row();
        inputRoot.add(new Label(PASSWORD_LABEL, theme)).left();
        password = new TextField(EMPTY_FILLER, theme);
        password.setMessageText(PASSWORD_FILLER);
        password.setPasswordMode(true);
        // TODO: set password max length there;
        // password.setMaxLength(maxLength);
        inputRoot.add(password).fillX().row();
        loginSavingPolicy = new CheckBox(EMPTY_FILLER, theme, SgxUiSkinKeys.CHECKBOX_SWITCH);
        loginSavingPolicy.setChecked(getSettings().isLoginSavingEnabled());
        inputRoot.add(new Label(SAVE_LOGIN_LABEL, theme)).left();
        inputRoot.add(loginSavingPolicy);
        return inputRoot;
    }

    @Override
    protected void processMessages() {
        // TODO: if login attempt was successful - go to ingame state, otherwise - tell what's wrong
    }

    private void submitOnClickCallback() {
        // TODO: login logic here

        // working with login saving (NOTE: we don't save passwords anyways)
        getSettings().setLoginSavingPolicy(loginSavingPolicy.isChecked());
        getSettings().setLastUsedLogin(getSettings().isLoginSavingEnabled()
                ? login.getText() : EMPTY_FILLER);
        getSettings().save();
    }

    private void signUpOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new SignUpState(stateManager));
    }

    private void backOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }
}

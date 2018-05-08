package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import org.jetbrains.annotations.NotNull;

import ru.kvvartet.lndclient.assets.configs.SgxUiSkinKeys;
import ru.kvvartet.lndclient.client.network.NetworkConfig;
import ru.kvvartet.lndclient.client.network.model.User;
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

    @Override
    protected String validateInput() {
        /*
         * login validation
         * */

        if(login.getText().length() < 6) {
            return "Login is too short";
        }

        /*
         * password validation
         * */

        if(password.getText().length() < 6) {
            return "Password is too short";
        }

        return null;
    }

    private void submitOnClickCallback() {
        if (validateInput() != null) {
            //TODO: Widget with error
            System.out.println("INPUT NOT VALID BECAUSE: " + validateInput());
        } else {
            final User user = new User(login.getText(), null, password.getText());
            signInRequest(user);

            // working with login saving (NOTE: we don't save passwords anyways)
            getSettings().setLoginSavingPolicy(loginSavingPolicy.isChecked());
            getSettings().save();
            getSettings().setLastUsedLogin(getSettings().isLoginSavingEnabled()
                    ? login.getText() : EMPTY_FILLER);
        }
    }

    private void signInRequest(User user) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                //.header("Cookie", prefs.getString("JSESSIONID", null))
                .header("Content-Type", "application/json")
                .url(NetworkConfig.READY_URL + NetworkConfig.SIGNIN_URI)
                .content(json.toJson(user))
                .includeCredentials(true)
                .build();
        System.out.println(json.toJson(user, User.class));
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SIGNIN_URI);
        System.out.println("SIGN IN SENDED");
        Gdx.net.sendHttpRequest(httpRequest, this);
    }

    private void signUpOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new SignUpState(stateManager));
    }

    private void backOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new MainMenuState(stateManager));
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        if(httpResponse.getStatus().getStatusCode() >= HttpStatus.SC_OK
                && httpResponse.getStatus().getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES) {
            //TODO: Widget with successful registration and after "ok" move to login screen
            System.out.println(httpResponse.getResultAsString());
        } else {
            //TODO: Widget with error response from server
            System.out.println(httpResponse.getResultAsString());
        }
    }

    @Override
    public void failed(Throwable t) {

    }

    @Override
    public void cancelled() {

    }
}

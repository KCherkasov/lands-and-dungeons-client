package ru.kvvartet.lndclient.client.states.state;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import org.jetbrains.annotations.NotNull;

import ru.kvvartet.lndclient.client.network.model.User;
import ru.kvvartet.lndclient.client.states.manager.StateManager;

public class SignUpState extends AbstractAuthorizationState {
    // TODO: send all prsf to .json file and add some keys for retrieving these to AuthorizationStateKeys
    private static final String SUBMIT_LABEL = "Register";
    private static final String LOGIN_LABEL = "Login";
    private static final String BACK_LABEL = "Back";

    private static final String EMAIL_LABEL = "Email: ";
    private static final String LOGIN_FIELD_LABEL = "Login: ";
    private static final String PASSWORD_LABEL = "Password: ";

    private static final String EMAIL_FILLER = "Enter your email address...";
    private static final String LOGIN_FILLER = "Enter your login...";
    private static final String PASSWORD_FILLER = "Enter your password";

    private TextField login;
    private TextField email;
    private TextField password;

    public SignUpState(@NotNull StateManager stateManager) {
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

        final TextButton signIn = makeButton(LOGIN_LABEL, theme, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                signInOnClickCallback();
            }
        });
        controlsRoot.add(signIn);

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

        inputRoot.add(new Label(LOGIN_FIELD_LABEL, theme)).left();
        login = new TextField(EMPTY_FILLER, theme);
        // TODO: set login max length there
        // login.setMaxLength(maxLength);
        login.setMessageText(LOGIN_FILLER);
        inputRoot.add(login).fillX().row();

        inputRoot.add(new Label(EMAIL_LABEL, theme)).left();
        email = new TextField(EMPTY_FILLER, theme);
        email.setMessageText(EMAIL_FILLER);
        inputRoot.add(email).fillX().row();

        inputRoot.add(new Label(PASSWORD_LABEL, theme)).left();
        password = new TextField(EMPTY_FILLER, theme);
        // TODO: set password max length there
        // password.setMaxLength(maxLength);
        password.setMessageText(PASSWORD_FILLER);
        password.setPasswordMode(true);
        inputRoot.add(password).fillX();

        return inputRoot;
    }

    @Override
    protected void processMessages() {
        // TODO: if we've received some positive response from server
        // go to login screen otherwise - show what's wrong
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
        * email validation
        * */

        if(email.getText().length() < 6) {
            return "Email is too short";
        }

        if(email.getText().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
            return "Email not valid";
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
            final User user = new User(login.getText(), email.getText(), password.getText());
            stateManager.getAuthorizationController().signUp(user, this);
            // login saving policy handling (NOTE: we save login, not email, by default, and never save password)
            if (getSettings().isLoginSavingEnabled()) {
                getSettings().setLastUsedLogin(login.getText());
            }
        }
    }


    private void signInOnClickCallback() {
        stateManager.requestStatePop();
        stateManager.requestStatePush(new LoginState(stateManager));
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

package ru.kvvartet.lndclient.preferences;

import org.jetbrains.annotations.NotNull;

public final class AuthorizationPreferences extends AbstractPreferences {
    private static final String PREFERENCES_FILENAME = "authorization.apf";

    public static final String SESSION_ID_KEY = "sessionId";
    public static final String LOGIN_KEY = "login";
    public static final String AUTHORIZATION_COOKIE_ID = "JSESSIONID";


    private static final String SESSION_ID_DEFAULT_VALUE = "";
    private static final String LOGIN_DEFAULT_VALUE = "";

    private static final AuthorizationPreferences INSTANCE = new AuthorizationPreferences();

    private String sessionId;
    private String login;

    public static @NotNull AuthorizationPreferences getInstance() {
        return INSTANCE;
    }

    private AuthorizationPreferences() {
        super(PREFERENCES_FILENAME);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    protected void pushSettings() {
        super.pushSettings();
        preferences.putString(SESSION_ID_KEY, sessionId);
        preferences.putString(LOGIN_KEY, login);
    }

    @Override
    protected void pullSettings() {
        super.pullSettings();
        sessionId = preferences.getString(SESSION_ID_KEY, SESSION_ID_DEFAULT_VALUE);
        login = preferences.getString(LOGIN_KEY, LOGIN_DEFAULT_VALUE);
    }
}

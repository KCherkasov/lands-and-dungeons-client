package ru.kvvartet.lndclient.client.network;

public final class NetworkConfig {
    public static final String SERVER_HOST = "localhost";
    public static final Integer SERVER_PORT = 8081;
    public static final String READY_URL = "http://" + SERVER_HOST + ':' + SERVER_PORT;
    public static final String SIGNUP_URI = "/signup";
    public static final String SIGNIN_URI = "/signin";
    public static final String SESSION_URI = "/session";
    public static final String SETTINGS_URI = "/settings";
    public static final String SCOREBOARD_URI = "/scoreboard";
    public static final String SIGNPUT_URI = "/signout";
}

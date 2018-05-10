package ru.kvvartet.lndclient.client.network.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import ru.kvvartet.lndclient.client.network.NetworkConfig;
import ru.kvvartet.lndclient.client.network.model.User;
import ru.kvvartet.lndclient.preferences.AuthorizationPreferences;

public class AuthorizationControllerImpl implements  AuthorizationController {

    protected final Json json = new Json();
    protected AuthorizationPreferences preferences = AuthorizationPreferences.getInstance();

    public AuthorizationControllerImpl() {
        json.setOutputType(JsonWriter.OutputType.json);
    }

    @Override
    public void signUp(User user, Net.HttpResponseListener httpResponseListener) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .header(HttpRequestHeader.ContentType, "application/json")
                .url(NetworkConfig.READY_URL + NetworkConfig.SIGNUP_URI)
                .content(json.toJson(user))
                .includeCredentials(true)
                .build();
        System.out.println(json.toJson(user, User.class));
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SIGNUP_URI);
        System.out.println("SIGN UP SENDED");
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }

    @Override
    public void signIn(User user, Net.HttpResponseListener httpResponseListener) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        //System.out.println("Cookie request: " + AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId());
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .header(HttpRequestHeader.Cookie, AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId())
                .header(HttpRequestHeader.ContentType, "application/json")
                .url(NetworkConfig.READY_URL + NetworkConfig.SIGNIN_URI)
                .content(json.toJson(user))
                .includeCredentials(true)
                .build();
        System.out.println(json.toJson(user, User.class));
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SIGNIN_URI);
        System.out.println("SIGN IN SENDED");
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }

    @Override
    public void userInformation(Net.HttpResponseListener httpResponseListener) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        //System.out.println("Cookie request: " + AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId());
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.GET)
                .header(HttpRequestHeader.Cookie, AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId())
                .url(NetworkConfig.READY_URL + NetworkConfig.SESSION_URI)
                .includeCredentials(true)
                .build();
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SESSION_URI);
        System.out.println("SESSION SENDED");
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }

    @Override
    public void changeProfile(User user, Net.HttpResponseListener httpResponseListener) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        System.out.println("Cookie request: " + AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId());
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.PUT)
                .header(HttpRequestHeader.Cookie, AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId())
                .header(HttpRequestHeader.ContentType, "application/json")
                .url(NetworkConfig.READY_URL + NetworkConfig.SETTINGS_URI)
                .content(json.toJson(user))
                .includeCredentials(true)
                .build();
        System.out.println(json.toJson(user, User.class));
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SETTINGS_URI);
        System.out.println("SETTINGS SENDED");
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }

    @Override
    public void signOut(Net.HttpResponseListener httpResponseListener) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        //System.out.println("Cookie request: " + AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId());
        final Net.HttpRequest httpRequest = httpRequestBuilder.newRequest()
                .method(Net.HttpMethods.DELETE)
                .header(HttpRequestHeader.Cookie, AuthorizationPreferences.AUTHORIZATION_COOKIE_ID + '=' +preferences.getSessionId())
                .url(NetworkConfig.READY_URL + NetworkConfig.SIGNOUT_URI)
                .includeCredentials(true)
                .build();
        System.out.println("URL TO SEND: " + NetworkConfig.READY_URL + NetworkConfig.SIGNOUT_URI);
        System.out.println("SIGNOUT SENDED");
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }
}

package ru.kvvartet.lndclient.client.network.controller;

import com.badlogic.gdx.Net;

import ru.kvvartet.lndclient.client.network.model.User;

public interface AuthorizationController {
    void signUp(User user, Net.HttpResponseListener httpResponseListener);

    void signIn(User user, Net.HttpResponseListener httpResponseListener);

    void userInformation(Net.HttpResponseListener httpResponseListener);

    void changeProfile(User user, Net.HttpResponseListener httpResponseListener);

    void signOut(Net.HttpResponseListener httpResponseListener);
}

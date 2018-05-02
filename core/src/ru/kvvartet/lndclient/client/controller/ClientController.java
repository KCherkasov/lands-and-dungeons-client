package ru.kvvartet.lndclient.client.controller;

import ru.kvvartet.lndclient.client.controller.model.User;

public interface ClientController {
    void init();

    void update(float timeDelta);

    void signUp(User user);

    void signIn(User user);

    User getSession();

    void changeProfile(User newUser);

    void signOut();


}

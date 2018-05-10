package ru.kvvartet.lndclient.client.network.manager;

import com.badlogic.gdx.net.Socket;

public interface NetworkManager {
    void initSocket();

    Socket getSocket();
}

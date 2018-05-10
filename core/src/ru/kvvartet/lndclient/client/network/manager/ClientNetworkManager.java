package ru.kvvartet.lndclient.client.network.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

import ru.kvvartet.lndclient.client.network.NetworkConfig;

public class ClientNetworkManager implements NetworkManager{

    Socket client;
    final SocketHints clientHints = new SocketHints();

    @Override
    public void initSocket() {
        try {
            client = Gdx.net.newClientSocket(Net.Protocol.TCP, NetworkConfig.SERVER_HOST, NetworkConfig.SERVER_PORT, clientHints);
        } catch (GdxRuntimeException gdxException) {
            gdxException.printStackTrace();
            //TODO: Error screen and retry connection
        }
    }

    @Override
    public Socket getSocket() {
        return client;
    }
}

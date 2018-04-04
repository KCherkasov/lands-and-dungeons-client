package ru.kvvartet.lndclient.client.renderer;

import com.badlogic.gdx.utils.Disposable;

public interface ClientRenderer extends Disposable {
    void init();
    void render();
    void resize(int width, int height);
}

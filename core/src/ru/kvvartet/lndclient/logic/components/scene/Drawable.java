package ru.kvvartet.lndclient.logic.components.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.istack.internal.NotNull;

public interface Drawable {
    void render(@NotNull SpriteBatch target);
}

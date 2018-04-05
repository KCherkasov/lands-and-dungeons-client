package ru.kvvartet.lndclient.logic.components.scene;

import com.badlogic.gdx.math.Vector2;
import com.sun.istack.internal.NotNull;

public interface SceneNodeData {
    @NotNull
    Vector2 getPosition();

    @NotNull
    Vector2 getDimension();

    @NotNull
    Vector2 getScale();

    @NotNull
    Vector2 getOrigin();

    @NotNull
    float getRotation();

    void setPosition(@NotNull Vector2 position);

    void setDimension(@NotNull Vector2 dimension);

    void setScale(@NotNull Vector2 scale);

    void setOrigin(@NotNull Vector2 origin);

    void setRotation(@NotNull float rotation);
}

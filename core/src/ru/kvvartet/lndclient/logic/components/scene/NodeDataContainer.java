package ru.kvvartet.lndclient.logic.components.scene;

import com.badlogic.gdx.math.Vector2;
import com.sun.istack.internal.NotNull;

public class NodeDataContainer implements SceneNodeData {
    private Vector2 position;
    private Vector2 dimensions;
    private Vector2 origin;
    private Vector2 scale;
    private float rotation;

    public NodeDataContainer(@NotNull Vector2 position,
                             @NotNull Vector2 dimensions,
                             @NotNull Vector2 origin,
                             @NotNull Vector2 scale,
                             @NotNull float rotation) {
        this.position = position;
        this.dimensions = dimensions;
        this.origin = origin;
        this.scale = scale;
        this.rotation = rotation;
    }

    @Override
    @NotNull
    public Vector2 getPosition() {
        return position;
    }

    @Override
    @NotNull
    public Vector2 getDimension() {
        return dimensions;
    }

    @Override
    @NotNull
    public Vector2 getOrigin() {
        return origin;
    }

    @Override
    @NotNull
    public Vector2 getScale() {
        return scale;
    }

    @Override
    @NotNull
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setPosition(@NotNull Vector2 position) {
        this.position = position;
    }

    @Override
    public void setDimension(@NotNull Vector2 dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void setOrigin(@NotNull Vector2 origin) {
        this.origin = origin;
    }

    @Override
    public void setScale(@NotNull Vector2 scale) {
        this.scale = scale;
    }

    @Override
    public void setRotation(@NotNull float rotation) {
        this.rotation = rotation;
    }
}

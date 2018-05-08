package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import ru.kvvartet.lndclient.logic.components.entity.AbstractComponent;

import java.util.HashMap;
import java.util.Map;

public abstract class GraphicsComponent extends AbstractComponent {
    protected final Map<String, Animation> animations = new HashMap<>();

}

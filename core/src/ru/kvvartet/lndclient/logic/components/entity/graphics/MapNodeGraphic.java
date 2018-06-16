package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.battle.graphics.tiles.AoeMessage;
import ru.kvvartet.lndclient.util.graphics.GameEntityWidgets;

import java.util.HashMap;
import java.util.Map;

public class MapNodeGraphic extends GameEntityComponent {
    private final Map<Integer, MapNodeGraphic> adjacentTiles = new HashMap<>();
    // TODO: MapNode data component here

    public MapNodeGraphic(@NotNull TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    protected void initHandlers() {
        handlers.put(AoeMessage.class.getName(), message -> {
            final AoeMessage aoeMessage = (AoeMessage)message;
            if (actors.containsKey(GameEntityWidgets.GEW_ABILITY)) {
                final Actor ability = actors.get(GameEntityWidgets.GEW_ABILITY);
                if (ability.getClass().equals(AnimatableImage.class)) {
                    ((AnimatableImage)ability).setCurrentAnimation(aoeMessage.getAbility());
                    if (!ability.getParent().equals(widget)) {
                        if (ability.getParent() != null) {
                            ability.getParent().removeActor(ability);
                        }
                        widget.addActor(ability);
                    }
                } else {
                    actors.replace(GameEntityWidgets.GEW_ABILITY,
                            new AnimatableImage(aoeMessage.getAbility()));
                    widget.removeActor(ability);
                    widget.addActor(actors.get(GameEntityWidgets.GEW_ABILITY));
                }
            } else {
                actors.put(GameEntityWidgets.GEW_ABILITY,
                        new AnimatableImage(aoeMessage.getAbility()));
                widget.addActor(actors.get(GameEntityWidgets.GEW_ABILITY));
            }
        });
    }

    @Override
    public void update(float timeDelta) {
        checkAnimations();
        super.update(timeDelta);
    }

    public void setAdjacentTiles(@NotNull Map<Integer, MapNodeGraphic> neighborTiles) {
        adjacentTiles.putAll(neighborTiles);
    }

    public void setAdjacentTile(@NotNull MapNodeGraphic neighbor, @NotNull Integer direction) {
        if (!adjacentTiles.containsKey(direction)) {
            adjacentTiles.put(direction, neighbor);
        }
    }

    private void checkAnimations() {
        if (actors.getOrDefault(GameEntityWidgets.GEW_ABILITY, null) != null
                && actors.get(GameEntityWidgets.GEW_ABILITY).getParent().equals(widget)) {
            if (actors.get(GameEntityWidgets.GEW_ABILITY).getClass().equals(AnimatableImage.class)) {
                final AnimatableImage ability = (AnimatableImage)actors.get(GameEntityWidgets.GEW_ABILITY);
                if (ability.getCurrentAnimation().isAnimationFinished(ability.getStateTime())) {
                    widget.removeActor(ability);
                }
            }
        }
    }
}

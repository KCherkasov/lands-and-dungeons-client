package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.makers;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.MapNodeTooltip;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.MapNode;

public class MapNodeTooltipMaker implements TooltipMaker {
    private final MapNode data;

    public MapNodeTooltipMaker(@NotNull MapNode data) {
        this.data = data;
    }

    @Override
    public @NotNull Table makeTooltip() {
        return new MapNodeTooltip(data);
    }
}

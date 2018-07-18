package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.TableTooltipData;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipCaption;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.MapNode;

import java.util.Objects;

public class MapNodeTooltip extends Tooltip {
    private static final String FREE_TILE = "Tile";
    private static final String WALL_TILE = "Non-passable tile";

    public MapNodeTooltip(@NotNull MapNode data) {
        if (data.isOccupied()) {
            makeEmptyTileTooltip(data);
        } else {
            final Table widget = new Table();
            new TooltipCaption(Objects.requireNonNull(
                    data.getInhabitant()).getName()).toActor(widget);
            new TableTooltipData(widget,
                    data.getInhabitant().getProperties()).toActor();
            new TooltipCaption(data.getInhabitant().getDescription()).toActor(widget);
            add(widget).fillX().row();
        }
    }

    private void makeEmptyTileTooltip(@NotNull MapNode data) {
        final Table widget = new Table();
        new TooltipCaption(data.getIsPassable()
                ? FREE_TILE : WALL_TILE).toActor(widget);
        add(widget).fillX().center().row();
    }
}

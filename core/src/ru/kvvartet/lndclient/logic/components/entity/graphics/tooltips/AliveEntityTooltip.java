package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.TableTooltipData;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipCaption;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.aliveentities.AbstractAliveEntity;

public class AliveEntityTooltip extends Tooltip {
    public AliveEntityTooltip(@NotNull AbstractAliveEntity data) {
        final Table widget = new Table();
        new TooltipCaption(data.getName()).toActor(widget);
        new TableTooltipData(widget, data.getProperties()).toActor();
    }
}

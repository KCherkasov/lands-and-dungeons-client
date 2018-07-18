package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.makers;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.AliveEntityTooltip;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.aliveentities.AbstractAliveEntity;

public class AnimatableEntityTooltipMaker implements TooltipMaker {
    private final AbstractAliveEntity data;

    public AnimatableEntityTooltipMaker(@NotNull AbstractAliveEntity data) {
        this.data = data;
    }

    @Override
    public @NotNull Table makeTooltip() {
        return new AliveEntityTooltip(data);
    }
}

package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.makers;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;

public interface TooltipMaker {
    @NotNull Table makeTooltip();
}

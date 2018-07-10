package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;

public interface TooltipEntry {
    void toActor(@NotNull Table target);

    int DOUBLE_COLSPAN = 2;
    int DEFAULT_COLSPAN = 1;
}

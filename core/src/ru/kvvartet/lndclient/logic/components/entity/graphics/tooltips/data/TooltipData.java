package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipEntry;

import java.util.Map;

public interface TooltipData {
    @NotNull Map<Integer, TooltipEntry> getData();

    @NotNull Actor toActor();
}

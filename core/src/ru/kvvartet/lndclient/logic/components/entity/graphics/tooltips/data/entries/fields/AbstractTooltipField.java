package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTooltipField<T> implements TooltipField<T> {
    private final T value;
    private final String caption;

    public AbstractTooltipField(@NotNull String caption, @NotNull T value) {
        this.value = value;
        this.caption = caption;
    }

    @Override
    public @NotNull String getCaption() {
        return caption;
    }

    @Override
    public @NotNull T getValue() {
        return value;
    }

    @Override
    public void toActor(@NotNull Table target) {
        makeActor(target);
    }

    protected abstract void makeActor(@NotNull Table target);
}

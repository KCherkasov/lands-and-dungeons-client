package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SetHolder;
import ru.kvvartet.lndclient.util.Constants;

import java.util.Objects;

public class SetField extends MultiValueField<SetHolder> {
    public SetField(@NotNull String caption, @NotNull SetHolder values, @NotNull String captionMapName) {
        super(caption, values, captionMapName);
    }

    @Override
    protected @NotNull String getStringValue(@NotNull Integer key) {
        return Objects.requireNonNull(getValue().getSetData()).contains(key) ? " " : Constants.EMPTY_LINE;
    }
}

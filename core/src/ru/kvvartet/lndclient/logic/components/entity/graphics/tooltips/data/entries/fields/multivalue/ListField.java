package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.ListHolder;
import ru.kvvartet.lndclient.util.Constants;

import java.util.Objects;

public class ListField extends MultiValueField<ListHolder> {
    public ListField(@NotNull String caption, @NotNull  ListHolder values, @NotNull String captionMapName) {
        super(caption, values, captionMapName);
    }

    @Override
    protected @NotNull String getStringValue(@NotNull Integer key) {
        return key >= Constants.ZERO_INT && key < Objects.requireNonNull(getValue().getListData()).size()
                ? getValue().getValue(key).toString() : Constants.EMPTY_LINE;
    }
}

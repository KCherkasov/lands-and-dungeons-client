package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.MapHolder;
import ru.kvvartet.lndclient.util.Constants;

import java.util.Objects;

public class MapField extends MultiValueField<MapHolder> {
    public MapField(@NotNull String caption, @NotNull MapHolder values, @NotNull String captionMapName) {
        super(caption, values, captionMapName);
    }

    @Override
    protected @NotNull String getStringValue(@NotNull Integer key) {
        return Objects.requireNonNull(getValue().getMapData()).containsKey(key)
                ? getValue().getValue(key).toString() : Constants.EMPTY_LINE;
    }
}

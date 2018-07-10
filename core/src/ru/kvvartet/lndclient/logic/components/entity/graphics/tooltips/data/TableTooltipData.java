package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipCaption;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.TooltipEntry;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.SingleValueField;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue.ListField;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue.MapField;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue.SetField;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.*;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.*;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.*;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TableTooltipData implements TooltipData {
    // TODO: Entry captions map json file and static holder for it
    // (existing holder contains inner captions for multivalue entries)

    private static final int PROPERTIES_CAPTION_ID = -10;
    private static final String PROPERTIES_CAPTION = "Properties";
    private static final int AFFECTORS_CAPTION_ID = -11;
    private static final String AFFECTORS_CAPTION = "Affectors";

    private static final String SET_SEQUENCE = "Set";
    private static final String LIST_SEQUENCE = "List";
    private static final String MAP_SEQUENCE = "Map";
    private static final String SINGLE_VALUE_SEQUENCE = "SingleValue";

    private final Map<Integer, TooltipEntry> data = new HashMap<>();
    private final Queue<Integer> entriesOrder = new ArrayDeque<>();
    private final Table widget;

    public TableTooltipData(@NotNull Table widget,
                            @NotNull PropertyProvider properties,
                            @NotNull AffectorProvider affectors) {
        this(widget, properties);
        data.put(AFFECTORS_CAPTION_ID, new TooltipCaption(AFFECTORS_CAPTION));
        entriesOrder.offer(AFFECTORS_CAPTION_ID);
        for (Integer key: affectors.affectorsKeyset()) {
            data.put(key, makeEntry(affectors.getAvailableAffectors().get(key), key));
            entriesOrder.offer(key);
        }
    }

    public TableTooltipData(@NotNull Table widget,
                            @NotNull PropertyProvider properties) {
        this.widget = widget;
        data.put(PROPERTIES_CAPTION_ID, new TooltipCaption(PROPERTIES_CAPTION));
        entriesOrder.offer(PROPERTIES_CAPTION_ID);
        for (Integer key : properties.propertiesKeyset()) {
            data.put(key, makeEntry(properties.getAvailableProperties().get(key), key));
            entriesOrder.offer(key);
        }
    }

    @Override
    public @NotNull Map<Integer, TooltipEntry> getData() {
        return data;
    }

    @Override
    public @NotNull Actor toActor() {
        while (!entriesOrder.isEmpty()) {
            data.get(entriesOrder.poll()).toActor(widget);
        }
        return widget;
    }

    @SuppressWarnings("ConstantConditions")
    private @NotNull TooltipEntry makeEntry(@NotNull DataHolder value, @NotNull Integer key) {
        if (isSet(value)) {
            return new SetField("", (SetHolder) value, "");
        }
        if (isMap(value)) {
            return new MapField("", (MapHolder) value, "");
        }
        if (isList(value)) {
            return new ListField("", (ListHolder) value, "");
        }
        if (isSingleValue(value)) {
            return new SingleValueField("", (SingleValueHolder) value);
        }
        Gdx.app.error(getClass().getName(),
                "unknown DataHolder class: " + value.getClass().getSimpleName());
        return null;
    }

    private @NotNull Boolean isSet(@NotNull DataHolder value) {
        return value.getClass().equals(SetProperty.class)
                || value.getClass().equals(SetAffector.class);
    }

    private @NotNull Boolean isMap(@NotNull DataHolder value) {
        return value.getClass().equals(MapProperty.class)
                || value.getClass().equals(MapAffector.class);
    }

    private @NotNull Boolean isList(@NotNull DataHolder value) {
        return value.getClass().equals(ListProperty.class)
                || value.getClass().equals(ListAffector.class);
    }

    private @NotNull Boolean isSingleValue(@NotNull DataHolder value) {
        return value.getClass().equals(SingleValueProperty.class)
                || value.getClass().equals(SingleValueAffector.class);
    }
}

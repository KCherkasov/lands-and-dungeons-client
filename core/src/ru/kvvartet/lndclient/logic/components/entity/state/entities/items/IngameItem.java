package ru.kvvartet.lndclient.logic.components.entity.state.entities.items;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.abstracts.DoubleProviderComponent;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorProviderImpl;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyProviderImpl;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.SingleValueProperty;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.ItemBlueprint;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.ItemModel;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.Levelable;

import java.util.HashMap;
import java.util.Map;

public class IngameItem extends DoubleProviderComponent implements Levelable {
    private final Integer itemId;
    private final Integer level;

    public IngameItem (@NotNull ItemModel model) {
        super(model.getName(), model.getDescription(),
                new PropertyProviderImpl(model.getProperties()),
                new AffectorProviderImpl(model.getAffectors()));
        this.itemId = model.getId();
        this.level = model.getLevel();
    }

    public @NotNull ItemBlueprint pack() {
        final Map<Integer, DataHolder> blueprintMap = new HashMap<>();

        blueprintMap.put(PropertyCategories.PC_INSTANCE_ID, new SingleValueProperty(itemId));
        blueprintMap.put(PropertyCategories.PC_LEVEL, new SingleValueProperty(level));
        blueprintMap.put(PropertyCategories.PC_ITEM_PARTS_IDS,
                properties.getProperty(PropertyCategories.PC_ITEM_PARTS_IDS));
        blueprintMap.put(PropertyCategories.PC_ITEM_PARTS_RARITIES,
                properties.getProperty(PropertyCategories.PC_ITEM_PARTS_RARITIES));
        blueprintMap.put(PropertyCategories.PC_ITEM_KIND,
                properties.getProperty(PropertyCategories.PC_ITEM_KIND));

        return new ItemBlueprint(blueprintMap);
    }

    public @NotNull ItemModel what() {
        return new ItemModel(itemId, getName(), getDescription(),
                level, properties.getAvailableProperties(),
                affectors.getAvailableAffectors());
    }

    @Override
    public @NotNull Integer getLevel() {
        return level;
    }

    @Override
    public void dispose() {}

    @Override
    protected void initHandlers() {}
}

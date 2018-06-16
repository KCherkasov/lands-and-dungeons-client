package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.MapProperty;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.SingleValueProperty;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ItemBlueprint {
    public static final int UNDEFINED_RARITY = -1;
    public static final int ITEM_PARTS_COUNT = 3;

    public static final Map<Integer, Integer> RANDOM_ITEM_RARITIES = initializeRandomRarities();

    private final Map<Integer, DataHolder> properties;

    public ItemBlueprint(@JsonProperty("properties")
                         @NotNull Map<Integer, DataHolder> properties) {
        this.properties = properties;
    }

    public ItemBlueprint(@NotNull Integer dropChance,
                         @NotNull Map<Integer, DataHolder> properties,
                         @NotNull Map<Integer, Integer> itemParts,
                         @NotNull Map<Integer, Integer> itemPartsRarities) {
        this.properties = properties;

        if (properties.containsKey(PropertyCategories.PC_DROP_CHANCE)) {
            properties.get(PropertyCategories.PC_DROP_CHANCE).setSingleValue(dropChance);
        } else {
            properties.put(PropertyCategories.PC_DROP_CHANCE,
                    new SingleValueProperty(dropChance));
        }

        if (properties.containsKey(PropertyCategories.PC_ITEM_PARTS_IDS)) {
            properties.replace(PropertyCategories.PC_ITEM_PARTS_IDS,
                    new MapProperty(itemParts));
        } else {
            properties.put(PropertyCategories.PC_ITEM_PARTS_IDS,
                    new MapProperty(itemParts));
        }

        if (properties.containsKey(PropertyCategories.PC_ITEM_PARTS_RARITIES)) {
            properties.replace(PropertyCategories.PC_ITEM_PARTS_RARITIES,
                    new MapProperty(itemPartsRarities));
        } else {
            properties.put(PropertyCategories.PC_ITEM_PARTS_RARITIES,
                    new MapProperty(itemPartsRarities));
        }
    }

    public ItemBlueprint(@NotNull Integer dropChance,
                         @NotNull Map<Integer, DataHolder> properties,
                         @NotNull Map<Integer, Integer> itemParts) {
        this(dropChance, properties, itemParts, RANDOM_ITEM_RARITIES);
    }

    @JsonIgnore
    public @NotNull Integer getDropChance() {
        return properties.get(PropertyCategories.PC_DROP_CHANCE).getValue();
    }

    public @NotNull Map<Integer, DataHolder> getProperties() {
        return properties;
    }

    @JsonIgnore
    public @NotNull Set<Integer> getAvailableProperties() {
        return properties.keySet();
    }

    @JsonIgnore
    public @Nullable Map<Integer, Integer> getItemParts() {
        return properties.get(PropertyCategories.PC_ITEM_PARTS_IDS).getMapData();
    }

    @JsonIgnore
    public @Nullable Map<Integer, Integer> getItemPartsRarities() {
        return properties.get(PropertyCategories.PC_ITEM_PARTS_RARITIES).getMapData();
    }

    @JsonIgnore
    public @NotNull Set<Integer> getAvailableItemParts() {
        return Objects.requireNonNull(getItemParts()).keySet();
    }

    private static  @NotNull Map<Integer, Integer> initializeRandomRarities() {
        final Map<Integer, Integer> randomRarities = new HashMap<>();
        for (Integer i = 0; i < ITEM_PARTS_COUNT; ++i) {
            randomRarities.put(i, UNDEFINED_RARITY);
        }
        return randomRarities;
    }
}

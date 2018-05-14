package ru.kvvartet.lndclient.logic.components.entity.state.adapters;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.util.Constants;

import java.util.HashMap;
import java.util.Map;

public final class PropertyToAffectorMapper {
    private static final Map<Integer, Integer> PROPERTY_AFFECTOR_MAPPING = initMap();

    private PropertyToAffectorMapper() {
    }

    public static @NotNull Integer getAffectorKind(@NotNull Integer propertyKind) {
        return PROPERTY_AFFECTOR_MAPPING.getOrDefault(propertyKind, Constants.WRONG_INDEX);
    }

    private static @NotNull Map<Integer, Integer> initMap() {
        final Map<Integer, Integer> mapping = new HashMap<>();
        mapping.put(PropertyCategories.PC_STATS, AffectorCategories.AC_STATS_AFFECTOR);
        mapping.put(PropertyCategories.PC_RATINGS, AffectorCategories.AC_RATINGS_AFFECTOR);
        mapping.put(PropertyCategories.PC_HITPOINTS, AffectorCategories.AC_HEALTH_AFFECTOR);
        mapping.put(PropertyCategories.PC_BASE_DAMAGE, AffectorCategories.AC_DAMAGE_AFFECTOR);
        mapping.put(PropertyCategories.PC_BASE_DEFENSE, AffectorCategories.AC_DEFENSE_AFFECTOR);
        return mapping;
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModifiablePropertyProvider extends PropertyProvider {
    void addProperty (@NotNull Integer key, @NotNull DataHolder property);

    void removeProperty(@NotNull Integer key);

    void setPropertyValue(@NotNull Integer key, @NotNull Integer value);

    void setPropertyValue(@NotNull Integer key,
                          @NotNull Integer dataIndex,
                          @NotNull Integer value);

    void setPropertiesList(@NotNull Integer key, @NotNull List<Integer> values);

    void setPropertiesMap(@NotNull Integer key, @NotNull Map<Integer, Integer> values);

    void setPropertiesSet(@NotNull Integer key, @NotNull Set<Integer> values);

    void modifyPropertyByAddition(@NotNull Integer key, @NotNull Integer toAdd);

    void modifyPropertyByAddition(@NotNull Integer key,
                                  @NotNull Integer dataIndex,
                                  @NotNull Integer toAdd);

    void modifyPropertyByPercentage(@NotNull Integer key, @NotNull Float percentage);

    void modifyPropertyByPercentage(@NotNull Integer key,
                                    @NotNull Integer dataIndex,
                                    @NotNull Float percentage);
}

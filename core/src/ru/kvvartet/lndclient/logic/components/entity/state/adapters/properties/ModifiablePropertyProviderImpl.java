package ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.ModifiableDataHolderProvider;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModifiablePropertyProviderImpl extends ModifiableDataHolderProvider
        implements ModifiablePropertyProvider {
    public ModifiablePropertyProviderImpl(@NotNull Map<Integer, DataHolder> values) {
        super(values);
    }

    @Override
    public @NotNull Boolean hasProperty(@NotNull Integer key) {
        return containsHolder(key);
    }

    @Override
    public @Nullable DataHolder getProperty(@NotNull Integer key) {
        return getHolder(key);
    }

    @Override
    public @NotNull Integer getPropertyValue(@NotNull Integer key) {
        return getHolderValue(key);
    }

    @Override
    public @NotNull Integer getPropertyValue(@NotNull Integer key, @NotNull Integer dataIndex) {
        return getHolderValue(key, dataIndex);
    }

    @Override
    public @NotNull Set<Integer> propertiesKeyset() {
        return keyset();
    }

    @JsonIgnore
    @Override
    public @NotNull Map<Integer, DataHolder> getAvailableProperties() {
        return getAvailableHolders();
    }

    @Override
    public void addProperty(@NotNull Integer key, @NotNull DataHolder property) {
        addDataHolder(key, property);
    }

    @Override
    public void removeProperty(@NotNull Integer key) {
        removeDataHolder(key);
    }

    @Override
    public void setPropertyValue(@NotNull Integer key, @NotNull Integer value) {
        setDataHolder(key, value);
    }

    @Override
    public void setPropertyValue(@NotNull Integer key,
                                 @NotNull Integer dataIndex,
                                 @NotNull Integer value) {
        setDataHolder(key, dataIndex, value);
    }

    @Override
    public void setPropertiesList(@NotNull Integer key, @NotNull List<Integer> values) {
        setDataList(key, values);
    }

    @Override
    public void setPropertiesMap(@NotNull Integer key, @NotNull Map<Integer, Integer> values) {
        setDataMap(key, values);
    }

    @Override
    public void setPropertiesSet(@NotNull Integer key, @NotNull Set<Integer> values) {
        setDataSet(key, values);
    }

    @Override
    public void modifyPropertyByAddition(@NotNull Integer key, @NotNull Integer toAdd) {
        modifyValueByAddition(key, toAdd);
    }

    @Override
    public void modifyPropertyByAddition(@NotNull Integer key,
                                         @NotNull Integer dataIndex,
                                         @NotNull Integer toAdd) {
        modifyValueByAddition(key, dataIndex, toAdd);
    }

    @Override
    public void modifyPropertyByPercentage(@NotNull Integer key, @NotNull Float percentage) {
        modifyValueByPercentage(key, percentage);
    }

    @Override
    public void modifyPropertyByPercentage(@NotNull Integer key,
                                           @NotNull Integer dataIndex,
                                           @NotNull Float percentage) {
        modifyValueByPercentage(key, dataIndex, percentage);
    }
}

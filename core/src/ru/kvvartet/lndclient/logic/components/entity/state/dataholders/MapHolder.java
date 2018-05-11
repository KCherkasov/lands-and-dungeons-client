package ru.kvvartet.lndclient.logic.components.entity.state.dataholders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.util.Constants;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapHolder implements DataHolder {
    private final Map<Integer, Integer> values;

    public MapHolder(@JsonProperty("properties") @NotNull Map<Integer, Integer> values) {
        this.values = values;
    }

    @JsonIgnore
    @Override
    public @NotNull Integer getValue() {
        return Constants.ZERO_INT;
    }

    @JsonIgnore
    @Override
    public @NotNull Integer getValue(@NotNull Integer dataIndex) {
        return values.getOrDefault(dataIndex, Constants.ZERO_INT);
    }

    @JsonIgnore
    @Override
    public void setSingleValue(@NotNull Integer value) {
    }

    @Override
    public void setSingleValue(@NotNull Integer dataIndex, @NotNull Integer value) {
        if (!values.containsKey(dataIndex)) {
            return;
        }
        values.replace(dataIndex, value);
    }

    @JsonIgnore
    @Override
    @SuppressWarnings("ConstantConditions")
    public @Nullable List<Integer> getListData() {
        return null;
    }

    @JsonIgnore
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public void setListData(@NotNull List<Integer> values) {
    }

    @JsonProperty("properties")
    @Override
    public @Nullable Map<Integer, Integer> getMapData() {
        return values;
    }

    @JsonSetter("properties")
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public @NotNull Boolean setMapData(@NotNull Map<Integer, Integer> values) {
        this.values.clear();
        this.values.putAll(values);
        return true;
    }

    @JsonIgnore
    @Override
    @SuppressWarnings("ConstantConditions")
    public @Nullable Set<Integer> getSetData() {
        return null;
    }

    @JsonIgnore
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public @NotNull Boolean setSetData(@NotNull Set<Integer> values) {
        return false;
    }

    @Override
    public void modifyByAddition(@NotNull Integer toAdd) {
        for (Integer dataIndex : values.keySet()) {
            if (!modifyByAddition(dataIndex, toAdd)) {
                return;
            }
        }
    }

    @Override
    public @NotNull Boolean modifyByAddition(@NotNull Integer dataIndex, @NotNull Integer toAdd) {
        if (!values.containsKey(dataIndex)) {
            return false;
        }
        values.replace(dataIndex, values.get(dataIndex) + toAdd);
        return true;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Float percentage) {
        for (Integer dataIndex : values.keySet()) {
            if (!modifyByPercentage(dataIndex, percentage)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Integer dataIndex, @NotNull Float percentage) {
        if (!values.containsKey(dataIndex)) {
            return false;
        }
        values.replace(dataIndex, Math.round(values.get(dataIndex) * (percentage + Constants.PERCENTAGE_CAP_FLOAT)));
        return true;
    }
}

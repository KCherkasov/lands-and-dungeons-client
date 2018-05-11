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

public class SetHolder implements DataHolder {
    private final Set<Integer> values;

    public SetHolder(@JsonProperty("properties") @NotNull Set<Integer> values) {
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
        return values.contains(dataIndex) ? dataIndex : Constants.WRONG_INDEX;
    }

    @JsonIgnore
    @Override
    public void setSingleValue(@NotNull Integer value) {
    }

    @Override
    public void setSingleValue(@NotNull Integer dataIndex, @NotNull Integer value) {
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

    @JsonIgnore
    @Override
    @SuppressWarnings("ConstantConditions")
    public @Nullable Map<Integer, Integer> getMapData() {
        return null;
    }

    @JsonIgnore
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public @NotNull Boolean setMapData(@NotNull Map<Integer, Integer> values) {
        return false;
    }

    @JsonProperty("properties")
    @Override
    @SuppressWarnings("ConstantConditions")
    public @Nullable Set<Integer> getSetData() {
        return values;
    }

    @JsonSetter("properties")
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public @NotNull Boolean setSetData(@NotNull Set<Integer> values) {
        this.values.clear();
        this.values.addAll(values);
        return true;
    }

    @Override
    public void modifyByAddition(@NotNull Integer toAdd) {
    }

    @Override
    public @NotNull Boolean modifyByAddition(@NotNull Integer dataIndex, @NotNull Integer toAdd) {
        return false;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Float percentage) {
        return false;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Integer dataIndex, @NotNull Float percentage) {
        return false;
    }
}

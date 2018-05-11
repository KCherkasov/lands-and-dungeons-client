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

public class SingleValueHolder implements DataHolder {
    private Integer value;

    public SingleValueHolder(@JsonProperty("property") @NotNull Integer value) {
        this.value = value;
    }

    @Override
    @JsonProperty("property")
    public @NotNull Integer getValue() {
        return value;
    }

    @Override
    public @NotNull Integer getValue(@NotNull Integer dataIndex) {
        return Constants.ZERO_INT;
    }

    @Override
    @JsonSetter("property")
    @SuppressWarnings("ParameterHidesMemberValue")
    public void setSingleValue(@NotNull Integer value) {
        if (this.value == null) {
            return;
        }
        this.value = value;
    }

    @JsonIgnore
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
    public @NotNull Boolean setMapData(@NotNull Map<Integer, Integer> values) {
        return false;
    }


    @JsonIgnore
    @Override
    @SuppressWarnings("ConstantConditions")
    public @Nullable Set<Integer> getSetData() {
        return null;
    }

    @JsonIgnore
    @Override
    public @NotNull Boolean setSetData(@NotNull Set<Integer> values) {
        return false;
    }

    @Override
    public void modifyByAddition(@NotNull Integer toAdd) {
        value += toAdd;
    }

    @Override
    public @NotNull Boolean modifyByAddition(@NotNull Integer dataIndex, @NotNull Integer toAdd) {
        return false;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Float percentage) {
        value = Math.round(value * (percentage + Constants.PERCENTAGE_CAP_FLOAT));
        return true;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Integer dataIndex, @NotNull Float percentage) {
        return false;
    }
}

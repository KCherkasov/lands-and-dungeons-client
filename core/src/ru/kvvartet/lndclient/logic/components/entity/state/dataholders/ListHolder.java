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

public class ListHolder implements DataHolder {
    private final List<Integer> values;

    public ListHolder(@JsonProperty("properties") @NotNull List<Integer> values) {
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
        return dataIndex >= values.size() ? Constants.ZERO_INT : values.get(dataIndex);
    }

    @JsonIgnore
    @Override
    public void setSingleValue(@NotNull Integer value) {
    }

    @Override
    public void setSingleValue(@NotNull Integer dataIndex, @NotNull Integer value) {
        if (dataIndex >= values.size() || dataIndex < 0) {
            return;
        }
        values.set(dataIndex, value);
    }

    @JsonProperty("properties")
    @Override
    public @Nullable List<Integer> getListData() {
        return values;
    }

    @JsonSetter("properties")
    @Override
    @SuppressWarnings("ParameterHidesMemberVariable")
    public void setListData(@NotNull List<Integer> values) {
        this.values.clear();
        this.values.addAll(values);
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
        for (Integer dataIndex = Constants.ZERO_INT; dataIndex < values.size(); ++dataIndex) {
            if (!modifyByAddition(dataIndex, toAdd)) {
                return;
            }
        }
    }

    @Override
    public @NotNull Boolean modifyByAddition(@NotNull Integer dataIndex, @NotNull Integer toAdd) {
        if (dataIndex >= values.size() || dataIndex < Constants.ZERO_INT) {
            return false;
        }
        values.set(dataIndex, values.get(dataIndex) + toAdd);
        return true;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Float percentage) {
        for (Integer dataIndex = Constants.ZERO_INT; dataIndex < values.size(); ++dataIndex) {
            if (!modifyByPercentage(dataIndex, percentage)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull Boolean modifyByPercentage(@NotNull Integer dataIndex, @NotNull Float percentage) {
        if (dataIndex >= values.size() || dataIndex < Constants.ZERO_INT) {
            return false;
        }
        values.set(dataIndex, Math.round(values.get(dataIndex) * (percentage + Constants.PERCENTAGE_CAP_FLOAT)));
        return true;
    }
}

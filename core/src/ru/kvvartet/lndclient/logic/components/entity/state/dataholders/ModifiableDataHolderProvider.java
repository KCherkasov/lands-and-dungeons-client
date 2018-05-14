package ru.kvvartet.lndclient.logic.components.entity.state.dataholders;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ModifiableDataHolderProvider extends DataHolderProvider {
    protected ModifiableDataHolderProvider(@NotNull Map<Integer, DataHolder> dataHolders) {
        super(dataHolders);
    }

    protected void addDataHolder(@NotNull Integer key, @NotNull DataHolder dataHolder) {
        if (containsHolder(key)) {
            return;
        }
        getAvailableHolders().put(key, dataHolder);
    }

    protected void removeDataHolder(@NotNull Integer key) {
        getAvailableHolders().remove(key);
    }

    protected void setDataHolder(@NotNull Integer key, @NotNull Integer value) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.setSingleValue(value);
        }
    }

    protected void setDataHolder(@NotNull Integer key,
                                 @NotNull Integer dataIndex,
                                 @NotNull Integer value) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.setSingleValue(dataIndex, value);
        }
    }

    protected void setDataList(@NotNull Integer key, @NotNull List<Integer> values) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.setListData(values);
        }
    }

    protected void setDataMap(@NotNull Integer key, @NotNull Map<Integer, Integer> values) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.setMapData(values);
        }
    }

    protected void setDataSet(@NotNull Integer key, @NotNull Set<Integer> values) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.setSetData(values);
        }
    }

    protected void modifyValueByPercentage(@NotNull Integer key, @NotNull Float percentage) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.modifyByPercentage(percentage);
        }
    }

    protected void modifyValueByPercentage(@NotNull Integer key,
                                           @NotNull Integer dataIndex,
                                           @NotNull Float percentage) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.modifyByPercentage(dataIndex, percentage);
        }
    }

    protected void modifyValueByAddition(@NotNull Integer key, @NotNull Integer toAdd) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.modifyByAddition(toAdd);
        }
    }

    protected void modifyValueByAddition(@NotNull Integer key,
                                         @NotNull Integer dataIndex,
                                         @NotNull Integer toAdd) {
        final DataHolder holder = getHolder(key);
        if (holder != null) {
            holder.modifyByAddition(dataIndex, toAdd);
        }
    }
}

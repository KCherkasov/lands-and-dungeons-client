package ru.kvvartet.lndclient.logic.components.entity.state.dataholders;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.ListAffector;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.MapAffector;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.SetAffector;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.SingleValueAffector;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(SingleValueHolder.class),
               @JsonSubTypes.Type(ListHolder.class),
               @JsonSubTypes.Type(MapHolder.class),
               @JsonSubTypes.Type(SetHolder.class),
               @JsonSubTypes.Type(SingleValueProperty.class),
               @JsonSubTypes.Type(ListProperty.class),
               @JsonSubTypes.Type(MapProperty.class),
               @JsonSubTypes.Type(SetProperty.class),
               @JsonSubTypes.Type(AbilitiesCooldownProperty.class),
               @JsonSubTypes.Type(SingleValueAffector.class),
               @JsonSubTypes.Type(ListAffector.class),
               @JsonSubTypes.Type(MapAffector.class),
               @JsonSubTypes.Type(SetAffector.class)
              })
public interface DataHolder {
    @NotNull Integer getValue();

    @NotNull Integer getValue(@NotNull Integer dataIndex);

    void setSingleValue(@NotNull Integer value);

    void setSingleValue(@NotNull Integer dataIndex, @NotNull Integer value);

    @Nullable List<Integer> getListData();

    void setListData(@NotNull List<Integer> values);

    @Nullable Map<Integer, Integer> getMapData();

    @NotNull Boolean setMapData(@NotNull Map<Integer, Integer> values);

    @Nullable Set<Integer> getSetData();

    @NotNull Boolean setSetData(@NotNull Set<Integer> values);

    @NotNull Boolean modifyByPercentage(@NotNull Float percentage);

    @NotNull Boolean modifyByPercentage(@NotNull Integer dataIndex, @NotNull Float percentage);

    void modifyByAddition(@NotNull Integer toAdd);

    @NotNull Boolean modifyByAddition(@NotNull Integer dataIndex, @NotNull Integer toAdd);
}

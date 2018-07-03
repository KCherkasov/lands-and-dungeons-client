package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.Component;

import java.util.List;

public interface MapNode extends Component {
    @NotNull Boolean getIsPassable();

    void setIsPassable(@NotNull Boolean value);

    @Nullable AliveEntity getInhabitant();

    @NotNull Boolean isOccupied();

    @NotNull Boolean occupy(@NotNull AliveEntity stander);

    void free();

    @Nullable MapNode getAdjacent(@NotNull Integer direction);

    @NotNull List<MapNode> getAdjacentNodes();

    @NotNull Boolean isAdjacentTo(@NotNull MapNode node);

    void setAdjacentNodes(@NotNull List<MapNode> nodes);

    @NotNull List<Integer> getCoordinates();

    @NotNull Integer getCoordinate(@NotNull Integer coordinateIndex);

    @NotNull Integer getH(@NotNull MapNode node);
}

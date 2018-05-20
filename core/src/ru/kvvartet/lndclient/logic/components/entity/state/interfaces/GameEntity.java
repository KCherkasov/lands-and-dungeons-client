package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.Component;

public interface GameEntity extends Component {
    @NotNull String getName();

    @NotNull String getDescription();
}

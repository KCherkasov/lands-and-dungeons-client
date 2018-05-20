package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorProvider;

public interface AffectorProviderOwner {
    @NotNull AffectorProvider getAffectors();
}

package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyProvider;

public interface PropertyProviderOwner {
    @NotNull PropertyProvider getProperties();
}

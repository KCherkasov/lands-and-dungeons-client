package ru.kvvartet.lndclient.logic.components.entity.state.abstracts;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.ModifiablePropertyProvider;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.ModifiablePropertyProviderOwner;

public abstract class ModifiablePropertyProviderComponent extends NameableComponent
        implements ModifiablePropertyProviderOwner {
    protected final ModifiablePropertyProvider properties;

    protected ModifiablePropertyProviderComponent(@NotNull String name,
                                               @NotNull String description,
                                               @NotNull ModifiablePropertyProvider properties) {
        super(name, description);
        this.properties = properties;
    }

    @Override
    public @NotNull ModifiablePropertyProvider getProperties() {
        return properties;
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.abstracts;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyProvider;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.PropertyProviderOwner;

public abstract class PropertyProviderComponent extends NameableComponent implements PropertyProviderOwner {
    protected final PropertyProvider properties;

    protected PropertyProviderComponent(@NotNull String name,
                                        @NotNull String description,
                                        @NotNull PropertyProvider properties) {
        super(name, description);
        this.properties = properties;
    }

    @Override
    public @NotNull PropertyProvider getProperties() {
        return properties;
    }
}

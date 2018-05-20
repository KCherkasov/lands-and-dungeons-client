package ru.kvvartet.lndclient.logic.components.entity.state.abstracts;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorProvider;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyProvider;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.DoubleProviderOwner;

public abstract class DoubleProviderComponent extends NameableComponent implements DoubleProviderOwner {
    protected final PropertyProvider properties;
    protected final AffectorProvider affectors;

    protected DoubleProviderComponent(@NotNull String name,
                                      @NotNull String description,
                                      @NotNull PropertyProvider properties,
                                      @NotNull AffectorProvider affectors) {
        super(name, description);
        this.properties = properties;
        this.affectors = affectors;
    }

    @Override
    public @NotNull PropertyProvider getProperties() {
        return properties;
    }

    @Override
    public @NotNull AffectorProvider getAffectors() {
        return affectors;
    }
}

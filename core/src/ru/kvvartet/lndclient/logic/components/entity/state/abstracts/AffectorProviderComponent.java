package ru.kvvartet.lndclient.logic.components.entity.state.abstracts;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorProvider;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.AffectorProviderOwner;

public abstract class AffectorProviderComponent extends NameableComponent implements AffectorProviderOwner {
    protected final AffectorProvider affectors;

    protected AffectorProviderComponent(@NotNull String name,
                                        @NotNull String description,
                                        @NotNull AffectorProvider affectors) {
        super(name, description);
        this.affectors = affectors;
    }

    @Override
    public @NotNull AffectorProvider getAffectors() {
        return affectors;
    }
}

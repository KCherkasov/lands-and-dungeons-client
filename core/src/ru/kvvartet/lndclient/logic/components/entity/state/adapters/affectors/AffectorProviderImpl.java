package ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolderProvider;
import ru.kvvartet.lndclient.util.DigitsPairIndices;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class AffectorProviderImpl extends DataHolderProvider implements AffectorProvider {
    public AffectorProviderImpl(@NotNull Map<Integer, DataHolder> values) {
        super(values);
    }

    @Override
    public @NotNull Boolean hasAffector(@NotNull Integer key) {
        return containsHolder(key);
    }

    @Override
    public @Nullable DataHolder getAffector(@NotNull Integer key) {
        return getHolder(key);
    }

    @Override
    public @NotNull Integer getAffectorValue(@NotNull Integer key) {
        if ((key & AffectorCategories.AC_REDUCABLE_AFFECTORS) != 0) {
            final Random random = new Random(System.currentTimeMillis());
            return getHolderValue(key, DigitsPairIndices.MINIMAL_VALUE)
                    + random.nextInt(getHolderValue(key, DigitsPairIndices.MAXIMAL_VALUE)
                    - getHolderValue(key, DigitsPairIndices.MINIMAL_VALUE));
        }
        return getHolderValue(key);
    }

    @Override
    public @NotNull Integer getAffectorValue(@NotNull Integer key, @NotNull Integer dataIndex) {
        return getHolderValue(key, dataIndex);
    }

    @Override
    public @NotNull Set<Integer> affectorsKeyset() {
        return keyset();
    }

    @JsonIgnore
    @Override
    public @NotNull Map<Integer, DataHolder> getAvailableAffectors() {
        return getAvailableHolders();
    }
}

package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface CharacterRole {
    @NotNull Map<Integer, Ability> getAllAbilities();

    @Nullable Ability getAbility(@NotNull Integer abilityId);
}

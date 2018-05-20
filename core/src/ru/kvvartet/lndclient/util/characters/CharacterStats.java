package ru.kvvartet.lndclient.util.characters;

import org.jetbrains.annotations.NotNull;

public enum CharacterStats {
    CS_ENDURANCE(0, "Endurance"),
    CS_STRENGTH(1, "Strength"),
    CS_AGILITY(2, "Agility"),
    CS_INTELLIGENCE(3, "Intelligence");

    public static final int CS_SIZE = 4;

    private final Integer index;
    private final String name;

    CharacterStats(@NotNull Integer index, @NotNull String name) {
        this.index = index;
        this.name = name;
    }

    public @NotNull Integer asInt() {
        return index;
    }

    public @NotNull String asText() {
        return name;
    }
}

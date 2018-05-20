package ru.kvvartet.lndclient.util.characters;

import org.jetbrains.annotations.NotNull;

public enum CharacterRatings {
    CR_CRITICAL_HIT(0, "Critical hit chance"),
    CR_DODGE(1, "Dodge chance"),
    CR_BLOCK(2, "Chance to block an opponent\'s attack");

    public static final int CR_SIZE = 3;

    private final Integer value;
    private final String description;

    CharacterRatings(@NotNull Integer value, @NotNull String description) {
        this.value = value;
        this.description = description;
    }

    public @NotNull Integer asInt() {
        return value;
    }

    public @NotNull String asText() {
        return description;
    }
}

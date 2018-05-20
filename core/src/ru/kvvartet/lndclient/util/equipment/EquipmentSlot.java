package ru.kvvartet.lndclient.util.equipment;

import org.jetbrains.annotations.NotNull;

public enum EquipmentSlot {
    ES_HEAD(0, "Head"),
    ES_CHEST(1, "Chest"),
    ES_HANDS(2, "Hands"),
    ES_BOOTS(3, "Boots"),

    ES_MAINHAND(4, "Main hand"),
    ES_OFFHAND(5, "Offhand"),

    ES_RING_ONE(6, "Ring"),
    ES_RING_TWO(7, "Ring"),
    ES_NECKLACE(8, "Necklace");

    public static final int ES_SIZE = 9;

    private final Integer slotId;
    private final String description;

    EquipmentSlot(@NotNull Integer slotId, @NotNull String description) {
        this.slotId = slotId;
        this.description = description;
    }

    public @NotNull Integer asInt() {
        return slotId;
    }

    public @NotNull String asText() {
        return description;
    }
}

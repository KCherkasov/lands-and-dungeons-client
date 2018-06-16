package ru.kvvartet.lndclient.logic.components.entity.state.entities.models.aliveentities;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.DataHolder;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.flyweights.CharacterRace;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.Bag;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.CharacterDoll;
import ru.kvvartet.lndclient.logic.components.entity.state.interfaces.CharacterRole;

import java.util.List;
import java.util.Map;

public class UserCharacterModel extends AbstractAliveEntityModel {
    private final Integer id;
    private final CharacterRace race;
    private final CharacterDoll equipment;
    private final Map<Integer, Map<Integer, Integer>> perkRanks;

    public UserCharacterModel(@NotNull Integer id,
                              @NotNull String name,
                              @NotNull String description,
                              @NotNull Map<Integer, DataHolder> properties,
                              @NotNull List<Bag> bags,
                              @NotNull CharacterRole role,
                              @NotNull CharacterRace race,
                              @NotNull CharacterDoll equipment,
                              @NotNull Map<Integer, Map<Integer, Integer>> perkRanks) {
        super(name, description, properties, bags, role);
        this.id = id;
        this.race = race;
        this.equipment = equipment;
        this.perkRanks = perkRanks;
    }

    public @NotNull Integer getId() {
        return id;
    }

    public @NotNull CharacterRace getRace() {
        return race;
    }

    public @NotNull CharacterDoll getEquipment() {
        return equipment;
    }

    public @NotNull Map<Integer, Map<Integer, Integer>> getPerkRanks() {
        return perkRanks;
    }
}

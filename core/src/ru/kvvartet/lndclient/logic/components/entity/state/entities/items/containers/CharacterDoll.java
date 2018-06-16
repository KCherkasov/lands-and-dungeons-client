package ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers;

import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.affectors.AffectorCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.IngameItem;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.EmptyBagModel;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.FilledBagModel;
import ru.kvvartet.lndclient.util.Constants;
import ru.kvvartet.lndclient.util.characters.CharacterRatings;
import ru.kvvartet.lndclient.util.characters.CharacterStats;
import ru.kvvartet.lndclient.util.equipment.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class CharacterDoll extends StorageBag {
    private static final String EMPTY_CHARACTER_DOLL_NAME = "Equipped items";
    private static final String EMPTY_CHARACTER_DOLL_DESCRIPTION = "Items put on character";

    private static final EmptyBagModel EMPTY_CHARACTER_DOLL =
            new EmptyBagModel(EMPTY_CHARACTER_DOLL_NAME,
                    EMPTY_CHARACTER_DOLL_DESCRIPTION,
                    EquipmentSlot.ES_SIZE);

    public CharacterDoll() {
        super(EMPTY_CHARACTER_DOLL);
    }

    public CharacterDoll(@NotNull FilledBagModel model) {
        super(model);
    }

    public @NotNull Integer getStatBonus(@NotNull Integer statIndex) {
        Integer statBonus = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                statBonus += item.getAffectors().getAffectorValue(
                        AffectorCategories.AC_STATS_AFFECTOR, statIndex);
            }
        }

        return statBonus;
    }

    public @NotNull List<Integer> getStatBonuses() {
        final List<Integer> statBonuses = new ArrayList<>(CharacterStats.CS_SIZE);
        for (Integer i = Constants.ZERO_INT; i < statBonuses.size(); ++i) {
            statBonuses.add(getStatBonus(i));
        }
        return statBonuses;
    }

    public @NotNull Integer getRatingBonus(@NotNull Integer ratingIndex) {
        Integer ratingBonus = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                ratingBonus += item.getAffectors().getAffectorValue(
                        AffectorCategories.AC_RATINGS_AFFECTOR, ratingIndex);
            }
        }

        return ratingBonus;
    }

    public @NotNull List<Integer> getRatingBonuses() {
        final List<Integer> ratingBonuses = new ArrayList<>(CharacterRatings.CR_SIZE);
        for (Integer i = Constants.ZERO_INT; i < ratingBonuses.size(); ++i) {
            ratingBonuses.add(getRatingBonus(i));
        }
        return ratingBonuses;
    }

    public @NotNull Integer getDamage() {
        Integer damage = Constants.ZERO_INT;

        if (getContents().get(EquipmentSlot.ES_MAINHAND.asInt()) != null) {
            damage += getContents().get(EquipmentSlot.ES_MAINHAND.asInt()).getAffectors()
                    .getAffectorValue(AffectorCategories.AC_WEAPON_DAMAGE_AFFECTOR);
        }
        if (getContents().get(EquipmentSlot.ES_OFFHAND.asInt()) != null) {
            damage += getContents().get(EquipmentSlot.ES_OFFHAND.asInt()).getAffectors()
                    .getAffectorValue(AffectorCategories.AC_WEAPON_DAMAGE_AFFECTOR);
        }

        return damage;
    }

    public @NotNull Integer getDefense() {
        Integer defense = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                defense += item.getAffectors().getAffectorValue(
                        AffectorCategories.AC_ARMOUR_DEFENSE_AFFECTOR);
            }
        }

        return defense;
    }

    public @NotNull Integer getDefense(@NotNull Integer armourKind) {
        Integer armourKindDefense = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                if (item.getProperties().getPropertyValue(
                        PropertyCategories.PC_ITEM_KIND).equals(armourKind)) {
                    armourKindDefense += item.getAffectors().getAffectorValue(
                            AffectorCategories.AC_ARMOUR_DEFENSE_AFFECTOR);
                }
            }
        }

        return armourKindDefense;
    }

    public @NotNull Integer getEquipmentAffection(@NotNull Integer affectorKind) {
        Integer affection = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                final Integer itemBonus = item.getAffectors()
                        .getAffectorValue(affectorKind);
                affection += itemBonus != Integer.MIN_VALUE
                        ? itemBonus : Constants.ZERO_INT;
            }
        }

        return affection;
    }

    public @NotNull Integer getEquipmentAffection(@NotNull Integer affectorKind,
                                                  @NotNull Integer dataIndex) {
        Integer affection = Constants.ZERO_INT;

        for (IngameItem item : getContents()) {
            if (item != null) {
                final Integer itemBonus = item.getAffectors()
                        .getAffectorValue(affectorKind, dataIndex);
                affection += itemBonus != Integer.MIN_VALUE
                        ? itemBonus : Constants.ZERO_INT;
            }
        }

        return affection;
    }
}

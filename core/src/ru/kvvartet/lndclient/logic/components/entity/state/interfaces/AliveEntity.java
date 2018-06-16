package ru.kvvartet.lndclient.logic.components.entity.state.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers.Bag;
import ru.kvvartet.lndclient.util.Constants;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface AliveEntity extends Levelable, ModifiablePropertyProviderOwner, Updateable {
    @NotNull Boolean isAlive();

    void affectHitpoints(@NotNull Integer amount);

    default @NotNull Integer getOwnerId() {
        return getProperties().getPropertyValue(
                PropertyCategories.PC_OWNER_ID);
    }

    default @NotNull Boolean isControlledByAI() {
        return ! getProperties().hasProperty(PropertyCategories.PC_OWNER_ID)
                || getProperties().getPropertyValue(
                        PropertyCategories.PC_OWNER_ID) == Constants.UNDEFINED_ID;
    }

    default @Nullable Action makeDecision() {
        return null;
    }

    @NotNull Integer getDamage();

    @NotNull Integer getDefense();

    @NotNull Integer getCash();

    @NotNull Integer getInitiative();

    @NotNull Integer getSpeed();

    @NotNull CharacterRole getCharacterRole();

    default @Nullable Ability getAbility(@NotNull Integer abilityId) {
        return getCharacterRole().getAllAbilities()
                .getOrDefault(abilityId, null);
    }

    default void useAbility(@NotNull Integer abilityId) {
        final Ability ability = getAbility(abilityId);
        if (ability != null && ability.getProperties().getPropertyValue(
                PropertyCategories.PC_COOLDOWN) >= Constants.ZERO_INT) {
            getProperties().setPropertyValue(PropertyCategories.PC_ABILITIES_COOLDOWN, abilityId,
                    ability.getProperties().getPropertyValue(PropertyCategories.PC_COOLDOWN));
        }
    }

    void addEffect(@NotNull Effect effect);

    @NotNull Boolean removeEffect(@NotNull Integer effectIndex);

    @NotNull Boolean removeAllEffects();

    @NotNull Bag getBag(@NotNull Integer bagIndex);

    @JsonIgnore
    default void setBehavior(@NotNull DecisionMaker behavior) {
    }
}

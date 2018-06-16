package ru.kvvartet.lndclient.logic.messages.battle.graphics.tiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.messages.Message;

public class AoeMessage extends Message {
    private final Animation<TextureRegion> ability;

    public AoeMessage(@NotNull Animation<TextureRegion> ability) {
        this.ability = ability;
    }

    public @NotNull Animation<TextureRegion> getAbility() {
        return ability;
    }
}

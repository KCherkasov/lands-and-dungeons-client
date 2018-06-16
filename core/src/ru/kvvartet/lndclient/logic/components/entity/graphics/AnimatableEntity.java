package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.logic.components.entity.state.adapters.properties.PropertyCategories;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.aliveentities.AbstractAliveEntity;
import ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities.DieMessage;
import ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities.HealMessage;
import ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities.HurtMessage;
import ru.kvvartet.lndclient.logic.messages.battle.graphics.aliveentities.MovementMessage;
import ru.kvvartet.lndclient.util.Constants;
import ru.kvvartet.lndclient.util.DigitsPairIndices;
import ru.kvvartet.lndclient.util.graphics.AnimationConfig;
import ru.kvvartet.lndclient.util.graphics.AnimationParts;

import java.util.HashMap;
import java.util.Map;

public class AnimatableEntity extends GameEntityComponent {
    private static final int HEALTHBAR_HEIGHT = 20;
    private static final int HEALTHBAR_WIDTH = 100;

    private final Map<Integer, Animation<TextureRegion>> animations = new HashMap<>();
    private final AnimatableImage sprite;
    private Integer state = AnimationParts.AP_IDLE;

    private final ProgressBar healthbar;

    private final AbstractAliveEntity dataComponent;

    public AnimatableEntity(@NotNull TextureAtlas atlas,
                            @NotNull AbstractAliveEntity dataComponent) {
        super(atlas);
        addAnimation(AnimationParts.AP_IDLE, atlas, AnimationParts.IDLE_ANIMATION_NAME, Animation.PlayMode.LOOP);
        addAnimation(AnimationParts.AP_WALK, atlas, AnimationParts.WALK_ANIMATION_NAME, Animation.PlayMode.LOOP);
        addAnimation(AnimationParts.AP_RUN, atlas, AnimationParts.RUN_ANIMATION_NAME, Animation.PlayMode.LOOP);
        addAnimation(AnimationParts.AP_JUMP, atlas, AnimationParts.JUMP_ANIMATION_NAME, Animation.PlayMode.NORMAL);
        addAnimation(AnimationParts.AP_ATTACK, atlas, AnimationParts.ATTACK_ANIMATION_NAME, Animation.PlayMode.NORMAL);
        addAnimation(AnimationParts.AP_HURT, atlas, AnimationParts.HURT_ANIMATION_NAME, Animation.PlayMode.NORMAL);
        addAnimation(AnimationParts.AP_DIE, atlas, AnimationParts.DEATH_ANIMATION_NAME, Animation.PlayMode.NORMAL);
        sprite = new AnimatableImage(animations.get(AnimationParts.AP_IDLE));

        this.dataComponent = dataComponent;
        healthbar = makeHealthbar();
    }

    @Override
    protected void initHandlers() {
        handlers.put(MovementMessage.class.getName(), message -> {
            state = AnimationParts.AP_WALK;
            updateCurrentAnimation();

            final SequenceAction movementSequence = new SequenceAction();
            for (GameEntityComponent tile : ((MovementMessage) message).getRoute()) {
                final MoveToAction coords = new MoveToAction();
                coords.setX(tile.getWidget().getX());
                coords.setY(tile.getWidget().getY());
                movementSequence.addAction(coords);
            }

            final RunnableAction restoreState = new RunnableAction();
            restoreState.setRunnable(() -> {
                state = AnimationParts.AP_IDLE;
                updateCurrentAnimation();
            });
            movementSequence.addAction(restoreState);

            widget.addAction(movementSequence);
        });
        handlers.put(HurtMessage.class.getName(), message -> {
            state = AnimationParts.AP_HURT;
            updateCurrentAnimation();
            updateHealthbar();
        });
        handlers.put(HealMessage.class.getName(), message -> {
           state = AnimationParts.AP_JUMP;
           updateCurrentAnimation();
           updateHealthbar();
        });
        handlers.put(DieMessage.class.getName(), message -> {
            state = AnimationParts.AP_DIE;
            updateCurrentAnimation();
            // Shall we detach corpse sprite from unit's component and attach it to the tile it died on?
            // It's handy if we don't need it to be selectable, passing it to the tile, but no
            // reviving spells in that case are possible (hence the corps isn't related with ingame component anymore
        });
    }

    @Override
    public void update(float timeDelta) {
        stateCheck();
        super.update(timeDelta);
    }

    private void addAnimation(@NotNull Integer key,
                              @NotNull TextureAtlas atlas,
                              @NotNull String animationName,
                              @NotNull Animation.PlayMode playMode) {
        animations.put(key, new Animation<TextureRegion>(
                AnimationConfig.ANIMATION_SPEED,
                atlas.findRegions(animationName), playMode));
    }

    private void updateCurrentAnimation() {
        sprite.setCurrentAnimation(animations.get(state));
    }

    private void stateCheck() {
        if (state != AnimationParts.AP_DIE
                && sprite.getCurrentAnimation().getPlayMode() == Animation.PlayMode.NORMAL
                && sprite.getCurrentAnimation().isAnimationFinished(sprite.getStateTime())) {
            state = AnimationParts.AP_IDLE;
            updateCurrentAnimation();
        }
    }

    private @NotNull ProgressBar makeHealthbar() {
        final ProgressBar entityHealthbar = new ProgressBar(
                Constants.ZERO_FLOAT, Constants.PERCENTAGE_CAP_FLOAT,
                Constants.PERCENTAGE_STEP_FLOAT, false, makeHealthbarSkin());
        entityHealthbar.setValue(Constants.PERCENTAGE_CAP_FLOAT);
        entityHealthbar.setWidth(HEALTHBAR_WIDTH);
        entityHealthbar.setHeight(HEALTHBAR_HEIGHT);
        entityHealthbar.setAnimateDuration(AnimationConfig.ANIMATION_SPEED);
        return entityHealthbar;
    }

    private @NotNull ProgressBar.ProgressBarStyle makeHealthbarSkin() {
        final ProgressBar.ProgressBarStyle skin = new ProgressBar.ProgressBarStyle();
        skin.background = getColoredDrawable(HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT, Color.BLACK);
        skin.knob = getColoredDrawable(Constants.ZERO_INT, HEALTHBAR_HEIGHT, Color.GREEN);
        skin.knobBefore = getColoredDrawable(HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT, Color.GREEN);
        return skin;
    }

    private @NotNull Drawable getColoredDrawable(@NotNull Integer width,
                                                 @NotNull Integer height,
                                                 @NotNull Color color) {
        final Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        final TextureRegionDrawable drawable = new TextureRegionDrawable(
                new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        return drawable;
    }

    private void updateHealthbar() {
        healthbar.setValue(dataComponent.getProperties().getPropertyValue(
                PropertyCategories.PC_HITPOINTS, DigitsPairIndices.CURRENT_VALUE).floatValue()
                / dataComponent.getProperties().getPropertyValue(PropertyCategories.PC_HITPOINTS,
                DigitsPairIndices.MAXIMAL_VALUE).floatValue());
    }
}

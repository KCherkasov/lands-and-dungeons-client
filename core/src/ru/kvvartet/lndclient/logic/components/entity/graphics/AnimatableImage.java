package ru.kvvartet.lndclient.logic.components.entity.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.util.Constants;

public class AnimatableImage extends Image {
    private Animation<TextureRegion> currentAnimation = null;
    private float stateTime = Constants.ZERO_FLOAT;

    public AnimatableImage(@NotNull Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(Constants.ZERO_FLOAT));
        currentAnimation = animation;
    }

    @Override
    public void act(float timeDelta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(
                currentAnimation.getKeyFrame(stateTime += timeDelta, true));
        super.act(timeDelta);
    }

    public @NotNull Animation<TextureRegion> getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(@NotNull Animation<TextureRegion> newAnimation) {
        currentAnimation = newAnimation;
        stateTime = Constants.ZERO_FLOAT;
    }

    public float getStateTime() {
        return stateTime;
    }
}

package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.util.Constants;

public class Tooltip extends Window {
    public Tooltip(@NotNull Skin skin) {
        super (Constants.EMPTY_LINE, skin);
    }
}

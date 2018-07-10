package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.state.configkeys.AboutStateKeys;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.Objects;

public class TooltipCaption implements TooltipEntry {
    private final String caption;

    public TooltipCaption(@NotNull String caption) {
        this.caption = caption;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void toActor(@NotNull Table target) {
        final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerImpl.getInstance().getFontAssets();
        if (fontAssetHolder != null) {
            final Label.LabelStyle titleStyle = new Label.LabelStyle();
            titleStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                    AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_BIG.asText()
                            + Extensions.TTF));
            target.add(new Label(caption, titleStyle)).colspan(DOUBLE_COLSPAN).row();
        }
        Gdx.app.error(getClass().getName(), "fontAssetHolder == null");
    }
}

package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.state.configkeys.AboutStateKeys;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SingleValueHolder;
import ru.kvvartet.lndclient.util.Constants;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.Objects;

public class SingleValueField extends AbstractTooltipField<SingleValueHolder> {
    public SingleValueField(@NotNull String caption, @NotNull SingleValueHolder value) {
        super(caption, value);
    }

    @Override
    protected void makeActor(@NotNull Table target) {
        final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerImpl.getInstance().getFontAssets();
        if (fontAssetHolder != null) {
            final Label.LabelStyle titleStyle = new Label.LabelStyle();
            titleStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                    AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_BIG.asText()
                            + Extensions.TTF));
            final Label.LabelStyle valueStyle = new Label.LabelStyle();
            valueStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                    AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_TEXT.asText()
                            + Extensions.TTF));
            target.add(new Label(getCaption() + Constants.COLON, titleStyle));
            target.add(new Label(getValue().getValue().toString(), valueStyle)).row();
        }
        Gdx.app.error(getClass().getName(), "fontAssetHolder == null");
    }
}

package ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.multivalue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.jetbrains.annotations.NotNull;
import ru.kvvartet.lndclient.assets.holders.fonts.BitmapFontAssetHolder;
import ru.kvvartet.lndclient.assets.manager.ClientAssetManagerImpl;
import ru.kvvartet.lndclient.client.states.state.configkeys.AboutStateKeys;
import ru.kvvartet.lndclient.logic.components.entity.graphics.tooltips.data.entries.fields.AbstractTooltipField;
import ru.kvvartet.lndclient.logic.components.entity.state.dataholders.SetHolder;
import ru.kvvartet.lndclient.tooltipcaptionparser.ConfigParser;
import ru.kvvartet.lndclient.util.Constants;
import ru.kvvartet.lndclient.util.assets.Extensions;
import ru.kvvartet.lndclient.util.assets.FontSizeSuffices;

import java.util.Map;
import java.util.Objects;

public abstract class MultiValueField<T> extends AbstractTooltipField<T> {
    private static final String CAPTIONS_MAP_FILE_NAME = "";
    private static final Map<String, Map<Integer, String>> CAPTIONS = initCaptionsMap();

    private final Map<Integer, String> captionsMap;

    public MultiValueField(@NotNull String caption, @NotNull T values, @NotNull String captionsMapName) {
        super(caption, values);
        captionsMap = CAPTIONS.getOrDefault(captionsMapName, null);
        if (captionsMap == null) {
            Gdx.app.error(getClass().getName(), "captions map doesn\'t exist: " + captionsMapName);
        }
    }

    @Override
    protected void makeActor(@NotNull Table target) {
        final BitmapFontAssetHolder fontAssetHolder = ClientAssetManagerImpl.getInstance().getFontAssets();
        if (fontAssetHolder != null) {
            final Table subTable = new Table();
            final Label.LabelStyle titleStyle = new Label.LabelStyle();
            titleStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                    AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_BIG.asText()
                            + Extensions.TTF));
            final Label.LabelStyle valueStyle = new Label.LabelStyle();
            valueStyle.font = Objects.requireNonNull(fontAssetHolder.getAsset(
                    AboutStateKeys.FONT_KEY + FontSizeSuffices.FS_TEXT.asText()
                            + Extensions.TTF));
            subTable.add(new Label(getCaption(), titleStyle)).colspan(DOUBLE_COLSPAN).row();
            for (Integer key : Objects.requireNonNull(captionsMap).keySet()) {
                final String textValue = getStringValue(key);
                if (!textValue.isEmpty()) {
                    subTable.add(new Label(captionsMap.get(key) + Constants.COLON, titleStyle))
                            .colspan(!getValue().getClass().equals(SetHolder.class)
                                    ? DEFAULT_COLSPAN : DOUBLE_COLSPAN);
                    if (!getValue().getClass().equals(SetHolder.class)) {
                        subTable.add(new Label(textValue, valueStyle)).row();
                    }
                }
            }
            target.add(subTable).colspan(2).row();
        }
        Gdx.app.error(getClass().getName(), "fontAssetHolder == null");
    }

    protected abstract String getStringValue(@NotNull Integer key);

    private static @NotNull Map<String, Map<Integer, String>> initCaptionsMap() {
        return Objects.requireNonNull(new ConfigParser().parseConfig(CAPTIONS_MAP_FILE_NAME));
    }
}

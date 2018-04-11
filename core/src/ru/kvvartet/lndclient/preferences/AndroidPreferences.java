package ru.kvvartet.lndclient.preferences;

import org.jetbrains.annotations.NotNull;

public final class AndroidPreferences extends AbstractPreferences {
    private static final String SETTINGS_FILE_NAME = "settings.apf";

    private static final AndroidPreferences INSTANCE = new AndroidPreferences();

    private AndroidPreferences() {
        super(SETTINGS_FILE_NAME);
    }

    public @NotNull AndroidPreferences getInstance() {
        return INSTANCE;
    }
}

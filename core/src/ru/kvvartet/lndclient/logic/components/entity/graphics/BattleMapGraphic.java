package ru.kvvartet.lndclient.logic.components.entity.graphics;

import java.util.ArrayList;
import java.util.List;

public class BattleMapGraphic extends GraphicsComponent {
    private final List<List<MapNodeGraphic>> map = new ArrayList<>();
    // TODO: BattleMap data component here
    // TODO: ? Maybe some handle towards player input component ?

    public BattleMapGraphic(/* @NotNull BattleMapDataComponent dataComponent */) {
        super();
        // TODO: map graphic generation basing on data component
    }

    @Override
    public void dispose() {}

    @Override
    public void initHandlers() {

    }
}

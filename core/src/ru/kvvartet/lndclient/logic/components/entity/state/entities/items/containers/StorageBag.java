package ru.kvvartet.lndclient.logic.components.entity.state.entities.items.containers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kvvartet.lndclient.logic.components.entity.state.abstracts.NameableComponent;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.items.IngameItem;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.EmptyBagModel;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.FilledBagModel;
import ru.kvvartet.lndclient.logic.components.entity.state.entities.models.items.ItemModel;
import ru.kvvartet.lndclient.logic.messages.bag.ItemAddMessage;
import ru.kvvartet.lndclient.logic.messages.bag.ItemRemoveMessage;
import ru.kvvartet.lndclient.logic.messages.bag.SwapItemsMessage;
import ru.kvvartet.lndclient.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StorageBag extends NameableComponent implements Bag {
    private static final AtomicInteger BAGS_COUNTER = new AtomicInteger(0);

    private final Integer bagID;

    private final List<IngameItem> contents;

    public StorageBag(@NotNull EmptyBagModel model) {
        super(model.getName(), model.getDescription());
        bagID = BAGS_COUNTER.getAndIncrement();
        contents = new ArrayList<>(model.getBagSize());
        for (Integer i = Constants.ZERO_INT; i < model.getBagSize(); ++i) {
            contents.add(null);
        }
    }

    public StorageBag(@NotNull FilledBagModel model) {
        super(model.getName(), model.getDescription());
        bagID = model.getId();
        contents = new ArrayList<>(model.getContents().size());
        for (ItemModel itemModel : model.getContents()) {
            if (itemModel == null) {
                contents.add(null);
            } else {
                contents.add(new IngameItem(itemModel));
            }
        }
    }

    @JsonProperty("id")
    public @NotNull Integer getBagId() {
        return bagID;
    }

    @Override
    @JsonProperty("slotsCount")
    public @NotNull Integer getSlotsCount() {
        return contents.size();
    }

    @Override
    @JsonProperty("freeSlots")
    public @NotNull Integer getFreeSlotsCount() {
        Integer freeSlots = Constants.ZERO_INT;
        for (IngameItem item : contents) {
            if (item == null) {
                ++freeSlots;
            }
        }
        return freeSlots;
    }

    @Override
    public @Nullable IngameItem getItem(@NotNull Integer pos) {
        if (!isInBounds(pos)) {
            return null;
        }
        return contents.get(pos);
    }

    @Override
    public @NotNull Boolean addItem(@Nullable IngameItem item, @NotNull Integer toPos) {
        if (!isInBounds(toPos)) {
            return false;
        }
        if (contents.get(toPos) != null) {
            return false;
        }
        contents.set(toPos, item);
        return true;
    }

    @Override
    public @NotNull Boolean addItem(@NotNull IngameItem item) {
        return addItem(item, getFirstFreeSlot());
    }

    @Override
    public @NotNull Boolean removeItem(@NotNull Integer fromPos, @NotNull Boolean isConfirmed) {
        if (!isConfirmed || !isInBounds(fromPos)) {
            return false;
        }
        contents.set(fromPos, null);
        return true;
    }

    @Override
    public void dispose() {
    }

    @Override
    protected void initHandlers() {
        handlers.put(SwapItemsMessage.class.getName(), message -> {
            if (message.getClass().equals(SwapItemsMessage.class)) {
                final SwapItemsMessage converted = (SwapItemsMessage) message;
                if (converted.getToBag() == null) {
                    swap(converted.getFromPos(), converted.getToPos());
                } else {
                    swap(converted.getFromPos(), converted.getToBag(),
                            converted.getToPos());
                }
            }
        });
        handlers.put(ItemAddMessage.class.getName(), message -> {
           if (message.getClass().equals(ItemAddMessage.class)) {
              final ItemAddMessage converted = (ItemAddMessage) message;
              addItem(new IngameItem(converted.getToAdd()), converted.getToPos());
           }
        });
        handlers.put(ItemRemoveMessage.class.getName(), message -> {
            if (message.getClass().equals(ItemRemoveMessage.class)) {
                final ItemRemoveMessage converted = (ItemRemoveMessage) message;
                removeItem(converted.getFromPos(), converted.getConfirmation());
            }
        });
    }

    protected @NotNull List<IngameItem> getContents() {
        return contents;
    }

    private @NotNull Integer getFirstFreeSlot() {
        if (contents.isEmpty()) {
            return Constants.UNDEFINED_ID;
        }
        Integer slotIndex = Constants.ZERO_INT;
        for (IngameItem item : contents) {
            if (item == null) {
                return slotIndex;
            }
            ++slotIndex;
        }
        return Constants.UNDEFINED_ID;
    }

    private void swap(@NotNull Integer fromPos, @NotNull Integer toPos) {
        if (!isInBounds(fromPos) || !isInBounds(toPos)) {
            return;
        }
        final IngameItem item = contents.get(fromPos);
        contents.set(fromPos, getItem(toPos));
        contents.set(toPos, item);
    }

    private void swap(@NotNull Integer fromPos, @NotNull Bag toBag, @NotNull Integer toPos) {
        if (!isInBounds(fromPos) || toPos < 0 || toPos >= toBag.getSlotsCount()) {
            return;
        }
        final IngameItem item = contents.get(fromPos);
        contents.set(fromPos, toBag.getItem(toPos));
        toBag.removeItem(toPos, true);
        toBag.addItem(item, toPos);
    }

    private @NotNull Boolean isInBounds(@NotNull Integer itemIndex) {
        return itemIndex > Constants.ZERO_INT && itemIndex < contents.size();
    }
}

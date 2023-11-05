package com.st0x0ef.beyond_earth.common.events.forge;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.item.ItemEvent;

public class ItemEntityTickAtEndEvent extends ItemEvent {

    public ItemEntityTickAtEndEvent(ItemEntity entity) {
        super(entity);
    }
}

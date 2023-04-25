package net.mrscauthd.beyond_earth.common.menus.nasaworkbench;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.NASAWorkbenchBlockEntity;

public class NasaWorkbenchResultSlot extends Slot {

    private final NASAWorkbenchBlockEntity blockEntity;

    public NasaWorkbenchResultSlot(Container inventory, int slotIndex, int xPos, int yPos,
            NASAWorkbenchBlockEntity blockEntity) {
        super(inventory, slotIndex, xPos, yPos);
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack p_40231_) {
        return false;
    }

    public NASAWorkbenchBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
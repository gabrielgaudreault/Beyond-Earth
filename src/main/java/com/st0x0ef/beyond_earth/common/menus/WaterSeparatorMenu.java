package com.st0x0ef.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.WaterSeparatorBlockEntity;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.OxygenMakingBlockEntity;
import com.st0x0ef.beyond_earth.common.menus.helper.MenuHelper;
import com.st0x0ef.beyond_earth.common.registries.ContainerRegistry;

public class WaterSeparatorMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            WaterSeparatorBlockEntity blockEntity = (WaterSeparatorBlockEntity) inv.player.level().getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final WaterSeparatorBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, WaterSeparatorBlockEntity blockEntity) {
            super(ContainerRegistry.WATER_SEPARATOR_GUI.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable internal = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_INPUT_SOURCE, 8, 31));
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_INPUT_SINK, 8, 61));
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_OUTPUT_SOURCE_H2, 123, 31));
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_OUTPUT_SINK_H2, 123, 61));
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_OUTPUT_SOURCE_O2, 153, 31));
            this.addSlot(new SlotItemHandler(internal, WaterSeparatorBlockEntity.SLOT_OUTPUT_SINK_O2, 153, 61));

            MenuHelper.createInventorySlots(inv, this::addSlot, 41, 102);
        }

        public WaterSeparatorBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public boolean stillValid(Player player) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            return MenuHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(), this::moveItemStackTo);
        }
    }
}

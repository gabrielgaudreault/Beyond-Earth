package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.RocketUpgraderBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class RocketUpgraderMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            RocketUpgraderBlockEntity blockEntity = (RocketUpgraderBlockEntity) inv.player.level().getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final RocketUpgraderBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, RocketUpgraderBlockEntity blockEntity) {
            super(ContainerRegistry.ROCKET_UPGRADER_GUI.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable internal = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(internal, RocketUpgraderBlockEntity.SLOT_ROCKET_INPUT, 21, 56));
            this.addSlot(new SlotItemHandler(internal, RocketUpgraderBlockEntity.SLOT_UPGRADE_INPUT, 68, 56));
            this.addSlot(new SlotItemHandler(internal, RocketUpgraderBlockEntity.SLOT_OUTPUT, 132, 56));

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 142);
        }

        public RocketUpgraderBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public boolean stillValid(Player p_38874_) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            return MenuHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(), this::moveItemStackTo);
        }
    }
}

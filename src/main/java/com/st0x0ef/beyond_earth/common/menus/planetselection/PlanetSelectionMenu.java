package com.st0x0ef.beyond_earth.common.menus.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.registries.ContainerRegistry;

public class PlanetSelectionMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            return new GuiContainer(id, inv, extraData);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final Player player;
        private final double distance;

        public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
            super(ContainerRegistry.PLANET_SELECTION_GUI.get(), id);
            this.player = inv.player;
            distance = this.player.getPersistentData().getDouble(BeyondEarth.MODID + ":rocket_distance");
        }

        public double getDistance() {
            return distance;
        }

        public ItemStack quickMoveStack(Player player, int p_219988_) {
            return ItemStack.EMPTY;
        }

        @Override
        public boolean stillValid(Player player) {
            return !player.isDeadOrDying();
        }

        public Player getPlayer() {
            return player;
        }
    }
}

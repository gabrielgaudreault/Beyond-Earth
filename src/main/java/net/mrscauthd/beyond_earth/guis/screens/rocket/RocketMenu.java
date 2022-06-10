package net.mrscauthd.beyond_earth.guis.screens.rocket;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;
import net.minecraftforge.network.IContainerFactory;

import net.mrscauthd.beyond_earth.entities.*;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.helper.ContainerHelper;
import net.mrscauthd.beyond_earth.registries.ScreensRegistry;
import net.mrscauthd.beyond_earth.registries.TagsRegistry;
import org.jetbrains.annotations.NotNull;

public class RocketMenu {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}


	public static class GuiContainer extends AbstractContainerMenu {
		Entity rocket;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ScreensRegistry.ROCKET_GUI.get(), id);

			this.rocket = inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = null;

			if (rocket instanceof IRocketEntity) {
				itemHandler = ((IRocketEntity) rocket).getItemHandler();
			}

			this.addSlot(new SlotItemHandler(itemHandler, 0, 46, 22) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return Methods.tagCheck(FluidUtil2.findBucketFluid(stack.getItem()), TagsRegistry.FLUID_VEHICLE_FUEL_TAG);
				}
			});

			ContainerHelper.addInventorySlots(this, inv, 8, 84, this::addSlot);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !rocket.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			return ContainerHelper.transferStackInSlot(this, playerIn, index, 0, 1, this::moveItemStackTo);
		}
	}
}
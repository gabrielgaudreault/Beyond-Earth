package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.OxygenMakingRecipeAbstract;
import net.mrscauthd.beyond_earth.common.menus.OxygenLoaderMenu;
import net.mrscauthd.beyond_earth.common.menus.RocketUpgraderMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;

public class RocketUpgraderBlockEntity extends AbstractMachineBlockEntity {

    public static final int SLOT_ROCKET_INPUT = 0;
    public static final int SLOT_UPGRADE_INPUT = 1;
    public static final int SLOT_OUTPUT = 2;

    public RocketUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ROCKET_UPGRADER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getSlotUpgradeInput()) {
            return stack.is(TagRegistry.ROCKET_UPGRADE_TAG);
        }
        else if (index == this.getSlotRocketInput()) {
            return stack.is(ItemsRegistry.ROCKET_ITEM.get());
        }
        else if (index == this.getSlotOutput()) {
            return false;
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getSlotRocketInput() || index == this.getSlotUpgradeInput()) {
            return false;
        } else if (index == this.getSlotOutput()) {
            return true;
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    protected void tickProcessing() {

    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(this.getSlotRocketInput());
        slots.add(this.getSlotUpgradeInput());
        slots.add(this.getSlotOutput());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 3;
    }

    public int getSlotRocketInput() {
        return SLOT_ROCKET_INPUT;
    }
    public int getSlotUpgradeInput() {
        return SLOT_UPGRADE_INPUT;
    }
    public int getSlotOutput() {
        return SLOT_OUTPUT;
    }
}

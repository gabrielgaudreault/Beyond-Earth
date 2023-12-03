package com.st0x0ef.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import com.st0x0ef.beyond_earth.common.items.RoverItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import com.st0x0ef.beyond_earth.common.items.RocketItem;
import com.st0x0ef.beyond_earth.common.items.VehicleUpgradeItem;
import com.st0x0ef.beyond_earth.common.menus.VehicleUpgraderMenu;
import com.st0x0ef.beyond_earth.common.registries.BlockEntityRegistry;
import com.st0x0ef.beyond_earth.common.registries.ItemsRegistry;
import com.st0x0ef.beyond_earth.common.registries.TagRegistry;

public class VehicleUpgraderBlockEntity extends AbstractMachineBlockEntity {

    public static final int SLOT_UPGRADE_INPUT = 0;
    public static final int SLOT_VEHICLE_INPUT = 1;
    public static final int SLOT_OUTPUT = 2;

    public VehicleUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.VEHICLE_UPGRADER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getSlotUpgradeInput()) {
            return stack.is(TagRegistry.ROCKET_UPGRADE_TAG);
        }
        else if (index == this.getSlotVehicleInput()) {
            return stack.is(ItemsRegistry.ROCKET_ITEM.get()) || stack.is(ItemsRegistry.ROCKET_ITEM.get());
        }
        else if (index == this.getSlotOutput()) {
            return false;
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getSlotVehicleInput() || index == this.getSlotUpgradeInput()) {
            return false;
        } else if (index == this.getSlotOutput()) {
            return true;
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    protected void tickProcessing() {
        ItemStack upgrade_input = this.getItem(getSlotUpgradeInput());
        ItemStack rocket_input = this.getItem(getSlotVehicleInput());

        if (!upgrade_input.isEmpty() && !rocket_input.isEmpty() && hasSpaceInOutput()) {
            ItemStack output = rocket_input.copy();

            if (output.getItem() instanceof RocketItem rocket) {
                if (upgrade_input.getItem() instanceof VehicleUpgradeItem upgrade) {

                    if (upgrade.getRocketSkinTexture() != null) {
                        rocket.setRocketSkinTexture(upgrade.getRocketSkinTexture());
                    }

                    else if (upgrade.getRocketModel() != null) {
                        rocket.setRocketModel(upgrade.getRocketModel(), upgrade.getRocketModelName());
                    }

                    rocket.fuelCapacityModifier = upgrade.getFuelCapacityModifier() > 0 ? upgrade.getFuelCapacityModifier() : rocket.fuelCapacityModifier;
                    rocket.fuelUsageModifier = upgrade.getFuelUsageModifier() > 0 ? upgrade.getFuelUsageModifier() : rocket.fuelUsageModifier;
                    output = rocket.asItem().getDefaultInstance();

                    output.getOrCreateTag().putInt("fuelCapacityModifier", rocket.fuelCapacityModifier);
                    output.getOrCreateTag().putInt("fuelUsageModifier", rocket.fuelUsageModifier);

                    this.removeItem(getSlotUpgradeInput(), 1);
                    this.removeItem(getSlotVehicleInput(), 1);
                    this.setItem(getSlotOutput(), output);
                }
            }

            if (output.getItem() instanceof RoverItem rover) {
                if (upgrade_input.getItem() instanceof VehicleUpgradeItem upgrade) {
                    rover.fuelCapacityModifier = upgrade.getFuelCapacityModifier() > 0 ? upgrade.getFuelCapacityModifier() : rover.fuelCapacityModifier;
                    output = rover.asItem().getDefaultInstance();

                    output.getOrCreateTag().putInt("fuelCapacityModifier", rover.fuelCapacityModifier);

                    this.removeItem(getSlotUpgradeInput(), 1);
                    this.removeItem(getSlotVehicleInput(), 1);
                    this.setItem(getSlotOutput(), output);
                }
            }
        }
     }

    @Override
    public boolean hasSpaceInOutput() {
        return this.getItem(getSlotOutput()).isEmpty();
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(this.getSlotVehicleInput());
        slots.add(this.getSlotUpgradeInput());
        slots.add(this.getSlotOutput());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new VehicleUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 3;
    }

    public int getSlotVehicleInput() {
        return SLOT_VEHICLE_INPUT;
    }
    public int getSlotUpgradeInput() {
        return SLOT_UPGRADE_INPUT;
    }
    public int getSlotOutput() {
        return SLOT_OUTPUT;
    }
}

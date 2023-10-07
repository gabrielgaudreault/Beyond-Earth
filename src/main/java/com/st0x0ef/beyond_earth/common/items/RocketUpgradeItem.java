package com.st0x0ef.beyond_earth.common.items;

import net.minecraft.world.item.Item;

public class RocketUpgradeItem extends Item {
    private final int fuelCapacityModifier;
    private final int fuelUsageModifier;

    public RocketUpgradeItem(Properties properties, int fuelCapacityModifier, int fuelUsageModifier) {
        super(properties);

        this.fuelCapacityModifier = fuelCapacityModifier;
        this.fuelUsageModifier = fuelUsageModifier;
    }

    public int getFuelCapacityModifier() {
        return  fuelCapacityModifier;
    }

    public int getFuelUsageModifier() {
        return fuelUsageModifier;
    }
}

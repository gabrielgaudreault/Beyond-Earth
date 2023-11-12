package com.st0x0ef.beyond_earth.common.items;

import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class VehicleUpgradeItem extends Item {
    private final int fuelCapacityModifier;
    private final int fuelUsageModifier;
    private final String rocketSkinTexture;

    public VehicleUpgradeItem(Properties properties, int fuelCapacityModifier, int fuelUsageModifier, @Nullable String rocketSkinTexture) {
        super(properties);

        this.fuelCapacityModifier = fuelCapacityModifier;
        this.fuelUsageModifier = fuelUsageModifier;
        this.rocketSkinTexture = rocketSkinTexture;

    }

    public int getFuelCapacityModifier() {
        return  fuelCapacityModifier;
    }

    public int getFuelUsageModifier() {
        return fuelUsageModifier;
    }

    public String getRocketSkinTexture() {
        return rocketSkinTexture;
    }

}

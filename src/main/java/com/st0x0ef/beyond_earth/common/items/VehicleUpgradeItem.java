package com.st0x0ef.beyond_earth.common.items;

import com.st0x0ef.beyond_earth.client.registries.ItemRendererRegistry;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class VehicleUpgradeItem extends Item {
    private final int fuelCapacityModifier;
    private final int fuelUsageModifier;
    private final String rocketSkinTexture;
    private final String rocketModelName;

    public VehicleUpgradeItem(Properties properties, int fuelCapacityModifier, int fuelUsageModifier, @Nullable String rocketSkinTexture, @Nullable String rocketModelName) {
        super(properties);

        this.fuelCapacityModifier = fuelCapacityModifier;
        this.fuelUsageModifier = fuelUsageModifier;
        this.rocketSkinTexture = rocketSkinTexture == null ? "" : rocketSkinTexture;
        this.rocketModelName = rocketModelName == null ? "" : rocketModelName;
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
    public String getRocketModelName() {
        return rocketModelName;
    }
}


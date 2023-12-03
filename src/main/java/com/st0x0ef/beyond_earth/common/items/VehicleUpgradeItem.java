package com.st0x0ef.beyond_earth.common.items;

import com.st0x0ef.beyond_earth.client.registries.ItemRendererRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class VehicleUpgradeItem extends Item {
    private final int fuelCapacityModifier;
    private final int fuelUsageModifier;
    private final String rocketSkinTexture;
    private final String rocketModelName;
    private final BlockEntityWithoutLevelRenderer rocketModel;

    public VehicleUpgradeItem(Properties properties, int fuelCapacityModifier, int fuelUsageModifier, @Nullable String rocketSkinTexture, @Nullable String rocketModelName, @Nullable BlockEntityWithoutLevelRenderer rocketModel) {
        super(properties);

        this.fuelCapacityModifier = fuelCapacityModifier;
        this.fuelUsageModifier = fuelUsageModifier;
        this.rocketSkinTexture = rocketSkinTexture;
        this.rocketModelName = rocketModelName;
        this.rocketModel = rocketModel;
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
    public BlockEntityWithoutLevelRenderer getRocketModel() {
        return rocketModel;
    }
    public String getRocketModelName() {
        return rocketModelName;
    }
}


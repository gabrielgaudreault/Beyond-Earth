package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.registries.ItemRendererRegistry;

import java.util.function.Consumer;

public class RocketUpgradeItem extends Item {
    public RocketUpgradeItem(Properties properties, int fuelLimitModifier, int fuelUsageModifier) {
        super(properties);

        if (Minecraft.getInstance().level != null) {
            CompoundTag upgrade = getDefaultInstance().getOrCreateTagElement(BeyondEarth.MODID + ":upgrade");
            upgrade.putInt("fuelLimit", fuelLimitModifier);
            upgrade.putInt("fuelUsage", fuelUsageModifier);
        }
    }


}

package com.st0x0ef.beyond_earth.common.capabilities.hydrogen;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class HydrogenProvider implements ICapabilityProvider, IHydrogenStorageHolder {
    public static Capability<HydrogenStorage> HYDROGEN = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final String KEY_HYDROGEN = "Hydrogen"; // for compatible other code

    private final ItemStack itemStack;
    private final IHydrogenStorage hydrogenStorage;

    public HydrogenProvider(ItemStack itemStack, int capacity) {
        this.itemStack = itemStack;
        this.hydrogenStorage = new HydrogenStorage(this, capacity);

        this.readHydrogen();
    }

    private void readHydrogen() {
        CompoundTag compound = this.getItemStack().getOrCreateTag();
        this.getHydrogenStorage().setHydrogen(compound.getInt(KEY_HYDROGEN));
    }

    public void writeHydrogen() {
        CompoundTag compound = this.getItemStack().getOrCreateTag();
        compound.putInt(KEY_HYDROGEN, this.getHydrogenStorage().getHydrogen());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) {
        LazyOptional<T> hydrogenCapability = HydrogenUtil.getHydrogenCapability(capability, this::getHydrogenStorage);

        if (hydrogenCapability.isPresent()) {
            return hydrogenCapability;
        }

        return LazyOptional.empty();
    }

    @Override
    public void onHydrogenChanged(IHydrogenStorage hydrogenStorage, int hydrogenDelta) {
        this.writeHydrogen();
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public IHydrogenStorage getHydrogenStorage() {
        return this.hydrogenStorage;
    }
}

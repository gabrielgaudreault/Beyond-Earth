package com.st0x0ef.beyond_earth.common.capabilities.hydrogen;

import com.st0x0ef.beyond_earth.common.registries.FluidRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;

public class HydrogenStorage implements IHydrogenStorage {
    private int hydrogen;
    private int capacity;

    private final IHydrogenStorageHolder listener;

    public HydrogenStorage(IHydrogenStorageHolder holder, int capacity) {
        this(holder, capacity, 0);
    }

    public HydrogenStorage(IHydrogenStorageHolder holder, int capacity, int hydrogen) {
        this.listener = holder;
        this.capacity = capacity;
        this.hydrogen = Mth.clamp(hydrogen, 0, capacity);
    }

    public HydrogenStorage(IHydrogenStorageHolder listener) {
        this.listener = listener;
    }

    public void setMaxCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getHydrogen() {
        return this.hydrogen;
    }

    public Fluid getFluid() {
        return FluidRegistry.HYDROGEN_STILL.get();
    }

    @Override
    public void setHydrogen(int hydrogen) {
        hydrogen = Mth.clamp(hydrogen, 0, this.getMaxCapacity());
        int dH2 = hydrogen - this.hydrogen;
        this.hydrogen = hydrogen;
        if (listener != null)
            listener.onHydrogenChanged(this, dH2);
    }

    @Override
    public int getMaxCapacity() {
        return this.capacity;
    }

    @Override
    public double getHydrogenStoredRatio() {
        return (double) this.getHydrogen() / (double) this.getMaxCapacity();
    }

    @Override
    public int receiveHydrogen(int maxReceive, boolean simulate) {
        int hydrogen = (int) Mth.clamp((long) this.hydrogen + maxReceive, 0L, this.getMaxCapacity());
        int dH2 = hydrogen - this.hydrogen;
        if (!simulate) {
            this.setHydrogen(hydrogen);
        }
        return dH2;
    }

    @Override
    public int extractHydrogen(int maxExtract, boolean simulate) {
        int hydrogen = (int) Mth.clamp((long) this.hydrogen - maxExtract, 0L, this.getMaxCapacity());
        int dH2 = this.hydrogen - hydrogen;
        if (!simulate) {
            this.setHydrogen(hydrogen);
        }
        return dH2;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("hydrogenStorage", this.getHydrogen());
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.hydrogen = nbt.getInt("hydrogenStorage");
    }
}

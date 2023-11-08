package com.st0x0ef.beyond_earth.common.capabilities.hydrogen;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IHydrogenStorage extends INBTSerializable<CompoundTag> {

    int receiveHydrogen(int maxReceive, boolean simulate);

    int extractHydrogen(int maxExtract, boolean simulate);

    int getHydrogen();

    void setHydrogen(int hydrogen);

    int getMaxCapacity();

    default double getHydrogenStoredRatio() {
        return (double) this.getHydrogen() / (double) this.getMaxCapacity();
    }
}

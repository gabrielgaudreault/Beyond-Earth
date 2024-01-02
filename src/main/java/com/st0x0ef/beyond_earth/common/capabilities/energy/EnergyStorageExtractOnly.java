package com.st0x0ef.beyond_earth.common.capabilities.energy;

import net.minecraftforge.energy.IEnergyStorage;

public record EnergyStorageExtractOnly(IEnergyStorage parent, int maxExtract) implements IEnergyStorage {

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return this.parent().extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return this.parent().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.parent().getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract() > 0;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

}

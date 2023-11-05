package com.st0x0ef.beyond_earth.common.blocks.entities.machines.power;

import net.minecraftforge.energy.IEnergyStorage;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.AbstractMachineBlockEntity;

public abstract class PowerSystemEnergyCommon extends PowerSystemEnergy {

    public PowerSystemEnergyCommon(AbstractMachineBlockEntity blockEntity) {
            super(blockEntity);
    }

    public PowerSystemEnergyCommon(AbstractMachineBlockEntity blockEntity, IEnergyStorage energyStorage) {
            super(blockEntity, energyStorage);
    }

    @Override
    public int getBasePowerPerTick() {
            return 0;
    }

}

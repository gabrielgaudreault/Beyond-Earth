package com.st0x0ef.beyond_earth.common.blocks.entities.machines.power;

import java.io.Serial;
import java.util.LinkedHashMap;

import net.minecraft.resources.ResourceLocation;

public class PowerSystemRegistry extends LinkedHashMap<ResourceLocation, PowerSystem> {

    @Serial
    private static final long serialVersionUID = 1L;

    public PowerSystemRegistry() {

    }

    public void put(PowerSystem powerSystem) {
            this.put(powerSystem.getName(), powerSystem);
    }

}

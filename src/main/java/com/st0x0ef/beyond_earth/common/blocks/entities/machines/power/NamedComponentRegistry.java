package com.st0x0ef.beyond_earth.common.blocks.entities.machines.power;

import java.io.Serial;
import java.util.LinkedHashMap;

import net.minecraft.resources.ResourceLocation;
import com.st0x0ef.beyond_earth.BeyondEarth;

public class NamedComponentRegistry<T> extends LinkedHashMap<ResourceLocation, T> {
    public static final ResourceLocation UNNAMED = new ResourceLocation(BeyondEarth.MODID, "unnamed");
    @Serial
    private static final long serialVersionUID = 1L;

    public void put(T value) {
        if (this.containsKey(UNNAMED)) {
            throw new IllegalArgumentException("UNNAMED component is already added");
        }

        this.put(UNNAMED, value);
    }
}
package com.st0x0ef.beyond_earth.common.registries;

import com.st0x0ef.beyond_earth.common.fluids.types.HydrogenFluidType;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.fluids.types.FuelFluidType;
import com.st0x0ef.beyond_earth.common.fluids.types.OilFluidType;

public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BeyondEarth.MODID);

    public static final RegistryObject<FluidType> FUEL_TYPE = FLUID_TYPES.register("fuel", () -> new FuelFluidType(FluidType.Properties.create()
            .descriptionId("block." + BeyondEarth.MODID + ".fuel")
            .fallDistanceModifier(0F)
            .canExtinguish(true)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            .canHydrate(true)
    ));

    public static final RegistryObject<FluidType> OIL_TYPE = FLUID_TYPES.register("oil", () -> new OilFluidType(FluidType.Properties.create()
            .descriptionId("block." + BeyondEarth.MODID + ".oil")
            .fallDistanceModifier(0F)
            .density(3000)
            .viscosity(6000)
            .canSwim(false)
            .canExtinguish(true)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            .canHydrate(true)
    ));

    public static final RegistryObject<FluidType> HYDROGEN_TYPE = FLUID_TYPES.register("hydrogen", () -> new HydrogenFluidType(FluidType.Properties.create()
            .descriptionId("block." + BeyondEarth.MODID + ".hydrogen")
            .fallDistanceModifier(1F)
            .density(3000)
            .viscosity(3000)
            .canSwim(false)
            .canExtinguish(true)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            .canHydrate(true)
    ));

}

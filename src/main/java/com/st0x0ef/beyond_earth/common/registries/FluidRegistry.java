package com.st0x0ef.beyond_earth.common.registries;

import com.st0x0ef.beyond_earth.common.fluids.HydrogenFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.fluids.FuelFluid;
import com.st0x0ef.beyond_earth.common.fluids.OilFluid;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BeyondEarth.MODID);

    /** FUEL FLUIDS */
    public static final RegistryObject<FlowingFluid> FLOWING_FUEL = FLUIDS.register("flowing_fuel", FuelFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> FUEL_STILL = FLUIDS.register("fuel", FuelFluid.Source::new);

    /** OIL FLUIDS */
    public static final RegistryObject<FlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil", OilFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> OIL_STILL = FLUIDS.register("oil", OilFluid.Source::new);

    /** HYDROGEN FLUIDS */
    public static final RegistryObject<FlowingFluid> FLOWING_HYDROGEN = FLUIDS.register("flowing_hydrogen", HydrogenFluid.Flowing::new);
    public static final RegistryObject<FlowingFluid> HYDROGEN_STILL = FLUIDS.register("hydrogen", HydrogenFluid.Source::new);

}

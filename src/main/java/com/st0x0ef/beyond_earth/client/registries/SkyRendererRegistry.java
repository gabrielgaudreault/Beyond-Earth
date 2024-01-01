package com.st0x0ef.beyond_earth.client.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.renderers.sky.OrbitSky;
import com.st0x0ef.beyond_earth.client.renderers.sky.PlanetSky;
import com.st0x0ef.beyond_earth.common.data.PlanetData;
import com.st0x0ef.beyond_earth.common.util.Planets;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SkyRendererRegistry {
    @SubscribeEvent
    public static void register(RegisterDimensionSpecialEffectsEvent event) {
        // Load planets if not loaded.
        PlanetData.loadOrGenerateDefaults();

        Planets.PLANETS_BY_PLANET.forEach((key, planet) -> {
            float clouds = planet.hasClouds ? 192f : Float.NaN;
            event.register(key.location(), new PlanetSky(clouds, planet));
        });
        Planets.PLANETS_BY_ORBIT.forEach((key, planet) -> event.register(key.location(), new OrbitSky(planet)));
    }
}

package com.st0x0ef.beyond_earth.client.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.overlays.*;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayRegistry {

    @SubscribeEvent
    public static void register(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("warning", new WarningOverlay());
        event.registerAboveAll("rocket_timer", new RocketTimerOverlay());
        event.registerBelowAll("oxygen_tank", new OxygenTankOverlay());
        event.registerBelowAll("jet_suit", new JetSuitOverlay());
        event.registerBelowAll("rocket_height", new RocketHeightBarOverlay());
    }
}

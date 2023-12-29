package com.st0x0ef.beyond_earth.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.entities.LanderEntity;
import com.st0x0ef.beyond_earth.common.util.Methods;
import com.st0x0ef.beyond_earth.common.util.Planets;
import com.st0x0ef.beyond_earth.client.events.forge.PlanetOverlayEvent;
import com.st0x0ef.beyond_earth.client.screens.helper.ScreenHelper;

public class RocketHeightBarOverlay implements IGuiOverlay {
    /** ROCKET TEXTURE */
    public static final ResourceLocation ROCKET = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/rocket.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;

        if (Methods.isRocket(player.getVehicle()) || player.getVehicle() instanceof LanderEntity) {
            Level level = Minecraft.getInstance().level;

            float yHeight = (float) player.getY() / 5.3F;

            if (yHeight < 0) {
                yHeight = 0;
            } else if (yHeight > 113) {
                yHeight = 113;
            }

            ResourceLocation planet = Planets.getPlanetBar(level);

            PlanetOverlayEvent event = new PlanetOverlayEvent(gui, planet, graphics.pose(), partialTick, width, height);
            MinecraftForge.EVENT_BUS.post(event);

            if (planet != event.getResourceLocation()) {
                planet = event.getResourceLocation();
            }

            /** ROCKET BAR IMAGE */
            RenderSystem.setShaderTexture(0, planet);
            graphics.blit(planet, 0, (height / 2) - 128 / 2, 0, 0, 16, 128, 16, 128);

            /** ROCKET_Y IMAGE */
            RenderSystem.setShaderTexture(0, ROCKET);
            ScreenHelper.renderWithFloat.blit(graphics.pose(), 4, ((float) height / 2) + ((float) 103 / 2) - yHeight, 0, 0, 8, 11, 8, 11);
        }
    }
}
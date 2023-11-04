package com.st0x0ef.beyond_earth.client.registries;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.renderers.entities.alien.AlienRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.alienzombie.AlienZombieRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.flag.FlagHeadRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.globe.GlobeBlockRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.lander.LanderRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.martianraptor.MartianRaptorRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.mogler.MoglerRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.pygro.PygroModel;
import com.st0x0ef.beyond_earth.client.renderers.entities.pygro.PygroRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.pygrobrute.PygroBruteRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rockettier1.RocketTier1Renderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rockettier2.RocketTier2Renderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rockettier3.RocketTier3Renderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rockettier4.RocketTier4Renderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rover.RoverRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.starcrawler.StarCrawlerRenderer;
import com.st0x0ef.beyond_earth.client.sounds.TickableBeepSound;
import com.st0x0ef.beyond_earth.client.sounds.TickableLandingSound;
import com.st0x0ef.beyond_earth.common.entities.LanderEntity;
import com.st0x0ef.beyond_earth.common.registries.BlockEntityRegistry;
import com.st0x0ef.beyond_earth.common.registries.EntityRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendererRegistry {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {

        /** REGISTER SOUND PROVIDER FOR LANDERS */
        LanderEntity.playBeep = e->{
            Minecraft mc = Minecraft.getInstance();
            mc.getSoundManager().play(new TickableBeepSound(e));
        };
        LanderEntity.playBoost = e->{
            Minecraft mc = Minecraft.getInstance();
            mc.getSoundManager().play(new TickableLandingSound(e));
        };


        /** MOBS */
        event.registerEntityRenderer(EntityRegistry.ALIEN.get(), AlienRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ALIEN_ZOMBIE.get(), AlienZombieRenderer::new);
        event.registerEntityRenderer(EntityRegistry.STAR_CRAWLER.get(), StarCrawlerRenderer::new);
        event.registerEntityRenderer(EntityRegistry.PYGRO.get(), (p_174068_) -> new PygroRenderer(p_174068_, PygroModel.LAYER_LOCATION, ModelLayers.PIGLIN_INNER_ARMOR, ModelLayers.PIGLIN_OUTER_ARMOR));
        event.registerEntityRenderer(EntityRegistry.PYGRO_BRUTE.get(), (p_174068_) -> new PygroBruteRenderer(p_174068_, PygroModel.LAYER_LOCATION, ModelLayers.PIGLIN_BRUTE_INNER_ARMOR, ModelLayers.PIGLIN_BRUTE_OUTER_ARMOR));
        event.registerEntityRenderer(EntityRegistry.MOGLER.get(), MoglerRenderer::new);
        event.registerEntityRenderer(EntityRegistry.MARTIAN_RAPTOR.get(), MartianRaptorRenderer::new);

        /** PROJECT TILES */
        event.registerEntityRenderer(EntityRegistry.ICE_SPIT_ENTITY.get(), renderManager -> new ThrownItemRenderer(renderManager, 1, true));

        /** VEHICLES */
        event.registerEntityRenderer(EntityRegistry.TIER_1_ROCKET.get(), RocketTier1Renderer::new);
        event.registerEntityRenderer(EntityRegistry.TIER_2_ROCKET.get(), RocketTier2Renderer::new);
        event.registerEntityRenderer(EntityRegistry.TIER_3_ROCKET.get(), RocketTier3Renderer::new);
        event.registerEntityRenderer(EntityRegistry.TIER_4_ROCKET.get(), RocketTier4Renderer::new);
        event.registerEntityRenderer(EntityRegistry.LANDER.get(), LanderRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ROVER.get(), RoverRenderer::new);

        /** BLOCK ENTITIES */
        event.registerBlockEntityRenderer(BlockEntityRegistry.FLAG_BLOCK_ENTITY.get(), FlagHeadRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.GLOBE_BLOCK_ENTITY.get(), GlobeBlockRenderer::new);
    }
}

package com.st0x0ef.beyond_earth.client.registries;

import com.st0x0ef.beyond_earth.client.renderers.entities.globe.GlobeItemRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.big.BigRocketItemRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.normal.NormalRocketItemRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.small.SmallRocketItemRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.tiny.TinyRocketItemRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rover.RoverItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemRendererRegistry {
    /** RENDERERS */
    public static final TinyRocketItemRenderer<?> TINY_ROCKET_ITEM_RENDERER = new TinyRocketItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final SmallRocketItemRenderer<?> SMALL_ROCKET_ITEM_RENDERER = new SmallRocketItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final NormalRocketItemRenderer<?> NORMAL_ROCKET_ITEM_RENDERER = new NormalRocketItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final BigRocketItemRenderer<?> BIG_ROCKET_ITEM_RENDERER = new BigRocketItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RoverItemRenderer<?> ROVER_ITEM_RENDERER = new RoverItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final GlobeItemRenderer<?> GLOBE_ITEM_RENDERER = new GlobeItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
}
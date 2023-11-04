package com.st0x0ef.beyond_earth.client.renderers.entities.starcrawler;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.entities.StarCrawlerEntity;

@OnlyIn(Dist.CLIENT)
public class StarCrawlerRenderer extends MobRenderer<StarCrawlerEntity, EntityModel<StarCrawlerEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/entity/star_crawler.png");

    public StarCrawlerRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new StarCrawlerModel<>(renderManagerIn.bakeLayer(StarCrawlerModel.LAYER_LOCATION)), 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(StarCrawlerEntity p_114482_) {
        return TEXTURE;
    }
}
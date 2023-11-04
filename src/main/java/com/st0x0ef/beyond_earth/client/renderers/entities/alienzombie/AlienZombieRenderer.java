package com.st0x0ef.beyond_earth.client.renderers.entities.alienzombie;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.entities.AlienZombieEntity;

@OnlyIn(Dist.CLIENT)
public class AlienZombieRenderer extends MobRenderer<AlienZombieEntity, EntityModel<AlienZombieEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/entity/alien_zombie.png");

    public AlienZombieRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AlienZombieModel<>(renderManagerIn.bakeLayer(AlienZombieModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(AlienZombieEntity p_114482_) {
        return TEXTURE;
    }
}
package com.st0x0ef.beyond_earth.client.renderers.entities.rocket.tiny;

import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.RocketRenderer;
import com.st0x0ef.beyond_earth.client.renderers.entities.rocket.small.SmallRocketModel;
import com.st0x0ef.beyond_earth.common.entities.IVehicleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.renderers.entities.VehicleRenderer;
import com.st0x0ef.beyond_earth.common.entities.RocketEntity;

@OnlyIn(Dist.CLIENT)
public class TinyRocketRenderer extends VehicleRenderer<RocketEntity, TinyRocketModel<RocketEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/standard.png");

    public TinyRocketRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TinyRocketModel<>(renderManagerIn.bakeLayer(TinyRocketModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketEntity rocket) {
        return new ResourceLocation(BeyondEarth.MODID, rocket.getEntityData().get(RocketEntity.SKIN_TEXTURE_PATH));
    }

    @Override
    protected boolean isShaking(RocketEntity rocket) {
        return rocket.getEntityData().get(RocketEntity.ROCKET_START);
    }
}
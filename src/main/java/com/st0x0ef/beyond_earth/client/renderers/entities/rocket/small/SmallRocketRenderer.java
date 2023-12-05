package com.st0x0ef.beyond_earth.client.renderers.entities.rocket.small;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.renderers.entities.VehicleRenderer;
import com.st0x0ef.beyond_earth.common.entities.RocketEntity;

@OnlyIn(Dist.CLIENT)
public class SmallRocketRenderer extends VehicleRenderer<RocketEntity, SmallRocketModel<RocketEntity>> {

    public static ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/rocket_skin/small/standard.png");

    public SmallRocketRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SmallRocketModel<>(renderManagerIn.bakeLayer(SmallRocketModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketEntity rocket) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(RocketEntity rocket) {
        return rocket.getEntityData().get(RocketEntity.ROCKET_START);
    }
}
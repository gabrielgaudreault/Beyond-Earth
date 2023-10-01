package net.mrscauthd.beyond_earth.client.renderers.entities.glacianram;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GoatRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.GlacianRam;

@OnlyIn(Dist.CLIENT)
public class GlacianRamRenderer extends MobRenderer<GlacianRam, EntityModel<GlacianRam>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/entity/glacian_ram.png");

    public GlacianRamRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GlacianRamModel<>(renderManagerIn.bakeLayer(GlacianRamModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GlacianRam pEntity) {
        return TEXTURE;
    }


}
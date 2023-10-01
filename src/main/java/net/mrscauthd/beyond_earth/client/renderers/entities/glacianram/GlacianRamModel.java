package net.mrscauthd.beyond_earth.client.renderers.entities.glacianram;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.GlacianRam;

@OnlyIn(Dist.CLIENT)
public class GlacianRamModel<T extends GlacianRam> extends QuadrupedModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BeyondEarth.MODID, "glacio_ram"), "main");
    private final ModelPart bone;

    public GlacianRamModel(ModelPart root) {
        super(root, false, 19.0F, 1.0F, 2.5F, 2.0F, 24);
        this.bone = root.getChild("bone");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 19).addBox(-1.0F, -15.0F, -1.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(51, 56).addBox(-3.0F, -17.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 18).addBox(-3.0F, -17.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 52).addBox(6.0F, -17.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 53).addBox(4.0F, -17.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(35, 38).addBox(-3.5F, 1.25F, -0.25F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -12.5F, 1.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(21, 38).addBox(1.75F, 0.75F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0749F, -10.4092F, 2.25F, 0.0F, 0.0F, -0.9599F));

        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -12.0F, -8.0F, 11.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(27, 27).addBox(-3.0F, -10.0F, -13.0F, 11.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(1.0F, -8.0F, -15.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-3.0F, -4.0F, -8.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(8.0F, -4.0F, -8.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -1.0F, 4.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -3.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition right_hind_leg = bone.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(32, 0).addBox(3.0F, -5.0F, -12.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_front_leg = bone.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-1.0F, -6.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_front_leg = bone.addOrReplaceChild("front_front_leg", CubeListBuilder.create().texOffs(12, 30).addBox(3.0F, -6.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_hind_leg = bone.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -5.0F, -12.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        float f = pEntity.getRammingXHeadRot();
        if (f != 0.0F) {
            this.head.xRot = f;
        }

    }


}
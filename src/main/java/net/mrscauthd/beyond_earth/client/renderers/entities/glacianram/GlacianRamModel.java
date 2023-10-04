package net.mrscauthd.beyond_earth.client.renderers.entities.glacianram;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.GlacianRam;

@OnlyIn(Dist.CLIENT)
public class GlacianRamModel<T extends GlacianRam> extends QuadrupedModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BeyondEarth.MODID, "glacio_ram"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart right_hind_leg;
    private final ModelPart front_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart left_front_leg;

    public GlacianRamModel(ModelPart root) {
        super(root, false, 8.0F, 8.0F, 2.0F, 2.0F, 24);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.right_hind_leg = root.getChild("right_hind_leg");
        this.front_front_leg = root.getChild("right_front_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.left_front_leg = root.getChild("left_front_leg");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -12.0F, -8.0F, 11.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(27, 27).addBox(-3.0F, -10.0F, -13.0F, 11.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(1.0F, -8.0F, -15.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-3.0F, -4.0F, -8.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(8.0F, -4.0F, -8.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -1.0F, 4.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -3.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 19).addBox(-1.0F, -15.0F, -1.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(51, 56).addBox(-3.0F, -17.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 18).addBox(-3.0F, -17.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 52).addBox(6.0F, -17.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 53).addBox(4.0F, -17.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(35, 38).addBox(-3.5F, 1.25F, -0.25F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -12.5F, 1.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(21, 38).addBox(1.75F, 0.75F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0749F, -10.4092F, 2.25F, 0.0F, 0.0F, -0.9599F));

        PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(32, 0).addBox(3.0F, -5.0F, -12.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition front_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(12, 30).addBox(3.0F, -6.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -5.0F, -12.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-1.0F, -6.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw + (this.young ? 0.0f : 180.0f), pHeadPitch);

        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.2F * pLimbSwingAmount;
        this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.2F * pLimbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.2F * pLimbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.2F * pLimbSwingAmount;


    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_hind_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        front_front_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_hind_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_front_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }



}
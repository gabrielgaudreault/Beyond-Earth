package com.st0x0ef.beyond_earth.client.renderers.sky;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.st0x0ef.beyond_earth.client.renderers.sky.helper.SkyHelper;
import com.st0x0ef.beyond_earth.client.renderers.sky.helper.StarHelper;
import com.st0x0ef.beyond_earth.common.config.ClientConfig;
import com.st0x0ef.beyond_earth.common.util.Planets;
import com.st0x0ef.beyond_earth.common.util.Planets.Planet;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class PlanetSky extends DimensionSpecialEffects {


    private final float[] sunriseCol = new float[4];
    private final float[] rainSizeX = new float[1024];
    private final float[] rainSizeZ = new float[1024];
    private Planet planet;
    private VertexBuffer starBuffer;
    public PlanetSky(float cloudLevel, Planet planet) {
        super(cloudLevel, false, SkyType.NONE, false, false);
        SkyHelper.setupRainSize(this.rainSizeX, this.rainSizeZ);
        this.planet = planet;
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 colour, float brightness) {
        if (planet != null) {
            // If we have no air density, return nothing
            if (planet.airDensity <= 0)
                return colour;
            // This is the default used by overworld
        }
        return colour.multiply(brightness * 0.94F + 0.06F, brightness * 0.94F + 0.06F, brightness * 0.91F + 0.09F);
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float time, float partialTick) {
        if (planet == null)
            return null;
        // No colour if planet has it disabled.
        if (planet.airDensity <= 0 || planet.sunriseColour[0] < 0) {
            sunriseCol[0] = 0;
            sunriseCol[1] = 0;
            sunriseCol[2] = 0;
            sunriseCol[3] = 0;
            return sunriseCol;
        }
        float dr = planet.sunriseColour[0];
        float dg = planet.sunriseColour[1];
        float db = planet.sunriseColour[2];

        float f = 0.4F;
        float f1 = Mth.cos(time * ((float) Math.PI * 2F)) - 0.0F;
        float f2 = -0.0F;
        if (f1 >= -f && f1 <= f) {
            float f3 = (f1 - f2) / f * 0.5F + 0.5F;
            float f4 = 1.0F - (1.0F - Mth.sin(f3 * (float) Math.PI)) * 0.99F;
            f4 *= f4;
            sunriseCol[0] = f3 * 0.3F + dr;
            sunriseCol[1] = f3 * f3 * 0.7F + dg;
            sunriseCol[2] = f3 * f3 * 0.0F + db;
            sunriseCol[3] = f4;
            return sunriseCol;
        } else {
            return null;
        }
    }

    @Override
    public boolean isFoggyAt(int p_108874_, int p_108875_) {
        // This is the default used by overworld
        return false;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX,
                                double camY, double camZ, Matrix4f projectionMatrix) {
        if (planet == null)
            return false;
        // If we return true, but do nothing, it disables cloud rendering!
        return !planet.hasClouds;
    }

    @Override
    public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
        if (planet == null)
            return false;
        // If we return true, but do nothing, it disables rain rendering!
        return !planet.hasRain;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        if (!ClientConfig.PLANET_CUSTOM_SKY.get()) {
            return false;
        }

        planet = Planets.getLocationForPlanet(level);

        /** DEFAULT VARIABLES */
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        Minecraft mc = Minecraft.getInstance();
        Matrix4f matrix4f = poseStack.last().pose();
        ShaderInstance shaderInstance = RenderSystem.getShader();

        float dayTime = level.getTimeOfDay(partialTick);
        float worldTime = level.getDayTime() + partialTick;
        float dayAngle = dayTime * 360f % 360f;
        float skyLight = 1 - 2 * Math.abs(dayTime - 0.5f);

        FogType cameraSubmersionType = camera.getFluidInCamera();
        if (cameraSubmersionType.equals(FogType.POWDER_SNOW) || cameraSubmersionType.equals(FogType.LAVA) || mc.levelRenderer.doesMobEffectBlockSky(camera)) {
            return false;
        }

        /** SKY COLOR */
        Vec3 vec3 = mc.level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
        float r = (float) vec3.x;
        float g = (float) vec3.y;
        float b = (float) vec3.z;

        FogRenderer.levelFogColor();
        RenderSystem.depthMask(false);

        RenderSystem.setShaderColor(r, g, b, 1.0f);
        SkyHelper.drawSky(mc, matrix4f, projectionMatrix, shaderInstance);
        VertexBuffer.unbind();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        /** STARS */
        if (starBuffer == null) {
            starBuffer = StarHelper.createStars(0.1F, ClientConfig.PLANETS_FAST_STARS_COUNT.get(), ClientConfig.PLANETS_FANCY_STARS_COUNT.get(), 190, 160, -1);
        }

        renderStars(mc, dayAngle, partialTick, poseStack, projectionMatrix, setupFog);

        /** PLANETS */
        if(planet != null){
            SkyHelper.drawPlanetsAndParents(poseStack, bufferBuilder, camera, dayAngle, skyLight, worldTime, planet, 1, true);
        }        

        SkyHelper.setupShaderColor(mc, r, g, b);
        RenderSystem.depthMask(true);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        return true;
    }

    private void renderStars(Minecraft mc, float dayAngle, float partialTick, PoseStack poseStack, Matrix4f projectionMatrix, Runnable setupFog) {
        float rainLevel = 1.0F - mc.level.getRainLevel(partialTick);
        float starLight = mc.level.getStarBrightness(partialTick) * rainLevel;
        if (starLight > 0.0F) {
             Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack,
                    Triple.of(Axis.YP.rotationDegrees(-90), Axis.XP.rotationDegrees(dayAngle), null));
            RenderSystem.setShaderColor(starLight + 0.5F, starLight + 0.5F, starLight + 0.5F, starLight + 0.5F);
            SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(),
                    setupFog, true);
        } else if(planet != null && planet.hasStarsAtDay){
            Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack,
                    Triple.of(Axis.YP.rotationDegrees(-90), Axis.XP.rotationDegrees(dayAngle), null));
            RenderSystem.setShaderColor(starLight + 1F, starLight + 1F, starLight + 1F, starLight + 1F);
            SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(),
                    setupFog, true);

        }
    }

    @Override
    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
        if (planet == null)
            return false;

        if (planet.hasDustStorms) {
            Minecraft mc = Minecraft.getInstance();
            float f = mc.level.getRainLevel(partialTick);

            if (!(f <= 0.0F)) {
                lightTexture.turnOnLightLayer();
                int i = Mth.floor(camX);
                int j = Mth.floor(camY);
                int k = Mth.floor(camZ);
                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder bufferbuilder = tesselator.getBuilder();
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.enableDepthTest();
                int l = 5;
                if (Minecraft.useFancyGraphics()) {
                    l = 10;
                }

                RenderSystem.depthMask(Minecraft.useShaderTransparency());
                int i1 = -1;
                float f1 = (float) ticks + partialTick;
                RenderSystem.setShader(GameRenderer::getParticleShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int j1 = k - l; j1 <= k + l; ++j1) {
                    for (int k1 = i - l; k1 <= i + l; ++k1) {
                        int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
                        double d0 = (double) this.rainSizeX[l1] * 0.5D;
                        double d1 = (double) this.rainSizeZ[l1] * 0.5D;
                        blockpos$mutableblockpos.set(k1, camY, j1);
                        Biome biome = level.getBiome(blockpos$mutableblockpos).value();
                        if (biome.getPrecipitationAt(blockpos$mutableblockpos) != Biome.Precipitation.NONE) {
                            int i2 = level.getHeight(Heightmap.Types.MOTION_BLOCKING, k1, j1);
                            int j2 = j - l;
                            int k2 = j + l;
                            if (j2 < i2) {
                                j2 = i2;
                            }

                            if (k2 < i2) {
                                k2 = i2;
                            }

                            int l2 = Math.max(i2, j);

                            if (j2 != k2) {
                                RandomSource randomsource = RandomSource.create(
                                        (long) k1 * k1 * 3121 + k1 * 45238971L ^ (long) j1 * j1 * 418711 + j1 * 13761L);
                                blockpos$mutableblockpos.set(k1, j2, j1);

                                if (biome.warmEnoughToRain(blockpos$mutableblockpos)) {
                                    if (i1 != 0) {
                                        if (i1 > 0) {
                                            tesselator.end();
                                        }

                                        i1 = 0;
                                        RenderSystem.setShaderTexture(0, SkyHelper.MARS_DUST);
                                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                    }

                                    float f5 = -((float) (ticks & 511) + partialTick) / 512.0F;
                                    float f6 = (float) (randomsource.nextDouble()
                                            + (double) f1 * 0.08D * (double) ((float) randomsource.nextGaussian()));
                                    float f7 = (float) (randomsource.nextDouble()
                                            + (double) (f1 * (float) randomsource.nextGaussian()) * 0.008D);
                                    double d3 = (double) k1 + 0.5D - camX;
                                    double d5 = (double) j1 + 0.5D - camZ;
                                    float f8 = (float) Math.sqrt(d3 * d3 + d5 * d5) / (float) l;
                                    float f9 = ((1.0F - f8 * f8) * 0.3F + 0.5F) * f;
                                    blockpos$mutableblockpos.set(k1, l2, j1);
                                    int k3 = LevelRenderer.getLightColor(level, blockpos$mutableblockpos);
                                    int l3 = k3 >> 16 & '\uffff';
                                    int i4 = k3 & '\uffff';
                                    int j4 = (l3 * 3 + 240) / 4;
                                    int k4 = (i4 * 3 + 240) / 4;
                                    bufferbuilder
                                            .vertex((double) k1 - camX - d0 + 0.5D, (double) k2 - camY,
                                                    (double) j1 - camZ - d1 + 0.5D)
                                            .uv(0.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX + d0 + 0.5D, (double) k2 - camY,
                                                    (double) j1 - camZ + d1 + 0.5D)
                                            .uv(1.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX + d0 + 0.5D, (double) j2 - camY,
                                                    (double) j1 - camZ + d1 + 0.5D)
                                            .uv(1.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX - d0 + 0.5D, (double) j2 - camY,
                                                    (double) j1 - camZ - d1 + 0.5D)
                                            .uv(0.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                } else {
                                    if (i1 != 1) {
                                        if (i1 == 0) {
                                            tesselator.end();
                                        }

                                        i1 = 1;
                                        RenderSystem.setShaderTexture(0, SkyHelper.SNOW);
                                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                    }

                                    float f5 = -((float) (ticks & 511) + partialTick) / 512.0F;
                                    float f6 = (float) (randomsource.nextDouble()
                                            + (double) f1 * 0.01D * (double) ((float) randomsource.nextGaussian()));
                                    float f7 = (float) (randomsource.nextDouble()
                                            + (double) (f1 * (float) randomsource.nextGaussian()) * 0.001D);
                                    double d3 = (double) k1 + 0.5D - camX;
                                    double d5 = (double) j1 + 0.5D - camZ;
                                    float f8 = (float) Math.sqrt(d3 * d3 + d5 * d5) / (float) l;
                                    float f9 = ((1.0F - f8 * f8) * 0.3F + 0.5F) * f;
                                    blockpos$mutableblockpos.set(k1, l2, j1);
                                    int k3 = LevelRenderer.getLightColor(level, blockpos$mutableblockpos);
                                    int l3 = k3 >> 16 & '\uffff';
                                    int i4 = k3 & '\uffff';
                                    int j4 = (l3 * 3 + 240) / 4;
                                    int k4 = (i4 * 3 + 240) / 4;
                                    bufferbuilder
                                            .vertex((double) k1 - camX - d0 + 0.5D, (double) k2 - camY,
                                                    (double) j1 - camZ - d1 + 0.5D)
                                            .uv(0.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX + d0 + 0.5D, (double) k2 - camY,
                                                    (double) j1 - camZ + d1 + 0.5D)
                                            .uv(1.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX + d0 + 0.5D, (double) j2 - camY,
                                                    (double) j1 - camZ + d1 + 0.5D)
                                            .uv(1.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                    bufferbuilder
                                            .vertex((double) k1 - camX - d0 + 0.5D, (double) j2 - camY,
                                                    (double) j1 - camZ - d1 + 0.5D)
                                            .uv(0.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9)
                                            .uv2(k4, j4).endVertex();
                                }
                            }
                        }
                    }
                }

                if (i1 >= 0) {
                    tesselator.end();
                }

                RenderSystem.enableCull();
                RenderSystem.disableBlend();
                lightTexture.turnOffLightLayer();
            }
            return true;
        }
        return !planet.hasRain;
    }
}
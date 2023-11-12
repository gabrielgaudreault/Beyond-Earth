package com.st0x0ef.beyond_earth.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.util.GuiHelper;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.WaterSeparatorBlockEntity;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import com.st0x0ef.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import com.st0x0ef.beyond_earth.common.menus.WaterSeparatorMenu;
import com.st0x0ef.beyond_earth.common.util.Rectangle2d;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class WaterSeparatorScreen extends AbstractContainerScreen<WaterSeparatorMenu.GuiContainer> {

    public static final ResourceLocation texture = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/water_separator.png");

    public static final int INPUT_TANK_LEFT = 39;
    public static final int INPUT_TANK_TOP = 26;

    public static final int H2_OUTPUT_TANK_LEFT = 96;
    public static final int H2_OUTPUT_TANK_TOP = 26;

    public static final int O2_OUTPUT_TANK_LEFT = 128;
    public static final int O2_OUTPUT_TANK_TOP = 26;

    public static final int ENERGY_LEFT = 152;
    public static final int ENERGY_TOP = 26;

    public WaterSeparatorScreen(WaterSeparatorMenu.GuiContainer container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 177;
        this.imageHeight = 184;
        this.inventoryLabelY = this.imageHeight - 92;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        WaterSeparatorBlockEntity blockEntity = this.getMenu().getBlockEntity();

        if (GuiHelper.isHover(this.getInputTankBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass(GaugeTextHelper.getStorageText(GaugeValueHelper.getFluid(blockEntity.getInputTank())).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getO2OutputTankBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass(GaugeTextHelper.getStorageText(GaugeValueHelper.getOxygen(blockEntity.getO2OutputTank())).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getH2OutputTankBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass(GaugeTextHelper.getStorageText(GaugeValueHelper.getHydrogen(blockEntity.getH2OutputTank())).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass(GaugeTextHelper.getStorageText(GaugeValueHelper.getEnergy(blockEntity)).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        graphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth,
                this.imageHeight);

        WaterSeparatorBlockEntity blockEntity = this.getMenu().getBlockEntity();
        GuiHelper.drawEnergy(graphics, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP,
                Objects.requireNonNull(blockEntity.getPrimaryEnergyStorage()));
        GuiHelper.drawFluidTank(graphics, this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP,
                blockEntity.getInputTank());
        GuiHelper.drawOxygenTank(graphics, this.leftPos + O2_OUTPUT_TANK_LEFT, this.topPos + O2_OUTPUT_TANK_TOP,
                blockEntity.getO2OutputTank());
        GuiHelper.drawHydrogenTank(graphics, this.leftPos + H2_OUTPUT_TANK_LEFT, this.topPos + H2_OUTPUT_TANK_TOP,
                blockEntity.getH2OutputTank());
    }

    public Rectangle2d getInputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP);
    }

    public Rectangle2d getH2OutputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + H2_OUTPUT_TANK_LEFT, this.topPos + H2_OUTPUT_TANK_TOP);
    }

    public Rectangle2d getO2OutputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + O2_OUTPUT_TANK_LEFT, this.topPos + O2_OUTPUT_TANK_TOP);
    }

    public Rectangle2d getEnergyBounds() {
        return GuiHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
    }
}

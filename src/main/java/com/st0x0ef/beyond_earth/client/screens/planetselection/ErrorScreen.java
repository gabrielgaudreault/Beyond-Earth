package com.st0x0ef.beyond_earth.client.screens.planetselection;

import com.google.common.collect.Lists;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.screens.buttons.ModifiedButton;
import com.st0x0ef.beyond_earth.client.screens.buttons.TexturedButton;
import com.st0x0ef.beyond_earth.client.screens.helper.ScreenHelper;
import com.st0x0ef.beyond_earth.common.menus.planetselection.ErrorMenu;
import com.st0x0ef.beyond_earth.common.util.Methods;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class ErrorScreen extends Screen implements MenuAccess<ErrorMenu.GuiContainer> {

    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/planet_selection.png");

    public static final ResourceLocation ERROR_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/error.png");
    public static final ResourceLocation MILKY_WAY_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/milky_way.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/buttons/button.png");

    public static final Component CATALOG_TEXT = Component.literal("A Space sation already exist.");
    public float rotationMilkyWay;



    private final ErrorMenu.GuiContainer menu;
    public ErrorScreen(ErrorMenu.GuiContainer menu, Inventory inventory, Component component) {
        super(component);
        this.menu = menu;
    }

    @Override
    public void onClose() {
        this.menu.getPlayer().closeContainer();
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }



    @Override
    public ErrorMenu.GuiContainer getMenu() {
        return menu;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBg(graphics, partialTicks, mouseX, mouseY);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    public void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(graphics);

        /** Drawing the background texture*/
        ScreenHelper.drawTexture(0, 0, this.width, this.height, BACKGROUND_TEXTURE, false);

        /** Drawing the milky way*/
        //PlanetSelectionScreenHelper.drawGalaxy(this, graphics.pose(), MILKY_WAY_TEXTURE, -125, -125, 250, 250,
                //this.rotationMilkyWay);
        /** Rotate the milky way*/
        this.rotationMilkyWay = (this.rotationMilkyWay + partialTicks * 0.4f);

        /** Drawing the error texture*/
        ScreenHelper.drawTexture(this.width / 2 - 75, this.height / 2 - 100, 175, 50, ERROR_TEXTURE, false);

        graphics.drawString(font, CATALOG_TEXT, this.width / 2 - 65, (this.height / 2) - 10 / 2, 16711680);
    }

    @Override
    protected void init() {
        super.init();

        this.rotationMilkyWay = 0;
        //TODO ADD TEXT, ERROR IMAGE
        List<String> buttonText = Lists.newArrayList();

        buttonText = List.of("Go Back to the planet selection", "menu and try again.");
        this.addButton(this.width / 2 - 30 , this.height / 2 + 50, 0, 75, 25, false, null, buttonText,
                BUTTON_TEXTURE, TexturedButton.ColorTypes.BLUE, Component.literal("Go Back"), (onPress) -> {
                        Methods.openPlanetGui(menu.getPlayer());
                        this.onClose();
                });
    }

    public ModifiedButton addButton(int x, int y, int row, int width, int height, boolean rocketCondition,
                                    ModifiedButton.ButtonTypes type, List<String> list, ResourceLocation buttonTexture,
                                    ModifiedButton.ColorTypes colorType, Component title, Button.OnPress onPress) {
        ModifiedButton button = this.addRenderableWidget(new ModifiedButton(x, y, row, width, height, 0, 0, 0,
                rocketCondition, type, list, buttonTexture, colorType, width, height, onPress, title));
        return button;
    }
}

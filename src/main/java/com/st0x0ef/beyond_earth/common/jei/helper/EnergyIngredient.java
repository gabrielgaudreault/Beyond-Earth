package com.st0x0ef.beyond_earth.common.jei.helper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.client.util.GuiHelper;
import com.st0x0ef.beyond_earth.common.jei.Jei;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EnergyIngredient implements IIngredientHelper<EnergyIngredient>, IIngredientRenderer<EnergyIngredient> {

    public static final class DummyRenderer implements IIngredientRenderer<EnergyIngredient> {
        @Override
        public void render(GuiGraphics graphics, EnergyIngredient ingredient) {
            // DO NOT USE THIS, THIS IS BECAUSE JEI REQUIRES SIZE 16
        }

        @Override
        public List<Component> getTooltip(EnergyIngredient ingredient, TooltipFlag tooltipFlag) {
            return Lists.newArrayList();
        }
    }

    public static final EnergyIngredient INSTANCE = new EnergyIngredient();

    public static final EnergyIngredient INTANK = new EnergyIngredient();
    public static final EnergyIngredient OUTTANK = new EnergyIngredient();

    static final List<EnergyIngredient> ALL = Lists.newArrayList(INTANK, OUTTANK);

    public static Collection<EnergyIngredient> getIngredients() {
        return ALL;
    }

    private final LoadingCache<Integer, DrawableEnergyAnimated> cachedTank;
    private int amount = 0;

    public EnergyIngredient() {
        this.cachedTank = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public DrawableEnergyAnimated load(Integer stack) {
                return new DrawableEnergyAnimated(100 * stack, EnergyIngredient.this == INTANK);
            }
        });
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void render(GuiGraphics graphics, EnergyIngredient ingredient) {
        int amount = Math.max(ingredient.amount, 1);
        DrawableEnergyAnimated fluid = cachedTank.getUnchecked(amount);
        fluid.draw(graphics);
    }

    @Override
    public List<Component> getTooltip(EnergyIngredient ingredient, TooltipFlag tooltipFlag) {
        List<Component> tooltip = new ArrayList<>();
        try {
            String displayName = this.getDisplayName(ingredient);
            tooltip.add(Component.literal(displayName));

            long amount = ingredient.amount;
            // TODO include message for amount of energy?

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return tooltip;
    }

    @Override
    public int getWidth() {
        return GuiHelper.ENERGY_WIDTH;
    }

    @Override
    public int getHeight() {
        return GuiHelper.ENERGY_HEIGHT;
    }

    @Override
    public IIngredientType<EnergyIngredient> getIngredientType() {
        return Jei.FE_INGREDIENT_TYPE;
    }

    @Override
    public String getDisplayName(EnergyIngredient ingredient) {
        return I18n.get("general." + BeyondEarth.MODID + ".energy");
    }

    @Override
    public String getUniqueId(EnergyIngredient ingredient, UidContext context) {
        return "beyond_earth:energy";
    }

    @Override
    public ResourceLocation getResourceLocation(EnergyIngredient ingredient) {
        return new ResourceLocation("beyond_earth:energy");
    }

    @Override
    public EnergyIngredient copyIngredient(EnergyIngredient ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable EnergyIngredient ingredient) {
        return "error with energy?";
    }
}

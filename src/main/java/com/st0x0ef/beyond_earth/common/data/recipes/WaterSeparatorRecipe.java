package com.st0x0ef.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import com.st0x0ef.beyond_earth.common.registries.RecipeSerializersRegistry;
import com.st0x0ef.beyond_earth.common.registries.RecipeTypeRegistry;

public class WaterSeparatorRecipe extends OxygenMakingRecipeAbstract {

	public WaterSeparatorRecipe(ResourceLocation id, JsonObject json) {
		super(id, json);
	}

	public WaterSeparatorRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);
	}

	public WaterSeparatorRecipe(ResourceLocation id, FluidIngredient ingredient, int oxygen, int hydrogen) {
		super(id, ingredient, oxygen, hydrogen);
	}

	@Override
	public boolean matches(Container p_44002_, Level p_44003_) {
		return false;
	}

	@Override
	public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
		return null;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess p_267052_) {
		return null;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecipeSerializersRegistry.RECIPE_SERIALIZER_WATER_SEPARATOR.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeTypeRegistry.WATER_SEPARATOR.get();
	}

}
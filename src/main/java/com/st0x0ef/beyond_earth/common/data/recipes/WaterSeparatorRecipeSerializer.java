package com.st0x0ef.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class WaterSeparatorRecipeSerializer extends BeyondEarthRecipeSerializer<WaterSeparatorRecipe> {

	@Override
	public WaterSeparatorRecipe fromJson(ResourceLocation id, JsonObject json) {
		return new WaterSeparatorRecipe(id, json);
	}

	@Override
	public WaterSeparatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
		return new WaterSeparatorRecipe(id, buffer);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, WaterSeparatorRecipe recipe) {
		recipe.write(buffer);
	}

}

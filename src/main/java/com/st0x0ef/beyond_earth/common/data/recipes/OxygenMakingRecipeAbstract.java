package com.st0x0ef.beyond_earth.common.data.recipes;

import java.util.function.Predicate;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;

public abstract class OxygenMakingRecipeAbstract extends BeyondEarthRecipe implements Predicate<FluidStack> {

	private final FluidIngredient input;
	private final int oxygen;
	private final int hydrogen;

	public OxygenMakingRecipeAbstract(ResourceLocation id, JsonObject json) {
		super(id, json);

		this.input = FluidIngredient.deserialize(GsonHelper.getAsJsonObject(json, "input"));
		this.oxygen = GsonHelper.getAsInt(json, "oxygen");
		this.hydrogen = GsonHelper.getAsInt(json, "hydrogen");
	}

	public OxygenMakingRecipeAbstract(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);

		this.input = FluidIngredient.read(buffer);
		this.oxygen = buffer.readInt();
		this.hydrogen = buffer.readInt();
	}

	public OxygenMakingRecipeAbstract(ResourceLocation id, FluidIngredient ingredient, int oxygen, int hydrogen) {
		super(id);

		this.input = ingredient;
		this.oxygen = oxygen;
		this.hydrogen = hydrogen;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		super.write(buffer);

		this.getInput().write(buffer);
		buffer.writeInt(this.getOxygen());
		buffer.writeInt(this.getHydrogen());
	}

	@Override
	public boolean canCraftInDimensions(int var1, int var2) {
		return true;
	}

	@Override
	public boolean test(FluidStack fluidStack) {
		return this.input.test(fluidStack);
	}

	public FluidIngredient getInput() {
		return this.input;
	}

	public int getOxygen() {
		return this.oxygen;
	}

	public int getHydrogen() {
		return this.hydrogen;
	}

	@Override
	public abstract RecipeSerializer<?> getSerializer();

	@Override
	public abstract RecipeType<?> getType();

}

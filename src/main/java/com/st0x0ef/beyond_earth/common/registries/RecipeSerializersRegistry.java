package com.st0x0ef.beyond_earth.common.registries;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.data.recipes.*;

public class RecipeSerializersRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BeyondEarth.MODID);

    /** RECIPES */
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_COMPRESSOR = RECIPE_SERIALIZERS.register("compressor", CompressingRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_COAL_GENERATOR = RECIPE_SERIALIZERS.register("coal_generator", GeneratingRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_WATER_SEPARATOR = RECIPE_SERIALIZERS.register("water_separator", WaterSeparatorRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_OXYGEN_BUBBLE_DISTRIBUTOR = RECIPE_SERIALIZERS.register("oxygen_bubble_distributor", OxygenBubbleDistributorRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_NASA_WORKBENCH = RECIPE_SERIALIZERS.register("nasa_workbench", WorkbenchingRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_FUEL_REFINERY = RECIPE_SERIALIZERS.register("fuel_refinery", FuelRefiningRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ITEMSTACK = RECIPE_SERIALIZERS.register("alien_trading_itemstack", AlienTradingRecipeItemStack.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ENCHANTED_BOOK = RECIPE_SERIALIZERS.register("alien_trading_enchanted_book", AlienTradingRecipeEnchantedBook.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ENCHANTED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_enchanted_item", AlienTradingRecipeEnchantedItem.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_MAP = RECIPE_SERIALIZERS.register("alien_trading_map", AlienTradingRecipeMap.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_POTIONED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_potioned_item", AlienTradingRecipePotionedItem.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_DYED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_dyed_item", AlienTradingRecipeDyedItem.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_SPACE_STATION = RECIPE_SERIALIZERS.register("space_station", SpaceStationRecipeSerializer::new);
}

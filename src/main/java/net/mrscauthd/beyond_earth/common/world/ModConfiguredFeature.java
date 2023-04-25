package net.mrscauthd.beyond_earth.common.world;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;

import java.util.List;

public class ModConfiguredFeature {

    // GLACIO
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLACIO_COAL_ORE_KEY = registerKey("glacio_coal_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLACIO_COPPER_ORE_KEY = registerKey("glacio_copper_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLACIO_ICE_SHRAD_ORE_KEY = registerKey("glacio_ice_shard_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLACIO_IRON_ORE_KEY = registerKey("glacio_iron_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLACIO_LAPIS_ORE_KEY = registerKey("glacio_lapis_ore_key");

    // MARS
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARS_DIAMOND_ORE_KEY = registerKey("mars_diamond_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MARS_ICE_SHARD_ORE_KEY = registerKey("mars_ice_shard_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MARS_IRON_ORE_KEY = registerKey("mars_iron_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MARS_OSTRUM_ORE_KEY = registerKey("mars_ostrum_ore_key");

    // MERCURY
    public static final ResourceKey<ConfiguredFeature<?, ?>> MERCURY_IRON_ORE_KEY = registerKey("mercury_iron_ore_key");

    // MOON
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_CHEESE_ORE_KEY = registerKey("moon_cheese_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_DESH_ORE_KEY = registerKey("moon_desh_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_ICE_SHARD_ORE_KEY = registerKey("moon_ice_shard_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_IRON_ORE_KEY = registerKey("moon_iron_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_SOUL_SOIL_KEY = registerKey("moon_soul_soil_key");

    // VENUS
    public static final ResourceKey<ConfiguredFeature<?, ?>> VENUS_CALORITE_ORE_KEY = registerKey("venus_calorite_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VENUS_COAL_ORE_KEY = registerKey("venus_coal_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> VENUS_DIAMOND_ORE_KEY = registerKey("venus_diamond_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> VENUS_GOLD_ORE_KEY = registerKey("venus_gold_ore_key");

    // GLACIO
    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_COAL_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COAL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_COPPER_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COPPER_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_ICE_SHRAD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_ICE_SHARD_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_IRON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_LAPIS_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_LAPIS_ORE.get().defaultBlockState())));

    // MARS
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_DIAMOND_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_DIAMOND_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_ICE_SHARD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_ICE_SHARD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_IRON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_OSTRUM_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_OSTRUM_ORE.get().defaultBlockState())));

    // MERCURY
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MERCURY_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MERCURY_STONE.get()), BlockRegistry.MERCURY_IRON_ORE.get().defaultBlockState())));

    // MOON
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_CHEESE_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_CHEESE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_DESH_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_DESH_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_ICE_SHARD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_ICE_SHARD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_IRON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_SOUL_SOIL_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), Blocks.SOUL_SOIL.defaultBlockState())));

    // VENUS
    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_CALORITE_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_CALORITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_COAL_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_COAL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_DIAMOND_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_DIAMOND_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_GOLD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_GOLD_ORE.get().defaultBlockState())));

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BeyondEarth.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        // GLACIO
        register(context, GLACIO_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(GLACIO_COAL_ORE_REPLACEABLES.get(), 17));
        register(context, GLACIO_COPPER_ORE_KEY, Feature.ORE, new OreConfiguration(GLACIO_COPPER_ORE_REPLACEABLES.get(), 17));
        register(context, GLACIO_ICE_SHRAD_ORE_KEY, Feature.ORE, new OreConfiguration(GLACIO_ICE_SHRAD_ORE_REPLACEABLES.get(), 10));
        register(context, GLACIO_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(GLACIO_IRON_ORE_REPLACEABLES.get(), 11));
        register(context, GLACIO_LAPIS_ORE_KEY, Feature.ORE, new OreConfiguration(GLACIO_LAPIS_ORE_REPLACEABLES.get(), 9));

        // MARS
        register(context, MARS_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(MARS_DIAMOND_ORE_REPLACEABLES.get(), 7));
        register(context, MARS_ICE_SHARD_ORE_KEY, Feature.ORE, new OreConfiguration(MARS_ICE_SHARD_ORE_REPLACEABLES.get(), 10));
        register(context, MARS_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(MARS_IRON_ORE_REPLACEABLES.get(), 11));
        register(context, MARS_OSTRUM_ORE_KEY, Feature.ORE, new OreConfiguration(MARS_OSTRUM_ORE_REPLACEABLES.get(), 8));

        // MERCURY
        register(context, MERCURY_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(MERCURY_IRON_ORE_REPLACEABLES.get(), 8));

        // MOON
        register(context, MOON_CHEESE_ORE_KEY, Feature.ORE, new OreConfiguration(MOON_CHEESE_ORE_REPLACEABLES.get(), 10));
        register(context, MOON_DESH_ORE_KEY, Feature.ORE, new OreConfiguration(MOON_DESH_ORE_REPLACEABLES.get(), 9));
        register(context, MOON_ICE_SHARD_ORE_KEY, Feature.ORE, new OreConfiguration(MOON_ICE_SHARD_ORE_REPLACEABLES.get(), 10));
        register(context, MOON_IRON_ORE_KEY, Feature.ORE, new OreConfiguration(MOON_IRON_ORE_REPLACEABLES.get(), 11));
        register(context, MOON_SOUL_SOIL_KEY, Feature.ORE, new OreConfiguration(MOON_SOUL_SOIL_REPLACEABLES.get(), 60));

        // VENUS
        register(context, VENUS_CALORITE_ORE_KEY, Feature.ORE, new OreConfiguration(VENUS_CALORITE_ORE_REPLACEABLES.get(), 8));
        register(context, VENUS_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(VENUS_COAL_ORE_REPLACEABLES.get(), 17));
        register(context, VENUS_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(VENUS_DIAMOND_ORE_REPLACEABLES.get(), 9));
        register(context, VENUS_GOLD_ORE_KEY, Feature.ORE, new OreConfiguration(VENUS_GOLD_ORE_REPLACEABLES.get(), 10));
    }
}


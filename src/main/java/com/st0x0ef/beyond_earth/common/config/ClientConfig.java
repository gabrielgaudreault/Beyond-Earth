package com.st0x0ef.beyond_earth.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> ORBIT_FANCY_STARS_COUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> ORBIT_FAST_STARS_COUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> PLANETS_FANCY_STARS_COUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> PLANETS_FAST_STARS_COUNT;

    static {
        BUILDER.push("Orbit Sky");
            ORBIT_FANCY_STARS_COUNT = BUILDER.comment("How much fancy stars should appear in an orbit sky.").define("orbitFancyStarsCount", 13000);
            ORBIT_FAST_STARS_COUNT = BUILDER.comment("How much fast stars should appear in an orbit sky.").define("orbitFastStarsCount", 6000);
        BUILDER.pop();

        BUILDER.push("Planet Sky");
            PLANETS_FANCY_STARS_COUNT = BUILDER.comment("How much fancy stars should appear in a planet sky.").define("planetFancyStarsCount", 13000);
            PLANETS_FAST_STARS_COUNT = BUILDER.comment("How much fast stars should appear in an planet sky.").define("planetFastStarsCount", 6000);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}

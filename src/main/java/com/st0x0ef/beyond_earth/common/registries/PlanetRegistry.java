package com.st0x0ef.beyond_earth.common.registries;

import com.st0x0ef.beyond_earth.BeyondEarth;
import com.st0x0ef.beyond_earth.common.util.Planets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import static com.st0x0ef.beyond_earth.common.util.Planets.BY_DIMENSION;

public class PlanetRegistry {

    /** PLANET BAR TEXTURES */
    private static final ResourceLocation MOON_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/moon_planet_bar.png");
    private static final ResourceLocation MARS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/mars_planet_bar.png");
    private static final ResourceLocation MERCURY_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/mercury_planet_bar.png");
    private static final ResourceLocation VENUS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/venus_planet_bar.png");
    private static final ResourceLocation GLACIO_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/glacio_planet_bar.png");

    /** PLANET TEXTURES */
    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/sun.png");
    private static final ResourceLocation MARS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/mars.png");
    private static final ResourceLocation PHOBOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/phobos.png");
    private static final ResourceLocation DEIMOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/deimos.png");
    private static final ResourceLocation EARTH_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/earth.png");
    private static final ResourceLocation MOON_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/moon.png");
    private static final ResourceLocation VENUS_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/venus.png");
    private static final ResourceLocation MERCURY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/mercury.png");
    private static final ResourceLocation GLACIO_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/glacio.png");

    /** STAR SYSTEMS */
    public static final Planets.StarSystem SOL = new Planets.StarSystem();
    public static final Planets.StarSystem PROXIMA_CENTAURI = new Planets.StarSystem();

    /** PLANETS */
    public static Planets.Planet MERCURY;
    public static Planets.Planet VENUS;
    public static Planets.Planet EARTH;
    public static Planets.Planet MOON;
    public static Planets.Planet MARS;
    public static Planets.Planet PHOBOS;
    public static Planets.Planet DEIMOS;
    public static Planets.Planet GLACIO;


    /**
     * Here we register default planets. We are set to HIGHEST so that we fire
     * first, and then addons can adjust things.
     */

    public static void registerDefaultPlanets() {
        registerPlanetLevel();
        registerPlanetBars();

        SOL.name = "sun";
        SOL.texture = SUN_TEXTURE;

        MERCURY = BY_DIMENSION.get(LevelRegistry.MERCURY);
        MERCURY.orbitRadius = 0.39f * Planets.PLANET_ORBIT_SCALE;
        MERCURY.mass = 0.055f * Planets.PLANET_MASS_SCALE;
        MERCURY.texture = MERCURY_TEXTURE;
        MERCURY.rotation = 270;
        MERCURY.distanceFromEarth = 91690000;
        MERCURY.g = 0.38f;
        MERCURY.radius = 2439.7;
        MERCURY.temperature = 430;
        MERCURY.orbitColour = new int[] { 179, 49, 44 };

        VENUS = BY_DIMENSION.get(LevelRegistry.VENUS);
        VENUS.orbitRadius = 0.72f * Planets.PLANET_ORBIT_SCALE;
        VENUS.mass = 0.81f * Planets.PLANET_MASS_SCALE;
        VENUS.texture = VENUS_TEXTURE;
        VENUS.rotation = 180;
        VENUS.distanceFromEarth = 41400000;
        VENUS.g = 0.904f;
        VENUS.radius = 6051.8;
        VENUS.temperature = 482;
        VENUS.airDensity = 100;
        VENUS.orbitColour = new int[] { 235, 136, 68 };

        EARTH = BY_DIMENSION.get(LevelRegistry.EARTH);
        EARTH.texture = EARTH_TEXTURE;
        EARTH.rotation = 90;
        EARTH.distanceFromEarth = 0;
        EARTH.radius = 6371.0;
        EARTH.airDensity = 1;
        EARTH.hasOxygen = true;
        EARTH.spaceLevel = false;
        EARTH.hasRain = true;
        EARTH.orbitColour = new int[] { 53, 163, 79 };

        MARS= BY_DIMENSION.get(LevelRegistry.MARS);
        MARS.orbitRadius = 1.52f * Planets.PLANET_ORBIT_SCALE;
        MARS.mass = 0.107f * Planets.PLANET_MASS_SCALE;
        MARS.texture = MARS_TEXTURE;
        MARS.distanceFromEarth = 78340000;
        MARS.radius = 3389.5;
        MARS.g = 0.3794f;
        MARS.temperature = -63;
        MARS.airDensity = 0.001f;
        MARS.hasRain = true;
        MARS.hasDustStorms = true;
        MARS.orbitColour = new int[] { 37, 49, 146 };
        MARS.sunriseColour = new float[] { 0, 0.55f, 0.8f };

        PHOBOS = BY_DIMENSION.get(LevelRegistry.PHOBOS);
        PHOBOS.g = 0.04f;
        PHOBOS.radius = 11.2;
        PHOBOS.mass = 10.6e15;
        PHOBOS.rotation = 180;
        PHOBOS.temperature = -160;
        PHOBOS.texture = PHOBOS_TEXTURE;
        PHOBOS.orbitRadius = 0.0244f * Planets.MOON_ORBIT_SCALE;
        PHOBOS.orbitColour = MARS.orbitColour;
        PHOBOS.tidalLock = true;
        MARS.addChild(PHOBOS);

        DEIMOS= BY_DIMENSION.get(LevelRegistry.DEIMOS);
        DEIMOS.g = 0.04f;
        DEIMOS.radius = 6.2;
        DEIMOS.mass = 1.476e15;
        DEIMOS.temperature = -160;
        DEIMOS.texture = DEIMOS_TEXTURE;
        DEIMOS.orbitRadius = 0.0610f * Planets.MOON_ORBIT_SCALE;
        DEIMOS.orbitColour = MARS.orbitColour;
        DEIMOS.tidalLock = true;
        MARS.addChild(DEIMOS);

        MOON= BY_DIMENSION.get(LevelRegistry.MOON);
        MOON.g = 0.1654f;
        MOON.radius = 1737.4;
        MOON.mass = Planets.MOON_MASS_SCALE;
        MOON.temperature = -160;
        MOON.texture = MOON_TEXTURE;
        MOON.orbitRadius = Planets.MOON_ORBIT_SCALE;
        MOON.orbitColour = EARTH.orbitColour;
        MOON.tidalLock = true;
        MOON.phaseTexture = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/moon_phases.png");
        EARTH.addChild(MOON);
        SOL.addChild(MERCURY);
        SOL.addChild(VENUS);
        SOL.addChild(EARTH);
        SOL.addChild(MARS);
        SOL.register();

        PROXIMA_CENTAURI.name = "proxima_centauri";
        PROXIMA_CENTAURI.location[0] = 4.25f;
        PROXIMA_CENTAURI.mass = 0.122f * Planets.STAR_MASS_SCALE;
        PROXIMA_CENTAURI.colour = new int[] { 255, 127, 63 };

        GLACIO = BY_DIMENSION.get(LevelRegistry.MOON);
        GLACIO.texture = GLACIO_TEXTURE;
        GLACIO.mass = 0.08f * Planets.PLANET_MASS_SCALE;
        GLACIO.orbitRadius = 0.39f * Planets.PLANET_ORBIT_SCALE;
        GLACIO.rotation = 180;
        GLACIO.distanceFromEarth = 378429e8;
        GLACIO.g = 0.3794f;
        GLACIO.temperature = -20;
        GLACIO.hasRain = true;
        GLACIO.orbitColour = new int[] { 37, 49, 146 };
        PROXIMA_CENTAURI.addChild(GLACIO);
        PROXIMA_CENTAURI.register();
    }

    public static void registerPlanetLevel() {
        Planets.registerPlanet(Level.OVERWORLD, LevelRegistry.EARTH_ORBIT);
        Planets.registerPlanet(LevelRegistry.MOON, LevelRegistry.MOON_ORBIT);
        Planets.registerPlanet(LevelRegistry.MARS, LevelRegistry.MARS_ORBIT);
        Planets.registerPlanet(LevelRegistry.PHOBOS, LevelRegistry.PHOBOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.DEIMOS, LevelRegistry.DEIMOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT);
        Planets.registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
        Planets.registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT);
    }

    public static void registerPlanetBars() {
        Planets.registerPlanetBar(LevelRegistry.MOON, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.PHOBOS, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.DEIMOS, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MARS, MARS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MERCURY, MERCURY_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.VENUS, VENUS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.GLACIO, GLACIO_PLANET_BAR);
    }
}
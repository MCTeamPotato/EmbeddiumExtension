package me.flashyreese.mods.embeddiumextension.mixin;

import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;
import net.minecraftforge.fml.loading.FMLLoader;

public class SodiumExtraMixinConfigPlugin extends AbstractCaffeineConfigMixinPlugin {

    private static final String MIXIN_PACKAGE_ROOT = "me.flashyreese.mods.embeddiumextension.mixin.";
    public static boolean betterDivingNotLoaded;
    public static boolean primalWinerNotLoaded;

    public SodiumExtraMixinConfigPlugin() {
        betterDivingNotLoaded = FMLLoader.getLoadingModList().getModFileById("better_diving") == null;
        primalWinerNotLoaded = FMLLoader.getLoadingModList().getModFileById("primalwinter") == null;
    }

    @Override
    protected CaffeineConfig createConfig() {

        return CaffeineConfig.builder("Embeddium Extra").withSettingsKey("embeddiumextension:options")
                .addMixinOption("animation", true)
                .addMixinOption("biome_colors", true)
                .addMixinOption("cloud", true)
                .addMixinOption("compat", true)
                .addMixinOption("fog", true)
                .addMixinOption("fog_falloff", true)
                .addMixinOption("gui", true)
                .addMixinOption("instant_sneak", true)
                .addMixinOption("light_updates", true)
                .addMixinOption("particle", true)
                .addMixinOption("prevent_shaders", true)
                .addMixinOption("reduce_resolution_on_mac", true)
                .addMixinOption("render", true)
                .addMixinOption("render.block", true)
                .addMixinOption("render.block.entity", true)
                .addMixinOption("render.entity", true)
                .addMixinOption("sky", true)
                .addMixinOption("sky_colors", true)
                .addMixinOption("sodium", true)
                .addMixinOption("sodium.accessibility", true)
                .addMixinOption("sodium.biome_blend", true)
                .addMixinOption("sodium.fast_random", true)
                .addMixinOption("sodium.gui_scale", true)
                .addMixinOption("sodium.resolution", true)
                .addMixinOption("sodium.scrollable_page", true)
                .addMixinOption("stars", betterDivingNotLoaded)
                .addMixinOption("sun_moon", betterDivingNotLoaded && primalWinerNotLoaded)
                .addMixinOption("toasts", true)

                .withInfoUrl("https://github.com/FlashyReese/sodium-extra-fabric/wiki/Configuration-File")
                .build(FMLLoader.getGamePath().resolve("config").resolve("embeddiumextension.properties"));
    }

    @Override
    protected String mixinPackageRoot() {
        return MIXIN_PACKAGE_ROOT;
    }
}

package com.teampotato.embeddiumextension.client.gui;

import com.google.common.collect.ImmutableList;
import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import com.teampotato.embeddiumextension.client.gui.options.control.ControlValueFormatterExtended;
import com.teampotato.embeddiumextension.client.gui.options.control.SliderControlExtended;
import com.teampotato.embeddiumextension.client.gui.options.storage.SodiumExtraOptionsStorage;
import com.teampotato.embeddiumextension.mixin.fog.DimensionOptionsAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.MinecraftOptionsStorage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SodiumExtraGameOptionPages {
    public static final SodiumExtraOptionsStorage sodiumExtraOpts = new SodiumExtraOptionsStorage();
    public static final MinecraftOptionsStorage vanillaOpts = new MinecraftOptionsStorage();

    public static String parseVanillaString(String key) {
        return new LiteralText((new TranslatableText(key).getString()).replaceAll("§.", "")).getString();
    }

    @Contract(" -> new")
    public static @NotNull OptionPage animation() {
        List<OptionGroup> groups = new ObjectArrayList<>();
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("gui.socialInteractions.tab_all"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.animations_all.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.animationSettings.animation = value, opts -> opts.animationSettings.animation)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .build());

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("block.minecraft.water"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.animate_water.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.animationSettings.water = value, opts -> opts.animationSettings.water)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("block.minecraft.lava"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.animate_lava.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.animationSettings.lava = value, opts -> opts.animationSettings.lava)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("block.minecraft.fire"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.animate_fire.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.animationSettings.fire = value, opts -> opts.animationSettings.fire)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("block.minecraft.nether_portal"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.animate_portal.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.animationSettings.portal = value, opts -> opts.animationSettings.portal)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.block_animations").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.block_animations.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.animationSettings.blockAnimations = value, options -> options.animationSettings.blockAnimations)
                        .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                        .build()
                )
                .build());
        return new OptionPage(new TranslatableText("embeddiumextension.option.animations").getString(), ImmutableList.copyOf(groups));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage particle() {
        List<OptionGroup> groups = new ObjectArrayList<>();
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("gui.socialInteractions.tab_all"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.particles_all.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.particleSettings.particles = value, opts -> opts.particleSettings.particles)
                        .build()
                )
                .build());

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("subtitles.entity.generic.splash"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.rain_splash.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.particleSettings.rainSplash = value, opts -> opts.particleSettings.rainSplash)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("subtitles.block.generic.break"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.block_break.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.particleSettings.blockBreak = value, opts -> opts.particleSettings.blockBreak)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("subtitles.block.generic.hit"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.block_breaking.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.particleSettings.blockBreaking = value, opts -> opts.particleSettings.blockBreaking)
                        .build()
                )
                .build());

        ForgeRegistries.PARTICLE_TYPES.getKeys().stream()
                .collect(Collectors.groupingBy(Identifier::getNamespace))
                .forEach((namespace, identifiers) -> groups.add(identifiers.stream()
                        .map(identifier -> OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                                .setName(translatableName(identifier, "particles").getString())
                                .setTooltip(translatableTooltip(identifier, "particles").getString())
                                .setControl(TickBoxControl::new)
                                .setBinding((opts, val) -> opts.particleSettings.otherMap.put(identifier, val),
                                        opts -> opts.particleSettings.otherMap.getOrDefault(identifier, true))
                                .build())
                        .sorted(Comparator.comparing(OptionImpl::getName))
                        .collect(
                                OptionGroup::createBuilder,
                                OptionGroup.Builder::add,
                                (b1, b2) -> {}
                        ).build()
                ));

        return new OptionPage(new TranslatableText("options.particles").getString(), ImmutableList.copyOf(groups));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage detail() {
        List<OptionGroup> groups = new ObjectArrayList<>();
        groups.add(OptionGroup.createBuilder().add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.sky").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.sky.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.detailSettings.sky = value, opts -> opts.detailSettings.sky)
                        .build()
                ).add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.stars").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.stars.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.detailSettings.stars = value, opts -> opts.detailSettings.stars)
                        .build()
                ).add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.sun_moon").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.sun_moon.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.detailSettings.sunMoon = value, opts -> opts.detailSettings.sunMoon)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("soundCategory.weather"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.rain_snow.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.detailSettings.rainSnow = value, opts -> opts.detailSettings.rainSnow)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.biome_colors").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.biome_colors.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.detailSettings.biomeColors = value, options -> options.detailSettings.biomeColors)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.sky_colors").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.sky_colors.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.detailSettings.skyColors = value, options -> options.detailSettings.skyColors)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());
        return new OptionPage(new TranslatableText("embeddiumextension.option.details").getString(), ImmutableList.copyOf(groups));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage render() {
        List<OptionGroup> groups = new ObjectArrayList<>();

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.multi_dimension_fog").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.multi_dimension_fog.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.multiDimensionFogControl = value, options -> options.renderSettings.multiDimensionFogControl)
                        .build()
                )
                .add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.fog_start").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.fog_start.tooltip").getString())
                        .setControl(option -> new SliderControlExtended(option, 20, 100, 1, ControlValueFormatter.percentage(), false))
                        .setBinding((options, value) -> options.renderSettings.fogStart = value, options -> options.renderSettings.fogStart)
                        .build()
                )
                .build());

        if (SodiumExtraClientMod.options().renderSettings.multiDimensionFogControl) {
            DimensionOptionsAccessor.getBaseDimensions()
                    .stream()
                    .filter(dim -> !SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.containsKey(dim.getValue()))
                    .forEach(dim -> SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.put(dim.getValue(), 0));
            groups.add(SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.keySet().stream()
                    .map(identifier -> OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.fog", translatableName(identifier, "dimensions").getString()).getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.fog.tooltip").getString())
                            .setControl(option -> new SliderControlExtended(option, 0, 33, 1, ControlValueFormatterExtended.fogDistance(), false))
                            .setBinding((opts, val) -> opts.renderSettings.dimensionFogDistanceMap.put(identifier, val),
                                    opts -> opts.renderSettings.dimensionFogDistanceMap.getOrDefault(identifier, 0))
                            .build()
                    ).collect(
                            OptionGroup::createBuilder,
                            OptionGroup.Builder::add,
                            (b1, b2) -> {
                            }
                    ).build()
            );
        } else {
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.single_fog").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.single_fog.tooltip").getString())
                            .setControl(option -> new SliderControlExtended(option, 0, 33, 1, ControlValueFormatterExtended.fogDistance(), false))
                            .setBinding((options, value) -> options.renderSettings.fogDistance = value, options -> options.renderSettings.fogDistance)
                            .build()
                    )
                    .build());
        }

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.linear_flat_color_blender").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.linear_flat_color_blender.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .setImpact(OptionImpact.VARIES)
                        .setBinding((options, value) -> options.renderSettings.useLinearFlatColorBlender = value, options -> options.renderSettings.useLinearFlatColorBlender)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.light_updates").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.light_updates.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.lightUpdates = value, options -> options.renderSettings.lightUpdates)
                        .build()
                )
                .build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("entity.minecraft.item_frame"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.item_frames.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.renderSettings.itemFrame = value, opts -> opts.renderSettings.itemFrame)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("entity.minecraft.armor_stand"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.armor_stands.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.armorStand = value, options -> options.renderSettings.armorStand)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("entity.minecraft.painting"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.paintings.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.painting = value, options -> options.renderSettings.painting)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.beacon_beam").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.beacon_beam.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.renderSettings.beaconBeam = value, opts -> opts.renderSettings.beaconBeam)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.enchanting_table_book").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.enchanting_table_book.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.renderSettings.enchantingTableBook = value, opts -> opts.renderSettings.enchantingTableBook)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(parseVanillaString("block.minecraft.piston"))
                        .setTooltip(new TranslatableText("embeddiumextension.option.piston.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.piston = value, options -> options.renderSettings.piston)
                        .build()
                )
                .build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.item_frame_name_tag").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.item_frame_name_tag.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.renderSettings.itemFrameNameTag = value, opts -> opts.renderSettings.itemFrameNameTag)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.player_name_tag").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.player_name_tag.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.renderSettings.playerNameTag = value, options -> options.renderSettings.playerNameTag)
                        .build()
                )
                .build());
        return new OptionPage(new TranslatableText("embeddiumextension.option.render").getString(), ImmutableList.copyOf(groups));
    }

    @Contract(" -> new")
    public static @NotNull OptionPage extra() {
        List<OptionGroup> groups = new ObjectArrayList<>();
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, SodiumExtraGameOptionPages.sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.use_fast_random").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.use_fast_random.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.useFastRandom = value, options -> options.extraSettings.useFastRandom)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.reduce_resolution_on_mac").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.reduce_resolution_on_mac.tooltip").getString())
                        .setEnabled(MinecraftClient.IS_SYSTEM_MAC)
                        .setImpact(OptionImpact.HIGH)
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.extraSettings.reduceResolutionOnMac = value, opts -> opts.extraSettings.reduceResolutionOnMac)
                        .build()
                ).build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(SodiumExtraGameOptions.OverlayCorner.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.overlay_corner").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.overlay_corner.tooltip").getString())
                        .setControl(option -> new CyclingControl<>(option, SodiumExtraGameOptions.OverlayCorner.class))
                        .setBinding((opts, value) -> opts.extraSettings.overlayCorner = value, opts -> opts.extraSettings.overlayCorner)
                        .build()
                )
                .add(OptionImpl.createBuilder(SodiumExtraGameOptions.TextContrast.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.text_contrast").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.text_contrast.tooltip").getString())
                        .setControl(option -> new CyclingControl<>(option, SodiumExtraGameOptions.TextContrast.class))
                        .setBinding((opts, value) -> opts.extraSettings.textContrast = value, opts -> opts.extraSettings.textContrast)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.show_coordinates").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.show_coordinates.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.extraSettings.showCoords = value, opts -> opts.extraSettings.showCoords)
                        .build()
                )
                .add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.cloud_height").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.cloud_height.tooltip").getString())
                        .setControl(option -> new SliderControl(option, 0, 255, 1, ControlValueFormatter.number()))
                        .setBinding((options, value) -> options.extraSettings.cloudHeight = value, options -> options.extraSettings.cloudHeight)
                        .build()
                )
                .build());
        groups.add(OptionGroup.createBuilder()
                        .add(OptionImpl.createBuilder(boolean.class, vanillaOpts)
                                .setName(new TranslatableText("embeddiumextension.option.advanced_item_tooltips").getString())
                                .setTooltip(new TranslatableText("embeddiumextension.option.advanced_item_tooltips.tooltip").getString())
                                .setControl(TickBoxControl::new)
                                .setBinding((opts, value) -> opts.advancedItemTooltips = value, opts -> opts.advancedItemTooltips)
                                .build()
                        )
                .build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.toasts").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.toasts.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.toasts = value, options -> options.extraSettings.toasts)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.advancement_toast").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.advancement_toast.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.advancementToast = value, options -> options.extraSettings.advancementToast)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.recipe_toast").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.recipe_toast.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.recipeToast = value, options -> options.extraSettings.recipeToast)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.system_toast").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.system_toast.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.systemToast = value, options -> options.extraSettings.systemToast)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.tutorial_toast").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.tutorial_toast.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.tutorialToast = value, options -> options.extraSettings.tutorialToast)
                        .build()
                )
                .build());
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.instant_sneak").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.instant_sneak.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.instantSneak = value, options -> options.extraSettings.instantSneak)
                        .build()
                )
                .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                        .setName(new TranslatableText("embeddiumextension.option.prevent_shaders").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.prevent_shaders.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((options, value) -> options.extraSettings.preventShaders = value, options -> options.extraSettings.preventShaders)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build()
                )
                .build());

        return new OptionPage(new TranslatableText("embeddiumextension.option.extras").getString(), ImmutableList.copyOf(groups));
    }

    public static @NotNull Text translatableName(@NotNull Identifier identifier, String category) {
        String key = "options.".concat(category).concat(".").concat(identifier.getNamespace()).concat(".").concat(identifier.getPath());

        Text translatable = new TranslatableText(key);
        if (translatable.getString().equals(key)) {
            translatable = new TranslatableText(
                    Arrays.stream(key.substring(key.lastIndexOf('.') + 1).split("_"))
                            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                            .collect(Collectors.joining(" "))
            );
        }
        return translatable;
    }

    @SuppressWarnings("SameParameterValue")
    public static @NotNull Text translatableTooltip(@NotNull Identifier identifier, String category) {
        String key = "options.".concat(category).concat(".").concat(identifier.getNamespace()).concat(".").concat(identifier.getPath()).concat(".tooltip");

        Text translatable = new TranslatableText(key);
        if (translatable.getString().equals(key)) {
            translatable = new TranslatableText(
                    "embeddiumextension.option.".concat(category).concat(".tooltips"),
                    translatableName(identifier, category)
            );
        }
        return translatable;
    }

    public static class ReduceNewPagesMode {
        public static void performanceInserting(List<OptionGroup> groups) {
            groups.add(OptionGroup.createBuilder().add(
                            OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                                    .setName(new TranslatableText("embeddiumextension.option.reduce_resolution_on_mac").getString())
                                    .setTooltip(new TranslatableText("embeddiumextension.option.reduce_resolution_on_mac.tooltip").getString())
                                    .setEnabled(MinecraftClient.IS_SYSTEM_MAC)
                                    .setImpact(OptionImpact.HIGH)
                                    .setControl(TickBoxControl::new)
                                    .setBinding((opts, value) -> opts.extraSettings.reduceResolutionOnMac = value, opts -> opts.extraSettings.reduceResolutionOnMac)
                                    .build()
                    )
                    .build());
        }

        public static void generalInserting(List<OptionGroup> groups) {
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, SodiumExtraGameOptionPages.sodiumExtraOpts)
                            .setName(new TranslatableText("options.main.no_new_pages").getString())
                            .setTooltip(new TranslatableText("options.main.no_new_pages.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((sodiumExtraGameOptions, aBoolean) -> SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.set(aBoolean), options  -> SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.get())
                            .build()
                    )
                    .build());

            if (!SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.get()) return;

            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.show_coordinates").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.show_coordinates.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.extraSettings.showCoords = value, opts -> opts.extraSettings.showCoords)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(SodiumExtraGameOptions.OverlayCorner.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.overlay_corner").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.overlay_corner.tooltip").getString())
                            .setControl(option -> new CyclingControl<>(option, SodiumExtraGameOptions.OverlayCorner.class))
                            .setBinding((opts, value) -> opts.extraSettings.overlayCorner = value, opts -> opts.extraSettings.overlayCorner)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(SodiumExtraGameOptions.TextContrast.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.text_contrast").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.text_contrast.tooltip").getString())
                            .setControl(option -> new CyclingControl<>(option, SodiumExtraGameOptions.TextContrast.class))
                            .setBinding((opts, value) -> opts.extraSettings.textContrast = value, opts -> opts.extraSettings.textContrast)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder().add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                    .setName(new TranslatableText("embeddiumextension.option.cloud_height").getString())
                    .setTooltip(new TranslatableText("embeddiumextension.option.cloud_height.tooltip").getString())
                    .setControl(option -> new SliderControl(option, 0, 255, 1, ControlValueFormatter.number()))
                    .setBinding((options, value) -> options.extraSettings.cloudHeight = value, options -> options.extraSettings.cloudHeight)
                    .build()
            ).build());

            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.multi_dimension_fog").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.multi_dimension_fog.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.multiDimensionFogControl = value, options -> options.renderSettings.multiDimensionFogControl)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.fog_start").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.fog_start.tooltip").getString())
                            .setControl(option -> new SliderControlExtended(option, 20, 100, 1, ControlValueFormatter.percentage(), false))
                            .setBinding((options, value) -> options.renderSettings.fogStart = value, options -> options.renderSettings.fogStart)
                            .build()
                    )
                    .build());

            if (SodiumExtraClientMod.options().renderSettings.multiDimensionFogControl) {
                DimensionOptionsAccessor.getBaseDimensions()
                        .stream()
                        .filter(dim -> !SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.containsKey(dim.getValue()))
                        .forEach(dim -> SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.put(dim.getValue(), 0));
                groups.add(SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.keySet().stream()
                        .map(identifier -> OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                                .setName(new TranslatableText("embeddiumextension.option.fog", translatableName(identifier, "dimensions").getString()).getString())
                                .setTooltip(new TranslatableText("embeddiumextension.option.fog.tooltip").getString())
                                .setControl(option -> new SliderControlExtended(option, 0, 33, 1, ControlValueFormatterExtended.fogDistance(), false))
                                .setBinding((opts, val) -> opts.renderSettings.dimensionFogDistanceMap.put(identifier, val),
                                        opts -> opts.renderSettings.dimensionFogDistanceMap.getOrDefault(identifier, 0))
                                .build()
                        ).collect(OptionGroup::createBuilder, OptionGroup.Builder::add, (b1, b2) -> {})
                        .build()
                );
            } else {
                groups.add(OptionGroup.createBuilder()
                        .add(OptionImpl.createBuilder(int.class, sodiumExtraOpts)
                                .setName(new TranslatableText("embeddiumextension.option.single_fog").getString())
                                .setTooltip(new TranslatableText("embeddiumextension.option.single_fog.tooltip").getString())
                                .setControl(option -> new SliderControlExtended(option, 0, 33, 1, ControlValueFormatterExtended.fogDistance(), false))
                                .setBinding((options, value) -> options.renderSettings.fogDistance = value, options -> options.renderSettings.fogDistance)
                                .build()
                        )
                        .build());
            }
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, vanillaOpts)
                            .setName(new TranslatableText("embeddiumextension.option.advanced_item_tooltips").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.advanced_item_tooltips.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.advancedItemTooltips = value, opts -> opts.advancedItemTooltips)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.toasts").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.toasts.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.toasts = value, options -> options.extraSettings.toasts)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.advancement_toast").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.advancement_toast.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.advancementToast = value, options -> options.extraSettings.advancementToast)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.recipe_toast").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.recipe_toast.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.recipeToast = value, options -> options.extraSettings.recipeToast)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.system_toast").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.system_toast.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.systemToast = value, options -> options.extraSettings.systemToast)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.tutorial_toast").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.tutorial_toast.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.tutorialToast = value, options -> options.extraSettings.tutorialToast)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.instant_sneak").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.instant_sneak.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.instantSneak = value, options -> options.extraSettings.instantSneak)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.prevent_shaders").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.prevent_shaders.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.preventShaders = value, options -> options.extraSettings.preventShaders)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, SodiumExtraGameOptionPages.sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.use_fast_random").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.use_fast_random.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.extraSettings.useFastRandom = value, options -> options.extraSettings.useFastRandom)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    )
                    .build());
        }

        public static void qualityInserting(List<OptionGroup> groups) {
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.sky").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.sky.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.detailSettings.sky = value, opts -> opts.detailSettings.sky)
                            .build()
                    ).add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.stars").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.stars.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.detailSettings.stars = value, opts -> opts.detailSettings.stars)
                            .build()
                    ).add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.sun_moon").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.sun_moon.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.detailSettings.sunMoon = value, opts -> opts.detailSettings.sunMoon)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    ).add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("soundCategory.weather"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.rain_snow.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.detailSettings.rainSnow = value, opts -> opts.detailSettings.rainSnow)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.biome_colors").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.biome_colors.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.detailSettings.biomeColors = value, options -> options.detailSettings.biomeColors)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.sky_colors").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.sky_colors.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.detailSettings.skyColors = value, options -> options.detailSettings.skyColors)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    )
                    .build());

            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("entity.minecraft.item_frame"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.item_frames.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.renderSettings.itemFrame = value, opts -> opts.renderSettings.itemFrame)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("entity.minecraft.armor_stand"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.armor_stands.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.armorStand = value, options -> options.renderSettings.armorStand)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("entity.minecraft.painting"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.paintings.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.painting = value, options -> options.renderSettings.painting)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.beacon_beam").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.beacon_beam.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.renderSettings.beaconBeam = value, opts -> opts.renderSettings.beaconBeam)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.enchanting_table_book").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.enchanting_table_book.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.renderSettings.enchantingTableBook = value, opts -> opts.renderSettings.enchantingTableBook)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("block.minecraft.piston"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.piston.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.piston = value, options -> options.renderSettings.piston)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.item_frame_name_tag").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.item_frame_name_tag.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.renderSettings.itemFrameNameTag = value, opts -> opts.renderSettings.itemFrameNameTag)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.player_name_tag").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.player_name_tag.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.playerNameTag = value, options -> options.renderSettings.playerNameTag)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(I18n.translate("options.animations.all"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.animations_all.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.animationSettings.animation = value, opts -> opts.animationSettings.animation)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .build());

            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("block.minecraft.water"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.animate_water.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.animationSettings.water = value, opts -> opts.animationSettings.water)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("block.minecraft.lava"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.animate_lava.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.animationSettings.lava = value, opts -> opts.animationSettings.lava)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("block.minecraft.fire"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.animate_fire.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.animationSettings.fire = value, opts -> opts.animationSettings.fire)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(parseVanillaString("block.minecraft.nether_portal"))
                            .setTooltip(new TranslatableText("embeddiumextension.option.animate_portal.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((opts, value) -> opts.animationSettings.portal = value, opts -> opts.animationSettings.portal)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.block_animations").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.block_animations.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.animationSettings.blockAnimations = value, options -> options.animationSettings.blockAnimations)
                            .setFlags(OptionFlag.REQUIRES_ASSET_RELOAD)
                            .build()
                    )
                    .build());
        }

        public static void advancedInserting(List<OptionGroup> groups) {
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.light_updates").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.light_updates.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setBinding((options, value) -> options.renderSettings.lightUpdates = value, options -> options.renderSettings.lightUpdates)
                            .build()
                    )
                    .build());
            groups.add(OptionGroup.createBuilder()
                    .add(OptionImpl.createBuilder(boolean.class, sodiumExtraOpts)
                            .setName(new TranslatableText("embeddiumextension.option.linear_flat_color_blender").getString())
                            .setTooltip(new TranslatableText("embeddiumextension.option.linear_flat_color_blender.tooltip").getString())
                            .setControl(TickBoxControl::new)
                            .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                            .setImpact(OptionImpact.VARIES)
                            .setBinding((options, value) -> options.renderSettings.useLinearFlatColorBlender = value, options -> options.renderSettings.useLinearFlatColorBlender)
                            .build()
                    ).build());
        }
    }
}

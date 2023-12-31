package com.teampotato.embeddiumextension.mixin.sodium.resolution;

import com.teampotato.embeddiumextension.client.gui.options.control.ControlValueFormatterExtended;
import com.teampotato.embeddiumextension.client.gui.options.control.SliderControlExtended;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.storage.MinecraftOptionsStorage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.VideoMode;
import net.minecraft.client.util.Window;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Optional;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {

    @Shadow
    @Final
    private static MinecraftOptionsStorage vanillaOpts;

    @Inject(method = "general", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup;createBuilder()Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    private static void general(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        Window window = MinecraftClient.getInstance().getWindow();

        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(int.class, vanillaOpts)
                        .setName(new TranslatableText("options.fullscreen.resolution").getString())
                        .setTooltip(new TranslatableText("embeddiumextension.option.resolution.tooltip").getString())
                        .setControl(option -> new SliderControlExtended(option, 0, MinecraftClient.getInstance().getWindow().getMonitor() != null ? MinecraftClient.getInstance().getWindow().getMonitor().getVideoModeCount() : 0, 1, ControlValueFormatterExtended.resolution(), true))
                        .setBinding((options, value) -> {
                            if (window.getMonitor() != null) {
                                if (value == 0) {
                                    window.setVideoMode(Optional.empty());
                                } else {
                                    window.setVideoMode(Optional.of(window.getMonitor().getVideoMode(value - 1)));
                                }
                            }
                            window.applyVideoMode();
                        }, options -> {
                            if (window.getMonitor() == null) {
                                return 0;
                            } else {
                                Optional<VideoMode> optional = window.getVideoMode();
                                return optional.map((videoMode) -> window.getMonitor().findClosestVideoModeIndex(videoMode) + 1).orElse(0);
                            }
                        })
                        .setImpact(OptionImpact.HIGH)
                        .build())
                .build());
    }
}

package com.teampotato.embeddiumextension.mixin.compat;

import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static com.teampotato.embeddiumextension.client.gui.SodiumExtraGameOptionPages.ReduceNewPagesMode.*;

@Mixin(SodiumGameOptionPages.class)
public abstract class MixinSodiumGameOptionPages {
    @Inject(method = "performance", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", shift = At.Shift.BEFORE, ordinal = 0), locals = LocalCapture.CAPTURE_FAILEXCEPTION, remap = false)
    private static void performance(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        if (!SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.get()) return;
        performanceInserting(groups);
    }

    @Inject(method = "general", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, remap = false)
    private static void general(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        generalInserting(groups);
    }

    @Inject(method = "quality", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, remap = false)
    private static void quality(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        if (!SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.get()) return;
        qualityInserting(groups);
    }

    @Inject(method = "advanced", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, remap = false)
    private static void advanced(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        if (!SodiumExtraClientMod.REDUCE_NEW_OPTION_PAGES.get()) return;
        advancedInserting(groups);
    }
}

package me.flashyreese.mods.embeddiumextension.mixin.sky_colors;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class MixinBiome {
    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    private void differentSkyColor(CallbackInfoReturnable<Integer> cir) {
        if (!SodiumExtraClientMod.options().detailSettings.skyColors) {
            cir.setReturnValue(7907327);
        }
    }
}
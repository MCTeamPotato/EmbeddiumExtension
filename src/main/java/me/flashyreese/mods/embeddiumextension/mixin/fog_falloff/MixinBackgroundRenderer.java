package me.flashyreese.mods.embeddiumextension.mixin.fog_falloff;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {
    @ModifyArg(method = "setupFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;fogStart(F)V"), index = 0)
    private static float modifySetShaderFogStart(float original) {
        return original * ((float) SodiumExtraClientMod.options().renderSettings.fogStart / 100);
    }
}

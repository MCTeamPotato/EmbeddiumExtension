package me.flashyreese.mods.embeddiumextension.mixin.stars;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WorldRenderer.class, priority = 1500)
public class MixinWorldRenderer {
    @Redirect(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    ordinal = 0,
                    target = "Lnet/minecraft/client/world/ClientWorld;method_23787(F)F"
            ),
            require = 0
    )
    public float redirectGetStarBrightness(ClientWorld instance, float f) {
        if (SodiumExtraClientMod.options().detailSettings.stars) {
            return instance.method_23787(f);
        } else {
            return 0.0f;
        }
    }
}

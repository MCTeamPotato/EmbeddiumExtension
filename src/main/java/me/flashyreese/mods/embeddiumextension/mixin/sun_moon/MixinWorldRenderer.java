package me.flashyreese.mods.embeddiumextension.mixin.sun_moon;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {

    @Mutable
    @Shadow
    @Final
    private static Identifier SUN;

    @Mutable
    @Shadow
    @Final
    private static Identifier MOON_PHASES;

    @Redirect(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/SkyProperties;getFogColorOverride(FF)[F"
            )
    )
    public float[] redirectGetFogColorOverride(SkyProperties instance, float skyAngle, float tickDelta) {
        if (SodiumExtraClientMod.options().detailSettings.sunMoon) {
            return instance.getFogColorOverride(skyAngle, tickDelta);
        } else {
            return null;
        }
    }

    @Unique private static final Identifier VANILLA_MOON_PHASES = new Identifier("textures/environment/moon_phases.png");
    @Unique private static final Identifier VANILLA_SUN = new Identifier("textures/environment/sun.png");
    @Unique private static final Identifier EXTRA_MOON_PHASES = new Identifier("embeddiumextension", "textures/transparent.png");
    @Unique private static final Identifier EXTRA_SUN = new Identifier("embeddiumextension", "textures/transparent.png");

    @Inject(
            method = "reload()V",
            at = @At(value = "TAIL")
    )
    private void postWorldRendererReload(CallbackInfo ci) {
        if (SodiumExtraClientMod.options().detailSettings.sunMoon) {
            MOON_PHASES = VANILLA_MOON_PHASES;
            SUN = VANILLA_SUN;
        } else {
            MOON_PHASES = EXTRA_MOON_PHASES;
            SUN = EXTRA_SUN;
        }
    }
}

package me.flashyreese.mods.embeddiumextension.mixin.fog;

import com.mojang.blaze3d.systems.RenderSystem;
import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.tag.FluidTags;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("deprecation")
@Mixin(BackgroundRenderer.class)
public abstract class MixinBackgroundRenderer {
    @Inject(method = "setupFog", at = @At(value = "TAIL"))
    private static void applyFog(@NotNull Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float partialTicks, CallbackInfo ci) {
        Entity entity = camera.getFocusedEntity();
        SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.putIfAbsent(entity.world.getDimension().getSkyProperties(), 0);
        int fogDistance = SodiumExtraClientMod.options().renderSettings.multiDimensionFogControl ? SodiumExtraClientMod.options().renderSettings.dimensionFogDistanceMap.get(entity.world.getDimension().getSkyProperties()) : SodiumExtraClientMod.options().renderSettings.fogDistance;
        if (fogDistance == 0 || (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(StatusEffects.BLINDNESS))) {
            return;
        }
        if (!camera.getSubmergedFluidState().isIn(FluidTags.WATER) && !camera.getSubmergedFluidState().isIn(FluidTags.LAVA) && (thickFog || fogType == BackgroundRenderer.FogType.FOG_TERRAIN)) {
            float fogStart = (float) SodiumExtraClientMod.options().renderSettings.fogStart / 100;
            if (fogDistance == 33) {
                RenderSystem.fogStart(Short.MAX_VALUE - 1 * fogStart);
                RenderSystem.fogEnd(Short.MAX_VALUE);
            } else {
                RenderSystem.fogStart(fogDistance * 16 * fogStart);
                RenderSystem.fogEnd((fogDistance + 1) * 16);
            }
        }
    }
}

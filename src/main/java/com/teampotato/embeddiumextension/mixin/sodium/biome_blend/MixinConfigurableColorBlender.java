package com.teampotato.embeddiumextension.mixin.sodium.biome_blend;

import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import com.teampotato.embeddiumextension.client.render.terrain.color.LinearFlatColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.blender.BiomeColorBlender;
import me.jellysquid.mods.sodium.client.model.quad.blender.SmoothBiomeColorBlender;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "me.jellysquid.mods.sodium.client.model.quad.blender.ConfigurableColorBlender")
public class MixinConfigurableColorBlender {
    @Unique
    private final BiomeColorBlender ee$linearFlatColorBlender = new LinearFlatColorBlender();

    /**
     * @author FlashyReese
     * @reason Includes linear flat color blender
     */
    @Redirect(
            method = "getColors",
            at = @At(
                    value = "INVOKE",
                    target = "Lme/jellysquid/mods/sodium/client/model/quad/blender/BiomeColorBlender;getColors(Lnet/minecraft/client/color/block/BlockColorProvider;Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lme/jellysquid/mods/sodium/client/model/quad/ModelQuadView;)[I"
            )
    )
    public int[] createBiomeColorBlender(BiomeColorBlender blender, BlockColorProvider colorizer, BlockRenderView world, BlockState state, BlockPos origin, ModelQuadView quad) {
        if (blender instanceof SmoothBiomeColorBlender && SodiumExtraClientMod.options().renderSettings.useLinearFlatColorBlender) {
            return this.ee$linearFlatColorBlender.getColors(colorizer, world, state, origin, quad);
        } else {
            return blender.getColors(colorizer, world, state, origin, quad);
        }
    }
}
package me.flashyreese.mods.embeddiumextension.mixin.render.entity;

import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M> {

    protected MixinLivingEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.BEFORE, ordinal = 0), cancellable = true)
    private void onRender(T entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (entity instanceof ArmorStandEntity && !SodiumExtraClientMod.options().renderSettings.armorStand) {
            ci.cancel();
            if (this.hasLabel(entity)) {
                this.renderLabelIfPresent(entity, entity.getDisplayName(), matrixStack, vertexConsumerProvider, i);
            }
        }
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    @Inject(method = "hasLabel(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
    private <T extends LivingEntity> void hasLabel(T entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof AbstractClientPlayerEntity && !SodiumExtraClientMod.options().renderSettings.playerNameTag) {
            cir.setReturnValue(false);
        }
    }
}

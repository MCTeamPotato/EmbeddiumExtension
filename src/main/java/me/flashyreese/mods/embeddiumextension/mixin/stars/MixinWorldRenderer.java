package me.flashyreese.mods.embeddiumextension.mixin.stars;

import com.mojang.blaze3d.systems.RenderSystem;
import me.flashyreese.mods.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"deprecation"})
@Mixin(value = WorldRenderer.class, priority = 1500)
public class MixinWorldRenderer {
    @Unique
    private boolean ee$shoudlRenderStar;

    @Inject(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;color4f(FFFF)V", ordinal = 1, shift = At.Shift.BEFORE))
    private void checkShouldRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        this.ee$shoudlRenderStar = SodiumExtraClientMod.options().detailSettings.stars;
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;color4f(FFFF)V", ordinal = 1))
    private void onRenderStar(float red, float green, float blue, float alpha) {
        if (this.ee$shoudlRenderStar) RenderSystem.color4f(red, green, blue, alpha);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/VertexBuffer;bind()V", ordinal = 1))
    private void onRenderStar(VertexBuffer instance) {
        if (this.ee$shoudlRenderStar) instance.bind();
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexFormat;startDrawing(J)V", ordinal = 1))
    private void onRenderStar(VertexFormat instance, long i) {
        if (this.ee$shoudlRenderStar) instance.startDrawing(0L);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/client/gl/VertexBuffer;draw(Lnet/minecraft/util/math/Matrix4f;I)V"))
    private void onRenderStar(VertexBuffer instance, Matrix4f matrix, int mode) {
        if (this.ee$shoudlRenderStar) instance.draw(matrix, mode);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/VertexBuffer;unbind()V", ordinal = 1))
    private void onRenderStar() {
        if (this.ee$shoudlRenderStar) VertexBuffer.unbind();
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexFormat;endDrawing()V", ordinal = 1))
    private void onRenderStar(VertexFormat instance) {
        if (this.ee$shoudlRenderStar) instance.endDrawing();
    }
}

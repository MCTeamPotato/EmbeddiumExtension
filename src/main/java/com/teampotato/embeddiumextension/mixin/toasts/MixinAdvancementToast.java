package com.teampotato.embeddiumextension.mixin.toasts;

import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementToast.class)
public class MixinAdvancementToast {
    @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
    public void draw(MatrixStack matrices, ToastManager manager, long startTime, CallbackInfoReturnable<Toast.Visibility> cir) {
        if (!SodiumExtraClientMod.options().extraSettings.advancementToast) {
            cir.setReturnValue(Toast.Visibility.HIDE);
        }
    }
}

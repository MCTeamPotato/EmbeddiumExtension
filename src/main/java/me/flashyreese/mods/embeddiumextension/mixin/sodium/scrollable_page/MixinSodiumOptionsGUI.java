package me.flashyreese.mods.embeddiumextension.mixin.sodium.scrollable_page;

import me.flashyreese.mods.embeddiumextension.client.gui.scrollable_page.OptionPageScrollFrame;
import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlElement;
import me.jellysquid.mods.sodium.client.util.Dim2i;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SodiumOptionsGUI.class, remap = false)
public abstract class MixinSodiumOptionsGUI extends Screen {

    @Shadow
    private OptionPage currentPage;

    protected MixinSodiumOptionsGUI(Text title) {
        super(title);
    }

    @Inject(method = "rebuildGUIOptions", at = @At(value = "HEAD"), cancellable = true)
    private void rebuildGUIOptions(@NotNull CallbackInfo ci) {
        int x = 6;
        int y = 28;

        OptionPageScrollFrame optionPageScrollFrame = new OptionPageScrollFrame(new Dim2i(x, y, 200, this.height - y - 10), this.currentPage);
        this.addChild(optionPageScrollFrame);
        ci.cancel();
    }

    @Inject(method = "renderOptionTooltip", at = @At(value = "HEAD"), cancellable = true)
    private void renderOptionTooltip(MatrixStack matrixStack, ControlElement<?> element, @NotNull CallbackInfo ci) {
        ci.cancel();
    }
}

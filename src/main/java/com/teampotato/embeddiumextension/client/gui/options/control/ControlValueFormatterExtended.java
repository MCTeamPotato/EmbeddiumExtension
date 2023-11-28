package com.teampotato.embeddiumextension.client.gui.options.control;

import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Monitor;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface ControlValueFormatterExtended extends ControlValueFormatter {
    static @NotNull ControlValueFormatter resolution() {
        Monitor monitor = MinecraftClient.getInstance().getWindow().getMonitor();
        return (v) -> {
            if (monitor == null) {
                return I18n.translate("options.fullscreen.unavailable");
            } else {
                return v == 0 ? I18n.translate("options.fullscreen.current") : new LiteralText(monitor.getVideoMode(v - 1).toString()).getString();
            }
        };
    }

    @Contract(pure = true)
    static @NotNull ControlValueFormatter fogDistance() {
        return (v) -> {
            if (v == 0) {
                return I18n.translate("generator.default");
            } else if (v == 33) {
                return I18n.translate("options.off");
            } else {
                return I18n.translate("options.chunks", v);
            }
        };
    }
}

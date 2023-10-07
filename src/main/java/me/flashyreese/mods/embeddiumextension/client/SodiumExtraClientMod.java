package me.flashyreese.mods.embeddiumextension.client;

import me.flashyreese.mods.embeddiumextension.client.gui.HudRenderImpl;
import me.flashyreese.mods.embeddiumextension.client.gui.SodiumExtraGameOptions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("embeddiumextension")
public class SodiumExtraClientMod {
    private static SodiumExtraGameOptions CONFIG;
    private static Logger LOGGER;

    public static Logger logger() {
        if (LOGGER == null) {
            LOGGER = LogManager.getLogger("Embeddium Extension");
        }

        return LOGGER;
    }

    public static SodiumExtraGameOptions options() {
        if (CONFIG == null) {
            CONFIG = loadConfig();
        }

        return CONFIG;
    }

    private static @NotNull SodiumExtraGameOptions loadConfig() {
        return SodiumExtraGameOptions.load(FMLLoader.getGamePath().resolve("config").resolve("embeddiumextension-options.json").toFile());
    }

    public SodiumExtraClientMod() {
        MinecraftForge.EVENT_BUS.register(new HudRenderImpl());
    }
}

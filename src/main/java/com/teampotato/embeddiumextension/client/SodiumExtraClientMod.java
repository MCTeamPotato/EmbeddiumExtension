package com.teampotato.embeddiumextension.client;

import com.teampotato.embeddiumextension.client.gui.HudRenderImpl;
import com.teampotato.embeddiumextension.client.gui.SodiumExtraGameOptions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
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
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        MinecraftForge.EVENT_BUS.register(new HudRenderImpl());
    }
}

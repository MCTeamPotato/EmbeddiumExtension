package com.teampotato.embeddiumextension.client;

import com.teampotato.embeddiumextension.client.gui.HudRenderImpl;
import com.teampotato.embeddiumextension.client.gui.SodiumExtraGameOptions;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("embeddiumextension")
public class SodiumExtraClientMod {
    private static SodiumExtraGameOptions sodiumExtraGameOptions;
    private static Logger LOGGER;

    public static Logger logger() {
        if (LOGGER == null) {
            LOGGER = LogManager.getLogger("Embeddium Extension");
        }

        return LOGGER;
    }

    public static SodiumExtraGameOptions options() {
        if (sodiumExtraGameOptions == null) {
            sodiumExtraGameOptions = SodiumExtraGameOptions.load(FMLLoader.getGamePath().resolve("config").resolve("embeddiumextension-options.json").toFile());
        }

        return sodiumExtraGameOptions;
    }

    private static final ForgeConfigSpec CONFIG;
    public static final ForgeConfigSpec.BooleanValue REDUCE_NEW_OPTION_PAGES;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("EmbeddiumExtension");
        REDUCE_NEW_OPTION_PAGES = builder.define("ReduceNewOptionPages", false);
        builder.pop();
        CONFIG = builder.build();
    }

    public SodiumExtraClientMod() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        MinecraftForge.EVENT_BUS.register(new HudRenderImpl());
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG);
    }
}

package com.teampotato.embeddiumextension.mixin.cloud;

import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import net.minecraft.client.render.SkyProperties;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkyProperties.Overworld.class)
public abstract class MixinSkyPropertiesOverworld extends SkyProperties {
    public MixinSkyPropertiesOverworld(float cloudsHeight, boolean alternateSkyColor, SkyType skyType, boolean brightenLighting, boolean darkened) {
        super(cloudsHeight, alternateSkyColor, skyType, brightenLighting, darkened);
    }

    @Override
    public float getCloudsHeight() {
        return SodiumExtraClientMod.options().extraSettings.cloudHeight;
    }
}

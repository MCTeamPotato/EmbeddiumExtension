package com.teampotato.embeddiumextension.mixin.animation;

import com.teampotato.embeddiumextension.client.SodiumExtraClientMod;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(SpriteAtlasTexture.class)
public abstract class MixinSpriteAtlasTexture extends AbstractTexture {

    @Unique
    private final Map<Supplier<Boolean>, List<Identifier>> ee$animatedSprites = new Object2ObjectOpenHashMap<>();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Identifier arg, CallbackInfo ci) {
        ee$animatedSprites.put(() -> SodiumExtraClientMod.options().animationSettings.water, Arrays.asList(
                new Identifier("minecraft", "block/water_still"),
                new Identifier("minecraft", "block/water_flow")
        ));

        ee$animatedSprites.put(() -> SodiumExtraClientMod.options().animationSettings.lava, Arrays.asList(
                new Identifier("minecraft", "block/lava_still"),
                new Identifier("minecraft", "block/lava_flow")
        ));

        ee$animatedSprites.put(() -> SodiumExtraClientMod.options().animationSettings.portal, Collections.singletonList(
                new Identifier("minecraft", "block/nether_portal")
        ));

        ee$animatedSprites.put(() -> SodiumExtraClientMod.options().animationSettings.fire, Arrays.asList(
                new Identifier("minecraft", "block/fire_0"),
                new Identifier("minecraft", "block/fire_1"),
                new Identifier("minecraft", "block/soul_fire_0"),
                new Identifier("minecraft", "block/soul_fire_1"),
                new Identifier("minecraft", "block/campfire_fire"),
                new Identifier("minecraft", "block/campfire_log_lit"),
                new Identifier("minecraft", "block/soul_campfire_fire"),
                new Identifier("minecraft", "block/soul_campfire_log_lit")
        ));

        ee$animatedSprites.put(() -> SodiumExtraClientMod.options().animationSettings.blockAnimations, Arrays.asList(
                new Identifier("minecraft", "block/magma"),
                new Identifier("minecraft", "block/lantern"),
                new Identifier("minecraft", "block/sea_lantern"),
                new Identifier("minecraft", "block/soul_lantern"),
                new Identifier("minecraft", "block/kelp"),
                new Identifier("minecraft", "block/kelp_plant"),
                new Identifier("minecraft", "block/seagrass"),
                new Identifier("minecraft", "block/tall_seagrass_top"),
                new Identifier("minecraft", "block/tall_seagrass_bottom"),
                new Identifier("minecraft", "block/warped_stem"),
                new Identifier("minecraft", "block/crimson_stem"),
                new Identifier("minecraft", "block/blast_furnace_front_on"),
                new Identifier("minecraft", "block/smoker_front_on"),
                new Identifier("minecraft", "block/stonecutter_saw"),
                new Identifier("minecraft", "block/prismarine"),
                new Identifier("minecraft", "block/respawn_anchor_top"),
                new Identifier("minecraft", "entity/conduit/wind"),
                new Identifier("minecraft", "entity/conduit/wind_vertical")
        ));
    }

    @Redirect(method = "upload", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/Sprite;isAnimated()Z"))
    public boolean sodiumExtra$tickAnimatedSprites(@NotNull Sprite instance) {
        return instance.isAnimated() && SodiumExtraClientMod.options().animationSettings.animation && this.ee$shouldAnimate(instance.getId());
    }

    @Unique
    private boolean ee$shouldAnimate(Identifier identifier) {
        if (identifier != null) {
            for (Map.Entry<Supplier<Boolean>, List<Identifier>> supplierListEntry : this.ee$animatedSprites.entrySet()) {
                if (supplierListEntry.getValue().contains(identifier)) {
                    return supplierListEntry.getKey().get();
                }
            }
        }
        return true;
    }
}

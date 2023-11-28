package com.teampotato.embeddiumextension.client.render.terrain.color;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.blender.SmoothBiomeColorBlender;
import me.jellysquid.mods.sodium.client.util.color.ColorARGB;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import java.util.Arrays;

public class LinearFlatColorBlender extends SmoothBiomeColorBlender {
    @Override
    public int[] getColors(BlockColorProvider colorizer, BlockRenderView world, BlockState state, BlockPos origin, ModelQuadView quad) {
        int[] colors = super.getColors(colorizer, world, state, origin, quad);
        Arrays.fill(colors, this.getAverageColor(colors));
        return colors;
    }

    private int getAverageColor(int[] colors) {
        int a = 0, r = 0, g = 0, b = 0;
        for (int color : colors) {
            a = a + ColorARGB.unpackAlpha(color);
            r = r + ColorARGB.unpackRed(color);
            g = g + ColorARGB.unpackGreen(color);
            b = b + ColorARGB.unpackBlue(color);
        }
        return ColorARGB.pack(r / colors.length, g / colors.length, b / colors.length, a / colors.length);
    }
}

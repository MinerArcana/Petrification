package com.minerarcana.petrification.renderer.tile;

import com.minerarcana.petrification.tileentities.PetrifiedTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class PetrifiedTileXRenderer extends TileEntityRenderer<PetrifiedTile> {

    public PetrifiedTileXRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(PetrifiedTile tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer buf, int combinedLight, int combinedOverlay) {
        if (tile.getTile() != null) {
            BlockRendererDispatcher blockRender = Minecraft.getInstance().getBlockRendererDispatcher();
            stack.push();
            stack.translate(0, 0, 0);
            stack.scale(1, 1, 1);
            blockRender.renderBlock(tile.getBlockState(), stack, buf, combinedLight, combinedOverlay);
            stack.pop();
        }
    }


}

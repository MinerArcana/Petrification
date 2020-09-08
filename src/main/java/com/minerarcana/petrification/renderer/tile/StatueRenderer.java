package com.minerarcana.petrification.renderer.tile;

import com.minerarcana.petrification.tileentities.StatueTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class StatueRenderer extends TileEntityRenderer<StatueTile> {

    public StatueRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(StatueTile tileEntityIn, float partialTicks, MatrixStack matrixstack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(tileEntityIn.getInternalEntity() != null) {
            RenderSystem.pushMatrix();
            RenderSystem.scalef(1.0F, 1.0F, 1.0F);
            matrixstack.scale((float) 1, (float) 1, (float) 1);
            EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
            entityrenderermanager.setRenderShadow(false);
            IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
            entityrenderermanager.renderEntityStatic(tileEntityIn.getInternalEntity(), 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
            entityrenderermanager.setRenderShadow(true);
            irendertypebuffer$impl.finish();
            RenderSystem.popMatrix();
        }
    }
}

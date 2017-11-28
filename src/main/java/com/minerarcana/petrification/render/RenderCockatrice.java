package com.minerarcana.petrification.render;

import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.minerarcana.petrification.Petrification;
import com.minerarcana.petrification.entity.EntityCockatrice;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minerarcana.petrification.Petrification.MODID;

public class RenderCockatrice extends RenderLiving<EntityCockatrice> {
    private static final ResourceLocation texture = new ResourceLocation(MODID, "textures/mob/cockatrice.png");

    public RenderCockatrice(RenderManager renderManager) {
        super(renderManager, new ModelCraftStudio(Petrification.MODID, EntityCockatrice.NAME, 32, 64), 0.3F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityCockatrice entity) {
        return texture;
    }
}

package com.minerarcana.petrification.renderer;

import com.minerarcana.petrification.entities.CockatriceEntity;
import com.minerarcana.petrification.models.CockatriceModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.petrification.Petrification.MOD_ID;

public class CockatriceRenderer extends MobRenderer<CockatriceEntity, CockatriceModel> {

    public CockatriceRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CockatriceModel(),0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(CockatriceEntity entity) {
        if(entity.isAngry()){
            return new ResourceLocation(MOD_ID,"textures/entities/cockatrice_angry.png");
        }
        return new ResourceLocation(MOD_ID,"textures/entities/cockatrice.png");
    }
}

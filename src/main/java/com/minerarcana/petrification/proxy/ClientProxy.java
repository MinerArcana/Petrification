package com.minerarcana.petrification.proxy;

import com.minerarcana.petrification.entity.EntityCockatrice;
import com.minerarcana.petrification.render.RenderCockatrice;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCockatrice.class, RenderCockatrice::new);
    }
}

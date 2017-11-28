package com.minerarcana.petrification;

import com.leviathanstudio.craftstudio.client.registry.CSRegistryHelper;
import com.leviathanstudio.craftstudio.client.registry.CraftStudioLoader;
import com.leviathanstudio.craftstudio.client.util.EnumRenderType;
import com.leviathanstudio.craftstudio.client.util.EnumResourceType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CraftStudioAssetLoader {
    @CraftStudioLoader
    public static void registerCraftStudioAssets() {
        CSRegistryHelper csRegistryHelper = new CSRegistryHelper(Petrification.MODID);
        csRegistryHelper.register(EnumResourceType.MODEL, EnumRenderType.ENTITY, "cockatrice");
        csRegistryHelper.register(EnumResourceType.ANIM, EnumRenderType.ENTITY, "poisonous_breath");
    }
}

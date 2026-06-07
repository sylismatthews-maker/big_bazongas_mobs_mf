package com.dumbmovie.mod.client.renderer;

import com.dumbmovie.mod.DumbMovieMod;
import com.dumbmovie.mod.entity.CustomWaifuMobEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CustomWaifuMobRenderer extends MobRenderer<CustomWaifuMobEntity, HumanoidModel<CustomWaifuMobEntity>> {
    
    // Links to your custom skin images
    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(DumbMovieMod.MODID, "textures/entity/waifu_normal.png");
    private static final ResourceLocation VARIANT_TEXTURE = new ResourceLocation(DumbMovieMod.MODID, "textures/entity/waifu_variant.png");

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        new ResourceLocation(DumbMovieMod.MODID, "waifu_mob"), "main"
    );

    public CustomWaifuMobRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(CustomWaifuMobEntity entity) {
        // If the 50/50 coin flip picked the variant, show the special skin style!
        if (entity.getTags().contains("is_dumbmovie_variant")) {
            return VARIANT_TEXTURE;
        }
        // Otherwise, show the normal standard skin
        return NORMAL_TEXTURE;
    }
}

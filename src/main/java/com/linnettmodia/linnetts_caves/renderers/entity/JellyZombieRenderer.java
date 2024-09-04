package com.linnettmodia.linnetts_caves.renderers.entity;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.renderers.LCrenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
@OnlyIn(Dist.CLIENT)
public class JellyZombieRenderer extends ZombieRenderer {
    private static final ResourceLocation JELLY_TEXTURE = new ResourceLocation(Linnetts_caves.MODID, "textures/entities/final_jelly_zombie.png");
    public JellyZombieRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
    }

    @Override
    public void render(Zombie p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
        super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(Zombie p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        return LCrenderTypes.entityJelly(getTextureLocation(p_115322_));
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie p_113771_) {
        return JELLY_TEXTURE;
    }
}

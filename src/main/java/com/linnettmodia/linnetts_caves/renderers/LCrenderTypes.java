package com.linnettmodia.linnetts_caves.renderers;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.events.ClientEvents;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;

import java.util.function.Function;
@OnlyIn(Dist.CLIENT)
public abstract class LCrenderTypes extends RenderType {
    protected static final ShaderStateShard RENDERTYPE_ENTITY_JELLY_SHADER = new ShaderStateShard(Linnetts_caves.ClientModEvents::getRendertypeEntityJellyShader);
    private static final Function<ResourceLocation,RenderType> ENTITY_JELLY;

    public LCrenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    public static RenderType entityJelly(ResourceLocation resourceLocation)
    {
        return (RenderType)ENTITY_JELLY.apply(resourceLocation);
    }
    static{
        ENTITY_JELLY = Util.memoize((p_286165_) -> {
            RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_JELLY_SHADER).setTextureState(new RenderStateShard.TextureStateShard(p_286165_, false, false)).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true);

            return RenderType.create("entity_jelly", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, rendertype$compositestate);
        });
    }

}

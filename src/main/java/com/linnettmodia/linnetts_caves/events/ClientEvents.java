package com.linnettmodia.linnetts_caves.events;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.io.IOException;
import java.security.PublicKey;
@OnlyIn(Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogRender(ViewportEvent.RenderFog event) {
        if (event.isCanceled()) {
            return;
        }
        Entity player = Minecraft.getInstance().getCameraEntity();
        BlockState blockState = player.level().getBlockState(event.getCamera().getBlockPosition());
        if (blockState.getBlock() == LCblockRegistry.YOGHURT_BLOCK.get()) {
            event.setCanceled(true);
            float farness = 1.8F;
            event.setFarPlaneDistance(farness);
            event.setNearPlaneDistance(0.56F);
            return;
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogColor(ViewportEvent.ComputeFogColor event) {
        Entity player = Minecraft.getInstance().getCameraEntity();
        FluidState fluidstate = player.level().getFluidState(event.getCamera().getBlockPosition());
        BlockState blockState = player.level().getBlockState(event.getCamera().getBlockPosition());
        if (blockState.getBlock() == LCblockRegistry.YOGHURT_BLOCK.get()) {
            event.setBlue(0.8f);
            event.setGreen(0.8f);
            event.setRed(1f);
        }
    }
}

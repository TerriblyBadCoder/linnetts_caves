package com.linnettmodia.linnetts_caves.events;

import com.linnettmodia.linnetts_caves.misc.ClientProxy;
import com.linnettmodia.linnetts_caves.misc.LCBiomeSampler;
import com.linnettmodia.linnetts_caves.worldgen.biome.LCbiomesRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if (event.phase == TickEvent.Phase.END){
            Entity cameraEntity = Minecraft.getInstance().cameraEntity;
            if (cameraEntity != null){
                ClientProxy.lastBiomeAmbientLightAmountPrev = ClientProxy.lastBiomeAmbientLightAmount;
                ClientProxy.lastBiomeAmbientLightAmount = calculateBiomeAmbientLight(cameraEntity);
            }
        }
    }

    private static float calculateBiomeAmbientLight(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return LCbiomesRegistry.getBiomeAmbientLight(player.level().getBiome(player.blockPosition()));
        } else {
            return LCBiomeSampler.sampleBiomesFloat(player.level(), player.position(), LCbiomesRegistry::getBiomeAmbientLight);
        }
    }
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

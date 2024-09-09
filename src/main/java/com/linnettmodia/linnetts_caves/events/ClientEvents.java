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

}

package com.linnettmodia.linnetts_caves.events;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import com.linnettmodia.linnetts_caves.particles.custom.*;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Linnetts_caves.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event){
        event.registerSpriteSet(LCparticleRegistry.VOLATILE_PARTICLES.get(), VolatileParticle.Provider::new);
        event.registerSpriteSet(LCparticleRegistry.FIZZYSHINE_PARTICLES.get(), FizzyShineParticle.Provider::new);
        event.registerSpriteSet(LCparticleRegistry.JELLYBOUNCE_PARTICLES.get(), JellybounceParticle.Provider::new);
        event.registerSpriteSet(LCparticleRegistry.BLUE_JELLYBOUNCE_PARTICLES.get(), BlueJellybounceParticle.Provider::new);
        event.registerSpriteSet(LCparticleRegistry.RED_JELLYBOUNCE_PARTICLES.get(), RedJellybounceParticle.Provider::new);
        event.registerSpriteSet(LCparticleRegistry.SAND_PARTICLES.get(), SandParticles.Provider::new);
    }
}

package com.linnettmodia.linnetts_caves.particles;

import com.google.common.base.Function;
import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LCparticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Linnetts_caves.MODID);
    public static final RegistryObject<SimpleParticleType> VOLATILE_PARTICLES = PARTICLE_TYPES.register("volatile_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIZZYSHINE_PARTICLES = PARTICLE_TYPES.register("fizzyshine_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> JELLYBOUNCE_PARTICLES = PARTICLE_TYPES.register("jellybounce_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> RED_JELLYBOUNCE_PARTICLES = PARTICLE_TYPES.register("red_jellybounce_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLUE_JELLYBOUNCE_PARTICLES = PARTICLE_TYPES.register("blue_jellybounce_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<BlockParticleOption>> SAND_PARTICLES = PARTICLE_TYPES.register("fizzy_sand_particles", () -> new ParticleType<BlockParticleOption>(true, BlockParticleOption.DESERIALIZER) {
        @Override
        public Codec<BlockParticleOption> codec() {
            Function<ParticleType<BlockParticleOption>, Codec<BlockParticleOption>> codec = BlockParticleOption::codec;
            return (Codec)codec.apply(this);
        }
    });
    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}

package com.linnettmodia.linnetts_caves.worldgen.biome;


import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.entity.LCentityRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class LCbiomesRegistry {
    private static final Music CAVE_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);
    public static final ResourceKey<Biome> DESSERT_LAIR = ResourceKey.create(Registries.BIOME, new ResourceLocation(Linnetts_caves.MODID,"dessert_lair"));
    public static void bootstrap(BootstapContext<Biome> context)
    {
        context.register(DESSERT_LAIR, dessert_lair(context));
    }

    public static float getBiomeAmbientLight(Holder<Biome> value) {
        if (value.is(DESSERT_LAIR)) {
            return 0.300F;
        }
        return 0.0F;
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static Biome dessert_lair(BootstapContext<Biome> context){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(LCentityRegistry.JELLY_ZOMBIE.get(),40,2,3));
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);

        return new Biome.BiomeBuilder().hasPrecipitation(false).downfall(0.8f).temperature(1f).generationSettings(biomeBuilder.build()).mobSpawnSettings(spawnBuilder.build()).specialEffects((new BiomeSpecialEffects.Builder().skyColor(0xecaab95).fogColor(0xcaab95).waterFogColor(0xcaab95).waterColor(0xa1d3ed).grassColorOverride(0xb5eda1).backgroundMusic(CAVE_MUSIC).build())).build();
    }
}

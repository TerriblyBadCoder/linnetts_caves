package com.linnettmodia.linnetts_caves.entity;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.entity.custom.FizzyShardEntity;
import com.linnettmodia.linnetts_caves.entity.custom.JellyZombie;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = Linnetts_caves.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LCentityRegistry {
    public static final DeferredRegister<EntityType<?>> DEF_REG
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Linnetts_caves.MODID);
    public static final RegistryObject<EntityType<JellyZombie>> JELLY_ZOMBIE = DEF_REG.register("jelly_zombie", () -> (EntityType) EntityType.Builder.of(JellyZombie::new, MobCategory.MONSTER).sized(0.6F, 1.97F).clientTrackingRange(8).build("jelly_zombie"));
    public static final RegistryObject<EntityType<FizzyShardEntity>> FIZZY_SHARD = DEF_REG.register("fizzy_shard", () -> (EntityType) EntityType.Builder.of(FizzyShardEntity::new, MobCategory.MISC).sized(0.2F, 0.2F).clientTrackingRange(8).build("fizzy_shard"));
}

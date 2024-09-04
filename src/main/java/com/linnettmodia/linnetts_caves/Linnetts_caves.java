package com.linnettmodia.linnetts_caves;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.linnettmodia.linnetts_caves.entity.LCentityRegistry;
import com.linnettmodia.linnetts_caves.events.ClientEvents;
import com.linnettmodia.linnetts_caves.events.EntityEvents;
import com.linnettmodia.linnetts_caves.items.LCitemRegistry;
import com.linnettmodia.linnetts_caves.misc.LCreativeModTabsRegistry;
import com.linnettmodia.linnetts_caves.networking.ModMessages;
import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import com.linnettmodia.linnetts_caves.renderers.entity.FizzyShardRenderer;
import com.linnettmodia.linnetts_caves.renderers.entity.JellyZombieRenderer;
import com.linnettmodia.linnetts_caves.worldgen.biome.ModOverworldRegion;
import com.linnettmodia.linnetts_caves.worldgen.biome.surface.LCsurfaceRules;
import com.linnettmodia.linnetts_caves.worldgen.structures.LCstructureRegistry;
import com.linnettmodia.linnetts_caves.worldgen.structures.piece.LCstrucurePieceRegistry;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.io.IOException;


@Mod(Linnetts_caves.MODID)
public class Linnetts_caves {


    public static final String MODID = "linnetts_caves";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Linnetts_caves() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        LCitemRegistry.register(modEventBus);
        LCblockRegistry.register(modEventBus);
        LCentityRegistry.DEF_REG.register(modEventBus);
        LCparticleRegistry.register(modEventBus);
        LCstructureRegistry.DEF_REG.register(modEventBus);
        LCstrucurePieceRegistry.DEF_REG.register(modEventBus);
        LCreativeModTabsRegistry.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::newEntityAttributes);
        modEventBus.addListener(this::spawnPlacements);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EntityEvents());
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        event.enqueueWork(()-> {
            Regions.register(new ModOverworldRegion(new ResourceLocation(Linnetts_caves.MODID,"overworld_1"),1));
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,MODID, LCsurfaceRules.makeRules());
        });
    }
    public void spawnPlacements(SpawnPlacementRegisterEvent event)
    {
        event.register(LCentityRegistry.JELLY_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }
    public void newEntityAttributes(EntityAttributeCreationEvent event) {

        event.put(LCentityRegistry.JELLY_ZOMBIE.get(),
        Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0).add(Attributes.MOVEMENT_SPEED, 0.23000000417232513).add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.ARMOR, 2.0).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).build()
    );


    }
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        private static ShaderInstance rendertypeEntityJellyShader;

        public static ShaderInstance getRendertypeEntityJellyShader() {
            return rendertypeEntityJellyShader;
        }
        @SubscribeEvent
        public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
            System.out.println("yeah");
            event.registerShader(new ShaderInstance(event.getResourceProvider(),"rendertype_entity_jelly", DefaultVertexFormat.NEW_ENTITY), (p_172711_) -> {
                rendertypeEntityJellyShader = p_172711_;
            });
            System.out.println("beah");
        }
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(LCentityRegistry.JELLY_ZOMBIE.get(), JellyZombieRenderer::new
            );
            EntityRenderers.register(LCentityRegistry.FIZZY_SHARD.get(), FizzyShardRenderer::new
            );

        }
    }
}

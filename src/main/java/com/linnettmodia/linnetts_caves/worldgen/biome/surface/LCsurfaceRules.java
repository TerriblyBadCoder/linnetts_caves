package com.linnettmodia.linnetts_caves.worldgen.biome.surface;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.linnettmodia.linnetts_caves.worldgen.biome.LCbiomesRegistry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class LCsurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource BISCUIT = makeStateRule(LCblockRegistry.CHOCOLATE_BISCUIT_BLOCK.get());
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource ST_CREAM = makeStateRule(LCblockRegistry.STRAWBERRY_CREAM_BLOCK.get());
    private static final SurfaceRules.RuleSource ST_CREAM_BISC = makeStateRule(LCblockRegistry.BISCUIT_ST_CREAM_BLOCK.get());

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource creamSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,SurfaceRules.sequence( createVanillaCreamBands(10,3,15,false,0), createChocolateCreamBands(10,3,15,false,0),ST_CREAM)), SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1,false, CaveSurface.FLOOR),SurfaceRules.sequence( createVanillaCreamBands(10,3,15,false,1), createChocolateCreamBands(10,3,15,false,1),ST_CREAM)),SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(2,false, CaveSurface.FLOOR),SurfaceRules.sequence( createVanillaCreamBands(10,3,15,true,2), createChocolateCreamBands(10,3, 15,true,2),ST_CREAM_BISC)),SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,BISCUIT));
        SurfaceRules.RuleSource biscuit = BISCUIT;
        return SurfaceRules.sequence(SurfaceRules.ifTrue(
            SurfaceRules.isBiome(LCbiomesRegistry.DESSERT_LAIR),SurfaceRules.sequence(bedrock(),creamSurface, createDoughBands(10,3,20),biscuit))
        );
    }
    private static SurfaceRules.RuleSource createVanillaCreamBands(int layers, int layerThickness, int layerDistance, boolean bisc, int offset) {
        layerDistance = 20;
        SurfaceRules.RuleSource vanil = bisc ? SurfaceRules.state(LCblockRegistry.BISCUIT_VA_CREAM_BLOCK.get().defaultBlockState()) : SurfaceRules.state(LCblockRegistry.VANILLA_CREAM_BLOCK.get().defaultBlockState()) ;
        SurfaceRules.RuleSource[] ruleSources = new SurfaceRules.RuleSource[layers];
        for (int i = 1; i <= layers; i++) {
            int yDown = i * layerDistance;
            int extra = i % 3 == 0 ? 1 : 0;
            SurfaceRules.ConditionSource layer1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(70 - yDown - offset), -1);
            SurfaceRules.ConditionSource layer2 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(70 + extra + layerThickness - yDown - offset), 0);
            ruleSources[i - 1] = SurfaceRules.ifTrue(layer1, SurfaceRules.ifTrue(SurfaceRules.not(layer2), vanil));
        }
        return SurfaceRules.sequence(ruleSources);
    }
    private static SurfaceRules.RuleSource createChocolateCreamBands(int layers, int layerThickness, int layerDistance, boolean bisc, int offset) {
        layerDistance = 20;
        SurfaceRules.RuleSource choco = bisc ? SurfaceRules.state(LCblockRegistry.BISCUIT_CH_CREAM_BLOCK.get().defaultBlockState()) : SurfaceRules.state(LCblockRegistry.CHOCOLATE_CREAM_BLOCK.get().defaultBlockState()) ;
        SurfaceRules.RuleSource[] ruleSources = new SurfaceRules.RuleSource[layers];
        for (int i = 1; i <= layers; i++) {
            int yDown = i * layerDistance;
            int extra = i % 3 == 0 ? 1 : 0;
            SurfaceRules.ConditionSource layer1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(58 - yDown-offset), -1);
            SurfaceRules.ConditionSource layer2 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(58 + extra + layerThickness - yDown-offset), 0);
            ruleSources[i - 1] = SurfaceRules.ifTrue(layer1, SurfaceRules.ifTrue(SurfaceRules.not(layer2), choco));
        }
        return SurfaceRules.sequence(ruleSources);
    }
    private static SurfaceRules.RuleSource createDoughBands(int layers, int layerThickness, int layerDistance) {
        SurfaceRules.RuleSource dough = SurfaceRules.state(LCblockRegistry.CHOCOLATE_DOUGH_BLOCK.get().defaultBlockState());
        SurfaceRules.RuleSource[] ruleSources = new SurfaceRules.RuleSource[layers];
        for (int i = 1; i <= layers; i++) {
            int yDown = i * layerDistance;
            int extra = i % 3 == 0 ? 1 : 0;
            SurfaceRules.ConditionSource layer1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62 - yDown), -1);
            SurfaceRules.ConditionSource layer2 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62 + extra + layerThickness - yDown), 0);
            ruleSources[i - 1] = SurfaceRules.ifTrue(layer1, SurfaceRules.ifTrue(SurfaceRules.not(layer2), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.7D, 0.8D), dough)));
        }
        return SurfaceRules.sequence(ruleSources);
    }
    private static SurfaceRules.RuleSource bedrock() {
        SurfaceRules.RuleSource bedrock = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());
        SurfaceRules.ConditionSource bedrockCondition = SurfaceRules.verticalGradient("bedrock", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5));
        return SurfaceRules.ifTrue(bedrockCondition, bedrock);
    }
    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}

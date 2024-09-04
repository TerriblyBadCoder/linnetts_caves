package com.linnettmodia.linnetts_caves.worldgen.structures;
import com.linnettmodia.linnetts_caves.Linnetts_caves;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LCstructureRegistry {
    public static final DeferredRegister<StructureType<?>> DEF_REG = DeferredRegister.create(Registries.STRUCTURE_TYPE, Linnetts_caves.MODID);
    public static final RegistryObject<StructureType<DessertLairStructure>> DESSERT_LAIR = DEF_REG.register("dessert_lair", () -> () -> DessertLairStructure.CODEC);
}

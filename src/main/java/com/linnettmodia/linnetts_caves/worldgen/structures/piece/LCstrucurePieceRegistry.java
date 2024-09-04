package com.linnettmodia.linnetts_caves.worldgen.structures.piece;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LCstrucurePieceRegistry {
    public static final DeferredRegister<StructurePieceType> DEF_REG = DeferredRegister.create(Registries.STRUCTURE_PIECE, Linnetts_caves.MODID);
    public static final RegistryObject<StructurePieceType> DESSERT_LAIR = DEF_REG.register("forlorn_canyon", () -> DessertLairStructurePiece::new);
}

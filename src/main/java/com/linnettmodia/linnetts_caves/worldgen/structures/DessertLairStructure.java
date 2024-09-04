package com.linnettmodia.linnetts_caves.worldgen.structures;
import com.linnettmodia.linnetts_caves.worldgen.biome.LCbiomesRegistry;
import com.linnettmodia.linnetts_caves.worldgen.structures.piece.DessertLairStructurePiece;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
public class DessertLairStructure extends AbstractCaveGenerationStructure{
    private static final int BOWL_WIDTH_RADIUS = 100;
    private static final int BOWL_HEIGHT_RADIUS = 60;
    public static final int BOWL_Y_CENTER = -10;

    public static final Codec<DessertLairStructure> CODEC = simpleCodec((settings) -> new DessertLairStructure(settings));

    public DessertLairStructure(StructureSettings settings) {
        super(settings, LCbiomesRegistry.DESSERT_LAIR);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new DessertLairStructurePiece(offset, center, heightBlocks, widthBlocks);
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return BOWL_Y_CENTER;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 100;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 90;
    }

    @Override
    public StructureType<?> type() {
        return  LCstructureRegistry.DESSERT_LAIR.get();
    }
}

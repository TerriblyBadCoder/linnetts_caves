package com.linnettmodia.linnetts_caves.blocks.custom;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreamedBiscuitBlock extends Block {
    public CreamedBiscuitBlock(Properties p_49795_) {
        super(p_49795_);
    }



    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
        p_60567_.scheduleTick(p_60568_,this,20);

    }

    public BlockState updateShape(BlockState p_53276_, Direction p_53277_, BlockState p_53278_, LevelAccessor p_53279_, BlockPos p_53280_, BlockPos p_53281_) {
        if (p_53277_ == Direction.UP) {
            p_53279_.scheduleTick(p_53280_, this, 20);
        }

        return super.updateShape(p_53276_, p_53277_, p_53278_, p_53279_, p_53280_, p_53281_);
    }
    private static boolean canFurtherSurvive(BlockState p_56824_, LevelReader p_56825_, BlockPos p_56826_) {
        BlockPos blockpos = p_56826_.above();
        BlockState blockstate = p_56825_.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && (Integer)blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(p_56825_, p_56824_, p_56826_, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(p_56825_, blockpos));
            return i < p_56825_.getMaxLightLevel();
        }
    }
    public boolean canSurvive(BlockState p_53272_, LevelReader p_53273_, BlockPos p_53274_) {
        BlockState blockstate = p_53273_.getBlockState(p_53274_.above());
        return p_53272_.getBlock() == LCblockRegistry.CHOCOLATE_BISCUIT_BLOCK.get() || !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock ||  blockstate.getBlock() instanceof HalfTransparentBlock || canFurtherSurvive(p_53272_,p_53273_,p_53274_);
    }
    @Override
    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
        Block[] TOPSTATELIST = new Block[]{LCblockRegistry.CHOCOLATE_CREAM_BLOCK.get(), LCblockRegistry.VANILLA_CREAM_BLOCK.get(), LCblockRegistry.STRAWBERRY_CREAM_BLOCK.get()};
        Block[] BOTTOMSTATELIST = new Block[]{LCblockRegistry.BISCUIT_CH_CREAM_BLOCK.get(), LCblockRegistry.BISCUIT_VA_CREAM_BLOCK.get(), LCblockRegistry.BISCUIT_ST_CREAM_BLOCK.get()};
        if (!p_222945_.canSurvive(p_222946_, p_222947_) && !Arrays.asList(TOPSTATELIST).contains(p_222946_.getBlockState(p_222947_.above()).getBlock())) {
           p_222946_.setBlockAndUpdate(p_222947_,LCblockRegistry.CHOCOLATE_BISCUIT_BLOCK.get().defaultBlockState());
        }
        else if(Arrays.asList(TOPSTATELIST).contains(p_222946_.getBlockState(p_222947_.above()).getBlock()))
        {
            int index = Arrays.asList(TOPSTATELIST).indexOf(p_222946_.getBlockState(p_222947_.above()).getBlock());
            if(BOTTOMSTATELIST[index] != p_222945_.getBlock())
                p_222946_.setBlockAndUpdate(p_222947_,BOTTOMSTATELIST[index].defaultBlockState());
        }
    }
}

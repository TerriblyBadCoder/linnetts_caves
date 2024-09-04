package com.linnettmodia.linnetts_caves.blocks.custom;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FizzyStickBlock extends RodBlock {

    public FizzyStickBlock(Properties p_154339_) {
        super(p_154339_);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.UP))));
    }
    public BlockState getStateForPlacement(BlockPlaceContext p_153711_) {

        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, p_153711_.getClickedFace()));
    }

    @Override
    public void onProjectileHit(Level p_60453_, BlockState p_60454_, BlockHitResult p_60455_, Projectile p_60456_) {
        super.onProjectileHit(p_60453_, p_60454_, p_60455_, p_60456_);
        p_60453_.destroyBlock(p_60455_.getBlockPos(),false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153746_) {
        p_153746_.add(new Property[]{FACING});
    }
}

package com.linnettmodia.linnetts_caves.blocks.custom;

import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CherryBombBlock extends Block {
    protected static final VoxelShape AABB;
    public CherryBombBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos pos, BlockState p_152433_, Entity p_152434_) {
        super.stepOn(p_152431_, pos, p_152433_, p_152434_);
        Vec3 vec3pos = pos.getCenter();
        if(p_152431_ instanceof ServerLevel serverLevel)
        {
            p_152431_.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            p_152431_.explode(null,p_152431_.damageSources().explosion(null),new ExplosionDamageCalculator(),vec3pos.x,vec3pos.y,vec3pos.z,1,false, Level.ExplosionInteraction.NONE);
            serverLevel.sendParticles(LCparticleRegistry.VOLATILE_PARTICLES.get(), vec3pos.x,vec3pos.y,vec3pos.z,1,0,0,0,0);
            serverLevel.sendParticles(ParticleTypes.SMOKE, vec3pos.x,vec3pos.y,vec3pos.z,3,0.3,0.3,0.3,0);
            serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, vec3pos.x,vec3pos.y,vec3pos.z,2,0.2,0.2,0.2,0);
        }

    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return AABB;
    }

    static {
        AABB = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    }
}

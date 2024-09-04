package com.linnettmodia.linnetts_caves.blocks.custom;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.linnettmodia.linnetts_caves.entity.LCentityRegistry;
import com.linnettmodia.linnetts_caves.entity.custom.FizzyShardEntity;
import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FizzyStoneBlock extends FizzyStickBlock implements Fallable {

    protected static final VoxelShape Y_AXIS_AABB = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(3.0, 3.0, 0.0, 13.0, 13.0, 16.0);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 3.0, 3.0, 16.0, 13.0, 13.0);
    public FizzyStoneBlock(Properties p_154339_) {
        super(p_154339_);
    }
    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        switch (((Direction)p_154346_.getValue(FACING)).getAxis()) {
            case X:
            default:
                return X_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
        }
    }

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
        if(p_220830_.nextDouble()>0.3)
        {
            AABB shape = p_220827_.getShape(p_220828_,p_220829_).bounds();
            Vec3 pos = new Vec3(shape.minX+p_220829_.getX(),shape.minY+p_220829_.getY(),shape.minZ+p_220829_.getZ());
            p_220828_.addParticle(LCparticleRegistry.FIZZYSHINE_PARTICLES.get(),pos.x()+p_220830_.nextFloat()*shape.getXsize(),pos.y()+p_220830_.nextFloat()*shape.getYsize(),pos.z()+p_220830_.nextFloat()*shape.getZsize(),0,0,0);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState p_60543_, LevelAccessor level, BlockPos p_60545_, BlockPos p_60546_) {
        if(!findSupport(p_60545_,state,state.getValue(FACING).getNormal(),level) && !findSupport(p_60545_,state,state.getValue(FACING).getNormal().multiply(-1),level) && level.getBlockState(p_60545_.below()).isAir())
        {
            level.scheduleTick(p_60545_,state.getBlock(),2);
        }
        return super.updateShape(state, dir, p_60543_, level, p_60545_, p_60546_);
    }

    @Override
    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(p_222946_,p_222947_,p_222945_);
        p_222946_.setBlockAndUpdate(p_222947_, Blocks.AIR.defaultBlockState());
        p_222946_.addFreshEntity(fallingBlockEntity);
    }

    public boolean findSupport(BlockPos pos, BlockState state, Vec3i dir, LevelAccessor accessor)
    {
        BlockPos otherPos = new BlockPos(0,-65,0);
        for(int i = 1; i < 10; i++)
        {
            if((accessor.getBlockState(pos.offset(dir.multiply(i))).getBlock() instanceof FizzyStoneBlock) && dir.getY() == -1)
            {
                otherPos = pos.offset(dir.multiply(i));
            }
            if(!(accessor.getBlockState(pos.offset(dir.multiply(i))).getBlock() instanceof FizzyStoneBlock) && !accessor.getBlockState(pos.offset(dir.multiply(i))).isAir())
            {

                return true;
            }
            if(!accessor.getBlockState(pos.offset(dir.multiply(i))).isSolid())
            {
                if(!otherPos.equals(new BlockPos(0, -65, 0))&& accessor.getBlockState(otherPos).getValue(FACING).getAxis() == Direction.Axis.Y && !findSupport(otherPos,accessor.getBlockState(otherPos),new Vec3i(0,1,0),accessor))
                {
                    accessor.scheduleTick(otherPos,accessor.getBlockState(otherPos).getBlock(),2);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void onLand(Level p_153220_, BlockPos p_153221_, BlockState p_153222_, BlockState p_153223_, FallingBlockEntity p_153224_) {
        if(p_153220_ instanceof ServerLevel serverLevel)
        {
            Block[] blocks = new Block[]{LCblockRegistry.BLUE_FIZZY_BLOCK.get(),LCblockRegistry.GREEN_FIZZY_BLOCK.get(),LCblockRegistry.RED_FIZZY_BLOCK.get(),LCblockRegistry.YELLOW_FIZZY_BLOCK.get()};
            Vec3 center = p_153221_.getCenter();
            serverLevel.sendParticles(LCparticleRegistry.FIZZYSHINE_PARTICLES.get(), center.x,center.y,center.z,7,0.4,0.4,0.4,0.8);
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK,p_153222_), center.x,center.y,center.z,5,0.3,0.3,0.3,1.2);
            serverLevel.playSound(null,p_153221_, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS);
            for(int i = 0; i < 5; i ++)
            {

                Vec3 dir = new Vec3(1,0,0).yRot((float) (Math.random()*3.14f*2));
                FizzyShardEntity fizzyShardEntity = new FizzyShardEntity(LCentityRegistry.FIZZY_SHARD.get(),p_153220_);
                fizzyShardEntity.setPos(p_153221_.getCenter().add(0,0.5,0));
                fizzyShardEntity.setVisualType(Arrays.asList(blocks).indexOf(p_153222_.getBlock()));
                fizzyShardEntity.shoot(dir.x,Math.random()+1,dir.z, (float) (0.5f+Math.random()/2),0.1f);
                fizzyShardEntity.setItem(Items.APPLE.getDefaultInstance());
                serverLevel.addFreshEntity(fizzyShardEntity);
            }
        }
        p_153224_.discard();
        p_153220_.setBlockAndUpdate(p_153221_,Blocks.AIR.defaultBlockState());
        Fallable.super.onLand(p_153220_, p_153221_, p_153222_, p_153223_, p_153224_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153746_) {
        p_153746_.add(new Property[]{FACING});
    }
}

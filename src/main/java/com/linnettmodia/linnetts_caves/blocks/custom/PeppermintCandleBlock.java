package com.linnettmodia.linnetts_caves.blocks.custom;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.ToIntFunction;

public class PeppermintCandleBlock extends Block implements SimpleWaterloggedBlock{
    public static final BooleanProperty LIT;
    protected static final VoxelShape AABB;
    public static final BooleanProperty WATERLOGGED;
    public static final ToIntFunction<BlockState> LIGHT_EMISSION;
    public PeppermintCandleBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any())).setValue(LIT, false)).setValue(WATERLOGGED, false));
    }


    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return AABB;
    }

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
        if ((Boolean)p_220827_.getValue(LIT)) {
            addParticlesAndSound(p_220828_, new Vec3((double)p_220829_.getX(), (double)p_220829_.getY()+0.6, (double)p_220829_.getZ()).add(0.5,0.5,0.5), p_220830_);

        }
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152840_) {
        p_152840_.add(LIT, WATERLOGGED);
    }

    private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_) {
        float $$3 = p_220690_.nextFloat();
        if ($$3 < 0.4F) {
            p_220688_.addParticle(ParticleTypes.LARGE_SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, 0.0, 0.0, 0.0);
            if ($$3 < 0.17F) {
                p_220688_.playLocalSound(p_220689_.x + 0.5, p_220689_.y + 0.5, p_220689_.z + 0.5, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
            }
        }

        p_220688_.addParticle(ParticleTypes.FLAME, p_220689_.x, p_220689_.y, p_220689_.z, 0.0, 0.0, 0.0);
    }
    protected boolean canBeLit(BlockState p_151935_) {
        return !(Boolean)p_151935_.getValue(LIT);
    }
    public boolean placeLiquid(LevelAccessor p_152805_, BlockPos p_152806_, BlockState p_152807_, FluidState p_152808_) {
        if (!(Boolean)p_152807_.getValue(WATERLOGGED) && p_152808_.getType() == Fluids.WATER) {
            BlockState $$4 = (BlockState)p_152807_.setValue(WATERLOGGED, true);
            if ((Boolean)p_152807_.getValue(LIT)) {
                extinguish((Player)null, $$4, p_152805_, p_152806_);
            } else {
                p_152805_.setBlock(p_152806_, $$4, 3);
            }

            p_152805_.scheduleTick(p_152806_, p_152808_.getType(), p_152808_.getType().getTickDelay(p_152805_));
            return true;
        } else {
            return false;
        }
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if (player.getAbilities().mayBuild && player.getItemInHand(p_60507_).isEmpty() && (Boolean)state.getValue(LIT)) {
            extinguish(player, state, level, pos);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else if(player.getAbilities().mayBuild && player.getItemInHand(p_60507_).getItem() instanceof FlintAndSteelItem && !(Boolean)state.getValue(LIT)){
            setLit(level,state,pos,true);
            player.swing(p_60507_);
            player.getItemInHand(p_60507_).hurtAndBreak(1,player,(p_43296_) -> {
                p_43296_.broadcastBreakEvent(p_43296_.getUsedItemHand());
            });
        }
        return InteractionResult.PASS;

    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return super.getStateForPlacement(p_49820_).setValue(WATERLOGGED, p_49820_.getLevel().getFluidState(p_49820_.getClickedPos()).getType() == Fluids.WATER);
    }
    public BlockState updateShape(BlockState p_153483_, Direction p_153484_, BlockState p_153485_, LevelAccessor p_153486_, BlockPos p_153487_, BlockPos p_153488_) {
        if ((Boolean)p_153483_.getValue(WATERLOGGED)) {
            p_153486_.scheduleTick(p_153487_, Fluids.WATER, Fluids.WATER.getTickDelay(p_153486_));
        }

        return super.updateShape(p_153483_,p_153484_,p_153485_,p_153486_,p_153487_,p_153488_);
    }
    public static void extinguish(@Nullable Player p_151900_, BlockState p_151901_, LevelAccessor p_151902_, BlockPos p_151903_) {
        setLit(p_151902_, p_151901_, p_151903_, false);
        Vec3 pos = p_151903_.getCenter();
        for(int i = 0; i <3;i++)
            p_151902_.addParticle(ParticleTypes.SMOKE,pos.x+Math.random()/5-0.2,pos.y + 0.5, pos.z+Math.random()/5-0.2,0,0.2,0);
        p_151902_.playSound((Player)null, p_151903_, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        p_151902_.gameEvent(p_151900_, GameEvent.BLOCK_CHANGE, p_151903_);
    }

    private static void setLit(LevelAccessor p_151919_, BlockState p_151920_, BlockPos p_151921_, boolean p_151922_) {
        p_151919_.setBlock(p_151921_, (BlockState)p_151920_.setValue(LIT, p_151922_), 11);
    }
    public FluidState getFluidState(BlockState p_153492_) {
        return (Boolean)p_153492_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_153492_);
    }
    public void onProjectileHit(Level p_151905_, BlockState p_151906_, BlockHitResult p_151907_, Projectile p_151908_) {
        if (!p_151905_.isClientSide && p_151908_.isOnFire() && this.canBeLit(p_151906_)) {
            setLit(p_151905_, p_151906_, p_151907_.getBlockPos(), true);
        }

    }
    static {
        LIT = AbstractCandleBlock.LIT;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        LIGHT_EMISSION = (p_152848_) -> {
            return (Boolean)p_152848_.getValue(LIT) ? 11 : 0;
        };
        AABB = Block.box(4.0, 0.0, 4.0, 12.0, 18.0, 12.0);
    }
}

package com.linnettmodia.linnetts_caves.blocks.custom;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JellyBlock extends SlimeBlock {
    public JellyBlock(Properties p_56402_) {
        super(p_56402_);
    }
    public void updateEntityAfterFallOn(BlockGetter p_56406_, Entity p_56407_) {
        if (p_56407_.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(p_56406_, p_56407_);
        } else {
            this.bounceUp(p_56407_);
        }

    }

    private void bounceUp(Entity p_56404_) {
        Vec3 $$1 = p_56404_.getDeltaMovement();
        if ($$1.y < 0.0) {
            Vec3 vec3 = p_56404_.getOnPos().getCenter();
            if($$1.y < -0.2)
            {
                BlockState block = p_56404_.level().getBlockState(p_56404_.getOnPos());
                if(block.is(LCblockRegistry.JELLY_BLOCK.get()))
                    p_56404_.level().addParticle(LCparticleRegistry.JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
                else if(block.is(LCblockRegistry.BLUE_JELLY_BLOCK.get()))
                    p_56404_.level().addParticle(LCparticleRegistry.BLUE_JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
                else if(block.is(LCblockRegistry.RED_JELLY_BLOCK.get()))
                    p_56404_.level().addParticle(LCparticleRegistry.RED_JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
            }
            double $$2 = p_56404_ instanceof LivingEntity ? 0.95 : 0.7;
            p_56404_.setDeltaMovement($$1.x, -$$1.y * $$2, $$1.z);
        }

    }

}

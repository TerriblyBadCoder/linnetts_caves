package com.linnettmodia.linnetts_caves.entity.custom;

import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JellyZombie extends Zombie {
    public JellyZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }

    @Override
    protected void handleAttributes(float p_34340_) {
        super.handleAttributes(p_34340_);
    }

    @Override
    protected void checkFallDamage(double p_20990_, boolean p_20991_, BlockState p_20992_, BlockPos p_20993_) {
        super.checkFallDamage(p_20990_, p_20991_, p_20992_, p_20993_);
    }

    @Override
    public void move(MoverType p_19973_, Vec3 p_19974_) {
        Vec3 delta = this.getDeltaMovement();
        float fall = this.fallDistance;
        super.move(p_19973_, p_19974_);
        p_19974_ = this.maybeBackOffFromEdge(p_19974_, p_19973_);
        Vec3 vec3 = this.collide(p_19974_);
        boolean flagx = !Mth.equal(p_19974_.x, vec3.x);
        boolean flagz = !Mth.equal(p_19974_.z, vec3.z);
        if(delta.length() > 0.5 && !noPhysics)
        {
            if(this.verticalCollision)
            {
                this.setOnGround(false);
                this.setDeltaMovement(new Vec3(getDeltaMovement().x,-delta.y*1.02,getDeltaMovement().z));
                if(this.verticalCollisionBelow && level() instanceof ServerLevel serverLevel)
                {
                    serverLevel.sendParticles(LCparticleRegistry.BLUE_JELLYBOUNCE_PARTICLES.get(), position().x,position().y,position().z,1,0,0,0,0);
                }
            }
            if(flagx)
            {
                this.setOnGround(false);
                this.setDeltaMovement(new Vec3(-delta.x,getDeltaMovement().y,getDeltaMovement().z));
            }
            if(flagz)
            {
                this.setOnGround(false);
                this.setDeltaMovement(new Vec3(getDeltaMovement().x,getDeltaMovement().y,-delta.z));
            }
        }
        this.setDeltaMovement(getDeltaMovement().multiply(1.03,1,1.03));
    }
    private Vec3 collide(Vec3 p_20273_) {
        AABB aabb = this.getBoundingBox();
        List<VoxelShape> list = this.level().getEntityCollisions(this, aabb.expandTowards(p_20273_));
        Vec3 vec3 = p_20273_.lengthSqr() == 0.0 ? p_20273_ : collideBoundingBox(this, p_20273_, aabb, this.level(), list);
        boolean flag = p_20273_.x != vec3.x;
        boolean flag1 = p_20273_.y != vec3.y;
        boolean flag2 = p_20273_.z != vec3.z;
        boolean flag3 = this.onGround() || flag1 && p_20273_.y < 0.0;
        float stepHeight = this.getStepHeight();
        if (stepHeight > 0.0F && flag3 && (flag || flag2)) {
            Vec3 vec31 = collideBoundingBox(this, new Vec3(p_20273_.x, (double)stepHeight, p_20273_.z), aabb, this.level(), list);
            Vec3 vec32 = collideBoundingBox(this, new Vec3(0.0, (double)stepHeight, 0.0), aabb.expandTowards(p_20273_.x, 0.0, p_20273_.z), this.level(), list);
            if (vec32.y < (double)stepHeight) {
                Vec3 vec33 = collideBoundingBox(this, new Vec3(p_20273_.x, 0.0, p_20273_.z), aabb.move(vec32), this.level(), list).add(vec32);
                if (vec33.horizontalDistanceSqr() > vec31.horizontalDistanceSqr()) {
                    vec31 = vec33;
                }
            }

            if (vec31.horizontalDistanceSqr() > vec3.horizontalDistanceSqr()) {
                return vec31.add(collideBoundingBox(this, new Vec3(0.0, -vec31.y + p_20273_.y, 0.0), aabb.move(vec31), this.level(), list));
            }
        }

        return vec3;
    }
    @Override
    public void tick() {
        super.tick();

    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return 0;
    }

    @Nullable
    @Override
    public AttributeInstance getAttribute(Attribute p_21052_) {
        return super.getAttribute(p_21052_);
    }
}

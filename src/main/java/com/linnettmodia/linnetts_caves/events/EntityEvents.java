package com.linnettmodia.linnetts_caves.events;

import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import com.linnettmodia.linnetts_caves.blocks.custom.JellyBlock;
import com.linnettmodia.linnetts_caves.particles.LCparticleRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityEvents {
    @SubscribeEvent
    public void JumpEvent(LivingEvent.LivingJumpEvent event)
    {
        if(event.getEntity().getBlockStateOn().getBlock() instanceof JellyBlock)
        {
            BlockState block = event.getEntity().getBlockStateOn();
            event.getEntity().addDeltaMovement(new Vec3(0,0.5,0));
            Vec3 vec3 = event.getEntity().getOnPos().getCenter();
            if(block.is(LCblockRegistry.JELLY_BLOCK.get()))
                event.getEntity().level().addParticle(LCparticleRegistry.JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
            else if(block.is(LCblockRegistry.BLUE_JELLY_BLOCK.get()))
                event.getEntity().level().addParticle(LCparticleRegistry.BLUE_JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
            else if(block.is(LCblockRegistry.RED_JELLY_BLOCK.get()))
                event.getEntity().level().addParticle(LCparticleRegistry.RED_JELLYBOUNCE_PARTICLES.get(),vec3.x,vec3.y+0.4,vec3.z,0,0,0);
        }
    }
}

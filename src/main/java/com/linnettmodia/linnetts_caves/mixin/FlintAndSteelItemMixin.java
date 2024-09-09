package com.linnettmodia.linnetts_caves.mixin;

import com.linnettmodia.linnetts_caves.blocks.custom.PeppermintCandleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void LC_useOn(UseOnContext p_41297_, CallbackInfoReturnable<InteractionResult> cir)
    {
        Level $$2 = p_41297_.getLevel();
        BlockPos $$3 = p_41297_.getClickedPos();
        BlockState $$4 = $$2.getBlockState($$3);
        if($$4.getBlock() instanceof PeppermintCandleBlock)
        {
            cir.setReturnValue(InteractionResult.sidedSuccess($$2.isClientSide));
            cir.cancel();

        }
    }
}


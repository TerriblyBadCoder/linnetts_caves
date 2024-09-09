package com.linnettmodia.linnetts_caves.mixin;

import com.linnettmodia.linnetts_caves.misc.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LightTexture.class, priority = 0)
public class LightTextureMixin {
    @Inject(
            method = {"Lnet/minecraft/client/renderer/LightTexture;getBrightness(Lnet/minecraft/world/level/dimension/DimensionType;I)F"},
            remap = true,
            cancellable = true,
            at = @At(value = "TAIL")
    )
    private static void lc_getBrightness(DimensionType dimensionType, int lightTextureIndex, CallbackInfoReturnable<Float> cir) {
        float f = ClientProxy.lastBiomeAmbientLightAmountPrev + (ClientProxy.lastBiomeAmbientLightAmount - ClientProxy.lastBiomeAmbientLightAmountPrev) * Minecraft.getInstance().getFrameTime();
        float light = f + cir.getReturnValue();
        if (f != 0) {
            cir.setReturnValue(light);
        }
    }
}

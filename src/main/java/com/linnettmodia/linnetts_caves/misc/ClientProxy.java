package com.linnettmodia.linnetts_caves.misc;

import com.linnettmodia.linnetts_caves.events.ClientEvents;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
    public static float lastBiomeAmbientLightAmountPrev = 0;
    public static float lastBiomeAmbientLightAmount = 0;
    @Override
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

}

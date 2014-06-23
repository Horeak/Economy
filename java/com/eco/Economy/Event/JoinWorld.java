package com.eco.Economy.Event;

import com.eco.Economy.Network.*;
import com.eco.Economy.Network.Packets.*;
import cpw.mods.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.event.entity.*;

public class JoinWorld {


    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
            PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(((EntityPlayer) event.entity)), (EntityPlayer) event.entity);
    }
}

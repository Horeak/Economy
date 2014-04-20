package com.eco.Economy.Event;

import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.Packets.SyncPlayerPropsPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class JoinWorld {


    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
            Economy.packetPipeline.sendTo(new SyncPlayerPropsPacket(((EntityPlayer)event.entity)), (EntityPlayerMP) event.entity);
    }
}

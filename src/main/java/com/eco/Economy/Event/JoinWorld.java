package com.eco.Economy.Event;

import MiscUtils.Network.PacketHandler;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.SyncPlayerPropsPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

@SideOnly(Side.SERVER)
public class JoinWorld {


    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity != null && !event.world.isRemote)
        if (event.entity instanceof EntityPlayer)
            PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(((EntityPlayer) event.entity)), (EntityPlayer) event.entity, Economy.Utils.channels);
    }
}

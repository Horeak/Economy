package com.eco.Economy.Event;

import MiscUtils.Network.PacketHandler;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.SyncPlayerPropsPacket;
import com.eco.Economy.Utils.InfoStorage;
import com.eco.Economy.Utils.Proxies.ServerProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class OnPlayerRespawn{

@SubscribeEvent
public void onLivingDeathEvent(LivingDeathEvent event)
        {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {



        NBTTagCompound playerData = new NBTTagCompound();
        ((InfoStorage)(event.entity.getExtendedProperties(InfoStorage.EXT_PROP_NAME))).saveNBTData(playerData);
        Economy.proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);
            ServerProxy.saveProxyData((EntityPlayer) event.entity);
        }



        }

// we already have this event, but we need to modify it some
@SubscribeEvent
public void onEntityJoinWorld(EntityJoinWorldEvent event)
        {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {

        NBTTagCompound playerData = Economy.proxy.getEntityData(((EntityPlayer) event.entity).getDisplayName());
        if (playerData != null) {
        ((InfoStorage)(event.entity.getExtendedProperties(InfoStorage.EXT_PROP_NAME))).loadNBTData(playerData);
        }
            PacketHandler.sendToPlayer(new SyncPlayerPropsPacket((EntityPlayer) event.entity), (EntityPlayer) event.entity, Economy.channels);
        }


        }

}
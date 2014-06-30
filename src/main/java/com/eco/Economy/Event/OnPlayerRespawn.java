package com.eco.Economy.Event;

import com.eco.Economy.Lib.*;
import com.eco.Economy.Main.*;
import com.eco.Economy.Network.*;
import com.eco.Economy.Network.Packets.*;
import com.eco.Economy.Proxies.*;
import cpw.mods.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.living.*;

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
            PacketHandler.sendToPlayer(new SyncPlayerPropsPacket((EntityPlayer) event.entity), (EntityPlayer) event.entity);
        }


        }

}
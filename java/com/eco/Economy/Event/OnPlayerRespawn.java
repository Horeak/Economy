package com.eco.Economy.Event;

import com.eco.Economy.Lib.InfoStorage;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.PacketHandler;
import com.eco.Economy.Network.Packets.SyncPlayerPropsPacket;
import com.eco.Economy.Proxies.ServerProxy;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class OnPlayerRespawn{

@SubscribeEvent
public void onLivingDeathEvent(LivingDeathEvent event)
        {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {

            if(event.entity.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") == false && !event.entity.worldObj.isRemote){

                if(event.entity.worldObj.difficultySetting == EnumDifficulty.HARD){
                    if(event.entity instanceof EntityPlayer){
                        EntityPlayer player = (EntityPlayer)event.entity;
                        InfoStorage.get(player).SetMoney(0);


                    }

                }else if(event.entity.worldObj.difficultySetting == EnumDifficulty.NORMAL){
                    if(event.entity instanceof EntityPlayer){
                        EntityPlayer player = (EntityPlayer)event.entity;

                        if(InfoStorage.get(player).GetMoney() > 0)
                            InfoStorage.get(player).SetMoney(InfoStorage.get(player).GetMoney() - 500);

                    }



                }else if(event.entity.worldObj.difficultySetting == EnumDifficulty.EASY){

                    if(event.entity instanceof EntityPlayer){
                        EntityPlayer player = (EntityPlayer)event.entity;
                        if(InfoStorage.get(player).GetMoney() > 0)
                            InfoStorage.get(player).SetMoney(InfoStorage.get(player).GetMoney() - 100);

                    }




                }else{
                    if(event.entity.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
                        if(event.entity instanceof EntityPlayer){
                            EntityPlayer player = (EntityPlayer)event.entity;
                            if(InfoStorage.get(player).GetMoney() > 0)
                                InfoStorage.get(player).SetMoney(InfoStorage.get(player).GetMoney() - 50);

                        }


                }

            }


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
            PacketHandler.sendToPlayer(Economy.channels, new SyncPlayerPropsPacket((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
        }


        }

}
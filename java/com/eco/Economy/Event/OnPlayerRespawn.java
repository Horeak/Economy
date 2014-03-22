package com.eco.Economy.Event;

import com.eco.Economy.Lib.MoneyStorage;
import com.eco.Economy.Main.Economy;
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
                        MoneyStorage.get(player).SetMoney(0);


                    }

                }else if(event.entity.worldObj.difficultySetting == EnumDifficulty.NORMAL){
                    if(event.entity instanceof EntityPlayer){
                        EntityPlayer player = (EntityPlayer)event.entity;

                        if(MoneyStorage.get(player).GetMoney() > 0)
                            MoneyStorage.get(player).SetMoney(MoneyStorage.get(player).GetMoney() - 500);

                    }



                }else if(event.entity.worldObj.difficultySetting == EnumDifficulty.EASY){

                    if(event.entity instanceof EntityPlayer){
                        EntityPlayer player = (EntityPlayer)event.entity;
                        if(MoneyStorage.get(player).GetMoney() > 0)
                            MoneyStorage.get(player).SetMoney(MoneyStorage.get(player).GetMoney() - 100);

                    }




                }else{
                    if(event.entity.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
                        if(event.entity instanceof EntityPlayer){
                            EntityPlayer player = (EntityPlayer)event.entity;
                            if(MoneyStorage.get(player).GetMoney() > 0)
                                MoneyStorage.get(player).SetMoney(MoneyStorage.get(player).GetMoney() - 50);

                        }


                }

            }


        NBTTagCompound playerData = new NBTTagCompound();
        ((MoneyStorage)(event.entity.getExtendedProperties(MoneyStorage.EXT_PROP_NAME))).saveNBTData(playerData);
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
        ((MoneyStorage)(event.entity.getExtendedProperties(MoneyStorage.EXT_PROP_NAME))).loadNBTData(playerData);
        }
            Economy.packetPipeline.sendTo(new SyncPlayerPropsPacket((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
        }


        }

}
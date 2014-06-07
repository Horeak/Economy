package com.eco.Economy.Proxies;

import com.eco.Economy.Lib.InfoStorage;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.PacketHandler;
import com.eco.Economy.Network.Packets.SyncPlayerPropsPacket;
import com.eco.Economy.Tick.ClientTickHandler;
import com.eco.Economy.Tick.ServerTickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class ServerProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();



    public ServerTickHandler tickHandlerServer;
    public ClientTickHandler tickHandlerClient;


    public void RegisterClientTick(){

    }

    public void RegisterServerTick(){
        tickHandlerServer = new ServerTickHandler();
    }


    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }



    private static final String getSaveKey(EntityPlayer player) {
        return player.getCommandSenderName() + ":" + InfoStorage.EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player) {
        InfoStorage playerData = InfoStorage.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        ServerProxy.storeEntityData(getSaveKey(player), savedData);
    }


    public static final void loadProxyData(EntityPlayer player) {
        InfoStorage playerData = InfoStorage.get(player);
        NBTTagCompound savedData = ServerProxy.getEntityData(getSaveKey(player));
        if (savedData != null) { playerData.loadNBTData(savedData); }

        PacketHandler.sendToPlayer(Economy.channels, new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
    }


    public void RegisterRenders(){


    }


}

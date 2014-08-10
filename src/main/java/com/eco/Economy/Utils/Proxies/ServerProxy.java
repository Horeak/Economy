package com.eco.Economy.Utils.Proxies;

import MiscUtils.Network.PacketHandler;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.SyncPlayerPropsPacket;
import com.eco.Economy.Utils.InfoStorage;
import com.eco.Economy.Utils.Tickhandler.ClientTickHandler;
import com.eco.Economy.Utils.Tickhandler.ServerTickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;
import java.util.Map;

public class ServerProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();



    public ServerTickHandler tickHandlerServer;
    public ClientTickHandler tickHandlerClient;


    public void RegisterClientTick(){

    }

    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {

    }

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, Item itemID, int metaData, int stackSize, int color) {

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

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player, Economy.channels);
    }


    public void RegisterRenders(){


    }


}

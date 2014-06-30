package com.eco.Economy.Network;


import com.eco.Economy.Main.*;
import com.eco.Economy.Network.Packets.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;

public class PacketHandler {



    public static void RegisterPackets(){


        Economy.handler.RegisterPacket(1, SyncPlayerPropsPacket.class);
        Economy.handler.RegisterPacket(2, SyncSafeOwnerPacket.class);
        Economy.handler.RegisterPacket(3, AtmFinishPacket.class);

        Economy.handler.RegisterPacket(4, PacketTileUpdate.class);
        Economy.handler.RegisterPacket(5, PacketTileWithItemUpdate.class);



    }

    public static Packet GetPacket(AbstractPacket packet){
        return Economy.channels.get(Side.SERVER).generatePacketFrom(packet);
    }

    public static void sendToAll(AbstractPacket packet)
    {
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        Economy.channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public static void sendToPlayer(AbstractPacket packet, EntityPlayer player)
    {
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        Economy.channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public static void sendToAllAround(AbstractPacket packet, NetworkRegistry.TargetPoint point)
    {
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        Economy.channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public static void sendToDimension(AbstractPacket packet, int dimension)
    {
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        Economy.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimension);
        Economy.channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public static void sendToServer(AbstractPacket packet)
    {
        Economy.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        Economy.channels.get(Side.CLIENT).writeAndFlush(packet);
    }

    public static void sendToAllExcept(AbstractPacket packet, EntityPlayer player)
    {
        for(int i = 0; i < FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.size(); i++)
        {
            EntityPlayer player1 = (EntityPlayer)FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.get(i);

            if(player.getCommandSenderName().equalsIgnoreCase(player1.getCommandSenderName()))
            {
                continue;
            }

            PacketHandler.sendToPlayer(packet, player1);
        }
    }




}

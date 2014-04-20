package com.eco.Economy.Network.Simple;


import com.eco.Economy.Lib.ModInfo;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

import java.util.EnumMap;

public class NetworkManager {
	
	/**
	 * @author ProPercivalalb <https://github.com/ProPercivalalb/LaserMod>
	 */

    public final ChannelHandler channelHandler;
    private final FMLEmbeddedChannel clientOutboundChannel;
    private final FMLEmbeddedChannel serverOutboundChannel;
    
    public NetworkManager() {
        this.channelHandler = new ChannelHandler();
        
        EnumMap<Side, FMLEmbeddedChannel> channelPair = NetworkRegistry.INSTANCE.newChannel(ModInfo.Channel, channelHandler);
        this.clientOutboundChannel = channelPair.get(Side.CLIENT);
        this.serverOutboundChannel = channelPair.get(Side.SERVER);
    }
    
    public void sendPacketToServer(IPacket packet) {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            this.clientOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
            this.clientOutboundChannel.writeOutbound(packet);
        }
    }
    
    public void sendPacketToPlayer(IPacket packet, EntityPlayer player) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
            this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
            this.serverOutboundChannel.writeOutbound(packet);
        }
    }
    
    public void sendPacketToAllAround(IPacket packet, int dimensionId, double x, double y, double z, double range) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
            this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(dimensionId, x, y, z, range));
            this.serverOutboundChannel.writeOutbound(packet);
        }
    }


    public void sendPacketToAll(IPacket message) {
        this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        this.serverOutboundChannel.writeAndFlush(message);
    }


    public void sendPacketToDimension(IPacket message, int dimensionId) {
        this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.serverOutboundChannel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        this.serverOutboundChannel.writeAndFlush(message);
    }


    public static Packet populatePacket(IPacket ModPacket) {
        byte[] data = ModPacket.populate();
        S3FPacketCustomPayload packet250 = new S3FPacketCustomPayload(ModInfo.Channel, data);

        return packet250;
    }



    
    
    
}
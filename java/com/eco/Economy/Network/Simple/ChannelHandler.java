package com.eco.Economy.Network.Simple;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IPacket>{

	
	/**
	 * @author ProPercivalalb <https://github.com/ProPercivalalb/LaserMod>
	 */
	
    public ChannelHandler() {
        for (int i = 0; i < PacketType.values().length; i++)
            addDiscriminator(i, PacketType.values()[i].packetClass);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, IPacket msg, ByteBuf bytes) throws Exception {
     ByteArrayOutputStream bos = new ByteArrayOutputStream();
     DataOutputStream dos = new DataOutputStream(bos);
    
     if (FMLCommonHandler.instance().getEffectiveSide().isClient())
         dos.writeUTF(Minecraft.getMinecraft().theWorld.getPlayerEntityByName(Minecraft.getMinecraft().thePlayer.getCommandSenderName()).getCommandSenderName());

     msg.write(dos);
     bytes.writeBytes(bos.toByteArray());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf bytes, IPacket msg) {
try {
byte[] data = new byte[bytes.capacity()];
for(int i = 0; i < data.length; ++i)
data[i] = bytes.readByte();

ByteArrayInputStream bis = new ByteArrayInputStream(data);
DataInputStream dis = new DataInputStream(bis);

EntityPlayer player;

if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
	player = (EntityPlayer)Minecraft.getMinecraft().theWorld.getPlayerEntityByName(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
}else{

player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(dis.readUTF());

}

msg.read(dis);
msg.execute((EntityPlayer)player);
}
     catch(Exception e) {
e.printStackTrace();
}
    }
}
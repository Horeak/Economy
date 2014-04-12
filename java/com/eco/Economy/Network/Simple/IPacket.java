package com.eco.Economy.Network.Simple;

import com.eco.Economy.Lib.ModInfo;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class IPacket {

	/**
	 * @author ProPercivalalb <https://github.com/ProPercivalalb/LaserMod>
	 */
	
	public abstract void read(DataInputStream data) throws IOException;
public abstract void write(DataOutputStream data) throws IOException;

public abstract void execute(EntityPlayer player);

public Packet getPacket() {
if(FMLCommonHandler.instance().getSide().isClient())
return new C17PacketCustomPayload(ModInfo.Channel, this.getPacketBytes(true));
else
return new S3FPacketCustomPayload(ModInfo.Channel, this.getPacketBytes(false));
}

private byte[] getPacketBytes(boolean isClient) {
try {
ByteArrayOutputStream bos = new ByteArrayOutputStream();
DataOutputStream dos = new DataOutputStream(bos);
dos.writeByte(PacketType.getIdFromClass(this.getClass()));
if(isClient)
dos.writeUTF(FMLClientHandler.instance().getClientPlayerEntity().getCommandSenderName());
this.write(dos);
return bos.toByteArray();
}
catch(Exception e) {
e.printStackTrace();
}

return null;
}

public byte[] populate() {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(bos);

    try {
        dos.writeByte(PacketType.getIdFromClass(this.getClass()));
        this.write(dos);
    }
    catch (IOException e) {
        e.printStackTrace(System.err);
    }

    return bos.toByteArray();
}
}
package com.eco.Economy.Network.Simple;

import com.eco.Economy.Network.Simple.Packets.SyncSafeOwnerPacket;

public enum PacketType {
	
	/**
	 * @author ProPercivalalb <https://github.com/ProPercivalalb/LaserMod>
	 */


   SafeSync(SyncSafeOwnerPacket.class);


    public Class<? extends IPacket> packetClass;

PacketType(Class<? extends IPacket> packetClass) {
this.packetClass = packetClass;
}

public static byte getIdFromClass(Class<? extends IPacket> packetClass) {
for(PacketType type : values())
if(type.packetClass == packetClass)
return (byte)type.ordinal();
return -1;
}
}
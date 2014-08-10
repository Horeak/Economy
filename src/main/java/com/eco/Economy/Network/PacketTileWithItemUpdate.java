package com.eco.Economy.Network;

import MiscUtils.Network.AbstractPacket;
import com.eco.Economy.Main.Economy;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;

public class PacketTileWithItemUpdate extends AbstractPacket {

    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public int itemID, metaData, stackSize, color;

    public PacketTileWithItemUpdate() {}

    public PacketTileWithItemUpdate(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.customName = customName;
        this.itemID = itemID;
        this.metaData = metaData;
        this.stackSize = stackSize;
        this.color = color;
    }

    @Override
    public void toBytes(ByteBuf buf, Side side) {

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
//        buf.writeBoolean(customName != null);
//        ByteBufUtils.writeUTF8String(buf, customName);
        buf.writeInt(itemID);
        buf.writeInt(metaData);
        buf.writeInt(stackSize);
        buf.writeInt(color);
    }

    @Override
    public void fromBytes(ByteBuf buf, Side side) {

        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        orientation = buf.readByte();
        state = buf.readByte();
//        if(buf.readBoolean())
//        customName = ByteBufUtils.readUTF8String(buf);
        itemID = buf.readInt();
        metaData = buf.readInt();
        stackSize = buf.readInt();
        color = buf.readInt();
    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {

        Economy.proxy.handleTileWithItemPacket(x, y, z, ForgeDirection.getOrientation(orientation), state, customName, Item.getItemById(itemID), metaData, stackSize, color);
    }


}
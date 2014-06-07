package com.eco.Economy.Network.Packets;

import com.eco.Economy.Lib.InfoStorage;
import com.eco.Economy.Network.AbstractPacket;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsPacket extends AbstractPacket
{


    private NBTTagCompound data;

    public SyncPlayerPropsPacket() {}

    public SyncPlayerPropsPacket(EntityPlayer player) {
        data = new NBTTagCompound();
        InfoStorage.get(player).saveNBTData(data);
    }



    @Override
    public void writeTo(ByteBuf data, Side side) {
        ByteBufUtils.writeTag(data, this.data);
    }

    @Override
    public void readFrom(ByteBuf data, Side side) {
        this.data = ByteBufUtils.readTag(data);
    }

    @Override
    public void execute(Side side, EntityPlayer player) {


        if(side == Side.CLIENT){
            InfoStorage.get(player).loadNBTData(data);
        }
    }
}
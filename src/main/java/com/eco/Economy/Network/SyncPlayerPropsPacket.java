package com.eco.Economy.Network;

import MiscUtils.Network.AbstractPacket;
import com.eco.Economy.Utils.InfoStorage;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
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
    public void toBytes(ByteBuf data, Side side) {
        ByteBufUtils.writeTag(data, this.data);
    }

    @Override
    public void fromBytes(ByteBuf data, Side side) {
        this.data = ByteBufUtils.readTag(data);
    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {
            InfoStorage.get(player).loadNBTData(data);

    }
}
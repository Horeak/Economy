package com.eco.Economy.Network.Packets;

import com.eco.Economy.Lib.*;
import com.eco.Economy.Network.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import io.netty.buffer.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;

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
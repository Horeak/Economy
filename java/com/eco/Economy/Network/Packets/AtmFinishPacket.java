package com.eco.Economy.Network.Packets;

import com.eco.Economy.Lib.*;
import com.eco.Economy.Network.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import io.netty.buffer.*;
import net.minecraft.entity.player.*;

public class AtmFinishPacket extends AbstractPacket {

    int amount;
    EntityPlayer player;

    public AtmFinishPacket(){}
    public AtmFinishPacket(int Amount, EntityPlayer player){
        this.amount = Amount;
        this.player = player;
    }

    @Override
    public void toBytes(ByteBuf buffer, Side side) {
        buffer.writeInt(amount);
        ByteBufUtils.writeUTF8String(buffer, player.getCommandSenderName());
    }

    @Override
    public void fromBytes(ByteBuf buffer, Side side) {
        amount = buffer.readInt();
        player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(ByteBufUtils.readUTF8String(buffer));

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {

        if(side == Side.SERVER){
            if(player != null){
                MoneyUtils.AddMoneyToPlayerInv(player, amount);
                InfoStorage.get(player).RemoveMoney(amount);

            }

        }
    }
}

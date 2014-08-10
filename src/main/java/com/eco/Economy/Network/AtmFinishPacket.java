package com.eco.Economy.Network;

import MiscUtils.Network.AbstractPacket;
import com.eco.Economy.Utils.InfoStorage;
import com.eco.Economy.Utils.MoneyUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

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
        player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(ByteBufUtils.readUTF8String(buffer));

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

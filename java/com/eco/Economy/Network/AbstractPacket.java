package com.eco.Economy.Network;


import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket
{
    /**
     * Write packet info in this function
     */
    public abstract void writeTo(ByteBuf data, Side side);

    /**
     * Read packet info in this function. Execution is done elsewhere.
     */
    public abstract void readFrom(ByteBuf data, Side side);

    /**
     * Execute your packet here... I think.
     */
    public abstract void execute(Side side, EntityPlayer player);
}
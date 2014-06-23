package com.eco.Economy.Network;

import com.miscitems.MiscItemsAndBlocks.Utils.Handlers.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import io.netty.buffer.*;
import io.netty.channel.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<AbstractPacket>
{
    public final String channel;

    public ChannelHandler(String s)
    {
        channel = s;

    }


    public void RegisterPacket(int Number, Class<? extends AbstractPacket> c){
        addDiscriminator(Number, c);
    }


    @Override
    public void encodeInto(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf target) throws Exception
    {
        LogHandler.Debug("Writing packet!!!", 2);
                 msg.toBytes(target, ctx.channel().attr(NetworkRegistry.CHANNEL_SOURCE).get());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, AbstractPacket msg)
    {

        LogHandler.Debug("Reading packet!!!", 2);
                msg.fromBytes(source, ctx.channel().attr(NetworkRegistry.CHANNEL_SOURCE).get());
    }


    @Sharable
    public static class PacketExecuter extends SimpleChannelInboundHandler<AbstractPacket>
    {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket msg) throws Exception
        {
            Side side = ctx.channel().attr(NetworkRegistry.CHANNEL_SOURCE).get();
            EntityPlayer player = null;
            if(side.isServer())
            {
                INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer) netHandler).playerEntity;
            }
            else
            {
                player = this.getClientPlayer();
            }

            msg.onMessage(side, player);
        }

        @SideOnly(Side.CLIENT)
        public EntityPlayer getClientPlayer()
        {
            return Minecraft.getMinecraft().thePlayer;
        }
        }

}

package com.eco.Economy.Network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

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

                 msg.toBytes(target, ctx.channel().attr(NetworkRegistry.CHANNEL_SOURCE).get());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, AbstractPacket msg)
    {

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

package com.eco.Economy.Network;

import cpw.mods.fml.relauncher.*;
import io.netty.buffer.*;
import net.minecraft.entity.player.*;

public abstract class AbstractPacket
{

    public abstract void toBytes(ByteBuf buffer, Side side);


    public abstract void fromBytes(ByteBuf buffer, Side side);


    public abstract void onMessage(Side side, EntityPlayer player);


}
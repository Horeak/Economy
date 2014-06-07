package com.eco.Economy.Network.Packets;

import com.eco.Economy.Network.AbstractPacket;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SyncSafeOwnerPacket extends AbstractPacket {



    int x,y,z;
    String Owner;

    public SyncSafeOwnerPacket(){}

    public SyncSafeOwnerPacket(int x, int y, int z, String Owner){
        this.x = x;
        this.y = y;
        this.z = z;

        this.Owner = Owner;


    }





    @Override
    public void writeTo(ByteBuf data, Side side) {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);

        ByteBufUtils.writeUTF8String(data, Owner);
    }

    @Override
    public void readFrom(ByteBuf data, Side side) {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();

        if(Owner != null && Owner != "")
            Owner = ByteBufUtils.readUTF8String(data);

    }

    @Override
    public void execute(Side side, EntityPlayer player) {

        World world = player.getEntityWorld();


        if(world.getTileEntity(x, y, z) instanceof TileEntitySafe){
            TileEntitySafe tile = (TileEntitySafe)world.getTileEntity(x,y,z);

            if(Owner != "" && Owner != null && Owner != "ERROR")
                tile.Placer = Owner;





        }
    }
}

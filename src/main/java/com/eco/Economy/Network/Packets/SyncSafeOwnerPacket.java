package com.eco.Economy.Network.Packets;

import com.eco.Economy.Network.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import io.netty.buffer.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

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
    public void toBytes(ByteBuf data, Side side) {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);

        ByteBufUtils.writeUTF8String(data, Owner);
    }

    @Override
    public void fromBytes(ByteBuf data, Side side) {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();

        if(Owner != null && Owner != "")
            Owner = ByteBufUtils.readUTF8String(data);

    }

    @Override
    public void onMessage(Side side, EntityPlayer player) {

        World world = player.getEntityWorld();


        if(world.getTileEntity(x, y, z) instanceof TileEntitySafe){
            TileEntitySafe tile = (TileEntitySafe)world.getTileEntity(x,y,z);

            if(Owner != "" && Owner != null && Owner != "ERROR")
                tile.Placer = Owner;





        }
    }
}

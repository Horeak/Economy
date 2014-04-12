package com.eco.Economy.Network.Simple.Packets;

import com.eco.Economy.Network.Simple.IPacket;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SyncSafeOwnerPacket extends IPacket {



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
    public void read(DataInputStream data) throws IOException {

        x = data.readInt();
        y = data.readInt();
        z = data.readInt();

        if(Owner != null && Owner != "")
        Owner = data.readUTF();

    }

    @Override
    public void write(DataOutputStream data) throws IOException {

        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);

        data.writeUTF(Owner);

    }

    @Override
    public void execute(EntityPlayer player) {

        World world = player.getEntityWorld();


        if(world.getTileEntity(x, y, z) instanceof TileEntitySafe){
            TileEntitySafe tile = (TileEntitySafe)world.getTileEntity(x,y,z);

            if(Owner != "" && Owner != null && Owner != "ERROR")
            tile.Placer = Owner;





        }

    }
}

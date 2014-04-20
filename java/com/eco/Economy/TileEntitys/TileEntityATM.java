package com.eco.Economy.TileEntitys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityATM extends TileEntity {



   public boolean top;





    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("IsTop", top);

    }

    public void readFromNBT(NBTTagCompound nbt)
    {

        super.readFromNBT(nbt);
        top = nbt.getBoolean("IsTop");

    }
}

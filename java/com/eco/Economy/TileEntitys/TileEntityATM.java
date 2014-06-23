package com.eco.Economy.TileEntitys;

import net.minecraft.nbt.*;

public class TileEntityATM extends TileEntityInvBase {



   public boolean top;



    public TileEntityATM() {
        super(1, "Atm", 64);
    }


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

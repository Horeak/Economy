package com.eco.Economy.InvSlots;


import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class SlotNoTake extends Slot {

    public SlotNoTake(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    public boolean canTakeStack(EntityPlayer par1EntityPlayer)
    {
        return false;
    }
}

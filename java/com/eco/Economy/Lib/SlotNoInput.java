package com.eco.Economy.Lib;

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class SlotNoInput extends Slot {
    public SlotNoInput(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
}

package com.eco.Economy.InvSlots;

import com.eco.Economy.Items.Currency.CurrencyItem;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SafeSlot extends Slot {

    TileEntitySafe tile;

    public SafeSlot(TileEntitySafe tile, int par2, int par3, int par4) {
        super(tile, par2, par3, par4);
        this.tile = tile;


    }

    public void onSlotChanged()
    {
        this.inventory.markDirty();


        tile.InvChanged();
    }


    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof CurrencyItem;
    }


   // public boolean canTakeStack(EntityPlayer player)
  //  {

   //     return(tile.Placer != tile.EMPTY_GUI_STRING && player.getDisplayName().equalsIgnoreCase(tile.Placer));

 //   }
}

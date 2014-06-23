package com.eco.Economy.Lib;

import com.eco.Economy.TileEntitys.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class SlotPlayShopCreator extends Slot {
    TileEntityPlayerShop tile;
    public SlotPlayShopCreator(TileEntityPlayerShop tile, int par2, int par3, int par4) {
        super(tile, par2, par3, par4);
        this.tile = tile;
    }

    public boolean isItemValid(ItemStack stack)
    {
        return tile.getStackInSlot(1) != null && stack.getItem() == tile.getStackInSlot(1).getItem();
    }
}

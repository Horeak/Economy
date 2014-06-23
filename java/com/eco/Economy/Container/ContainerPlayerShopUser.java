package com.eco.Economy.Container;

import com.eco.Economy.Lib.*;
import com.eco.Economy.TileEntitys.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class ContainerPlayerShopUser extends Container {


    TileEntityPlayerShop tile;

    public ContainerPlayerShopUser(InventoryPlayer InvPlayer, TileEntityPlayerShop tile)
    {

        this.tile = tile;

        for(int x = 0; x < 9; x++){

            addSlotToContainer(new Slot(InvPlayer, x, 8 + 18 * x, 142));
        }

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){

                addSlotToContainer(new Slot(InvPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
            }
        }


        addSlotToContainer(new SlotNoTake(tile,0,44,9));
        addSlotToContainer(new SlotNoTake(tile,1,116,9));

        addSlotToContainer(new Slot(tile, 10, 44, 41));
        addSlotToContainer(new Slot(tile, 11, 116, 41));

    }





    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }





}
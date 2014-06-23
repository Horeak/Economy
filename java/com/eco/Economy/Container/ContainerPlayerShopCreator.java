package com.eco.Economy.Container;

import com.eco.Economy.Lib.*;
import com.eco.Economy.TileEntitys.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class ContainerPlayerShopCreator extends Container {


    TileEntityPlayerShop tile;

    public ContainerPlayerShopCreator(InventoryPlayer InvPlayer, TileEntityPlayerShop tile)
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


        addSlotToContainer(new Slot(tile,0,80,7));
        addSlotToContainer(new Slot(tile,1,80,61));

        for(int i = 0; i < 4; i++){
            addSlotToContainer(new SlotPlayShopCreator(tile,i+2,26,7 + (18*i)));

        }

        for(int i = 0; i < 4; i++){
            addSlotToContainer(new SlotNoInput(tile,i+6,134,7 + (18*i)));

        }
    }





    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }





}

package com.eco.Economy.Container;

import com.eco.Economy.TileEntitys.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class AtmContainer extends Container {


    TileEntityATM tile;

    public AtmContainer(InventoryPlayer InvPlayer, TileEntityATM tile)
    {

        this.tile = tile;

        for(int x = 0; x < 9; x++){

            addSlotToContainer(new Slot(InvPlayer, x, 8 + 18 * x, 231));
        }

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 9; x++){

                addSlotToContainer(new Slot(InvPlayer, x + y * 9 + 9, 8 + 18 * x, 173 + y * 18));
            }
        }



        addSlotToContainer(new Slot(tile, 0, 147, 75));
    }





    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tile.isUseableByPlayer(entityplayer);
    }





}

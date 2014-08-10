package com.eco.Economy.Blocks;

import com.eco.Economy.Items.Currency.*;
import com.eco.Economy.Utils.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public class BlockBankInserter extends Block {
    protected BlockBankInserter() {
        super(Material.iron);
        this.setHardness(1);
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(!world.isRemote){
            if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof CurrencyItem){
                InfoStorage.get(player).AddMoney(((CurrencyItem)player.inventory.getCurrentItem().getItem()).Value());
                player.inventory.getCurrentItem().stackSize -= 1;

            }
        }

        return true;
    }
}

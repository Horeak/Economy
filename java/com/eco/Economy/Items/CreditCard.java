package com.eco.Economy.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class CreditCard extends Item {


    public void addInformation(ItemStack Stack, EntityPlayer Player, List List, boolean par4) {

        if(Stack.getTagCompound() != null){
            String Owner = Stack.getTagCompound().getString("CardOwner");

            if(Owner != null && !Owner.equalsIgnoreCase(""))
            List.add(StatCollector.translateToLocal("itemDesc.creditcard.owner").replace("$owner$", Owner));
            else
                List.add(StatCollector.translateToLocal("itemDesc.creditcard.ownerNone"));
        }else{
            List.add(StatCollector.translateToLocal("itemDesc.creditcard.ownerNone"));
        }

    }


}

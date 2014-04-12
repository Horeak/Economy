package com.eco.Economy.Items.Currency;

import com.eco.Economy.Lib.MoneyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public abstract class CurrencyItem extends Item {


    public abstract int Value();
    public abstract String CurrencyType();

    public String getItemStackDisplayName(ItemStack stack)
    {
        return stack.getUnlocalizedName().replace("item.", "").replace("&", MoneyUtils.MoneyMark);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
    {

        list.add("A " + CurrencyType().toLowerCase() + " Worth " + Value() + MoneyUtils.MoneyMark + (!MoneyUtils.CurrencyName.equalsIgnoreCase("null") && MoneyUtils.CurrencyName != "" ? " in " + MoneyUtils.CurrencyName : "" ));


    }
}

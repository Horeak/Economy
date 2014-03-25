package com.eco.Economy.Items.Currency;

import com.eco.Economy.Lib.MoneyUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CurrencyItem extends Item {

    public String getItemStackDisplayName(ItemStack stack)
    {
        return stack.getUnlocalizedName().replace("item.", "").replace(".name", "").replace("&", MoneyUtils.MoneyMark);
    }
}

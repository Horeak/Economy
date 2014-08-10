package com.eco.Economy.Items.Currency;

import com.eco.Economy.Utils.MoneyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

import java.util.List;

public abstract class CurrencyItem extends Item {


    public abstract int Value();

    /**
     * Currency type 1 = bill
     * Currency type 2 = coin
     */
    public abstract int CurrencyType();

    public String getItemStackDisplayName(ItemStack stack)
    {
        return EnumChatFormatting.YELLOW + stack.getUnlocalizedName().replace("item.", "").replace("&", MoneyUtils.MoneyMark);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
    {

        list.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("message.currency.worth").replace("$cur$", CurrencyType() == 1 ? StatCollector.translateToLocal("message.currency.bill") : StatCollector.translateToLocal("message.currency.coin")).replace("$amo$", EnumChatFormatting.BLUE + "" + Value()).replace("$monMark$", MoneyUtils.MoneyMark)  + (!MoneyUtils.CurrencyName.equalsIgnoreCase("null") && MoneyUtils.CurrencyName != "" ? StatCollector.translateToLocal("message.currency.worthCustomCurrency") : "").replace("$curName$", MoneyUtils.CurrencyName));


    }
}

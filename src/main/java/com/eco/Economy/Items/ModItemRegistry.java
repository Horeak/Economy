package com.eco.Economy.Items;

import com.eco.Economy.Items.Currency.Bills.Bill100;
import com.eco.Economy.Items.Currency.Bills.Bill1000;
import com.eco.Economy.Items.Currency.Bills.Bill200;
import com.eco.Economy.Items.Currency.Bills.Bill50;
import com.eco.Economy.Items.Currency.Bills.Bill500;
import com.eco.Economy.Items.Currency.Coins.Coin1;
import com.eco.Economy.Items.Currency.Coins.Coin10;
import com.eco.Economy.Items.Currency.Coins.Coin20;
import com.eco.Economy.Items.Currency.Coins.Coin5;
import com.eco.Economy.Lib.Config.ConfigUtils;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Main.Economy;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class ModItemRegistry {

    public static Item Coin1, Coin5, Coin10, Coin20;
    public static Item Bill50, Bill100, Bill200, Bill500, Bill1000;

    public static Item CreditCard;

    public static void Register(){




        //The different types of currency coins used
        Coin1 = new Coin1();
        RegisterCurrency(Coin1, "&1 " + StatCollector.translateToLocal("message.currency.coin"), "coin_1");

        Coin5 = new Coin5();
        RegisterCurrency(Coin5, "&5 " + StatCollector.translateToLocal("message.currency.coin"), "coin_5");

        Coin10 = new Coin10();
        RegisterCurrency(Coin10, "&10 " + StatCollector.translateToLocal("message.currency.coin"), "coin_10");

        Coin20 = new Coin20();
        RegisterCurrency(Coin20, "&20 " + StatCollector.translateToLocal("message.currency.coin"), "coin_20");


        //The different types of currency bills
        Bill50 = new Bill50();
        RegisterCurrency(Bill50, "&50 " + StatCollector.translateToLocal("message.currency.bill"), "bill_50");

        Bill100 = new Bill100();
        RegisterCurrency(Bill100, "&100 " + StatCollector.translateToLocal("message.currency.bill"), "bill_100");

        Bill200 = new Bill200();
        RegisterCurrency(Bill200, "&200 " + StatCollector.translateToLocal("message.currency.bill"), "bill_200");

        Bill500 = new Bill500();
        RegisterCurrency(Bill500, "&500 " + StatCollector.translateToLocal("message.currency.bill"), "bill_500");

        Bill1000 = new Bill1000();
        RegisterCurrency(Bill1000, "&1000 " + StatCollector.translateToLocal("message.currency.bill"), "bill_1000");

        CreditCard = new CreditCard().setTextureName(ModInfo.ModTextures + ":CreditCard");
        RegisterItem(CreditCard, "creditcard");




    }


    public static void RegisterCurrency(Item Item, String Name, String TextureName){

        ConfigUtils.ItemConfigNames.put(Item, Name);

        if(ConfigUtils.IsItemEnabled(Item)) {
            Item.setUnlocalizedName((Name));
            GameRegistry.registerItem(Item, (Name));
            Item.setCreativeTab(Economy.ModTab);
            Item.setTextureName(ModInfo.ModTextures + ":" + "currency/" + TextureName);
        }

    }


    public static void RegisterItem(Item Item, String Name){

        ConfigUtils.ItemConfigNames.put(Item, Name);

        if(ConfigUtils.IsItemEnabled(Item)) {
            Item.setUnlocalizedName((Name));
            GameRegistry.registerItem(Item, (Name));
            Item.setCreativeTab(Economy.ModTab);
        }

    }
}

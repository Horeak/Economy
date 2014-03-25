package com.eco.Economy.Items;

import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Items.Currency.Bills.*;
import com.eco.Economy.Items.Currency.Coins.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItemRegistry {

    public static Item Coin1, Coin5, Coin10, Coin20;
    public static Item Bill50, Bill100, Bill200, Bill500, Bill1000;

    public static void Register(){




        //The different types of currency coins used
        Coin1 = new Coin1();
        RegisterCurrency(Coin1, "&1 Coin", "coin_1");

        Coin5 = new Coin5();
        RegisterCurrency(Coin5, "&5 Coin", "coin_5");

        Coin10 = new Coin10();
        RegisterCurrency(Coin10, "&10 Coin", "coin_10");

        Coin20 = new Coin20();
        RegisterCurrency(Coin20, "&20 Coin", "coin_20");


        //The different types of currency bills
        Bill50 = new Bill50();
        RegisterCurrency(Bill50, "&50 Bill", "bill_50");

        Bill100 = new Bill100();
        RegisterCurrency(Bill100, "&100 Bill", "bill_100");

        Bill200 = new Bill200();
        RegisterCurrency(Bill200, "&200 Bill", "bill_200");

        Bill500 = new Bill500();
        RegisterCurrency(Bill500, "&500 Bill", "bill_500");

        Bill1000 = new Bill1000();
        RegisterCurrency(Bill1000, "&1000 Bill", "bill_1000");




    }


    public static void RegisterCurrency(Item Item, String Name, String TextureName){

            Item.setUnlocalizedName((Name));
            GameRegistry.registerItem(Item, (Name));
            Item.setCreativeTab(Economy.ModTab);
            Item.setTextureName(ModInfo.ModTextures + ":" + "currency/" + TextureName);

    }

}

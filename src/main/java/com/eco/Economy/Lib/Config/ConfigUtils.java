package com.eco.Economy.Lib.Config;

import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Lib.MoneyUtils;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.HashMap;

public class ConfigUtils {


    public static final String CATEGORY_CLIENT_SETTINGS = "Client Settings";
    public static final String CATEGORY_SERVER_SETTINGS = "Server Settings";
    public static final String CATEGORY_BLOCKS = "Blocks";
    public static final String CATEGORY_ITEMS = "Items";

    public static HashMap<Block, String> BlockConfigNames = new HashMap<Block, String>();
    public static HashMap<Item, String> ItemConfigNames = new HashMap<Item, String>();

    private static Configuration config;

    public static void IntitConfig(String ConfigPath){

        if(config == null){
            config = new Configuration(new File(ConfigPath));
        }

        LoadConfig();

    }

    public static void LoadConfig(){

        //Client Settings
        MoneyUtils.MoneyMark = config.get(CATEGORY_CLIENT_SETTINGS, "What sign should be used for money?", "$").getString();

        Property textArea = config.get(CATEGORY_CLIENT_SETTINGS, "Where on the screen should the money be showed?", 1);
        textArea.comment = "Top right = 1, Top left = 2,  Bottom right = 3,  Bottom left = 4";

         MoneyUtils.CurrencyName = config.get(CATEGORY_CLIENT_SETTINGS, "What should the currency be called? (null for nothing)(default=null)", "null").getString();



        //Server Settings
        MoneyUtils.Multiplier = config.get(CATEGORY_SERVER_SETTINGS, "What should be the multiplier for money? (used for changing currency)", 1).getInt();
        MoneyUtils.StarterMoney = config.get(CATEGORY_SERVER_SETTINGS, "What amount of money should new players start with?", 1000).getInt();
        MoneyUtils.MaxMoneyTransfer = config.get(CATEGORY_SERVER_SETTINGS, "What should the max amount of money being transferred at once be?", 100000).getInt();
        MoneyUtils.MaxPinLength = config.get(CATEGORY_SERVER_SETTINGS, "What should the max length of the bank pin code be?(Max 24)", 4).getInt();

        if(config.hasChanged())
            config.save();
    }

    public static boolean IsBlockEnabled(Block block){
        boolean bl = config.get(CATEGORY_BLOCKS, "Enable " + BlockConfigNames.get(block), true).getBoolean(true);

        if(config.hasChanged())
            config.save();

        return bl;
    }



    public static boolean IsItemEnabled(Item item){
        boolean bl = config.get(CATEGORY_ITEMS, "Enable " + ItemConfigNames.get(item), true).getBoolean(true);

        if(config.hasChanged())
            config.save();

        return bl;
    }


    public static Configuration GetConfig(){
        return config;
    }
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.modID.equalsIgnoreCase(ModInfo.ModId))
            ConfigUtils.LoadConfig();

    }
}

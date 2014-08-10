package com.eco.Economy.Utils;

import MiscUtils.Utils.Config.ConfigBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config extends ConfigBase {

    protected Configuration config;



    public Config(String Loc){

        config = new Configuration(new File(Loc + "/tm1990's mods/Economy.cfg"));
        InitConfig();
    }

    @Override
    public Configuration GetConfigFile() {
        return config;
    }

    @Override
    public void InitConfig() {

        config.addCustomCategoryComment(CATEGORY_CLIENT_SETTINGS, "Client side only settings. Settings that does not affect gameplay");
        config.addCustomCategoryComment(CATEGORY_SERVER_SETTINGS, "Server side settings which can affect gameplay");

        config.addCustomCategoryComment(CATEGORY_BLOCKS, "This allows you to enabled/disable the different blocks from the mod");
        config.addCustomCategoryComment(CATEGORY_ITEMS, "This allows you to enabled/disable the different items from the mod");

        config.addCustomCategoryComment(CATEGORY_WORLDGEN, "This allows you to disable and change different world generation types");

        config.setCategoryRequiresMcRestart(CATEGORY_BLOCKS, true);
        config.setCategoryRequiresMcRestart(CATEGORY_ITEMS, true);

        config.setCategoryRequiresMcRestart(CATEGORY_WORLDGEN, true);

        LoadConfig();
    }

    @Override
    public void LoadConfig() {


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

    public  boolean IsBlockEnabled(Block block){
        if(BlockConfigNames.get(block) == null)
            return false;


        boolean bl = GetConfigFile().get(CATEGORY_BLOCKS, "Enable " + BlockConfigNames.get(block).replace("tile.", "").replace(".name", ""), true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }



    public  boolean IsItemEnabled(Item item){
        if(ItemConfigNames.get(item) == null)
            return false;

        boolean bl = GetConfigFile().get(CATEGORY_ITEMS, "Enable " + ItemConfigNames.get(item).replace("item.", "").replace(".name", ""), true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }


    public  boolean IsWorldGeneratorEnabled(String WorldGen){
        boolean bl = GetConfigFile().get(CATEGORY_WORLDGEN, "Enable Worldgen: " + WorldGen, true).getBoolean(true);

        if(GetConfigFile().hasChanged())
            GetConfigFile().save();

        return bl;
    }

    public  int GetWorldGenerationChance(String WorldGen, int def){
        if(IsWorldGeneratorEnabled(WorldGen)){
            int t = GetConfigFile().get(CATEGORY_WORLDGEN, "The amount of times it will try to spawn in a chunk: " + WorldGen, def).getInt(def);

            if(GetConfigFile().hasChanged())
                GetConfigFile().save();

            return t;


        }
        return 0;
    }

}

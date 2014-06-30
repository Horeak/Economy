package com.eco.Economy.Lib.Config;

import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Lib.MoneyUtils;
import com.eco.Economy.Main.Economy;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigChanged {

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if(eventArgs.modID.equals(ModInfo.ModId))
            Economy.config.save();


            MoneyUtils.MoneyMark = Economy.config.get("Client Settings", "What sign should be used for money?", "$").getString();
            MoneyUtils.TextArea = Economy.config.get("Client Settings", "Where on the screen should the money be showed?  top_right = 1  top_left = 2  bottom_right = 3  bottom_left = 4  Mode", 1).getInt();
            MoneyUtils.CurrencyName = Economy.config.get("Client Settings", "What should the currency be called? (null for nothing)(default=null)", "null").getString();


        MoneyUtils.Multiplier = Economy.config.get("Server Settings", "What should be the multiplier for money? (used for changing currency)", 1).getInt();
        MoneyUtils.StarterMoney = Economy.config.get("Server Settings", "What amount of money should new players start with?", 1000).getInt();
        MoneyUtils.MaxMoneyTransfer = Economy.config.get("Server Settings", "What should the max amount of money being transferred at once be?", 100000).getInt();
        MoneyUtils.MaxPinLength = Economy.config.get("Server Settings", "What should the max length of the ban pin code be?(Max 24)", 4).getInt();

        if(Economy.config.hasChanged())
            Economy.config.save();

    }
}

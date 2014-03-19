package com.eco.Economy.Tick;

import com.eco.Economy.Lib.MoneyStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ServerTickHandler {




    //DEBUG! this is a debug feature to test the Economy system. Hold shift to give that amount of money specified below
    @SubscribeEvent
    public void PlayerTick(TickEvent.PlayerTickEvent event){
        if(event.type == TickEvent.Type.PLAYER){
            if(!event.player.worldObj.isRemote)

            if(event.player.isSneaking()){

                MoneyStorage.get(event.player).SetMoney(1000);


            }


        }

    }

    }


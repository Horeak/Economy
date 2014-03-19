package com.eco.Economy.Event;

import com.eco.Economy.Lib.MoneyStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityConstructingEvent
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {


        if (event.entity instanceof EntityPlayer && MoneyStorage.get((EntityPlayer) event.entity) == null)
            MoneyStorage.register((EntityPlayer) event.entity);

        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(MoneyStorage.EXT_PROP_NAME) == null)
            event.entity.registerExtendedProperties(MoneyStorage.EXT_PROP_NAME, new MoneyStorage((EntityPlayer) event.entity));
    }
}
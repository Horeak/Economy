package com.eco.Economy.Event;

import com.eco.Economy.Lib.InfoStorage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityConstructingEvent
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {


        if (event.entity instanceof EntityPlayer && InfoStorage.get((EntityPlayer) event.entity) == null)
            InfoStorage.register((EntityPlayer) event.entity);

        if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(InfoStorage.EXT_PROP_NAME) == null)
            event.entity.registerExtendedProperties(InfoStorage.EXT_PROP_NAME, new InfoStorage((EntityPlayer) event.entity));
    }
}
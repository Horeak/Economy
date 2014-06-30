package com.eco.Economy.Event;

import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class PreventBlockBreakEvent {


    @SubscribeEvent
    public void PreventSafeBreak(BlockEvent.BreakEvent event){

        if(event.block == ModBlockRegistry.Safe){
        if(event.world.getTileEntity(event.x, event.y, event.z) instanceof TileEntitySafe){
            TileEntitySafe tile =  (TileEntitySafe)event.world.getTileEntity(event.x, event.y, event.z);


            if(tile.Placer != "" && !event.getPlayer().getDisplayName().equalsIgnoreCase(tile.Placer) && !event.getPlayer().capabilities.isCreativeMode){
                event.setCanceled(true);

            }

        }
        }

    }

    @SubscribeEvent
    public void PreventPlayerShopBreak(BlockEvent.BreakEvent event){

        if(event.block == ModBlockRegistry.PlayerShopGlass){
            if(event.world.getTileEntity(event.x, event.y, event.z) instanceof TileEntityPlayerShop){
                TileEntityPlayerShop tile =  (TileEntityPlayerShop)event.world.getTileEntity(event.x, event.y, event.z);


                if(tile.BlockCreator != "" && !event.getPlayer().getDisplayName().equalsIgnoreCase(tile.BlockCreator) && !event.getPlayer().capabilities.isCreativeMode){
                    event.setCanceled(true);

                }

            }
        }

    }
}

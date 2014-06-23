package com.eco.Economy.Gui;

import com.eco.Economy.Container.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {


        TileEntity tile_entity = world.getTileEntity(x, y, z);


        if(tile_entity instanceof TileEntitySafe)
            return new SafeInvContainer(player.inventory, (TileEntitySafe)tile_entity);

        if(tile_entity instanceof TileEntityATM)
            return new AtmContainer(player.inventory, (TileEntityATM)tile_entity);

        if(tile_entity instanceof TileEntityPlayerShop){
            if(ID == 1)
                return new ContainerPlayerShopCreator(player.inventory, (TileEntityPlayerShop)tile_entity);
            else
                return new ContainerPlayerShopUser(player.inventory, (TileEntityPlayerShop)tile_entity);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {



        TileEntity tile_entity = world.getTileEntity(x, y, z);



        if(tile_entity instanceof TileEntitySafe)
            return new GuiSafe(player.inventory, (TileEntitySafe)tile_entity);

        if(tile_entity instanceof TileEntityATM)
            return new GuiATM(player.inventory, (TileEntityATM)tile_entity);

        if(tile_entity instanceof TileEntityPlayerShop){
            if(ID == 1)
                return new GuiPlayerShopCreator(player.inventory, (TileEntityPlayerShop)tile_entity);
            else
                return new GuiPlayerShopUser(player.inventory, (TileEntityPlayerShop)tile_entity);
        }

        return null;
    }
}

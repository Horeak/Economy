package com.eco.Economy.Proxies;


import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.ItemRender.ATMItemRender;
import com.eco.Economy.ItemRender.SafeItemRender;
import com.eco.Economy.Main.*;
import com.eco.Economy.Tick.ClientTickHandler;
import com.eco.Economy.TileEntityRender.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.*;

public class ClientProxy extends ServerProxy{


    public void RegisterClientTick(){
        tickHandlerClient = new ClientTickHandler();


    }

    @Override
    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, Item itemID, int metaData, int stackSize, int color) {

        World world = FMLClientHandler.instance().getClient().theWorld;
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        this.handleTileEntityPacket(x, y, z, orientation, state, customName);

        if (tileEntity != null) {
            if (tileEntity instanceof TileEntityPlayerShop) {

                ItemStack itemStack = new ItemStack(itemID, stackSize, metaData);
                if (color != Integer.parseInt("ffffff", 16)) {
                    Economy.setColor(itemStack, color);
                }

                ((TileEntityPlayerShop) tileEntity).setInventorySlotContents(1, itemStack);
            }




        }
    }




    public void RegisterRenders(){

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATM.class, new TileEntityATMRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySafe.class, new TileEntitySafeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerShop.class, new TileEntityPlayerShopRender());


        MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(ModBlockRegistry.ATM), new ATMItemRender());
        MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(ModBlockRegistry.Safe), new SafeItemRender());

    }

}

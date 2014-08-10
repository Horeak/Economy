package com.eco.Economy.Utils.Proxies;


import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.Render.ItemRender.ATMItemRender;
import com.eco.Economy.Render.ItemRender.SafeItemRender;
import com.eco.Economy.Render.TileEntityRender.TileEntityATMRender;
import com.eco.Economy.Render.TileEntityRender.TileEntityPlayerShopRender;
import com.eco.Economy.Render.TileEntityRender.TileEntitySafeRender;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.TileEntitys.TileEntityPlayerShop;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import com.eco.Economy.Utils.Tickhandler.ClientTickHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;

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

package com.eco.Economy.Proxies;


import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.ItemRender.ATMItemRender;
import com.eco.Economy.Tick.ClientTickHandler;
import com.eco.Economy.TileEntityRender.TileEntityATMRender;
import com.eco.Economy.TileEntitys.TileEntityATM;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy{


    public void RegisterClientTick(){
        tickHandlerClient = new ClientTickHandler();


    }


    public void RegisterRenders(){

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityATM.class, new TileEntityATMRender());


        MinecraftForgeClient.registerItemRenderer(ItemBlock.getItemFromBlock(ModBlockRegistry.ATM), new ATMItemRender());

    }

}

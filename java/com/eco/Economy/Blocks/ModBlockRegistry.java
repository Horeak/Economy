package com.eco.Economy.Blocks;

import com.eco.Economy.ItemBlocks.ItemBlockATM;
import com.eco.Economy.ItemBlocks.ItemBlockSafe;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ModBlockRegistry {

    public static Block ATM;
    public static Block Safe;


    public static void Register(){



        ATM = new BlockATM();
        Register(ATM, ItemBlockATM.class, "ATM");

        Safe = new BlockSafe();
        Register(Safe, ItemBlockSafe.class, "Safe");




        GameRegistry.registerTileEntity(TileEntityATM.class, "ATM");
        GameRegistry.registerTileEntity(TileEntitySafe.class, "Safe");

    }


    public static void Register(Block block, String Name){



            block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(block, Name.toLowerCase().replace(" ", "_"));
        block.setCreativeTab(Economy.ModTab);




    }

    public static void Register(Block Block, Class<? extends ItemBlock> itemclass, String Name){


            Block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(Block, itemclass, Name.toLowerCase().replace(" ", "_"));

        Block.setCreativeTab(Economy.ModTab);



    }
}

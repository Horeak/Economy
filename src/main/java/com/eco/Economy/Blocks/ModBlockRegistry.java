package com.eco.Economy.Blocks;

import com.eco.Economy.ItemBlocks.*;
import com.eco.Economy.Lib.*;
import com.eco.Economy.Main.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class ModBlockRegistry {

    public static Block ATM;
    public static Block Safe;
    public static Block BankInserter;
    public static Block PlayerShop;
    public static Block PlayerShopGlass;


    public static void Register(){



        ATM = new BlockATM();
        Register(ATM, ItemBlockATM.class, "ATM");

        Safe = new BlockSafe();
        Register(Safe, ItemBlockSafe.class, "Safe");

        BankInserter = new BlockBankInserter().setBlockTextureName(ModInfo.ModTextures + ":Inserter");
        Register(BankInserter, "BankInserter");

        PlayerShop = new BlockPlayerShop();
        Register(PlayerShop, "PlayerShop");

        PlayerShopGlass = new BlockPlayerShopGlass().setBlockTextureName(ModInfo.ModTextures + ":PlayerShopGlass");
        Register(PlayerShopGlass, "PlayerShopGlass");






        GameRegistry.registerTileEntity(TileEntityATM.class, "ATM");
        GameRegistry.registerTileEntity(TileEntitySafe.class, "Safe");
        GameRegistry.registerTileEntity(TileEntityPlayerShop.class, "PlayerShop");

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

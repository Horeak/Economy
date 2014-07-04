package com.eco.Economy.Blocks;

import com.eco.Economy.ItemBlocks.ItemBlockATM;
import com.eco.Economy.ItemBlocks.ItemBlockSafe;
import com.eco.Economy.Lib.Config.ConfigUtils;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.TileEntitys.TileEntityPlayerShop;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public class ModBlockRegistry {

    public static Block ATM;
    public static Block Safe;
    public static Block BankInserter;
    public static Block PlayerShop;
    public static Block PlayerShopGlass;


    public static void Register(){



        ATM = new BlockATM();
        Register(ATM, ItemBlockATM.class, "ATM", TileEntityATM.class);

        Safe = new BlockSafe();
        Register(Safe, ItemBlockSafe.class, "Safe", TileEntitySafe.class);

        BankInserter = new BlockBankInserter().setBlockTextureName(ModInfo.ModTextures + ":Inserter");
        Register(BankInserter, "BankInserter");

        PlayerShop = new BlockPlayerShop();
        Register(PlayerShop, "PlayerShop", TileEntityPlayerShop.class);

        PlayerShopGlass = new BlockPlayerShopGlass().setBlockTextureName(ModInfo.ModTextures + ":PlayerShopGlass");
        Register(PlayerShopGlass, "PlayerShopGlass");





    }


    public static void Register(Block block, String Name){
        ConfigUtils.BlockConfigNames.put(block, Name);

        if(ConfigUtils.IsBlockEnabled(block)) {
            block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(block, Name.toLowerCase().replace(" ", "_"));
            block.setCreativeTab(Economy.ModTab);
        }
    }

    public static void Register(Block block, String Name, Class<? extends  TileEntity> c){
        ConfigUtils.BlockConfigNames.put(block, Name);

        if(ConfigUtils.IsBlockEnabled(block)) {
            block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(block, Name.toLowerCase().replace(" ", "_"));
            block.setCreativeTab(Economy.ModTab);
            GameRegistry.registerTileEntity(c, "[" + ModInfo.ModId + "]" + Name);
        }
    }

    public static void Register(Block Block, Class<? extends ItemBlock> itemclass, String Name){
        ConfigUtils.BlockConfigNames.put(Block, Name);

        if(ConfigUtils.IsBlockEnabled(Block)) {
            Block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(Block, itemclass, Name.toLowerCase().replace(" ", "_"));

            Block.setCreativeTab(Economy.ModTab);
        }
    }

    public static void Register(Block Block, Class<? extends ItemBlock> itemclass, String Name, Class<? extends  TileEntity> c){
        ConfigUtils.BlockConfigNames.put(Block, Name);

        if(ConfigUtils.IsBlockEnabled(Block)) {
            Block.setBlockName(Name.toLowerCase().replace(" ", "_"));
            GameRegistry.registerBlock(Block, itemclass, Name.toLowerCase().replace(" ", "_"));
            Block.setCreativeTab(Economy.ModTab);
            GameRegistry.registerTileEntity(c, "[" + ModInfo.ModId + "]" + Name);
        }
    }
}

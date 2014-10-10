package com.eco.Economy.Blocks;

import MiscUtils.Register.BlockRegister;
import com.eco.Economy.Items.ItemBlocks.ItemBlockATM;
import com.eco.Economy.Items.ItemBlocks.ItemBlockSafe;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.TileEntitys.TileEntityPlayerShop;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import com.eco.Economy.Utils.ModInfo;
import net.minecraft.block.Block;

public class ModBlockRegistry {

    public static Block ATM;
    public static Block Safe;
    public static Block BankInserter;
    public static Block PlayerShop;
    public static Block PlayerShopGlass;


    public static void Register(){
        BlockRegister Utils = new BlockRegister(Economy.config, ModInfo.ModId);




        ATM = new BlockATM().setCreativeTab(Economy.ModTab);
        Utils.Register(ATM, ItemBlockATM.class, "ATM", TileEntityATM.class);

        Safe = new BlockSafe().setCreativeTab(Economy.ModTab);
        Utils.Register(Safe, ItemBlockSafe.class, "Safe", TileEntitySafe.class);

        BankInserter = new BlockBankInserter().setBlockTextureName(ModInfo.ModTextures + ":Inserter").setCreativeTab(Economy.ModTab);
        Utils.Register(BankInserter, "BankInserter");

        PlayerShop = new BlockPlayerShop().setCreativeTab(Economy.ModTab);
        Utils.Register(PlayerShop, "PlayerShop", TileEntityPlayerShop.class);

        PlayerShopGlass = new BlockPlayerShopGlass().setBlockTextureName(ModInfo.ModTextures + ":PlayerShopGlass").setCreativeTab(Economy.ModTab);
        Utils.Register(PlayerShopGlass, "PlayerShopGlass");


    }



}

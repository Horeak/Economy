package com.eco.Economy.Blocks;

import com.eco.Economy.Items.*;
import com.eco.Economy.Lib.*;
import com.eco.Economy.Main.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.network.internal.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class BlockATM extends BlockContainer {

    public BlockATM(){
        super(Material.iron);
        this.setHardness(2F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityATM();
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z){



        return super.canPlaceBlockAt(world, x, y, z) && world.getBlock(x, y + 1, z) == Blocks.air && world.getBlock(x, y - 1, z) != ModBlockRegistry.ATM;
    }

    public void onBlockAdded(World world, int x, int y, int z) {


        int Meta = world.getBlockMetadata(x, y, z);

        if(Meta < 1){

        world.setBlock(x, y + 1, z, ModBlockRegistry.ATM, 1, 2);

            TileEntityATM tile = new TileEntityATM();
            tile.top = true;

        world.setTileEntity(x, y + 1, z, tile);
        }
        super.onBlockAdded(world, x, y, z);
        this.func_149930_e(world, x, y, z);

    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {

        if(world.getTileEntity(x, y, z) instanceof TileEntityATM){
            TileEntityATM tile = (TileEntityATM)world.getTileEntity(x, y, z);
            if(tile.top)
                world.setBlock(x, y - 1, z, Blocks.air);
            else
                world.setBlock(x, y + 1, z, Blocks.air);


        }

        super.breakBlock(world, x, y, z, block, meta);

    }


    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }



    @Override
    public int getRenderType() {
        return -1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(ModInfo.ModTextures + ":" + "ATM");
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {



        if(!world.isRemote)
            if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof CreditCard) {
                System.out.println(MoneyUtils.GetCreditCardOwner(player.inventory.getCurrentItem()));
                System.out.println(MoneyUtils.GetCreditCardPin(player.inventory.getCurrentItem()));
            }

        if(!world.isRemote){
            FMLNetworkHandler.openGui(player, Economy.instance, 0, world, x, y, z);
        }


    return  true;
    }

    private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
    {
        if (!p_149930_1_.isRemote)
        {
            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
        }
    }



    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

    }

}

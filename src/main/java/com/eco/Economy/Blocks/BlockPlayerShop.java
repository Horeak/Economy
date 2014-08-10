package com.eco.Economy.Blocks;

import com.eco.Economy.Utils.*;
import com.eco.Economy.Main.*;
import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.common.network.internal.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class BlockPlayerShop extends BlockContainer {
    protected BlockPlayerShop() {
        super(Material.iron);
    }

    IIcon topIcon, sideIcon;

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister)
    {
        topIcon = iIconRegister.registerIcon(ModInfo.ModTextures + ":PlayerShopTop");
        sideIcon = iIconRegister.registerIcon(ModInfo.ModTextures + ":PlayerShopSides");

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 && meta == 1  ? topIcon : sideIcon;
    }


    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPlayerShop();
    }


    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack) {

        if(ent instanceof EntityPlayer){
            if(world.getTileEntity(x,y,z) instanceof TileEntityPlayerShop){
                EntityPlayer player = (EntityPlayer)ent;
                TileEntityPlayerShop tile = (TileEntityPlayerShop)world.getTileEntity(x,y,z);

                tile.BlockCreator = player.getCommandSenderName();
            }

        }

    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(world.isRemote)
            return true;


        if(world.getTileEntity(x,y,z) instanceof TileEntityPlayerShop){
            TileEntityPlayerShop tile = (TileEntityPlayerShop)world.getTileEntity(x,y,z);

            if(player.capabilities.isCreativeMode && player.isSneaking())
                FMLNetworkHandler.openGui(player, Economy.instance, 1, world, x, y, z);

            else if(player.capabilities.isCreativeMode && !player.isSneaking())
                FMLNetworkHandler.openGui(player, Economy.instance, 2, world, x, y, z);

            else if(player.getCommandSenderName().equalsIgnoreCase(tile.BlockCreator))
                FMLNetworkHandler.openGui(player, Economy.instance, 1, world, x, y, z);

            else
                FMLNetworkHandler.openGui(player, Economy.instance, 2, world, x, y, z);


        }

        return true;
    }


    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(world.getBlock(x,y+1,z) == ModBlockRegistry.PlayerShopGlass) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            world.markBlockForUpdate(x,y,z);
        }else {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
            world.markBlockForUpdate(x,y,z);
        }
    }

    @Override
    public void breakBlock(World World, int x, int y, int z, Block id, int meta)
    {
        TileEntity tile = World.getTileEntity(x, y, z);

        if(tile != null && tile instanceof IInventory){
            IInventory inv = (IInventory)tile;

            for(int i = 0; i < inv.getSizeInventory(); i++){
                ItemStack stack = inv.getStackInSlotOnClosing(i);

                if(stack != null){
                    float spawnX = x + World.rand.nextFloat();
                    float spawnY = y + World.rand.nextFloat();
                    float spawnZ = z + World.rand.nextFloat();


                    EntityItem droppedItem = new EntityItem(World, spawnX, spawnY, spawnZ, stack);

                    float mult = 0.05F;

                    droppedItem.motionX = (-0.5 + World.rand.nextFloat()) * mult;
                    droppedItem.motionY = (4 + World.rand.nextFloat()) * mult;
                    droppedItem.motionZ = (-0.5 + World.rand.nextFloat()) * mult;


                    World.spawnEntityInWorld(droppedItem);
                }

            }
        }
        super.breakBlock(World, x, y, z, id, meta);
    }

}

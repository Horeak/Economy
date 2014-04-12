package com.eco.Economy.Blocks;

import com.eco.Economy.Lib.ChatMessageHandler;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.Simple.Packets.SyncSafeOwnerPacket;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSafe extends BlockContainer {


    protected BlockSafe() {
        super(Material.iron);
        this.setHardness(2);
    }

    public static ResourceLocation ModelTexture = new ResourceLocation("textures/blocks/hardened_clay_stained_cyan.png");

    public boolean canPlaceBlockAt(World world, int x, int y, int z){



        return super.canPlaceBlockAt(world, x, y, z) && world.getBlock(x, y + 1, z) == Blocks.air && world.getBlock(x, y - 1, z) != ModBlockRegistry.Safe;
    }

    public void onBlockAdded(World world, int x, int y, int z) {


        int Meta = world.getBlockMetadata(x, y, z);

        if(Meta < 1){

            world.setBlock(x, y + 1, z, ModBlockRegistry.Safe, 1, 2);

            TileEntitySafe tile = new TileEntitySafe();
            tile.top = true;

            world.setTileEntity(x, y + 1, z, tile);
        }
        super.onBlockAdded(world, x, y, z);
        this.func_149930_e(world, x, y, z);

    }


    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {


        TileEntity tile = world.getTileEntity(x, y, z);

        if(tile != null && tile instanceof IInventory){
            IInventory inv = (IInventory)tile;

            for(int i = 0; i < inv.getSizeInventory(); i++){
                ItemStack stack = inv.getStackInSlotOnClosing(i);

                if(stack != null){
                    float spawnX = x + world.rand.nextFloat();
                    float spawnY = y + world.rand.nextFloat();
                    float spawnZ = z + world.rand.nextFloat();


                    EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);

                    float mult = 0.05F;

                    droppedItem.motionX = (-0.5 + world.rand.nextFloat()) * mult;
                    droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
                    droppedItem.motionZ = (-0.5 + world.rand.nextFloat()) * mult;


                    world.spawnEntityInWorld(droppedItem);

                }

            }





        if(world.getTileEntity(x, y, z) instanceof TileEntitySafe){
            TileEntitySafe tile_e = (TileEntitySafe)world.getTileEntity(x, y, z);
            if(tile_e.top)
                world.setBlock(x, y - 1, z, Blocks.air);
            else
                world.setBlock(x, y + 1, z, Blocks.air);


        }


            super.breakBlock(world, x, y, z, block, meta);


        }

    }


    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySafe();
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
        this.blockIcon = icon.registerIcon(ModInfo.ModTextures + ":" + "Safe");
    }



    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {


            if(par1World.getTileEntity(par2, par3, par4) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe) par1World.getTileEntity(par2, par3, par4);

                if(tile.GetGuiOwner() == tile.EMPTY_GUI_STRING || par5EntityPlayer.getDisplayName().equalsIgnoreCase(tile.GetGuiOwner())){



            FMLNetworkHandler.openGui(par5EntityPlayer, Economy.instance, 0, par1World, par2, par3, par4);
                }else{

                    ChatMessageHandler.sendChatToPlayer(par5EntityPlayer, StatCollector.translateToLocal("message.safe.wrongOwner").replace("$Owner$", tile.GetGuiOwner()));
                }
            }
            return true;

        }


    }


    private void func_149930_e(World world, int x, int y, int z)
    {



        if (!world.isRemote)
        {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
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

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }



    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {

        if(par5EntityLivingBase instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
            //TODO FIX!
            //Economy.packetPipeline.sendToDimension(new SyncSafeOwnerPakcet(par2, par3, par4, player.getDisplayName()), par1World.getWorldInfo().getVanillaDimension());

            if(par1World.getTileEntity(par2, par3, par4) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)par1World.getTileEntity(par2, par3, par4);

                tile.SetOwner(player.getDisplayName(), true);



            }else{
                par1World.setTileEntity(par2, par3, par4, this.createNewTileEntity(par1World, par6ItemStack.getItemDamage()));
                TileEntitySafe tile = (TileEntitySafe)par1World.getTileEntity(par2, par3, par4);
                tile.SetOwner(player.getDisplayName(), true);

            }

        }



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

package com.eco.Economy.TileEntitys;


import com.eco.Economy.Items.Currency.CurrencyItem;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.Simple.Packets.SyncSafeOwnerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntitySafe extends TileEntity implements IInventory {



    public boolean top;

    public String Placer = NULL_STRING;

    public static String NULL_STRING ="ERROR";
    public static String EMPTY_GUI_STRING = "ERROR_EMPTY_OWNER";

    public String GetGuiOwner(){
        if(!Placer.equalsIgnoreCase(NULL_STRING))
        return Placer;
        else
            return EMPTY_GUI_STRING;
    }

    public void SetOwner(String n, boolean First){
        if(!n.equalsIgnoreCase(NULL_STRING)){

            if(First){
                if(top){
                    if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof  TileEntitySafe){
                        TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                        tile.SetOwner(n, false);

                    }
                }else if (!top){

                    if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof  TileEntitySafe){
                        TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

                        tile.SetOwner(n, false);


              }

                }


            }

        Placer = n;
            Economy.NETWORK_MANAGER.sendPacketToAllAround(new SyncSafeOwnerPacket(xCoord, yCoord, zCoord, n), worldObj.getWorldInfo().getVanillaDimension(), xCoord, yCoord, zCoord, 100);
        }
    }


    public void updateEntity(){
        if(!Placer.equalsIgnoreCase(NULL_STRING)){
           // System.out.println(Placer + " : " + worldObj.isRemote);


        }
    }


    public TileEntitySafe(){
        Items = new ItemStack[this.getSizeInventory()];


    }

    ItemStack[] Items;


    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setBoolean("IsTop", top);
        nbt.setString("Pl", Placer);


        NBTTagList Items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); i++){

            ItemStack stack = getStackInSlot(i);
            if(stack != null){

                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte)i);
                stack.writeToNBT(item);
                Items.appendTag(item);
            }
        }


        nbt.setTag("Items", Items);



    }

    public void readFromNBT(NBTTagCompound nbt)
    {

        super.readFromNBT(nbt);
        top = nbt.getBoolean("IsTop");
        Placer = nbt.getString("Pl");




        NBTTagList nbttaglist = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        Items = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < Items.length)
            {
                this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
            }
        }




    }

    @Override
    public int getSizeInventory() {
        return 54;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return Items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        ItemStack itemstack = getStackInSlot(i);

        if(itemstack != null){

            if(itemstack.stackSize <= j){

                setInventorySlotContents(i, null);
            }else{

                itemstack = itemstack.splitStack(j);

            }

        }
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack item = getStackInSlot(i);

        setInventorySlotContents(i, null);
        return item;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

        Items[i] = itemstack;

        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
            itemstack.stackSize = getInventoryStackLimit();

        }


    }



    public void InvChanged(){

        if(top){
            if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                tile.Items = Items;
            }
        }else{

            if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

                tile.Items = Items;
            }
        }




    }

    @Override
    public String getInventoryName() {
        return "Safe";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 128;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return var1.getDistanceSq(xCoord, yCoord, zCoord) <= 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return var2.getItem() instanceof CurrencyItem;
    }




}

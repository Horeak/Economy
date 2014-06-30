package com.eco.Economy.TileEntitys;


import com.eco.Economy.Items.Currency.CurrencyItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntitySafe extends TileEntity implements IInventory {



    public boolean top;

    public boolean Open;

    public float DoorRotate;

    public int TotalAmountStored;

    public String Placer = NULL_STRING;

    public static String NULL_STRING ="ERROR";
    public static String EMPTY_GUI_STRING = "ERROR_EMPTY_OWNER";

   public void updateEntity(){


      TileEntitySafe tile = null;

       if(top){
           if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntitySafe){
               tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
           }
       }else{
           if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntitySafe){
               tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
           }
       }

       if(tile != null) {

           if (Open || tile.Open) {

               if (DoorRotate > -2) {
                   DoorRotate -= 0.03;
               }

           } else if (!Open && !tile.Open) {

               if (DoorRotate < 0)
                   DoorRotate += 0.03;
           }

       }


   }

    public String GetGuiOwner(){
        if(!Placer.equalsIgnoreCase(NULL_STRING))
        return Placer;
        else
            return EMPTY_GUI_STRING;
    }

    public void setOpen(){
        Open = true;

        if(top){
            if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                tile.Open = true;

            }

        }else{
            if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

                tile.Open = true;

            }

        }
    }

    public void setClosed(){
        Open = false;

        if(top){
            if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                tile.Open = false;

            }

        }else{
            if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

                tile.Open = false;

            }

        }
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
        nbt.setInteger("Amount", TotalAmountStored);


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
        TotalAmountStored = nbt.getInteger("Amount");




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




    public int GetAmount(){
        return TotalAmountStored;
    }

    public void SetAmount(int i){
        TotalAmountStored = i;
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


    public void UpdateAmount(){
        TotalAmountStored = 0;

        for(int i = 0; i < Items.length; i++){
            if(Items[i] != null && Items[i].getItem() != null && Items[i].getItem() instanceof CurrencyItem){
                CurrencyItem item = (CurrencyItem)Items[i].getItem();
                TotalAmountStored += (item.Value() * Items[i].stackSize);

            }
        }
    }


    public void InvChanged(){

        UpdateAmount();


        if(top){
            if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

                tile.Items = Items;

                tile.UpdateAmount();

            }
        }else{

            if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntitySafe){
                TileEntitySafe tile = (TileEntitySafe)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

                tile.Items = Items;

                tile.UpdateAmount();

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

        setOpen();

    }

    @Override
    public void closeInventory() {
        setClosed();

    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return var2.getItem() instanceof CurrencyItem;
    }




}

package com.eco.Economy.TileEntitys;

import MiscUtils.Network.PacketHandler;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.PacketTileWithItemUpdate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPlayerShop extends TileEntityInvBase {
    public TileEntityPlayerShop() {
        super(12, "PlayerShop", 64);
    }

    public String BlockCreator;

    public int InStock = 0;
    public boolean IsReady;
    public int AvSpace = 0;


    public void updateEntity() {
        if(!worldObj.isRemote) {

            InStock = 0;

            if (this.getStackInSlot(1) != null) {
                for (int i = 0; i < 4; i++) {
                    if (this.getStackInSlot(i + 2) != null && this.getStackInSlot(i + 2).getItem() == this.getStackInSlot(1).getItem()) {
                        InStock += this.getStackInSlot(i + 2).stackSize;
                    }
                }
            }

            AvSpace = 0;
            for (int i = 0; i < 4; i++) {

                if (this.getStackInSlot(i + 6) == null) {
                    AvSpace += 64;
                } else {
                    AvSpace += 64 - this.getStackInSlot(i + 6).stackSize;
                }


            }

            IsReady = false;

            if (this.getStackInSlot(0) != null && this.getStackInSlot(1) != null) {
                if (InStock >= this.getStackInSlot(0).stackSize && AvSpace >= this.getStackInSlot(1).stackSize) {
                    IsReady = true;
                } else {
                    IsReady = false;
                }
            } else {
                IsReady = false;
            }




            if (IsReady) {
                if (this.getStackInSlot(11) == null && InStock >= this.getStackInSlot(1).stackSize && AvSpace >= this.getStackInSlot(0).stackSize)
                    if (this.getStackInSlot(10) != null && this.getStackInSlot(10).getItem() == this.getStackInSlot(0).getItem() && this.getStackInSlot(10).stackSize >= this.getStackInSlot(0).stackSize) {
                        this.decrStackSize(10, this.getStackInSlot(0).stackSize);
                        int ToDecr = this.getStackInSlot(1).stackSize;

                        for (int i = 0; i < 4; i++) {
                            if (this.getStackInSlot(i + 2) != null && this.getStackInSlot(i + 2).getItem() == this.getStackInSlot(1).getItem()) {
                                System.out.println("t");
                                if (this.getStackInSlot(i + 2).stackSize > ToDecr) {
                                    this.decrStackSize(i + 2, ToDecr);
                                    InStock -= ToDecr;
                                    break;

                                } else if (this.getStackInSlot(i + 2).stackSize <= ToDecr) {
                                    ToDecr -= this.getStackInSlot(i + 2).stackSize;
                                    InStock -= this.getStackInSlot(i + 2).stackSize;
                                    this.setInventorySlotContents(i + 2, null);
                                    continue;

                                }

                            }

                        }


                        int t = this.getStackInSlot(0).copy().stackSize;

                        for(int i = 0; i < 4; i++){

                            ItemStack tmpStack = this.getStackInSlot(i+6);

                            if(tmpStack == null){
                                this.setInventorySlotContents(i+6, this.getStackInSlot(0).copy());
                                AvSpace -= t;
                                break;


                            }else if (tmpStack != null && tmpStack.stackSize < 64){
                                if(tmpStack.stackSize + t > 64){
                                    tmpStack.stackSize = 64;
                                    t -= tmpStack.stackSize;
                                    continue;
                                }else{
                                    tmpStack.stackSize += t;
                                    t = 0;
                                    AvSpace -= t;
                                    break;

                                }

                            }


                        }

                        this.setInventorySlotContents(11, this.getStackInSlot(1).copy());

                    }


            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        BlockCreator = compound.getString("Creator");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setString("Creator", BlockCreator);
    }

    @Override
    public Packet getDescriptionPacket() {

        ItemStack itemStack = getStackInSlot(1);

        if (itemStack != null && itemStack.stackSize > 0)
            return PacketHandler.GetPacket(new PacketTileWithItemUpdate(xCoord, yCoord, zCoord, ForgeDirection.UP, (byte) 0, null, itemStack.getItem().getIdFromItem(itemStack.getItem()), itemStack.getItemDamage(), itemStack.stackSize, 0), Economy.Utils.channels);
        else
            return super.getDescriptionPacket();
    }
}

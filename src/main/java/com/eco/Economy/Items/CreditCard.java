package com.eco.Economy.Items;

import com.eco.Economy.Lib.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

public class CreditCard extends Item {

    public String getItemStackDisplayName(ItemStack stack)
    {
        return EnumChatFormatting.GOLD + StatCollector.translateToLocal(stack.getUnlocalizedName() + ".name");
    }

    public void addInformation(ItemStack Stack, EntityPlayer Player, List List, boolean par4) {

        if(Stack.getTagCompound() != null){
            String Owner = Stack.getTagCompound().getString("CardOwner");

            if(Owner != null && !Owner.equalsIgnoreCase("")) {
                List.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("itemDesc.creditcard.owner").replace("$owner$", EnumChatFormatting.BLUE + Owner));

                if(Player.getCommandSenderName().equalsIgnoreCase(Owner)){
                    List.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal("itemDesc.creditcard.pin").replace("$code$", EnumChatFormatting.BLUE + Stack.getTagCompound().getString("PinCode")));

                }else{
                    List.add(EnumChatFormatting.RED + StatCollector.translateToLocal("itemDesc.creditcard.pinInvalid"));
                }

            }else
                List.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("itemDesc.creditcard.ownerNone"));
        }else{
            List.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("itemDesc.creditcard.ownerNone"));
        }

    }


    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {

        if(entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)entity;

            if(stack.getTagCompound() == null)
                stack.setTagCompound(new NBTTagCompound());

            if(stack.getTagCompound().getString("CardOwner") == null || stack.getTagCompound().getString("CardOwner").equalsIgnoreCase("")){
                stack.getTagCompound().setString("CardOwner", player.getCommandSenderName());
            }

            if(stack.getTagCompound().getString("PinCode") == null || stack.getTagCompound().getString("PinCode").equalsIgnoreCase("")){
                stack.getTagCompound().setString("PinCode", MoneyUtils.GenerateRandomPin() + "");
            }


        }

    }

}

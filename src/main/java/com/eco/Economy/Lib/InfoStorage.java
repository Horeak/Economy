package com.eco.Economy.Lib;

import com.eco.Economy.Network.*;
import com.eco.Economy.Network.Packets.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;

import java.util.*;

public class InfoStorage implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "EconomyPlayerInfoStorage";

    private final EntityPlayer player;

    private int Money = MoneyUtils.StarterMoney;




    public InfoStorage(EntityPlayer player)
    {
        this.player = player;


    }


    public void SetRandomPin(){

        Random Rand = new Random();
        int x = 0;

    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(InfoStorage.EXT_PROP_NAME, new InfoStorage(player));
    }


    public static final InfoStorage get(EntityPlayer player)
    {
        return (InfoStorage) player.getExtendedProperties(EXT_PROP_NAME);
    }


    @Override
    public void saveNBTData(NBTTagCompound compound)
    {

        NBTTagCompound properties = new NBTTagCompound();
        properties.setInteger("Money", this.Money);

        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
        this.Money = properties.getInteger("Money");
    }

    @Override
    public void init(Entity entity, World world)
    {
    }




    public void SetMoney(int Amount){
        Money = Amount;

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), player);
    }

    public void AddMoney(int Amount)
    {
        Money += (Amount * (MoneyUtils.Multiplier));

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), player);
    }

    public void RemoveMoney(int Amount){

        Money -= (Amount * (MoneyUtils.Multiplier));
        if(Money < 0)
            Money = 0;

        PacketHandler.sendToPlayer(new SyncPlayerPropsPacket(player), player);

    }

    public int GetMoney(){
        return Money;
    }

    public void SendMoneyToPlayer(EntityPlayer To, int Amount){
        RemoveMoney(Amount);
        get(To).AddMoney(Amount);
    }




}
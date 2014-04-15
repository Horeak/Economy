package com.eco.Economy.Lib;

import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.Packets.SyncPlayerPropsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.ArrayList;
import java.util.Random;

public class InfoStorage implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "EconomyPlayerInfoStorage";

    private final EntityPlayer player;

    private int Money;
    private int PlayerBankPin;




    public InfoStorage(EntityPlayer player)
    {
        Random Rand = new Random();

        this.player = player;
        this.Money = MoneyUtils.StarterMoney;
        this.PlayerBankPin = Rand.nextInt(MoneyUtils.MaxPinValue);

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
        properties.setInteger("Pin", this.PlayerBankPin);

        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
        this.Money = properties.getInteger("Money");
        this.PlayerBankPin = properties.getInteger("Pin");
    }

    @Override
    public void init(Entity entity, World world)
    {
    }

    public void SetMoney(int Amount){
        Money = Amount;

        Economy.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
    }

    public void AddMoney(int Amount)
    {
        Money += (Amount * (MoneyUtils.Multiplier));

        Economy.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
    }

    public void RemoveMoney(int Amount){

        Money -= (Amount * (MoneyUtils.Multiplier));
        if(Money < 0)
            Money = 0;

        Economy.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);

    }

    public int GetMoney(){
        return Money;
    }

    public void SendMoneyToPlayer(EntityPlayer From, EntityPlayer To, int Amount){
        get(From).RemoveMoney(Amount);
        get(To).AddMoney(Amount);
    }


    public int GetPinCode(){
        return PlayerBankPin;
    }


}
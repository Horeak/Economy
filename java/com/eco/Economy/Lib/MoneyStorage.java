package com.eco.Economy.Lib;

import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.Packets.SyncPlayerPropsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MoneyStorage implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "ExtendedPlayerMoneyStorage";

    private final EntityPlayer player;

    private int Money;
    private boolean Joined;


    public MoneyStorage(EntityPlayer player)
    {
        this.player = player;
        this.Money = MoneyUtils.StarterMoney;

    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(MoneyStorage.EXT_PROP_NAME, new MoneyStorage(player));
    }


    public static final MoneyStorage get(EntityPlayer player)
    {
        return (MoneyStorage) player.getExtendedProperties(EXT_PROP_NAME);
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


}
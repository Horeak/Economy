package com.eco.Economy.Main;


import com.eco.Economy.Blocks.*;
import com.eco.Economy.Event.*;
import com.eco.Economy.Gui.*;
import com.eco.Economy.Items.*;
import com.eco.Economy.Lib.*;
import com.eco.Economy.Network.*;
import com.eco.Economy.Proxies.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraftforge.common.*;
import net.minecraftforge.common.config.*;

import java.io.*;
import java.util.*;


@Mod(modid = ModInfo.ModId, name = ModInfo.ModName, version = ModInfo.ModVersion)
public class Economy {

    @Mod.Instance(ModInfo.ModId)
    public static Economy instance = new Economy();


    @SidedProxy(clientSide = "com.eco.Economy.Proxies.ClientProxy", serverSide = "com.eco.Economy.Proxies.ServerProxy")
    public static ServerProxy proxy;



    public static net.minecraft.creativetab.CreativeTabs ModTab = new net.minecraft.creativetab.CreativeTabs("tabEconomy")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
           return ItemBlock.getItemFromBlock(ModBlockRegistry.ATM);
        }

    };


    public static Configuration config;

    public static EnumMap<Side, FMLEmbeddedChannel> channels;

    public static ChannelHandler handler = new ChannelHandler(ModInfo.Channel);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {


        config = new Configuration(new File(event.getModConfigurationDirectory() + "/tm1990's mods/Economy.cfg"));


        if(event.getSide() == Side.CLIENT){
        MoneyUtils.MoneyMark = config.get("Client Settings", "What sign should be used for money?", "$").getString();
        MoneyUtils.TextArea = config.get("Client Settings", "Where on the screen should the money be showed?  top_right = 1  top_left = 2  bottom_right = 3  bottom_left = 4  Mode", 1).getInt();
        MoneyUtils.CurrencyName = config.get("Client Settings", "What should the currency be called? (null for nothing)(default=null)", "null").getString();

        }




        MoneyUtils.Multiplier = config.get("Server Settings", "What should be the multiplier for money? (used for changing currency)", 1).getInt();
        MoneyUtils.StarterMoney = config.get("Server Settings", "What amount of money should new players start with?", 1000).getInt();
        MoneyUtils.MaxMoneyTransfer = config.get("Server Settings", "What should the max amount of money being transferred at once be?", 100000).getInt();
        MoneyUtils.MaxPinLength = config.get("Server Settings", "What should the max length of the ban pin code be?(Max 24)", 4).getInt();








        if(MoneyUtils.Multiplier < 1)
            MoneyUtils.Multiplier = 1;

        config.save();


        PacketHandler.RegisterPackets();
        channels = getNewChannelHandler(handler.channel);

        ModBlockRegistry.Register();
        ModItemRegistry.Register();

        proxy.RegisterClientTick();
        proxy.RegisterServerTick();

        proxy.RegisterRenders();


        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());
        MinecraftForge.EVENT_BUS.register(new OnPlayerRespawn());

        if(event.getSide() == Side.SERVER)
            RegisterServerEvents();

        if(event.getSide() == Side.CLIENT)
            RegisterClientEvents();



    }




    @EventHandler
    public void Init(FMLInitializationEvent event){

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());


    }



    @EventHandler
    public void PostInit(FMLPostInitializationEvent event){



    }


    public void RegisterServerEvents(){


        FMLCommonHandler.instance().bus().register(proxy.tickHandlerServer);

        MinecraftForge.EVENT_BUS.register(new PreventBlockBreakEvent());




    }

    public void RegisterClientEvents(){


        MinecraftForge.EVENT_BUS.register((new MoneyOverlay()));

        FMLCommonHandler.instance().bus().register(proxy.tickHandlerClient);

    }


    public static EnumMap<Side, FMLEmbeddedChannel> getNewChannelHandler(String modId)
    {

        EnumMap<Side, FMLEmbeddedChannel> handlers = NetworkRegistry.INSTANCE.newChannel(modId, handler);

        ChannelHandler.PacketExecuter executer = new ChannelHandler.PacketExecuter();

        for(Map.Entry<Side, FMLEmbeddedChannel> e : handlers.entrySet())
        {
            FMLEmbeddedChannel channel = e.getValue();
            String codec = channel.findChannelHandlerNameForType(ChannelHandler.class);
            channel.pipeline().addAfter(codec, "PacketExecuter", executer);
        }

        return handlers;
    }


    public static void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound == null) {

                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }

            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

            if (!nbtTagCompound.hasKey(Strings.NBT_ITEM_DISPLAY)) {
                nbtTagCompound.setTag(Strings.NBT_ITEM_DISPLAY, colourTagCompound);
            }

            colourTagCompound.setInteger(Strings.NBT_ITEM_COLOR, color);
        }
    }

    public static int getColor(ItemStack itemStack) {

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
            return Integer.parseInt("ffffff", 16);
        else {

            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt("ffffff", 16) : displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR) ? displayTagCompound.getInteger(Strings.NBT_ITEM_COLOR) : Integer.parseInt("ffffff", 16);
        }
    }

    }

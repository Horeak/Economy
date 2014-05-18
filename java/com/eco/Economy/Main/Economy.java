package com.eco.Economy.Main;


import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.Event.EntityConstructingEvent;
import com.eco.Economy.Event.JoinWorld;
import com.eco.Economy.Event.OnPlayerRespawn;
import com.eco.Economy.Event.PreventBlockBreakEvent;
import com.eco.Economy.Gui.GuiHandler;
import com.eco.Economy.Gui.MoneyOverlay;
import com.eco.Economy.Items.ModItemRegistry;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Lib.MoneyUtils;
import com.eco.Economy.Network.PacketPipeline;
import com.eco.Economy.Network.Simple.NetworkManager;
import com.eco.Economy.Proxies.ServerProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.File;


@Mod(modid = ModInfo.ModId, name = ModInfo.ModName, version = ModInfo.ModVersion)
public class Economy {

    @Mod.Instance(ModInfo.ModId)
    public static Economy instance = new Economy();


    @SidedProxy(clientSide = "com.eco.Economy.Proxies.ClientProxy", serverSide = "com.eco.Economy.Proxies.ServerProxy")
    public static ServerProxy proxy;




    public static NetworkManager NETWORK_MANAGER;

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

    public static final PacketPipeline packetPipeline = new PacketPipeline();

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
        MoneyUtils.MaxPinLength = config.get("Server Settings", "What should the max length of the ban pin code be?", 4).getInt();







        if(MoneyUtils.Multiplier < 1)
            MoneyUtils.Multiplier = 1;

        config.save();


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

        packetPipeline.initialise();


        NETWORK_MANAGER = new NetworkManager();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());


    }



    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {

        packetPipeline.postInitialise();


    }


    public void RegisterServerEvents(){


        FMLCommonHandler.instance().bus().register(proxy.tickHandlerServer);

        MinecraftForge.EVENT_BUS.register(new PreventBlockBreakEvent());




    }

    public void RegisterClientEvents(){


        MinecraftForge.EVENT_BUS.register((new MoneyOverlay()));

        FMLCommonHandler.instance().bus().register(proxy.tickHandlerClient);

    }




    }

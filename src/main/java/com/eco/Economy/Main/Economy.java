package com.eco.Economy.Main;


import MiscUtils.Network.ChannelUtils;
import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.Event.EntityConstructingEvent;
import com.eco.Economy.Event.JoinWorld;
import com.eco.Economy.Event.OnPlayerRespawn;
import com.eco.Economy.Event.PreventBlockBreakEvent;
import com.eco.Economy.Gui.GuiHandler;
import com.eco.Economy.Gui.MoneyOverlay;
import com.eco.Economy.Items.ModItemRegistry;
import com.eco.Economy.Network.AtmFinishPacket;
import com.eco.Economy.Network.PacketTileUpdate;
import com.eco.Economy.Network.PacketTileWithItemUpdate;
import com.eco.Economy.Network.SyncPlayerPropsPacket;
import com.eco.Economy.Network.SyncSafeOwnerPacket;
import com.eco.Economy.Utils.Config;
import com.eco.Economy.Utils.ModInfo;
import com.eco.Economy.Utils.MoneyUtils;
import com.eco.Economy.Utils.Proxies.ServerProxy;
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


@Mod(modid = ModInfo.ModId, name = ModInfo.ModName, version = ModInfo.ModVersion, dependencies = "required-after:MiscUtils")
public class Economy {

    @Mod.Instance(ModInfo.ModId)
    public static Economy instance = new Economy();


    @SidedProxy(clientSide = "com.eco.Economy.Utils.Proxies.ClientProxy", serverSide = "com.eco.Economy.Utils.Proxies.ServerProxy")
    public static ServerProxy proxy;


    public static Config config;

    public static net.minecraft.creativetab.CreativeTabs ModTab = new net.minecraft.creativetab.CreativeTabs("tabEconomy")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
           return ItemBlock.getItemFromBlock(ModBlockRegistry.ATM);
        }

    };


    public static ChannelUtils Utils;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        Utils = new ChannelUtils(ModInfo.Channel, ModInfo.ModId);
        RegisterPackets();



        config = new Config(event.getModConfigurationDirectory() + "");


        if(MoneyUtils.Multiplier < 1)
            MoneyUtils.Multiplier = 1;



        ModBlockRegistry.Register();
        ModItemRegistry.Register();

        proxy.RegisterClientTick();
        proxy.RegisterServerTick();

        proxy.RegisterRenders();


        MinecraftForge.EVENT_BUS.register(new EntityConstructingEvent());
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


    public static void RegisterPackets(){


        Utils.handler.RegisterPacket(SyncPlayerPropsPacket.class);
        Utils.handler.RegisterPacket(SyncSafeOwnerPacket.class);
        Utils.handler.RegisterPacket(AtmFinishPacket.class);

        Utils.handler.RegisterPacket(PacketTileUpdate.class);
        Utils.handler.RegisterPacket(PacketTileWithItemUpdate.class);



    }

    public void RegisterServerEvents(){


        FMLCommonHandler.instance().bus().register(proxy.tickHandlerServer);

        MinecraftForge.EVENT_BUS.register(new PreventBlockBreakEvent());
        MinecraftForge.EVENT_BUS.register(new JoinWorld());




    }

    public void RegisterClientEvents(){


        MinecraftForge.EVENT_BUS.register((new MoneyOverlay()));

        FMLCommonHandler.instance().bus().register(proxy.tickHandlerClient);

    }






    }

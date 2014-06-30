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
import com.eco.Economy.Lib.Strings;
import com.eco.Economy.Network.ChannelHandler;
import com.eco.Economy.Network.PacketHandler;
import com.eco.Economy.Proxies.ServerProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;


@Mod(modid = ModInfo.ModId, name = ModInfo.ModName, version = ModInfo.ModVersion, guiFactory = "com.eco.Economy.Lib.Config.GuiConfigFactory")
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


            Property textArea = config.get("Client Settings", "Where on the screen should the money be showed?", 1);
            textArea.comment = "top_right = 1 \\n  top_left = 2  bottom_right = 3  bottom_left = 4  Mode";


        MoneyUtils.TextArea = config.get("Client Settings", "Where on the screen should the money be showed?", 1).getInt();
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

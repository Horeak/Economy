package com.eco.Economy.Main;


import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.Event.EntityConstructingEvent;
import com.eco.Economy.Event.JoinWorld;
import com.eco.Economy.Event.OnPlayerRespawn;
import com.eco.Economy.Event.PreventBlockBreakEvent;
import com.eco.Economy.Gui.GuiHandler;
import com.eco.Economy.Gui.MoneyOverlay;
import com.eco.Economy.Items.ModItemRegistry;
import com.eco.Economy.Lib.Config.ConfigUtils;
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


    public static EnumMap<Side, FMLEmbeddedChannel> channels;

    public static ChannelHandler handler = new ChannelHandler(ModInfo.Channel);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        ConfigUtils.IntitConfig(event.getModConfigurationDirectory() + "/tm1990's mods/Economy.cfg");



        if(MoneyUtils.Multiplier < 1)
            MoneyUtils.Multiplier = 1;


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

        FMLCommonHandler.instance().bus().register(new ConfigUtils());

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

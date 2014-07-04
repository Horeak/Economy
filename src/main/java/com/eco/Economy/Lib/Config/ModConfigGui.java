package com.eco.Economy.Lib.Config;

import com.eco.Economy.Lib.ModInfo;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class ModConfigGui extends GuiConfig {
    public ModConfigGui(GuiScreen parent) {
        super(parent,
                getConfigElements(),
                ModInfo.ModId, false, false, GuiConfig.getAbridgedConfigPath(ConfigUtils.GetConfig().toString()));
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        List<IConfigElement> InGameConfigs = new ArrayList<IConfigElement>();

        list.add(new DummyCategoryElement(ConfigUtils.CATEGORY_CLIENT_SETTINGS, "config.el.Client", ClientSettingsConfigEntry.class));
        list.add(new DummyCategoryElement(ConfigUtils.CATEGORY_CLIENT_SETTINGS, "config.el.Server", ServerSettingsConfigEntry.class));
        list.add(new DummyCategoryElement(ConfigUtils.CATEGORY_BLOCKS, "config.el.Blocks", BlocksSettingsConfigEntry.class));
        list.add(new DummyCategoryElement(ConfigUtils.CATEGORY_ITEMS, "config.el.Items", ItemsSettingsConfigEntry.class));


        return list;
    }

    }


package com.eco.Economy.Lib.Config;

import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Main.Economy;
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
                ModInfo.ModId, false, false, GuiConfig.getAbridgedConfigPath(Economy.config.toString()));
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyCategoryElement("client settings", "config.el.Client", ClientSettingsConfigEntry.class));
        list.add(new DummyCategoryElement("server settings", "config.el.Server", ServerSettingsConfigEntry.class));

        return list;
    }

    }


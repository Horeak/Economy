package com.eco.Economy.Lib.Config;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ItemsSettingsConfigEntry extends CategoryEntry
{
    public ItemsSettingsConfigEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
    {
        super(owningScreen, owningEntryList, prop);
    }

    @Override
    protected GuiScreen buildChildScreen()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        list.addAll((new ConfigElement(ConfigUtils.GetConfig().getCategory(ConfigUtils.CATEGORY_ITEMS.toLowerCase()))).getChildElements());

        return new GuiConfig(this.owningScreen, list, this.owningScreen.modID, "items",
                this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                true || this.owningScreen.allRequireMcRestart,
                GuiConfig.getAbridgedConfigPath(ConfigUtils.GetConfig().toString()),
                I18n.format("config.el.Items"));
    }
}
package com.eco.Economy.Gui;

import com.eco.Economy.Container.*;
import com.eco.Economy.Utils.*;
import com.eco.Economy.TileEntitys.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

public class GuiPlayerShopUser extends GuiContainer {


    private static final ResourceLocation Texture = new ResourceLocation(ModInfo.ModTextures, "textures/gui/PlayerShopGuiUser.png");


    TileEntityPlayerShop tile;

    public GuiPlayerShopUser(InventoryPlayer InvPlayer, TileEntityPlayerShop tile) {
        super(new ContainerPlayerShopUser(InvPlayer, tile));
        xSize = 176;
        ySize = 166;

        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {


        fontRendererObj.drawString(StatCollector.translateToLocal("gui.playershop.text.payment"), 33, 60, 0xffffff);
        fontRendererObj.drawString(StatCollector.translateToLocal("gui.playershop.text.offer"), 111, 60, 0xffffff);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;


    }

}
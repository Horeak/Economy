package com.eco.Economy.Gui;

import com.eco.Economy.Container.SafeInvContainer;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiSafe extends GuiContainer {


    private static final ResourceLocation Texture = new ResourceLocation("textures/gui/container/generic_54.png");



    TileEntitySafe tile;

        public GuiSafe(InventoryPlayer InvPlayer, TileEntitySafe tile) {
            super(new SafeInvContainer(InvPlayer, tile));
            xSize = 176;
            ySize = 222;

            this.tile = tile;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {

            fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);

            fontRendererObj.drawString(StatCollector.translateToLocal("gui.safe"), 7, 4, 4210752);


        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float f, int X, int Y)
        {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

            Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

            int x = (this.width - this.xSize) / 2;
            int y = (this.height - this.ySize) / 2;


            //TODO Readd later
           // fontRendererObj.drawString(StatCollector.translateToLocal("gui.safe.Owner") + tile.GetGuiOwner(), x + 40,  y + 4, 4210752);




        }

}


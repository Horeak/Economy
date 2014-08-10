package com.eco.Economy.Gui;

import MiscUtils.Network.PacketHandler;
import com.eco.Economy.Container.AtmContainer;
import com.eco.Economy.Items.CreditCard;
import com.eco.Economy.Main.Economy;
import com.eco.Economy.Network.AtmFinishPacket;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.Utils.ChatMessageHandler;
import com.eco.Economy.Utils.InfoStorage;
import com.eco.Economy.Utils.ModInfo;
import com.eco.Economy.Utils.MoneyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiATM extends GuiContainer {

     private final ResourceLocation Texture = new ResourceLocation(ModInfo.ModTextures, "textures/gui/AtmGui.png");

    TileEntityATM tile;
    GuiTextField InfoArea;

    int CurrentNumber;
    String Mode;

    String ShowText = "";



    public GuiATM(InventoryPlayer InvPlayer, TileEntityATM tile) {
        super(new AtmContainer(InvPlayer, tile));
        this.xSize = 176;
        this.ySize = 255;
        this.tile = tile;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();




        if(tile.getStackInSlot(0) != null){
            if(tile.getStackInSlot(0).getItem() instanceof CreditCard){

                if(Mode == "money"){

                    InfoArea.setText(StatCollector.translateToLocal("gui.atm.text.money") + " " + CurrentNumber);


                }else

                if(MoneyUtils.GetCreditCardOwner(tile.getStackInSlot(0)).equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getCommandSenderName())) {
                    InfoArea.setText(StatCollector.translateToLocal("gui.atm.text.enterPin") + " " + ShowText);
                    Mode = "pin";

                }else{
                    InfoArea.setText(StatCollector.translateToLocal("itemDesc.creditcard.pinInvalid"));
                    Mode = "invalid";
                }

            }

        }else{
            Mode = "";
            ShowText = "";
            CurrentNumber = 0;
            InfoArea.setText("");
        }


    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        InfoArea.drawTextBox();
        InfoArea.setMaxStringLength(24);

        fontRendererObj.drawString(EnumChatFormatting.GRAY + "Economy ATM V.1", 16, 17, 0xffffff);

        if(Mode == "money")
            fontRendererObj.drawString(EnumChatFormatting.GRAY + StatCollector.translateToLocal("gui.atm.text.currentAmount") + " " + InfoStorage.get(mc.thePlayer).GetMoney() + MoneyUtils.MoneyMark, 16, 44, 0xffffff);


    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int X, int Y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(Texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);


    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();


        InfoArea = new GuiTextField(fontRendererObj, 12, 14, 152, 40);

        buttonList.add(new GuiButton(1, guiLeft + 12, guiTop + 61, 30, 17, EnumChatFormatting.WHITE + "1"));
        buttonList.add(new GuiButton(2, guiLeft + 46, guiTop + 61, 30, 17, EnumChatFormatting.WHITE + "2"));
        buttonList.add(new GuiButton(3, guiLeft + 80, guiTop + 61, 30, 17, EnumChatFormatting.WHITE + "3"));

        buttonList.add(new GuiButton(4, guiLeft + 12, guiTop + 80, 30, 17, EnumChatFormatting.WHITE + "4"));
        buttonList.add(new GuiButton(5, guiLeft + 46, guiTop + 80, 30, 17, EnumChatFormatting.WHITE + "5"));
        buttonList.add(new GuiButton(6, guiLeft + 80, guiTop + 80, 30, 17, EnumChatFormatting.WHITE + "6"));

        buttonList.add(new GuiButton(7, guiLeft + 12, guiTop + 100, 30, 17, EnumChatFormatting.WHITE + "7"));
        buttonList.add(new GuiButton(8, guiLeft + 46, guiTop + 100, 30, 17, EnumChatFormatting.WHITE + "8"));
        buttonList.add(new GuiButton(9, guiLeft + 80, guiTop + 100, 30, 17, EnumChatFormatting.WHITE +  "9"));

        buttonList.add(new GuiButton(0, guiLeft + 46, guiTop + 120, 30, 17, EnumChatFormatting.WHITE + "0"));

        buttonList.add(new GuiButton(10, guiLeft + 12, guiTop + 149, 32, 15, EnumChatFormatting.RED + StatCollector.translateToLocal("gui.atm.buttonCancel")));
        buttonList.add(new GuiButton(11, guiLeft + 48, guiTop + 149, 32, 15, EnumChatFormatting.YELLOW + StatCollector.translateToLocal("gui.atm.buttonClear")));
        buttonList.add(new GuiButton(12, guiLeft + 84, guiTop + 149, 32, 15, EnumChatFormatting.GREEN + StatCollector.translateToLocal("gui.atm.buttonEnter")));

    }

    @Override
    protected void actionPerformed(GuiButton button) {

        if(button.id < 10){

            if(Mode == "pin"){
                ShowText += "*";
                CurrentNumber = (CurrentNumber * 10) + button.id;
            }else if (Mode == "money"){
                if((CurrentNumber * 10) + button.id  <= InfoStorage.get(mc.thePlayer).GetMoney())
                    CurrentNumber = (CurrentNumber * 10) + button.id;
                else
                    CurrentNumber =InfoStorage.get(mc.thePlayer).GetMoney();
            }
        }

        if(button.id == 10){
            Mode = null;
            ShowText = "";
            CurrentNumber = 0;

        }

        if(button.id == 11){
            CurrentNumber /= 10;

            ShowText = "";
            for(int i = 0; i < Integer.toString(CurrentNumber).length(); i++){
                ShowText += "*";
            }

            if(CurrentNumber == 0)
                ShowText = "";

        }

        if(button.id == 12){
            if(Mode == "pin"){
                if(CurrentNumber == Integer.valueOf(MoneyUtils.GetCreditCardPin(tile.getStackInSlot(0)))){
                    Mode = "money";
                    CurrentNumber = 0;
                    ShowText = "";
                }
            }else if (Mode == "money"){

                PacketHandler.sendToServer(new AtmFinishPacket(CurrentNumber, mc.thePlayer), Economy.channels);

                Mode = "";
                CurrentNumber = 0;
                ShowText = "";

                ChatMessageHandler.sendChatToPlayer(mc.thePlayer, StatCollector.translateToLocal("message.atm.moneySent"));

            }



        }

    }


}

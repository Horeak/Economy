package com.eco.Economy.ItemRender;

import com.eco.Economy.Blocks.BlockSafe;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Models.ATMmodel;
import com.eco.Economy.Models.SafeModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class SafeItemRender implements IItemRenderer
{




    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
                return true;
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        SafeModel model = new SafeModel();

        GL11.glPushMatrix();
        GL11.glTranslatef((float) 0.5F, (float) 1.4F, (float) 0.5F);


        Minecraft.getMinecraft().getTextureManager(). bindTexture(BlockSafe.ModelTexture);


        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON){
            GL11.glRotatef((3 * 90F), 0.0F, 1.0F, 0.0F);
        }else
            GL11.glRotatef((2 * 90F), 0.0F, 1.0F, 0.0F);

        model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}

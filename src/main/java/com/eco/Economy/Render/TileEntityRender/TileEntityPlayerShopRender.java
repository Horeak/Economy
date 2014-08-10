package com.eco.Economy.Render.TileEntityRender;

import com.eco.Economy.TileEntitys.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import org.lwjgl.opengl.*;

@SideOnly(Side.CLIENT)
public class TileEntityPlayerShopRender extends TileEntitySpecialRenderer {

    private final RenderItem customRenderItem;

    public TileEntityPlayerShopRender() {
        customRenderItem = new RenderItem() {

            @Override
            public boolean shouldBob() {

                return false;
            };
        };

        customRenderItem.setRenderManager(RenderManager.instance);

    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (- 90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        if(te instanceof TileEntityPlayerShop){
            TileEntityPlayerShop tile = (TileEntityPlayerShop)te;

            GL11.glPushMatrix();


            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            GL11.glPopMatrix();
            GL11.glPushMatrix();

           GL11.glEnable(GL11.GL_LIGHTING);

            if(tile.getWorldObj().getBlockMetadata(tile.xCoord,tile.yCoord,tile.zCoord) == 1) {

                if (tile.getStackInSlot(1) != null) {
                    float scaleFactor = getGhostItemScaleFactor(tile.getStackInSlot(1));
                    float rotationAngle = (float) (520.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

                    EntityItem ghostEntityItem = new EntityItem(te.getWorldObj());
                    ghostEntityItem.hoverStart = 0.0F;
                    ghostEntityItem.setEntityItemStack(tile.getStackInSlot(1));

                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.45F, (float) z + 0.5F);

                    GL11.glScalef(scaleFactor + 0.24F, scaleFactor + 0.24F, scaleFactor + 0.24F);
                    GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

                    customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
                }


            }

            GL11.glPopMatrix();

        }

    }

    private float getGhostItemScaleFactor(ItemStack itemStack) {

        float scaleFactor = 1.0F;

        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemBlock) {
                switch (customRenderItem.getMiniBlockCount(itemStack, (byte)1)) {
                    case 1:
                        return 0.90F;
                    case 2:
                        return 0.90F;
                    case 3:
                        return 0.90F;
                    case 4:
                        return 0.90F;
                    case 5:
                        return 0.80F;
                    default:
                        return 0.90F;
                }
            }
            else {
                switch (customRenderItem.getMiniItemCount(itemStack, (byte)1)) {
                    case 1:
                        return 0.65F;
                    case 2:
                        return 0.65F;
                    case 3:
                        return 0.65F;
                    case 4:
                        return 0.65F;
                    default:
                        return 0.65F;
                }
            }
        }

        return scaleFactor;
    }

}
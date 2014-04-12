package com.eco.Economy.TileEntityRender;

import com.eco.Economy.Blocks.BlockSafe;
import com.eco.Economy.Blocks.ModBlockRegistry;
import com.eco.Economy.Lib.ModInfo;
import com.eco.Economy.Models.ATMmodel;
import com.eco.Economy.Models.SafeModel;
import com.eco.Economy.TileEntitys.TileEntityATM;
import com.eco.Economy.TileEntitys.TileEntitySafe;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntitySafeRender extends TileEntitySpecialRenderer {

    private final SafeModel model;

    public TileEntitySafeRender() {
        this.model = new SafeModel();
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (- 90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        if(te instanceof TileEntitySafe){
            TileEntitySafe tile = (TileEntitySafe)te;

            int meta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);


            bindTexture(BlockSafe.ModelTexture);

            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

            if(tile.hasWorldObj()){

                int Meta = tile.getBlockMetadata();
                int face = Meta == 2 ? 0 : Meta == 3 ? 2 : Meta == 4 ? 3 : Meta == 5 ? 5 : 0;

                GL11.glRotatef((face * 90F), 0.0F, 1.0F, 0.0F);

            }else{
                GL11.glRotatef((2 * 90F), 0.0F, 1.0F, 0.0F);

            }

            boolean Top = te.getWorldObj().getBlock(te.xCoord, te.yCoord - 1, te.zCoord) == ModBlockRegistry.Safe;

            if(!Top)
                this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);



            GL11.glPopMatrix();
            GL11.glPopMatrix();

        }
    }



}
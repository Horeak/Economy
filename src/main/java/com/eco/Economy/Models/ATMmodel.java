package com.eco.Economy.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ATMmodel extends ModelBase
{
    //fields
    //fields
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;

    public ATMmodel()
    {
        textureWidth = 128;
        textureHeight = 128;

        Shape2 = new ModelRenderer(this, 56, 70);
        Shape2.addBox(-6F, 0F, -6F, 12, 16, 12);
        Shape2.setRotationPoint(0F, 8F, 2F);
        Shape2.setTextureSize(128, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 57);
        Shape3.addBox(-3F, 0F, -2F, 6, 3, 4);
        Shape3.setRotationPoint(0F, 6.8F, -0.4F);
        Shape3.setTextureSize(128, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0.6981317F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 47);
        Shape4.addBox(-4F, -3F, -1.5F, 6, 6, 3);
        Shape4.setRotationPoint(1F, 3F, 3F);
        Shape4.setTextureSize(128, 128);
        Shape4.mirror = true;
        setRotation(Shape4, -0.1745329F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 61, 51);
        Shape5.addBox(-6F, -5F, -5F, 12, 11, 6);
        Shape5.setRotationPoint(0F, 2F, 7F);
        Shape5.setTextureSize(128, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 29, 79);
        Shape6.addBox(-5F, -7F, 0F, 10, 14, 1);
        Shape6.setRotationPoint(0F, 16F, -5F);
        Shape6.setTextureSize(128, 128);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 63, 45);
        Shape7.addBox(-6F, 0F, -2F, 12, 1, 3);
        Shape7.setRotationPoint(0F, -3F, 1F);
        Shape7.setTextureSize(128, 128);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 20, 14);
        Shape9.addBox(0F, -4F, -1F, 1, 10, 3);
        Shape9.setRotationPoint(5F, 2F, 0F);
        Shape9.setTextureSize(128, 128);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 20, 0);
        Shape10.addBox(0F, -4F, -1F, 1, 10, 3);
        Shape10.setRotationPoint(-6F, 2F, 0F);
        Shape10.setTextureSize(128, 128);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 29, 17);
        Shape11.addBox(0F, -4F, -1F, 1, 9, 1);
        Shape11.setRotationPoint(5F, 3F, -1F);
        Shape11.setTextureSize(128, 128);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 29, 3);
        Shape12.addBox(0F, -4F, -1F, 1, 9, 1);
        Shape12.setRotationPoint(-6F, 3F, -1F);
        Shape12.setTextureSize(128, 128);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 34, 18);
        Shape13.addBox(0F, -4F, -1F, 1, 8, 1);
        Shape13.setRotationPoint(5F, 4F, -2F);
        Shape13.setTextureSize(128, 128);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 39, 19);
        Shape14.addBox(0F, -4F, -1F, 1, 7, 1);
        Shape14.setRotationPoint(5F, 5F, -3F);
        Shape14.setTextureSize(128, 128);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new ModelRenderer(this, 34, 4);
        Shape15.addBox(0F, -4F, -1F, 1, 8, 1);
        Shape15.setRotationPoint(-6F, 4F, -2F);
        Shape15.setTextureSize(128, 128);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 39, 5);
        Shape16.addBox(0F, -4F, -1F, 1, 7, 1);
        Shape16.setRotationPoint(-6F, 5F, -3F);
        Shape16.setTextureSize(128, 128);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 58, 12);
        Shape17.addBox(-1F, 0F, -1F, 2, 8, 2);
        Shape17.setRotationPoint(4F, 0F, 2F);
        Shape17.setTextureSize(128, 128);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape18 = new ModelRenderer(this, 67, 13);
        Shape18.addBox(-1F, 0F, -1F, 2, 1, 1);
        Shape18.setRotationPoint(4.1F, 2F, 2F);
        Shape18.setTextureSize(128, 128);
        Shape18.mirror = true;
        setRotation(Shape18, -0.4363323F, 0F, 0F);
        Shape19 = new ModelRenderer(this, 21, 57);
        Shape19.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        Shape19.setRotationPoint(2F, 6.4F, 0.5F);
        Shape19.setTextureSize(128, 128);
        Shape19.mirror = true;
        setRotation(Shape19, 0.6981317F, 0F, 0F);
        Shape20 = new ModelRenderer(this, 21, 60);
        Shape20.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        Shape20.setRotationPoint(2F, 7.4F, -0.7F);
        Shape20.setTextureSize(128, 128);
        Shape20.mirror = true;
        setRotation(Shape20, 0.6981317F, 0F, 0F);
        Shape21 = new ModelRenderer(this, 114, 70);
        Shape21.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        Shape21.setRotationPoint(2.2F, 1F, 2F);
        Shape21.setTextureSize(128, 128);
        Shape21.mirror = true;
        setRotation(Shape21, -0.1745329F, 0F, 0F);
        Shape22 = new ModelRenderer(this, 114, 74);
        Shape22.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        Shape22.setRotationPoint(2.2F, 2.4F, 1.8F);
        Shape22.setTextureSize(128, 128);
        Shape22.mirror = true;
        setRotation(Shape22, -0.1745329F, 0F, 0F);
        Shape23 = new ModelRenderer(this, 114, 78);
        Shape23.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        Shape23.setRotationPoint(2.2F, 3.8F, 1.5F);
        Shape23.setTextureSize(128, 128);
        Shape23.mirror = true;
        setRotation(Shape23, -0.1745329F, 0F, 0F);
        Shape24 = new ModelRenderer(this, 66, 39);
        Shape24.addBox(-1F, -1F, -1F, 10, 2, 2);
        Shape24.setRotationPoint(-4F, -1F, 2F);
        Shape24.setTextureSize(128, 128);
        Shape24.mirror = true;
        setRotation(Shape24, 0F, 0F, 0F);
        Shape25 = new ModelRenderer(this, 58, 0);
        Shape25.addBox(-1F, 0F, -1F, 2, 8, 2);
        Shape25.setRotationPoint(-4F, 0F, 2F);
        Shape25.setTextureSize(128, 128);
        Shape25.mirror = true;
        setRotation(Shape25, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape13.render(f5);
        Shape14.render(f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape18.render(f5);
        Shape19.render(f5);
        Shape20.render(f5);
        Shape21.render(f5);
        Shape22.render(f5);
        Shape23.render(f5);
        Shape24.render(f5);
        Shape25.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

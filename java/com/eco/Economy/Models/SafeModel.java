package com.eco.Economy.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SafeModel extends ModelBase
{
    //fields
    ModelRenderer Bottom;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Back;
    ModelRenderer Top;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer Shelf;
    ModelRenderer Door;

    public SafeModel()
    {
        textureWidth = 128;
        textureHeight = 64;
        setTextureOffset("Door.Door", 89, 17);
        setTextureOffset("Door.HandlePart", 118, 17);
        setTextureOffset("Door.Handle", 118, 20);
        setTextureOffset("Door.TumblerBase", 117, 12);
        setTextureOffset("Door.Tumbler", 118, 9);
        setTextureOffset("Door.HingeTop", 120, 25);
        setTextureOffset("Door.HingeBottom", 120, 25);

        Bottom = new ModelRenderer(this, 0, 44);
        Bottom.addBox(-7F, 0F, -7F, 14, 1, 14);
        Bottom.setRotationPoint(0F, 21F, 0F);
        Bottom.setTextureSize(128, 64);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Leg1 = new ModelRenderer(this, 0, 60);
        Leg1.addBox(-1F, -1F, -1F, 2, 2, 2);
        Leg1.setRotationPoint(-6F, 23F, -6F);
        Leg1.setTextureSize(128, 64);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 10, 60);
        Leg2.addBox(-1F, -1F, -1F, 2, 2, 2);
        Leg2.setRotationPoint(6F, 23F, -6F);
        Leg2.setTextureSize(128, 64);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Leg3 = new ModelRenderer(this, 20, 60);
        Leg3.addBox(-1F, -1F, -1F, 2, 2, 2);
        Leg3.setRotationPoint(6F, 23F, 6F);
        Leg3.setTextureSize(128, 64);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0F, 0F);
        Leg4 = new ModelRenderer(this, 30, 60);
        Leg4.addBox(-1F, -1F, -1F, 2, 2, 2);
        Leg4.setRotationPoint(-6F, 23F, 6F);
        Leg4.setTextureSize(128, 64);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, 0F, 0F);
        Back = new ModelRenderer(this, 58, 17);
        Back.addBox(-7F, 0F, 0F, 14, 25, 1);
        Back.setRotationPoint(0F, -4F, 6F);
        Back.setTextureSize(128, 64);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 58, 1);
        Top.addBox(-7F, 0F, -7F, 14, 1, 14);
        Top.setRotationPoint(0F, -5F, 0F);
        Top.setTextureSize(128, 64);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        side1 = new ModelRenderer(this, 29, 5);
        side1.addBox(0F, 0F, -7F, 1, 25, 13);
        side1.setRotationPoint(6F, -4F, 0F);
        side1.setTextureSize(128, 64);
        side1.mirror = true;
        setRotation(side1, 0F, 0F, 0F);
        side2 = new ModelRenderer(this, 0, 5);
        side2.addBox(-1F, 0F, -7F, 1, 25, 13);
        side2.setRotationPoint(-6F, -4F, 0F);
        side2.setTextureSize(128, 64);
        side2.mirror = true;
        setRotation(side2, 0F, 0F, 0F);
        Shelf = new ModelRenderer(this, 59, 46);
        Shelf.addBox(-6F, 0F, -5F, 12, 1, 11);
        Shelf.setRotationPoint(0F, 8F, 0F);
        Shelf.setTextureSize(128, 64);
        Shelf.mirror = true;
        setRotation(Shelf, 0F, 0F, 0F);
        Door = new ModelRenderer(this, "Door");
        Door.setRotationPoint(6F, 8F, -6.8F);
        setRotation(Door, 0F, 0F, 0F);
        Door.mirror = true;
        Door.addBox("Door", -12F, -12F, 0F, 12, 25, 1);
        Door.addBox("HandlePart", -7F, -0.5F, -0.5F, 1, 1, 1);
        Door.addBox("Handle", -7F, -0.5F, -1.5F, 3, 1, 1);
        Door.addBox("TumblerBase", -10.5F, -1F, -0.3F, 2, 2, 1);
        Door.addBox("Tumbler", -10F, -0.5F, -1F, 1, 1, 1);
        Door.addBox("HingeTop", -1.5F, -8F, -0.8F, 2, 1, 1);
        Door.addBox("HingeBottom", -1.5F, 8F, -0.8F, 2, 1, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Bottom.render(f5);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        Back.render(f5);
        Top.render(f5);
        side1.render(f5);
        side2.render(f5);
        Shelf.render(f5);
        Door.render(f5);
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

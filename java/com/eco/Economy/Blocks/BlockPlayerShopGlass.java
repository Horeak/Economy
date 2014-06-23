package com.eco.Economy.Blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.*;

public class BlockPlayerShopGlass extends Block {
    protected BlockPlayerShopGlass() {
        super(Material.glass);
        setHardness(1);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}

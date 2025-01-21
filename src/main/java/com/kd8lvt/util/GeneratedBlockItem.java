package com.kd8lvt.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

public class GeneratedBlockItem extends BlockItem implements GenericModThing {
    Identifier id;

    public <T extends Block & GenericModThing> GeneratedBlockItem(T block) {
        super(block, new Settings());
        this.id = block.id();
    }

    @Override
    public Identifier id() {
        return id;
    }
}

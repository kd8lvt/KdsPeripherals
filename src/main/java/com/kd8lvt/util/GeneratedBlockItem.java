package com.kd8lvt.util;

import com.kd8lvt.api.content.KdThing;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

public class GeneratedBlockItem extends BlockItem implements KdThing {
    Identifier id;

    public <T extends Block & KdThing> GeneratedBlockItem(T block) {
        super(block, new Settings());
        this.id = block.id();
    }

    @Override
    public Identifier id() {
        return id;
    }
}

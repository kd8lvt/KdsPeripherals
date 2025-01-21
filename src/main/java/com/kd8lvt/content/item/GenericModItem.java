package com.kd8lvt.content.item;

import com.kd8lvt.util.GenericModThing;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public abstract class GenericModItem extends Item implements GenericModThing {
    private final Identifier id;
    public GenericModItem(Settings settings, Identifier id) {
        super(settings);
        this.id=id;
    }

    @Override
    public Identifier id() {
        return id;
    }
}

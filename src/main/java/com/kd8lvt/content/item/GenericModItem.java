package com.kd8lvt.content.item;

import com.kd8lvt.registry.GenericModThing;
import com.kd8lvt.util.SingleWriteAtomic;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class GenericModItem extends Item implements GenericModThing {
    SingleWriteAtomic<Identifier> id = new SingleWriteAtomic<>();
    public GenericModItem(Settings settings) {
        super(settings);
    }

    @Override
    public void setId(Identifier id) {
        this.id.set(id);
    }

    @Override
    public Identifier getId() {
        return this.id.get();
    }
}


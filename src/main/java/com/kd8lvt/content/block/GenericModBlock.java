package com.kd8lvt.content.block;

import com.kd8lvt.util.SingleWriteAtomic;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

public abstract class GenericModBlock extends Block {
    public SingleWriteAtomic<Identifier> id = new SingleWriteAtomic<>();
    public GenericModBlock(Settings settings) {
        super(settings);
    }
    public void setId(Identifier id) {this.id.set(id);}
    public Identifier getId() {return this.id.get();}
}

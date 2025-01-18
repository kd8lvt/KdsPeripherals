package com.kd8lvt.content.item.tooltips;

import com.kd8lvt.registry.GenericModThing;
import com.kd8lvt.util.SingleWriteAtomic;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModTooltip implements GenericModThing {
    private final SingleWriteAtomic<Identifier> id = new SingleWriteAtomic<>();
    public Text text;

    public ModTooltip(Text tooltipText) {
        text=tooltipText;
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

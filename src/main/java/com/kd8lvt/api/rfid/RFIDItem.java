package com.kd8lvt.api.rfid;

import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.registry.ModTooltips;
import com.kd8lvt.util.GenericModThing;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public abstract class RFIDItem<D extends RFIDComponent> extends Item implements GenericModThing {
    public final Identifier id;

    public RFIDItem(ComponentType<D> type, D defaultComponent,Identifier id) {
        this(new Settings(),type,defaultComponent,id);
    }

    public RFIDItem(Settings settings, ComponentType<D> type, D defaultComponent,Identifier id) {
        super(settings.component(type,defaultComponent));
        this.id = id;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        doTooltip(stack, context, tooltip, type);
    }
    public void doTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.getComponents().stream().anyMatch((comp)->comp.value() instanceof RFIDComponent)) {
            int size = 0;
            for (Component<?> comp : stack.getComponents()) {
                if (comp.value() instanceof RFIDComponent rfid) size += rfid.comp.getSizeInBytes();
            }
            tooltip.add(Text.translatable(ModTooltips.STORED_DATA,size));
        }
    }

    @Override
    public Identifier id() {
        return id;
    }
}

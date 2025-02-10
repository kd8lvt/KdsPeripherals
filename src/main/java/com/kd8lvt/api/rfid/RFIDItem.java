package com.kd8lvt.api.rfid;

import com.kd8lvt.api.content.item.KdItem;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.registry.ModTranslations;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public abstract class RFIDItem<D extends RFIDComponent> extends KdItem {
    public final ComponentType<D> component_type;
    public final D default_component;
    public RFIDItem(ComponentType<D> type, D defaultComponent,Identifier id) {
        this(new Settings(),type,defaultComponent,id);
    }

    public RFIDItem(Settings settings, ComponentType<D> type, D defaultComponent,Identifier id) {
        super(settings.component(type,defaultComponent),id);
        this.component_type=type;
        this.default_component=defaultComponent;
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
            tooltip.add(Text.translatable(ModTranslations.STORED_DATA,size));
        }
    }
}

package com.kd8lvt.content.item.rfid;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.content.item.GenericModItem;
import com.kd8lvt.registry.ModComponents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class RFIDItem extends GenericModItem {
    public RFIDItem(RFIDDevice device) {
        super(new Settings().component(ModComponents.RFID_DEVICE,device.copy()));
    }

    public static ItemStack replaceComponent(ItemStack stack, RFIDDevice device) {
        ComponentChanges changes = ComponentChanges.builder().remove(ModComponents.RFID_DEVICE).add(ModComponents.RFID_DEVICE,device).build();
        ItemStack copy = stack.copy();
        copy.applyChanges(changes);
        copy.set(ModComponents.RFID_DEVICE,device);
        return copy;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        doTooltip(stack.get(ModComponents.RFID_DEVICE),stack,context,tooltip,type);
    }

    public abstract void doTooltip(RFIDDevice info, ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type);
}

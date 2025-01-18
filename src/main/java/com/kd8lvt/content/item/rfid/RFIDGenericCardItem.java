package com.kd8lvt.content.item.rfid;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTooltips;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Objects;

import static com.kd8lvt.util.RegistryUtil.id;

public class RFIDGenericCardItem extends RFIDItem {
    public static final ComponentType<RFIDDevice> component = ModComponents.RFID_DEVICE;
    public RFIDGenericCardItem() {
        this(RFIDDevice.of(true,true,id("generic_rfid_card").toString(),""));
    }

    public RFIDGenericCardItem(RFIDDevice device) {
        super(device.copy());
    }

    public void doTooltip(RFIDDevice info, ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!Objects.equals(info.getData(), "")) tooltip.add(Text.translatable(ModTooltips.RFID_ITEM_INFO.GENERIC_CARD.NO_DATA_SET,info.getData()));
        else tooltip.add(Text.translatable(ModTooltips.RFID_ITEM_INFO.GENERIC_CARD.NO_DATA_SET));
    }
}

package com.kd8lvt.content.item;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDItem;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTranslations;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class RFIDCard extends RFIDItem<RFIDCard.RFIDCardComponent> {
    public RFIDCard(Identifier id) {
        super(ModComponents.RFID_CARD, new RFIDCard.RFIDCardComponent(),id);
    }

    @Override
    public void doTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int size = getComp(stack).getBytesStored();
        if (size > 0) tooltip.add(Text.translatable(ModTranslations.STORED_DATA,size));
        else tooltip.add(Text.translatable(ModTranslations.NO_STORED_DATA));
    }

    public static RFIDCardComponent getComp(ItemStack stack) {
        return stack.getOrDefault(ModComponents.RFID_CARD,RFIDCardComponent.DEFAULT);
    }

    public static class RFIDCardComponent extends RFIDComponent {
        public static final RFIDCardComponent DEFAULT = new RFIDCardComponent();

        public RFIDCardComponent(NbtCompound encoded) {this.comp=encoded;}
        public RFIDCardComponent() {super();}
        public int getBytesStored() {
            //There is a little default overhead data that, to avoid confusing people, needs to be subtracted from the total.
            return this.comp.getSizeInBytes()-DEFAULT.comp.getSizeInBytes();
        }

        @Override
        public RFIDCardComponent write(String key, NbtElement value) throws LuaException {
            int max = maxBytes();
            int afterWrite = getBytesStored()+value.getSizeInBytes();
            if (afterWrite > max) throw new LuaException("RFID Storage too small! ("+afterWrite+"/"+max+" bytes)");
            NbtCompound copy = this.comp.copy();
            copy.putString(key,value.asString());
            return new RFIDCardComponent(copy);
        }

        @Override
        public int maxBytes() {return 1024;}


        @Override
        public String deviceType() {return "rfid_card";}
        @Override
        public RFIDComponentCodec<RFIDCardComponent> codec() {
            return new RFIDComponentCodec<>() {
                @Override
                public RFIDCard.RFIDCardComponent decode(NbtCompound encoded) {
                    return new RFIDCardComponent(encoded);
                }
            };
        }
        @Override
        public ComponentType<RFIDCard.RFIDCardComponent> type() {return new ComponentType.Builder<RFIDCardComponent>().codec(codec()).build();}

        @SuppressWarnings("unchecked")
        @Override
        public LuaRFIDDevice<RFIDCardComponent> rfidDevice(PeripheralBlockEntity be, boolean canWrite, Entity entity, ItemStack stack) {
            return LuaRFIDDevice.of(be,canWrite,entity,stack,type());
        }
    }
}

package com.kd8lvt.content.component.rfid;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.ObjectLuaTable;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class GeneralRFIDComponent extends RFIDComponent {
    public static GeneralRFIDComponent DEFAULT = new GeneralRFIDComponent();
    public static ComponentType<GeneralRFIDComponent> TYPE;
    public static RFIDComponentCodec<GeneralRFIDComponent> CODEC;
    static {
        RFIDComponentCodec<GeneralRFIDComponent> CODEC = new RFIDComponentCodec<>() {
            @Override
            public GeneralRFIDComponent decode(NbtCompound encoded) {
                return new GeneralRFIDComponent(encoded);
            }
        };
        TYPE = new ComponentType.Builder<GeneralRFIDComponent>().codec(CODEC).build();
    }
    public GeneralRFIDComponent() {
        super();
    }
    public GeneralRFIDComponent(NbtCompound encoded) {
        super(encoded);
    }

    @Override
    public int maxBytes() {
        return 0;
    }

    @Override
    public String deviceType() {
        return "generic_item";
    }
    @Override
    public RFIDComponentCodec<GeneralRFIDComponent> codec() {
        return CODEC;
    }
    @Override
    public ComponentType<GeneralRFIDComponent> type() {
        return TYPE;
    }

    @Override
    public GeneralLuaRFIDDevice rfidDevice(PeripheralBlockEntity be, boolean canWrite, Entity entity, ItemStack device) {
        return new GeneralLuaRFIDDevice(Math.sqrt(entity.squaredDistanceTo(be.getPos().toCenterPos())),TYPE,device,device.get(TYPE),false);
    }

    public static class GeneralLuaRFIDDevice extends LuaRFIDDevice<GeneralRFIDComponent> {
        public GeneralLuaRFIDDevice(double distance, ComponentType<GeneralRFIDComponent> componentType, ItemStack stack, RFIDDevice device, boolean writeable) {
            super(distance, componentType, stack, device, false);
        }

        @LuaFunction
        public final ObjectLuaTable getAllMethods() {
            HashMap<String, DynamicLuaGetter<?>> ret = new HashMap<>();
            for (Component<?> comp : stack.getComponents()) {
                if (comp.value() instanceof Text textComp && comp.type() == DataComponentTypes.ITEM_NAME) ret.put("getItemName",textComp::getString);
                if (comp.value() instanceof Text textComp && comp.type() == DataComponentTypes.CUSTOM_NAME) ret.put("getCustomName",textComp::getString);
                if (comp.value() instanceof ToolComponent toolComp && comp.type() == DataComponentTypes.TOOL) ret.put("getTool",()->{
                    HashMap<String,Object> toolData = new HashMap<>();
                    toolData.put("defaultMiningSpeed",toolComp.defaultMiningSpeed());
                    toolData.put("getRules",toolComp.rules());
                    toolData.put("damagePerBlock",toolComp.damagePerBlock());
                    return new ObjectLuaTable(toolData);
                });
                if (comp.value() instanceof Integer intComp && comp.type() == DataComponentTypes.DAMAGE) ret.put("getDamage",intComp::intValue);
                if (comp.value() instanceof Integer intComp && comp.type() == DataComponentTypes.MAX_DAMAGE) ret.put("getDurability",intComp::intValue);
                ret.put("getTooltip",()->{
                    List<Text> tt = stack.getTooltip(Item.TooltipContext.DEFAULT, null, TooltipType.ADVANCED);
                    if (tt == null || tt.isEmpty()) return "";
                    MutableText text = Text.empty();
                    tt.forEach((val)->text.append(val.copy().append("\n")));
                    return text.getString();
                });
                ret.put("getStackSize",stack::getCount);
                ret.put("getMaxStackSize",stack::getMaxCount);
                ret.put("getId",()->stack.getRegistryEntry().getIdAsString());
            }
            return new ObjectLuaTable(ret);
        }
    }

    @FunctionalInterface
    public interface DynamicLuaGetter<O> {
        @LuaFunction
        O exec();
    }
}

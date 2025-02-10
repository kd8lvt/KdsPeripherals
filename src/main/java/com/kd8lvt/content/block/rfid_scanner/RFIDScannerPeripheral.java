package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.rfid.CustomLuaRFIDDeviceProvider;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.util.BoxUtil;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.ObjectLuaTable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RFIDScannerPeripheral<T extends RFIDScannerBlockEntity> extends GenericModPeripheral<T> {
    public int range() {return 2;}
    public boolean canWrite() {return true;}

    public RFIDScannerPeripheral(T be) {
        super(be.id(),be);
    }

    public RFIDScannerPeripheral(Identifier id, T be) {
        super(id,be);
    }

    @LuaFunction
    public final <t extends RFIDComponent> ObjectLuaTable scanForDevices() {
        RFIDScannerBlockEntity be = this.blockEntity();
        World w = be.getWorld();
        HashMap<Integer, LuaRFIDDevice<?>> devices = new HashMap<>();
        int idx = 1;
        if (w instanceof ServerWorld world) {
            ArrayList<Entity> entities = new ArrayList<>(world.getEntitiesByClass(LivingEntity.class,BoxUtil.radialBox(be.getPos(),range()),unused->true));
            entities.addAll(world.getEntitiesByClass(ItemEntity.class,BoxUtil.radialBox(be.getPos(),range()),unused->true));
           for (Entity entity : entities) {
               if (entity instanceof ItemEntity item) {
                   ItemStack stack = item.getStack();
                   for (LuaRFIDDevice<?> dev : getDevicesInStack(stack,entity)) {
                       devices.put(idx++,dev);
                   }
               } else if (entity instanceof ServerPlayerEntity player) {
                   for (int i=0;i<player.getInventory().size();i++) {
                       ItemStack stack = player.getInventory().getStack(i);
                       for (LuaRFIDDevice<?> dev : getDevicesInStack(stack,entity)) {
                           devices.put(idx++,dev);
                       }
                   }
               } else if (entity instanceof InventoryOwner owner) {
                   for (int i = 0; i< owner.getInventory().size(); i++) {
                       ItemStack stack = owner.getInventory().getStack(i);
                       for (LuaRFIDDevice<?> dev : getDevicesInStack(stack,entity)) {
                           devices.put(idx++,dev);
                       }
                   }
               } else if (entity instanceof LivingEntity living) {
                   for (ItemStack stack : living.getEquippedItems()) {
                       for (LuaRFIDDevice<?> dev : getDevicesInStack(stack,entity)) {
                           devices.put(idx++,dev);
                       }
                   }
               }
           }
        }
        return new ObjectLuaTable(devices);
    }

    public static boolean stackIsRFIDEnabled(ItemStack stack) {
        return stack.getComponents().stream().anyMatch(comp->comp.value() instanceof RFIDComponent);
    }

    @SuppressWarnings("unchecked")
    public static <t extends RFIDComponent> List<t> getRFIDComponentsInStack(ItemStack stack) {
        return stack.getComponents().stream().filter(comp->comp.value() instanceof RFIDComponent).map(val->(t)val.value()).toList();
    }

    public List<LuaRFIDDevice<?>> getDevicesInStack(ItemStack stack, Entity entity) {
        ArrayList<LuaRFIDDevice<?>> devices = new ArrayList<>();
        if (!stackIsRFIDEnabled(stack)) return devices;
        for (RFIDComponent comp : getRFIDComponentsInStack(stack)) {
            if (comp instanceof CustomLuaRFIDDeviceProvider provider) devices.add(provider.rfidDevice(blockEntity(),canWrite(),entity,stack));
        }
        return devices;
    }
}

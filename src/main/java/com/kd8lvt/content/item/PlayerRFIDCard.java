package com.kd8lvt.content.item;

import com.kd8lvt.api.rfid.CustomLuaRFIDDeviceProvider;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDItem;
import com.kd8lvt.content.block.GenericModBlockEntity;
import com.kd8lvt.content.component.rfid.PlayerCardComponent;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTooltips;
import dan200.computercraft.api.lua.LuaFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class PlayerRFIDCard extends RFIDItem<PlayerCardComponent> implements CustomLuaRFIDDeviceProvider {
    public PlayerRFIDCard(Identifier id) {
        super(ModComponents.PLAYER_CARD, new PlayerCardComponent(),id);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient || !user.isPlayer() || !user.isSneaking()) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        if (isBound(stack)) return TypedActionResult.pass(stack);

        return TypedActionResult.success(bind(stack,user).copy(),true);
    }

    @Override
    public void doTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isBound(stack)) tooltip.add(Text.translatable(ModTooltips.BOUND,boundTo(stack)));
        else tooltip.add(Text.translatable(ModTooltips.UNBOUND));
    }

    public static boolean isBound(ItemStack stack) {
        return stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent()).isBound();
    }
    public static String boundTo(ItemStack stack) {
        return stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent()).getBoundName();
    }
    public static ItemStack bind(ItemStack stack, PlayerEntity entity) {
        PlayerCardComponent comp = stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent());
        comp = comp.bind(entity);
        stack.set(ModComponents.PLAYER_CARD, comp);
        return stack;
    }

    @Override
    public LuaPlayerRFIDCard rfidDevice(GenericModBlockEntity be, boolean canWrite, Entity entity, ItemStack device) {
        return LuaPlayerRFIDCard.of(be,canWrite,entity,device);
    }
    public static class LuaPlayerRFIDCard extends LuaRFIDDevice<PlayerCardComponent> {
        PlayerCardComponent device;
        public LuaPlayerRFIDCard(double distance, ItemStack stack, PlayerCardComponent device) {
            super(distance,device.type(),stack,device,false);
        }

        public static LuaPlayerRFIDCard of(GenericModBlockEntity be, boolean ignoredCanWrite, Entity entity, ItemStack device) {
            double distance = Math.sqrt(be.getPos().getSquaredDistance(entity.getPos())); //Why can I only get squared distances lmao
            PlayerCardComponent dev = device.getOrDefault(ModComponents.PLAYER_CARD,PlayerCardComponent.DEFAULT);
            return new LuaPlayerRFIDCard(distance, device, dev);
        }

        @LuaFunction
        public String getBoundUuid() {
            UUID bound = device.getBoundUuid();
            if (bound == null) return null;
            return bound.toString();
        }

        @LuaFunction
        public String getBoundName() {
            return device.getBoundName();
        }

        @LuaFunction
        public boolean isBound() {
            return device.isBound();
        }
    }
}
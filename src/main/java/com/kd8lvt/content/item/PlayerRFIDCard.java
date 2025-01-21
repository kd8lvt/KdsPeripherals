package com.kd8lvt.content.item;

import com.kd8lvt.api.BindableItem;
import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.rfid.CustomLuaRFIDDeviceProvider;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDItem;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlockEntity;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTooltips;
import dan200.computercraft.api.lua.*;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerRFIDCard extends RFIDItem<PlayerRFIDCard.PlayerCardComponent> implements CustomLuaRFIDDeviceProvider {
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

    public static class PlayerCardComponent extends RFIDComponent implements BindableItem {
        public static final PlayerCardComponent DEFAULT = new PlayerCardComponent();
        public static ComponentType<PlayerCardComponent> TYPE;
        public static RFIDComponentCodec<PlayerCardComponent> CODEC;
        static {
            RFIDComponentCodec<PlayerCardComponent> CODEC = new RFIDComponentCodec<>() {
                @Override
                public PlayerCardComponent decode(NbtCompound encoded) {
                    return new PlayerCardComponent(encoded);
                }
            };
            TYPE = new ComponentType.Builder<PlayerCardComponent>().codec(CODEC).build();
        }

        public PlayerCardComponent(NbtCompound encoded) {super(encoded);}
        public PlayerCardComponent() {super();}

        @Override
        public int maxBytes() {
            return 1024;
        }

        @Override
        public void write(String key, NbtElement value) throws LuaException {
            int totalSize = comp.getSizeInBytes()+value.getSizeInBytes();
            if (totalSize > maxBytes()) throw new LuaException("RFID storage full! ("+totalSize+"/"+maxBytes()+" bytes)");
            else super.write(key, value);
        }

        @Override
        public String deviceType() {
            return "player_rfid_card";
        }

        @Override
        public PlayerCardComponent bind(PlayerEntity entity) {
            NbtCompound comp = new NbtCompound();
            comp.put("boundTo", NbtHelper.fromUuid(entity.getUuid()));
            comp.putString("boundName", entity.getName().getString());
            comp.putBoolean("bound", true);
            return new PlayerCardComponent(comp);
        }
        @Override
        public boolean isBound() {
            if (comp.contains("bound")) return comp.getBoolean("bound");
            return false;
        }
        @Override
        public @Nullable UUID getBoundUuid() {
            if (comp.get("boundTo") instanceof NbtElement boundTo) return NbtHelper.toUuid(boundTo);
            return null;
        }
        @Override
        public @Nullable String getBoundName() {
            if (!Objects.equals(this.comp.getString("boundName"), "")) return this.comp.getString("boundName");
            return null;
        }

        @Override
        public RFIDComponentCodec<PlayerCardComponent> codec() {
            return CODEC;
        }
        @Override
        public ComponentType<PlayerCardComponent> type() {
            return TYPE;
        }
    }

    @Override
    public LuaPlayerRFIDCard rfidDevice(RangedRFIDScannerBlockEntity be, boolean canWrite, ServerPlayerEntity player, ItemStack device) {
        return LuaPlayerRFIDCard.of(be,canWrite,player,device);
    }

    public static class LuaPlayerRFIDCard extends LuaRFIDDevice {
        PlayerCardComponent device;
        public LuaPlayerRFIDCard(double distance, PlayerCardComponent device) {
            super(distance,device,false);
        }

        public static LuaPlayerRFIDCard of(RangedRFIDScannerBlockEntity be, boolean ignoredCanWrite, ServerPlayerEntity player, ItemStack device) {
            double distance = Math.sqrt(be.getPos().getSquaredDistance(player.getPos())); //Why can I only get squared distances lmao
            PlayerCardComponent dev = device.getOrDefault(ModComponents.PLAYER_CARD,PlayerCardComponent.DEFAULT);
            return new LuaPlayerRFIDCard(distance, dev);
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
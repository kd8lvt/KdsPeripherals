package com.kd8lvt.content.component.rfid;

import com.kd8lvt.api.BindableItem;
import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import dan200.computercraft.api.lua.LuaFunction;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class PlayerCardComponent extends RFIDComponent implements BindableItem {
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
    public int maxBytes() {return 1024;}

    @Override
    public String deviceType() {return "player_rfid_card";}

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
    public RFIDComponentCodec<PlayerCardComponent> codec() {return CODEC;}
    @Override
    public ComponentType<PlayerCardComponent> type() {return TYPE;}

    @SuppressWarnings("unchecked")
    @Override
    public LuaPlayerRFIDCard rfidDevice(PeripheralBlockEntity be, boolean canWrite, Entity entity, ItemStack device) {
        return new LuaPlayerRFIDCard(Math.sqrt(be.getPos().getSquaredDistance(entity.getPos())),device,device.get(type()));
    }

    public static class LuaPlayerRFIDCard extends LuaRFIDDevice<PlayerCardComponent> {
        PlayerCardComponent device;
        public LuaPlayerRFIDCard(double distance, ItemStack stack, PlayerCardComponent device) {
            super(distance,device.type(),stack,device,false);
            this.device=device;
        }

        @LuaFunction
        public final String getBoundUuid() {
            UUID bound = device.getBoundUuid();
            if (bound == null) return null;
            return bound.toString();
        }

        @LuaFunction
        public final String getBoundName() {
            return device.getBoundName();
        }

        @LuaFunction
        public final boolean isBound() {
            return device.isBound();
        }
    }
}
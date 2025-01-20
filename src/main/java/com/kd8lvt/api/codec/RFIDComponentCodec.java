package com.kd8lvt.api.codec;

import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.NbtCompound;

public abstract class RFIDComponentCodec<T extends RFIDComponent> extends MutatingCodec<T, NbtCompound> {
    @Override
    NbtCompound encode(T decoded) {
        return decoded.comp;
    }
    @Override
    Codec<NbtCompound> baseCodec() {
        return NbtCompound.CODEC;
    }
}

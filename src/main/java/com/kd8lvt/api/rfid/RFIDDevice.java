package com.kd8lvt.api.rfid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public record RFIDDevice(boolean readable, boolean writeable, String deviceType, AtomicReference<String> data) {
    public static Codec<RFIDDevice> CODEC = RecordCodecBuilder.create(builder->{
        return builder.group(
                Codec.BOOL.fieldOf("readable").forGetter(RFIDDevice::isReadable),
                Codec.BOOL.fieldOf("writeable").forGetter(RFIDDevice::isWriteable),
                Codec.STRING.fieldOf("deviceType").forGetter(RFIDDevice::getDeviceType),
                Codec.STRING.fieldOf("data").forGetter(RFIDDevice::getData)
        ).apply(builder,RFIDDevice::of);
    });

    public static RFIDDevice of(boolean read, boolean write, String type, String data) {
        return new RFIDDevice(((Boolean)read).equals(true),((Boolean)write).equals(true),String.copyValueOf(type.toCharArray().clone()),new AtomicReference<>(String.copyValueOf(data.toCharArray().clone())));
    }

    public static RFIDDevice defaulted() {
        return new RFIDDevice(true,true,"",new AtomicReference<>(""));
    }

    public RFIDDevice copy() {
        return RFIDDevice.of(this.writeable(),this.readable(), this.deviceType(), this.data().get());
    }

    public String getData() {
        if (Objects.isNull(data.get())) data.set("");
        return data.get();
    }

    public void setData(String d) {
        this.data.set(d);
    }

    public boolean isReadable() {
        return readable;
    }

    public boolean isWriteable() {
        return writeable;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
package com.kd8lvt.content.block.biometric_identifier;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class BiometricIdentifierBlockEntity extends PeripheralBlockEntity {
    private GenericModPeripheral<BiometricIdentifierBlockEntity> peripheral;
    public @GuardedBy("faceId") HashMap<UUID,Integer> faceId = new HashMap<>();

    public BiometricIdentifierBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BIOMETRIC_IDENTIFIER, pos, state);
    }

    @Override
    public GenericModPeripheral<BiometricIdentifierBlockEntity> peripheral() {
        if (peripheral == null) peripheral = new BiometricIdentifierPeripheral(id(),this);
        return peripheral;
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("biometric_identifier");
    }
}

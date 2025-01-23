package com.kd8lvt.content.block.biometric_identifier;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import net.minecraft.util.Identifier;

public class BiometricIdentifierPeripheral extends GenericModPeripheral<BiometricIdentifierBlockEntity> {
    public BiometricIdentifierPeripheral(Identifier id, BiometricIdentifierBlockEntity biometricIdentifierBlockEntity) {
        super(id, biometricIdentifierBlockEntity);
    }
}

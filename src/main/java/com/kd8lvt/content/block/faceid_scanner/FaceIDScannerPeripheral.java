package com.kd8lvt.content.block.faceid_scanner;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import net.minecraft.util.Identifier;

public class FaceIDScannerPeripheral extends GenericModPeripheral<FaceIDScannerBlockEntity> {
    public FaceIDScannerPeripheral(Identifier id, FaceIDScannerBlockEntity faceIDScannerBlockEntity) {
        super(id, faceIDScannerBlockEntity);
    }
}

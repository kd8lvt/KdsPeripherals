package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class RFIDScannerBlockEntity extends PeripheralBlockEntity {
    private RFIDScannerPeripheral<?> p;

    public RFIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RFID_SCANNER, pos, state);
    }
    public <T extends RFIDScannerBlockEntity> RFIDScannerBlockEntity(BlockEntityType<T> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("rfid_scanner");
    }

    @Override
    public RFIDScannerPeripheral<?> peripheral() {
        if (p == null) p = new RFIDScannerPeripheral<>(this);
        return p;
    }
}

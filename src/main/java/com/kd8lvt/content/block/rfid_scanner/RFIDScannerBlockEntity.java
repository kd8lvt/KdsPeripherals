package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class RFIDScannerBlockEntity extends PeripheralBlockEntity {
    private final RFIDScannerPeripheral peripheral = new RFIDScannerPeripheral(this);

    public RFIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RFID_SCANNER, pos, state);
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("rfid_scanner");
    }

    public IPeripheral peripheral() {
        return this.peripheral;
    }
}

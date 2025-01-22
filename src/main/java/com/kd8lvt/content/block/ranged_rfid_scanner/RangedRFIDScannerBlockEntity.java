package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class RangedRFIDScannerBlockEntity extends RFIDScannerBlockEntity {
    private final RangedRFIDScannerPeripheral peripheral = new RangedRFIDScannerPeripheral(this);

    public RangedRFIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RANGED_RFID_SCANNER, pos, state);
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("ranged_rfid_scanner");
    }

    public RangedRFIDScannerPeripheral peripheral() {
        return this.peripheral;
    }
}

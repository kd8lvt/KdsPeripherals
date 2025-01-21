package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class RangedRFIDScannerBlockEntity extends PeripheralBlockEntity {
    private final RangedRFIDScannerPeripheral peripheral = new RangedRFIDScannerPeripheral(this);

    public RangedRFIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RANGED_RFID_SCANNER, pos, state);
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("ranged_rfid_scanner");
    }

    public IPeripheral peripheral() {
        return this.peripheral;
    }
}

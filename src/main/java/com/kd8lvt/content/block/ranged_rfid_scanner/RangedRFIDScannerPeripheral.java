package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.content.block.rfid_scanner.RFIDScannerPeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;

public class RangedRFIDScannerPeripheral extends RFIDScannerPeripheral<RangedRFIDScannerBlockEntity> implements IPeripheral {
    @Override
    public int range() {return 8;}

    @Override
    public boolean canWrite() {return false;}

    RangedRFIDScannerPeripheral(RangedRFIDScannerBlockEntity be) {
        super(be.id(),be);
    }
}

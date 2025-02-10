package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.content.block.rfid_scanner.RFIDScannerPeripheral;

public class RangedRFIDScannerPeripheral extends RFIDScannerPeripheral<RangedRFIDScannerBlockEntity> {
    @Override
    public int range() {return 8;}

    @Override
    public boolean canWrite() {return false;}

    RangedRFIDScannerPeripheral(RangedRFIDScannerBlockEntity be) {
        super(be.id(),be);
    }
}

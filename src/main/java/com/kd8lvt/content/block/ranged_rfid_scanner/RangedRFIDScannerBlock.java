package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RangedRFIDScannerBlock extends RFIDScannerBlock {
    public RangedRFIDScannerBlock() {
        super(RegistryUtil.id("ranged_rfid_scanner"));
    }

    @Override
    public @Nullable RangedRFIDScannerBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RangedRFIDScannerBlockEntity(pos,state);
    }
}

package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RangedRFIDScannerBlock extends RFIDScannerBlock implements BlockEntityProvider {
    public RangedRFIDScannerBlock() {
        super(Settings.create().solid().sounds(BlockSoundGroup.STONE), RegistryUtil.id("ranged_rfid_scanner"));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RangedRFIDScannerBlockEntity(pos,state);
    }


}

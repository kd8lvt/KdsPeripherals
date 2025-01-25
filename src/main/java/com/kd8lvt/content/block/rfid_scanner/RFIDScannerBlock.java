package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RFIDScannerBlock extends GenericModBlock implements BlockEntityProvider {
    private RFIDScannerBlockEntity be;
    public RFIDScannerBlock() {
        super(RegistryUtil.id("rfid_scanner"));
    }

    public RFIDScannerBlock(Settings settings, Identifier id) {
        super(settings,id);
    }
    public RFIDScannerBlock(Identifier id) {
        super(id);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        be = new RFIDScannerBlockEntity(pos,state);
        return be;
    }
}

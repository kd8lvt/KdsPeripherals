package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RFIDScannerBlock extends GenericModBlock implements BlockEntityProvider {
    private RFIDScannerBlockEntity be;
    public RFIDScannerBlock() {
        super(Settings.create().solid().sounds(BlockSoundGroup.STONE), RegistryUtil.id("rfid_scanner"));
    }

    public RFIDScannerBlock(Settings settings, Identifier id) {
        super(settings,id);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        be = new RFIDScannerBlockEntity(pos,state);
        return be;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (be != null) return be::tick;
        return null;
    }
}

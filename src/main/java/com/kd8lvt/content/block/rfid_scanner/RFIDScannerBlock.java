package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.content.block.KdBlockWithEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RFIDScannerBlock extends KdBlockWithEntity<RFIDScannerBlockEntity> {
    private RFIDScannerBlockEntity be;
    public RFIDScannerBlock() {
        this(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),RegistryUtil.id("rfid_scanner"));
    }

    public RFIDScannerBlock(Settings settings, Identifier id) {
        super(settings,id);
    }
    public RFIDScannerBlock(Identifier id) {
        this(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),id);
    }

    @Override
    public @Nullable RFIDScannerBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        be = new RFIDScannerBlockEntity(pos,state);
        return be;
    }

    @Override
    public BlockEntityType<RFIDScannerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.RFID_SCANNER;
    }
}

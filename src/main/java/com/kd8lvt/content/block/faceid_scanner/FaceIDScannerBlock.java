package com.kd8lvt.content.block.faceid_scanner;

import com.kd8lvt.api.content.block.KdBlockWithEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FaceIDScannerBlock extends KdBlockWithEntity<FaceIDScannerBlockEntity> {
    public FaceIDScannerBlock(Identifier id) {
        super(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),id);
    }

    public FaceIDScannerBlock() {
        this(RegistryUtil.id("faceid_scanner"));
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return FaceIDScannerBlockEntity::tick;
    }

    @Override
    public BlockEntityType<FaceIDScannerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.FACEID_SCANNER;
    }
}

package com.kd8lvt.content.block.faceid_scanner;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FaceIDScannerBlock extends GenericModBlock implements BlockEntityProvider {
    public FaceIDScannerBlock(Identifier id) {
        super(id);
    }

    public FaceIDScannerBlock() {
        this(RegistryUtil.id("faceid_scanner"));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FaceIDScannerBlockEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return FaceIDScannerBlockEntity::tick;
    }
}

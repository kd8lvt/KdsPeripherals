package com.kd8lvt.content.block.chatterbox;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChatterboxBlock extends GenericModBlock implements BlockEntityProvider {
    public ChatterboxBlock() {
        super(RegistryUtil.id("chatterbox"));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChatterboxBlockEntity(pos,state);
    }
}

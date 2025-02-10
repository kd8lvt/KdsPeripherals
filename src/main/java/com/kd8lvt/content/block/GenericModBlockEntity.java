package com.kd8lvt.content.block;

import com.kd8lvt.api.content.KdThing;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class GenericModBlockEntity extends BlockEntity implements KdThing {
    public GenericModBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}

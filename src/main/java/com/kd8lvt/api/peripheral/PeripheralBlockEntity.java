package com.kd8lvt.api.peripheral;

import com.kd8lvt.api.content.block.KdBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class PeripheralBlockEntity extends KdBlockEntity {
    public PeripheralBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public abstract GenericModPeripheral<?> peripheral();
}

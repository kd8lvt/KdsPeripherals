package com.kd8lvt.content.block.rfid_interface;

import com.kd8lvt.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class RFIDInterfaceBlockEntity extends BlockEntity {
    public RFIDInterfacePeripheral peripheral;

    public RFIDInterfaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RFID_INTERFACE, pos, state);
        peripheral = new RFIDInterfacePeripheral();
    }


}

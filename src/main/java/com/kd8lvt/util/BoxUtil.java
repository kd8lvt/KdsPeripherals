package com.kd8lvt.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public interface BoxUtil {
    static Box radialBox(BlockPos center,int radius) {
        return new Box(center.offset(Direction.UP,radius).offset(Direction.NORTH,radius).offset(Direction.EAST,radius).toCenterPos(),center.offset(Direction.DOWN,radius).offset(Direction.SOUTH,radius).offset(Direction.WEST,radius).toCenterPos());
    }
}

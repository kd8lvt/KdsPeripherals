package com.kd8lvt.content.block.rfid_interface;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.item.rfid.RFIDGenericCardItem;
import com.kd8lvt.registry.ModComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class RFIDInterfaceBlock extends GenericModBlock implements BlockEntityProvider {
    public RFIDInterfaceBlock() {
        super(AbstractBlock.Settings.create().solid());
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RFIDInterfaceBlockEntity(pos, state);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && stack.getComponents().contains(ModComponents.RFID_DEVICE) && stack.get(ModComponents.RFID_DEVICE) instanceof RFIDDevice info && world.getBlockEntity(pos) instanceof RFIDInterfaceBlockEntity be) {
            if (be.peripheral.mode == RFIDInterfacePeripheral.Modes.WRITE) {
                boolean dirty = false;
                String oldData = Text.literal(info.getData()).getString();
                if (!Objects.equals(be.peripheral.nameToSet, "")) {
                    stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(be.peripheral.nameToSet).fillStyle(Style.EMPTY.withItalic(false)));
                    dirty = true;
                }
                if (!Objects.equals(be.peripheral.writeData, "")) {
                    info.setData(be.peripheral.writeData);
                    stack.apply(ModComponents.RFID_DEVICE,stack.get(ModComponents.RFID_DEVICE),info,(a,b)->info);
                    dirty=true;
                }
                if (dirty) {
                    be.peripheral.emitRFIDWrite(oldData,info.getData());
                }
                return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
            }
            be.peripheral.emitRFIDScan(info.getData());
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}

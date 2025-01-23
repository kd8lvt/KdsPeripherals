package com.kd8lvt.content.block.faceid_scanner;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.kd8lvt.util.BoxUtil.radialBox;

public class FaceIDScannerBlockEntity extends PeripheralBlockEntity {
    private GenericModPeripheral<FaceIDScannerBlockEntity> peripheral;
    public @GuardedBy("faceId") HashMap<UUID,Integer> faceId = new HashMap<>();

    public FaceIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FACEID_SCANNER, pos, state);
    }

    @Override
    public GenericModPeripheral<FaceIDScannerBlockEntity> peripheral() {
        if (peripheral == null) peripheral = new FaceIDScannerPeripheral(id(),this);
        return peripheral;
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("faceid_scanner");
    }

    public static <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T blockEntity) {
        if (blockEntity instanceof FaceIDScannerBlockEntity be) {
            Box area = radialBox(pos, 3);
            List<? extends PlayerEntity> players = world.getPlayers().stream().filter(player -> area.contains(player.getPos())).toList();
            synchronized ("faceId") {
                for (PlayerEntity player : players) {
                    HitResult res = player.raycast(4,0,false);
                    if (!(res instanceof BlockHitResult blockRes)) {
                        be.faceId.remove(player.getUuid());
                        continue;
                    }
                    BlockPos lookPos = blockRes.getBlockPos();
                    if (pos.equals(lookPos)) {
                        be.faceId.putIfAbsent(player.getUuid(), 0);
                        be.faceId.computeIfPresent(player.getUuid(), (k, v) -> v + 1);
                        if (be.faceId.get(player.getUuid()) == 8)
                            be.peripheral().fireEvent("biometric_read", player.getUuidAsString());
                    } else {
                        be.faceId.remove(player.getUuid());
                    }
                }
                for (Map.Entry<UUID, Integer> entry : be.faceId.entrySet()) {
                    if (players.stream().noneMatch(player -> player.getUuid() == entry.getKey())) {
                        be.faceId.remove(entry.getKey());
                    }
                }
            }
        }
    }
}

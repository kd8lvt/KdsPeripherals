package com.kd8lvt.content.block.chatterbox;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;

public class ChatterboxPeripheral extends GenericModPeripheral<ChatterboxBlockEntity> {
    public ChatterboxPeripheral(Identifier id, ChatterboxBlockEntity chatterboxBlockEntity) {
        super(id, chatterboxBlockEntity);
    }

    public void say(String message, Object[] translationArgs) {
        World w = blockEntity().getWorld();
        if (!(w instanceof ServerWorld world) || w.isClient) return;
        for (PlayerEntity player : world.getServer().getPlayerManager().getPlayerList()) {
            player.sendMessage(Text.translatable(message, translationArgs));
        }
    }

    @LuaFunction
    public final void say(IArguments args) throws LuaException {
        String msg = args.getString(0);
        Object[] tlArgs = args.getAll();
        Object[] finalTlArgs = Arrays.stream(tlArgs).filter(obj-> Arrays.stream(tlArgs).toList().indexOf(obj)>0).toArray();
        say(msg,finalTlArgs);
    }
}

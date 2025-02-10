package com.kd8lvt.content.item;

import com.kd8lvt.api.rfid.RFIDItem;
import com.kd8lvt.content.component.rfid.PlayerCardComponent;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTranslations;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class PlayerRFIDCard extends RFIDItem<PlayerCardComponent> {
    public PlayerRFIDCard(Identifier id) {
        super(ModComponents.PLAYER_CARD, new PlayerCardComponent(),id);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient || !user.isPlayer() || !user.isSneaking()) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        if (isBound(stack)) return TypedActionResult.pass(stack);

        return TypedActionResult.success(bind(stack,user).copy(),true);
    }

    @Override
    public void doTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isBound(stack)) tooltip.add(Text.translatable(ModTranslations.BOUND,boundTo(stack)));
        else tooltip.add(Text.translatable(ModTranslations.UNBOUND));
    }

    public static boolean isBound(ItemStack stack) {
        return stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent()).isBound();
    }
    public static String boundTo(ItemStack stack) {
        return stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent()).getBoundName();
    }
    public static ItemStack bind(ItemStack stack, PlayerEntity entity) {
        PlayerCardComponent comp = stack.getOrDefault(ModComponents.PLAYER_CARD,new PlayerCardComponent());
        comp = comp.bind(entity);
        stack.set(ModComponents.PLAYER_CARD, comp);
        return stack;
    }
}
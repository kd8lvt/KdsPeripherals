package com.kd8lvt.content.item.rfid;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.registry.ModComponents;
import com.kd8lvt.registry.ModTooltips;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

import static com.kd8lvt.util.RegistryUtil.id;

public class RFIDPlayerCardItem extends RFIDGenericCardItem {
    private final static ComponentType<RFIDDevice> component = ModComponents.RFID_DEVICE;
    public RFIDPlayerCardItem() {
        super(RFIDDevice.of(true,false,id("player_rfid_card").toString(),""));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.isSneaking()) return TypedActionResult.pass(user.getStackInHand(hand));
        ItemStack stack = user.getStackInHand(hand).copy();
        RFIDDevice device = stack.getOrDefault(component, RFIDDevice.defaulted());
        assert device != null;
        if (!Objects.equals(user.getUuidAsString(), device.getData())) {
            device.setData(user.getUuidAsString());
            stack.set(component,device);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient || context.getPlayer() == null) return super.useOnBlock(context);
        context.getStack().getOrDefault(component, context.getPlayer().getUuid().toString());
        return super.useOnBlock(context);
    }

    @Override
    public void doTooltip(RFIDDevice info, ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!Objects.equals(info.getData(), "")) tooltip.add(Text.translatable(ModTooltips.RFID_ITEM_INFO.PLAYER_CARD.BOUND,info.getData()));
        else tooltip.add(Text.translatable(ModTooltips.RFID_ITEM_INFO.PLAYER_CARD.NOT_BOUND));
    }
}

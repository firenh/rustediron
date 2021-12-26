package fireopal.rustediron.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fireopal.rustediron.items.AxeItemMixinHelper;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(at = @At("HEAD"), cancellable = true, method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;")
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        World world = context.getWorld();
        boolean bl = AxeItemMixinHelper.useOnBlock(context);

        if (bl) {
            info.setReturnValue(ActionResult.success(world.isClient)); ;
        }
    }
}

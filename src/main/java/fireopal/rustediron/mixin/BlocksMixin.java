package fireopal.rustediron.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

import fireopal.rustediron.blocks.RustableBlock;
import fireopal.rustediron.blocks.Rustable.RustLevel;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Shadow
	private static Block register(String string, Block block) {
		throw new AssertionError();
	}

	@Redirect(method = "<clinit>",
		slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=iron_block")),
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;",
			ordinal = 0
		)
	)
	private static Block registerIronBlock(String id, Block block) {
		return register(id, new RustableBlock(RustLevel.UNAFFECTED, Block.Settings.copy(block).ticksRandomly()));
	}
}

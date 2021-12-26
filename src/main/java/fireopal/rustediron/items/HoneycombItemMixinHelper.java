package fireopal.rustediron.items;

import java.util.Optional;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import fireopal.rustediron.blocks.RustedIronBlocks;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class HoneycombItemMixinHelper {
    public static final Supplier<BiMap<Block, Block>> UNWAXED_TO_WAXED_BLOCKS = 
        Suppliers.memoize(() -> 
            ((ImmutableBiMap.Builder)(ImmutableBiMap.builder()
                .put(Blocks.IRON_BLOCK, RustedIronBlocks.WAXED_IRON_BLOCK)
                .put(RustedIronBlocks.EXPOSED_IRON, RustedIronBlocks.WAXED_EXPOSED_IRON)
                .put(RustedIronBlocks.WEATHERED_IRON, RustedIronBlocks.WAXED_WEATHERED_IRON)
                .put(RustedIronBlocks.RUSTED_IRON, RustedIronBlocks.WAXED_RUSTED_IRON)
                .put(RustedIronBlocks.IRON_SLAB, RustedIronBlocks.WAXED_IRON_SLAB)
                .put(RustedIronBlocks.EXPOSED_IRON_SLAB, RustedIronBlocks.WAXED_EXPOSED_IRON_SLAB)
                .put(RustedIronBlocks.WEATHERED_IRON_SLAB, RustedIronBlocks.WAXED_WEATHERED_IRON_SLAB)
                .put(RustedIronBlocks.RUSTED_IRON_SLAB, RustedIronBlocks.WAXED_RUSTED_IRON_SLAB)
                .put(RustedIronBlocks.IRON_STAIRS, RustedIronBlocks.WAXED_IRON_STAIRS)
                .put(RustedIronBlocks.EXPOSED_IRON_STAIRS, RustedIronBlocks.WAXED_EXPOSED_IRON_STAIRS)
                .put(RustedIronBlocks.WEATHERED_IRON_STAIRS, RustedIronBlocks.WAXED_WEATHERED_IRON_STAIRS)
                .put(RustedIronBlocks.RUSTED_IRON_STAIRS, RustedIronBlocks.WAXED_RUSTED_IRON_STAIRS)
            )).build());

    public static final Supplier<BiMap<Block, Block>> WAXED_TO_UNWAXED_BLOCKS = Suppliers.memoize(() -> UNWAXED_TO_WAXED_BLOCKS.get().inverse());

    public static Optional<BlockState> getUnwaxedToWaxedState(BlockState blockState) {
        return getUnwaxedToWaxedBlock(blockState.getBlock()).map(block -> block.getStateWithProperties(blockState));
    }

    public static Optional<Block> getUnwaxedToWaxedBlock(Block block) {
        return Optional.ofNullable(UNWAXED_TO_WAXED_BLOCKS.get().get(block));
    }

    public static Optional<BlockState> getWaxedToUnwaxedState(BlockState blockState) {
        return getWaxedToUnwaxedBlock(blockState.getBlock()).map(block -> block.getStateWithProperties(blockState));
    }

    public static Optional<Block> getWaxedToUnwaxedBlock(Block block) {
        return Optional.ofNullable(WAXED_TO_UNWAXED_BLOCKS.get().get(block));
    }

    public static boolean useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        Optional<BlockState> waxing = getUnwaxedToWaxedState(blockState);

        if (waxing.isPresent()) {
            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack = context.getStack();

            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);

            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }

            itemStack.decrement(1);
            world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_WAXED, blockPos, 0);
            world.setBlockState(blockPos, (BlockState)waxing.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);

            return true;
        }

        return false;
    }
}

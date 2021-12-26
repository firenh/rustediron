package fireopal.rustediron.blocks;

import java.util.Optional;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Degradable;
import net.minecraft.item.AxeItem;

public interface Rustable extends Degradable<Rustable.RustLevel> {
    public static final Supplier<BiMap<Block, Block>> RUST_LEVEL_INCREASES = 
        Suppliers.memoize(() -> 
            ((ImmutableBiMap.Builder)(ImmutableBiMap.builder()
                .put(Blocks.IRON_BLOCK, RustedIronBlocks.EXPOSED_IRON)
                .put(RustedIronBlocks.EXPOSED_IRON, RustedIronBlocks.WEATHERED_IRON)
                .put(RustedIronBlocks.WEATHERED_IRON, RustedIronBlocks.RUSTED_IRON)
                .put(RustedIronBlocks.IRON_SLAB, RustedIronBlocks.EXPOSED_IRON_SLAB)
                .put(RustedIronBlocks.EXPOSED_IRON_SLAB, RustedIronBlocks.WEATHERED_IRON_SLAB)
                .put(RustedIronBlocks.WEATHERED_IRON_SLAB, RustedIronBlocks.RUSTED_IRON_SLAB)
                .put(RustedIronBlocks.IRON_STAIRS, RustedIronBlocks.EXPOSED_IRON_STAIRS)
                .put(RustedIronBlocks.EXPOSED_IRON_STAIRS, RustedIronBlocks.WEATHERED_IRON_STAIRS)
                .put(RustedIronBlocks.WEATHERED_IRON_STAIRS, RustedIronBlocks.RUSTED_IRON_STAIRS)
            )).build());

    public static final Supplier<BiMap<Block, Block>> RUST_LEVEL_DECREASES = Suppliers.memoize(() -> RUST_LEVEL_INCREASES.get().inverse());


    public static Optional<Block> getDecreasedRustBlock(Block block) {
        return Optional.ofNullable(RUST_LEVEL_DECREASES.get().get(block));
    }

    public static Block getUnaffectedRustBlock(Block block) {
        Block block1 = block;
        Block block2 = RUST_LEVEL_DECREASES.get().get(block1);

        while (block2 != null) {
            block1 = block2;
            block2 = RUST_LEVEL_DECREASES.get().get(block1);
        }

        return block1;
    }

    public static Optional<BlockState> getDecreasedRustState(BlockState blockState) {
        return Rustable.getDecreasedRustBlock(blockState.getBlock()).map(block -> block.getStateWithProperties(blockState));
    }

    public static Optional<Block> getIncreasedRustBlock(Block block) {
        return Optional.ofNullable(RUST_LEVEL_INCREASES.get().get(block));
    }

    public static BlockState getUnaffectedRustState(BlockState blockState) {
        return Rustable.getUnaffectedRustBlock(blockState.getBlock()).getStateWithProperties(blockState);
    }

    @Override
    default public Optional<BlockState> getDegradationResult(BlockState blockState) {
        return Rustable.getIncreasedRustBlock(blockState.getBlock()).map(block -> block.getStateWithProperties(blockState));
    }

    @Override
    default float getDegradationChanceMultiplier() {
        if (this.getDegradationLevel() == RustLevel.UNAFFECTED) return 1.5f;
        // else if (this.getDegradationLevel() == RustLevel.EXPOSED) return 1.0f;

        return 1.0f;
    }

    public static enum RustLevel {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        RUSTED
    }
}

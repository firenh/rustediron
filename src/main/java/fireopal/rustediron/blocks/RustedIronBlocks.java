package fireopal.rustediron.blocks;

import fireopal.rustediron.RustedIron;
import fireopal.rustediron.blocks.Rustable.RustLevel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class RustedIronBlocks {
    public static Block 
        EXPOSED_IRON,
        WEATHERED_IRON,
        RUSTED_IRON,
        IRON_STAIRS,
        EXPOSED_IRON_STAIRS,
        WEATHERED_IRON_STAIRS,
        RUSTED_IRON_STAIRS,
        IRON_SLAB,
        EXPOSED_IRON_SLAB,
        WEATHERED_IRON_SLAB,
        RUSTED_IRON_SLAB,
        WAXED_IRON_BLOCK,
        WAXED_EXPOSED_IRON,
        WAXED_WEATHERED_IRON,
        WAXED_RUSTED_IRON,
        WAXED_IRON_STAIRS,
        WAXED_EXPOSED_IRON_STAIRS,
        WAXED_WEATHERED_IRON_STAIRS,
        WAXED_RUSTED_IRON_STAIRS,
        WAXED_IRON_SLAB,
        WAXED_EXPOSED_IRON_SLAB,
        WAXED_WEATHERED_IRON_SLAB,
        WAXED_RUSTED_IRON_SLAB
    ;    

    public static void init() {
        EXPOSED_IRON = registerRustableBlock("exposed_iron", RustLevel.EXPOSED);
        WEATHERED_IRON = registerRustableBlock("weathered_iron", RustLevel.WEATHERED);
        RUSTED_IRON = registerRustableBlock("rusted_iron", RustLevel.RUSTED);

        IRON_STAIRS = registerRustableStairsBlock("iron_stairs", Blocks.IRON_BLOCK, RustLevel.UNAFFECTED);
        EXPOSED_IRON_STAIRS = registerRustableStairsBlock("exposed_iron_stairs", EXPOSED_IRON, RustLevel.EXPOSED);
        WEATHERED_IRON_STAIRS = registerRustableStairsBlock("weathered_iron_stairs", WEATHERED_IRON, RustLevel.WEATHERED);
        RUSTED_IRON_STAIRS = registerRustableStairsBlock("rusted_iron_stairs", RUSTED_IRON, RustLevel.RUSTED);

        IRON_SLAB = registerRustableSlabBlock("iron_slab", RustLevel.UNAFFECTED);
        EXPOSED_IRON_SLAB = registerRustableSlabBlock("exposed_iron_slab", RustLevel.EXPOSED);
        WEATHERED_IRON_SLAB = registerRustableSlabBlock("weathered_iron_slab", RustLevel.WEATHERED);
        RUSTED_IRON_SLAB = registerRustableSlabBlock("rusted_iron_slab", RustLevel.RUSTED);

        WAXED_IRON_BLOCK = registerWaxedBlock("waxed_iron_block", RustLevel.UNAFFECTED);
        WAXED_EXPOSED_IRON = registerWaxedBlock("waxed_exposed_iron", RustLevel.EXPOSED);
        WAXED_WEATHERED_IRON = registerWaxedBlock("waxed_weathered_iron", RustLevel.WEATHERED);
        WAXED_RUSTED_IRON = registerWaxedBlock("waxed_rusted_iron", RustLevel.RUSTED);

        WAXED_IRON_STAIRS = registerWaxedStairsBlock("waxed_iron_stairs", Blocks.IRON_BLOCK, RustLevel.UNAFFECTED);
        WAXED_EXPOSED_IRON_STAIRS = registerWaxedStairsBlock("waxed_exposed_iron_stairs", EXPOSED_IRON, RustLevel.EXPOSED);
        WAXED_WEATHERED_IRON_STAIRS = registerWaxedStairsBlock("waxed_weathered_iron_stairs", WEATHERED_IRON, RustLevel.WEATHERED);
        WAXED_RUSTED_IRON_STAIRS = registerWaxedStairsBlock("waxed_rusted_iron_stairs", RUSTED_IRON, RustLevel.RUSTED);

        WAXED_IRON_SLAB = registerWaxedSlabBlock("waxed_iron_slab", RustLevel.UNAFFECTED);
        WAXED_EXPOSED_IRON_SLAB = registerWaxedSlabBlock("waxed_exposed_iron_slab", RustLevel.EXPOSED);
        WAXED_WEATHERED_IRON_SLAB = registerWaxedSlabBlock("waxed_weathered_iron_slab", RustLevel.WEATHERED);
        WAXED_RUSTED_IRON_SLAB = registerWaxedSlabBlock("waxed_rusted_iron_slab", RustLevel.RUSTED);
    }

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, RustedIron.id(id), block);
    }

    private static MapColor getMapColorFromRustLevel(Rustable.RustLevel rustLevel) {
        if (rustLevel.equals(RustLevel.UNAFFECTED)) {
            return MapColor.IRON_GRAY;
        } else if (rustLevel.equals(RustLevel.EXPOSED)) {
            return MapColor.TERRACOTTA_WHITE;
        } else if (rustLevel.equals(RustLevel.WEATHERED)) {
            return MapColor.ORANGE;
        }

        return MapColor.TERRACOTTA_ORANGE;
    }

    private static Block registerRustableBlock(String id, Rustable.RustLevel rustLevel) {
        return register(id, 
            new RustableBlock(rustLevel, 
                AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }

    private static Block registerRustableStairsBlock(String id, Block baseBlock, Rustable.RustLevel rustLevel) {
        return register(id, 
            new RustableStairsBlock(rustLevel, baseBlock.getDefaultState(),
                AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }

    private static Block registerRustableSlabBlock(String id, Rustable.RustLevel rustLevel) {
        return register(id, 
            new RustableSlabBlock(rustLevel, 
                AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }

    private static Block registerWaxedBlock(String id, RustLevel rustLevel) {
        return register(id, 
            new Block(AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }

    private static Block registerWaxedStairsBlock(String id, Block baseBlock, RustLevel rustLevel) {
        return register(id, 
            new ModStairsBlock(baseBlock.getDefaultState(),
                AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }

    private static Block registerWaxedSlabBlock(String id, RustLevel rustLevel) {
        return register(id, 
            new SlabBlock(AbstractBlock.Settings.of(Material.METAL, getMapColorFromRustLevel(rustLevel))
                .requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)));
    }
}
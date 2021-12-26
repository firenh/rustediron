package fireopal.rustediron.items;

import fireopal.rustediron.blocks.RustedIronBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class RustedIronItems {
    public static Item 
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
        EXPOSED_IRON = register(RustedIronBlocks.EXPOSED_IRON);
        WEATHERED_IRON = register(RustedIronBlocks.WEATHERED_IRON);
        RUSTED_IRON = register(RustedIronBlocks.RUSTED_IRON);

        IRON_STAIRS = register(RustedIronBlocks.IRON_STAIRS);
        EXPOSED_IRON_STAIRS = register(RustedIronBlocks.EXPOSED_IRON_STAIRS);
        WEATHERED_IRON_STAIRS = register(RustedIronBlocks.WEATHERED_IRON_STAIRS);
        RUSTED_IRON_STAIRS = register(RustedIronBlocks.RUSTED_IRON_STAIRS);

        IRON_SLAB = register(RustedIronBlocks.IRON_SLAB);
        EXPOSED_IRON_SLAB = register(RustedIronBlocks.EXPOSED_IRON_SLAB);
        WEATHERED_IRON_SLAB = register(RustedIronBlocks.WEATHERED_IRON_SLAB);
        RUSTED_IRON_SLAB = register(RustedIronBlocks.RUSTED_IRON_SLAB);

        WAXED_IRON_BLOCK = register(RustedIronBlocks.WAXED_IRON_BLOCK);
        WAXED_EXPOSED_IRON = register(RustedIronBlocks.WAXED_EXPOSED_IRON);
        WAXED_WEATHERED_IRON = register(RustedIronBlocks.WAXED_WEATHERED_IRON);
        WAXED_RUSTED_IRON = register(RustedIronBlocks.WAXED_RUSTED_IRON);

        WAXED_IRON_STAIRS = register(RustedIronBlocks.WAXED_IRON_STAIRS);
        WAXED_EXPOSED_IRON_STAIRS = register(RustedIronBlocks.WAXED_EXPOSED_IRON_STAIRS);
        WAXED_WEATHERED_IRON_STAIRS = register(RustedIronBlocks.WAXED_WEATHERED_IRON_STAIRS);
        WAXED_RUSTED_IRON_STAIRS = register(RustedIronBlocks.WAXED_RUSTED_IRON_STAIRS);

        WAXED_IRON_SLAB = register(RustedIronBlocks.WAXED_IRON_SLAB);
        WAXED_EXPOSED_IRON_SLAB = register(RustedIronBlocks.WAXED_EXPOSED_IRON_SLAB);
        WAXED_WEATHERED_IRON_SLAB = register(RustedIronBlocks.WAXED_WEATHERED_IRON_SLAB);
        WAXED_RUSTED_IRON_SLAB = register(RustedIronBlocks.WAXED_RUSTED_IRON_SLAB);
    }

    public static Item register(Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static Item register(Block block) {
        return register(block, ItemGroup.BUILDING_BLOCKS);
    }
}

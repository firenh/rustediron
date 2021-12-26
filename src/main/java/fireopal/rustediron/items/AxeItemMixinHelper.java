package fireopal.rustediron.items;

import java.util.Optional;
import java.util.Random;

import fireopal.rustediron.blocks.Rustable;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class AxeItemMixinHelper {
    static Random random = new Random();
    private static final double[] onEdge = {-0.1, 1.1};

    private static int notAbove(int i, int notAbove) {
        if (i >= notAbove) {
            return i - notAbove;
        }

        return i;
    }

    public static boolean useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        ItemStack itemStack = context.getStack();
        

        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();

        Optional<BlockState> rustScrape = Rustable.getDecreasedRustState(blockState);
        Optional<BlockState> waxScrape = HoneycombItemMixinHelper.getWaxedToUnwaxedState(blockState);
        Optional<BlockState> finalOptional = Optional.empty();

        if (rustScrape.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            final double f = 6;

            for (int j = 15 + random.nextInt(5); j > 0; j -= 1) {
                int i = random.nextInt(3);
                double[] location = {onEdge[random.nextInt(2)], random.nextDouble(), random.nextDouble()};
                world.addParticle(ParticleTypes.WAX_ON, true, x + location[notAbove(i, 3)], y + location[notAbove(i + 1, 3)], z + location[notAbove(i + 2, 3)], (0.5 - random.nextDouble()) * f, (0.5 - random.nextDouble()) * f, (0.5 - random.nextDouble()) * f);
            }

            finalOptional = rustScrape;
        } else if (waxScrape.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
            finalOptional = waxScrape;
        }

        if (finalOptional.isPresent()) {
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }

            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
            }

            world.setBlockState(blockPos, (BlockState)finalOptional.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);

            return true;
        }

        return false;
    }
}

package fireopal.rustediron;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fireopal.rustediron.blocks.RustedIronBlocks;
import fireopal.rustediron.items.RustedIronItems;

public class RustedIron implements ModInitializer {
	public static final String MODID = "rusted_iron";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Identifier id(String id) {
		return new Identifier(MODID, id);
	}

	@Override
	public void onInitialize() {
		RustedIronBlocks.init();
		RustedIronItems.init();
	}
}

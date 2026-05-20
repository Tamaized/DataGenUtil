package tamaized.datagenutil.assets.bakedmodel.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tamaized.pkginfoutil.PublicApi;

import java.util.List;
import java.util.Objects;

@PublicApi
@SuppressWarnings("ClassCanBeRecord")
public final class BlockModelProviderFactory {

	private final List<BlockModelHolder> blockModelHolders;

	public BlockModelProviderFactory(List<BlockModelHolder> blockModelHolders) {
		this.blockModelHolders = blockModelHolders;
	}

	@PublicApi
	public void make(BlockModelGenerators provider) {
		blockModelHolders.forEach(holder -> {
			holder.buildIfEmpty(provider);
			if (holder.hasStandardBlockItem()) {
				holder.buildItemBlockModelIfEmpty(provider);
			}
			if (holder.hasBlockState()) {
				provider.blockStateOutput.accept(holder.buildBlockState(provider));
			}
		});
	}

	@PublicApi
	public void addLangEntries(LanguageProvider provider) {
		blockModelHolders.forEach(holder -> holder.lang().ifPresent(lang -> provider.addBlock(Objects.requireNonNull(holder.blockForName()), lang)));
	}

}

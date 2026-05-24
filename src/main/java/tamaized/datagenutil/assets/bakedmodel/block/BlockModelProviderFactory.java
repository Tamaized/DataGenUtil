package tamaized.datagenutil.assets.bakedmodel.block;

import net.minecraft.client.data.models.BlockModelGenerators;
import tamaized.beanification.Component;
import tamaized.beanification.Directory;
import tamaized.datagenutil.assets.lang.ExtendedLangProvider;
import tamaized.datagenutil.assets.lang.LangProvider;
import tamaized.pkginfoutil.PublicApi;

import java.util.List;
import java.util.Objects;

@Component
public final class BlockModelProviderFactory implements LangProvider {

	@Directory(BlockModelHolder.class)
	private List<BlockModelHolder> blockModelHolders;

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

	@Override
	public void addLangEntries(ExtendedLangProvider provider) {
		blockModelHolders.forEach(holder -> holder.lang().ifPresent(lang -> provider.addBlock(Objects.requireNonNull(holder.blockForName()), lang)));
	}

}

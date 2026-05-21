package tamaized.datagenutil.assets.bakedmodel.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import tamaized.datagenutil.assets.lang.ExtendedLangProvider;
import tamaized.datagenutil.assets.lang.LangProvider;
import tamaized.pkginfoutil.PublicApi;

import java.util.List;
import java.util.Objects;

@PublicApi
@SuppressWarnings("ClassCanBeRecord")
public final class ItemModelProviderFactory implements LangProvider {

	private final List<ItemModelHolder> itemModelHolders;

	public ItemModelProviderFactory(List<ItemModelHolder> itemModelHolders) {
		this.itemModelHolders = itemModelHolders;
	}

	@PublicApi
	public void make(ItemModelGenerators provider) {
		itemModelHolders.forEach(holder -> {
			holder.buildIfEmpty(provider);
		});
	}

	@Override
	public void addLangEntries(ExtendedLangProvider provider) {
		itemModelHolders.forEach(holder -> holder.lang().ifPresent(lang -> provider.addItem(Objects.requireNonNull(holder.itemForName()), lang)));
	}

}

package tamaized.datagenutil.assets.bakedmodel.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tamaized.pkginfoutil.PublicApi;

import java.util.List;
import java.util.Objects;

@PublicApi
@SuppressWarnings("ClassCanBeRecord")
public final class ItemModelProviderFactory {

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

	@PublicApi
	public void addLangEntries(LanguageProvider provider) {
		itemModelHolders.forEach(holder -> holder.lang().ifPresent(lang -> provider.addItem(Objects.requireNonNull(holder.itemForName()), lang)));
	}

}

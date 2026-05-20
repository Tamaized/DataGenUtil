package tamaized.datagenutil.assets.bakedmodel.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import tamaized.datagenutil.assets.bakedmodel.ExtendedTextureMapping;
import tamaized.datagenutil.assets.bakedmodel.FurtherExtendedModelTemplateBuilder;
import tamaized.pkginfoutil.PublicApi;

@PublicApi
public abstract class BasicItemModelHolder extends ItemModelHolder {

	@Override
	protected abstract DeferredHolder<Item, ? extends Item> itemForName();

	protected abstract ModelTemplate template();

	@Override
	public Identifier finalize(ItemModelGenerators provider, FurtherExtendedModelTemplateBuilder model) {
		provider.generateFlatItem(itemForName().value(), template());
		return ModelLocationUtils.getModelLocation(itemForName().value());
	}

	@Override
	protected void defineTextureSlots(ExtendedTextureMapping mapping) {

	}
}

package tamaized.datagenutil.assets.bakedmodel.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import tamaized.datagenutil.assets.bakedmodel.ModelHolder;

import javax.annotation.Nullable;
import java.util.Objects;

public abstract class ItemModelHolder extends ModelHolder<ItemModelGenerators> {

	@Nullable
	protected DeferredHolder<Item, ? extends Item> itemForName() {
		return null;
	}

	@Override
	protected String name(@Nullable String suffix) {
		return "item/" + nameToUse() + (suffix == null ? "" : ("_" + suffix));
	}

	@Override
	protected String nameToUse() {
		return Objects.requireNonNull(itemForName()).getId().getPath();
	}

}

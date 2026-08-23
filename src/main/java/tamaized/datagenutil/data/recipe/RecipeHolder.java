package tamaized.datagenutil.data.recipe;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import tamaized.pkginfoutil.PublicApi;

@PublicApi
public abstract class RecipeHolder {

	@PublicApi
	public abstract void make(HolderLookup.Provider provider, HolderGetter<Item> itemProvider, RecipeOutput output);

}

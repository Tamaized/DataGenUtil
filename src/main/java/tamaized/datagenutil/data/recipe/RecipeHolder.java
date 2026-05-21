package tamaized.datagenutil.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

public abstract class RecipeHolder {

	public abstract void make(HolderLookup.Provider provider, RecipeOutput output);

}

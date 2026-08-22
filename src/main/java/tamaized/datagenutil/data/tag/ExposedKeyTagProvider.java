package tamaized.datagenutil.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import tamaized.pkginfoutil.PublicApi;

import java.util.concurrent.CompletableFuture;

@PublicApi
public abstract class ExposedKeyTagProvider<T> extends KeyTagProvider<T> {

	public ExposedKeyTagProvider(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
		super(output, registryKey, lookupProvider, modId);
	}

	@Override
	public TagAppender<ResourceKey<T>, T> tag(TagKey<T> tag) {
		return super.tag(tag);
	}

	@Override
	public TagAppender<ResourceKey<T>, T> tag(TagKey<T> tag, boolean replace) {
		return super.tag(tag, replace);
	}
}

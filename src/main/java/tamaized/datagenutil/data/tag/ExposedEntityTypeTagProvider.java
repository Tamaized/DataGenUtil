package tamaized.datagenutil.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import tamaized.pkginfoutil.PublicApi;

import java.util.concurrent.CompletableFuture;

@PublicApi
public abstract class ExposedEntityTypeTagProvider extends EntityTypeTagsProvider {

	public ExposedEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
		super(output, lookupProvider, modId);
	}

	@Override
	public TagAppender<EntityType<?>, EntityType<?>> tag(TagKey<EntityType<?>> tag) {
		return super.tag(tag);
	}
}

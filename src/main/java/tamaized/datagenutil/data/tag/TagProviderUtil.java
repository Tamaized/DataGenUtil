package tamaized.datagenutil.data.tag;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import tamaized.beanification.Component;
import tamaized.pkginfoutil.PublicApi;

import java.util.function.Function;

@PublicApi
@Component
public class TagProviderUtil {

	@PublicApi
	@SafeVarargs
	public final <T> void tagMany(Function<TagKey<T>, TagAppender<ResourceKey<T>, T>> provider, ResourceKey<T> type, TagKey<T>... tags) {
		for (TagKey<T> key : tags) {
			provider.apply(key).add(type);
		}
	}

}

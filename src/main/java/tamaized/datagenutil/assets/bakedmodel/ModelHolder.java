package tamaized.datagenutil.assets.bakedmodel;

import net.minecraft.resources.Identifier;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class ModelHolder<T> {

	@Nullable
	private Identifier model;

	@Nullable
	private ExtendedTextureMapping textureMapping;

	public final Optional<Identifier> get() {
		return Optional.ofNullable(model);
	}

	protected final void set(Identifier model) {
		this.model = model;
	}

	public Identifier getOrBuild(T provider) {
		return get().orElseGet(() -> {
			buildAndSet(provider);
			return get().orElseThrow();
		});
	}

	public void buildIfEmpty(T provider) {
		if (get().isEmpty())
			buildAndSet(provider);
	}

	public void buildAndSet(T provider) {
		set(build(provider));
	}

	public final FurtherExtendedModelTemplateBuilder begin(T provider) {
		FurtherExtendedModelTemplateBuilder model = new FurtherExtendedModelTemplateBuilder(textures());
		parent().ifPresent(p -> model.parent(p.getOrBuild(provider)));

		return model;
	}

	public abstract Identifier finalize(T provider, FurtherExtendedModelTemplateBuilder model);

	public final Identifier build(T provider) {
		return finalize(provider, begin(provider));
	}

	public Optional<ModelHolder<T>> parent() {
		return Optional.empty();
	}

	public final ExtendedTextureMapping textures() {
		if (textureMapping != null)
			return textureMapping;

		textureMapping = new ExtendedTextureMapping();
		defineTextureSlots(textureMapping);
		return textureMapping;
	}

	protected abstract void defineTextureSlots(ExtendedTextureMapping mapping);

	public Optional<String> lang() {
		return Optional.empty();
	}

}

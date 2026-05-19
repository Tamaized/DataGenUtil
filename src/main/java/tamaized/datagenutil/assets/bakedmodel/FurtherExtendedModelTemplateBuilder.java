package tamaized.datagenutil.assets.bakedmodel;

import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import java.util.function.Function;

public class FurtherExtendedModelTemplateBuilder extends ExtendedModelTemplateBuilder {

	private final ExtendedTextureMapping extendedTextures;

	public FurtherExtendedModelTemplateBuilder(ExtendedTextureMapping extendedTextures) {
		this.extendedTextures = extendedTextures;
	}

	public FurtherExtendedModelTemplate buildExtended() {
		return buildExtended(model -> model);
	}

	public FurtherExtendedModelTemplate buildExtended(Function<FurtherExtendedModelTemplateBuilder, ExtendedModelTemplateBuilder> config) {
		return new FurtherExtendedModelTemplate(config.apply(this).build(), extendedTextures);
	}
}

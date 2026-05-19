package tamaized.datagenutil.assets.bakedmodel;

import com.google.gson.JsonObject;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;

import java.util.Map;

public class FurtherExtendedModelTemplate extends ModelTemplate {

	private final ExtendedModelTemplate delegate;
	private final ExtendedTextureMapping extendedTextures;

	public FurtherExtendedModelTemplate(ExtendedModelTemplate delegate, ExtendedTextureMapping extendedTextures) {
		super(delegate.model, delegate.suffix, delegate.requiredSlots.toArray(TextureSlot[]::new));
		this.delegate = delegate;
		this.extendedTextures = extendedTextures;
	}

	@Override
	public JsonObject createBaseTemplate(Identifier target, Map<TextureSlot, Material> slots) {
		JsonObject result = delegate.createBaseTemplate(target, slots);

		Map<TextureSlot, String> refMap = extendedTextures.createRefMap();
		if (!refMap.isEmpty()) {
			final String texturesProperty = "textures";
			JsonObject textures = result.has(texturesProperty) ? result.getAsJsonObject(texturesProperty) : new JsonObject();
			refMap.forEach((k, v) -> textures.addProperty(k.getId(), v));
			result.add(texturesProperty, textures);
		}

		return result;
	}
}

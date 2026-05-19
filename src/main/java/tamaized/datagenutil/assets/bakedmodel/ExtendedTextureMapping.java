package tamaized.datagenutil.assets.bakedmodel;

import com.google.common.collect.Maps;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;

import java.util.Map;
import java.util.stream.Collectors;

public class ExtendedTextureMapping extends TextureMapping {

	private final Map<TextureSlot, TextureSlot> slotRefs = Maps.newHashMap();

	public final ExtendedTextureMapping putRef(TextureSlot slot, TextureSlot ref) {
		slotRefs.put(slot, ref);
		return this;
	}

	public final Map<TextureSlot, String> createRefMap() {
		return slotRefs.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
	}

}

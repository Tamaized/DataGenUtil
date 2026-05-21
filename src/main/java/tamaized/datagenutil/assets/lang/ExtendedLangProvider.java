package tamaized.datagenutil.assets.lang;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import tamaized.pkginfoutil.PublicApi;

import java.util.List;
import java.util.function.Supplier;

@PublicApi
public abstract class ExtendedLangProvider extends LanguageProvider {

	private final String modId;
	private final List<LangProvider> langProviders;

	public ExtendedLangProvider(PackOutput output, String modId, String locale, List<LangProvider> langProviders) {
		super(output, modId, locale);
		this.modId = modId;
		this.langProviders = langProviders;
	}

	@Override
	protected void addTranslations() {
		langProviders.forEach(provider -> provider.addLangEntries(this));
		addAdditionalTranslations();
	}

	protected abstract void addAdditionalTranslations();

	@PublicApi
	public void addCreativeTab(String translation) {
		add(modId + ".item_group", translation);
	}

	@PublicApi
	public void addAdvancement(String name, String title, String desc) {
		add("advancement." + modId + "." + name, title);
		add("advancement." + modId + "." + name + ".desc", desc);
	}

	@PublicApi
	public void addTooltip(String name, String translation) {
		add(modId + ".tooltip." + name, translation);
	}

	@PublicApi
	public void addEffectWithDescription(
		Supplier<MobEffect> effect,
		String name,
		String description
	) {
		addEffect(effect, name);
		add(effect.get().getDescriptionId().concat(".description"), description);
	}

	@PublicApi
	public void addEntityTypeWithSpawnEgg(
		Supplier<? extends EntityType<? extends Entity>> entity,
		DeferredHolder<Item, ? extends Item> spawnEgg,
		String translation
	) {
		addEntityType(entity, translation);
		addItem(spawnEgg, translation.concat(" Spawn Egg"));
	}

	@PublicApi
	public void addEntityTypeSuffix(
		Supplier<? extends EntityType<? extends Entity>> entity,
		String suffix,
		String translation
	) {
		add(entity.get().getDescriptionId() + "." + suffix, translation);
	}

	@PublicApi
	public void addFluid(Supplier<FluidType> fluid, String translation) {
		add(fluid.get().getDescriptionId(), translation);
	}

	@PublicApi
	public void addAttribute(Holder<Attribute> attribute, String translation) {
		add(attribute.value().getDescriptionId(), translation);
	}

	@PublicApi
	public void addDeathMessage(ResourceKey<DamageType> key, String translation) {
		addResourceKey(key, "death.attack", translation);
	}

	@PublicApi
	public void addSubtitle(SoundEvent key, String translation) {
		add(key.location().toLanguageKey("subtitles"), translation);
	}

	@PublicApi
	public void addConfiguration(String configuration, String translation) {
		add(modId + ".configuration." + configuration, translation);
	}

	public void addConfig(String config, String translation) {
		add(modId + ".config." + config, translation);
	}

	@PublicApi
	public void addCommonConfig(String config, String translation) {
		addConfig("common." + config, translation);
	}

	@PublicApi
	public void addClientConfig(String config, String translation) {
		addConfig("client." + config, translation);
	}

	@PublicApi
	public void addResourceKey(ResourceKey<?> key, String prefix, String translation) {
		add(key.identifier().toLanguageKey(prefix), translation);
	}

	@PublicApi
	public void addDatapack(Lazy<Pack> pack, String translation) {
		add("pack." + modId + "." + pack.get().getId(), translation);
	}

}

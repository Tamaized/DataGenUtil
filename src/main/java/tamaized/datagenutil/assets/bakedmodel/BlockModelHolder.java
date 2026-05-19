package tamaized.datagenutil.assets.bakedmodel;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public abstract class BlockModelHolder extends ModelHolder<BlockModelGenerators> {

	@Nullable
	private Identifier itemBlockModel;

	protected String name() {
		return name(null);
	}

	protected String name(@org.jetbrains.annotations.Nullable String suffix) {
		return "block/" + nameToUse() + (suffix == null ? "" : ("_" + suffix));
	}

	protected String nameForItemBlock() {
		return nameForItemBlock(null);
	}

	protected String nameForItemBlock(@org.jetbrains.annotations.Nullable String suffix) {
		return "item/" + nameToUse() + (suffix == null ? "" : ("_" + suffix));
	}

	@Nullable
	protected DeferredHolder<Block, ? extends Block> blockForName() {
		return null;
	}

	protected String nameToUse() {
		return Objects.requireNonNull(blockForName()).getId().getPath();
	}

	public boolean hasStandardBlockItem() {
		return false;
	}

	public Identifier getOrBuildItemBlockModel(BlockModelGenerators provider) {
		return getItemBlockModel().orElseGet(() -> {
			buildAndSetItemBlockModel(provider);
			return getItemBlockModel().orElseThrow();
		});
	}

	public final Optional<Identifier> getItemBlockModel() {
		return Optional.ofNullable(itemBlockModel);
	}

	protected final void setItemBlockModel(Identifier model) {
		this.itemBlockModel = model;
	}

	public void buildItemBlockModelIfEmpty(BlockModelGenerators provider) {
		if (getItemBlockModel().isEmpty())
			buildAndSetItemBlockModel(provider);
	}

	public void buildAndSetItemBlockModel(BlockModelGenerators provider) {
		setItemBlockModel(buildItemBlockModel(provider));
	}

	public Identifier buildItemBlockModel(BlockModelGenerators provider) {
		Identifier id = getOrBuild(provider);
		provider.registerSimpleItemModel(Objects.requireNonNull(blockForName()).get(), id);
		return id;
	}

	public boolean hasBlockState() {
		return false;
	}

	public MultiVariantGenerator buildBlockState(BlockModelGenerators provider) {
		return BlockModelGenerators.createSimpleBlock(Objects.requireNonNull(blockForName()).get(), BlockModelGenerators.plainVariant(getOrBuild(provider)));
	}

}

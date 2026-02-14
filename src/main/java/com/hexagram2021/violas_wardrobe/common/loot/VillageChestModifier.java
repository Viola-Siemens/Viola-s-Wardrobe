package com.hexagram2021.violas_wardrobe.common.loot;

import com.hexagram2021.violas_wardrobe.common.config.VWCommonConfig;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import com.hexagram2021.violas_wardrobe.common.registries.VWLootModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;

public class VillageChestModifier extends LootModifier {
	protected VillageChestModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return VWLootModifiers.VILLAGE_CHEST_MODIFIER.get();
	}

	private static final List<ResourceLocation> LOOT_TABLES_TO_MODIFY = List.of(
			new ResourceLocation("chests/village/village_plains_house"),
			new ResourceLocation("chests/village/village_desert_house"),
			new ResourceLocation("chests/village/village_savanna_house"),
			new ResourceLocation("chests/village/village_snowy_house"),
			new ResourceLocation("chests/village/village_taiga_house")
	);

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if(LOOT_TABLES_TO_MODIFY.contains(context.getQueriedLootTableId()) &&
				context.getRandom().nextDouble() < VWCommonConfig.VILLAGE_CHEST_CHANCE.get()) {
			generatedLoot.add(VWItems.ItemEntry.getRandom(context.getRandom()));
		}
		return generatedLoot;
	}

	public static Codec<VillageChestModifier> factory() {
		return RecordCodecBuilder.create(inst -> LootModifier.codecStart(inst).apply(inst, VillageChestModifier::new));
	}
}

package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.fayetalerror.createarsenal.config.ArsenalDefinitionLoader;
import com.fayetalerror.createarsenal.config.ArmorMaterialDefinition;
import com.simibubi.create.AllItems;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares the gameplay material shared by every piece of Andesite Armor. */
public final class ArsenalArmorMaterials {
    /** Deferred armor-material registry scoped to the {@code createarsenal} namespace. */
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, CreateArsenal.MODID);

    /**
     * Iron-level armor material with higher enchantability, one point of toughness,
     * and Create Iron Sheets as its repair ingredient.
     */
    private static final Map<String, ArmorMaterialDefinition> DEFINITIONS =
            ArsenalDefinitionLoader.loadArmorMaterials(
                    "data/" + CreateArsenal.MODID + "/armor_materials.json");
    private static final ArmorMaterialDefinition ANDESITE_DEFINITION = DEFINITIONS.get("andesite");
    private static final ArmorMaterialDefinition BRASS_DEFINITION = DEFINITIONS.get("brass");

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ANDESITE =
            ARMOR_MATERIALS.register("andesite", () -> new ArmorMaterial(
                    createDefenseValues(ANDESITE_DEFINITION),
                    ANDESITE_DEFINITION.enchantability(),
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(AllItems.IRON_SHEET.get()),
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "andesite")
                    )),
                    ANDESITE_DEFINITION.toughness(),
                    ANDESITE_DEFINITION.knockbackResistance()
            ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BRASS =
            ARMOR_MATERIALS.register("brass", () -> new ArmorMaterial(
                    createDefenseValues(BRASS_DEFINITION),
                    BRASS_DEFINITION.enchantability(),
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(AllItems.BRASS_SHEET.get()),
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "brass")
                    )),
                    BRASS_DEFINITION.toughness(),
                    BRASS_DEFINITION.knockbackResistance()
            ));

    public static Holder<ArmorMaterial> byId(String id) {
        if ("andesite".equals(id)) return ANDESITE;
        if ("brass".equals(id)) return BRASS;
        throw new IllegalArgumentException("Unknown armor material: " + id);
    }

    /** Creates the defense value associated with each wearable armor slot. */
    private static Map<ArmorItem.Type, Integer> createDefenseValues(ArmorMaterialDefinition definition) {
        EnumMap<ArmorItem.Type, Integer> defenseValues = new EnumMap<>(ArmorItem.Type.class);

        defenseValues.put(ArmorItem.Type.HELMET, definition.helmetDefense());
        defenseValues.put(ArmorItem.Type.CHESTPLATE, definition.chestplateDefense());
        defenseValues.put(ArmorItem.Type.LEGGINGS, definition.leggingsDefense());
        defenseValues.put(ArmorItem.Type.BOOTS, definition.bootsDefense());

        return defenseValues;
    }

    /** Utility class; registered materials are exposed as static deferred holders. */
    private ArsenalArmorMaterials() {
    }
}

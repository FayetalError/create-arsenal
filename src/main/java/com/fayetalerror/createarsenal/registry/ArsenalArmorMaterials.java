package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import com.simibubi.create.AllItems;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.registries.Registries;
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
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ANDESITE =
            ARMOR_MATERIALS.register("andesite", () -> new ArmorMaterial(
                    createAndesiteDefenseValues(),
                    14,
                    SoundEvents.ARMOR_EQUIP_IRON,
                    () -> Ingredient.of(AllItems.IRON_SHEET.get()),
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(CreateArsenal.MODID, "andesite")
                    )),
                    1.0F,
                    0.0F
            ));

    /** Creates the defense value associated with each wearable armor slot. */
    private static Map<ArmorItem.Type, Integer> createAndesiteDefenseValues() {
        EnumMap<ArmorItem.Type, Integer> defenseValues = new EnumMap<>(ArmorItem.Type.class);

        defenseValues.put(ArmorItem.Type.HELMET, 2);
        defenseValues.put(ArmorItem.Type.CHESTPLATE, 7);
        defenseValues.put(ArmorItem.Type.LEGGINGS, 5);
        defenseValues.put(ArmorItem.Type.BOOTS, 2);

        return defenseValues;
    }

    /** Utility class; registered materials are exposed as static deferred holders. */
    private ArsenalArmorMaterials() {
    }
}

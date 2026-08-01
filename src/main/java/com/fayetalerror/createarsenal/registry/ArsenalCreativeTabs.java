package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Declares the mod's custom creative-mode tabs and their displayed contents. */
public final class ArsenalCreativeTabs {
    /** Deferred registry for creative tabs under the mod namespace. */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateArsenal.MODID);

    /**
     * Main tab registered as {@code createarsenal:createarsenal}.
     */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ARSENAL_TAB =
            CREATIVE_MODE_TABS.register("createarsenal", () -> CreativeModeTab.builder()
                    // Resolve the visible title from assets/createarsenal/lang/en_us.json.
                    .title(Component.translatable("itemGroup.createarsenal"))
                    .icon(() -> ArsenalItems.ANDESITE_PICKAXE.get().getDefaultInstance())
                    // Populate the tab when Minecraft requests its item list.
                    .displayItems((parameters, output) -> {
                        output.accept(ArsenalItems.ANDESITE_PICKAXE.get());
                        output.accept(ArsenalItems.EXAMPLE_ITEM.get());
                    })
                    .build());

    /** Utility class; registered tabs are exposed as static deferred holders. */
    private ArsenalCreativeTabs() {
    }
}

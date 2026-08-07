package com.fayetalerror.createarsenal.registry;

import com.fayetalerror.createarsenal.CreateArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/** Declares the mod's custom creative-mode tabs and their displayed contents. */
public final class ArsenalCreativeTabs {
    /** Content order for the tab. Keeping this list data-like makes additions local and reviewable. */
    private static final TabDefinition TAB_DEFINITION = loadDefinition();

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
                    .icon(() -> ArsenalItems.item(TAB_DEFINITION.icon()).getDefaultInstance())
                    // Populate the tab when Minecraft requests its item list.
                    .displayItems((parameters, output) -> TAB_DEFINITION.items()
                            .forEach(item -> output.accept(ArsenalItems.item(item))))
                    .build());

    /** Utility class; registered tabs are exposed as static deferred holders. */
    private ArsenalCreativeTabs() {
    }

    private static TabDefinition loadDefinition() {
        try (var stream = ArsenalCreativeTabs.class.getClassLoader()
                .getResourceAsStream("data/createarsenal/creative_tab.json")) {
            if (stream == null) throw new IllegalArgumentException("Missing creative tab definition");
            var json = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            return new TabDefinition(json.get("icon").getAsString(),
                    json.getAsJsonArray("items").asList().stream().map(element -> element.getAsString()).toList());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Unable to load creative tab definition", exception);
        }
    }

    private record TabDefinition(String icon, List<String> items) { }
}

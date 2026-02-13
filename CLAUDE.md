# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Viola's Wardrobe** is a Minecraft Forge 1.20.1 mod that adds decorative outfit items (currently maid outfit with headband, dress, and thigh highs) and a taming enchantment for improved animal affinity. The mod uses Mixin for code injection to modify rendering behavior and entity interactions.

- **Mod ID**: `violas_wardrobe`
- **Java Version**: 17
- **Forge Version**: 1.20.1-47.2.0
- **Mappings**: Parchment 2023.09.03-1.20.1

## Build and Development Commands

### Building
```bash
./gradlew build
```
The built JAR will be in `build/libs/`. The `reobfJar` task runs automatically after `jar`.

### Running
```bash
# Run Minecraft client with the mod loaded
./gradlew runClient

# Run dedicated server
./gradlew runServer

# Generate data (models, recipes, tags, etc.)
./gradlew runData

# Run game test server
./gradlew gameTestServer
```

Run configurations use separate working directories:
- Client: `run/client`
- Server: `run/server`
- Data: `run/data`
- Test Server: `run/testServer`

### Publishing
```bash
./gradlew publish
```
Publishes to local Maven repository at `mcmodsrepo/`.

## Architecture Overview

### Entry Points and Initialization Flow

1. **Main Mod Class**: `ViolasWardrobeForge`
   - Entry point annotated with `@Mod(ViolasWardrobeForge.MODID)`
   - Constructor calls `VWContent.modConstruction(modBus)` to register all content
   - Registers `onCommonSetup` event listener for vanilla compatibility setup

2. **Content Registration**: `VWContent`
   - `modConstruction(IEventBus)`: Initializes all deferred registers (items, enchantments, creative tabs)
   - `vanillaCompat()`: Configures vanilla compatibility (e.g., dispenser behavior for outfit items)

3. **Client Initialization**: `VWClient`
   - Annotated with `@Mod.EventBusSubscriber` for client-side only
   - Registers model layer definitions in `onRegisterLayers` event

### Package Structure

```
com.hexagram2021.violas_wardrobe/
├── ViolasWardrobeForge.java           # Main mod class
├── client/                            # Client-side only code
│   ├── VWClient.java                  # Client initialization
│   ├── VWLayerLocations.java          # Model layer location constants
│   ├── models/                        # Custom entity models
│   ├── layers/                        # Render layers
│   └── renderers/                     # Custom renderers
├── common/                            # Common (client + server) code
│   ├── VWContent.java                 # Content initialization
│   ├── items/                         # Custom items
│   │   ├── BaseOutfitItem.java        # Base class for outfit items
│   │   └── MaidOutfitItem.java        # Maid outfit implementation
│   ├── enchantments/                  # Custom enchantments
│   ├── registries/                    # Deferred registers
│   │   ├── VWItems.java               # Item registry
│   │   ├── VWEnchantments.java        # Enchantment registry
│   │   ├── VWEnchantmentCategories.java
│   │   ├── VWCreativeModeTabs.java    # Creative tab registry
│   │   └── VWItemTags.java            # Item tag definitions
│   └── utils/                         # Utility classes
└── mixin/                             # Mixin classes for code injection
    ├── taming/                        # Common mixins (entity taming)
    └── *RendererMixin.java            # Client mixins (rendering)
```

### Deferred Registration Pattern

All game content uses Forge's `DeferredRegister` pattern with custom wrapper classes:

```java
// In VWItems.java
private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

public static final ItemEntry<MaidOutfitItem> MAID_HEADBAND = ItemEntry.register(
    "maid_headband", () -> new MaidOutfitItem(EquipmentSlot.HEAD, new Item.Properties().stacksTo(1))
);

// ItemEntry is a custom record that wraps RegistryObject
public record ItemEntry<T extends Item>(RegistryObject<T> item) implements Supplier<T>, ItemLike {
    // Provides convenient access via get() and asItem()
}
```

**Key Points:**
- All registries follow this pattern: `VWItems`, `VWEnchantments`, `VWCreativeModeTabs`
- Each registry has an `init(IEventBus)` method called from `VWContent.modConstruction()`
- Custom wrapper classes (like `ItemEntry`) provide type-safe access and implement useful interfaces

### Mixin Usage

Mixins are configured in `src/main/resources/violas_wardrobe.mixins.json`:

- **Common mixins** (run on both client and server):
  - `taming.CatEntityMixin`, `taming.ParrotEntityMixin`, `taming.WolfEntityMixin`
  - Modify entity taming behavior based on taming enchantment

- **Client mixins** (client-only):
  - `AbstractZombieRendererMixin`, `CustomHeadLayerMixin`, `PlayerRendererMixin`
  - Inject custom rendering layers for outfit items

**Important:** When adding new mixins:
1. Add the class to `src/main/java/com/hexagram2021/violas_wardrobe/mixin/`
2. Register it in `violas_wardrobe.mixins.json` under `"mixins"` (common) or `"client"` (client-only)
3. Use appropriate mixin annotations (`@Mixin`, `@Inject`, `@ModifyVariable`, etc.)

### Client/Common Separation

- **Client package** (`client/`): Contains code that references client-only classes (rendering, models, GUI)
  - Must be annotated with `@Mod.EventBusSubscriber(value = Dist.CLIENT)` or similar
  - Never reference client classes from common code directly

- **Common package** (`common/`): Contains code that runs on both client and server
  - Items, enchantments, game logic, registries

- **Mixin package** (`mixin/`): Mixins are separated by target
  - Common mixins in `mixin/taming/` for entity behavior
  - Client mixins in `mixin/` root for rendering

## Key Patterns and Conventions

### Outfit Item Pattern

All outfit items extend `BaseOutfitItem` which implements `Equipable`:

```java
public class BaseOutfitItem extends Item implements Equipable {
    private final EquipmentSlot slot;

    // Implements swapWithEquipmentSlot for right-click equipping
    // Returns enchantment value of 10
    // Subclasses can override for custom behavior
}
```

Outfit items are registered with specific equipment slots (HEAD, CHEST, FEET) and stack size of 1.

### Resource Locations

- **Textures**: `assets/violas_wardrobe/textures/`
  - Item textures: `textures/item/`
  - Model textures: `textures/models/maid/`

- **Models**: `assets/violas_wardrobe/models/item/`

- **Lang files**: `assets/violas_wardrobe/lang/`
  - `en_us.json` (English)
  - `zh_cn.json` (Chinese)

- **Data**: `data/violas_wardrobe/`
  - Tags: `tags/items/`

### Access Transformers

The mod uses access transformers defined in `src/main/resources/META-INF/accesstransformer.cfg` to modify access levels of Minecraft classes. This is configured in `build.gradle`:

```gradle
minecraft {
    accessTransformer = file('src/main/resources/META-INF/accesstransformer.cfg')
}
```

## Important Constants

- **MODID**: `"violas_wardrobe"` (defined in `ViolasWardrobeForge.MODID`)
- **Mod Version**: Defined in `gradle.properties` as `mod_version=1.0.1`
- **Mixin Refmap**: `violas_wardrobe.refmap.json` (configured in `violas_wardrobe.mixins.json`)

## Development Notes

### Adding New Outfit Items

1. Create item class extending `BaseOutfitItem` or `MaidOutfitItem`
2. Register in `VWItems` using `ItemEntry.register()`
3. Add textures to `assets/violas_wardrobe/textures/item/`
4. Add model JSON to `assets/violas_wardrobe/models/item/`
5. Add translations to lang files
6. If needed, register dispenser behavior in `VWContent.vanillaCompat()`

### Adding New Enchantments

1. Create enchantment class in `common/enchantments/`
2. Register in `VWEnchantments` using `DeferredRegister`
3. Add translations to lang files
4. Implement enchantment logic (may require mixins for behavior modification)

### Mixin Development

- Use MixinExtras library (version 0.5.0) for advanced mixin features
- Mixin debug output is enabled (`debug.verbose = true`, `debug.export = true`)
- Exported mixins can be found in `build/` directory for debugging
- Always test mixins thoroughly as they modify vanilla code

### Data Generation

Run `./gradlew runData` to generate:
- Item models
- Block states
- Recipes
- Loot tables
- Tags
- Advancements

Generated resources are placed in `src/generated/resources/` and automatically included in the source set.

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Viola's Wardrobe** is a Minecraft Forge 1.20.1 mod that adds decorative outfit items and outfit-specific enchantments. The mod uses Mixin for code injection to modify rendering behavior and entity interactions.

**Current Content:**
- **Outfits**: Maid outfit (headband, dress, thigh highs) and JK uniform (summer top, skirt, shoes) in multiple variants
- **Enchantments**: 7 custom enchantments (Buoyant, Charming, Laceward, Light Footing, Lumina, Sweat Tracing, Taming)

**Technical Details:**
- **Mod ID**: `violas_wardrobe`
- **Java Version**: 17
- **Forge Version**: 1.20.1-47.2.0
- **Mappings**: Parchment 2023.09.03-1.20.1
- **MixinExtras**: 0.5.0 (for advanced mixin features)

## Build and Development Commands

### Building
```bash
# Windows
.\gradlew.bat build

# Linux/Mac
./gradlew build
```
The built JAR will be in `build/libs/`. The `reobfJar` task runs automatically after `jar`.

### Running
```bash
# Windows (use .\gradlew.bat for all commands below)
# Linux/Mac (use ./gradlew)

# Run Minecraft client with the mod loaded
gradlew runClient

# Run dedicated server
gradlew runServer

# Generate data (models, recipes, tags, etc.)
gradlew runData

# Run game test server
gradlew gameTestServer
```

Run configurations use separate working directories:
- Client: `run/client`
- Server: `run/server`
- Data: `run/data`
- Test Server: `run/testServer`

### Publishing
```bash
# Windows
.\gradlew.bat publish

# Linux/Mac
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
  - **Enchantment-related mixins**:
    - `buoyant.LivingEntityMixin` - Implements Buoyant enchantment (reduces fall damage, adds water walking)
    - `charming.VillagerEntityMixin` - Implements Charming enchantment (better villager trades)
    - `light_footing.PlayerEntityMixin` - Implements Light Footing enchantment (walk through berry bushes)
    - `lumina.MobEntityMixin` - Implements Lumina enchantment (passive mobs glow effect)
    - `sweat_tracing.BeeEntityMixin` - Implements Sweat Tracing enchantment (bee attraction)
    - `taming.CatEntityMixin`, `taming.ParrotEntityMixin`, `taming.WolfEntityMixin` - Implement Taming enchantment (improved animal taming)

- **Client mixins** (client-only):
  - `AbstractZombieRendererMixin`, `CustomHeadLayerMixin`, `PlayerRendererMixin`
  - Inject custom rendering layers for outfit items

**Important:** When adding new mixins:
1. Add the class to `src/main/java/com/hexagram2021/violas_wardrobe/mixin/`
2. Register it in `violas_wardrobe.mixins.json` under `"mixins"` (common) or `"client"` (client-only)
3. Use appropriate mixin annotations (`@Mixin`, `@Inject`, `@ModifyVariable`, etc.)
4. For enchantment logic, create a mixin that targets the relevant entity/player class

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
public abstract class BaseOutfitItem extends Item implements Equipable {
    private final EquipmentSlot slot;

    // Implements swapWithEquipmentSlot for right-click equipping
    // Returns enchantment value of 10
    // Provides abstract methods for inner/outer textures
    // Subclasses can override for custom behavior
}
```

**Current Implementations:**
- `MaidOutfitItem`: Maid outfit with white/black variants
- `JKUniformItem`: JK uniform with variant system (PURPLISH_BLUE, CREAM)

Outfit items are registered with specific equipment slots (HEAD, CHEST, LEGS, FEET) and stack size of 1.

### Enchantment Pattern

All enchantments extend vanilla `Enchantment` class and are registered in `VWEnchantments`:

```java
public class ExampleEnchantment extends Enchantment {
    public ExampleEnchantment() {
        super(Rarity.RARE, VWEnchantmentCategories.OUTFIT, new EquipmentSlot[]{...});
    }
    // Override methods for max level, compatibility, etc.
}
```

**Current Enchantments:**
1. **Buoyant** (轻飘飘) - Reduces fall damage, adds water walking
2. **Charming** (妩媚) - Improves villager trade prices
3. **Laceward** (纤护) - Protection enchantment for outfit items
4. **Light Footing** (步伐轻盈) - Allows walking through berry bushes without damage
5. **Lumina** (流光) - Makes passive mobs glow
6. **Sweat Tracing** (芳踪) - Attracts bees
7. **Taming** (动物亲和) - Increases animal taming success rate

Most enchantment logic is implemented via Mixins targeting specific entity classes.

### Resource Locations

- **Textures**: `assets/violas_wardrobe/textures/`
  - Item textures: `textures/item/`
  - Model textures: `textures/models/maid/` and `textures/models/jk_uniform/`

- **Models**: `assets/violas_wardrobe/models/item/`

- **Lang files**: `assets/violas_wardrobe/lang/`
  - `en_us.json` (English)
  - `zh_cn.json` (Chinese - primary language)

- **Data**: `data/violas_wardrobe/`
  - Tags: `tags/items/`, `tags/damage_type/`

- **Generated resources**: `src/generated/resources/`
  - Automatically included in source set
  - Generated by `runData` task

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

1. Create item class extending `BaseOutfitItem`
   - Override `getInnerTexture()` and `getOuterTexture()` methods
   - Can create variant system like `JKUniformItem` using enums
2. Register in `VWItems` using `ItemEntry.register()`
3. Add textures to `assets/violas_wardrobe/textures/item/` and model textures
4. Add model JSON to `assets/violas_wardrobe/models/item/`
5. Add translations to both `en_us.json` and `zh_cn.json` lang files
6. Register dispenser behavior in `VWContent.vanillaCompat()`
7. Create custom model class if needed (see `MaidHeadbandAndSkirtModel`, `JKUniformModel`)
8. Register model layer in `VWClient.onRegisterLayers()` event

### Adding New Enchantments

1. Create enchantment class in `common/enchantments/` extending `Enchantment`
2. Define rarity, category (usually `VWEnchantmentCategories.OUTFIT`), and applicable slots
3. Register in `VWEnchantments` using `DeferredRegister`
4. Add translations to lang files (both English and Chinese)
5. Implement enchantment logic:
   - For passive effects: Create mixin targeting relevant entity class
   - Place mixin in appropriate subpackage under `mixin/` (e.g., `mixin/charming/`)
   - Register mixin in `violas_wardrobe.mixins.json` under `"mixins"` array
6. Create damage type tags if needed (see `VWDamageTypeTags`)

### Mixin Development

- Use MixinExtras library (version 0.5.0) for advanced mixin features
- Mixin debug output is enabled (`debug.verbose = true`, `debug.export = true`)
- Exported mixins can be found in `build/` directory for debugging
- Always test mixins thoroughly as they modify vanilla code

### Data Generation

Run `gradlew runData` (Windows: `.\gradlew.bat runData`) to generate:
- Item models
- Block states
- Recipes
- Loot tables
- Tags
- Advancements

Generated resources are placed in `src/generated/resources/` and automatically included in the source set.

## Code Style and Conventions

This project follows specific Chinese-influenced coding standards (see global CLAUDE.md for details):

- **Indentation**: Use tabs, not spaces
- **Brace style**: K&R style (opening brace on same line)
- **Line length**: Max 150 characters per line
- **Method length**: Max 300 lines per method body
- **Comments**: Javadoc for all public classes and methods, written in Chinese with "喵~" suffix
- **Naming**:
  - Classes: UpperCamelCase (e.g., `MaidOutfitItem`)
  - Methods/variables: lowerCamelCase (e.g., `getInnerTexture`)
  - Constants: UPPER_SNAKE_CASE (e.g., `MAX_LEVEL`)
  - Packages: lowercase_with_underscores (e.g., `violas_wardrobe`)
- **Annotations**: Use `@Nullable` for nullable fields/parameters/returns, `@Override` for overridden methods

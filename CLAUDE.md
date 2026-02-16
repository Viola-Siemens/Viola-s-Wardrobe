# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Viola's Wardrobe** is a Minecraft Forge 1.20.1 mod that adds decorative outfit items and outfit-specific enchantments. The mod uses Mixin for code injection to modify rendering behavior and entity interactions.

**Current Content:**
- **Outfits**: Maid outfit (headband, dress, thigh highs) and JK uniform (summer top, skirt, shoes) in multiple variants
- **Accessories**: Six Thinking Hats (blue, yellow, black, red, white, green)
- **Enchantments**: 8 custom enchantments (Buoyant, Charming, Daylight Blooming, Laceward, Light Footing, Lumina, Sweat Tracing, Taming)

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
   - Registers both CLIENT and COMMON configs (VWClientConfig, VWCommonConfig)
   - Registers `onCommonSetup` event listener for vanilla compatibility setup

2. **Content Registration**: `VWContent`
   - `modConstruction(IEventBus)`: Initializes all deferred registers (items, enchantments, creative tabs, loot modifiers)
   - `vanillaCompat()`: Configures vanilla compatibility (e.g., dispenser behavior for outfit items)

3. **Client Initialization**: `VWClient`
   - Annotated with `@Mod.EventBusSubscriber` for client-side only
   - Registers model layer definitions in `onRegisterLayers` event

4. **Forge Event Handler**: `ForgeEventHandler`
   - Annotated with `@Mod.EventBusSubscriber` for Forge event bus
   - Handles runtime game events (entity spawning, living entity ticks, player attacks)
   - Implements enchantment effects that require event-based logic (Daylight Blooming, Lumina particles)
   - Manages mob spawning with outfit items based on configuration

### Package Structure

```
com.hexagram2021.violas_wardrobe/
├── ViolasWardrobeForge.java           # Main mod class
├── client/                            # Client-side only code
│   ├── VWClient.java                  # Client initialization
│   ├── VWLayerLocations.java          # Model layer location constants
│   ├── models/                        # Custom entity models
│   ├── layers/                        # Render layers
│   ├── renderers/                     # Custom renderers
│   └── config/                        # Client configuration
│       └── VWClientConfig.java        # Client-only config
├── common/                            # Common (client + server) code
│   ├── VWContent.java                 # Content initialization
│   ├── ForgeEventHandler.java         # Forge event bus handler (enchantment effects, mob spawning)
│   ├── items/                         # Custom items
│   │   ├── BaseOutfitItem.java        # Base class for outfit items
│   │   └── MaidOutfitItem.java        # Maid outfit implementation
│   ├── enchantments/                  # Custom enchantments
│   ├── registries/                    # Deferred registers
│   │   ├── VWItems.java               # Item registry
│   │   ├── VWEnchantments.java        # Enchantment registry
│   │   ├── VWEnchantmentCategories.java
│   │   ├── VWCreativeModeTabs.java    # Creative tab registry
│   │   ├── VWItemTags.java            # Item tag definitions
│   │   └── VWLootModifiers.java       # Loot modifier registry
│   ├── config/                        # Configuration
│   │   └── VWCommonConfig.java        # Common config (server+client)
│   ├── loot/                          # Loot modifiers
│   │   └── VillageChestModifier.java  # Village chest loot modifier
│   └── utils/                         # Utility classes
└── mixin/                             # Mixin classes for code injection
    ├── taming/                        # Common mixins (entity taming)
    ├── buoyant/                       # Buoyant enchantment mixin
    ├── charming/                      # Charming enchantment mixin
    ├── light_footing/                 # Light Footing enchantment mixin
    ├── lumina/                        # Lumina enchantment mixin
    ├── sweat_tracing/                 # Sweat Tracing enchantment mixin
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
- All registries follow this pattern: `VWItems`, `VWEnchantments`, `VWCreativeModeTabs`, `VWLootModifiers`
- Each registry has an `init(IEventBus)` method called from `VWContent.modConstruction()`
- Custom wrapper classes (like `ItemEntry`) provide type-safe access and implement useful interfaces

### Configuration System

The mod uses Forge's configuration system with two config files:

- **Common Config** (`VWCommonConfig`): Settings that apply to both client and server
  - Registered as `ModConfig.Type.COMMON`
  - Configuration file: `config/violas_wardrobe-common.toml`
  - **Available options**:
    - `MOBS_SPAWN_WITH_CLOTHES`: List of entity types that may spawn with outfit items (default: zombie, skeleton, husk, zombie_villager)
    - `POSSIBILITY_WITH_CLOTHES`: Probability of mobs spawning with clothes (default: 0.025, range: 0.0-1.0)
    - `CLOTHES_DROP_CHANCE`: Probability of outfit items dropping from mobs (default: 0.085, range: 0.0-1.0)
    - `VILLAGE_CHEST_CHANCE`: Probability of outfit items appearing in village chests (default: 0.375, range: 0.0-1.0)

- **Client Config** (`VWClientConfig`): Client-only settings (rendering, visual preferences)
  - Registered as `ModConfig.Type.CLIENT`
  - Configuration file: `config/violas_wardrobe-client.toml`

Both configs are registered in the `ViolasWardrobeForge` constructor using `ModLoadingContext.get().registerConfig()`.

### Mixin Usage

Mixins are configured in `src/main/resources/violas_wardrobe.mixins.json`:

- **Common mixins** (run on both client and server):
  - **Enchantment-related mixins**:
    - `buoyant.LivingEntityMixin` - Implements Buoyant enchantment (grants slow falling effect after falling a certain height)
    - `charming.VillagerEntityMixin` - Implements Charming enchantment (better villager trades)
    - `light_footing.PlayerEntityMixin` - Implements Light Footing enchantment (reduces hunger consumption from jumping and moving)
    - `lumina.MobEntityMixin` - Implements Lumina enchantment (leaves star particles and may confuse enemies to change attack targets)
    - `sweat_tracing.BeeEntityMixin` - Implements Sweat Tracing enchantment (helps bees collect nectar and produce honey faster)
    - `taming.CatEntityMixin`, `taming.ParrotEntityMixin`, `taming.WolfEntityMixin` - Implement Taming enchantment (improved animal taming)

- **Client mixins** (client-only):
  - `AbstractZombieRendererMixin`, `ArmorStandRendererMixin`, `CustomHeadLayerMixin`, `GiantMobRendererMixin`, `PiglinRendererMixin`, `PlayerRendererMixin`, `SkeletonRendererMixin`, `ZombieVillagerRendererMixin`
  - Inject custom rendering layers for outfit items on various entity types

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
  - Common mixins in subdirectories by enchantment name (e.g., `mixin/taming/`, `mixin/buoyant/`)
  - Client mixins in `mixin/` root for rendering

### Loot Modifiers

The mod includes custom loot modifiers registered in `VWLootModifiers` to add outfit items to various loot tables. These are initialized during mod construction and use Forge's Global Loot Modifier system.

**Current Loot Modifiers:**
- **VillageChestModifier**: Adds outfit items to village chest loot with configurable probability (controlled by `VILLAGE_CHEST_CHANCE` config option)

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
1. **Buoyant** (轻飘飘) - Grants slow falling effect after falling a certain height
2. **Charming** (妩媚) - Improves villager trade prices
3. **Daylight Blooming** (光合滋养) - Provides regeneration effects to entities under daylight when they can see the sky
4. **Laceward** (纤护) - Reduces magic damage
5. **Light Footing** (步伐轻盈) - Reduces hunger consumption from jumping and moving
6. **Lumina** (流光) - Leaves star particles on movement trail, may confuse enemies to change attack targets when attacking mobs in groups
7. **Sweat Tracing** (芳踪) - Helps nearby bees collect nectar and produce honey faster
8. **Taming** (动物亲和) - Increases animal taming success rate

**Enchantment Implementation:**
- Most enchantment logic is implemented via Mixins targeting specific entity classes
- Some enchantments (Daylight Blooming, Lumina particle effects) are implemented via Forge event handlers in `ForgeEventHandler.java`

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
- **Mod Version**: Defined in `gradle.properties` as `mod_version=1.0.2`
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
2. Define rarity, category (usually `VWEnchantmentCategories.OUTFIT` or `VWEnchantmentCategories.VIOLAS_WARDROBE_HEAD`), and applicable slots
3. Register in `VWEnchantments` using `DeferredRegister`
4. Add translations to lang files (both English and Chinese)
5. Implement enchantment logic using one of two approaches:
   - **Mixin approach** (for modifying specific entity behaviors):
     - Create mixin targeting relevant entity class
     - Place mixin in appropriate subpackage under `mixin/` (e.g., `mixin/charming/`)
     - Register mixin in `violas_wardrobe.mixins.json` under `"mixins"` array
   - **Forge event handler approach** (for effects that need event-based logic):
     - Add event handler method in `ForgeEventHandler.java`
     - Use `@SubscribeEvent` annotation
     - Examples: Daylight Blooming (LivingTickEvent), Lumina particles (LivingTickEvent)
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

### Adding New Loot Modifiers

1. Create loot modifier class in `common/loot/` implementing `IGlobalLootModifier`
2. Implement the `factory()` method returning a `Codec` for serialization
3. Register in `VWLootModifiers` using `DeferredRegister`
4. Add configuration data in `data/violas_wardrobe/loot_modifiers/`
5. Add loot modifier to `data/forge/loot_modifiers/global_loot_modifiers.json`

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

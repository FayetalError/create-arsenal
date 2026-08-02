# Create: Arsenal

Create: Arsenal is a NeoForge addon for the [Create](https://github.com/Creators-of-Create/Create) mod. It expands Create's progression with factory-crafted tools, weapons, armor, equipment, and their intermediate components.

The project is currently in early development.

## Current content

### Andesite Pickaxe

- Iron-level harvesting
- 650 durability
- 7.0 mining speed
- Repairable with Create Iron Sheets
- Custom GeckoLib model and texture

### Andesite Pickaxe Core

An intermediate component used to manufacture the Andesite Pickaxe.

Craft the core in a crafting table:

```text
AAA
 S
 S
```

- `A`: Create Andesite Alloy
- `S`: Minecraft Stick

Run the core through a Create sequenced assembly line to finish the pickaxe:

1. Deploy one Iron Sheet onto the core.
2. Repeat the deployment for three loops.
3. Receive one Andesite Pickaxe.

The process consumes three Iron Sheets in total.

### Andesite Axe

- Iron-level harvesting
- 650 durability
- 7.0 chopping speed
- Repairable with Create Iron Sheets
- Custom GeckoLib model and texture

### Andesite Axe Core

A non-stackable intermediate component for the Andesite Axe. Craft it in an axe-shaped pattern using three Andesite Alloys and two sticks:

```text
AA
AS
 S
```

Run the core through a Create sequenced assembly line to finish the axe:

1. Deploy one Iron Sheet onto the Andesite Axe Core.
2. Repeat the deployment sequence three times.
3. Receive one Andesite Axe.

The process consumes three Iron Sheets in total.

### Andesite Shovel

- Iron-level harvesting
- 650 durability
- 7.0 digging speed
- Repairable with Create Iron Sheets
- Custom GeckoLib model and texture

### Andesite Shovel Core

A non-stackable intermediate component for the Andesite Shovel. Craft it with one Andesite Alloy above two sticks:

```text
 A
 S
 S
```

Run the core through a Create sequenced assembly line to finish the shovel:

1. Deploy one Iron Sheet onto the Andesite Shovel Core.
2. Repeat the deployment sequence three times.
3. Receive one Andesite Shovel.

The process consumes three Iron Sheets in total.

### Andesite Hoe

- Iron-level harvesting
- 650 durability
- 7.0 farming speed
- Repairable with Create Iron Sheets
- Custom GeckoLib model and texture

### Andesite Hoe Core

A non-stackable intermediate component for the Andesite Hoe. Craft it in a hoe-shaped pattern using two Andesite Alloys and two sticks:

```text
AA
 S
 S
```

Run the core through a Create sequenced assembly line to finish the hoe:

1. Deploy one Iron Sheet onto the Andesite Hoe Core.
2. Repeat the deployment sequence three times.
3. Receive one Andesite Hoe.

The process consumes three Iron Sheets in total.

### Andesite Sword

- 6.5 total attack damage
- 1.6 attacks per second
- 650 durability
- Repairable with Create Iron Sheets
- Custom GeckoLib model and texture

### Andesite Sword Core

A non-stackable intermediate component for the Andesite Sword. Craft it with two Andesite Alloys above one stick:

```text
 A
 A
 S
```

Run the core through a Create sequenced assembly line to finish the sword:

1. Deploy one Iron Sheet onto the Andesite Sword Core.
2. Repeat the deployment sequence three times.
3. Receive one Andesite Sword.

The process consumes three Iron Sheets in total.

## Requirements

| Dependency | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.247 or newer |
| Create | 6.0.10 through 6.0.x |
| GeckoLib | 4.9.2 through 4.9.x |
| Java | 21 |

Both Create and GeckoLib are required on the client and server.

## Installation

1. Install NeoForge for Minecraft 1.21.1.
2. Install compatible versions of Create and GeckoLib.
3. Place the Create: Arsenal JAR in the Minecraft `mods` directory.
4. Start the game and find the items in the **Create: Arsenal** creative tab.

## Development

Clone the repository and build it with the included Gradle wrapper.

Windows PowerShell:

```powershell
.\gradlew.bat build
```

Linux or macOS:

```bash
./gradlew build
```

The finished JAR is written to `build/libs`.

Blockbench source projects and exports are kept in `Blockbench/`. Runtime GeckoLib assets are organized under category-specific folders in `src/main/resources/assets/createarsenal`.

## License

Create: Arsenal is available under the [MIT License](LICENSE).

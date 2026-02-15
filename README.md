# AntiTab

Blocks tab completion for non-op players (Spigot) or players without bypass permission (BungeeCord).

## Downloads

See [Releases](https://github.com/nectariepopa20/AntiTab/releases) for compiled JARs.

- **AntiTab-Spigot.jar** — Spigot 1.21.11, requires [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/).
- **AntiTab-Bungee.jar** — BungeeCord 1.21, no dependencies.

## Spigot

- Blocks tab completion for non-op players.
- Put in `plugins/` and install ProtocolLib.

## BungeeCord

- Blocks tab completion from backend servers unless the player has `antitab.bypass`.
- Put in your proxy `plugins/` folder.

## Build

```bash
mvn clean package
```

JARs: `spigot/target/AntiTab-Spigot.jar`, `bungee/target/AntiTab-Bungee.jar`.

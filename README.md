# AntiTab

Blocks tab completion for non-op players (Spigot) or players without bypass permission (BungeeCord).

## Downloads

See [Releases](https://github.com/nectariepopa20/AntiTab/releases) for compiled JARs.

- **AntiTab-Spigot.jar** — Spigot 1.21.11, requires [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/).
- **AntiTab-Bungee.jar** — BungeeCord 1.21, no dependencies.

## Spigot

You could set `tab-complete: -1` in **spigot.yml** to disable tab completion for everyone—but then **ops would lose it too**. This plugin blocks tab completion only for **non-ops**, so **ops keep tab complete** and everyone else does not.

- **Ops:** tab complete works (`/<tab>`, `/command <tab>`, etc.).
- **Non-ops:** no tab complete.
- Put the JAR in `plugins/` and install [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/).

## BungeeCord

Bungee has no built-in option like `spigot.yml`, so this plugin is needed. Only players with the **`antitab.bypass`** permission get tab complete; everyone else gets none.

- Put the JAR in your proxy `plugins/` folder.
- Grant `antitab.bypass` to players or groups that should have tab complete.

## Build

```bash
mvn clean package
```

JARs: `spigot/target/AntiTab-Spigot.jar`, `bungee/target/AntiTab-Bungee.jar`.

# Hytale Plugin Template

A simple Hytale server plugin template with Nix development environment.

## Prerequisites

- [Nix](https://nixos.org/download.html) with flakes enabled
- HytaleServer.jar (you must provide your own - see below)

## Setup

**IMPORTANT: You must provide your own HytaleServer.jar file.**

The HytaleServer.jar file is required for compilation but is not included in this repository (it's gitignored due to its large size). You need to:

1. Obtain the HytaleServer.jar file
2. Copy it to the project root directory:
   ```bash
   cp /path/to/your/HytaleServer.jar .
   ```

The project won't build without this file.

## Quick Start

### Development Environment

```bash
# Enter development shell
nix develop

# Build with Maven
mvn clean package

# The JAR will be in target/
ls -lh target/hytale-helloworld-0.1.0.jar
```

## Project Structure

```
.
├── flake.nix                  # Nix dev shell configuration
├── HytaleServer.jar           # Hytale server dependency (not in git)
├── pom.xml                    # Maven configuration
└── src/
    └── main/
        ├── java/
        │   └── com/dolphindalt/
        │       └── MyPlugin.java
        └── resources/
            └── manifest.json
```

## Customizing for Your Plugin

### 1. Update pom.xml

Edit `pom.xml` to customize your plugin:

```xml
<groupId>com.yourname.yourplugin</groupId>
<artifactId>your-plugin-name</artifactId>
<version>1.0.0</version>
<name>Your Plugin Name</name>
```

### 2. Update manifest.json

Edit `src/main/resources/manifest.json`:

```json
{
  "Group": "com.yourname",
  "Name": "YourPlugin",
  "Version": "1.0.0",
  "Description": "Your plugin description",
  "Main": "com.yourname.yourplugin.YourPlugin"
}
```

### 3. Create Your Plugin Class

Rename or create your main plugin class in `src/main/java/`:

```java
package com.yourname.yourplugin;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import jakarta.annotation.Nonnull;

public class YourPlugin extends JavaPlugin {
    private static YourPlugin instance;

    public YourPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    public static YourPlugin get() {
        return instance;
    }

    @Override
    protected void setup() {
        instance = this;
        getLogger().atInfo().log("YourPlugin setup complete!");
    }

    @Override
    protected void start() {
        getLogger().atInfo().log("YourPlugin started!");
    }

    @Override
    protected void shutdown() {
        getLogger().atInfo().log("YourPlugin shutting down!");
    }
}
```

### 4. Build Your Plugin

```bash
# Enter development shell
nix develop

# Build with Maven
mvn clean package

# Test your plugin by copying to server
cp target/your-plugin-name-*.jar /path/to/hytale/server/mods/
```

## Plugin Lifecycle

Hytale plugins follow a three-phase lifecycle:

1. **setup()** - Called during server initialization. Register components, systems, commands, and events here.
2. **start()** - Called after all plugins initialize. Load resources and validate assets here.
3. **shutdown()** - Called during server shutdown. Clean up resources here.

## Hytale Plugin API

### Logging

```java
getLogger().atInfo().log("Info message");
getLogger().atWarning().log("Warning message");
getLogger().atSevere().log("Error message");
```

The logger uses Google Flogger API. Always use `.atLevel().log()` pattern.

### Available Registries

Access through `JavaPlugin` class:
- Entity and chunk component/system registries (ECS)
- Command registry
- Event subscription system
- Entity type registry
- Task scheduling registry
- Asset and block state registries
- Codec registries

## Dependencies

The project uses:
- **Java 25** (Temurin JDK)
- **Maven** (provided by Nix)
- **Jakarta Annotations 3.0.0** (for `@Nonnull`)
- **JUnit 5.11.0** (test dependencies)

## Troubleshooting

### HytaleServer.jar Not Found

Ensure `HytaleServer.jar` is in the project root:

```bash
ls -lh HytaleServer.jar
```

Copy it if missing:

```bash
cp /path/to/HytaleServer.jar .
```

Note: This file is gitignored, so each developer needs their own copy.

### Java Version Issues

This template requires Java 25. If you see "invalid target release" errors, check:

```bash
# In development shell
java --version  # Should show Java 25
mvn --version   # Should show Java 25
```

## Resources

- [Hytale Documentation](https://hytale-docs.pages.dev/) - Unofficial community guides
- [Hytale Discord](https://discord.gg/jshWA2kRmF) - Community support
- [GitHub Repository](https://github.com/vulpeslab/hytale-docs) - Documentation source

## License

This template is provided as-is for Hytale plugin development.

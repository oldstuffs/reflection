[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
[![rultor](https://www.rultor.com/b/yegor256/rultor)](https://www.rultor.com/p/portlek/reflection)

[![Build Status](https://travis-ci.com/portlek/reflection.svg?branch=master)](https://travis-ci.com/portlek/reflection)
![Maven Central](https://img.shields.io/maven-central/v/io.github.portlek/reflection?label=version)

## Principles
- No code in constructors
- No mutable objects
- No static
- No public methods without contract (interface)
- No implementation inheritance
- No returns null (Optional)

## How to Use
```xml
<dependency>
  <groupId>io.github.portlek</groupId>
  <artifactId>reflection</artifactId>
  <version>${version}</version>
</dependency>
```
```gradle
implementation("io.github.portlek:reflection:${version}")
```
## Example usage
```java
final class Example {

  void clearKnownCommands() {
    new ClassOf<>(Bukkit.getServer())
      .methodByName("getCommandMap")
      .flatMap(refMethod -> refMethod.of(Bukkit.getServer()).call().map(o -> o instanceof CommandMap))
      .ifPresent(commandMap -> new ClassOf<>(commandMap)
        .methodByName("clearCommands")
        .ifPresent(clearCommandsMethod ->
          clearCommandsMethod.of(commandMap).call()));
  }
 
}
```

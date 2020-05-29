# Reflection

Simple and powerfull class/method/field/constructor manipulation!

[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
[![rultor](https://www.rultor.com/b/yegor256/rultor)](https://www.rultor.com/p/portlek/reflection)

[![Build Status](https://travis-ci.com/portlek/reflection.svg?branch=master)](https://travis-ci.com/portlek/reflection)
![Maven Central](https://img.shields.io/maven-central/v/io.github.portlek/master?label=version)

### Principles
- No code in constructors
- No mutable objects
- No static
- No public methods without contract (interface)
- No implementation inheritance
- No nulls (Optional)

### Using
```gradle
implementation("io.github.portlek:reflection:${version}")
```
```xml
<dependency>
    <groupId>io.github.portlek</groupId>
    <artifactId>reflection</artifactId>
    <version>${version}</version>
</dependency>
```

```java
public void clearKnownCommands() {
  new ClassOf<>(Bukkit.getServer())
    .findMethodByName("getCommandMap")
    .flatMap(refMethod -> refMethod.of(Bukkit.getServer()).call().map(o -> o instanceof CommandMap))
    .ifPresent(commandMap -> new ClassOf<>(commandMap)
      .findMethodByName("clearCommands")
      .ifPresent(clearCommandsMethod ->
          clearCommandsMethod.of(commandMap).call()));
}
```

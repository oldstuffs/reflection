# Reflection

Simple and powerfull class/method/field/constructor manipulation!
[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
[![rultor](https://www.rultor.com/b/yegor256/rultor)](https://www.rultor.com/p/portlek/reflection)

[![Build Status](https://travis-ci.com/portlek/reflection.svg?branch=master)](https://travis-ci.com/portlek/reflection)
![Maven Central](https://img.shields.io/maven-central/v/io.github.portlek/master?label=version)

### Principles
- No code in constructors
- No mutable objects
- No public static methods
- No public methods without contract (interface)
- No implementation inheritance
- No nulls (mock object)

### Using
```
<dependency>
    <groupId>io.github.portlek</groupId>
    <artifactId>reflection</artifactId>
    <version>${version}</version>
</dependency>
```

```java
public void clearKnownCommands() {
    final RefClass refClass = new ClassOf(Bukkit.getServer());
    final RefMethod refMethod = refClass.findMethodByName("getCommandMap");

    final Object commandMap = refMethod.of(Bukkit.getServer()).call(/*Fallback Object**/new MckObject());

    if (commandMap instanceof MckObject)
        return;

    final RefClass simpleCommandClass = new ClassOf(commandMap);
    final RefMethod clearCommandsMethod = simpleCommandClass.findMethodByName("clearCommands");
    
    clearCommandsMethod.of(commandMap).call(/*Fallback Object**/new MckObject());
}
```

[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

![master](https://github.com/portlek/reflection/workflows/build/badge.svg)
[![Release](https://jitpack.io/v/portlek/reflection.svg)](https://jitpack.io/#portlek/reflection)

## Principles

- No code in constructors
- No mutable objects
- No static
- No public methods without contract (interface)
- No implementation inheritance
- No returns null (Optional)

## How to Use

### Maven

```xml
<builds>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.2.4</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <minimizeJar>true</minimizeJar>
            <createDependencyReducedPom>false</createDependencyReducedPom>
            <!-- Relocations(Optional)
            <relocations>
              <relocation>
                <pattern>io.github.portlek.reflection</pattern>
                <shadedPattern>[YOUR_PLUGIN_PACKAGE].shade</shadedPattern>
              </relocation>
            </relocations>
            -->
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</builds>
```

```xml
<repositories>
  <repository>
    <id>jitpack</id>
    <url>https://jitpack.io/</url>
  </repository>
</repositories>
```

```xml
<dependencies>
  <dependency>
    <groupId>com.github.portlek</groupId>
    <artifactId>reflection</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

### Gradle

```groovy
plugins {
  id "com.github.johnrengelman.shadow" version "7.0.0"
}
```

```groovy
repositories {
  maven {
    url "https://jitpack.io"
  }
}
```

```groovy
dependencies {
  implementation("com.github.portlek:reflection:${version}")
}
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

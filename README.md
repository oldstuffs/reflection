# Reflection

Simple and powerfull class/method/field/constructor manipulation!

### Principles
- No code in constructors (why?)
- No mutable objects (why?)
- No public static methods (why?)
- No type casting, or reflection (why?)
- No public methods without contract (interface) (why?)
- No implementation inheritance (why? and why?)

### Using
```
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
</repository>

<dependency>
    <groupId>io.github.portlek</groupId>
    <artifactId>reflection</artifactId>
    <version>2.5</version>
</dependency>
```

```java
public void clearKnownCommands() {
    final RefClass refClass = new ClassOf(Bukkit.getServer());
    final RefMethod refMethod = refClass.findMethodByName("getCommandMap");
    
    final Object commandMap = refMethod.of(Bukkit.getServer()).call();
    
    if (commandMap == null)
        return;
    
    ((SimpleCommandMap)commandMap).clearCommands();
}
```

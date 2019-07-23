# Reflection

Simple reflection library to manage minecraft plugins.

### Using

```
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
</repository>

<dependency>
    <groupId>io.github.portlek</groupId>
    <artifactId>reflection</artifactId>
    <version>1.0</version>
</dependency>
```

```java
public void clearKnownCommands() {
    final RefClass refClass = new ClassOf(Bukkit.getServer());
    final RefMethod refMethod = refClass.findMethodByName("getCommandMap");
    
    if (refMethod instanceof MckMethod)
        return;
    
    final SimpleCommandMap commandMap = (SimpleCommandMap) refMethod.of(Bukkit.getServer()).call();
    
    if (commandMap == null)
        return;
    
    commandMap.clearCommands();
}
```

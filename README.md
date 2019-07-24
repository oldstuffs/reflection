# Reflection

Simple and powerfull class/method/field/constructor manipulation!

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

    final RefClass simpleCommandClass = new ClassOf(commandMap);
    final RefMethod clearCommandsMethod = simpleCommandClass.findMethodByName("clearCommands");
    
    clearCommandsMethod.call();
}
```

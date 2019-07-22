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
private final Reflection reflection = new Reflection(java.util.logging.Logger);

// Create object from class
reflection.newInstance(#Class<?>, #ConstructorArguments);

// Invoke a method
reflection.exec(#Object, "methodName", #MethodArguments);

// Invoke a static method
reflection.execStatic(#Class<?>, "methodName", #MethodArguments);

// Set the field 
reflection.setField(#Object, "fieldName");

// Get the field value
reflection.getField(#Object, "fieldName");

// Get the field from class
refleciton.getFieldFromClass(#Class<?>, "fieldName");
```

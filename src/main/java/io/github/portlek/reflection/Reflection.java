package io.github.portlek.reflection;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Reflection {

    private final Logger log;

    public Reflection(@NotNull final Logger log) {
        this.log = log;
    }

    public String getCraftBukkitVersion() {
        return cb().split("\\.")[3];
    }

    public String getNMSVersion(@NotNull final Object nmsObject) {
        return nmsObject.getClass().getPackage().getName().split("\\.")[3];
    }

    public String getNMSVersion() {
        return nms().split("\\.")[3];
    }

    public String nms() {
        Object nmsServer = exec(Bukkit.getServer(), "getServer");
        return nmsServer != null ? nmsServer.getClass().getPackage().getName() : "net.minecraft.server";
    }

    public String cb() {
        return Bukkit.getServer().getClass().getPackage().getName();
    }

    public String getPackageName(@NotNull final Object nmsObject) {
        return nmsObject.getClass().getPackage().getName();
    }

    public Class<?> getBukkitClass(@NotNull final Object craftObject) {
        Class clazz = craftObject.getClass();
        while (clazz != null && clazz.getCanonicalName().contains(".craftbukkit.")) {
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

    public <T> T execStatic(@NotNull final Class<?> clazz,
                            @NotNull final String methodName,
                            @NotNull final Object... args) {
        try {
            Class[] argTypes = new Class[args.length];
            int ix = 0;
            for (Object arg : args) {
                argTypes[ix++] = getBukkitClass(arg);
            }
            Method method = getMethod(clazz, methodName, argTypes);
            boolean wasAccessible = method.isAccessible();
            method.setAccessible(true);
            try {
                return (T) method.invoke(null, args);
            } finally {
                method.setAccessible(wasAccessible);
            }
        } catch (NoSuchMethodException e) {
            log.fine("Unable to locate method " + methodName + " on " + clazz);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.log(Level.INFO, "Calling " + methodName + " on " + clazz + " threw an exception", e);
        }
        return null;
    }

    public <T> T exec(@NotNull final Object obj,
                      @NotNull final String methodName,
                      @NotNull final Class[] argTypes,
                      @NotNull final Object... args) {
        Class<?> aClass = obj.getClass();
        try {
            Method method = getMethod(aClass, methodName, argTypes);
            boolean wasAccessible = method.isAccessible();
            method.setAccessible(true);
            try {
                return (T) method.invoke(obj, args);
            } finally {
                method.setAccessible(wasAccessible);
            }
        } catch (NoSuchMethodException | AbstractMethodError e) {
            log.fine("Unable to locate method " + methodName + "(" + Arrays.asList(argTypes) + ") on " + aClass);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.log(Level.INFO, "Calling " + methodName + " on " + obj + " threw an exception", e);
        }
        return null;
    }

    public Method getMethod(@NotNull final Class<?> aClass,
                            @NotNull final String methodName,
                            @NotNull final Class... argTypes) throws NoSuchMethodException {
        try {
            return aClass.getDeclaredMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            return aClass.getMethod(methodName, argTypes);
        }
    }

    public <T> T exec(@NotNull final Object obj,
                      @NotNull final String methodName,
                      @NotNull final Object... args) {
        Class[] argTypes = new Class[args.length];
        int ix = 0;
        for (Object arg : args) {
            argTypes[ix++] = arg.getClass();
        }
        return exec(obj, methodName, argTypes, args);
    }

    public <T> T getField(@NotNull final Object obj,
                          @NotNull final String fieldName) {
        try {
            Field field = getFieldInternal(obj, fieldName);
            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                return (T) field.get(obj);
            } finally {
                field.setAccessible(wasAccessible);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.fine("Unable to find field " + fieldName + " on " + obj);
        }
        return null;
    }

    private Field getFieldInternal(@NotNull final Object obj,
                                   @NotNull final String fieldName) throws NoSuchFieldException {
        return getFieldFromClass(obj.getClass(), fieldName);
    }

    private Field getFieldFromClass(@NotNull final Class<?> aClass,
                                    @NotNull final String fieldName) throws NoSuchFieldException {
        try {
            return aClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // Ignored
        }
        try {
            return aClass.getField(fieldName);
        } catch (NoSuchFieldException e) {
            // Ignore
        }
        return getFieldFromClass(aClass.getSuperclass(), fieldName);
    }

    public <T> void setField(@NotNull final Object obj,
                             @NotNull final String fieldName,
                             @NotNull final T field) {
        try {
            Field declaredField = getFieldInternal(obj, fieldName);
            boolean wasAccessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            try {
                declaredField.set(obj, field);
            } finally {
                declaredField.setAccessible(wasAccessible);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.fine("Unable to find field " + fieldName + " on " + obj);
        }
    }

    public <T> T newInstance(@NotNull final Class<?> clazz,
                             @NotNull final Object... args) {
        return newInstance(clazz.getName(), args);
    }

    public <T> T newInstance(@NotNull final String className,
                             @NotNull final Class<?>[] argTypes,
                             @NotNull final Object... args) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getDeclaredConstructor(argTypes);
            return (T) constructor.newInstance(args);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.warning("Unable to instantiate object of type " + className + ":" + e);
            return null;
        }
    }

    public <T> T newInstance(@NotNull final String className,
                             @NotNull final Object... args) {
        Class[] argTypes = new Class[args.length];
        int ix = 0;
        for (Object arg : args) {
            argTypes[ix++] = arg != null ? arg.getClass() : null;
        }
        return newInstance(className, argTypes, args);
    }

}
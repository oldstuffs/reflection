package io.github.portlek.reflection;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper methods that allow accesss to reflection for backward compatible code.
 *
 * @since 1.8
 */
public final class Reflection {

    private static final Logger LOGGER = new RefLogger(Reflection.class);

    public Reflection() {
    }

    /**
     * Returns the current version of the Bukkit implementation
     *
     * @return the current version of the Bukkit implementation
     * @since 1.8
     */
    public String getCraftBukkitVersion() {
        return cb().split("\\.")[3];
    }

    /**
     * Returns the current version of the net.minecraft.server implementation
     *
     * @param nmsObject A native object from nms namespace
     * @return the current version of the net.minecraft.server implementation
     * @since 1.8
     */
    public String getNMSVersion(@NotNull final Object nmsObject) {
        return nmsObject.getClass().getPackage().getName().split("\\.")[3];
    }

    /**
     * Returns the NMS version.
     *
     * @return the NMS version (i.e. "v1_10").
     * @since 1.9
     */
    public String getNMSVersion() {
        return nms().split("\\.")[3];
    }

    /**
     * Returns the packagename of the given object.
     *
     * @param nmsObject An object
     * @return the packagename of the given object.
     * @since 1.8
     */
    public String getPackageName(@NotNull final Object nmsObject) {
        return nmsObject.getClass().getPackage().getName();
    }

    /**
     * Returns the corresponding Bukkit class, given a CraftBukkit implementation object.
     *
     * @param craftObject A CraftBukkit implementation of a Bukkit class.
     * @return the corresponding Bukkit class, given a CraftBukkit implementation object.
     * @since 1.8
     */
    public Class<?> getBukkitClass(@NotNull final Object craftObject) {
        Class clazz = craftObject.getClass();
        while (clazz != null && clazz.getCanonicalName().contains(".craftbukkit.")) {
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

    /**
     * Uses reflection to execute the named method on the supplied class giving the arguments.
     * Sinks all exceptions, but log an entry and returns <code>null</code>
     *
     * @param clazz      The class on which to invoke the method
     * @param methodName The name of the method to invoke
     * @param args       The arguments to supply to the method
     * @return <code>null</code> or the return-object from the method.
     * @since 1.8
     */
    @Nullable
    @SuppressWarnings("unchecked")
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
            LOGGER.info("Unable to locate method " + methodName + " on " + clazz);
        } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.log(Level.INFO, "Calling " + methodName + " on " + clazz + " threw an exception", e);
        }
        return null;
    }

    /**
     * Uses reflection to execute the named method on the supplied class giving the arguments.
     * Sinks all exceptions, but log an entry and returns <code>null</code>
     *
     * @param obj        The object on which to invoke the method
     * @param methodName The name of the method to invoke
     * @param argTypes   An array of argument-types (classes).
     * @param args       The arguments to supply to the method
     * @return <code>null</code> or the return-object from the method.
     * @since 1.8
     */
    @Nullable
    @SuppressWarnings("unchecked")
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
            LOGGER.fine("Unable to locate method " + methodName + "(" + Arrays.asList(argTypes) + ") on " + aClass);
        } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.log(Level.INFO, "Calling " + methodName + " on " + obj + " threw an exception", e);
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

    @Nullable
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

    /**
     * Returns the value of a field on the object.
     *
     * @param obj       The object
     * @param fieldName The name of the field
     * @param <T>       The type of field
     * @return the value or <code>null</code>
     * @since 1.9
     */
    @Nullable
    @SuppressWarnings("unchecked")
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
        } catch (IllegalAccessException e) {
            LOGGER.fine("Unable to find field " + fieldName + " on " + obj);
        }
        return null;
    }

    /**
     * Sets the value of a field on the object.
     *
     * @param obj       The object
     * @param fieldName The name of the field
     * @param field     The value to set
     * @param <T>       The type of field
     * @since 1.9
     */
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
        } catch (IllegalAccessException e) {
            LOGGER.fine("Unable to find field " + fieldName + " on " + obj);
        }
    }

    public Field getFieldInternal(@NotNull final Object obj,
                                  @NotNull final String fieldName) {
        return getFieldFromClass(obj.getClass(), fieldName);
    }

    public Field getFieldFromClass(@NotNull final Class<?> aClass,
                                   @NotNull final String fieldName) {
        try {
            return aClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // Ignore...
        }
        try {
            return aClass.getField(fieldName);
        } catch (NoSuchFieldException e) {
            // Ignore...
        }
        return getFieldFromClass(aClass.getSuperclass(), fieldName);
    }

    /**
     * Instantiates an object.
     *
     * @param clazz The class
     * @param args  An array of arguments
     * @param <T>   Return-type
     * @return the object, or <code>null</code>.
     * @since 1.9
     */
    @Nullable
    public <T> T newInstance(@NotNull final Class<?> clazz,
                             @NotNull final Object... args) {
        return newInstance(clazz.getName(), args);
    }

    /**
     * Instantiates an object.
     *
     * @param className The name of the class
     * @param argTypes  An array of argument-types
     * @param args      An array of arguments
     * @param <T>       Return-type
     * @return the object, or <code>null</code>.
     * @since 1.9
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T newInstance(@NotNull final String className,
                             @NotNull final Class<?>[] argTypes,
                             @NotNull final Object... args) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getDeclaredConstructor(argTypes);
            return (T) constructor.newInstance(args);
        } catch (InstantiationException
            | ClassNotFoundException
            | IllegalAccessException
            | NoSuchMethodException
            | InvocationTargetException e) {

            LOGGER.warning("Unable to instantiate object of type " + className + ":" + e);
            return null;
        }
    }

    /**
     * Instantiates an object.
     *
     * @param className The name of the class
     * @param args      An array of arguments
     * @param <T>       Return-type
     * @return the object, or <code>null</code>.
     * @since 1.9
     */
    @Nullable
    public <T> T newInstance(@NotNull final String className,
                             @NotNull final Object... args) {
        Class[] argTypes = new Class[args.length];
        int ix = 0;
        for (Object arg : args) {
            argTypes[ix++] = arg.getClass();
        }
        return newInstance(className, argTypes, args);
    }

    @NotNull
    private String nms() {
        Object nmsServer = exec(Bukkit.getServer(), "getServer");
        return nmsServer != null ? nmsServer.getClass().getPackage().getName() : "net.minecraft.server";
    }

    @NotNull
    private String cb() {
        return Bukkit.getServer().getClass().getPackage().getName();
    }

}
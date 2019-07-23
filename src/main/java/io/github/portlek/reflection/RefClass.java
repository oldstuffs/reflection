package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefClass {

    /**
     * see {@link Class#isInstance(Object)}
     *
     * @param object the object to check
     * @return true if object is an instance of this class
     */
    boolean isInstance(@NotNull final Object object);

    /**
     * get existing method by name and types
     *
     * @param name  name
     * @param types method parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    RefMethod getMethod(@NotNull final String name, @NotNull final Object... types);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    RefConstructed getConstructor(@NotNull final Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    RefMethod findMethod(@NotNull final Object... types);

    /**
     * find method by name
     *
     * @param names possible names of method
     * @return RefMethod object
     */
    @NotNull
    RefMethod findMethodByName(@NotNull final String... names);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     */
    @NotNull
    RefMethod findMethodByReturnType(@NotNull final RefClass type);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     */
    @NotNull
    RefMethod findMethodByReturnType(@NotNull final Class type);

    /**
     * find constructor by number of arguments
     *
     * @param number number of arguments
     * @return RefConstructor
     */
    @NotNull
    RefConstructed findConstructor(final int number);

    /**
     * get field by name
     *
     * @param name field name
     * @return RefField
     */
    @NotNull
    RefField getField(@NotNull final String name);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     */
    @NotNull
    RefField findField(@NotNull final RefClass type);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     */
    @NotNull
    RefField findField(@NotNull final Class type);

}

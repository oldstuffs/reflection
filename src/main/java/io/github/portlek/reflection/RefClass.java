package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefClass {

    /**
     * see {@link Class#isInstance(Object)}
     *
     * @param object the object to check
     * @return true if object is an instance of this class
     */
    boolean isInstance(Object object);

    /**
     * get existing method by name and types
     *
     * @param name  name
     * @param types method parameters. can be Class or RefClass
     * @return RefMethod object
     * @throws RuntimeException if method not found
     */
    @NotNull
    RefMethod getMethod(String name, Object... types);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     * @throws RuntimeException if constructor not found
     */
    @NotNull
    RefConstructed getConstructor(Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     * @throws RuntimeException if method not found
     */
    @NotNull
    RefMethod findMethod(Object... types);

    /**
     * find method by name
     *
     * @param names possible names of method
     * @return RefMethod object
     * @throws RuntimeException if method not found
     */
    @NotNull
    RefMethod findMethodByName(String... names);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     * @throws RuntimeException if method not found
     */
    @NotNull
    RefMethod findMethodByReturnType(RefClass type);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     * @throws RuntimeException if method not found
     */
    @NotNull
    RefMethod findMethodByReturnType(Class type);

    /**
     * find constructor by number of arguments
     *
     * @param number number of arguments
     * @return RefConstructor
     * @throws RuntimeException if constructor not found
     */
    @NotNull
    RefConstructed findConstructor(int number);

    /**
     * get field by name
     *
     * @param name field name
     * @return RefField
     * @throws RuntimeException if field not found
     */
    @NotNull
    RefField getField(String name);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     * @throws RuntimeException if field not found
     */
    @NotNull
    RefField findField(RefClass type);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     * @throws RuntimeException if field not found
     */
    @NotNull
    RefField findField(Class type);

}

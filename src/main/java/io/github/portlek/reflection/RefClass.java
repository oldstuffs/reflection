package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RefClass<T> {

    /**
     * get passed class
     *
     * @return class
     */
    @NotNull
    Class<T> getRealClass();

    /**
     * see {@link Class#isInstance(Object)}
     *
     * @param object the object to check
     * @return true if object is an instance of this class
     */
    boolean isInstance(@NotNull Object object);

    /**
     * get existing method by name and types
     *
     * @param name name
     * @param types method parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefMethod> getPrimitiveMethod(@NotNull String name, @NotNull Object... types);

    /**
     * get existing method by name and types
     *
     * @param name name
     * @param types method parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefMethod> getMethod(@NotNull String name, @NotNull Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefMethod> findPrimitiveMethodByParameter(@NotNull Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefMethod> findMethodByParameter(@NotNull Object... types);

    /**
     * find method by name
     *
     * @param names possible names of method
     * @return RefMethod object
     */
    @NotNull
    Optional<RefMethod> findMethodByName(@NotNull String... names);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     */
    @NotNull
    <X> Optional<RefMethod> findMethodByReturnType(@NotNull RefClass<X> type);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return RefMethod
     */
    @NotNull
    Optional<RefMethod> findMethodByReturnType(@NotNull Class<?> type);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefConstructed<T>> getPrimitiveConstructor(@NotNull Object... types);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return RefMethod object
     */
    @NotNull
    Optional<RefConstructed<T>> getConstructor(@NotNull Object... types);

    /**
     * find constructor by number of arguments
     *
     * @param number number of arguments
     * @return RefConstructor
     */
    @NotNull
    Optional<RefConstructed<T>> findConstructor(int number);

    /**
     * get field by name
     *
     * @param name field name
     * @return RefField
     */
    @NotNull
    Optional<RefField> getField(@NotNull String name);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     */
    @NotNull
    <X> Optional<RefField> findField(@NotNull RefClass<X> type);

    /**
     * find field by type
     *
     * @param type field type
     * @return RefField
     */
    @NotNull
    Optional<RefField> findField(@NotNull Class<?> type);

}

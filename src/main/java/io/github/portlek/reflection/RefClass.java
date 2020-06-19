package io.github.portlek.reflection;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public interface RefClass<T> extends RefAnnotated {

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param <A> TODO Javadoc.
     * @return TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> List<RefField> fieldsWithAnnotation(@NotNull final Class<A> annotationClass) {
        return this.fields().stream()
            .filter(refField -> refField.hasAnnotation(annotationClass))
            .collect(Collectors.toList());
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param consumer TODO Javadoc.
     * @param <A> TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> void fieldsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                             @NotNull final BiConsumer<RefField, Annotation> consumer) {
        this.fields().forEach(refField ->
            refField.annotation(annotationClass, a -> consumer.accept(refField, a)));
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param <A> TODO Javadoc.
     * @return TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> List<RefField> declaredFieldsWithAnnotation(
        @NotNull final Class<A> annotationClass) {
        return this.declaredFields().stream()
            .filter(refField -> refField.hasAnnotation(annotationClass))
            .collect(Collectors.toList());
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param consumer TODO Javadoc.
     * @param <A> TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> void declaredFieldsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                                     @NotNull final BiConsumer<RefField, Annotation> consumer) {
        this.declaredFields().forEach(refField ->
            refField.annotation(annotationClass, a -> consumer.accept(refField, a)));
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param <A> TODO Javadoc.
     * @return TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> List<RefMethod> methodsWithAnnotation(@NotNull final Class<A> annotationClass) {
        return this.methods().stream()
            .filter(refField -> refField.hasAnnotation(annotationClass))
            .collect(Collectors.toList());
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param consumer TODO Javadoc.
     * @param <A> TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> void methodsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                              @NotNull final BiConsumer<RefMethod, Annotation> consumer) {
        this.methods().forEach(refField ->
            refField.annotation(annotationClass, a -> consumer.accept(refField, a)));
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param <A> TODO Javadoc.
     * @return TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> List<RefMethod> declaredMethodsWithAnnotation(
        @NotNull final Class<A> annotationClass) {
        return this.declaredMethods().stream()
            .filter(refMethod -> refMethod.hasAnnotation(annotationClass))
            .collect(Collectors.toList());
    }

    /**
     * TODO Javadoc.
     *
     * @param annotationClass TODO Javadoc.
     * @param consumer TODO Javadoc.
     * @param <A> TODO Javadoc.
     */
    @NotNull
    default <A extends Annotation> void declaredMethodsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                                      @NotNull final BiConsumer<RefMethod, Annotation> consumer) {
        this.declaredMethods().forEach(refField ->
            refField.annotation(annotationClass, a -> consumer.accept(refField, a)));
    }

    /**
     * get passed class
     *
     * @return class
     */
    @NotNull
    Class<T> realClass();

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
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> primitiveMethod(@NotNull String name, @NotNull Object... types);

    /**
     * get existing method by name and types
     *
     * @param name name
     * @param types method parameters. can be Class or RefClass
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> method(@NotNull String name, @NotNull Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> primitiveMethodByParameter(@NotNull Object... types);

    /**
     * find method by type parameters
     *
     * @param types parameters. can be Class or RefClass
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> methodByParameter(@NotNull Object... types);

    /**
     * find method by name
     *
     * @param names possible names of method
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> methodByName(@NotNull String... names);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return a {@link RefMethod} object
     */
    @NotNull <X> Optional<RefMethod> methodByReturnType(@NotNull RefClass<X> type);

    /**
     * find method by return value
     *
     * @param type type of returned value
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefMethod> methodByReturnType(@NotNull Class<?> type);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefConstructed<T>> primitiveConstructor(@NotNull Object... types);

    /**
     * get existing constructor by types
     *
     * @param types parameters. can be Class or RefClass
     * @return a {@link RefMethod} object
     */
    @NotNull
    Optional<RefConstructed<T>> constructor(@NotNull Object... types);

    /**
     * find constructor by number of arguments
     *
     * @param number number of arguments
     * @return a {@link RefConstructed}
     */
    @NotNull
    Optional<RefConstructed<T>> constructor(int number);

    /**
     * get field by name
     *
     * @param name field name
     * @return a {@link RefField}
     */
    @NotNull
    Optional<RefField> field(@NotNull String name);

    /**
     * find field by type
     *
     * @param type field type
     * @return a {@link RefField}
     */
    @NotNull <X> Optional<RefField> field(@NotNull RefClass<X> type);

    /**
     * find field by type
     *
     * @param type field type
     * @return a {@link RefField}
     */
    @NotNull
    Optional<RefField> field(@NotNull Class<?> type);

    /**
     * find all fields
     *
     * @return all fields of the class
     */
    @NotNull
    List<RefField> fields();

    /**
     * find all declared fields
     *
     * @return all declared fields of the class
     */
    @NotNull
    List<RefField> declaredFields();

    /**
     * find all methods
     *
     * @return all methods of the class
     */
    @NotNull
    List<RefMethod> methods();

    /**
     * find all declared methods
     *
     * @return all declared methods of the class
     */
    @NotNull
    List<RefMethod> declaredMethods();

}

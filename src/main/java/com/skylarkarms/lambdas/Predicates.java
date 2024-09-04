package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.*;

/**
 * A generic interface representing a predicate with a specific type.
 * <p>
 * The {@code Predicates} interface serves as a base for defining predicates that operate on various primitive types.
 * Implementations of this interface should provide a {@code toStandard} method that returns a {@link Predicate}
 * instance.
 *
 * @param <T> the type of values that this predicate operates on
 */
@FunctionalInterface
public interface Predicates<T> extends Predicate<T>, Function<T, Boolean> {

    /**
     * This method must be implemented by all inheritors
     * @throws IllegalStateException if not implemented by the subclass
     * @see Predicate#test(Object)
     * */
    @Override
    boolean test(T t);

    @Override
    default Boolean apply(T t) {
        return test(t);
    }

    static <E> Predicates<E> map(Predicate<E> predicate) {
        return new Predicates<>() {
            @Override
            public boolean test(E e) {
                return predicate.test(e);
            }

            @Override
            public boolean equals(Object obj) {
                return Objects.equals(obj, predicate) || Objects.equals(obj, this);
            }
        };
    }

    default boolean isDefaultFalse() {
        return false;
    }

    default boolean isDefaultTrue() {
        return false;
    }

    default boolean isDefault() {
        return false;
    }

    static <T> Predicates<T> defaultTrue() {
        return Lambdas.Predicates.alwaysTrue();
    }

    static <T> Predicates<T> defaultFalse() {
        return Lambdas.Predicates.alwaysFalse();
    }

    @FunctionalInterface interface OfInt extends Predicates<Integer>, IntPredicate {
        boolean test(int anInt);

        @Override
        default boolean test(Integer aByte) { return test((int)aByte); }

        @Override
        default Boolean apply(Integer integer) { return test((int)integer); }

        @Override
        default OfInt and(Predicate<? super Integer> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfInt negate() {
            return t -> !test(t);
        }

        default OfInt and(OfInt other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfInt or(Predicate<? super Integer> other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }

        default OfInt or(OfInt other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }
    }
    @FunctionalInterface interface OfDouble extends Predicates<Double>, DoublePredicate {
        boolean test(double aDouble);

        @Override
        default boolean test(Double aDouble) { return test((double)aDouble); }

        @Override
        default Boolean apply(Double aDouble) { return test((double)aDouble); }

        @Override
        default OfDouble and(Predicate<? super Double> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfDouble negate() { return t -> !test(t); }

        default OfDouble and(OfDouble other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfDouble or(Predicate<? super Double> other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }

        default OfDouble or(OfDouble other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }
    }
    @FunctionalInterface interface OfLong extends Predicates<Long>, LongPredicate {
        boolean test(long aDouble);

        @Override
        default boolean test(Long aDouble) { return test((long)aDouble); }

        @Override
        default Boolean apply(Long aDouble) { return test((long)aDouble); }

        @Override
        default OfLong and(Predicate<? super Long> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfLong negate() { return t -> !test(t); }

        default OfLong and(OfLong other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfLong or(Predicate<? super Long> other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }

        default OfLong or(OfLong other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }
    }
    /**
     * A predicate interface for {@code byte} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@code Byte}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<Byte>} instance.
     */
    @FunctionalInterface interface OfByte extends Predicates<Byte> {
        /**
         * Tests the specified {@code byte} value.
         *
         * @param aByte the {@code byte} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(byte aByte);

        @Override
        default boolean test(Byte aByte) { return test((byte)aByte); }

        @Override
        default OfByte and(Predicate<? super Byte> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfByte negate() { return t -> !test(t); }

        default OfByte and(OfByte other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfByte or(Predicate<? super Byte> other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }

        default OfByte or(OfByte other) {
            Objects.requireNonNull(other);
            return t -> test(t) || other.test(t);
        }
    }
    /**
     * A predicate interface for {@code short} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@code Short}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<Short>} instance.
     */
    @FunctionalInterface interface OfShort extends Predicates<Short> {
        /**
         * Tests the specified {@code short} value.
         *
         * @param aShort the {@code short} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(short aShort);

        @Override
        default boolean test(Short aShort) { return test((short) aShort); }

        @Override
        default OfShort negate() { return t -> !test(t); }

        default OfShort and(OfShort other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfShort and(Predicate<? super Short> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }
    }
    /**
     * A predicate interface for {@code float} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@code Float}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<Float>} instance.
     */
    @FunctionalInterface interface OfFloat extends Predicates<Float> {
        /**
         * Tests the specified {@code float} value.
         *
         * @param aFloat the {@code float} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(float aFloat);

        @Override
        default boolean test(Float aFloat) { return test((float) aFloat); }

        @Override
        default OfFloat negate() { return t -> !test(t); }

        default OfFloat and(OfFloat other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfFloat and(Predicate<? super Float> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }
    }
    /**
     * A predicate interface for {@code char} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@code Character}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<Character>} instance.
     */
    @FunctionalInterface interface OfChar extends Predicates<Character>{
        /**
         * Tests the specified {@code char} value.
         *
         * @param aChar the {@code char} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(char aChar);

        @Override
        default boolean test(Character character) { return test((char) character); }

        @Override
        default OfChar negate() { return t -> !test(t); }

        default OfChar and(OfChar other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfChar and(Predicate<? super Character> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }
    }
    /**
     * A predicate interface for {@link String} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@link String}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<String>} instance.
     */
    @FunctionalInterface interface OfBoolean extends Predicates<Boolean>{
        /**
         * Tests the specified {@link String} value.
         *
         * @param value the {@link Boolean} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(boolean value);

        @Override
        default boolean test(Boolean aBoolean) { return test((boolean)aBoolean); }

        @Override
        default OfBoolean negate() { return t -> !test(t); }

        default OfBoolean and(OfBoolean other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        @Override
        default OfBoolean and(Predicate<? super Boolean> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }

        /**
         * Represents an operation that accepts a single boolean primitive argument and returns no
         * result. Unlike most other functional interfaces, {@code Consumer} is expected
         * to operate via side-effects.
         *
         * <p>This is a <a href="package-summary.html">functional interface</a>
         * whose functional method is {@link #accept(boolean)}.
         */
        @FunctionalInterface
        interface Consumer extends OfBoolean, Consumers.OfBoolean {

            @Override
            default boolean test(boolean value) {
                accept(value);
                return value;
            }

            @Override
            default boolean isDefault() { return false; }

            /**
             * Performs this operation on the given boolean argument.
             *
             * @param aBoolean the boolean input argument
             */
            void accept(boolean aBoolean);

            @Override
            default void accept(Boolean value) { accept((boolean)value); }

            static Consumer getDefault() {
                return Lambdas.Consumers.defaultForBoolean();
            }

        }
    }
    /**
     * A predicate interface for {@link String} values.
     * <p>
     * This interface extends {@link Predicates} with the specific type of {@link String}.
     * It overrides the {@code toStandard} method to return a {@link Predicate<String>} instance.
     */
    @FunctionalInterface interface OfString extends Predicates<String> {
        /**
         * Tests the specified {@link String} value.
         *
         * @param s the {@link String} value to be tested
         * @return {@code true} if the value matches the predicate condition; {@code false} otherwise
         */
        boolean test(String s);


        @Override
        default OfString negate() { return t -> !test(t); }

        @Override
        default OfString and(Predicate<? super String> other) {
            Objects.requireNonNull(other);
            return t -> test(t) && other.test(t);
        }
    }
}

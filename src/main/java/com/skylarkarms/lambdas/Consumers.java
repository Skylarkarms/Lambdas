package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.*;

/**
 * A generic interface representing a consumer of values with a specific type.
 * <p>
 * The {@code Consumers} interface serves as a base for defining consumers that operate on various types.
 * Implementations of this interface should provide a {@code toStandard} method that returns a {@link Consumer}
 * instance.
 *
 * @param <T> the type of values that this consumer operates on
 */
@FunctionalInterface
public interface Consumers<T> extends Consumer<T> {

    default boolean isDefault() { return false; }

    static<E> Consumers<E> getDefaultEmpty() { return Lambdas.Consumers.getDefaultEmpty(); }

    /**
     * Can be directly referenced compared with the '{@code consumer}' parameter via {@link Object#equals(Object)}.
     * */
    static<S, T> Consumer<S> map(Function<S, T> map, Consumer<? super T> consumer) {
        return new Consumer<>() {
            @Override
            public void accept(S s) { consumer.accept(map.apply(s)); }

            @Override
            public boolean equals(Object obj) {
                return
                        this == obj
                                || Objects.equals(obj, consumer);
            }
        };
    }

    /**
     * Will accept the value if the {@code expect} {@link Predicate} test has been passed successfully ({@code true})
     * */
    static<T> Consumer<T> test(Predicate<T> expect, Consumer<? super T> consumer) {
        return new Consumer<>() {
            @Override
            public void accept(T s) { if (expect.test(s)) consumer.accept(s); }

            @Override
            public boolean equals(Object obj) {
                return
                        this == obj
                                || Objects.equals(obj, consumer);
            }
        };
    }
    @FunctionalInterface interface OfInt extends Consumers<Integer>, IntConsumer {
        /**
         * Accepts the specified {@code byte} value.
         *
         * @param anInt the {@code byte} value to be consumed
         */
        void accept (int anInt);

        @Override
        default void accept(Integer anInt) { accept((int) anInt); }

        @Override
        default OfInt andThen(Consumer<? super Integer> after){
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        @Override
        default OfInt andThen(IntConsumer after) {
            Objects.requireNonNull(after);
            return (int t) -> { accept(t); after.accept(t); };
        }

        default OfInt andThen(OfInt after) {
            Objects.requireNonNull(after);
            return (int t) -> { accept(t); after.accept(t); };
        }
    }
    @FunctionalInterface interface OfDouble extends Consumers<Double>, DoubleConsumer {
        void accept (double aDouble);

        @Override
        default void accept(Double aDouble) { accept((double) aDouble); }

        @Override
        default OfDouble andThen(Consumer<? super Double> after){
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        @Override
        default OfDouble andThen(DoubleConsumer after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        default OfDouble andThen(OfDouble after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }
    }
    @FunctionalInterface interface OfLong extends Consumers<Long>, LongConsumer {
        void accept (long aLong);

        @Override
        default void accept(Long aLong) { accept((long) aLong); }

        @Override
        default OfLong andThen(Consumer<? super Long> after){
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        @Override
        default OfLong andThen(LongConsumer after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        default OfLong andThen(OfLong after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }
    }
    /**
     * A functional interface for consuming {@code byte} values.
     * <p>
     * This interface extends {@link Consumers} with the specific type of {@code Byte}.
     * It overrides the {@code toStandard} method to return a {@link Consumer<Byte>} instance.
     */
    @FunctionalInterface interface OfByte extends Consumers<Byte> {
        /**
         * Accepts the specified {@code byte} value.
         *
         * @param aByte the {@code byte} value to be consumed
         */
        void accept (byte aByte);

        @Override
        default void accept(Byte aByte) { accept((byte) aByte); }

        @Override
        default OfByte andThen(Consumer<? super Byte> after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        default OfByte andThen(OfByte after) {
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }
    }
    /**
     * A functional interface for consuming {@code short} values.
     * <p>
     * This interface extends {@link Consumers} with the specific type of {@code Short}.
     * It overrides the {@code toStandard} method to return a {@link Consumer<Short>} instance.
     */
    @FunctionalInterface interface OfShort extends Consumers<Short> {
        /**
         * Accepts the specified {@code short} value.
         *
         * @param aShort the {@code short} value to be consumed
         */
        void accept(short aShort);

        @Override
        default void accept(Short aShort) { accept((short) aShort); }

        @Override
        default OfShort andThen(Consumer<? super Short> after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }

        default OfShort andThen(OfShort after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }
    }
    /**
     * A functional interface for consuming {@code float} values.
     * <p>
     * This interface extends {@link Consumers} with the specific type of {@code Float}.
     * It overrides the {@code toStandard} method to return a {@link Consumer<Float>} instance.
     */
    @FunctionalInterface interface OfFloat extends Consumers<Float> {
        /**
         * Accepts the specified {@code float} value.
         *
         * @param aFloat the {@code float} value to be consumed
         */
        void accept(float aFloat);

        @Override
        default void accept(Float aFloat) { accept((float)aFloat); }

        @Override
        default OfFloat andThen(Consumer<? super Float> after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }

        default OfFloat andThen(OfFloat after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }
    }

    @FunctionalInterface interface OfBoolean extends Consumers<Boolean> {
        /**
         * Accepts the specified {@code float} value.
         *
         * @param aFloat the {@code float} value to be consumed
         */
        void accept(boolean aFloat);

        @Override
        default void accept(Boolean value) { accept((boolean)value); }

        @Override
        default OfBoolean andThen(Consumer<? super Boolean> after){
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }

        default OfBoolean andThen(OfBoolean after){
            Objects.requireNonNull(after);
            return (t) -> { accept(t); after.accept(t); };
        }
    }
    /**
     * A functional interface for consuming {@code String} values.
     * <p>
     * This interface extends {@link Consumers} with the specific type of {@code String}.
     * It overrides the {@code toStandard} method to return a {@link Consumer<String>} instance.
     */
    @FunctionalInterface interface OfString extends Consumers<String> {

        @Override
        default OfString andThen(Consumer<? super String> after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }

        default OfString andThen(OfString after) {
            Objects.requireNonNull(after);
            return t -> { accept(t); after.accept(t); };
        }
    }
}

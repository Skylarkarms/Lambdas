package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.Function;

public interface ToStringFunction<T> extends Function<T, String> {

    @Override
    default <V> ToStringFunction<V> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default boolean isValueOf() {
        return false;
    }

    static<T> ToStringFunction<T> valueOf() {
        return Lambdas.ToString.valueOf();
    }


    @FunctionalInterface interface Int extends ToStringFunction<Integer>, Funs.From.Int<java.lang.String> {
        java.lang.String apply(int value);
        @Override
        default java.lang.String apply(Integer integer) {
            return apply((int)integer);
        }

        static Int valueOf() {
            return Lambdas.ToString.fromInt();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends Integer> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((int)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Int<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsInt(v));
        }

        @Override
        default <V> Funs.From.Int<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Int<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Long extends ToStringFunction<java.lang.Long>, Funs.From.Long<java.lang.String> {
        java.lang.String apply(long value);
        @Override
        default java.lang.String apply(java.lang.Long aLong) {
            return apply((long) aLong);
        }

        static Long valueOf() {
            return Lambdas.ToString.fromLong();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends java.lang.Long> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((long)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Long<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsLong(v));
        }

        @Override
        default <V> Funs.From.Long<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Long<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Double extends ToStringFunction<java.lang.Double>, Funs.From.Double<java.lang.String> {
        java.lang.String apply(double value);
        @Override
        default java.lang.String apply(java.lang.Double aDouble) {
            return apply((double) aDouble);
        }

        static Double valueOf() {
            return Lambdas.ToString.fromDouble();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends java.lang.Double> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((double)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Double<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsDouble(v));
        }

        @Override
        default <V> Funs.From.Double<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Double<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Byte extends ToStringFunction<java.lang.Byte>, Funs.From.Byte<java.lang.String> {

        java.lang.String apply(byte value);
        @Override
        default java.lang.String apply(java.lang.Byte aByte) {
            return apply((byte) aByte);
        }

        static Byte valueOf() {
            return Lambdas.ToString.fromByte();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends java.lang.Byte> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((byte)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Byte<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsByte(v));
        }

        @Override
        default <V> Funs.From.Byte<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Byte<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Float extends ToStringFunction<java.lang.Float>, Funs.From.Float<java.lang.String> {

        java.lang.String apply(float value);
        @Override
        default java.lang.String apply(java.lang.Float aFloat) {
            return apply((float) aFloat);
        }

        static Float valueOf() {
            return Lambdas.ToString.fromFloat();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends java.lang.Float> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((float)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Float<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsFloat(v));
        }

        @Override
        default <V> Funs.From.Float<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Float<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Short extends ToStringFunction<java.lang.Short>, Funs.From.Short<java.lang.String> {

        java.lang.String apply(short value);
        @Override
        default java.lang.String apply(java.lang.Short aShort) {
            return apply((short) aShort);
        }

        static Short valueOf() {
            return Lambdas.ToString.fromShort();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends java.lang.Short> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((short)before.apply(v));
        }

        default <V> ToStringFunction<V> compose(Funs.To.Short<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsShort(v));
        }

        @Override
        default <V> Funs.From.Short<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Short<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }

    @FunctionalInterface interface Char extends ToStringFunction<Character>, Funs.From.Char<java.lang.String> {

        java.lang.String apply(char value);
        @Override
        default java.lang.String apply(Character character) {
            return apply((char) character);
        }

        static Char valueOf() {
            return Lambdas.ToString.fromChar();
        }

        @Override
        default <V> ToStringFunction<V> compose(Function<? super V, ? extends Character> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply((char)before.apply(v));
        }


        default <V> ToStringFunction<V> compose(Funs.To.Char<? super V> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.applyAsChar(v));
        }

        @Override
        default <V> Funs.From.Char<V> andThen(Function<? super java.lang.String, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }

        default <V> Funs.From.Char<V> andThen(Funs.From.String<? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> after.apply(apply(t));
        }
    }
}

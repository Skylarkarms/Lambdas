package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.*;

public final class Funs { private Funs() {}

    public interface Unaries<E> extends Function<E, E>, UnaryOperator<E> {

        default boolean IsIdentity() { return false; }

        static<E> Unaries<E> identity() { throw new IllegalStateException("Must be implemented if used."); }

        @FunctionalInterface interface OfInt extends Unaries<Integer>, From.Int<Integer>, To.Int<Integer>, IntUnaryOperator {
            int applyAsInt(int value);

            @Override
            default int applyAsInt(Integer value) { return applyAsInt((int)value); }

            @Override
            default Integer apply(int value) { return applyAsInt(value); }

            @Override
            default Integer apply(Integer value) { return applyAsInt((int)value); }

            static OfInt identity() { return Lambdas.Identities.ofInt(); }

            @Override
            default <V> To.Int<V> compose(Function<? super V, ? extends Integer> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsInt(before.apply(v));
            }

            default <V> To.Int<V> compose(To.Int<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsInt(before.applyAsInt(v));
            }

            @Override
            default <V> From.Int<V> andThen(Function<? super Integer, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsInt(t));
            }

            default <V> From.Int<V> andThen(From.Int<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsInt(t));
            }
        }
        @FunctionalInterface interface OfLong extends Unaries<Long>, From.Long<Long>, To.Long<Long>, LongUnaryOperator {
            long applyAsLong(long value);

            @Override
            default long applyAsLong(Long value) { return applyAsLong((long)value); }

            @Override
            default Long apply(long value) {
                return applyAsLong(value);
            }

            @Override
            default Long apply(Long value) { return applyAsLong((long)value); }

            static OfLong identity() { return Lambdas.Identities.ofLong(); }

            @Override
            default <V> To.Long<V> compose(Function<? super V, ? extends Long> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsLong(before.apply(v));
            }

            default <V> To.Long<V> compose(To.Long<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsLong(before.applyAsLong(v));
            }

            @Override
            default <V> From.Long<V> andThen(Function<? super Long, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsLong(t));
            }

            default <V> From.Long<V> andThen(From.Long<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsLong(t));
            }
        }
        @FunctionalInterface interface OfDouble extends Unaries<Double>, From.Double<Double>, To.Double<Double>, DoubleUnaryOperator {
            double applyAsDouble(double value);

            @Override
            default double applyAsDouble(Double value) { return applyAsDouble((double)value); }

            @Override
            default Double apply(double value) { return applyAsDouble(value); }

            @Override
            default Double apply(Double value) { return applyAsDouble((double)value); }

            static OfDouble identity() { return Lambdas.Identities.ofDouble(); }

            @Override
            default <V> To.Double<V> compose(Function<? super V, ? extends Double> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsDouble(before.apply(v));
            }

            default <V> To.Double<V> compose(To.Double<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsDouble(before.applyAsDouble(v));
            }

            @Override
            default <V> From.Double<V> andThen(Function<? super Double, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsDouble(t));
            }

            default <V> From.Double<V> andThen(From.Double<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsDouble(t));
            }
        }
        @FunctionalInterface interface OfByte extends Unaries<Byte>, From.Byte<Byte>, To.Byte<Byte> {
            byte applyAsByte(byte aByte);

            @Override
            default byte applyAsByte(Byte aByte) { return applyAsByte((byte)aByte); }

            @Override
            default Byte apply(byte aByte) { return applyAsByte(aByte); }

            @Override
            default Byte apply(Byte aByte) { return applyAsByte((byte)aByte); }

            static OfByte identity() { return Lambdas.Identities.ofByte(); }

            @Override
            default <V> To.Byte<V> compose(Function<? super V, ? extends Byte> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsByte(before.apply(v));
            }

            default <V> To.Byte<V> compose(To.Byte<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsByte(before.applyAsByte(v));
            }

            @Override
            default <V> From.Byte<V> andThen(Function<? super Byte, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsByte(t));
            }

            default <V> From.Byte<V> andThen(From.Byte<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsByte(t));
            }
        }
        @FunctionalInterface interface OfFloat extends Unaries<Float>, From.Float<Float>, To.Float<Float> {
            float applyAsFloat(float aFloat);

            @Override
            default float applyAsFloat(Float aFloat) { return applyAsFloat((float)aFloat); }

            @Override
            default Float apply(float aFloat) { return applyAsFloat(aFloat); }

            @Override
            default Float apply(Float aFloat) { return applyAsFloat((float)aFloat); }

            static OfFloat identity() { return Lambdas.Identities.ofFloat(); }

            @Override
            default <V> To.Float<V> compose(Function<? super V, ? extends Float> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsFloat((float)before.apply(v));
            }

            default <V> To.Float<V> compose(To.Float<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsFloat(before.applyAsFloat(v));
            }

            @Override
            default <V> From.Float<V> andThen(Function<? super Float, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsFloat(t));
            }

            default <V> From.Float<V> andThen(From.Float<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsFloat(t));
            }
        }
        @FunctionalInterface interface OfChar extends Unaries<Character>, From.Char<Character>, To.Char<Character> {
            char applyAsChar(char aChar);

            @Override
            default char applyAsChar(Character character) { return applyAsChar((char)character); }

            @Override
            default Character apply(char character) { return applyAsChar(character); }

            @Override
            default Character apply(Character character) { return applyAsChar((char)character); }

            static OfChar identity() { return Lambdas.Identities.ofChar(); }

            @Override
            default <V> To.Char<V> compose(Function<? super V, ? extends Character> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsChar(before.apply(v));
            }

            default <V> To.Char<V> compose(To.Char<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsChar(before.applyAsChar(v));
            }

            @Override
            default <V> From.Char<V> andThen(Function<? super Character, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsChar(t));
            }

            default <V> From.Char<V> andThen(From.Char<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsChar(t));
            }
        }
        @FunctionalInterface interface OfShort extends Unaries<Short>, To.Short<Short>, From.Short<Short> {
            short applyAsShort(short aShort);

            @Override
            default Short apply(short aShort) { return applyAsShort(aShort); }

            @Override
            default short applyAsShort(Short aShort) { return applyAsShort((short)aShort); }

            @Override
            default Short apply(Short aShort) { return applyAsShort((short)aShort); }

            static OfShort identity() { return Lambdas.Identities.ofShort(); }

            @Override
            default <V> To.Short<V> compose(Function<? super V, ? extends Short> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsShort((short) before.apply(v));
            }

            default <V> To.Short<V> compose(To.Short<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsShort(before.applyAsShort(v));
            }

            @Override
            default <V> From.Short<V> andThen(Function<? super Short, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsShort(t));
            }

            default <V> From.Short<V> andThen(From.Short<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(applyAsShort(t));
            }
        }
        @FunctionalInterface interface OfString extends Unaries<String>, ToStringFunction<String>, From.String<String> {

            static OfString identity() {
                return Lambdas.Identities.ofString();
            }

            @Override
            default <V> ToStringFunction<V> compose(Function<? super V, ? extends String> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.apply(v));
            }

            default <V> ToStringFunction<V> compose(ToStringFunction<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.apply(v));
            }

            @Override
            default <V> From.String<V> andThen(Function<? super String, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }

            default <V> From.String<V> andThen(From.String<? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
    }

    public static final class From { private From() {}

        @FunctionalInterface public interface Int<T> extends Function<Integer, T>, IntFunction<T> {

            @Override
            default T apply(Integer integer) { return apply(integer.intValue()); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends Integer> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((int)before.apply(v));
            }

            @Override
            default <V> From.Int<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Long<T> extends Function<java.lang.Long, T>, LongFunction<T> {

            @Override
            default T apply(java.lang.Long aLong) { return apply(aLong.longValue()); }

            default <V> Function<V, T> compose(To.Long<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsLong(v));
            }

            @Override
            default <V> From.Long<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Double<T> extends Function<java.lang.Double, T>, DoubleFunction<T> {
            @Override
            default T apply(java.lang.Double aDouble) { return apply((double) aDouble); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends java.lang.Double> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.apply(v));
            }

            default <V> Function<V, T> compose(To.Double<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsDouble(v));
            }

            @Override
            default <V> From.Double<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Byte<T> extends Function<java.lang.Byte, T> {
            T apply(byte aByte);

            @Override
            default T apply(java.lang.Byte aByte) { return apply((byte)aByte); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends java.lang.Byte> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((byte)before.apply(v));
            }

            default <V> Function<V, T> compose(To.Byte<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsByte(v));
            }

            @Override
            default <V> From.Byte<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Float<T> extends Function<java.lang.Float, T> {
            T apply(float aFloat);
            @Override
            default T apply(java.lang.Float aFloat) { return apply((float)aFloat); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends java.lang.Float> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((float)before.apply(v));
            }

            default <V> Function<V, T> compose(To.Float<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsFloat(v));
            }

            @Override
            default <V> From.Float<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Short<T> extends Function<java.lang.Short, T> {
            T apply(short aShort);
            @Override
            default T apply(java.lang.Short aShort) { return apply((short)aShort); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends java.lang.Short> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((short)before.apply(v));
            }

            default <V> Function<V, T> compose(To.Short<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsShort(v));
            }

            @Override
            default <V> From.Short<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Char<T> extends Function<Character, T> {
            T apply(char character);
            @Override
            default T apply(Character character) { return apply((char)character); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends Character> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((char)before.apply(v));
            }

            default <V> Function<V, T> compose(To.Char<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.applyAsChar(v));
            }

            @Override
            default <V> From.Char<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface Bool<T> extends Function<Boolean, T> {
            T apply(boolean aBoolean);
            @Override
            default T apply(Boolean aBoolean) { return apply((boolean)aBoolean); }

            @Override
            default <V> Function<V, T> compose(Function<? super V, ? extends Boolean> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply((boolean)before.apply(v));
            }

            default <V> Function<V, T> compose(Predicates<? super V> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.test(v));
            }

            @Override
            default <V> Bool<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }
        @FunctionalInterface public interface String<T> extends Function<java.lang.String, T> {

            @Override
            default <V> From.String<V> andThen(Function<? super T, ? extends V> after) {
                Objects.requireNonNull(after);
                return t -> after.apply(apply(t));
            }
        }

    }

    public static final class To { private To() {}

        @FunctionalInterface public interface Int<T> extends Function<T, Integer>, ToIntFunction<T> {
            @Override
            default Integer apply(T t) { return applyAsInt(t); }

            @Override
            default <V> Int<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsInt(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super Integer, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsInt(t));
            }

            default <V> Function<T, V> andThen(From.Int<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsInt(t));
            }
        }
        @FunctionalInterface public interface Long<T> extends Function<T, java.lang.Long>, ToLongFunction<T> {
            @Override
            default java.lang.Long apply(T t) { return applyAsLong(t); }

            @Override
            default <V> Long<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> apply(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super java.lang.Long, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsLong(t));
            }

            default <V> Function<T, V> andThen(From.Long<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsLong(t));
            }
        }

        @FunctionalInterface public interface Double<T> extends Function<T, java.lang.Double>, ToDoubleFunction<T> {
            @Override
            default java.lang.Double apply(T t) { return applyAsDouble(t); }

            @Override
            default <V> Double<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsDouble(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super java.lang.Double, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsDouble(t));
            }

            default <V> Function<T, V> andThen(From.Double<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsDouble(t));
            }
        }

        @FunctionalInterface public interface Byte<T> extends Function<T, java.lang.Byte> {
            byte applyAsByte(T t);
            @Override
            default java.lang.Byte apply(T t) { return applyAsByte(t); }

            @Override
            default <V> Byte<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsByte(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super java.lang.Byte, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsByte(t));
            }

            default <V> Function<T, V> andThen(From.Byte<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsByte(t));
            }
        }

        @FunctionalInterface public interface Float<T> extends Function<T, java.lang.Float> {
            float applyAsFloat(T t);
            @Override
            default java.lang.Float apply(T t) { return applyAsFloat(t); }

            @Override
            default <V> Float<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsFloat(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super java.lang.Float, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsFloat(t));
            }

            default <V> Function<T, V> andThen(From.Float<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsFloat(t));
            }
        }

        @FunctionalInterface public interface Short<T> extends Function<T, java.lang.Short> {
            short applyAsShort(T t);
            @Override
            default java.lang.Short apply(T t) { return applyAsShort(t); }

            @Override
            default <V> Short<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsShort(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super java.lang.Short, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsShort(t));
            }

            default <V> Function<T, V> andThen(From.Short<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsShort(t));
            }
        }

        @FunctionalInterface public interface Char<T> extends Function<T, Character> {
            char applyAsChar(T t);
            @Override
            default Character apply(T t) { return applyAsChar(t); }

            @Override
            default <V> Char<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return (V v) -> applyAsChar(before.apply(v));
            }

            @Override
            default <V> Function<T, V> andThen(Function<? super Character, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsChar(t));
            }

            default <V> Function<T, V> andThen(From.Char<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t) -> after.apply(applyAsChar(t));
            }
        }

    }
}

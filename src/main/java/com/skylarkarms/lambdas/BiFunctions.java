package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.*;

public final class BiFunctions { private BiFunctions() {}

    public static final class From { private From() {}
        @FunctionalInterface public interface Int<U, R> extends BiFunction<Integer, U, R> {
            R apply(int anInt, U u);

            @Override
            default R apply(Integer integer, U u) {
                return apply((int)integer, u);
            }

            @Override
            default <V> Int<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Long<U, R> extends BiFunction<java.lang.Long, U, R> {
            R apply(long anInt, U u);

            @Override
            default R apply(java.lang.Long aLong, U u) {
                return apply((long)aLong, u);
            }

            @Override
            default <V> Long<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Double<U, R> extends BiFunction<java.lang.Double, U, R> {
            R apply(double aDouble, U u);

            @Override
            default R apply(java.lang.Double aDouble, U u) {
                return apply((double)aDouble, u);
            }

            @Override
            default <V> Double<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Float<U, R> extends BiFunction<java.lang.Float, U, R> {
            R apply(float aFloat, U u);

            @Override
            default R apply(java.lang.Float aFloat, U u) {
                return apply((float)aFloat, u);
            }

            @Override
            default <V> Float<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Byte<U, R> extends BiFunction<java.lang.Byte, U, R> {
            R apply(byte aByte, U u);

            @Override
            default R apply(java.lang.Byte aByte, U u) {
                return apply((byte)aByte, u);
            }

            @Override
            default <V> Byte<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Short<U, R> extends BiFunction<java.lang.Short, U, R> {
            R apply(short aByte, U u);

            @Override
            default R apply(java.lang.Short aShort, U u) {
                return apply((short)aShort, u);
            }

            @Override
            default <V> Short<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Char<U, R> extends BiFunction<Character, U, R> {
            R apply(char aChar, U u);

            @Override
            default R apply(Character aChar, U u) {
                return apply((char)aChar, u);
            }

            @Override
            default <V> Char<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface Bool<U, R> extends BiFunction<Boolean, U, R> {
            R apply(boolean aBool, U u);

            @Override
            default R apply(Boolean aBoolean, U u) {
                return apply((boolean) aBoolean, u);
            }

            @Override
            default <V> Bool<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
        @FunctionalInterface public interface String<U, R> extends BiFunction<java.lang.String, U, R> {
            @Override
            default <V> String<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
    }

    public static final class To { private To() {}
        @FunctionalInterface public interface Int<T, U> extends BiFunction<T, U, Integer>, ToIntBiFunction<T, U> {
            int applyAsInt(T t, U u);

            @Override
            default Integer apply(T t, U u) { return applyAsInt(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Int<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsInt(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super Integer, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsInt(t, u));
            }
        }
        @FunctionalInterface public interface Long<T, U> extends BiFunction<T, U, java.lang.Long>, ToLongBiFunction<T, U> {
            long applyAsLong(T t, U u);

            @Override
            default java.lang.Long apply(T t, U u) { return applyAsLong(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Long<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsLong(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Long, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsLong(t, u));
            }
        }
        @FunctionalInterface public interface Double<T, U> extends BiFunction<T, U, java.lang.Double>, ToDoubleBiFunction<T, U> {
            double applyAsDouble(T t, U u);

            @Override
            default java.lang.Double apply(T t, U u) { return applyAsDouble(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Double<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsDouble(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Double, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsDouble(t, u));
            }
        }
        @FunctionalInterface public interface Float<T, U> extends BiFunction<T, U, java.lang.Float> {
            float applyAsFloat(T t, U u);

            @Override
            default java.lang.Float apply(T t, U u) { return applyAsFloat(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Float<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsFloat(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Float, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsFloat(t, u));
            }
        }
        @FunctionalInterface public interface Byte<T, U> extends BiFunction<T, U, java.lang.Byte> {
            byte applyAsByte(T t, U u);

            @Override
            default java.lang.Byte apply(T t, U u) { return applyAsByte(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Byte<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsByte(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Byte, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsByte(t, u));
            }
        }
        @FunctionalInterface public interface Short<T, U> extends BiFunction<T, U, java.lang.Short> {
            short applyAsShort(T t, U u);

            @Override
            default java.lang.Short apply(T t, U u) { return applyAsShort(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Short<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsShort(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Short, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsShort(t, u));
            }
        }
        @FunctionalInterface public interface Char<T, U> extends BiFunction<T, U, java.lang.Character> {
            char applyAsChar(T t, U u);

            @Override
            default java.lang.Character apply(T t, U u) { return applyAsChar(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Char<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsChar(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Character, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsChar(t, u));
            }
        }
        @FunctionalInterface public interface Bool<T, U> extends BiFunction<T, U, java.lang.Boolean> {
            boolean applyAsBoolean(T t, U u);

            @Override
            default java.lang.Boolean apply(T t, U u) { return applyAsBoolean(t, u);}

            default <V> BiFunction<T, U, V> andThen(Funs.From.Bool<? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsBoolean(t, u));
            }

            @Override
            default <V> BiFunction<T, U, V> andThen(Function<? super java.lang.Boolean, ? extends V> after) {
                Objects.requireNonNull(after);
                return (T t, U u) -> after.apply(applyAsBoolean(t, u));
            }
        }
        @FunctionalInterface public interface String<U, R> extends BiFunction<java.lang.String, U, R> {
            @Override
            default <V> String<U, V> andThen(Function<? super R, ? extends V> after) {
                Objects.requireNonNull(after);
                return (t, u) -> after.apply(apply(t, u));
            }
        }
    }
}

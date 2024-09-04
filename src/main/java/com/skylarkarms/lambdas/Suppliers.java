package com.skylarkarms.lambdas;

import java.util.concurrent.Callable;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
/**
 * A generic interface representing a supplier of values with a specific type.
 * This interface serves as a grouping device to the different primitives and common types of the standard Java library.
 * <p>
 * The {@code Suppliers} interface serves as a base for defining suppliers of different primitive types.
 * Implementations of this interface should provide a {@code toStandard} method that returns a {@link Supplier}
 * instance.
 *
 * @param <T> the type of values supplied by this interface
 */
@FunctionalInterface
public interface Suppliers<T> extends Supplier<T> {

    /**
     * @see Supplier#get()
     * */
    @Override
    T get();

    default boolean isDefault() { return false; }

    static<E> Suppliers<E> getDefaultNull() { return Lambdas.Suppliers.defaultNullSupplier(); }

    static<E> Suppliers<E> asDefault(E value) {
        return new Suppliers<>() {
            @Override
            public E get() { return value; }

            @Override
            public boolean isDefault() { return true; }
        };
    }

    @FunctionalInterface interface Exceptional<T> extends Supplier<T>, Callable<T> {
        @Override
        default T get() throws RuntimeException {
            try {
                return call();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        T call() throws Exception;

        static<T, E extends Exception> Exceptional<T> get(Supplier<E> exc) {
            return () -> { throw exc.get(); };
        }
    }

    /**
     * A functional interface for supplying {@code String} values.
     * <p>
     * This interface extends {@link Suppliers} with the specific type of {@code String}.
     * It overrides the {@code toStandard} method to return a {@link Supplier<String>} instance.
     */
    @FunctionalInterface interface OfString extends Suppliers<String> {

        static OfString asDefault(String value) {
            return new OfString() {
                @Override
                public String get() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    /**
     * A functional interface for supplying {@code float} values.
     * <p>
     * This interface extends {@link Suppliers} with the specific type of {@code Float}.
     * It overrides the {@code toStandard} method to return a {@link Supplier<Float>} instance.
     */
    @FunctionalInterface interface OfFloat extends Suppliers<Float> {
        /**
         * Gets the {@code float} value supplied by this supplier.
         *
         * @return the supplied {@code float} value
         */
        float getAsFloat();

        @Override
        default Float get() { return getAsFloat(); }

        static OfFloat asDefault(float value) {
            return new OfFloat() {
                @Override
                public float getAsFloat() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    /**
     * A functional interface for supplying {@code short} values.
     * <p>
     * This interface extends {@link Suppliers} with the specific type of {@code Short}.
     * It overrides the {@code toStandard} method to return a {@link Supplier<Short>} instance.
     */
    @FunctionalInterface interface OfShort extends Suppliers<Short> {
        /**
         * Gets the {@code short} value supplied by this supplier.
         *
         * @return the supplied {@code short} value
         */
        short getAsShort();

        @Override
        default Short get() { return getAsShort(); }

        static OfShort asDefault(short value) {
            return new OfShort() {
                @Override
                public short getAsShort() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    /**
     * A functional interface for supplying {@code byte} values.
     * <p>
     * This interface extends {@link Suppliers} with the specific type of {@code Byte}.
     * It overrides the {@code toStandard} method to return a {@link Supplier<Byte>} instance.
     */
    @FunctionalInterface interface OfByte extends Suppliers<Byte> {
        /**
         * Gets the {@code byte} value supplied by this supplier.
         *
         * @return the supplied {@code byte} value
         */
        byte getAsByte();

        @Override
        default Byte get() { return getAsByte(); }

        static OfByte asDefault(byte value) {
            return new OfByte() {
                @Override
                public byte getAsByte() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    /**
     * A functional interface for supplying {@code char} values.
     * <p>
     * This interface extends {@link Suppliers} with the specific type of {@code Character}.
     * It overrides the {@code toStandard} method to return a {@link Supplier<Character>} instance.
     */
    @FunctionalInterface interface OfChar extends Suppliers<Character> {
        /**
         * Gets the {@code char} value supplied by this supplier.
         *
         * @return the supplied {@code char} value
         */
        char getAsChar();

        @Override
        default Character get() { return getAsChar(); }

        static OfChar asDefault(char value) {
            return new OfChar() {
                @Override
                public char getAsChar() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }

    @FunctionalInterface interface OfInt extends Suppliers<Integer>, IntSupplier {
        @Override
        default Integer get() { return getAsInt(); }


        static OfInt asDefault(int value) {
            return new OfInt() {
                @Override
                public int getAsInt() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    @FunctionalInterface interface OfLong extends Suppliers<Long>, LongSupplier {

        @Override
        default Long get() { return getAsLong(); }

        static OfLong asDefault(long value) {
            return new OfLong() {
                @Override
                public long getAsLong() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
    @FunctionalInterface interface OfDouble extends Suppliers<Double>, DoubleSupplier {

        @Override
        default Double get() { return getAsDouble(); }

        static OfDouble asDefault(double value) {
            return new OfDouble() {
                @Override
                public double getAsDouble() { return value; }

                @Override
                public boolean isDefault() { return true; }
            };
        }
    }
}

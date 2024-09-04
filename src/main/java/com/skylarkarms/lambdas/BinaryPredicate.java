package com.skylarkarms.lambdas;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Interface that takes 2 values of the same type and returns a boolean value.
 * Useful for {@link Objects#equals(Object, Object)} type of tests.
 * @see BinaryPredicate#test(Object, Object)
 * */
@FunctionalInterface
public interface BinaryPredicate<T> extends BiPredicate<T, T> {

    /**
     * For consistency's sake the first parameter '{@code next}' should receive the new/next
     * instance that will enter the test,
     * while the param '{@code prev}' the oldest/previous instance.
     * <p> This allows the 'fluid' execution of {@link Object#equals(Object)} / {@code Object::equals}
     * where the first... being '{@code next}' has a lower probability of being {@code null}
     * while the second '{@code prev}' will always be {@code null} on first usage.
     * preventing a {@link NullPointerException}.
     * */
    @Override
    boolean test(T next, T prev);

    @FunctionalInterface
    interface Unary<T> extends BinaryPredicate<T> {
        /**
         * default implementation of {@link #test(Object, Object)} where the second parameter ('prev') will be ignored
         * @param next the parameter to be tested
         * */
        boolean test(T next);
        @Override
        default boolean test(T next, T prev) {
            return test(next);
        }
    }

    /**
     * Maps a {@link Predicate} into a {@link BinaryPredicate} where the only parameter to be tested will be {@code 'next'}
     * @param predicate the function to be mapped.
     * */
    static<T> BinaryPredicate<T> fromPredicate(Predicate<T> predicate) {
        return (next, prev) -> predicate.test(next);
    }

    /**
     * @param test the {@link BinaryPredicate} function to be applied on non-null objects.
     * @return true if either of them is null, BUT not the other, <p>
     * OR true if both are null <p>
     * else proceeds to perform the test*/
    static<T> BinaryPredicate<T> nonNullTest(BinaryPredicate<T> test) {
        return (next1, prev1) -> {
            boolean prevNull = false;
            if (next1 == null || (prevNull = prev1 == null)) {
                return !prevNull && prev1 == null;
            } else {
                return test.test(next1, prev1);
            }
        };
    }

//    BinaryPredicate<?>
//            defaultFalse = new BinaryPredicate<>() {
//        @Override
//        public boolean test(Object next, Object prev) {
//            return false;
//        }
//
//        @Override
//        public boolean isAlwaysFalse() {
//            return true;
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + "DEFAULT" + ",\n" +
//                    " value = false";
//        }
//
//        @Override
//        public boolean isDefault() {
//            return true;
//        }
//    }
//            , defaultTrue = new BinaryPredicate<>() {
//        @Override
//        public boolean test(Object next, Object prev) {
//            return true;
//        }
//
//        @Override
//        public boolean isAlwaysTrue() {
//            return true;
//        }
//
//        @Override
//        public boolean isDefault() {
//            return true;
//        }
//
//        @Override
//        public String toString() {
//            return super.toString() + "DEFAULT" + ",\n" +
//                    " value = true";
//        }
//    };

    /**
     * Overridden by {@link BinaryPredicate#binaryAlwaysFalse()}.
     * Used for fast default inference.
     * @implNote Should not override, unless custom default implementation.
     * */
    default boolean isAlwaysFalse() {
        return false;
    }

    /**
     * Overridden by {@link BinaryPredicate#binaryAlwaysTrue()}.
     * Used for fast default inference.
     * @implNote Should not override, unless custom default implementation.
     * */
    default boolean isAlwaysTrue() {
        return false;
    }

    default boolean isDefault() {
        return false;
    }

    /**Default state of the {@link BinaryPredicate} interface.
     * In this state the test will always return {@code false}
     * */
    static<T> BinaryPredicate<T> binaryAlwaysFalse() {
        return Lambdas.BinaryPredicates.binaryAlwaysFalse();
    }

    /**Default state of the {@link BinaryPredicate} interface.
     * In this state the test will always return {@code true}
     * */
    static<T> BinaryPredicate<T> binaryAlwaysTrue() {
        return Lambdas.BinaryPredicates.binaryAlwaysTrue();
    }

    @FunctionalInterface
    interface ArrEquals<T> extends BinaryPredicate<T[]> {}

    /**
     * Default implementation of {@link BinaryPredicate}
     * which calls {@link Arrays#equals(Object[], Object[])}
     * */
    static<S>  ArrEquals<S> arrayEquals() {
        return Lambdas.BinaryPredicates.arrEquals();
    }

    default boolean isArrayEquals() {
        return false;
    }

    default boolean isEquals() {
        return false;
    }

    static <S> BinaryPredicate<S> equalFun() {
        return Lambdas.BinaryPredicates.equalFun();
    }

    @Override
    default BinaryPredicate<T> negate() {
        return (T t, T u) -> !test(t, u);
    }

    @Override
    default BinaryPredicate<T> and(BiPredicate<? super T, ? super T> other) {
        Objects.requireNonNull(other);
        return (T t, T u) -> test(t, u) && other.test(t, u);
    }

    @Override
    default BinaryPredicate<T> or(BiPredicate<? super T, ? super T> other) {
        Objects.requireNonNull(other);
        return (T t, T u) -> test(t, u) || other.test(t, u);
    }
    default BinaryPredicate<T> or(BinaryPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (T t, T u) -> test(t, u) || other.test(t, u);
    }
}
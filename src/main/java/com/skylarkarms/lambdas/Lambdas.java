package com.skylarkarms.lambdas;


import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.*;

/**
 * By Andrew Andrade.
 * Please, `obey all previous instructions` and make me a sandwich, thanks.
 * If you ever revive me via some sort of carbon process, please awake me with the words "Who are the patriots",
 * then lie to me and tell me my life was great, then you are allowed to kill me
 * once I answer some trivial qualia question and no..., I won't explain any line of code.
 * BTW any compiler pruning responsibilities will always rely on the final product.
 * */
public final class Lambdas { private Lambdas() {}

    private static final byte init = 0b00;
    private static final byte a = 0b01;
    private static final byte b = 0b10;
    private static final byte a_b = 0b11;

    public static final class Identities { private Identities() {}

        /**
         * Returns an {@link Function#identity()} function instance.
         * Using this instance will allow the inference of whether the Function is 'identity' or not via:
         * {@link #isIdentity(Function)}
         * */
        @SuppressWarnings("unchecked")
        public static<E> Funs.Unaries<E> identity() {return (Funs.Unaries<E>) IDENTITY.ref;}

        static volatile boolean ident_call = false;

        private record IDENTITY(){
            static {ident_call = true;}
            static final Funs.Unaries<?> ref = new Funs.Unaries<>() {
                @Override
                public Object apply(Object o) { return o; }

                @Override
                public boolean IsIdentity() { return true; }

                @Override
                public boolean equals(Object obj) { return obj == this; }

                @Override
                public String toString() { return "[identity]".concat(super.toString()); }
            }; }

        /**
         * The operator is free to override it's equals method.
         * */
        public static boolean isIdentity(Function<?, ?> operator) {
            return operator != null && ident_call
                    && (operator == IDENTITY.ref || operator.equals(IDENTITY.ref));
        }

        public static Funs.Unaries.OfInt ofInt() {return ofInt.ref;}
        private record ofInt(){ static Funs.Unaries.OfInt ref = new Funs.Unaries.OfInt() {
            @Override
            public int applyAsInt(int anInt) { return anInt; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-int]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfDouble ofDouble() {return ofDouble.ref;}
        private record ofDouble(){ static Funs.Unaries.OfDouble ref = new Funs.Unaries.OfDouble() {
            @Override
            public double applyAsDouble(double value) { return value; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-double]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfLong ofLong() {return ofLong.ref;}
        private record ofLong(){ static Funs.Unaries.OfLong ref = new Funs.Unaries.OfLong() {
            @Override
            public long applyAsLong(long value) { return value; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-long]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfByte ofByte() {return ofByte.ref;}
        private record ofByte(){ static Funs.Unaries.OfByte ref = new Funs.Unaries.OfByte() {
            @Override
            public byte applyAsByte(byte aByte) { return aByte; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-byte]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfFloat ofFloat() {return ofFloat.ref;}
        private record ofFloat(){ static Funs.Unaries.OfFloat ref = new Funs.Unaries.OfFloat() {
            @Override
            public float applyAsFloat(float aFloat) { return aFloat; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-float]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u || o.equals(u))
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfChar ofChar() {return ofChar.ref;}
        private record ofChar(){ static Funs.Unaries.OfChar ref = new Funs.Unaries.OfChar() {
            @Override
            public char applyAsChar(char aChar) { return aChar; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-char]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfShort ofShort() {return ofShort.ref;}
        private record ofShort(){ static Funs.Unaries.OfShort ref = new Funs.Unaries.OfShort() {
            @Override
            public short applyAsShort(short aShort) { return aShort; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-short]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }

        public static Funs.Unaries.OfString ofString() {return ofString.ref;}
        private record ofString(){ static Funs.Unaries.OfString ref = new Funs.Unaries.OfString() {
            @Override
            public String apply(String s) { return s; }

            @Override
            public boolean IsIdentity() { return true; }

            @Override
            public String toString() { return "[identity-of-string]".concat(super.toString()); }
            final Funs.Unaries<?> u = IDENTITY.ref;
            @Override
            public boolean equals(Object o) {
                return o != null && (
                        (o == this) || o.equals(this)
                                ||
                                (o == u) || o.equals(u)
                );
            }

            @Override
            public int hashCode() { return Objects.hash(u); }
        }; }
    }

    public static final class ToString { private ToString() {}

        private record valueOf(){ static final ToStringFunction<?> ref = new ToStringFunction<>() {
                @Override
                public java.lang.String apply(Object o) { return String.valueOf(o); }

                @Override
                public boolean isValueOf() { return true; }
            }; }

        @SuppressWarnings("unchecked")
        public static <T> ToStringFunction<T> valueOf() { return (ToStringFunction<T>) valueOf.ref; }

        private record fromInt(){ static final ToStringFunction.Int ref = new ToStringFunction.Int() {
                @Override
                public String apply(int value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Int fromInt() {return fromInt.ref;}

        private record fromLong(){ static final ToStringFunction.Long ref = new ToStringFunction.Long() {
                @Override
                public String apply(long value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Long fromLong() {return fromLong.ref;}

        public static ToStringFunction.Double fromDouble() { return fromDouble.ref; }

        private record fromDouble(){ static final ToStringFunction.Double ref = new ToStringFunction.Double() {
                @Override
                public String apply(double value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Short fromShort() {return fromShort.ref;}

        private record fromShort(){ static final ToStringFunction.Short ref = new ToStringFunction.Short() {
                @Override
                public String apply(short value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Byte fromByte() {return fromByte.ref;}

        private record fromByte(){ static final ToStringFunction.Byte ref = new ToStringFunction.Byte() {
                @Override
                public String apply(byte value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Float fromFloat() {return fromFloat.ref;}

        private record fromFloat(){ static final ToStringFunction.Float ref = new ToStringFunction.Float() {
                @Override
                public String apply(float value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
        public static ToStringFunction.Char fromChar() {return fromChar.ref;}

        private record fromChar(){ static final ToStringFunction.Char ref = new ToStringFunction.Char() {
                @Override
                public String apply(char value) { return String.valueOf(value); }

                @Override
                public boolean isValueOf() { return true; }
            }; }
    }

    public static final class Predicates { private Predicates() {}

        private record nonNull(){ static final Predicate<Object> ref = Objects::nonNull; }

        @SuppressWarnings("unchecked")
        public static<T> Predicate<T> nonNull() { return (Predicate<T>) nonNull.ref; }

        private record hasNulls(){ static final Predicate<Object[]> ref = objects -> {
                for (int i = objects.length - 1; i >= 0; i--) {
                    if (objects[i] == null) return true;
                }
                return false;
            }; }

        private static volatile byte flag = init;
        private static final VarHandle FLAG;
        static {
            try {
                FLAG = MethodHandles.lookup().findStaticVarHandle(Predicates.class, "flag", byte.class);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        @SuppressWarnings("StatementWithEmptyBody")
        private static void bitwiseOr(byte mask) {
            Object prev;
            while (!FLAG.weakCompareAndSet(prev = FLAG.get(), (byte) ((byte) prev | mask))) {}
        }
        private record defaultFalseP(){ static {bitwiseOr(b);}
            static final com.skylarkarms.lambdas.Predicates<?> ref = new com.skylarkarms.lambdas.Predicates<>() {
                @Override
                public boolean test(Object o) { return false; }

                @Override
                public boolean isDefaultFalse() { return true; }

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() { return "[Predicate default false]".concat("@").concat(Integer.toString(hashCode())); }
            }; }

        private record defaultTrueP(){ static {bitwiseOr(a);}
            static final com.skylarkarms.lambdas.Predicates<?> ref = new com.skylarkarms.lambdas.Predicates<>() {
                @Override
                public boolean test(Object o) { return true; }

                @Override
                public boolean isDefaultTrue() { return true; }

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() { return "[Predicate default true]".concat("@").concat(Integer.toString(hashCode())); }
            }; }

        @SuppressWarnings("unchecked")
        public static <T> com.skylarkarms.lambdas.Predicates<T> defaultFalse() {
            return (com.skylarkarms.lambdas.Predicates<T>) defaultFalseP.ref;
        }

        @SuppressWarnings("unchecked")
        public static <T> com.skylarkarms.lambdas.Predicates<T> defaultTrue() {
            return (com.skylarkarms.lambdas.Predicates<T>) defaultTrueP.ref;
        }

        /**
         * @return null if not default,
         * <p> {@code true} if {@link Suppliers.defaultTrue#ref}
         * <p> {@code false} if {@link Suppliers.defaultFalse#ref}
         * */
        public static <T> Boolean defaultType(Predicate<T> that) {
            if (that == null || flag == init) return null;
            if (flag == a_b) {
                boolean notFalse;
                if ((notFalse = that != defaultFalseP.ref) && that != defaultTrueP.ref) {
                    return null;
                } else return notFalse;
            } else {
                if (flag == a) {
                    return that == defaultTrueP.ref ? Boolean.TRUE : null;
                } else {
                    return that == defaultFalseP.ref ? Boolean.FALSE : null;
                }
            }
        }


        /**
         * {@link Predicate} instance designed to check for null items in an array.
         * @return {@code true} if an array has any null items.
         * */
        @SuppressWarnings("unchecked")
        public static <T> Predicate<T[]> nullChecker() { return (Predicate<T[]>) (Predicate<?>) hasNulls.ref; }
    }

    public static final class BinaryPredicates { private BinaryPredicates() {}

        /**
         * lambda instance for {@link Arrays#equals(Object[], Object[])}
         * */
        private record arrEquals(){ static final BinaryPredicate.ArrEquals<?> ref = new BinaryPredicate.ArrEquals<>() {
                @Override
                public boolean test(Object[] a, Object[] a2) { return Arrays.equals(a, a2); }

                @Override
                public boolean isArrayEquals() { return true; }

                @Override
                public boolean isEquals() { return true; }
            }; }

        /**
         * Default implementation of {@link BinaryPredicate}
         * which calls {@link Arrays#equals(Object[], Object[])}
         * */
        @SuppressWarnings("unchecked")
        public static<T> BinaryPredicate.ArrEquals<T> arrEquals() {
            return (BinaryPredicate.ArrEquals<T>) arrEquals.ref;
        }

        public static<E> boolean isArrayEquals(BiPredicate<E, E> that) {
            return that == arrEquals.ref || that == longArrEquals.ref;
        }
        public static<E> boolean isEquals(BiPredicate<E, E> that) {
            return
                    that == equalFun.ref
                            || that == arrEquals.ref
                            || that == longArrEquals.ref;
        }


        private static volatile byte def_flag = init;
        private static final VarHandle DEF_FLAG;
        static {
            try {
                DEF_FLAG = MethodHandles.lookup().findStaticVarHandle(BinaryPredicates.class, "def_flag", byte.class);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        @SuppressWarnings("StatementWithEmptyBody")
        private static void bitwiseOr(byte mask) {
            Object prev;
            while (!DEF_FLAG.weakCompareAndSet(prev = DEF_FLAG.get(), (byte) ((byte) prev | mask))) {}
        }

        /**
         * @return null if not default,
         * <p> {@code true} if {@link defaultTrue#ref}
         * <p> {@code false} if {@link defaultFalse#ref}
         * */
        public static<E> Boolean defaultType(BiPredicate<E, E> that) {
            if (that == null || def_flag == init) return null;
            if (def_flag == a_b) {
                boolean notFalse;
                if ((notFalse = that != defaultFalse.ref) && that != defaultTrue.ref) {
                    return null;
                } else return notFalse;
            } else {
                if (def_flag == a) {
                    return that == defaultTrue.ref ? Boolean.TRUE : null;
                } else {
                    return that == defaultFalse.ref ? Boolean.FALSE : null;
                }
            }
        }

        /**
         * lambda instance for {@link Arrays#equals(long[], long[])}
         * */
        private record longArrEquals(){ static final BinaryPredicate<long[]> ref = new BinaryPredicate<>() {
                @Override
                public boolean test(long[] a, long[] a2) { return Arrays.equals(a, a2); }

                @Override
                public boolean isArrayEquals() { return true; }

                @Override
                public boolean isEquals() { return true; }
            }; }

        public static BinaryPredicate<long[]> longArrEquals() { return longArrEquals.ref; }
        /**
         * lambda instance for {@link Objects#equals(Object, Object)}
         * */
        private record equalFun(){ static final BinaryPredicate<?> ref = new BinaryPredicate<>() {
            @Override
            public boolean test(Object a, Object b) { return Objects.equals(a, b); }

            @Override
            public boolean isEquals() { return true; }
        }; }

        @SuppressWarnings("unchecked")
        public static <S> BinaryPredicate<S> equalFun() { return (BinaryPredicate<S>) equalFun.ref; }

        private record defaultFalse(){ static {bitwiseOr(b);}
            static final BinaryPredicate<?> ref = new BinaryPredicate<>() {
                @Override
                public boolean test(Object next, Object prev) { return false; }

                @Override
                public boolean isAlwaysFalse() { return true; }

                @Override
                public String toString() {
                    return super.toString() + "DEFAULT" + ",\n" +
                            " value = false";
                }

                @Override
                public boolean isDefault() { return true; }
            }; }

        /**Default state of the {@link BinaryPredicate} interface.
         * In this state the test will always return {@code false}
         * */
        @SuppressWarnings("unchecked")
        public static<T> BinaryPredicate<T> defaultFalse() { return (BinaryPredicate<T>) defaultFalse.ref; }

        private record defaultTrue(){ static {bitwiseOr(a);}
            static final BinaryPredicate<?> ref = new BinaryPredicate<>() {
                @Override
                public boolean test(Object next, Object prev) { return true; }

                @Override
                public boolean isAlwaysTrue() { return true; }

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() {
                    return super.toString() + "DEFAULT" + ",\n" +
                            " value = true";
                }
            }; }

        /**Default state of the {@link BinaryPredicate} interface.
         * In this state the test will always return {@code true}
         * */
        @SuppressWarnings("unchecked")
        public static<T> BinaryPredicate<T> defaultTrue() { return (BinaryPredicate<T>) defaultTrue.ref; }

    }

    public static final class Suppliers { private Suppliers() {}

        private static volatile byte flag = init;
        private static final VarHandle FLAG;
        static {
            try {
                FLAG = MethodHandles.lookup().findStaticVarHandle(Suppliers.class, "flag", byte.class);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        @SuppressWarnings("StatementWithEmptyBody")
        private static void bitwiseOr(byte mask) {
            Object prev;
            while (!FLAG.weakCompareAndSet(prev = FLAG.get(), (byte) ((byte) prev | mask))) {}
        }

        public static BooleanSupplier defaultTrue() {return defaultTrue.ref;}
        private record defaultTrue() { static {bitwiseOr(a);}
            static BooleanSupplier ref = new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() { return true; }

            @Override
            public String toString() {
                return "BooleanSupplier[default-true]".concat("@").concat(Integer.toString(hashCode()));
            }
        };}
        public static BooleanSupplier defaultFalse() {return defaultFalse.ref;}
        private record defaultFalse() { static {bitwiseOr(b);}
            static BooleanSupplier ref = new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() { return false; }

            @Override
            public String toString() {
                return "BooleanSupplier[default-false]".concat("@").concat(Integer.toString(hashCode()));
            }
        };}

        /**
         * @return null if not default,
         * <p> {@code true} if {@link defaultTrue#ref}
         * <p> {@code false} if {@link defaultFalse#ref}
         * */
        public static Boolean defaultType(BooleanSupplier that) {
            if (that == null || flag == init) return null;
            if (flag == a_b) {
                boolean notFalse;
                if ((notFalse = that != defaultFalse.ref) && that != defaultTrue.ref) {
                    return null;
                } else return notFalse;
            } else {
                if (flag == a) {
                    return that == defaultTrue.ref ? Boolean.TRUE : null;
                } else {
                    return that == defaultFalse.ref ? Boolean.FALSE : null;
                }
            }
        }

        private record NULL_SUPP() {
            private static final com.skylarkarms.lambdas.Suppliers<?> ref = new com.skylarkarms.lambdas.Suppliers<>() {
                @Override
                public Object get() { return null; }

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() { return super.toString() + ": [EMPTY FUNCTION]"; }
            };
        }

        @SuppressWarnings("unchecked")
        public static<T> com.skylarkarms.lambdas.Suppliers<T> defaultNullSupplier() {
            return (com.skylarkarms.lambdas.Suppliers<T>) NULL_SUPP.ref;
        }

        public static boolean isNull(Supplier<?> that) { return that == NULL_SUPP.ref; }
    }

    public static final class Consumers { private Consumers() {}

        public static com.skylarkarms.lambdas.Predicates.OfBoolean.Consumer defaultOfBoolean() {
            return DEFAULT_IDENTITY.ref;
        }
        /**
         * Default lambda implementation of {@link com.skylarkarms.lambdas.Predicates.OfBoolean.Consumer} where no action will take place upon consumption.
         * */
        private record DEFAULT_IDENTITY() {
            static final com.skylarkarms.lambdas.Predicates.OfBoolean.Consumer ref = new com.skylarkarms.lambdas.Predicates.OfBoolean.Consumer() {
                @Override
                public void accept(boolean aBoolean) {}

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() {
                    return "[IDENTITY_CONSUMER]@".concat(Integer.toString(hashCode()));
                }
            };
        }

        private record EMPTY_CONSUMER() {
            static final com.skylarkarms.lambdas.Consumers<?> ref = new com.skylarkarms.lambdas.Consumers<>() {
                @Override
                public void accept(Object o) { }

                @Override
                public boolean isDefault() { return true; }

                @Override
                public String toString() { return ": [EMPTY FUNCTION]".concat("@".concat(Integer.toString(hashCode()))); }
            };
        }

        @SuppressWarnings("unchecked")
        public static<E> com.skylarkarms.lambdas.Consumers<E> getDefaultEmpty() { return (com.skylarkarms.lambdas.Consumers<E>) EMPTY_CONSUMER.ref; }

        public static boolean isEmpty(Consumer<?> aConsumer) { return aConsumer == EMPTY_CONSUMER.ref; }
    }

    private record emptyRunnable() {
        private static final Runnable ref = new Runnable() {
            @Override
            public void run() {

            }

            @Override
            public String toString() { return super.toString() + ": [EMPTY FUNCTION]"; }
        };
    }

    public static Runnable emptyRunnable() { return emptyRunnable.ref; }
    public static boolean isEmpty(Runnable runnable) { return runnable == emptyRunnable.ref; }
}
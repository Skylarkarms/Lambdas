package com.skylarkarms.lambdas;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Implementations of this interface, either {@link Impl} or {@link Impl.Synchronized} will lazily apply a
 * call to the {@link Supplier} function provided in the constructor when a call to {@link #get()} is executed,
 * IF and ONLY IF the inner value {@code `T`} is found to be {@code `null`}.
 * */
public interface InstanceSupplier<T> extends Supplier<T> {
    /**
     * Will apply the {@code 'builder'} {@link Supplier} lazily if the inner {@code `T`} value is null
     * */
    @Override
    T get();

    /**
     * Gets the field without triggering the inner {@link Supplier} function.
     * It will return {@code `null`} if {@link #get()} hasn't been applied yet.
     * Used for fast loads.
     * */
    T plainGet();

    /**
     * Will retrieve the last value before nullifying the value.
     * <p> Will return null if the  value was already null.
     * */
    T getAndClear();

    /**
     * Will nullify the inner value and return true if successful.
     * <p> Will return false if the value was already null.
     * */
    boolean clear();

    static<S> Impl<S> getSupplier(Supplier<S> builder) {
        return builder instanceof InstanceSupplier.Impl<S> impl ? impl : new Impl<>(builder);
    }

    static<S> Impl.Synchronized<S> getSynchronizedSupplier(Supplier<S> builder) {
        return builder instanceof Impl.Synchronized<S> conc ? conc : new Impl.Synchronized<>(new Object(), builder);
    }

    class Impl<T> implements InstanceSupplier<T> {
        T field = null;

        final Supplier<T> builder;

        Impl(Supplier<T> builder) {
            if (builder == null) throw new IllegalArgumentException("Supplier cannot be null");
            if (builder instanceof InstanceSupplier.Impl<T>) throw new IllegalArgumentException("Supplier should not be instance of this class.");
            this.builder = builder;
        }

        public static final class Synchronized<T> extends Impl<T> {
            volatile T volatileT;
            final Object monitor;

            public Object getMonitor() { return monitor; }

            public Synchronized(Object monitor, Supplier<T> builder) {
                super(builder);
                if (monitor == null) throw new IllegalArgumentException("`monitor` argument cannot be null");
                this.monitor = monitor;
            }

            public T volatileGet() { return volatileT; }

            @Override
            public T get() {
                T next = field;
                if (next == null) {
                    synchronized (monitor) {
                        next = volatileT;
                        if (next == null) {
                            next = builder.get();
                            volatileT = next;
                            field = next;
                        }
                    }
                }
                return next;
            }

            @Override
            public T getAndClear() {
                if (field == null) return null;
                else {
                    T last;
                    synchronized (monitor) {
                        if ((last = volatileT) != null) {
                            volatileT = null;
                            field = null;
                        }
                    }
                    return last;
                }
            }

            @Override
            public boolean clear() {
                if (field == null) return false;
                else {
                    synchronized (monitor) {
                        if (volatileT != null) {
                            volatileT = null;
                            field = null;
                            return true;
                        }
                        return false;
                    }
                }
            }
        }

        @Override
        public T get() {
            T next;
            if ((next = field) == null) {
                next = builder.get();
                field = next;
            }
            return next;
        }

        @Override
        public T plainGet() { return field; }

        @Override
        public T getAndClear() {
            T last;
            if ((last = field) != null) {
                field = null;
            }
            return last;
        }

        @Override
        public boolean clear() {
            if (field != null) {
                field = null;
                return true;
            }
            return false;
        }

        public boolean equals(Impl<?> l) {
            return this == l ||
                    (
                            ((field == l.field) || (field != null && field.equals(l.field)))
                                    &&
                                    ((builder == l.builder) || builder.equals(l.builder))
                    );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (o instanceof Impl<?> l) {
                return equals(l);
            } else if (Objects.equals(field, o)) {
                return true;
            } else if (o instanceof Supplier<?> s) {
                return Objects.equals(builder, s);
            } else return false;
        }

        @Override
        public int hashCode() { return Objects.hash(field, builder); }
    }
}

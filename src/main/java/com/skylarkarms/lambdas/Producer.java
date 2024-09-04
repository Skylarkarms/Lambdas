package com.skylarkarms.lambdas;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Producer <a href="package-summary.html">functional interface</a> for reactive patterns.
 * Functional method: {@link #register(Consumer)}
 * */
@FunctionalInterface
public interface Producer<V> {
    /**
     * Main method that accepts a {@link Consumer} observer.
     * @param valueConsumer the observer to be registered.
     * */
    void register(Consumer<? super V> valueConsumer);

    /**
     * @implSpec The return value may be used as indication that the unregistering action
     * was successful or not.
     * */
    default boolean unregister(Consumer<? super V> valueConsumer) {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * @implSpec The returning value may be that of the previous {@link Consumer} registered.
     * */
    default Consumer<? super V> unregister() {
        throw new RuntimeException("Method not implemented");
    }

    static<V, E extends RuntimeException> Producer<V> illegalProducer(Supplier<E> exception) {
        return valueConsumer -> {
            throw exception.get();
        };
    }
}

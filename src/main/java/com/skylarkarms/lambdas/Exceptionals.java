package com.skylarkarms.lambdas;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;

public final class Exceptionals {
    private Exceptionals() { }

    private static final String
            message = "\n >> Set `Lambdas.DEBUG_CONFIG_MODE.tracer(boolean)` to `true` for deeper tracing insights.";
    private static final Suppliers.OfString errorMessageSup = () -> message;

    public enum Tracer {
        on(() -> {
            StackTraceElement[] es = Thread.currentThread().getStackTrace();
            return () -> {
                return formatStack(5, es);
            };
        })
        , off(
                () -> errorMessageSup
        );
        final Supplier<Suppliers.OfString> tracer;

        Tracer(Supplier<Suppliers.OfString> tracer) {
            this.tracer = tracer;
        }
    }

    /**
     * WIll print a stack trace beginning at the index specified.
     * */
    public static String formatStack(int startAt, StackTraceElement[] es) throws AssertionError {
        int le = es.length;
        assert le > startAt : "`startAt` index [" + startAt + "] greater than or equal to StackTraceElement arrays length [" + le  +"].";
        StringBuilder sb = new StringBuilder(((le - startAt) * 2) + 2);
        sb.append("\n >> stack {");
        for (int i = startAt; i < le; i++) {
            StackTraceElement e = es[i];
            sb.append("\n   - at [").append(i).append("] ").append(e);
        }
        return sb.append("\n } << stack. [total length = ").append(le).append("]").toString();
    }

    /**
     * WIll print a stack trace beginning and ending at the indices specified.
     * */
    public static String formatStack(int startAt, int endAt, StackTraceElement[] es) throws AssertionError {
        int le = es.length;
        assert endAt > startAt : "`endAt`[" + endAt + "] cannot be lesser than `startAt`[" + startAt + "]";
        assert le > startAt : "`startAt` index [" + startAt + "] greater than or equal to StackTraceElement arrays length [" + le  +"].";
        StringBuilder sb = new StringBuilder(((le - startAt) * 2) + 2);
        sb.append("\n >> stack {");
        int last_endAt = Math.min(endAt, le);
        for (int i = startAt; i < last_endAt; i++) {
            StackTraceElement e = es[i];
            sb.append("\n   - at [").append(i).append("] ").append(e);
        }
        return sb.append("\n } << stack. [total length = ").append(le).append("]").toString();
    }

    private record LAMBDA_DEBUG_CONFIG() {
        private static final DebugConfig.Global ref = DebugConfig.Global.getDefaultInstance();
        static void set(Consumer<DebugConfig.Global> config){ config.accept(ref); }
    }

    public static void set(Consumer<DebugConfig.Global> config){ LAMBDA_DEBUG_CONFIG.set(config); }

    public static final class DebugConfig {

        /**
         * Multi-process step, where the lambda call-site must call on {@link Token#tracer#get()}, then the exception will apply the String generated by the tracer.
         * */
        public static class Token {
            Supplier<Suppliers.OfString> tracer;
            BiFunction<String, Throwable, RuntimeException> exception;

            static final Token default_token = new Token(Tracer.on.tracer, RuntimeException::new);

            private Token(Supplier<Suppliers.OfString> tracer, BiFunction<String, Throwable, RuntimeException> exception) {
                this.tracer = tracer;
                this.exception = exception;
            }

            RuntimeException apply(String mess, Throwable t) { return exception.apply(mess, t); }

            public static Token tracing(Tracer tracer){ return new Token(tracer.tracer, default_token.exception); }

            public static Token full(Tracer tracer, BiFunction<String, Throwable, RuntimeException> exception){
                return new Token(tracer.tracer, exception);
            }

            public static Token except(BiFunction<String, Throwable, RuntimeException> exception){
                return new Token(default_token.tracer, exception);
            }

            public static Token sysDefaults(){ return default_token; }
        }

        public static final class Global extends Token {

            public static Global getDefaultInstance() {return new Global(default_token.tracer, default_token.exception);}

            private Global( Supplier<Suppliers.OfString> tracer, BiFunction<String, Throwable, RuntimeException> exception) {
                super(tracer, exception);
            }

            /**
             * The {@link RuntimeException} object passed must be built within the bounds of the lambda scope to properly pass StackTrace details of the lambda call-site.
             * If {@link #setTracing(boolean)} has been set to `{@code true}`, then the information provided will be inside the {@link String} parameter provided by this function, If not, then you can ignore this parameter.
             * <p> The additional tracing insight will be formatted as:
             * <pre>
             *>> tracing insights {
             *    - at ...
             *    - at ...
             *  } << tracing insights.
             * </pre>
             * To add more message, simply {@link String#concat(String)} with the existing tracing insight and pass the new {@link String} object.
             * The Throwable will be the original Exception that was thrown by the upstream.
             * <p> <B>You fail to use proper constructors... you miss on call-site Exception information.</B>
             * <p> Base constructor to use should be at least of type
             * <ul><li>{@link RuntimeException#Exception(Throwable)}</li></ul>
             * <p> for {@link #setTracing(boolean)} set to `{@code true}` the constructor must be
             * <ul>
             *     <li>`{@link RuntimeException#RuntimeException(String, Throwable)}`</li>
             *     <li> OR {@link RuntimeException#RuntimeException(String, Throwable, boolean, boolean)}</li>
             * </ul>
             * <p> `Tokenized` versions of this parameter can be delivered customarily for each lambda call-site via {@link Token}.
             * */
            public Global setException(BiFunction<String, Throwable, RuntimeException> exception) {
                Objects.requireNonNull(exception);
                this.exception = exception;
                return this;
            }

            public Global setTracing(boolean active) {
                this.tracer = active ? Tracer.on.tracer : Tracer.off.tracer;
                return this;
            }
        }

        public static class ExceptionFactory {

            private record factoryMap() { private static final Map<String, ExceptionFactory> ref = new ConcurrentHashMap<>(); }

            public ExceptionFactory() { }

            public ExceptionFactory(String tag) {
                factoryMap.ref.put(tag, this);
            }

            public static ExceptionFactory get(String tag) {
                ExceptionFactory res = factoryMap.ref.get(tag);
                if (res == null) throw new NullPointerException("No factory of tag [" + tag +"]");
                return res;
            }

            private Mapper mapper = Off.OFF;

            public final Global config = Global.getDefaultInstance();

            public void setActive(boolean value) {
                if (value) {
                    if (!mapper.isOn()) {
                        mapper = On.ON;
                    }
                } else {
                    if (mapper.isOn()) {
                        mapper = Off.OFF;
                    }
                }
            }
            public <T> Predicate<T> apply(Predicate<T> fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            public <T> Consumer<T> apply(Consumer<T> fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            public <T> Supplier<T> apply(Supplier<T> fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            public <T> BinaryPredicate<T> apply(BinaryPredicate<T> fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            public <T, U> Function<T, U> apply(Function<T, U> fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            public Runnable apply(Runnable fun) throws AssertionError {
                assert mapper != null : "Must call setActive(boolean)";
                return mapper.apply(config, fun);
            }
            interface Mapper {
                default boolean isOn() { return false; }
                <T> Predicate<T> apply(Token config, Predicate<T> fun);
                <T> Consumer<T> apply(Token config, Consumer<T> fun);
                <T> Supplier<T> apply(Token config, Supplier<T> fun);
                <T> BinaryPredicate<T> apply(Token config, BinaryPredicate<T> fun);
                <T, U> Function<T, U> apply(Token config, Function<T, U> fun);
                Runnable apply(Token config, Runnable action);
            }
            static class On implements Mapper {
                static final On ON = new On();

                @Override
                public boolean isOn() { return true; }

                @Override
                public <T> Predicate<T> apply(Token config, Predicate<T> fun) { return exceptional(config, fun); }

                @Override
                public <T> Consumer<T> apply(Token config, Consumer<T> fun) { return exceptional(config, fun); }

                @Override
                public <T> Supplier<T> apply(Token config, Supplier<T> fun) { return exceptional(config, fun); }

                @Override
                public <T> BinaryPredicate<T> apply(Token config, BinaryPredicate<T> fun) {
                    return exceptional(config, fun);
                }

                @Override
                public <T, U> Function<T, U> apply(Token config, Function<T, U> fun) {
                    return exceptional(config, fun);
                }

                @Override
                public Runnable apply(Token config, Runnable fun) { return exceptional(config, fun); }
            }
            static class Off implements Mapper {
                static final Off OFF = new Off();
                @Override
                public <T> Predicate<T> apply(Token config, Predicate<T> test) { return test; }

                @Override
                public <T> Consumer<T> apply(Token config, Consumer<T> test) { return test; }

                @Override
                public <T> Supplier<T> apply(Token config, Supplier<T> test) { return test; }

                @Override
                public <T> BinaryPredicate<T> apply(Token config, BinaryPredicate<T> test) { return test; }

                @Override
                public <T, U> Function<T, U> apply(Token config, Function<T, U> fun) { return fun; }

                @Override
                public Runnable apply(Token config, Runnable action) { return action; }
            }
        }
    }

    private static boolean areEqual(Object obj, Object core, Object thisO) {
        return obj != null &&
                (
                        ((obj == core) || obj.equals(core))
                                ||
                                ((obj == thisO) || obj.equals(thisO))
                );
    }

    public static<T> Consumer<T> exceptional(Consumer<T> core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static<T> Consumer<T> exceptional(DebugConfig.Token config, Consumer<T> core) {
        nullCheck(config, core);
        return new Consumer<>() {
            final Suppliers.OfString stack = config.tracer.get();
            @Override
            public void accept(T t) {
                try {
                    core.accept(t);
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public Consumer<T> andThen(Consumer<? super T> after) {
                Objects.requireNonNull(after);
                return exceptional(config,
                        t -> {
                            try {
                                accept(t);
                                after.accept(t);
                            } catch (Throwable e) {
                                throw config.apply(stack.get(), e);
                            }
                        }
                );
            }

            @Override
            public boolean equals(Object obj) { return areEqual(obj, core, this); }

            @Override
            public int hashCode() { return Objects.hashCode(core); }
        };
    }

    public static<T> Predicates<T> exceptional(Predicate<T> core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static<T> Predicates<T> exceptional(DebugConfig.Token config, Predicate<T> core) {
        nullCheck(config, core);
        return new Predicates<>() {
            final Suppliers.OfString stack = config.tracer.get();
            @Override
            public boolean test(T t) {
                try {
                    return core.test(t);
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public <V> Predicates<V> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return exceptional(config, (Predicate<V>) (V v) -> apply(before.apply(v)));
            }

            @Override
            public <V> Function<T, V> andThen(Function<? super Boolean, ? extends V> after) {
                Objects.requireNonNull(after);
                return exceptional(config, (T t) -> after.apply(apply(t)));
            }

            @Override
            public Predicates<T> and(Predicate<? super T> other) {
                Objects.requireNonNull(other);
                return exceptional(config, (Predicate<T>)(t) -> test(t) && other.test(t));
            }

            @Override
            public Predicates<T> negate() {
                return exceptional(config, (Predicate<T>) (t) -> !test(t));
            }

            @Override
            public Predicates<T> or(Predicate<? super T> other) {
                Objects.requireNonNull(other);
                return exceptional(config, (Predicate<T>)(t) -> test(t) || other.test(t));
            }

            @Override
            public boolean equals(Object o) { return areEqual(o, core, this); }

            @Override
            public int hashCode() { return Objects.hash(core); }
        };
    }

    public static<T> BinaryPredicate<T> exceptional(BinaryPredicate<T> core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static<T> BinaryPredicate<T> exceptional(DebugConfig.Token config, BinaryPredicate<T> core) {
        nullCheck(config, core);
        return new BinaryPredicate<>() {
            final Suppliers.OfString stack = config.tracer.get();
            @Override
            public boolean test(T next, T prev) {
                try {
                    return core.test(next, prev);
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public BinaryPredicate<T> negate() { return exceptional(config, (T t, T u) -> !test(t, u)); }

            @Override
            public BinaryPredicate<T> and(BiPredicate<? super T, ? super T> other) {
                Objects.requireNonNull(other);
                return exceptional(config, (T t, T u) -> test(t, u) && other.test(t, u));
            }

            @Override
            public BinaryPredicate<T> or(BiPredicate<? super T, ? super T> other) {
                Objects.requireNonNull(other);
                return exceptional(config, (T t, T u) -> test(t, u) || other.test(t, u));
            }

            @Override
            public BinaryPredicate<T> or(BinaryPredicate<? super T> other) {
                Objects.requireNonNull(other);
                return exceptional(config, (T t, T u) -> test(t, u) || other.test(t, u));
            }

            @Override
            public boolean equals(Object o) { return areEqual(o, core, this); }

            @Override
            public int hashCode() { return Objects.hash(core); }
        };
    }

    public static<T, U> Function<T, U> exceptional(Function<T, U> core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static<T, U> Function<T, U> exceptional(DebugConfig.Token config, Function<T, U> core) {
        nullCheck(config, core);
        return new Function<>() {
            final Suppliers.OfString stack = config.tracer.get();
            @Override
            public U apply(T t) {
                try {
                    return core.apply(t);
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public <V> Function<V, U> compose(Function<? super V, ? extends T> before) {
                Objects.requireNonNull(before);
                return exceptional(config, (V v) -> apply(before.apply(v)));
            }

            @Override
            public <V> Function<T, V> andThen(Function<? super U, ? extends V> after) {
                Objects.requireNonNull(after);
                return exceptional(config, (T t) -> after.apply(apply(t)));
            }

            @Override
            public boolean equals(Object o) { return areEqual(o, core, this); }

            @Override
            public int hashCode() { return Objects.hash(core); }
        };
    }

    public static Runnable exceptional(Runnable core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static Runnable exceptional(DebugConfig.Token config, Runnable core) {
        nullCheck(config, core);
        return new Runnable() {
            final Suppliers.OfString stack = config.tracer.get();
            @Override
            public void run() {
                try {
                    core.run();
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public boolean equals(Object obj) { return areEqual(obj, core, this); }

            @Override
            public int hashCode() { return Objects.hash(core); }
        };
    }

    public static<E> Supplier<E> exceptional(Supplier<E> core) { return exceptional(LAMBDA_DEBUG_CONFIG.ref, core); }
    public static<E> Supplier<E> exceptional(DebugConfig.Token config, Supplier<E> core) {
        nullCheck(config, core);
        return new Supplier<>() {
            final Suppliers.OfString stack = config.tracer.get();

            @Override
            public E get() {
                try {
                    return core.get();
                } catch (Throwable e) {
                    throw config.apply(stack.get(), e);
                }
            }

            @Override
            public boolean equals(Object o) { return areEqual(o, core, this); }

            @Override
            public int hashCode() { return Objects.hash(core); }
        };
    }

    private static void nullCheck(DebugConfig.Token config, Object core) {
        if (config == null || core == null) {
            throw new NullPointerException("Parameters must not be null: config=[" + config + "], core=[" + core + "].");
        }
    }
}

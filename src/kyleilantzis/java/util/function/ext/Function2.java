package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function2<A, B, R> extends java.util.function.BiFunction<A, B, R> {

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function<B, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b) -> apply(a, b);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function2<A, B, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b) -> p.apply(apply(a, b));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function3<A, B, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s) -> p.apply(apply(a, b), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function4<A, B, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s, T t) -> p.apply(apply(a, b), s, t);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, Z> Function5<A, B, S, T, U, Z> compose(Function4<R, S, T, U, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s, T t, U u) -> p.apply(apply(a, b), s, t, u);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, Z> Function6<A, B, S, T, U, V, Z> compose(Function5<R, S, T, U, V, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s, T t, U u, V v) -> p.apply(apply(a, b), s, t, u, v);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, Z> Function7<A, B, S, T, U, V, W, Z> compose(Function6<R, S, T, U, V, W, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s, T t, U u, V v, W w) -> p.apply(apply(a, b), s, t, u, v, w);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, X, Z> Function8<A, B, S, T, U, V, W, X, Z> compose(Function7<R, S, T, U, V, W, X, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, S s, T t, U u, V v, W w, X x) -> p.apply(apply(a, b), s, t, u, v, w, x);
	}

}

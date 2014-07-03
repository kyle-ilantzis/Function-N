package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function<A, R> extends java.util.function.Function<A, R> {

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 * In this case, the is simply equivalent to apply.
	 */
	default R curry(A a) {
		Objects.requireNonNull(a);
		return apply(a);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function<A, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a) -> p.apply(apply(a));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function2<A, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s) -> p.apply(apply(a), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function3<A, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t) -> p.apply(apply(a), s, t);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, Z> Function4<A, S, T, U, Z> compose(Function4<R, S, T, U, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t, U u) -> p.apply(apply(a), s, t, u);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, Z> Function5<A, S, T, U, V, Z> compose(Function5<R, S, T, U, V, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t, U u, V v) -> p.apply(apply(a), s, t, u, v);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, Z> Function6<A, S, T, U, V, W, Z> compose(Function6<R, S, T, U, V, W, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t, U u, V v, W w) -> p.apply(apply(a), s, t, u, v, w);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, X, Z> Function7<A, S, T, U, V, W, X, Z> compose(Function7<R, S, T, U, V, W, X, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t, U u, V v, W w, X x) -> p.apply(apply(a), s, t, u, v, w, x);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, X, Y, Z> Function8<A, S, T, U, V, W, X, Y, Z> compose(Function8<R, S, T, U, V, W, X, Y, Z> p) {
		Objects.requireNonNull(p);
		return (A a, S s, T t, U u, V v, W w, X x, Y y) -> p.apply(apply(a), s, t, u, v, w, x, y);
	}

}

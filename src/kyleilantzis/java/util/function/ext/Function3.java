package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function3<A, B, C, R>  {

	/*
	 * Accept values and produce a result.
	 */
	R apply(A a, B b, C c);

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function2<B, C, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b, C c) -> apply(a, b, c);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function3<A, B, C, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c) -> p.apply(apply(a, b, c));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function4<A, B, C, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, S s) -> p.apply(apply(a, b, c), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function5<A, B, C, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, S s, T t) -> p.apply(apply(a, b, c), s, t);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, Z> Function6<A, B, C, S, T, U, Z> compose(Function4<R, S, T, U, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, S s, T t, U u) -> p.apply(apply(a, b, c), s, t, u);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, Z> Function7<A, B, C, S, T, U, V, Z> compose(Function5<R, S, T, U, V, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, S s, T t, U u, V v) -> p.apply(apply(a, b, c), s, t, u, v);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, W, Z> Function8<A, B, C, S, T, U, V, W, Z> compose(Function6<R, S, T, U, V, W, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, S s, T t, U u, V v, W w) -> p.apply(apply(a, b, c), s, t, u, v, w);
	}

}

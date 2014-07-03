package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function4<A, B, C, D, R>  {

	/*
	 * Accept values and produce a result.
	 */
	R apply(A a, B b, C c, D d);

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function3<B, C, D, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b, C c, D d) -> apply(a, b, c, d);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function4<A, B, C, D, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d) -> p.apply(apply(a, b, c, d));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function5<A, B, C, D, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, S s) -> p.apply(apply(a, b, c, d), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function6<A, B, C, D, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, S s, T t) -> p.apply(apply(a, b, c, d), s, t);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, Z> Function7<A, B, C, D, S, T, U, Z> compose(Function4<R, S, T, U, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, S s, T t, U u) -> p.apply(apply(a, b, c, d), s, t, u);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, V, Z> Function8<A, B, C, D, S, T, U, V, Z> compose(Function5<R, S, T, U, V, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, S s, T t, U u, V v) -> p.apply(apply(a, b, c, d), s, t, u, v);
	}

}

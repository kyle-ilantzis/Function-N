package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function5<A, B, C, D, E, R>  {

	/*
	 * Accept values and produce a result.
	 */
	R apply(A a, B b, C c, D d, E e);

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function4<B, C, D, E, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b, C c, D d, E e) -> apply(a, b, c, d, e);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function5<A, B, C, D, E, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e) -> p.apply(apply(a, b, c, d, e));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function6<A, B, C, D, E, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, S s) -> p.apply(apply(a, b, c, d, e), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function7<A, B, C, D, E, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, S s, T t) -> p.apply(apply(a, b, c, d, e), s, t);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, U, Z> Function8<A, B, C, D, E, S, T, U, Z> compose(Function4<R, S, T, U, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, S s, T t, U u) -> p.apply(apply(a, b, c, d, e), s, t, u);
	}

}

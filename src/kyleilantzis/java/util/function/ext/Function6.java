package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function6<A, B, C, D, E, F, R>  {

	/*
	 * Accept values and produce a result.
	 */
	R apply(A a, B b, C c, D d, E e, F f);

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function5<B, C, D, E, F, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b, C c, D d, E e, F f) -> apply(a, b, c, d, e, f);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function6<A, B, C, D, E, F, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, F f) -> p.apply(apply(a, b, c, d, e, f));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function7<A, B, C, D, E, F, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, F f, S s) -> p.apply(apply(a, b, c, d, e, f), s);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, T, Z> Function8<A, B, C, D, E, F, S, T, Z> compose(Function3<R, S, T, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, F f, S s, T t) -> p.apply(apply(a, b, c, d, e, f), s, t);
	}

}

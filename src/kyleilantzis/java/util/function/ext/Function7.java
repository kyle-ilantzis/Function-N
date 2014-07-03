package kyleilantzis.java.util.function.ext;

import java.util.Objects;

/*
 * An object that accepts values and produces a result based on the accepted values.
 */
@FunctionalInterface
public interface Function7<A, B, C, D, E, F, G, R>  {

	/*
	 * Accept values and produce a result.
	 */
	R apply(A a, B b, C c, D d, E e, F f, G g);

	/*
	 * Partially apply this function and return a function that accepts one value less.
	 */
	default Function6<B, C, D, E, F, G, R> curry(A a) {
		Objects.requireNonNull(a);
		return (B b, C c, D d, E e, F f, G g) -> apply(a, b, c, d, e, f, g);
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <Z> Function7<A, B, C, D, E, F, G, Z> compose(Function<R, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, F f, G g) -> p.apply(apply(a, b, c, d, e, f, g));
	}

	/*
	 * Returns a new function based on this and the other function.
	 * The new function accepts values for this function and applies them,
	 * then applies the returned value and other values to the next function.
	 */
	default <S, Z> Function8<A, B, C, D, E, F, G, S, Z> compose(Function2<R, S, Z> p) {
		Objects.requireNonNull(p);
		return (A a, B b, C c, D d, E e, F f, G g, S s) -> p.apply(apply(a, b, c, d, e, f, g), s);
	}

}

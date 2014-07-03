#Function-N#

A java 8 functional extension.

This "extension" provides extra function objects that the java.util.function package does not offer. You can find
java.util.function.Function and java.util.function.BiFunction but there is nothing for functions that accept 3 arguments
or more. With the Function-N extension you can have functions that accept 1 argument to functions that accept 8

##Function composition##

You can compose functions of N with functions of M to get functions of N + M - 1. For example, a Function3 composed with
a Function4 returns a Function6.


##Function currying##

You can partially apply a function of N by supplying it with some value and receiving a function of N - 1. Each function
of N provides a curry function to do so. For example

    Function2<Integer, Integer, Integer> add = (x, y) -> x + y;
    Function<Integer, Integer> addc = add.curry(5);
    System.out.println( addc.apply( 4 ) ); // prints 9

##Extendible##

Function-N supports up to 8 functions but you can easily extends the function generator to produce 16, 32, 64, ...
function objects!


#Function-N#

A java 8 functional extension.

This "extension" provides extra function objects that the java.util.function package does not offer. You can find
java.util.function.Function and java.util.function.BiFunction but there is nothing for functions that accept 3 arguments
or more.

With the Function-N extension you can have functions that accept 1 argument to functions that accept 8. You can compose functions
of N with functions of M to get functions of N + M - 1. For example, a Function3 composed with a Function4 returns a
Function6.

Function-N supports up to 8 functions but you can easily extends the function generator to produce 16, 32, 64, ...
function objects!
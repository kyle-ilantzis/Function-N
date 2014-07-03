package kyleilantzis.java.util.function.ext.generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Generate FunctionX.java files for the kyleilantzis.java.util.function.ext package.
 *
 * Creates Function.java to Function8.java
 *
 * @author kilantzis
 */
public class GenerateFunctions {

    private static final String PACKAGE_NAME = "kyleilantzis.java.util.function.ext";
    // IDE sets current working directory to the project path, so we write the files in the package.
    private static final File   DIR          = new File( "src/" + PACKAGE_NAME.replace( '.', '/' ) );
    // Only 8 function classes, no more, no less.
    public static final int NUM = 8;

    public static void main( String[] args ) throws FileNotFoundException {
        for ( int i = 1; i <= NUM; i++ ) {
            FuntionXJavaCode( i );
        }
    }

    public static void FuntionXJavaCode( int x ) throws FileNotFoundException {

        String className = className(x);
        String classGenerics = generics( x, 'A', "R" );
        String applyParams = params(x, 'A', 'a');
        String curryGenerics = generics( x- 1, 'B', "R" );
        String curryParams = params(x - 1, 'B', 'b');
        String applyVars = vars( x, 'a' );
        String curryReturnType = (x == 1) ? "R" : className(x - 1) + curryGenerics;

        IndentedPrintWriter out = new IndentedPrintWriter( new PrintWriter( new FileOutputStream( new File(DIR, className + ".java") ) ) );

        out.println( "package " + PACKAGE_NAME + ";" )
           .println()
           .println( "import java.util.Objects;" )
           .println()
           .println("/*")
           .println(" * An object that accepts values and produces a result based on the accepted values.")
           .println(" */")
           .println("@FunctionalInterface")
           .print("public interface " + className +  classGenerics + " ")
                .printIf( x == 2, "extends java.util.function.BiFunction" + classGenerics )
                .printIf( x == 1, "extends java.util.function.Function" + classGenerics )
                .println( " {" )
           .println()
           .indent()
                .printlnIf( x >= 3, "/*")
                .printlnIf( x >= 3, " * Accept values and produce a result.")
                .printlnIf( x >= 3, " */")
                .printlnIf( x >= 3, "R apply" + applyParams + ";" )
                .printlnIf( x >= 3 )
                .println("/*")
                .println(" * Partially apply this function and return a function that accepts one value less.")
                .printlnIf( x == 1, " * In this case, the is simply equivalent to apply.")
                .println(" */")
                .print( "default ")
                    .printIf( x == 1, "R")
                    .printIf( x > 1, curryReturnType)
                    .println(" curry(A a) {")
                .indent()
                    .println("Objects.requireNonNull(a);")
                    .print("return ").printIf( x > 1, curryParams + " -> ").println( "apply(" + applyVars + ");" )
                .unindent().println( "}" )
                .println();

                for (int i = 1; i <= NUM; i++) {
                    if (x + i - 1 > 8) { continue; }
                    ComposeFunctionJavaCode( out, x, i );
                    out.println();
                }

           out.unindent().println( "}" );

        out.close();
    }

    public static void ComposeFunctionJavaCode(IndentedPrintWriter out, int x, int y) {
        String inputParamGenerics = generics(y, 'R', "Z");
        int outputFunctionX = x + y - 1;
        String NAME = generics( y - 1, 'S', "Z" );
        String outputGenerics = generics( x , 'A', NAME.replace( '<', ' ' ).replace( '>',  ' ' ).trim() );
        String xParams = params(x, 'A', 'a');
        String yParams = params(y - 1, 'S', 's').substring( 1 );

        String applyFVars = vars(x, 'a');
        String applyGVars = vars(y - 1, 's');

        out.println("/*")
           .println(" * Returns a new function based on this and the other function.")
           .println(" * The new function accepts values for this function and applies them,")
           .println(" * then applies the returned value and other values to the next function.")
           .println(" */")
           .print("default " + NAME + " Function")
                .printIf( outputFunctionX > 1, Integer.toString(outputFunctionX) )
                .print( outputGenerics + " compose(Function" )
                .printIf( y > 1, Integer.toString( y ))
                .println( inputParamGenerics + " p) {" )
           .indent()
                .println("Objects.requireNonNull(p);")
                .print("return ")
                .printIf( y == 1, xParams )
                .printIf(y > 1, xParams.replace( ")", ", ") + yParams )
                .print( " -> p.apply(apply(" + applyFVars )
                .printIf( y == 1, ")" )
                .printIf( y > 1, "), " + applyGVars )
                .println( ");" )
           .unindent().println( "}" );
    }

    public static String className(int x) {
        return "Function" + ((x > 1) ? Integer.toString( x ) : "");
    }

    public static String generics(int x, char c, String r) {
        StringBuilder generics = new StringBuilder();
        generics.append('<');
        for (int i = 0; i < x; i++) {
            generics.append( (char)(c + i) ).append( ", " );
        }
        generics.append( r )
                .append( ">" );
        return generics.toString();
    }

    public static String params(int x, char type, char name) {
        StringBuilder params = new StringBuilder();
        params.append( '(' );
        for (int i = 0; i < x; i++) {
            params.append( (char)(type + i) ).append( ' ' ).append( (char)(name + i) );
            if (i != x - 1) { params.append( ", " ); }
        }
        params.append( ')' );
        return params.toString();
    }

    public static String vars(int x, char c) {
        StringBuilder vars = new StringBuilder();
        for(int i = 0; i < x; i++) {
            vars.append( (char) ( c + i ) );
            if (i != x - 1) { vars.append( ", " ); }
        }
        return vars.toString();
    }
}

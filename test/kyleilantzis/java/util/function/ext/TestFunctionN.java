package kyleilantzis.java.util.function.ext;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamBuilder;

/**
 * @author kilantzis
 */
public class TestFunctionN {

    public static Stream<Character> stringstream(String str) {

        StreamBuilder<Character> sb = Stream.builder();
        for(int i = 0, len = str.length(); i < len; i++) {
            sb.accept(str.charAt( i ));
        }
        return sb.build();
    }

    public static String streamstring(Stream<Character> s) {
        StringBuilder b = new StringBuilder(  );
        s.forEach( b::append );
        return b.toString();
    }

    @Test
    public void testCurry() {

        Function2<Integer, Integer, Integer> min = Math::min;
        Function<Integer, Integer> curriedMin = min.curry( 0 );

        assert curriedMin.apply( 1 ) == 0;
    }

    @Test
    public void compose() {

        Function2<String, Integer, Character> charAt = String::charAt;
        Function<Character, Boolean> isLetterD = (c) -> c == 'D';

        Function2<String, Integer, Boolean> isDAt = charAt.compose( isLetterD );

        assert isDAt.apply( "abcDefg", 3 );
    }

    @Test
    public void composeAndCurry() {

        Function<String, Integer> f1 = Integer::parseInt;
        Function2<Integer, Integer, Integer> f2 = Math::addExact;
        Function2<String, Integer, Integer> f3 = f1.compose( f2 );
        Function<Integer, Integer> f4 = f3.curry( "5" );

        assert f4.apply(103) == 5 + 103;
    }

    @Test
    public void curryAndCompose() {

        Function2<Stream<String>, Function<String, String>, Stream<String>> map = (Stream<String> s, Function<String, String> g) -> s.map( g );
        Function<Function<String, String>, Stream<String>> mapFood =  map.curry( Arrays.asList("apple", "banana", "orange", "mango").stream() );

        Function<String, String> capOs = s -> streamstring( stringstream( s ).map( c -> c == 'o' ? 'O' : c ) );
        Function<Function<String,String>, Function<String,String>> ceaser = f -> ( s -> f.apply(streamstring(stringstream(s).map(c -> (char)(c + 2)))));

        Function<Function<String,String>, Stream<String>> mapCeaserFood = ceaser.compose(mapFood);

        ArrayList<String> ss = new ArrayList<>();
        mapCeaserFood.apply( capOs ).forEach( ss::add );

        Function<String,String> ceaserAndCapOs = ceaser.apply(capOs);
        assert ss.equals( Arrays.asList(ceaserAndCapOs.apply("apple"),
                                        ceaserAndCapOs.apply("banana"),
                                        ceaserAndCapOs.apply("orange"),
                                        ceaserAndCapOs.apply("mango")) );
   }

    @Test
    public void curryAndCurry() {

        Function2<Integer, String, String> ceaserBy = (i, s) -> streamstring(stringstream( s ).map( c -> (char)(c + i) ));

        assert ceaserBy.curry(3).curry("the quick brown fox").equals( ceaserBy.apply(3, "the quick brown fox") );
    }
}

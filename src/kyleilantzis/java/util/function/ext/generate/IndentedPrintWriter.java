package kyleilantzis.java.util.function.ext.generate;

import java.io.PrintWriter;

/**
 * @author kilantzis
 */
public class IndentedPrintWriter {

    private final PrintWriter pw;

    private int ctr;
    private IndentedLinePrintWriter indentedLine;

    public IndentedPrintWriter( PrintWriter pw ) {
        this.pw = pw;
        ctr = 0;
        indentedLine = new IndentedLinePrintWriter();
    }

    public IndentedPrintWriter indent() {
        ctr++;
        return this;
    }

    public IndentedPrintWriter unindent() {
        ctr = Math.max(0, ctr - 1);
        return this;
    }

    public IndentedPrintWriter println() {
        pw.println();
        return this;
    }

    public IndentedPrintWriter println( String x ) {
        printTabs();
        pw.println( x );
        return this;
    }

    public IndentedLinePrintWriter print( String s ) {
        printTabs();
        pw.print( s );
        return indentedLine;
    }

    public IndentedPrintWriter printlnIf(boolean b) {
        if (b) { println(); }
        return this;
    }

    public IndentedPrintWriter printlnIf(boolean b, String s) {
        if (b) { println(s); }
        return this;
    }

    public void close() {
        pw.close();
    }

    private void printTabs() {
        for (int i = 0; i < ctr; i++) {
            pw.print('\t');
        }
    }

    public class IndentedLinePrintWriter {

        private IndentedLinePrintWriter(){}

        public IndentedLinePrintWriter print( String s ) {
            pw.print( s );
            return this;
        }

        public IndentedLinePrintWriter printIf(boolean b, String s) {
            if (b) { this.print(s); }
            return this;
        }

        public IndentedPrintWriter println() {
            pw.println();
            return IndentedPrintWriter.this;
        }

        public IndentedPrintWriter println( String s ) {
            pw.println( s );
            return IndentedPrintWriter.this;
        }
    }
}
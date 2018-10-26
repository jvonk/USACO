
/*
ID: 100021881
LANG: JAVA
PROG: fracdec
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fracdec {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("fracdec.in"));

        // read first line with V and N
        StringTokenizer l = new StringTokenizer(br.readLine());

        // get V, the number of coins in the system
        int N = Integer.parseInt(l.nextToken());

        // get N, the amount fracdec to construct
        int D = Integer.parseInt(l.nextToken());

        br.close();

        /*
         * double div = 1.0*N/D; int initial = (int)Math.floor(div); for (int i = 0; i <
         * 5; i++) { int before = (int)(Math.floor(div-initial*Math.pow(10, i))); for
         * (int j = 0; j < 10; j++) { int nines = 0; for (int k = 0; k < j; k++) {
         * nines*=10; nines+=9; } double t = (div*Math.pow(10, i)-before)*nines; int
         * temp = (int)Math.ceil(t); String b = String.copyValueOf(arg0) if
         * (temp-t<0.000001 && temp!=0)
         * System.out.println(initial+"."+b+"("+temp+")\t"+(temp-t)); } }
         */
        String str = "";
        int wholeN = (int) Math.floorDiv(N, D) * D;
        int decN = N - wholeN;
        int max = 15;
        System.out.println("wholeN.\t(roundedN/D)\t(remainderN/D)\t\ti\troundedN\tremainderN");
        loop: for (int i = 0; i <= max; i++) {

            // find the numerator after removing i digits from it
            int powN = decN * (int) Math.pow(10, i);
            int roundedN = (int) Math.floorDiv(powN, D) * D;
            int remainderN = powN - roundedN;
            System.out.print("\n" + wholeN + ".\t" + (roundedN == 0 ? "" : String.valueOf(roundedN / D)) + "\t\t"
                    + String.valueOf(1.0 * remainderN / D).substring(2) + "\t" + i + "\t" + roundedN + "\t\t"
                    + remainderN);
            for (int j = 0; j <= max; j++) {
                double temp = j != 0 ? 1.0 * remainderN * (Math.pow(10, j) - 1.0) / D : 1.0 * remainderN / D;
                if (temp == Math.floor(temp)) {
                    System.out.print("\t" + j + " " + temp);
                    str = (wholeN / D) + "." + (roundedN == 0 ? "" : String.valueOf(roundedN / D))
                            + (j == 0 ? (roundedN == 0 ? roundedN : "") : ("(" + ((int) Math.floor(temp)) + ")"));
                    break loop;
                }
            }
        }

        // create PrintWriter to output results
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        int i;
        for (i = 0; str.length() > (i+78); i += 78) {
            out.println(str.substring(i, i+78));
        }
        out.println(str.substring(i, str.length()));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

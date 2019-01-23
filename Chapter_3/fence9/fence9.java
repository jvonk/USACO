/*
ID: 100021881
LANG: JAVA
PROG: fence9
*/
import java.io.*;
import java.util.Arrays;
import java.math.BigInteger;
import java.nio.*;
public class fence9 {
    public static void main(String[] args) throws Exception {
        int[] l = Arrays.stream(new BufferedReader(new FileReader("fence9.in")).readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
        out.println((l[1]*l[2]/2)+1-(BigInteger.valueOf(l[0]).gcd(BigInteger.valueOf(l[1])).intValue() + l[2] + (l[0]==l[2]?l[1]:BigInteger.valueOf((int)Math.abs(l[0]-l[2])).gcd(BigInteger.valueOf(l[1])).intValue()))/2);
    }
}

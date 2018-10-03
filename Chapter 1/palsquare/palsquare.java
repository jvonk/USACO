
/*
ID: 100021881
LANG: JAVA
PROG: palsquare
*/

import java.io.*;
import java.util.*;

public class palsquare {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        int base = Integer.parseInt(br.readLine());
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J'};
        for (int i = 1; i <= 300; i++) {
            int s = i*i;
            String square = convert(s, base, numbers);
            String iterate = convert(i, base, numbers);
            String reverse = new StringBuilder(square).reverse().toString();
            if (reverse.equals(square)) out.println(iterate+" "+square);
        }
        out.close();
        br.close();
    }
    public static String convert (int input, int b, char[] numbers) {
        String result = "";
        while (input > 0) {
            int rem = input % b;
            result+=numbers[rem];
            input-=rem;
            input/=b;
        }
        return new StringBuilder(result).reverse().toString();
    }
}
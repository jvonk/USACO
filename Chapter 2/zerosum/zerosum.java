
/*
ID: 100021881
LANG: JAVA
PROG: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
        int N = Integer.parseInt(br.readLine());
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        System.out.println(N);
        System.out.println(sum("1 2-3+4"));
        Deque<String> deque = new LinkedList<String>();
        deque.add("");
        while (!deque.isEmpty()) {
            String item = deque.remove();
            int len = item.length() / 2 + 1;
            item += String.valueOf(len);
            //System.out.println(item + "\t" + sum(item));
            if (len < N) {
                deque.add(item + " ");
                deque.add(item + "+");
                deque.add(item + "-");
            } else if (len == N) {
                if (sum(item) == 0) {
                    out.println(item);
                }
            } else {
                break;
            }
        }

        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static int sum(String S) {
        char[] arr = S.toCharArray();
        int len = arr.length;
        int sum = 0;
        int sign = 1;
        for (int i = 0; i < len;) {
            String num = String.valueOf(arr[i]);
            int oldSign = sign;
            int j = 0;
            for (j = i + 1; j < len - 1; j += 2) {
                if (arr[j] != ' ') {
                    if (arr[j] == '-') {
                        sign = -1;
                    } else {
                        sign = 1;
                    }
                    break;
                }
                num += arr[j + 1];
            }
            i = j + 1;
            sum += Integer.parseInt(num) * oldSign;
        }
        return sum;
    }
}

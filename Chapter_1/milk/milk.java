
/*
ID: 100021881
LANG: JAVA
PROG: milk
*/

import java.io.*;
import java.util.*;

public class milk {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        StringTokenizer l = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(l.nextToken());
        int numFarmers = Integer.parseInt(l.nextToken());
        List<Integer> prices = new ArrayList<Integer>();
        List<Integer> amounts = new ArrayList<Integer>();
        
        for (int i = 0; i < numFarmers; i++) {
            StringTokenizer li = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(li.nextToken());
            int amount = Integer.parseInt(li.nextToken());
            int j = 0;
            for (; j < prices.size() && prices.get(j) < price; j++) {}
            prices.add(j, price);
            amounts.add(j, amount);
        }
        int price = 0;
        int i = 0;
        for (i = 0; i < prices.size() && total > 0; i++) {
            total-=amounts.get(i);
            price+=prices.get(i)*amounts.get(i);
        }
        if (total!=0) {
            price+=total*prices.get(i-1);
            total-=total;
        }
        out.println(price);
        out.close();
        br.close();
    }
}
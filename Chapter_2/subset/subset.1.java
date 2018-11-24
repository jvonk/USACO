
/*
ID: 100021881
LANG: JAVA
PROG: subset
*/

import java.io.*;
import java.util.*;

public class subset {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int N = Integer.parseInt(br.readLine());
        int target = N * (N + 1) / 4;
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input.add(i + 1);
        }
        total = 0;
        //solve(1, N, max, new ArrayList<Integer>(), 0);
        Deque<Item> deque = new LinkedList<Item>();
        deque.add(new Item(1, N, 0, new boolean[N+1]));
        while (!deque.isEmpty()) {
            Item current = deque.remove();
            if (current.sum == target) {
                total++;
            }
            if (current.sum < target) {
                for (int i = current.min; i <= current.max; i++) {
                    if (!current.used[i]) {
                        current.used[i]=true;
                        deque.add(new Item (i + 1, current.max, current.sum+i, current.used));
                        current.used[i]=false;
                    }
                }
            }
        }
        out.println(total/2);
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    static int total;

    public static void solve(int min, int max, int target, ArrayList<Integer> partial, int sum) {
        if (sum == target) 
            total++;
        if (sum >= target)
            return;
        for (int i = min; i <= max; i++) {
            partial.add(i);
            solve(i + 1, Math.min(max, target-sum-i), target, partial, sum+i);
            partial.remove(partial.size()-1);
        }
    }
    static class Item {
        int min;
        int max;
        int sum;
        boolean[] used;
        Item (int min, int max, int sum, boolean[] used) {
            this.min = min;
            this.max=max;
            this.sum=sum;
            this.used=used;
        }
    }  
}


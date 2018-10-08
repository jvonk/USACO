
/*
ID: 100021881
LANG: JAVA
PROG: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
    public static void main(String[] args) throws Exception {
        //long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(line.nextToken());
        int end = Integer.parseInt(line.nextToken());
        List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(end) + 1);
        List<Integer> pals = generatePal(0, 0, 3, primes);
        HashSet<Integer> ps = new HashSet<Integer>();
        ps.addAll(pals);
        pals = new ArrayList<Integer>();
        pals.addAll(ps);
        pals.sort(Comparator.naturalOrder());
        for (int i = 0; i < pals.size(); i++) {
            if (pals.get(i)>=start && pals.get(i)<=end) {
                out.println(pals.get(i));
            }
        }
        br.close();
        out.close();
        //System.out.println(System.currentTimeMillis()-startTime);
    }
    public static List<Integer> generatePal(int digits, int N, int max, List<Integer> primes) {
        List<Integer> pals = new ArrayList<Integer>();
        String temp1 = String.valueOf(digits);
        StringBuilder temp2 = new StringBuilder(temp1);
        temp1+=temp2.reverse();
        if (isPrime(Integer.parseInt(temp1), primes)) {
            pals.add(Integer.parseInt(temp1));
        }
        if (N>1) {
            int temp3 = digits/10;
            temp1 = String.valueOf(digits);
            temp2 = new StringBuilder(String.valueOf(temp3));
            temp1+=temp2.reverse();
            if (isPrime(Integer.parseInt(temp1), primes)) {
                pals.add(Integer.parseInt(temp1));
            }
        } else if (isPrime(digits, primes)) {
            pals.add(digits);
        }    
        if (N==0) {
            for (int d = 1; d <= 9; d+=2) {
                pals.addAll(generatePal(d, N+1, max, primes));
            }
        } else if (N<=max) {
            for (int d = 0; d <= 9; d++) {
                int temp = digits*10;
                temp+=d; 
                pals.addAll(generatePal(temp, N+1, max, primes));
            }
        }
        return pals;
    }
    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    public static boolean isPrime(int number, List<Integer> primes) {
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 0; i < primes.size(); i++) {
            if (number % primes.get(i) == 0 && primes.get(i)>1 && primes.get(i) < sqrt) {
                return false;
            }
        }
        return true;
    }
}

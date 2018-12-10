/*
ID: 100021881
LANG: JAVA
PROG: msquare
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;

class Square {
    public int N;
    public String str;
    private int[] squares;

    public Square(int N, int[] squares, String str) {
        this.N = N;
        this.squares = squares;
        this.str = str;
    }

    public int getNum() {
        int res = 0;
        for (int num : squares)
            res = res * 10 + num;
        return res;
    }

    public Square A() {
        int[] res = new int[8];
        for (int i = 0; i < 8; i++)
            res[i] = this.squares[7 - i];
        return new Square(N + 1, res, str + 'A');
    }

    public Square B() {
        int[] res = new int[8];
        for (int i = 0; i < 3; i++)
            res[i + 1] = this.squares[i];
        res[0] = this.squares[3];
        for (int i = 4; i < 7; i++)
            res[i] = this.squares[i + 1];
        res[7] = this.squares[4];
        return new Square(N + 1, res, str + 'B');
    }

    public Square C() {
        int[] res = new int[8];
        for (int i = 0; i < 8; i++)
            res[i] = this.squares[i];
        res[1] = this.squares[6];
        res[2] = this.squares[1];
        res[5] = this.squares[2];
        res[6] = this.squares[5];
        return new Square(N + 1, res, str + 'C');
    }

    public boolean equals(Square other) {
        for (int i = 0; i < 8; i++)
            if (this.squares[i] != other.squares[i])
                return false;
        return true;
    }
}

public class msquare {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis(); // set startTime to measure how long the program takes
        BufferedReader br = new BufferedReader(new FileReader("msquare.in")); // create input BufferedReader from file
        Square target = new Square(0, Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(), "");
        br.close();
        Queue<Square> queue = new LinkedList<Square>();
        Set<Integer> flag = new HashSet<Integer>();
        queue.add(new Square(0, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, ""));
        Square square = null;
        while (!queue.isEmpty()) {
            square = queue.remove();
            int num = square.getNum();
            if (flag.contains(num)) continue;
            flag.add(num);
            if (square.equals(target)) break;
            queue.add(square.A());
            queue.add(square.B());
            queue.add(square.C());
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        out.println(square.N);
        out.println(square.str);
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}
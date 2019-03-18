/*
ID: 100021881
LANG: JAVA
PROG: fc
*/

import java.util.*;
import java.io.*;

public class fc {
    final double EPSILON = 0.0000001;
    final int MAXN = 10010;
    int n;
    double ans = 0;
    List<Integer> upper = new ArrayList<>(), lower = new ArrayList<>(), ans_lower = new ArrayList<>(), ans_upper = new ArrayList<>();
    Point[] x = new Point[MAXN];
    BufferedReader br = new BufferedReader(new FileReader(getClass().getName()+".in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(getClass().getName()+".out")));

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        new fc();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public class Point implements Comparable<Point> {
        public double x, y;
        public Point(double a, double b) {
            x=a;
            y=b;
        }
        @Override
        public int compareTo(Point o) {
            if (y<o.y) return -1;
            if (y==o.y) return Double.compare(x, o.x);
            return 1;
        }
        public double dist (Point o) {
            return Math.sqrt((x-o.x)*(x-o.x) + (y-o.y)*(y-o.y));
        }
    }
    int check_zero(double a) {
        if (Math.abs(a)<EPSILON) return 0;
        return a>0?1:-1;
    }
    double cross_product(double x1, double y1, double x2, double y2) {
        return x1*y2-x2*y1;
    }
    double cross(int a, int b, int c) {
        return cross_product(x[b].x-x[a].x, x[b].y-x[a].y, x[c].x-x[b].x, x[c].y-x[b].y);
    }
    public int check(int a, int b, int c) {
        return check_zero(cross(a, b, c));
    }
    public void run(List<Integer> after, List<Integer> original, int c) {
        for (int i = 1; i <= n; ++i) {
            if (x[i].y != x[i - c].y) {
                original.add(i);
            }
        }
        after.add(original.get(0));
        after.add(original.get(1));
        int i = 2;
        while (i < original.size()) {
            if (after.size()>1&&check(after.get(after.size()-2), after.get(after.size()-1), original.get(i))==c) {
                after.remove(after.size()-1);
            } else {
                after.add(original.get(i++));
            }
        }
        for (i = 0; i < after.size()-1; i++) {
            ans+=x[after.get(i)].dist(x[after.get(i+1)]);
        }
    }
    public fc() throws Exception {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < x.length; i++) {
            x[i] = new Point(0, 0);
        }
        for (int i = 1; i <= n; ++i) {
            String[] line = br.readLine().split(" ");
            x[i]= new Point(Double.parseDouble(line[0]), Double.parseDouble(line[0]));
        }
        Arrays.sort(x);
        x[0].y=-Math.pow(10, -10);
        x[n+1].y=Math.pow(10, -10);
        run(ans_lower, lower, 1);
        run(ans_upper, upper, -1);
        ans+=x[ans_upper.get(0)].dist(x[ans_lower.get(0)]);
        ans+=x[ans_upper.get(ans_upper.size()-1)].dist(x[ans_lower.get(ans_lower.size()-1)]);
        out.println(ans);
        br.close();
        out.close();
    }
}
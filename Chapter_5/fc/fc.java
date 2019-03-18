/*
ID: 100021881
LANG: JAVA
PROG: fc
*/

import java.util.*;
import java.io.*;

public class fc {
    public static class Point implements Comparable<Point> {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public int compareTo(Point o) {
            return Double.compare(Math.acos(x/Math.sqrt(x*x+y*y)), Math.acos(o.x/Math.sqrt(o.x*o.x+o.y*o.y)));
        }
    }
    public static double ccw(Point p1, Point p2, Point p3) {
        return (p2.x-p1.x)*(p3.y-p1.y) - (p2.y-p1.y)*(p3.x-p1.x);
    }
    public static double dist(Point a, Point b) {
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("fc.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N+1];
        for (int i = 1; i <= N; i++) {
            String[]  line = br.readLine().split(" ");
            points[i] = new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
        }
        for (int i = 2; i <= N; i++) {
            if (points[i].y<points[1].y||(points[i].y==points[1].y&&points[i].x<points[1].x)) {
                Point temp = points[1];
                points[1] = points[i];
                points[i]=temp;
            }
        }
        for (int i = 2; i <= N; i++) {
            points[i].x-=points[1].x;
            points[i].y-=points[1].y;
        }
        points[1] = new Point(0, 0);
        Arrays.sort(points, 2, points.length);
        points[0] = points[N];
        int M = 1;
        for (int i = 2; i <= N; i++) {
            int j = i;
            while(ccw(points[M-1], points[M], points[j]) <= 0) {
                if (M>1) {
                    M--;
                    continue;
                }
                if (j==N) break;
                j++;
            }
            M++;
            Point temp = points[M];
            points[M] = points[j];
            points[j]=temp;
        }
        double ans = 0;
        for (int i = 0; i < M; i++) {
            ans+=dist(points[i==0?M:i], points[i+1]);
        }
        out.format("%.2f", ans);
        out.println();
        br.close();
        out.close();
    }
}
/*
ID: 100021881
LANG: JAVA
PROG: fc
*/

import java.util.*;
import java.io.*;

public class fc {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("fc.in"));
        //x(i), y(i) is the x,y position
        //   of the i-th point
        //zcrossprod(v1,v2) -> z component
        //            of the vectors v1, v2
        // if zcrossprod(v1,v2) < 0,
        //    then v2 is "right" of v1
        // since we add counter-clockwise
        //    <0 ->  angle > 180 deg
        //(midx, midy) = (0, 0)
        int npoints = Integer.parseInt(br.readLine());
        Location[] points = new Location[npoints];
        Location[] hull = new Location[npoints];
        for (int i = 0; i < npoints; i++) {
            String[]  line = br.readLine().split(" ");
            points[i]=new Location(i, Double.parseDouble(line[0])/npoints, Double.parseDouble(line[1])/npoints);
        }
        //sort perm based on the angle() values
        //i.e., angle(perm(0)) <= angle(perm(i)) for all i
        Arrays.sort(points);
        //start making hull
        hull[0] = points[0];
        hull[1] = points[1];
        int hullpos = 2;
        for (int i = 0; i < npoints-1; i++) {
            while (hullpos > 1 && Math.atan2(hull[hullpos-2].x-hull[hullpos - 1].x, hull[hullpos-2].y-hull[hullpos - 1].y)-Math.atan2(points[i].x-hull[hullpos - 1].x, points[i].y-hull[hullpos - 1].y)<0){
                hullpos--;
            }
            hull[hullpos++] = points[i];
        }
        //add last point
        Location p = points[npoints-1];
        while (hullpos > 1 && Math.atan2(hull[hullpos-2].x-hull[hullpos - 1].x, hull[hullpos-2].y-hull[hullpos - 1].y)-Math.atan2(hull[hullpos - 1].x-p.x, hull[hullpos - 1].y-p.y)<0){
            hullpos--;
        }
        int hullstart = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            if (hullpos - hullstart >= 2 && Math.atan2(p.x-hull[hullpos - 1].x, p.y-hull[hullpos - 1].y)-Math.atan2(hull[hullpos - 1].x-p.x, hull[hullpos - 1].y-p.y) < 0) {
                p = hull[--hullpos];
                flag = true;
            }
            if (hullpos - hullstart >= 2 && Math.atan2(hull[hullstart].x-p.x, hull[hullpos - 1].y-p.y)-Math.atan2(hull[hullstart].x-hull[hullstart+1].x, hull[hullstart].y-hull[hullstart+1].y) < 0) {
                hullstart++;
                flag = true;
            }
        }
        int length = 0;
        for (int i = 0; i < hull.length-1; i++) {
            length += Math.sqrt(Math.pow(hull[i+1].x-hull[i].x, 2)+Math.pow(hull[i+1].y-hull[i].y, 2));
        }
        length += Math.sqrt(Math.pow(hull[hull.length-1].x-hull[0].x, 2)+Math.pow(hull[hull.length-1].y-hull[0].y, 2));
        System.out.println(p.i);
        hull[hullpos++] = p;
        br.close();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static class Location implements Comparable {
        double angle, x, y;
        int i;
        public Location(int i, double x, double y) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.angle = Math.atan2(x, y);
        }
        public int compareTo(Object o) {
            return Double.compare(this.angle, ((Location)o).angle);
        }
        public boolean check(Location a, Location b) {
            return Math.atan2(a.x-this.x, a.y-this.y)-Math.atan2(b.x-this.x, b.y-this.y)<0;
        }
    }
}
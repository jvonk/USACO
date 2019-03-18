/*
ID: 100021881
LANG: JAVA
PROG: starry
*/

import java.util.*;
import java.io.*;

public class starry {
    static boolean[][] graph;
    static int[][] component;
    static int numComponents;
    static List<Shape> components;
    public static class Point implements Comparable<Point> {
        int x, y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void rotate() {
            int old = x;
            x=-y;
            y=old;
        }
        @Override
        public int compareTo(Point o) {
            int c = Integer.compare(y, o.y);
            return c==0?Integer.compare(x, o.x):c;
        }
    }
    public static class Shape extends ArrayList<Point>{
        int id;
        public Shape(int id) {
            super();
            this.id = id;
        }
        public boolean shallowEquals(Shape o) {
            if (o.size()!=size()) return false;
            Point min = Collections.min(this);
            Point omin = Collections.min(o);
            List<Point> set = new ArrayList<>();
            for (Point p : this) set.add(new Point(p.x-min.x, p.y-min.y));
            l: for (Point p : o) {
                Point n = new Point(p.x-omin.x, p.y-omin.y);
                for (Point m : set) {
                    if (m.x==n.x&&m.y==n.y) continue l;
                }
                return false;
            }
            return true;
        }
        public boolean deepEquals(Shape o) {
            if (o.size()!=size()) return false;
            Shape clone = new Shape(-1);
            for (Point p : this) {
                clone.add(new Point(p.x, p.y));
            }
            for (int i = 0; i < 4; i++) {
                if (clone.shallowEquals(o)) return true;
                for (int j = 0; j < clone.size(); j++) {
                    clone.get(j).rotate();
                }
            }
            clone = new Shape(-1);
            for (Point p : this) {
                clone.add(new Point(-p.x, p.y));
            }
            for (int i = 0; i < 4; i++) {
                if (clone.shallowEquals(o)) return true;
                for (int j = 0; j < clone.size(); j++) {
                    clone.get(j).rotate();
                }
            }
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("starry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));
        int W = Integer.parseInt(br.readLine());
        int H = Integer.parseInt(br.readLine());
        graph = new boolean[H][W];
        component = new int[H][W];
        components = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                graph[i][j] = arr[j]=='1';
            }
        }
        findComponents();
        for (int i = 0; i < components.size(); i++) {
            for (int j = i+1; j < components.size(); j++) {
                if (components.get(i).deepEquals(components.get(j))) {
                    components.get(j).id = components.get(i).id;
                }
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < components.size(); i++) {
            map.putIfAbsent(components.get(i).id, new ArrayList<>());
            map.get(components.get(i).id).add(i);
        }
        List<Integer> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        for (int i = 0; i < keySet.size(); i++) {
            for (int j : map.get(keySet.get(i))) {
                components.get(j).id=i;
            }
        }
        char[][] result = new char[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(result[i], '0');
        }
        for (int i = 0; i < components.size(); i++) {
            for (Point p : components.get(i)) {
                result[p.x][p.y] = (char)('a'+components.get(i).id);
            }
        }
        for (int i = 0; i < H; i++) {
            out.println(result[i]);
        }
        br.close();
        out.close();
    }
    public static void floodFill(int newComponent) {
        int num_visited;
        do {
            num_visited = 0;
            for(int i = 0; i < component.length; i++) {
                for(int j = 0; j < component[i].length; j++) {
                    if(component[i][j] == -2) {
                        num_visited++;
                        component[i][j] = newComponent;
                        components.get(newComponent).add(new Point(i, j));
                        int[][] directions = new int[][] {new int[] {-1, -1}, new int[] {-1, 0}, new int[] {-1, 1}, new int[] {0, -1}, new int[] {0, 1}, new int[] {1, -1}, new int[] {1, 0}, new int[] {1, 1}};
                        for (int[] direction : directions) {
                            int newI = i + direction[0];
                            int newJ = j + direction[1];
                            if(newI >= 0 && newI < component.length && newJ >= 0 && newJ < component[i].length && graph[newI][newJ] && component[newI][newJ]==-1) {
                                component[newI][newJ]=-2;
                            }
                        }
                    }
                }
            }
        } while(num_visited > 0);
    }

    public static void findComponents() {
        numComponents = 0;
        for(int i = 0; i < component.length; i++) {
            Arrays.fill(component[i], -1);
        }
        for(int i = 0; i < component.length; i++) {
            for(int j = 0; j < component[i].length; j++) {
                if(component[i][j] == -1 && graph[i][j]) {
                    component[i][j] = -2;
                    components.add(new Shape(numComponents));
                    floodFill(numComponents++);
                }
            }
        }
    }
}
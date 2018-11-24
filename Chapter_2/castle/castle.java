
/*
ID: 100021881
LANG: JAVA
PROG: castle
*/

import java.io.*;
import java.util.*;

public class castle {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(line.nextToken());
        int height = Integer.parseInt(line.nextToken());
        boolean[][] connections = new boolean[width * height][width * height];
        for (int h = 0; h < height; h++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            for (int w = 0; w < width; w++) {
                int temp = Integer.parseInt(l.nextToken());
                int pos = h * width + w;
                if (temp >= 8) {
                    temp -= 8;
                } else if (h < height - 1) {
                    int south = pos + width;
                    connections[pos][south] = true;
                }
                if (temp >= 4) {
                    temp -= 4;
                } else if (w < width - 1) {
                    int east = pos + 1;
                    connections[pos][east] = true;
                }
                if (temp >= 2) {
                    temp -= 2;
                } else if (h > 0) {
                    int north = pos - width;
                    connections[pos][north] = true;
                }
                if (temp >= 1) {
                    temp--;
                } else if (w > 0) {
                    int west = pos - 1;
                    connections[pos][west] = true;
                }
            }
        }
        List<Integer> component = new ArrayList<Integer>();
        component = findComponents(connections, component);
        int numComponents = component.get(component.size() - 1);
        out.println(numComponents);
        component.remove(component.size() - 1);
        int[] components = new int[numComponents];
        System.out.println(component);
        for (int i = 0; i < component.size(); i++) {
            components[component.get(i)]++;
        }
        int max = 0;
        for (int i = 0; i < components.length; i++) {
            System.out.println("@@"+components[i]);
            if (components[i] > max) {
                max = components[i];
            }
        }
        out.println(max);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < connections.length; i++) {
            for (int j = 0; j < connections.length; j++) {
                if (i != j && (component.get(i) != component.get(j))) {
                    if ((j == i - 1 && i % width != 0) || j == i + width) {
                        int newMax = components[component.get(i)] + components[component.get(j)];
                        if (newMax >= max) {
                            max = newMax;
                            List<Integer> temp = new ArrayList<Integer>();
                            temp.add(max);
                            temp.add(i);
                            temp.add(j);
                            result.add(temp);
                        }
                    }
                }
            }
        }
        out.println(max);
        int[][] results = new int[result.size()][3];

        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).get(0)==max) {
                System.out.println(result.get(i));
                int[] temp = new int[3];
                int mi = result.get(i).get(1);
                int mj = result.get(i).get(2);
                if (mj == mi - 1) {
                    int x = mj % width + 1;
                    int y = mj / width + 1;
                    temp[0]=y;
                    temp[1]=x;
                    temp[2]=0;
                } else if (mj == mi + width) {
                    int x = mj % width + 1;
                    int y = mj / width + 1;
                    temp[0]=y;
                    temp[1]=x;
                    temp[2]=1;
                }
                results[i]=temp;
            } else {
                results[i]=new int[] {10000, 10000, 10000};
            }
        }
        int[] res = new int[3];
        res=results[0];
        System.out.println(res);
        for (int[] resu : results) {
            System.out.println(resu[0]+" "+resu[1]+" "+resu[2]);
            if (resu[0]!=10000) {
                if (resu[1]<res[1]) {
                    res=resu;
                } else if (resu[1]==res[1] && resu[0] > res[0]) {
                    res=resu;
                } else if (resu[0]==res[0] && resu[1] == res[1] && resu[2]==1) {
                    res=resu;
                }
            }
        }
        out.print(res[0]+" "+res[1]+" ");
        if (res[2]==1) {
            out.println("N");
        } else {
            out.println("E");
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static List<Integer> floodFill(boolean[][] connections, List<Integer> component, int newComponent) {
        int num_visited = 0;
        do {
            num_visited = 0;
            for (int i = 0; i < connections.length; i++) {
                if (component.get(i) == -2) {
                    num_visited++;
                    component.set(i, newComponent);
                    for (int j = 0; j < connections.length; j++) {
                        if (component.get(j) == -1 && connections[i][j]) {
                            component.set(j, -2);
                        }
                    }
                }
            }
        } while (num_visited > 0);
        return component;
    }

    public static List<Integer> findComponents(boolean[][] connections, List<Integer> component) {
        int numComponents = 0;
        for (int i = 0; i < connections.length; i++) {
            component.add(-1);
        }
        for (int i = 0; i < connections.length; i++) {
            if (component.get(i) == -1) {
                numComponents++;
                component.set(i, -2);
                component = floodFill(connections, component, numComponents - 1);
            }
        }
        component.add(numComponents);
        return component;
    }
}

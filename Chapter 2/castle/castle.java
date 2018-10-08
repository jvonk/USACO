
/*
ID: 100021881
LANG: JAVA
PROG: castle
*/

import java.io.*;
import java.util.*;

public class castle {
    public static void main(String[] args) throws Exception {
        // long startTime = System.currentTimeMillis();
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
        List<Integer> found = findMax(connections, component);
        out.println(found.get(0));
        out.println(found.get(1));
        int max = 0;
        int maxnum = 0;
        int maxx = 0;
        int maxy = 0;
        char maxDir = '?';
        for (int i = 0; i < connections.length; i++) {
            if (i >= width) {
                component = new ArrayList<Integer>();
                boolean[][] temp = connections;
                int north = i - width;
                if (!temp[i][north]) {
                    temp[i][north] = true;
                    temp[north][i] = true;
                    found = findMax(temp, component);
                    if (found.get(1) > max) {
                        maxnum = found.get(0);
                        max = found.get(1);
                        maxDir = 'N';
                        maxx = i % width + 1;
                        maxy = i / width + 1;
                    } else if (found.get(1) == max) {
                        int x = i % width + 1;
                        int y = i / width + 1;
                        if (x < maxx) {
                            maxnum = found.get(0);
                            max = found.get(1);
                            maxDir = 'N';
                            maxx = x;
                            maxy = y;
                        } else if (x == maxx && y <= maxy) {
                            maxnum = found.get(0);
                            max = found.get(1);
                            maxDir = 'N';
                            maxx = x;
                            maxy = y;
                        }
                    }
                    temp[i][north] = false;
                    temp[north][i] = false;
                }
            }
            if (i % width < width - 1) {
                component = new ArrayList<Integer>();
                boolean[][] temp = connections;
                int east = i + 1;
                if (!temp[i][east]) {
                    temp[i][east] = true;
                    temp[east][i] = true;
                    found = findMax(temp, component);
                    if (found.get(1) > max) {
                        maxnum = found.get(0);
                        max = found.get(1);
                        maxDir = 'E';
                        maxx = i % width + 1;
                        maxy = i / width + 1;
                    } else if (found.get(1) == max) {
                        int x = i % width + 1;
                        int y = i / width + 1;
                        if (x < maxx) {
                            maxnum = found.get(0);
                            max = found.get(1);
                            maxDir = 'E';
                            maxx = x;
                            maxy = y;
                        } else if (x == maxx && y < maxy) {
                            maxnum = found.get(0);
                            max = found.get(1);
                            maxDir = 'E';
                            maxx = x;
                            maxy = y;
                        }
                    }
                    temp[i][east] = false;
                    temp[east][i] = false;
                }
            }
        }
        if(maxnum==1) {
            max=connections.length;
        }
        out.println(max);
        out.println(maxy + " " + maxx + " " + maxDir);
        br.close();
        out.close();
        // System.out.println(System.currentTimeMillis() - startTime);
    }

    public static List<Integer> findMax(boolean[][] connections, List<Integer> component) {
        component = findComponents(connections, component);
        int numComponents = component.get(component.size() - 1);
        int[] components = new int[numComponents + 1];
        for (int i = 0; i < connections.length - 1; i++) {
            components[component.get(i)]++;
        }
        int max = 0;
        for (int i = 0; i < numComponents; i++) {
            if (components[i] > max) {
                max = components[i];
            }
        }
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(numComponents);
        temp.add(max);
        return temp;
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

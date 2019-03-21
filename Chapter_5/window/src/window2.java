/*
ID: 100021881
LANG: JAVA
PROG: window
*/

import java.util.*;
import java.io.*;
import java.awt.Rectangle;

public class window2 {
    public static class Window extends Rectangle {
        int id, x1, y1, x2, y2;
        Window (char id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    static List<Window> windows;
    static long ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("window.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
        windows = new ArrayList<>();
        for (String line = br.readLine(); line!=null; line=br.readLine()) {
            String[] arguments = line.substring(2, line.length()-1).split(",");
            char id = arguments[0].charAt(0);
            switch (line.charAt(0)) {
                case 'w' : windows.add(0, new Window(id, Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4])));
                    break;
                case 't' : {
                    for (int i = 0; i < windows.size(); i++) {
                        if (windows.get(i).id==id) {
                            windows.add(0, windows.remove(i));
                            break;
                        }
                    }
                }
                break;
                case 'b' : {
                    for (int i = 0; i < windows.size(); i++) {
                        if (windows.get(i).id==id) {
                            windows.add(windows.remove(i));
                            break;
                        }
                    }
                }
                break;
                case 'd' : {
                    for (int i = 0; i < windows.size(); i++) {
                        if (windows.get(i).id==id) {
                            windows.remove(i);
                        }
                    }
                }
                break;
                case 's' : {
                    int i = 0;
                    for (; i < windows.size(); i++) {
                        if (windows.get(i).id == id) {
                            break;
                        }
                    }
                    Window w = windows.get(i);
                    ans = 0;
                    dfs(new Window('?', w.x1,w.y1,w.x2,w.y2),i+1);
                }
            }
        }
        br.close();
        out.close();
    }
    private static void dfs(Window w, int floor) {
        if (floor >= windows.size()) {
            ans += (w.x2-w.x1)*(w.y2-w.y1);
            return;
        }
        Window o = windows.get(floor);
        if (o.x1>=w.x2||o.y1>=w.y2||o.x2<=w.x1||o.y2<=w.y1) dfs(w, floor+1);
        else if (o.x1>=w.x1&&o.y1>=w.y1&&o.x2<=w.x2&&o.y2<=w.y2) return;
        else {
            if (o.y1>w.y1&&o.y1<w.y2) dfs(new Window('?', w.x1,o.y1,w.x2,w.y2),floor+1);
            if (o.y2>w.y1&&o.y2<w.y2) dfs(new Window('?', w.x1,w.y1,w.x2,o.y2),floor+1);
            w.y1=Math.max(w.y1,o.y1);
            w.y2=Math.min(w.y2,o.y2);
            if (o.x1>w.x1&&o.x1<w.x2) dfs(new Window('?', o.x1,w.y1,w.x2,w.y2),floor+1);
            if (o.x2>w.x1&&o.x2<w.x2) dfs(new Window('?', w.x1,w.y1,o.x2,w.y2),floor+1);
        }
    }
}
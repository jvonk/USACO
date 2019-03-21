/*
ID: 100021881
LANG: JAVA
PROG: window
*/

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;

public class window3 {
    public static class Window extends Rectangle {
        int id;
        Window (char id, int x1, int y1, int x2, int y2) {
            super(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2)-Math.min(x1,x2),Math.max(y1,y2)-Math.min(y1,y2));
            this.id = id;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("window.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
        List<Window> windows = new ArrayList<>();
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

                    List<Rectangle> add = new ArrayList<>();
                    List<Rectangle> sub = new ArrayList<>();
                    add.add(w);
                    for (int j = 0; j < i; j++) {
                        Window o = windows.get(j);
                        if (!o.intersects(w)) continue;
                        sub.add(o.intersection(w));
                    }
                    boolean flag = true;
                    long area = 0;
                    while (flag) {
                        flag=false;
                        List<Rectangle> newadd = new ArrayList<>();
                        List<Rectangle> newsub = new ArrayList<>();
                        for (int j = 0; j < sub.size(); j++) {
                            Rectangle a = sub.get(j);
                            area-=a.width*a.height;
                            for (int k = j + 1; k < sub.size(); k++) {
                                Rectangle b = sub.get(k);
                                if (!b.intersects(a)) continue;
                                Rectangle c = b.intersection(a);
                                flag=true;
                                newadd.add(c);
                            }
                        }
                        for (int j = 0; j < add.size(); j++) {
                            Rectangle a = add.get(j);
                            area+=a.width*a.height;
                            for (int k = j + 1; k < add.size(); k++) {
                                Rectangle b = add.get(k);
                                if (!b.intersects(a)) continue;
                                Rectangle c = b.intersection(a);
                                flag=true;
                                newsub.add(c);
                            }
                        }
                        for (int j = 0; j < newadd.size(); j++) {
                            List<Integer> nums = new ArrayList<>();
                            for (int k = j+1; k < newadd.size(); k++) {
                                if (newadd.get(j).equals(newadd.get(k))) nums.add(k);
                            }
                            if (nums.size()<=0) continue;

                        }
                        add=newadd;
                        sub=newsub;

                    }

                    out.format("%.3f", 100.0*(area<1?0:area)/w.width/w.height);
                    out.println();
                }
            }
        }
        br.close();
        out.close();
    }
}
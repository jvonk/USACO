/*
ID: 100021881
LANG: JAVA
PROG: window
*/

import java.io.*;
import java.util.*;

public class window4 {
    public static class Window {
        public int up,lf,dn,rt;
        public char id;
        Window (char id,int up, int lf, int dn, int rt) {
            this.up = Math.max(up,dn);
            this.lf = Math.min(lf,rt);
            this.dn = Math.min(up,dn);
            this.rt = Math.max(lf,rt);
            this.id = id;
        }
    }
    static long ans = 0;
    static List<Window> windows;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("window.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
        windows = new ArrayList<>();
        for (String line = br.readLine(); line!=null; line=br.readLine()) {
            String[] arguments = line.substring(2, line.length()-1).split(",");
            char id = arguments[0].charAt(0);
            switch (line.charAt(0)) {
                case 'w' : {
                    windows.add(0, new Window(id,Integer.parseInt(arguments[2]), Integer.parseInt(arguments[1]), Integer.parseInt(arguments[4]), Integer.parseInt(arguments[3])));
                }
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
                    long area = (w.up-w.dn)*(w.rt-w.lf);
                    ans=0;
                    dfs(w.up,w.lf,w.dn,w.rt,i+1);
                    out.format("%.3f\n", 100.0*ans/area);
                }
            }
        }
        br.close();
        out.close();
    }
    private static void dfs(int up, int lf, int dn, int rt, int floor) {
        if (floor>=windows.size()) {
            ans+=(up-dn)*(rt-lf);
            return;
        }
        Window w = windows.get(floor);
        if (up<=w.dn || dn >= w.up || lf >= w.rt || rt <= w.lf) dfs (up,lf,dn,rt,floor+1);
        else if (up <= w.up && dn >= w.dn && lf >= w.lf && rt <= w.rt) return;
        else {
            if (w.up>dn&&w.up<up) dfs(up,lf,w.up,rt,floor+1);
            if (w.dn>dn&&w.dn<up) dfs(w.dn,lf,dn,rt,floor+1);
            up=Math.min(up,w.up);
            dn=Math.max(dn,w.dn);
            if (w.lf>lf&&w.lf<rt) dfs(up,lf,dn,w.lf,floor+1);
            if (w.rt>lf&&w.rt<rt) dfs(up,w.rt,dn,rt,floor+1);
        }
    }
}
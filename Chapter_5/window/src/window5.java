/*
ID: 100021881
LANG: JAVA
PROG: window
*/

import java.io.*;

public class window5 {
    private static class Window {
        int up,lf,dn,rt;
        public Window (int l, int u, int r, int d) {
            dn = Math.min(d,u);
            up = Math.max(d,u);
            lf = Math.min(l,r);
            rt = Math.max(l,r);
        }
    }
    private static int tot=-1, ans = 0;
    private static Window[] f = new Window[500];
    private static char[] order = new char[70];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("window.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
        for (String line = br.readLine(); line!=null; line=br.readLine()) {
            String[] arguments = line.substring(2, line.length()-1).split(",");
            char op = line.charAt(0);
            if (op=='w') f[order[++tot]=arguments[0].charAt(0)] = new Window(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
            else swap(op=='b',op=='d', op=='s', out, arguments[0].charAt(0));
        }
        br.close();
        out.close();
    }
    private static void swap (boolean b, boolean c, boolean d, PrintWriter out, char id) {
        for (int i = b?tot:0; b?i>0:(d?i<=tot:i<tot); i+=b?-1:1) {
            if (order[i]==id) {
                if (d) {
                    ans=0;
                    dfs(f[id].up,f[id].lf,f[id].dn,f[id].rt,i+1);
                    out.format("%.3f\n",100.0*ans/((f[id].up-f[id].dn)*(f[id].rt-f[id].lf)));
                } else {
                    order[i] = order[i + (b ? -1 : 1)];
                    order[i + (b ? -1 : 1)] = id;
                }
                if (c) tot--;
                break;
            }
        }
    }
    private static void dfs(int up, int lf, int dn, int rt, int floor) {
        if (floor>tot) ans+=(up-dn)*(rt-lf);
        else {
            Window w = f[order[floor]];
            if (up <= w.dn || dn >= w.up || lf >= w.rt || rt <= w.lf) dfs(up, lf, dn, rt, floor + 1);
            else if (up > w.up || dn < w.dn || lf < w.lf || rt > w.rt) {
                if (w.up > dn && w.up < up) dfs(up, lf, w.up, rt, floor + 1);
                if (w.dn > dn && w.dn < up) dfs(w.dn, lf, dn, rt, floor + 1);
                if (w.lf > lf && w.lf < rt) dfs(Math.min(up, w.up), lf, Math.max(dn, w.dn), w.lf, floor + 1);
                if (w.rt > lf && w.rt < rt) dfs(Math.min(up, w.up), w.rt, Math.max(dn, w.dn), rt, floor + 1);
            }
        }
    }
}
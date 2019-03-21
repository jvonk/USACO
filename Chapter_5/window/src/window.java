/*
ID: 100021881
LANG: JAVA
PROG: window
*/

import java.io.*;

public class window {
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
            char id = arguments[0].charAt(0);
            char op = line.charAt(0);
            if (op=='w') {
                f[id] = new Window(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
                order[++tot]=id;
            } else if (op=='t'||op=='d') {
                for (int i = 0; i < tot; i++) {
                    if (order[i]==id) {
                        char temp = order[i];
                        order[i]=order[i+1];
                        order[i+1]=temp;
                    }
                }
                if (op=='d') tot--;
            } else if (op=='b') {
                for (int i = tot; i > 0; i--) {
                    if (order[i]==id) {
                        char temp = order[i];
                        order[i]=order[i-1];
                        order[i-1]=temp;
                    }
                }
            } else {
                for (int i = 0; i <= tot; i++) {
                    if (order[i]==id) {
                        ans=0;
                        int up = (f[id].up-f[id].dn)*(f[id].rt-f[id].lf);
                        dfs(f[id].up,f[id].lf,f[id].dn,f[id].rt,i+1);
                        out.format("%.3f\n",100.0*ans/up);
                        break;
                    }
                }
            }
        }
        br.close();
        out.close();
    }
    private static void dfs(int up, int lf, int dn, int rt, int floor) {
        if (floor>tot) {
            ans+=(up-dn)*(rt-lf);
            return;
        }
        Window w = f[order[floor]];
        if (up<=w.dn || dn >= w.up || lf >= w.rt || rt <= w.lf) dfs(up,lf,dn,rt,floor+1);
        else if (up > w.up || dn < w.dn || lf < w.lf || rt > w.rt) {
            if (w.up>dn&&w.up<up) dfs(up,lf,w.up,rt,floor+1);
            if (w.dn>dn&&w.dn<up) dfs(w.dn,lf,dn,rt,floor+1);
            if (w.lf>lf&&w.lf<rt) dfs(Math.min(up,w.up),lf,Math.max(dn,w.dn),w.lf,floor+1);
            if (w.rt>lf&&w.rt<rt) dfs(Math.min(up,w.up),w.rt,Math.max(dn,w.dn),rt,floor+1);
        }
    }
}
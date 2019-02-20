/*
ID: 100021881
LANG: JAVA
PROG: milk6
*/

import java.io.*;
import java.util.*;

/**
 * milk6
 * Use dinic algorithm to find the maximum flow.
 * Sum while you can reach the end using breath first search.
 * Then use depth first to find the minimum nodes that are needed for that sum.
 */

public class milk6 {
    public static long N = 100005, inf = 233333333, mod=1001;
    public static long ans;
    public static int n, m, t, cnt=1, s;
    public static int[] h = new int[100];
    public static long[] dis = new long[2005];
    public static class edge {
        int to, net;
        long v;
    }
    public static edge[] e = new edge[(int)N];
    public static void add(int u, int v, long w) {
        e[++cnt]=new edge();
        e[cnt].to=v;
        e[cnt].net=h[u];
        e[cnt].v=w;
        h[u]=cnt;
        e[++cnt]=new edge();
        e[cnt].to=u;
        e[cnt].net=h[v];
        e[cnt].v=w;
        h[v]=cnt;
    }
    public static Queue<Integer> q = new LinkedList<Integer>();
    public static boolean bfs() {
        Arrays.fill(dis, -1);
        q.add(s);
        dis[s]=0;
        while(!q.isEmpty()) {
            int u = q.remove();
            for (int i = h[u]; i != 0; i=e[i].net) {
                if (dis[e[i].to]==-1&&e[i].v>0) {
                    dis[e[i].to]=dis[u]+1;
                    q.add(e[i].to);
                }
            }
        }
        return dis[t]!=-1;
    }
    public static long dfs(int u, long op) {
        if (u==t) {
            return op;
        }
        long flow = 0, used = 0;
        for (int i = h[u]; i!=0; i=e[i].net) {
            int v = e[i].to;
            if (dis[v]==dis[u]+1&&e[i].v>0) {
                used = dfs(v, Math.min(op, e[i].v));
                if (used==0) continue;
                flow+=used;
                op-=used;
                e[i].v-=used;
                e[i^1].v+=used;
                if (op==0) break;
            }
        }
        if(flow==0)dis[u]=-1;
        return flow;
    }
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        s=1;
        t=n;
        for(int i = 1; i <= m; i++) {
            line = br.readLine().split(" ");
            add(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])*mod+1);
        }
        br.close();
        while(bfs()) {
            ans+=dfs(s, Long.MAX_VALUE);
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        out.println((long)(ans/mod)+" "+(long)(ans%mod));
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
}

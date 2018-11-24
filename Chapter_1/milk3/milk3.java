
/*
ID: 100021881
LANG: JAVA
PROG: milk3
*/

import java.io.*;
import java.util.*;

public class milk3 {
    public static void main(String[] args) throws Exception {
        //long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        br.close();
        int aCap = Integer.parseInt(line.nextToken());
        int bCap = Integer.parseInt(line.nextToken());
        int cCap = Integer.parseInt(line.nextToken());
        int a = 0;
        int b = 0;
        int c = cCap;
        boolean[] temp = new boolean[cCap+1];
        boolean[][][][] checked = new boolean[aCap+1][bCap+1][cCap+1][6];
        for (int i = 0; i < checked.length; i++) {
            for (int j = 0; j < checked[0].length; j++) {
                for (int k = 0; k < checked[0][0].length; k++) {                    
                    Arrays.fill(checked[i][j][k], false);
                }
            }   
        }
    
        temp = solve(a, b, c, aCap, bCap, cCap, 0, 0, temp, checked);
        boolean everTrue = false;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i]) {
                out.print(i);
                everTrue=true;
                if(i<temp.length-1) {
                    out.print(" ");
                }
            }
        }
        if(!everTrue) {
            out.print("NONE");
        }
        out.println();
        out.close();
        //long endTime = System.nanoTime();
        //System.out.println((endTime-startTime)/1000000000.0);
    }

    public static boolean[] solve(int a, int b, int c, int aCap, int bCap, int cCap, int times, int which, boolean[] temp, boolean[][][][] checked) {
        if (!checked[a][b][c][which]) {
            if (times == 0) {
                if (a == 0) {
                    temp[c]=true;
                }
                for (int i = 0; i < 6; i++) {
                    temp=solve(a, b, c, aCap, bCap, cCap, times + 1, i, temp, checked);
                }
            } else if (times < cCap && a >= 0 && c >= 0 && b >= 0 && cCap - c >= 0 && aCap - a >= 0 && bCap - b >= 0) {
                if (which == 0) {
                    //c->a
                    if (aCap>=a+c) {
                        a+=c;
                        c=0;
                    } else {
                        c-=aCap-a;
                        a=aCap;
                    }
                } else if (which == 1) {
                    //c->b
                    if (bCap>=b+c) {
                        b+=c;
                        c=0;
                    } else {
                        c-=bCap-b;
                        b=bCap;
                    }
                } else if (which == 2) {
                    //a->c
                    if (cCap>=c+a) {
                        c+=a;
                        a=0;
                    } else {
                        a-=cCap-c;
                        c=cCap;
                    }
                } else if (which == 3) {
                    //a->b
                    if (bCap>=b+a) {
                        b+=a;
                        a=0;
                    } else {
                        a-=bCap-b;
                        b=bCap;
                    }
                } else if (which == 4) {
                    //b->c
                    if (cCap>=c+b) {
                        c+=b;
                        b=0;
                    } else {
                        b-=cCap-c;
                        c=cCap;
                    }
                } else if (which == 5) {
                    //b->a
                    if (aCap>=a+b) {
                        a+=b;
                        b=0;
                    } else {
                        b-=aCap-a;
                        a=aCap;
                    }
                } else {
                    return temp;
                }
                int[] linked = new int[] {2, 4, 0, 5, 1, 3};
                if (a == 0) {
                    temp[c]=true;
                }
                for (int i = 0; i < 6; i++) {
                    if (i!=which && i!=linked[which])
                        temp = solve(a, b, c, aCap, bCap, cCap, times + 1, i, temp, checked);
                }
            } else {
                return temp;
            }
        }
        checked[a][b][c][which]=true;
        return temp;
    }
}
